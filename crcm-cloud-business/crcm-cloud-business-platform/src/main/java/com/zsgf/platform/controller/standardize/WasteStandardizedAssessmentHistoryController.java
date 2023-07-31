package com.zsgf.platform.controller.standardize;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentHistory;
import com.zsgf.platform.service.standardize.WasteStandardizedAssessmentHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 规范化考核评分记录Controller
 *
 * @author gzl
 * @date 2023-03-24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteStandardizedAssessmentHistory")
@Api(tags = "规范化考核评分记录")
public class WasteStandardizedAssessmentHistoryController extends BaseController {


    private final WasteStandardizedAssessmentHistoryService wasteStandardizedAssessmentHistoryService;

    /**
     * 新增规范化考核评分记录
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增规范化考核评分记录")
    @SysLog(title = "新增规范化考核评分记录", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteStandardizedAssessmentHistory wasteStandardizedAssessmentHistory) {
        return RestResult.succeed(wasteStandardizedAssessmentHistoryService.saveWasteStandardizedAssessmentHistory(wasteStandardizedAssessmentHistory));
    }

    /**
     * 修改规范化考核评分记录
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改规范化考核评分记录")
    @SysLog(title = "修改规范化考核评分记录", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteStandardizedAssessmentHistory wasteStandardizedAssessmentHistory) {
        return RestResult.succeed(wasteStandardizedAssessmentHistoryService.updateWasteStandardizedAssessmentHistory(wasteStandardizedAssessmentHistory));
    }

    /**
     * 删除规范化考核评分记录
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除规范化考核评分记录")
    @SysLog(title = "删除规范化考核评分记录", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteStandardizedAssessmentHistoryService.deleteWasteStandardizedAssessmentHistory(id));
    }


    /**
     * 分页查询规范化考核评分记录
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询规范化考核评分记录")
    public RestResult<PageT<WasteStandardizedAssessmentHistory>> getPage(PageT<WasteStandardizedAssessmentHistory> page, WasteStandardizedAssessmentHistory wasteStandardizedAssessmentHistory) {
        return RestResult.succeed(wasteStandardizedAssessmentHistoryService.findWasteStandardizedAssessmentHistoryPage(page, wasteStandardizedAssessmentHistory));
    }

    /**
     * 查询规范化考核评分记录列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询规范化考核评分记录列表")
    public RestResult<List<WasteStandardizedAssessmentHistory>> getList(WasteStandardizedAssessmentHistory wasteStandardizedAssessmentHistory) {
        return RestResult.succeed(wasteStandardizedAssessmentHistoryService.findWasteStandardizedAssessmentHistoryList(wasteStandardizedAssessmentHistory));
    }

    /**
     * 获取规范化考核评分记录详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取规范化考核评分记录详细信息")
    public RestResult<WasteStandardizedAssessmentHistory> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteStandardizedAssessmentHistoryService.findWasteStandardizedAssessmentHistoryById(id));
    }
}
