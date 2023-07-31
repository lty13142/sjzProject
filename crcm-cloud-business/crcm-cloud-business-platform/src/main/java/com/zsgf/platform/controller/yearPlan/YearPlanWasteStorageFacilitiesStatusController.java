package com.zsgf.platform.controller.yearPlan;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteStorageFacilitiesStatus;
import com.zsgf.platform.service.yearPlan.YearPlanWasteStorageFacilitiesStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_管理计划_08危险废物贮存设施现状Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yearPlanWasteStorageFacilitiesStatus")
@Api(tags = "危险废物_管理计划_08危险废物贮存设施现状")
public class YearPlanWasteStorageFacilitiesStatusController extends BaseController {


    private final YearPlanWasteStorageFacilitiesStatusService yearPlanWasteStorageFacilitiesStatusService;

    /**
     * 新增危险废物_管理计划_08危险废物贮存设施现状
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_管理计划_08危险废物贮存设施现状")
    @SysLog(title = "新增危险废物_管理计划_08危险废物贮存设施现状", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody YearPlanWasteStorageFacilitiesStatus yearPlanWasteStorageFacilitiesStatus) {
        return RestResult.succeed(yearPlanWasteStorageFacilitiesStatusService.saveYearPlanWasteStorageFacilitiesStatus(yearPlanWasteStorageFacilitiesStatus));
    }

    /**
     * 修改危险废物_管理计划_08危险废物贮存设施现状
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_管理计划_08危险废物贮存设施现状")
    @SysLog(title = "修改危险废物_管理计划_08危险废物贮存设施现状", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody YearPlanWasteStorageFacilitiesStatus yearPlanWasteStorageFacilitiesStatus) {
        return RestResult.succeed(yearPlanWasteStorageFacilitiesStatusService.updateYearPlanWasteStorageFacilitiesStatus(yearPlanWasteStorageFacilitiesStatus));
    }

    /**
     * 删除危险废物_管理计划_08危险废物贮存设施现状
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_管理计划_08危险废物贮存设施现状")
    @SysLog(title = "删除危险废物_管理计划_08危险废物贮存设施现状", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanWasteStorageFacilitiesStatusService.deleteYearPlanWasteStorageFacilitiesStatus(id));
    }


    /**
     * 分页查询危险废物_管理计划_08危险废物贮存设施现状
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_管理计划_08危险废物贮存设施现状")
    public RestResult<PageT<YearPlanWasteStorageFacilitiesStatus>> getPage(PageT<YearPlanWasteStorageFacilitiesStatus> page, YearPlanWasteStorageFacilitiesStatus yearPlanWasteStorageFacilitiesStatus) {
        return RestResult.succeed(yearPlanWasteStorageFacilitiesStatusService.findYearPlanWasteStorageFacilitiesStatusPage(page, yearPlanWasteStorageFacilitiesStatus));
    }

    /**
     * 查询危险废物_管理计划_08危险废物贮存设施现状列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_管理计划_08危险废物贮存设施现状列表")
    public RestResult<List<YearPlanWasteStorageFacilitiesStatus>> getList(YearPlanWasteStorageFacilitiesStatus yearPlanWasteStorageFacilitiesStatus) {
        return RestResult.succeed(yearPlanWasteStorageFacilitiesStatusService.findYearPlanWasteStorageFacilitiesStatusList(yearPlanWasteStorageFacilitiesStatus));
    }

    /**
     * 获取危险废物_管理计划_08危险废物贮存设施现状详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_管理计划_08危险废物贮存设施现状详细信息")
    public RestResult<YearPlanWasteStorageFacilitiesStatus> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanWasteStorageFacilitiesStatusService.findYearPlanWasteStorageFacilitiesStatusById(id));
    }
}
