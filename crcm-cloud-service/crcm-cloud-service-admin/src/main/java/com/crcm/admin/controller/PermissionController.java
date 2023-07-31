package com.crcm.admin.controller;

import com.crcm.admin.model.vo.AddPermissionVO;
import com.crcm.admin.model.vo.PermissionVO;
import com.crcm.admin.model.vo.SysRolePermissionVO;
import com.crcm.admin.service.PermissionService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "系统权限接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    private final PermissionService permissionService;
//    @Autowired
//    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @GetMapping("/resources/{roleId}")
    @ApiOperation(value = "查询系统列表")
    public RestResult<List<Long>> getResources(@PathVariable("roleId") String roleId) {
        return RestResult.succeed(permissionService.findRoleResources(roleId));
    }

    @PostMapping("/setRoleResources")
    @ApiOperation(value = "设置角色资源权限")
    @SysLog(title = "设置角色资源权限", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.OTHER)
    public RestResult<Boolean> setRoleResources(@RequestBody PermissionVO t) {
        // 清空动态权限配置，重新加载
//        dynamicSecurityMetadataSource.clearDataSource();
        return RestResult.succeed(permissionService.setRoleResources(t));
    }

    @GetMapping("/menus/{roleId}")
    @ApiOperation(value = "查询系统列表")
    public RestResult<Map<String, List<Long>>> getMenus(@PathVariable("roleId") String roleId) {
        return RestResult.succeed(permissionService.findRoleMenus(roleId));
    }

    @PostMapping("setRoleMenus")
    @ApiOperation(value = "设置角色菜单权限")
    @SysLog(title = "设置角色菜单权限", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.OTHER)
    public RestResult<Boolean> setRoleMenus(@RequestBody PermissionVO t) {
        return RestResult.succeed(permissionService.setRoleMenus(t));
    }

    @GetMapping("/role")
    @ApiOperation(value = "查询权限所有的角色")
    public RestResult<PageT<SysRolePermissionVO>> getPageRole(PageT<SysRolePermissionVO> page, PermissionVO vo) {
        return RestResult.succeed(permissionService.findPermissionRole(page, vo));
    }

    @GetMapping("/role/without")
    @ApiOperation(value = "查询未授权的角色")
    public RestResult<List<SysRolePermissionVO>> getWithoutPermissionRole(PermissionVO vo) {
        return RestResult.succeed(permissionService.findWithoutPermissionRole(vo));
    }

    @PostMapping("/add/menu")
    @ApiOperation(value = "添加资源角色授权角色")
    @SysLog(title = "添加资源角色授权角色", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.OTHER)
    public RestResult<Boolean> addMenuPermission(@Validated @RequestBody AddPermissionVO vo) {
        vo.setType(SystemConstant.PERMISSION_TYPE.MENU);
        return RestResult.succeed(permissionService.addPermission(vo));
    }

    @PostMapping("/add/resource")
    @ApiOperation(value = "添加资源角色授权角色")
    @SysLog(title = "添加资源角色授权角色", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.OTHER)
    public RestResult<Boolean> addResourcePermission(@Validated @RequestBody AddPermissionVO vo) {
        vo.setType(SystemConstant.PERMISSION_TYPE.RESOURCE);
        // 清空动态权限配置，重新加载
//        dynamicSecurityMetadataSource.clearDataSource();
        return RestResult.succeed(permissionService.addPermission(vo));
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除资源角色授权角色")
    @SysLog(title = "删除资源角色授权角色", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> removePermission(@Validated @RequestBody AddPermissionVO vo) {
        // 清空动态权限配置，重新加载
//        dynamicSecurityMetadataSource.clearDataSource();
        return RestResult.succeed(permissionService.removePermission(vo));
    }
}

