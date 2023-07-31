package com.zsgf.platform.controller.wasteReport;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReportStorage;
import com.zsgf.platform.service.wasteReport.WasteYearReportStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_产生年报_05贮存情况Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteYearReportStorage")
@Api(tags = "危险废物_产生年报_05贮存情况")
public class WasteYearReportStorageController extends BaseController {


    private final WasteYearReportStorageService wasteYearReportStorageService;

    /**
     * 新增危险废物_产生年报_05贮存情况
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_产生年报_05贮存情况")
    @SysLog(title = "新增危险废物_产生年报_05贮存情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteYearReportStorage wasteYearReportStorage) {
        return RestResult.succeed(wasteYearReportStorageService.saveWasteYearReportStorage(wasteYearReportStorage));
    }

    /**
     * 修改危险废物_产生年报_05贮存情况
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_产生年报_05贮存情况")
    @SysLog(title = "修改危险废物_产生年报_05贮存情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteYearReportStorage wasteYearReportStorage) {
        return RestResult.succeed(wasteYearReportStorageService.updateWasteYearReportStorage(wasteYearReportStorage));
    }

    /**
     * 删除危险废物_产生年报_05贮存情况
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_产生年报_05贮存情况")
    @SysLog(title = "删除危险废物_产生年报_05贮存情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteYearReportStorageService.deleteWasteYearReportStorage(id));
    }


    /**
     * 分页查询危险废物_产生年报_05贮存情况
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_产生年报_05贮存情况")
    public RestResult<PageT<WasteYearReportStorage>> getPage(PageT<WasteYearReportStorage> page, WasteYearReportStorage wasteYearReportStorage) {
        return RestResult.succeed(wasteYearReportStorageService.findWasteYearReportStoragePage(page, wasteYearReportStorage));
    }

    /**
     * 查询危险废物_产生年报_05贮存情况列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_产生年报_05贮存情况列表")
    public RestResult<List<WasteYearReportStorage>> getList(WasteYearReportStorage wasteYearReportStorage) {
        return RestResult.succeed(wasteYearReportStorageService.findWasteYearReportStorageList(wasteYearReportStorage));
    }

    /**
     * 获取危险废物_产生年报_05贮存情况详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_产生年报_05贮存情况详细信息")
    public RestResult<WasteYearReportStorage> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteYearReportStorageService.findWasteYearReportStorageById(id));
    }
}
