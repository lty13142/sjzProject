package com.zsgf.platform.controller.yearPlan;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.yearPlan.YearPlanProductEquipment;
import com.zsgf.platform.service.yearPlan.YearPlanProductEquipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_管理计划_14产品生产主要生产设备Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yearPlanProductEquipment")
@Api(tags = "危险废物_管理计划_14产品生产主要生产设备")
public class YearPlanProductEquipmentController extends BaseController {


    private final YearPlanProductEquipmentService yearPlanProductEquipmentService;

    /**
     * 新增危险废物_管理计划_14产品生产主要生产设备
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_管理计划_14产品生产主要生产设备")
    @SysLog(title = "新增危险废物_管理计划_14产品生产主要生产设备", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody YearPlanProductEquipment yearPlanProductEquipment) {
        return RestResult.succeed(yearPlanProductEquipmentService.saveYearPlanProductEquipment(yearPlanProductEquipment));
    }

    /**
     * 修改危险废物_管理计划_14产品生产主要生产设备
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_管理计划_14产品生产主要生产设备")
    @SysLog(title = "修改危险废物_管理计划_14产品生产主要生产设备", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody YearPlanProductEquipment yearPlanProductEquipment) {
        return RestResult.succeed(yearPlanProductEquipmentService.updateYearPlanProductEquipment(yearPlanProductEquipment));
    }

    /**
     * 删除危险废物_管理计划_14产品生产主要生产设备
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_管理计划_14产品生产主要生产设备")
    @SysLog(title = "删除危险废物_管理计划_14产品生产主要生产设备", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanProductEquipmentService.deleteYearPlanProductEquipment(id));
    }


    /**
     * 分页查询危险废物_管理计划_14产品生产主要生产设备
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_管理计划_14产品生产主要生产设备")
    public RestResult<PageT<YearPlanProductEquipment>> getPage(PageT<YearPlanProductEquipment> page, YearPlanProductEquipment yearPlanProductEquipment) {
        return RestResult.succeed(yearPlanProductEquipmentService.findYearPlanProductEquipmentPage(page, yearPlanProductEquipment));
    }

    /**
     * 查询危险废物_管理计划_14产品生产主要生产设备列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_管理计划_14产品生产主要生产设备列表")
    public RestResult<List<YearPlanProductEquipment>> getList(YearPlanProductEquipment yearPlanProductEquipment) {
        return RestResult.succeed(yearPlanProductEquipmentService.findYearPlanProductEquipmentList(yearPlanProductEquipment));
    }

    /**
     * 获取危险废物_管理计划_14产品生产主要生产设备详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_管理计划_14产品生产主要生产设备详细信息")
    public RestResult<YearPlanProductEquipment> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanProductEquipmentService.findYearPlanProductEquipmentById(id));
    }
}
