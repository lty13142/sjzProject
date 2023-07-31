package com.zsgf.platform.controller.businessReport;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportNewWasteOutsourcingDisposal;
import com.zsgf.platform.service.businessReport.BusinessYearReportNewWasteOutsourcingDisposalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_经营年报_09新产生危废委外处置Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/businessYearReportNewWasteOutsourcingDisposal")
@Api(tags = "危险废物_经营年报_09新产生危废委外处置")
public class BusinessYearReportNewWasteOutsourcingDisposalController extends BaseController {


    private final BusinessYearReportNewWasteOutsourcingDisposalService businessYearReportNewWasteOutsourcingDisposalService;

    /**
     * 新增危险废物_经营年报_09新产生危废委外处置
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_经营年报_09新产生危废委外处置")
    @SysLog(title = "新增危险废物_经营年报_09新产生危废委外处置", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody BusinessYearReportNewWasteOutsourcingDisposal businessYearReportNewWasteOutsourcingDisposal) {
        return RestResult.succeed(businessYearReportNewWasteOutsourcingDisposalService.saveBusinessYearReportNewWasteOutsourcingDisposal(businessYearReportNewWasteOutsourcingDisposal));
    }

    /**
     * 修改危险废物_经营年报_09新产生危废委外处置
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_经营年报_09新产生危废委外处置")
    @SysLog(title = "修改危险废物_经营年报_09新产生危废委外处置", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody BusinessYearReportNewWasteOutsourcingDisposal businessYearReportNewWasteOutsourcingDisposal) {
        return RestResult.succeed(businessYearReportNewWasteOutsourcingDisposalService.updateBusinessYearReportNewWasteOutsourcingDisposal(businessYearReportNewWasteOutsourcingDisposal));
    }

    /**
     * 删除危险废物_经营年报_09新产生危废委外处置
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_经营年报_09新产生危废委外处置")
    @SysLog(title = "删除危险废物_经营年报_09新产生危废委外处置", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(businessYearReportNewWasteOutsourcingDisposalService.deleteBusinessYearReportNewWasteOutsourcingDisposal(id));
    }


    /**
     * 分页查询危险废物_经营年报_09新产生危废委外处置
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_经营年报_09新产生危废委外处置")
    public RestResult<PageT<BusinessYearReportNewWasteOutsourcingDisposal>> getPage(PageT<BusinessYearReportNewWasteOutsourcingDisposal> page, BusinessYearReportNewWasteOutsourcingDisposal businessYearReportNewWasteOutsourcingDisposal) {
        return RestResult.succeed(businessYearReportNewWasteOutsourcingDisposalService.findBusinessYearReportNewWasteOutsourcingDisposalPage(page, businessYearReportNewWasteOutsourcingDisposal));
    }

    /**
     * 查询危险废物_经营年报_09新产生危废委外处置列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_经营年报_09新产生危废委外处置列表")
    public RestResult<List<BusinessYearReportNewWasteOutsourcingDisposal>> getList(BusinessYearReportNewWasteOutsourcingDisposal businessYearReportNewWasteOutsourcingDisposal) {
        return RestResult.succeed(businessYearReportNewWasteOutsourcingDisposalService.findBusinessYearReportNewWasteOutsourcingDisposalList(businessYearReportNewWasteOutsourcingDisposal));
    }

    /**
     * 获取危险废物_经营年报_09新产生危废委外处置详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_经营年报_09新产生危废委外处置详细信息")
    public RestResult<BusinessYearReportNewWasteOutsourcingDisposal> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(businessYearReportNewWasteOutsourcingDisposalService.findBusinessYearReportNewWasteOutsourcingDisposalById(id));
    }
}
