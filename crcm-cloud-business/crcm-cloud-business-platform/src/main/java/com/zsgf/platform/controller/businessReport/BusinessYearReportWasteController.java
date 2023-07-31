package com.zsgf.platform.controller.businessReport;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportWaste;
import com.zsgf.platform.service.businessReport.BusinessYearReportWasteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_经营年报_03经营废物信息Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/businessYearReportWaste")
@Api(tags = "危险废物_经营年报_03经营废物信息")
public class BusinessYearReportWasteController extends BaseController {


    private final BusinessYearReportWasteService businessYearReportWasteService;

    /**
     * 新增危险废物_经营年报_03经营废物信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_经营年报_03经营废物信息")
    @SysLog(title = "新增危险废物_经营年报_03经营废物信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody BusinessYearReportWaste businessYearReportWaste) {
        return RestResult.succeed(businessYearReportWasteService.saveBusinessYearReportWaste(businessYearReportWaste));
    }

    /**
     * 修改危险废物_经营年报_03经营废物信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_经营年报_03经营废物信息")
    @SysLog(title = "修改危险废物_经营年报_03经营废物信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody BusinessYearReportWaste businessYearReportWaste) {
        return RestResult.succeed(businessYearReportWasteService.updateBusinessYearReportWaste(businessYearReportWaste));
    }

    /**
     * 删除危险废物_经营年报_03经营废物信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_经营年报_03经营废物信息")
    @SysLog(title = "删除危险废物_经营年报_03经营废物信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(businessYearReportWasteService.deleteBusinessYearReportWaste(id));
    }


    /**
     * 分页查询危险废物_经营年报_03经营废物信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_经营年报_03经营废物信息")
    public RestResult<PageT<BusinessYearReportWaste>> getPage(PageT<BusinessYearReportWaste> page, BusinessYearReportWaste businessYearReportWaste) {
        return RestResult.succeed(businessYearReportWasteService.findBusinessYearReportWastePage(page, businessYearReportWaste));
    }

    /**
     * 查询危险废物_经营年报_03经营废物信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_经营年报_03经营废物信息列表")
    public RestResult<List<BusinessYearReportWaste>> getList(BusinessYearReportWaste businessYearReportWaste) {
        return RestResult.succeed(businessYearReportWasteService.findBusinessYearReportWasteList(businessYearReportWaste));
    }

    /**
     * 获取危险废物_经营年报_03经营废物信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_经营年报_03经营废物信息详细信息")
    public RestResult<BusinessYearReportWaste> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(businessYearReportWasteService.findBusinessYearReportWasteById(id));
    }
}