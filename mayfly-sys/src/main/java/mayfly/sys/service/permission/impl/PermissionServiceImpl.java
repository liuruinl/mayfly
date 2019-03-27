package mayfly.sys.service.permission.impl;

import mayfly.common.enums.StatusEnum;
import mayfly.common.exception.BusinessRuntimeException;
import mayfly.common.log.MethodLog;
import mayfly.common.permission.PermissionHandler;
import mayfly.common.permission.registry.PermissionCodeRegistry;
import mayfly.common.utils.EnumUtils;
import mayfly.common.utils.PlaceholderResolver;
import mayfly.common.utils.UUIDUtils;
import mayfly.common.web.UriPattern;
import mayfly.dao.PermissionMapper;
import mayfly.dao.RoleResourceMapper;
import mayfly.entity.Menu;
import mayfly.entity.Permission;
import mayfly.entity.RoleResource;
import mayfly.sys.common.cache.UserCacheKey;
import mayfly.sys.common.enums.MethodEnum;
import mayfly.sys.common.enums.ResourceTypeEnum;
import mayfly.sys.service.base.impl.BaseServiceImpl;
import mayfly.sys.service.permission.MenuService;
import mayfly.sys.service.permission.PermissionService;
import mayfly.sys.web.permission.vo.LoginSuccessVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 权限服务实现类
 * @author: hml
 * @date: 2018/6/26 上午9:49
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper, Permission> implements PermissionService, PermissionCodeRegistry {
    /**
     * 占位符解析器
     */
    private static PlaceholderResolver resolver = PlaceholderResolver.getDefaultResolver();

    /**
     * 权限码与状态分割符号
     */
    public static final String CODE_STATUS_SEPARATOR = ":";

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private MenuService menuService;
    //权限处理器
    private PermissionHandler permissionHandler = PermissionHandler.getInstance();

    @Override
    public PermissionHandler getPermissionHandler() {
        return permissionHandler;
    }

    @MethodLog(value = "保存id以及权限列表", time = true)
    @Override
    public LoginSuccessVO saveIdAndPermission(Integer id) {
        String token = UUIDUtils.generateUUID();
        List<Menu> menus = menuService.getByUserId(id);
        //如果权限被禁用，将会在code后加上:0标志
        List<String> permissionCodes = permissionMapper.selectByUserId(id).stream()
                .map(p -> p.getStatus().equals(StatusEnum.DISABLE.getValue()) ? p.getCode() + CODE_STATUS_SEPARATOR + p.getStatus() : p.getCode())
                .collect(Collectors.toList());
        //缓存用户id
        redisTemplate.opsForValue().set(resolver.resolveByObject(UserCacheKey.USER_ID_KEY, token), id, UserCacheKey.EXPIRE_TIME, TimeUnit.HOURS);
        //保存用户权限code
        permissionHandler.savePermission(id, permissionCodes, UserCacheKey.EXPIRE_TIME, TimeUnit.MINUTES);
        return LoginSuccessVO.builder().token(token).menus(menus).permissions(permissionCodes).build();
    }

    @Override
    public Integer getIdByToken(String token) {
        return (Integer)redisTemplate.opsForValue().get(resolver.resolveByObject(UserCacheKey.USER_ID_KEY, token));
    }

    @Override
    public List<UriPattern> getUriPermissionByToken(String token) {
        return (List<UriPattern>)redisTemplate.opsForValue().get(resolver.resolveByObject(UserCacheKey.USER_PERMISSION_KEY, token));
    }

    @Override
    public Permission changeStatus(Integer id, Integer status) {
        Permission p = getById(id);
        if (p == null) {
            throw new BusinessRuntimeException("该权限不存在！");
        }
        if (!EnumUtils.isExist(StatusEnum.values(), status)) {
            throw new BusinessRuntimeException("权限status错误！");
        }
        if (p.getStatus().equals(status)) {
            return p;
        }
        // 重命名redis key,是禁用则将key改为 code:0形式，否则将code:0改为code
        String code = p.getCode();
        if (StatusEnum.DISABLE.getValue().equals(status)) {
            permissionHandler.disabledPermission(code);
        } else {
            permissionHandler.enablePermission(code);
        }
        //更新数据库
        p.setStatus(status);
        p.setUpdateTime(LocalDateTime.now());
        updateById(p);
        return p;
    }

    @Override
    public Permission savePermission(Permission permission) {
        if (!EnumUtils.isExist(MethodEnum.values(), permission.getMethod())) {
            throw new BusinessRuntimeException("权限method错误！");
        }
        if (countByCondition(Permission.builder().code(permission.getCode()).build()) != 0) {
            throw new BusinessRuntimeException("该权限code已经存在！");
        }
        LocalDateTime now = LocalDateTime.now();
        permission.setCreateTime(now);
        permission.setUpdateTime(now);
        permission.setStatus(StatusEnum.ENABLE.getValue());
        return save(permission);
    }

    @Override
    public Permission updatePermission(Permission permission) {
        if (!EnumUtils.isExist(MethodEnum.values(), permission.getMethod())) {
            throw new BusinessRuntimeException("权限method错误！");
        }
        Permission old = getById(permission.getId());
        if (old == null) {
            throw new BusinessRuntimeException("权限id不存在！");
        }
        // 如果旧的权限code与新权限code不同，则需校验新的code
        if (!old.getCode().equals(permission.getCode())) {
            if (countByCondition(Permission.builder().code(permission.getCode()).build()) != 0) {
                throw new BusinessRuntimeException("该权限code已经存在！");
            }
        }
        permission.setUpdateTime(LocalDateTime.now());
        return updateById(permission);
    }

    @Transactional
    @Override
    public Boolean deletePermission(Integer id) {
        if (deleteById(id)) {
            roleResourceMapper.deleteByCriteria(RoleResource.builder()
                    .resourceId(id).type(ResourceTypeEnum.PERMISSION.getValue()).build());
            return true;
        }
        return false;
    }

    @Override
    public void save(Integer userId, Collection<String> permissionCodes, long time, TimeUnit timeUnit) {
        // 给权限code key添加用户id
        String permissionKey = resolver.resolveByObject(UserCacheKey.USER_PERMISSION_KEY, userId);
        redisTemplate.boundSetOps(permissionKey).add(permissionCodes.toArray());
        redisTemplate.boundSetOps(permissionKey).expire(time, timeUnit);
    }

    @Override
    public void delete(Integer userId) {
        redisTemplate.delete(resolver.resolveByObject(UserCacheKey.USER_PERMISSION_KEY, userId));
    }

    @Override
    public boolean has(Integer userId, String permissionCode) {
        return redisTemplate.opsForSet().isMember(resolver.resolveByObject(UserCacheKey.USER_PERMISSION_KEY, userId), permissionCode);
    }
}