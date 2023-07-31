package com.zsgf.platform.controller.yearPlan;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteStoragePlan;
import com.zsgf.platform.service.yearPlan.YearPlanWasteStoragePlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_管理计划_03危废贮存计划Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yearPlanWasteStoragePlan")
@Api(tags = "危险废物_管理计划_03危废贮存计划")
public class YearPlanWasteStoragePlanController extends BaseController {


    private final YearPlanWasteStoragePlanService yearPlanWasteStoragePlanService;

    /**
     * 新增危险废物_管理计划_03危废贮存计划
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_管理计划_03危废贮存计划")
    @SysLog(title = "新增危险废物_管理计划_03危废贮存计划", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody YearPlanWasteStoragePlan yearPlanWasteStoragePlan) {
        return RestResult.succeed(yearPlanWasteStoragePlanService.saveYearPlanWasteStoragePlan(yearPlanWasteStoragePlan));
    }

    /**
     * 修改危险废物_管理计划_03危废贮存计划
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_管理计划_03危废贮存计划")
    @SysLog(title = "修改危险废物_管理计划_03危废贮存计划", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody YearPlanWasteStoragePlan yearPlanWasteStoragePlan) {
        return RestResult.succeed(yearPlanWasteStoragePlanService.updateYearPlanWasteStoragePlan(yearPlanWasteStoragePlan));
    }

    /**
     * 删除危险废物_管理计划_03危废贮存计划
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_管理计划_03危废贮存计划")
    @SysLog(title = "删除危险废物_管理计划_03危废贮存计划", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanWasteStoragePlanService.deleteYearPlanWasteStoragePlan(id));
    }


    /**
     * 分页查询危险废物_管理计划_03危废贮存计划
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_管理计划_03危废贮存计划")
    public RestResult<PageT<YearPlanWasteStoragePlan>> getPage(PageT<YearPlanWasteStoragePlan> page, YearPlanWasteStoragePlan yearPlanWasteStoragePlan) {
        return RestResult.succeed(yearPlanWasteStoragePlanService.findYearPlanWasteStoragePlanPage(page, yearPlanWasteStoragePlan));
    }

    /**
     * 查询危险废物_管理计划_03危废贮存计划列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_管理计划_03危废贮存计划列表")
    public RestResult<List<YearPlanWasteStoragePlan>> getList(YearPlanWasteStoragePlan yearPlanWasteStoragePlan) {
        return RestResult.succeed(yearPlanWasteStoragePlanService.findYearPlanWasteStoragePlanList(yearPlanWasteStoragePlan));
    }

    /**
     * 获取危险废物_管理计划_03危废贮存计划详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_管理计划_03危废贮存计划详细信息")
    public RestResult<YearPlanWasteStoragePlan> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanWasteStoragePlanService.findYearPlanWasteStoragePlanById(id));
    }
}