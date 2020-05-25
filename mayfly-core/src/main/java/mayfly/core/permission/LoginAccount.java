package mayfly.core.permission;

import mayfly.core.base.model.Result;
import mayfly.core.exception.BusinessRuntimeException;
import mayfly.core.util.ArrayUtils;
import mayfly.core.util.StringUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

/**
 * @author meilin.huang
 * @date 2020-03-03 8:40 上午
 */
public class LoginAccount implements Serializable {

    private static final long serialVersionUID = -8397728352145291302L;

    /**
     * 账号id
     */
    private Long id;

    /**
     * 账号用户名
     */
    private String username;

    /**
     * 账号权限数组
     */
    private String[] permissions;

    /**
     * 账号角色
     */
    private String[] roles;


    /**
     * 权限码与状态分割符号
     */
    public static final String CODE_STATUS_SEPARATOR = ":";

    /**
     * 线程上下文，用于保存到登录账号信息
     */
    private static final ThreadLocal<LoginAccount> CONTEXT = new ThreadLocal<>();

    public LoginAccount() {}
    public LoginAccount(Long id) {
        this.id = id;
    }

    /**
     * 创建登录账号信息
     *
     * @param id 账号id
     * @return {@linkplain LoginAccount}
     */
    public static LoginAccount create(Long id) {
        return new LoginAccount(id);
    }

    /**
     * 设置登录账号上下文
     *
     * @param loginAccount login account
     */
    public static void setToContext(LoginAccount loginAccount) {
        CONTEXT.set(loginAccount);
    }

    /**
     * 获取该线程上下文的登录账号
     */
    public static LoginAccount getForContext() {
        return CONTEXT.get();
    }

    /**
     * 移除登录账号
     */
    public static void removeForContext() {
        CONTEXT.remove();
    }

    /**
     * 从上下文获取登录账号id
     *
     * @return id（上下文没有登录信息则抛没有权限异常）
     */
    public static Long getLoginAccountId() {
        return Optional.ofNullable(LoginAccount.getForContext()).map(LoginAccount::getId)
                .orElseThrow(() ->new BusinessRuntimeException(Result.CodeEnum.NO_PERMISSION));
    }

    /**
     * 判断该账号是否拥有该权限码
     *
     * @param permissionInfo 权限信息
     * @return true：有
     */
    public boolean hasPermission(PermissionInfo permissionInfo) throws PermissionDisabledException {
        if (!permissionInfo.isRequireCode()) {
            return true;
        }
        if (!hasRole(permissionInfo.getRoles())) {
            return false;
        }
        if (ArrayUtils.isEmpty(permissions)) {
            return true;
        }

        // 校验权限code
        String permissionCode = permissionInfo.getPermissionCode();
        if (StringUtils.isEmpty(permissionCode)) {
            return false;
        }
        String[] codes = this.permissions;
        if (ArrayUtils.contains(codes, permissionCode)) {
            return true;
        }
        if (ArrayUtils.contains(codes, getDisablePermissionCode(permissionCode))) {
            throw new PermissionDisabledException();
        }
        return false;
    }

    /**
     * 判断账号的角色是否存在于指定roles中
     *
     * @param roles 角色数组
     * @return true:账号拥有该角色
     */
    public boolean hasRole(String[] roles) {
        String[] selfRoles = this.roles;
        if (ArrayUtils.isEmpty(selfRoles)) {
            return true;
        }
        for (String role : roles) {
            if (ArrayUtils.contains(selfRoles, role)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取权限code对应的禁用code,即code + ":" + 0
     *
     * @param code code
     * @return 禁用code
     */
    public static String getDisablePermissionCode(String code) {
        return code + CODE_STATUS_SEPARATOR + 0;
    }


    //-----    链式赋值调用   -----//

    public LoginAccount username(String username) {
        this.username = username;
        return this;
    }

    public LoginAccount permissions(String[] permissions) {
        this.permissions = permissions;
        return this;
    }

    public LoginAccount permissions(Collection<String> permissions) {
        this.permissions = permissions.toArray(new String[0]);
        return this;
    }

    public LoginAccount roles(String[] roles) {
        this.roles = roles;
        return this;
    }


    //---------  getter setter  --------//

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
