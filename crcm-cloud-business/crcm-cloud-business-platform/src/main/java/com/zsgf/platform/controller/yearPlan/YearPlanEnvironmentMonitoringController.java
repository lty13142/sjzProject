package com.zsgf.platform.controller.yearPlan;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.yearPlan.YearPlanEnvironmentMonitoring;
import com.zsgf.platform.service.yearPlan.YearPlanEnvironmentMonitoringService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_管理计划_17环境监测情况Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yearPlanEnvironmentMonitoring")
@Api(tags = "危险废物_管理计划_17环境监测情况")
public class YearPlanEnvironmentMonitoringController extends BaseController {


    private final YearPlanEnvironmentMonitoringService yearPlanEnvironmentMonitoringService;

    /**
     * 新增危险废物_管理计划_17环境监测情况
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_管理计划_17环境监测情况")
    @SysLog(title = "新增危险废物_管理计划_17环境监测情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody YearPlanEnvironmentMonitoring yearPlanEnvironmentMonitoring) {
        return RestResult.succeed(yearPlanEnvironmentMonitoringService.saveYearPlanEnvironmentMonitoring(yearPlanEnvironmentMonitoring));
    }

    /**
     * 修改危险废物_管理计划_17环境监测情况
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_管理计划_17环境监测情况")
    @SysLog(title = "修改危险废物_管理计划_17环境监测情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody YearPlanEnvironmentMonitoring yearPlanEnvironmentMonitoring) {
        return RestResult.succeed(yearPlanEnvironmentMonitoringService.updateYearPlanEnvironmentMonitoring(yearPlanEnvironmentMonitoring));
    }

    /**
     * 删除危险废物_管理计划_17环境监测情况
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_管理计划_17环境监测情况")
    @SysLog(title = "删除危险废物_管理计划_17环境监测情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanEnvironmentMonitoringService.deleteYearPlanEnvironmentMonitoring(id));
    }


    /**
     * 分页查询危险废物_管理计划_17环境监测情况
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_管理计划_17环境监测情况")
    public RestResult<PageT<YearPlanEnvironmentMonitoring>> getPage(PageT<YearPlanEnvironmentMonitoring> page, YearPlanEnvironmentMonitoring yearPlanEnvironmentMonitoring) {
        return RestResult.succeed(yearPlanEnvironmentMonitoringService.findYearPlanEnvironmentMonitoringPage(page, yearPlanEnvironmentMonitoring));
    }

    /**
     * 查询危险废物_管理计划_17环境监测情况列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_管理计划_17环境监测情况列表")
    public RestResult<List<YearPlanEnvironmentMonitoring>> getList(YearPlanEnvironmentMonitoring yearPlanEnvironmentMonitoring) {
        return RestResult.succeed(yearPlanEnvironmentMonitoringService.findYearPlanEnvironmentMonitoringList(yearPlanEnvironmentMonitoring));
    }

    /**
     * 获取危险废物_管理计划_17环境监测情况详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_管理计划_17环境监测情况详细信息")
    public RestResult<YearPlanEnvironmentMonitoring> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanEnvironmentMonitoringService.findYearPlanEnvironmentMonitoringById(id));
    }
}
