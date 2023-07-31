package com.sjz.evaluations.controller;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.evaluations.model.entity.GrOrganizationManagement;
import com.sjz.evaluations.service.GrOrganizationManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 基层组织管理Controller
 * 
 * @author zzyt
 * @date 2023-04-03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/organizationManagement")
@Api(tags = "基层组织管理")
public class GrOrganizationManagementController extends BaseController {


    private final GrOrganizationManagementService grOrganizationManagementService;

    /**
     * 新增基层组织管理
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增基层组织管理")
    @SysLog(title = "新增基层组织管理", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody GrOrganizationManagement grOrganizationManagement) {
        return RestResult.succeed(grOrganizationManagementService.saveGrOrganizationManagement(grOrganizationManagement));
    }

    /**
     * 修改基层组织管理
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改基层组织管理")
    @SysLog(title = "修改基层组织管理", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody GrOrganizationManagement grOrganizationManagement) {
        return RestResult.succeed(grOrganizationManagementService.updateGrOrganizationManagement(grOrganizationManagement));
    }

    /**
     * 删除基层组织管理
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除基层组织管理")
    @SysLog(title = "删除基层组织管理", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(grOrganizationManagementService.deleteGrOrganizationManagement(id));
    }


    /**
     * 分页查询基层组织管理
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询基层组织管理")
    public RestResult<PageT<GrOrganizationManagement>> getPage(PageT<GrOrganizationManagement> page, GrOrganizationManagement grOrganizationManagement) {
        return RestResult.succeed(grOrganizationManagementService.findGrOrganizationManagementPage(page, grOrganizationManagement));
    }

    /**
     * 查询基层组织管理列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询基层组织管理列表")
    public RestResult<List<GrOrganizationManagement>> getList(GrOrganizationManagement grOrganizationManagement) {
        return RestResult.succeed(grOrganizationManagementService.findGrOrganizationManagementList(grOrganizationManagement));
    }

    /**
     * 获取基层组织管理详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取基层组织管理详细信息")
    public RestResult<GrOrganizationManagement> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(grOrganizationManagementService.findGrOrganizationManagementById(id));
    }
}
