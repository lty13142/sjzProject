package com.zsgf.platform.controller.yearPlan;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteMinimization;
import com.zsgf.platform.service.yearPlan.YearPlanWasteMinimizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_管理计划_18危险废物减量化信息Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yearPlanWasteMinimization")
@Api(tags = "危险废物_管理计划_18危险废物减量化信息")
public class YearPlanWasteMinimizationController extends BaseController {


    private final YearPlanWasteMinimizationService yearPlanWasteMinimizationService;

    /**
     * 新增危险废物_管理计划_18危险废物减量化信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_管理计划_18危险废物减量化信息")
    @SysLog(title = "新增危险废物_管理计划_18危险废物减量化信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody YearPlanWasteMinimization yearPlanWasteMinimization) {
        return RestResult.succeed(yearPlanWasteMinimizationService.saveYearPlanWasteMinimization(yearPlanWasteMinimization));
    }

    /**
     * 修改危险废物_管理计划_18危险废物减量化信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_管理计划_18危险废物减量化信息")
    @SysLog(title = "修改危险废物_管理计划_18危险废物减量化信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody YearPlanWasteMinimization yearPlanWasteMinimization) {
        return RestResult.succeed(yearPlanWasteMinimizationService.updateYearPlanWasteMinimization(yearPlanWasteMinimization));
    }

    /**
     * 删除危险废物_管理计划_18危险废物减量化信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_管理计划_18危险废物减量化信息")
    @SysLog(title = "删除危险废物_管理计划_18危险废物减量化信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanWasteMinimizationService.deleteYearPlanWasteMinimization(id));
    }


    /**
     * 分页查询危险废物_管理计划_18危险废物减量化信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_管理计划_18危险废物减量化信息")
    public RestResult<PageT<YearPlanWasteMinimization>> getPage(PageT<YearPlanWasteMinimization> page, YearPlanWasteMinimization yearPlanWasteMinimization) {
        return RestResult.succeed(yearPlanWasteMinimizationService.findYearPlanWasteMinimizationPage(page, yearPlanWasteMinimization));
    }

    /**
     * 查询危险废物_管理计划_18危险废物减量化信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_管理计划_18危险废物减量化信息列表")
    public RestResult<List<YearPlanWasteMinimization>> getList(YearPlanWasteMinimization yearPlanWasteMinimization) {
        return RestResult.succeed(yearPlanWasteMinimizationService.findYearPlanWasteMinimizationList(yearPlanWasteMinimization));
    }

    /**
     * 获取危险废物_管理计划_18危险废物减量化信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_管理计划_18危险废物减量化信息详细信息")
    public RestResult<YearPlanWasteMinimization> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanWasteMinimizationService.findYearPlanWasteMinimizationById(id));
    }
}
