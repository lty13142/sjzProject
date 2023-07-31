package com.zsgf.platform.controller.solid.report;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.solid.report.SolidYearReportSelfUse;
import com.zsgf.platform.service.solid.report.SolidYearReportSelfUseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据共享_一般工业固体废物_产生年报_03自行利用情况Controller
 *
 * @author gzl
 * @date 2023-03-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/solidYearReportSelfUse")
@Api(tags = "数据共享_一般工业固体废物_产生年报_03自行利用情况")
public class SolidYearReportSelfUseController extends BaseController {


    private final SolidYearReportSelfUseService solidYearReportSelfUseService;

    /**
     * 新增数据共享_一般工业固体废物_产生年报_03自行利用情况
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增数据共享_一般工业固体废物_产生年报_03自行利用情况")
    @SysLog(title = "新增数据共享_一般工业固体废物_产生年报_03自行利用情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody SolidYearReportSelfUse solidYearReportSelfUse) {
        return RestResult.succeed(solidYearReportSelfUseService.saveSolidYearReportSelfUse(solidYearReportSelfUse));
    }

    /**
     * 修改数据共享_一般工业固体废物_产生年报_03自行利用情况
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改数据共享_一般工业固体废物_产生年报_03自行利用情况")
    @SysLog(title = "修改数据共享_一般工业固体废物_产生年报_03自行利用情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody SolidYearReportSelfUse solidYearReportSelfUse) {
        return RestResult.succeed(solidYearReportSelfUseService.updateSolidYearReportSelfUse(solidYearReportSelfUse));
    }

    /**
     * 删除数据共享_一般工业固体废物_产生年报_03自行利用情况
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除数据共享_一般工业固体废物_产生年报_03自行利用情况")
    @SysLog(title = "删除数据共享_一般工业固体废物_产生年报_03自行利用情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(solidYearReportSelfUseService.deleteSolidYearReportSelfUse(id));
    }


    /**
     * 分页查询数据共享_一般工业固体废物_产生年报_03自行利用情况
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询数据共享_一般工业固体废物_产生年报_03自行利用情况")
    public RestResult<PageT<SolidYearReportSelfUse>> getPage(PageT<SolidYearReportSelfUse> page, SolidYearReportSelfUse solidYearReportSelfUse) {
        return RestResult.succeed(solidYearReportSelfUseService.findSolidYearReportSelfUsePage(page, solidYearReportSelfUse));
    }

    /**
     * 查询数据共享_一般工业固体废物_产生年报_03自行利用情况列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询数据共享_一般工业固体废物_产生年报_03自行利用情况列表")
    public RestResult<List<SolidYearReportSelfUse>> getList(SolidYearReportSelfUse solidYearReportSelfUse) {
        return RestResult.succeed(solidYearReportSelfUseService.findSolidYearReportSelfUseList(solidYearReportSelfUse));
    }

    /**
     * 获取数据共享_一般工业固体废物_产生年报_03自行利用情况详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取数据共享_一般工业固体废物_产生年报_03自行利用情况详细信息")
    public RestResult<SolidYearReportSelfUse> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(solidYearReportSelfUseService.findSolidYearReportSelfUseById(id));
    }
}
