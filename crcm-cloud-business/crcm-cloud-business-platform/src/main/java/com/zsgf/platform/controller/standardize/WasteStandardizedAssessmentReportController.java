package com.zsgf.platform.controller.standardize;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentReport;
import com.zsgf.platform.service.standardize.WasteStandardizedAssessmentReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标准化考核整改报告Controller
 *
 * @author gzl
 * @date 2023-03-24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteStandardizedAssessmentReport")
@Api(tags = "标准化考核整改报告")
public class WasteStandardizedAssessmentReportController extends BaseController {


    private final WasteStandardizedAssessmentReportService wasteStandardizedAssessmentReportService;

    /**
     * 新增标准化考核整改报告
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增标准化考核整改报告")
    @SysLog(title = "新增标准化考核整改报告", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteStandardizedAssessmentReport wasteStandardizedAssessmentReport) {
        return RestResult.succeed(wasteStandardizedAssessmentReportService.saveWasteStandardizedAssessmentReport(wasteStandardizedAssessmentReport));
    }

    /**
     * 修改标准化考核整改报告
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改标准化考核整改报告")
    @SysLog(title = "修改标准化考核整改报告", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteStandardizedAssessmentReport wasteStandardizedAssessmentReport) {
        return RestResult.succeed(wasteStandardizedAssessmentReportService.updateWasteStandardizedAssessmentReport(wasteStandardizedAssessmentReport));
    }

    /**
     * 删除标准化考核整改报告
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除标准化考核整改报告")
    @SysLog(title = "删除标准化考核整改报告", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteStandardizedAssessmentReportService.deleteWasteStandardizedAssessmentReport(id));
    }


    /**
     * 分页查询标准化考核整改报告
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询标准化考核整改报告")
    public RestResult<PageT<WasteStandardizedAssessmentReport>> getPage(PageT<WasteStandardizedAssessmentReport> page, WasteStandardizedAssessmentReport wasteStandardizedAssessmentReport) {
        return RestResult.succeed(wasteStandardizedAssessmentReportService.findWasteStandardizedAssessmentReportPage(page, wasteStandardizedAssessmentReport));
    }

    /**
     * 查询标准化考核整改报告列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询标准化考核整改报告列表")
    public RestResult<List<WasteStandardizedAssessmentReport>> getList(WasteStandardizedAssessmentReport wasteStandardizedAssessmentReport) {
        return RestResult.succeed(wasteStandardizedAssessmentReportService.findWasteStandardizedAssessmentReportList(wasteStandardizedAssessmentReport));
    }

    /**
     * 获取标准化考核整改报告详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取标准化考核整改报告详细信息")
    public RestResult<WasteStandardizedAssessmentReport> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteStandardizedAssessmentReportService.findWasteStandardizedAssessmentReportById(id));
    }
}
