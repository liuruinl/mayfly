package mayfly.sys.web.permission.controller;

import mayfly.common.log.MethodLog;
import mayfly.common.result.Result;
import mayfly.common.validation.annotation.Valid;
import mayfly.entity.Permission;
import mayfly.sys.common.utils.BeanUtils;
import mayfly.sys.service.permission.PermissionService;
import mayfly.sys.web.form.PageForm;
import mayfly.sys.web.permission.form.PermissionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author meilin.huang
 * @version 1.0
 * @date 2018-12-14 5:09 PM
 */
@mayfly.common.permission.Permission(code = "permission:")
@RestController
@RequestMapping("/sys")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @MethodLog(value = "获取权限列表", result = false, time = true)
    @GetMapping("/v1/permissions")
    public Result list(PermissionForm condition, @Valid PageForm pageForm) {
//        if (status != null) {
//            Permission condition = Permission.builder().status(status).build();
//            return Result.success().withData(permissionService.listByCondition(condition));
//        }
//        List<Permission> allPermissions = new ArrayList<>();
//        WebApplicationContext wc = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
//        RequestMappingHandlerMapping bean = wc.getBean(RequestMappingHandlerMapping.class);
//        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
//        PermissionHandler ph = PermissionHandler.getInstance();
//        for (RequestMappingInfo rmi : handlerMethods.keySet()) {
//            HandlerMethod hm = handlerMethods.get(rmi);
//            Method method = hm.getMethod();
//            PermissionInfo pi = ph.getPermissionInfo(method);
//            if (pi == null) {
//                continue;
//            }
//            String methodName = null;
//            Iterator<RequestMethod> methods = rmi.getMethodsCondition().getMethods().iterator();
//            while (methods.hasNext()) {
//                methodName = methods.next().name();
//            }
//            String uri = null;
//            Iterator uris = rmi.getPatternsCondition().getPatterns().iterator();
//            while (uris.hasNext()) {
//                uri = uris.next().toString();
//            }
//            Permission permission = new Permission();
//            permission.setCode(pi.getPermissionCode());
//            permission.setUriPattern(uri);
//            permission.setMethod(mayfly.common.web.RequestMethod.getByMethodName(methodName).getType());
//            //如有该方法有记录日志，则调取起日志功能描述
//            Optional.ofNullable(method.getAnnotation(MethodLog.class)).ifPresent(log -> permission.setDescription(log.value()));
//            allPermissions.add(permission);
//        }
//
//        //查询入库的api列表
//        List<Permission> addPermissions = permissionService.listAll("create_time DESC");
//        //跟所有需要拦截的的uri进行比较
//        CollectionUtils.CompareResult<Permission> compareResult = CollectionUtils.compare(allPermissions, addPermissions, (Permission p1, Permission p2) ->
//                p1.getCode().equals(p2.getCode()) ? 0 : 1);
//
//        //获取还没有入库的权限
//        Collection<Permission> needAdd = compareResult.getAddValue();
//        needAdd.forEach(n -> n.setStatus((byte)3));
//        //需要删除的权限，即权限不存在了
//        Collection<Permission> del = compareResult.getDelValue();
//        del.forEach(d -> d.setStatus((byte)2));
//        Collection<Permission> unmodified = compareResult.getUnmodifiedValue();
//
//        List<Permission> permissions = new ArrayList<>();
//        permissions.addAll(del);
//        permissions.addAll(needAdd);
//        permissions.addAll(unmodified);
//        return Result.success().withData(permissions);
        Permission p = BeanUtils.copyProperties(condition, Permission.class);
        return Result.success().withData(permissionService.listByCondition(p, pageForm));
    }

    @PostMapping("/v1/permissions")
    @MethodLog("新增权限")
    public Result save(@RequestBody @Valid PermissionForm permissionForm) {
        return Result.success().withData(permissionService.savePermission(BeanUtils.copyProperties(permissionForm, Permission.class)));
    }

    @PutMapping("/v1/permissions/{id}")
    @MethodLog("修改权限")
    public Result update(@RequestBody @Valid PermissionForm permissionForm, @PathVariable Integer id) {
        Permission permission = BeanUtils.copyProperties(permissionForm, Permission.class);
        permission.setId(id);
        return Result.success().withData(permissionService.updatePermission(permission));
    }

    @MethodLog(value = "启用禁用权限", time = true)
    @PutMapping("/v1/permissions/{id}/{status}")
    public Result changeStatus(@PathVariable Integer id, @PathVariable Integer status) {
        return Result.success().withData(permissionService.changeStatus(id, status));
    }

    @DeleteMapping("/v1/permissions/{id}")
    @MethodLog("删除权限")
    public Result del(@PathVariable Integer id) {
        return permissionService.deletePermission(id) ? Result.success() : Result.serverError();
    }
}