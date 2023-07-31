package com.zsgf.platform.controller.solid.report;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.solid.report.SolidYearReportWaste;
import com.zsgf.platform.service.solid.report.SolidYearReportWasteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据共享_一般工业固体废物_产生年报_02产废信息Controller
 *
 * @author gzl
 * @date 2023-03-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/solidYearReportWaste")
@Api(tags = "数据共享_一般工业固体废物_产生年报_02产废信息")
public class SolidYearReportWasteController extends BaseController {


    private final SolidYearReportWasteService solidYearReportWasteService;

    /**
     * 新增数据共享_一般工业固体废物_产生年报_02产废信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增数据共享_一般工业固体废物_产生年报_02产废信息")
    @SysLog(title = "新增数据共享_一般工业固体废物_产生年报_02产废信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody SolidYearReportWaste solidYearReportWaste) {
        return RestResult.succeed(solidYearReportWasteService.saveSolidYearReportWaste(solidYearReportWaste));
    }

    /**
     * 修改数据共享_一般工业固体废物_产生年报_02产废信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改数据共享_一般工业固体废物_产生年报_02产废信息")
    @SysLog(title = "修改数据共享_一般工业固体废物_产生年报_02产废信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody SolidYearReportWaste solidYearReportWaste) {
        return RestResult.succeed(solidYearReportWasteService.updateSolidYearReportWaste(solidYearReportWaste));
    }

    /**
     * 删除数据共享_一般工业固体废物_产生年报_02产废信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除数据共享_一般工业固体废物_产生年报_02产废信息")
    @SysLog(title = "删除数据共享_一般工业固体废物_产生年报_02产废信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(solidYearReportWasteService.deleteSolidYearReportWaste(id));
    }


    /**
     * 分页查询数据共享_一般工业固体废物_产生年报_02产废信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询数据共享_一般工业固体废物_产生年报_02产废信息")
    public RestResult<PageT<SolidYearReportWaste>> getPage(PageT<SolidYearReportWaste> page, SolidYearReportWaste solidYearReportWaste) {
        return RestResult.succeed(solidYearReportWasteService.findSolidYearReportWastePage(page, solidYearReportWaste));
    }

    /**
     * 查询数据共享_一般工业固体废物_产生年报_02产废信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询数据共享_一般工业固体废物_产生年报_02产废信息列表")
    public RestResult<List<SolidYearReportWaste>> getList(SolidYearReportWaste solidYearReportWaste) {
        return RestResult.succeed(solidYearReportWasteService.findSolidYearReportWasteList(solidYearReportWaste));
    }

    /**
     * 获取数据共享_一般工业固体废物_产生年报_02产废信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取数据共享_一般工业固体废物_产生年报_02产废信息详细信息")
    public RestResult<SolidYearReportWaste> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(solidYearReportWasteService.findSolidYearReportWasteById(id));
    }
}
