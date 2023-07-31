package com.zsgf.platform.controller.businessReport;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportNewWasteSelfDisposal;
import com.zsgf.platform.service.businessReport.BusinessYearReportNewWasteSelfDisposalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_经营年报_08新产生危废自行处置Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/businessYearReportNewWasteSelfDisposal")
@Api(tags = "危险废物_经营年报_08新产生危废自行处置")
public class BusinessYearReportNewWasteSelfDisposalController extends BaseController {


    private final BusinessYearReportNewWasteSelfDisposalService businessYearReportNewWasteSelfDisposalService;

    /**
     * 新增危险废物_经营年报_08新产生危废自行处置
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_经营年报_08新产生危废自行处置")
    @SysLog(title = "新增危险废物_经营年报_08新产生危废自行处置", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody BusinessYearReportNewWasteSelfDisposal businessYearReportNewWasteSelfDisposal) {
        return RestResult.succeed(businessYearReportNewWasteSelfDisposalService.saveBusinessYearReportNewWasteSelfDisposal(businessYearReportNewWasteSelfDisposal));
    }

    /**
     * 修改危险废物_经营年报_08新产生危废自行处置
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_经营年报_08新产生危废自行处置")
    @SysLog(title = "修改危险废物_经营年报_08新产生危废自行处置", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody BusinessYearReportNewWasteSelfDisposal businessYearReportNewWasteSelfDisposal) {
        return RestResult.succeed(businessYearReportNewWasteSelfDisposalService.updateBusinessYearReportNewWasteSelfDisposal(businessYearReportNewWasteSelfDisposal));
    }

    /**
     * 删除危险废物_经营年报_08新产生危废自行处置
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_经营年报_08新产生危废自行处置")
    @SysLog(title = "删除危险废物_经营年报_08新产生危废自行处置", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(businessYearReportNewWasteSelfDisposalService.deleteBusinessYearReportNewWasteSelfDisposal(id));
    }


    /**
     * 分页查询危险废物_经营年报_08新产生危废自行处置
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_经营年报_08新产生危废自行处置")
    public RestResult<PageT<BusinessYearReportNewWasteSelfDisposal>> getPage(PageT<BusinessYearReportNewWasteSelfDisposal> page, BusinessYearReportNewWasteSelfDisposal businessYearReportNewWasteSelfDisposal) {
        return RestResult.succeed(businessYearReportNewWasteSelfDisposalService.findBusinessYearReportNewWasteSelfDisposalPage(page, businessYearReportNewWasteSelfDisposal));
    }

    /**
     * 查询危险废物_经营年报_08新产生危废自行处置列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_经营年报_08新产生危废自行处置列表")
    public RestResult<List<BusinessYearReportNewWasteSelfDisposal>> getList(BusinessYearReportNewWasteSelfDisposal businessYearReportNewWasteSelfDisposal) {
        return RestResult.succeed(businessYearReportNewWasteSelfDisposalService.findBusinessYearReportNewWasteSelfDisposalList(businessYearReportNewWasteSelfDisposal));
    }

    /**
     * 获取危险废物_经营年报_08新产生危废自行处置详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_经营年报_08新产生危废自行处置详细信息")
    public RestResult<BusinessYearReportNewWasteSelfDisposal> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(businessYearReportNewWasteSelfDisposalService.findBusinessYearReportNewWasteSelfDisposalById(id));
    }
}
