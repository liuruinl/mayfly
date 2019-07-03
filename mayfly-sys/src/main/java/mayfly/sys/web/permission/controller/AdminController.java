package mayfly.sys.web.permission.controller;

import mayfly.common.log.MethodLog;
import mayfly.common.result.Result;
import mayfly.common.validation.annotation.Valid;
import mayfly.sys.service.permission.MenuService;
import mayfly.sys.service.permission.PermissionService;
import mayfly.sys.web.permission.form.AdminLoginForm;
import mayfly.sys.web.permission.form.PermissionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员控制器
 * @author hml
 * @date 2018/6/27 下午4:44
 */
@RestController
@RequestMapping("/open")
public class AdminController {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private MenuService menuService;

    @MethodLog(value = "管理员登录", result = false, time = true)
    @PostMapping("/v1/login")
    public Result login(AdminLoginForm loginForm) {
        return Result.success().withData(permissionService.saveIdAndPermission(1));
    }

    @GetMapping("/v1/test")
    public Result test(@Valid PermissionForm permissionForm) {
        return Result.success();
    }

}
