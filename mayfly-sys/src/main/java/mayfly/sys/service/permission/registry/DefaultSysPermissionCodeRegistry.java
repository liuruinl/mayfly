package mayfly.sys.service.permission.registry;

import mayfly.common.enums.StatusEnum;
import mayfly.common.permission.registry.PermissionCacheHandler;
import mayfly.common.permission.registry.SysPermissionCodeRegistry;
import mayfly.sys.service.permission.PermissionService;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 默认系统权限注册器
 * @author meilin.huang
 * @version 1.0
 * @date 2019-03-28 11:07 AM
 */
public class DefaultSysPermissionCodeRegistry implements SysPermissionCodeRegistry {

    private PermissionService permissionService;

    private Set<String> permissions;

    public DefaultSysPermissionCodeRegistry(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public void save() {
        this.permissions = permissionService.listAll().stream()
                .map(p -> p.getStatus().equals(StatusEnum.DISABLE.getValue()) ? PermissionCacheHandler.getDisablePermissionCode(p.getCode()) : p.getCode())
                .collect(Collectors.toSet());
    }

    @Override
    public boolean has(String permissionCode) {
        return permissions.contains(permissionCode);
    }

    @Override
    public void rename(String oldCode, String newCode) {
        permissions.remove(oldCode);
        permissions.add(newCode);
    }
}