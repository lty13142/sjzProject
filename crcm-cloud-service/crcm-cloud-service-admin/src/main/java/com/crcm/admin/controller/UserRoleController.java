package com.crcm.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.admin.model.entity.SysRole;
import com.crcm.admin.model.entity.SysUserRole;
import com.crcm.admin.model.vo.AddUserRoleVO;
import com.crcm.admin.model.vo.UserRoleEditVO;
import com.crcm.admin.model.vo.UserRoleVO;
import com.crcm.admin.service.UserRoleService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户角色接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/userRole")
public class UserRoleController extends BaseController {

    private final UserRoleService userRoleService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询用户角色")
    public RestResult<Page<SysUserRole>> getPage(SysUserRole t, Page<SysUserRole> page) {
        return RestResult.succeed(userRoleService.findPage(page, t));
    }

    @GetMapping("/getUserRoles")
    @ApiOperation(value = "查询用户角色列表")
    public RestResult<List<SysRole>> getUserRoles(String userId) {
        return RestResult.succeed(userRoleService.findUserRoles(userId));
    }

    @GetMapping("/page/user")
    @ApiOperation(value = "分页查询拥有角色授权的用户")
    public RestResult<PageT<UserRoleVO>> getRoleUsers(PageT<UserRoleVO> page, Long roleId) {
        return RestResult.succeed(userRoleService.findPageRoleUsers(page, roleId));
    }

    @GetMapping("/page/user/without")
    @ApiOperation(value = "查询没有角色授权的用户")
    public RestResult<PageT<UserRoleVO>> getWithoutRoleUsers(PageT<UserRoleVO> page, UserRoleVO vo) {
        return RestResult.succeed(userRoleService.findWithoutRoleUsers(page, vo));
    }

    @GetMapping("/getRoleUsers")
    @ApiOperation(value = "查询拥有角色授权的用户")
    public RestResult<List<UserRoleVO>> getRoleUsers(Long roleId) {
        return RestResult.succeed(userRoleService.findRoleUsers(roleId));
    }


    @PostMapping("/distribution")
    @ApiOperation(value = "分配用户角色")
    @SysLog(title = "分配用户角色", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Object> distribution(@RequestBody UserRoleEditVO t) {
        userRoleService.distribution(t);
        return RestResult.succeed();
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加用户角色关系")
    @SysLog(title = "添加用户角色关系", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Object> addUserRole(@Validated @RequestBody AddUserRoleVO vo) {
        userRoleService.addUserRole(vo);
        return RestResult.succeed();
    }

    @PostMapping("/remove")
    @ApiOperation(value = "移除用户角色关系")
    @SysLog(title = "移除用户角色关系", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Object> removeUserRole(@Validated @RequestBody AddUserRoleVO vo) {
        userRoleService.removeUserRole(vo);
        return RestResult.succeed();
    }
}

