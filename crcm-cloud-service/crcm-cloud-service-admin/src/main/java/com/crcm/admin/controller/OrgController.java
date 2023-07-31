package com.crcm.admin.controller;


import com.crcm.admin.model.entity.SysOrg;
import com.crcm.admin.service.OrgService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.cloud.start.sso.annation.Inner;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.dto.TreeView;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "部门组织表接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/org")
public class OrgController extends BaseController {

    private final OrgService orgService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @SysLog(title = "新增部门组织", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Integer> save(@RequestBody SysOrg t) {
        return RestResult.succeed(orgService.saveOrg(t));
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改部门组织", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Integer> update(@Validated @RequestBody SysOrg t) {
        return RestResult.succeed(orgService.updateOrg(t));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @SysLog(title = "删除部门组织", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Integer> delete(@RequestBody SysOrg t) {
        return RestResult.succeed(orgService.deleteById(t.getId()));
    }

    @Inner
    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult<SysOrg> getById(@RequestParam("orgId")Long orgId) {

        return RestResult.succeed(orgService.findById(orgId));
    }

    @GetMapping("/getPage")
    @ApiOperation(value = "组织分页查询")
    @SysLog(title = "组织分页查询", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.UPDATE)
    @ApiResponses(@ApiResponse(code = 200, message = "请求成功", response = SysOrg.class))
    public RestResult<PageT<SysOrg>> getPage(SysOrg t, PageT<SysOrg> page) {
        return RestResult.succeed(orgService.findPage(page, t));
    }

    @GetMapping("/getTree")
    @ApiOperation(value = "查询组织树")
    public RestResult<List<TreeView>> getTree(SysOrg t) {
        return RestResult.succeed(orgService.findTree(t));
    }

    @GetMapping("/getList")
    @ApiOperation(value = "查询组织列表")
    public RestResult<List<SysOrg>> getList(SysOrg t) {
        return RestResult.succeed(orgService.findList(t));
    }

    @GetMapping("/getTreeOrg")
    @ApiOperation(value = "查询类型为组织的树")
    public RestResult<List<TreeView>> getTreeOrg(SysOrg t) {
        return RestResult.succeed(orgService.findTreeOrg(t));
    }

    @GetMapping(value = "/getResponsibilityOrg")
    @ApiOperation(value = "查询负责部门")
    public RestResult getResponsibilityOrg() {
        return RestResult.succeed(orgService.findResponsibilityOrg());
    }

}

