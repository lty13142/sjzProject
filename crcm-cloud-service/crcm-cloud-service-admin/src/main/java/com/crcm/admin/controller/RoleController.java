package com.crcm.admin.controller;

import com.crcm.admin.model.dto.SysRoleQueryDTO;
import com.crcm.admin.model.entity.SysRole;
import com.crcm.admin.service.RoleService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "角色接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController extends BaseController {

    private final RoleService roleService;

    @PostMapping("/save")
    @ApiOperation(value = "保存角色")
    @SysLog(title = "保存角色", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> saveRole(@Valid @RequestBody SysRole t) {
        String result = roleService.saveRole(t);
        return StringUtils.isBlank(result) ? RestResult.succeed() : RestResult.failed(result);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改角色")
    @SysLog(title = "修改角色", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> updateRole(@Valid @RequestBody SysRole t) {
        String result = roleService.updateRole(t);
        return StringUtils.isBlank(result) ? RestResult.succeed() : RestResult.failed(result);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除角色")
    @SysLog(title = "删除角色", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> deleteById(@PathVariable("id") Long id) {
        return RestResult.succeed(roleService.deleteById(id));
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询角色")
    public RestResult<SysRole> getById(String id) {
        return RestResult.succeed(roleService.findById(id));
    }

    @GetMapping("/getPage")
    @ApiOperation(value = "分页查询角色")
    public RestResult<PageT<SysRole>> getPageRole(SysRoleQueryDTO t, PageT<SysRole> page) {
        return RestResult.succeed(roleService.findPageRole(page, t));
    }

    @GetMapping("/getList")
    @ApiOperation(value = "获取角色列表")
    public RestResult<List<SysRole>> getList(SysRoleQueryDTO t) {
        return RestResult.succeed(roleService.findList(t));
    }


}

