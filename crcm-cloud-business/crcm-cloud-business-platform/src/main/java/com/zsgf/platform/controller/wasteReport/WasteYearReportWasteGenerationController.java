package com.zsgf.platform.controller.wasteReport;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReportWasteGeneration;
import com.zsgf.platform.service.wasteReport.WasteYearReportWasteGenerationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_产生年报_02废物产生情况Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteYearReportWasteGeneration")
@Api(tags = "危险废物_产生年报_02废物产生情况")
public class WasteYearReportWasteGenerationController extends BaseController {


    private final WasteYearReportWasteGenerationService wasteYearReportWasteGenerationService;

    /**
     * 新增危险废物_产生年报_02废物产生情况
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_产生年报_02废物产生情况")
    @SysLog(title = "新增危险废物_产生年报_02废物产生情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteYearReportWasteGeneration wasteYearReportWasteGeneration) {
        return RestResult.succeed(wasteYearReportWasteGenerationService.saveWasteYearReportWasteGeneration(wasteYearReportWasteGeneration));
    }

    /**
     * 修改危险废物_产生年报_02废物产生情况
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_产生年报_02废物产生情况")
    @SysLog(title = "修改危险废物_产生年报_02废物产生情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteYearReportWasteGeneration wasteYearReportWasteGeneration) {
        return RestResult.succeed(wasteYearReportWasteGenerationService.updateWasteYearReportWasteGeneration(wasteYearReportWasteGeneration));
    }

    /**
     * 删除危险废物_产生年报_02废物产生情况
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_产生年报_02废物产生情况")
    @SysLog(title = "删除危险废物_产生年报_02废物产生情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteYearReportWasteGenerationService.deleteWasteYearReportWasteGeneration(id));
    }


    /**
     * 分页查询危险废物_产生年报_02废物产生情况
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_产生年报_02废物产生情况")
    public RestResult<PageT<WasteYearReportWasteGeneration>> getPage(PageT<WasteYearReportWasteGeneration> page, WasteYearReportWasteGeneration wasteYearReportWasteGeneration) {
        return RestResult.succeed(wasteYearReportWasteGenerationService.findWasteYearReportWasteGenerationPage(page, wasteYearReportWasteGeneration));
    }

    /**
     * 查询危险废物_产生年报_02废物产生情况列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_产生年报_02废物产生情况列表")
    public RestResult<List<WasteYearReportWasteGeneration>> getList(WasteYearReportWasteGeneration wasteYearReportWasteGeneration) {
        return RestResult.succeed(wasteYearReportWasteGenerationService.findWasteYearReportWasteGenerationList(wasteYearReportWasteGeneration));
    }

    /**
     * 获取危险废物_产生年报_02废物产生情况详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_产生年报_02废物产生情况详细信息")
    public RestResult<WasteYearReportWasteGeneration> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteYearReportWasteGenerationService.findWasteYearReportWasteGenerationById(id));
    }
}
