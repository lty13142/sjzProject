package com.zsgf.platform.controller.solid;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.solid.SolidStorageDisposalSite;
import com.zsgf.platform.service.solid.SolidStorageDisposalSiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据共享_一般工业固体废物_贮存处置场信息Controller
 *
 * @author gzl
 * @date 2023-03-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/solidStorageDisposalSite")
@Api(tags = "数据共享_一般工业固体废物_贮存处置场信息")
public class SolidStorageDisposalSiteController extends BaseController {


    private final SolidStorageDisposalSiteService solidStorageDisposalSiteService;

    /**
     * 新增数据共享_一般工业固体废物_贮存处置场信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增数据共享_一般工业固体废物_贮存处置场信息")
    @SysLog(title = "新增数据共享_一般工业固体废物_贮存处置场信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody SolidStorageDisposalSite solidStorageDisposalSite) {
        return RestResult.succeed(solidStorageDisposalSiteService.saveSolidStorageDisposalSite(solidStorageDisposalSite));
    }

    /**
     * 修改数据共享_一般工业固体废物_贮存处置场信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改数据共享_一般工业固体废物_贮存处置场信息")
    @SysLog(title = "修改数据共享_一般工业固体废物_贮存处置场信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody SolidStorageDisposalSite solidStorageDisposalSite) {
        return RestResult.succeed(solidStorageDisposalSiteService.updateSolidStorageDisposalSite(solidStorageDisposalSite));
    }

    /**
     * 删除数据共享_一般工业固体废物_贮存处置场信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除数据共享_一般工业固体废物_贮存处置场信息")
    @SysLog(title = "删除数据共享_一般工业固体废物_贮存处置场信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(solidStorageDisposalSiteService.deleteSolidStorageDisposalSite(id));
    }


    /**
     * 分页查询数据共享_一般工业固体废物_贮存处置场信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询数据共享_一般工业固体废物_贮存处置场信息")
    public RestResult<PageT<SolidStorageDisposalSite>> getPage(PageT<SolidStorageDisposalSite> page, SolidStorageDisposalSite solidStorageDisposalSite) {
        return RestResult.succeed(solidStorageDisposalSiteService.findSolidStorageDisposalSitePage(page, solidStorageDisposalSite));
    }

    /**
     * 查询数据共享_一般工业固体废物_贮存处置场信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询数据共享_一般工业固体废物_贮存处置场信息列表")
    public RestResult<List<SolidStorageDisposalSite>> getList(SolidStorageDisposalSite solidStorageDisposalSite) {
        return RestResult.succeed(solidStorageDisposalSiteService.findSolidStorageDisposalSiteList(solidStorageDisposalSite));
    }

    /**
     * 获取数据共享_一般工业固体废物_贮存处置场信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取数据共享_一般工业固体废物_贮存处置场信息详细信息")
    public RestResult<SolidStorageDisposalSite> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(solidStorageDisposalSiteService.findSolidStorageDisposalSiteById(id));
    }
}
