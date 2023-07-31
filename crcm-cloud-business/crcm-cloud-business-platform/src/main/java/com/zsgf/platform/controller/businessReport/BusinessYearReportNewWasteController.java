package com.zsgf.platform.controller.businessReport;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportNewWaste;
import com.zsgf.platform.service.businessReport.BusinessYearReportNewWasteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_经营年报_07新产生危废情况Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/businessYearReportNewWaste")
@Api(tags = "危险废物_经营年报_07新产生危废情况")
public class BusinessYearReportNewWasteController extends BaseController {


    private final BusinessYearReportNewWasteService businessYearReportNewWasteService;

    /**
     * 新增危险废物_经营年报_07新产生危废情况
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_经营年报_07新产生危废情况")
    @SysLog(title = "新增危险废物_经营年报_07新产生危废情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody BusinessYearReportNewWaste businessYearReportNewWaste) {
        return RestResult.succeed(businessYearReportNewWasteService.saveBusinessYearReportNewWaste(businessYearReportNewWaste));
    }

    /**
     * 修改危险废物_经营年报_07新产生危废情况
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_经营年报_07新产生危废情况")
    @SysLog(title = "修改危险废物_经营年报_07新产生危废情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody BusinessYearReportNewWaste businessYearReportNewWaste) {
        return RestResult.succeed(businessYearReportNewWasteService.updateBusinessYearReportNewWaste(businessYearReportNewWaste));
    }

    /**
     * 删除危险废物_经营年报_07新产生危废情况
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_经营年报_07新产生危废情况")
    @SysLog(title = "删除危险废物_经营年报_07新产生危废情况", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(businessYearReportNewWasteService.deleteBusinessYearReportNewWaste(id));
    }


    /**
     * 分页查询危险废物_经营年报_07新产生危废情况
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_经营年报_07新产生危废情况")
    public RestResult<PageT<BusinessYearReportNewWaste>> getPage(PageT<BusinessYearReportNewWaste> page, BusinessYearReportNewWaste businessYearReportNewWaste) {
        return RestResult.succeed(businessYearReportNewWasteService.findBusinessYearReportNewWastePage(page, businessYearReportNewWaste));
    }

    /**
     * 查询危险废物_经营年报_07新产生危废情况列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_经营年报_07新产生危废情况列表")
    public RestResult<List<BusinessYearReportNewWaste>> getList(BusinessYearReportNewWaste businessYearReportNewWaste) {
        return RestResult.succeed(businessYearReportNewWasteService.findBusinessYearReportNewWasteList(businessYearReportNewWaste));
    }

    /**
     * 获取危险废物_经营年报_07新产生危废情况详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_经营年报_07新产生危废情况详细信息")
    public RestResult<BusinessYearReportNewWaste> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(businessYearReportNewWasteService.findBusinessYearReportNewWasteById(id));
    }
}
