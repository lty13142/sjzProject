package com.zsgf.platform.controller.wasteReport;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReportOutsourcingUse;
import com.zsgf.platform.service.wasteReport.WasteYearReportOutsourcingUseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_产生年报_04委外利用情况Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteYearReportOutsourcingUse")
@Api(tags = "危险废物_产生年报_04委外利用情况")
public class WasteYearReportOutsourcingUseController extends BaseController {


    private final WasteYearReportOutsourcingUseService wasteYearReportOutsourcingUseService;

    /**
     * 新增危险废物_产生年报_04委外利用情况
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_产生年报_04委外利用情况")
    @SysLog(title = "新增危险废物_产生年报_04委外利用情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteYearReportOutsourcingUse wasteYearReportOutsourcingUse) {
        return RestResult.succeed(wasteYearReportOutsourcingUseService.saveWasteYearReportOutsourcingUse(wasteYearReportOutsourcingUse));
    }

    /**
     * 修改危险废物_产生年报_04委外利用情况
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_产生年报_04委外利用情况")
    @SysLog(title = "修改危险废物_产生年报_04委外利用情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteYearReportOutsourcingUse wasteYearReportOutsourcingUse) {
        return RestResult.succeed(wasteYearReportOutsourcingUseService.updateWasteYearReportOutsourcingUse(wasteYearReportOutsourcingUse));
    }

    /**
     * 删除危险废物_产生年报_04委外利用情况
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_产生年报_04委外利用情况")
    @SysLog(title = "删除危险废物_产生年报_04委外利用情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteYearReportOutsourcingUseService.deleteWasteYearReportOutsourcingUse(id));
    }


    /**
     * 分页查询危险废物_产生年报_04委外利用情况
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_产生年报_04委外利用情况")
    public RestResult<PageT<WasteYearReportOutsourcingUse>> getPage(PageT<WasteYearReportOutsourcingUse> page, WasteYearReportOutsourcingUse wasteYearReportOutsourcingUse) {
        return RestResult.succeed(wasteYearReportOutsourcingUseService.findWasteYearReportOutsourcingUsePage(page, wasteYearReportOutsourcingUse));
    }

    /**
     * 查询危险废物_产生年报_04委外利用情况列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_产生年报_04委外利用情况列表")
    public RestResult<List<WasteYearReportOutsourcingUse>> getList(WasteYearReportOutsourcingUse wasteYearReportOutsourcingUse) {
        return RestResult.succeed(wasteYearReportOutsourcingUseService.findWasteYearReportOutsourcingUseList(wasteYearReportOutsourcingUse));
    }

    /**
     * 获取危险废物_产生年报_04委外利用情况详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_产生年报_04委外利用情况详细信息")
    public RestResult<WasteYearReportOutsourcingUse> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteYearReportOutsourcingUseService.findWasteYearReportOutsourcingUseById(id));
    }
}