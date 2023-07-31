package com.zsgf.platform.controller.wasteReport;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.wasteReport.WasteMonthReportStorage;
import com.zsgf.platform.service.wasteReport.WasteMonthReportStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_产生月报_05产废贮存信息Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteMonthReportStorage")
@Api(tags = "危险废物_产生月报_05产废贮存信息")
public class WasteMonthReportStorageController extends BaseController {


    private final WasteMonthReportStorageService wasteMonthReportStorageService;

    /**
     * 新增危险废物_产生月报_05产废贮存信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_产生月报_05产废贮存信息")
    @SysLog(title = "新增危险废物_产生月报_05产废贮存信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteMonthReportStorage wasteMonthReportStorage) {
        return RestResult.succeed(wasteMonthReportStorageService.saveWasteMonthReportStorage(wasteMonthReportStorage));
    }

    /**
     * 修改危险废物_产生月报_05产废贮存信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_产生月报_05产废贮存信息")
    @SysLog(title = "修改危险废物_产生月报_05产废贮存信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteMonthReportStorage wasteMonthReportStorage) {
        return RestResult.succeed(wasteMonthReportStorageService.updateWasteMonthReportStorage(wasteMonthReportStorage));
    }

    /**
     * 删除危险废物_产生月报_05产废贮存信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_产生月报_05产废贮存信息")
    @SysLog(title = "删除危险废物_产生月报_05产废贮存信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteMonthReportStorageService.deleteWasteMonthReportStorage(id));
    }


    /**
     * 分页查询危险废物_产生月报_05产废贮存信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_产生月报_05产废贮存信息")
    public RestResult<PageT<WasteMonthReportStorage>> getPage(PageT<WasteMonthReportStorage> page, WasteMonthReportStorage wasteMonthReportStorage) {
        return RestResult.succeed(wasteMonthReportStorageService.findWasteMonthReportStoragePage(page, wasteMonthReportStorage));
    }

    /**
     * 查询危险废物_产生月报_05产废贮存信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_产生月报_05产废贮存信息列表")
    public RestResult<List<WasteMonthReportStorage>> getList(WasteMonthReportStorage wasteMonthReportStorage) {
        return RestResult.succeed(wasteMonthReportStorageService.findWasteMonthReportStorageList(wasteMonthReportStorage));
    }

    /**
     * 获取危险废物_产生月报_05产废贮存信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_产生月报_05产废贮存信息详细信息")
    public RestResult<WasteMonthReportStorage> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteMonthReportStorageService.findWasteMonthReportStorageById(id));
    }
}
