package com.zsgf.platform.controller.businessReport;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.businessReport.BusinessDayReport;
import com.zsgf.platform.service.businessReport.BusinessDayReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_经营日报_01基本信息Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/businessDayReport")
@Api(tags = "危险废物_经营日报_01基本信息")
public class BusinessDayReportController extends BaseController {


    private final BusinessDayReportService businessDayReportService;

    /**
     * 新增危险废物_经营日报_01基本信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_经营日报_01基本信息")
    @SysLog(title = "新增危险废物_经营日报_01基本信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody BusinessDayReport businessDayReport) {
        return RestResult.succeed(businessDayReportService.saveBusinessDayReport(businessDayReport));
    }

    /**
     * 修改危险废物_经营日报_01基本信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_经营日报_01基本信息")
    @SysLog(title = "修改危险废物_经营日报_01基本信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody BusinessDayReport businessDayReport) {
        return RestResult.succeed(businessDayReportService.updateBusinessDayReport(businessDayReport));
    }

    /**
     * 删除危险废物_经营日报_01基本信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_经营日报_01基本信息")
    @SysLog(title = "删除危险废物_经营日报_01基本信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(businessDayReportService.deleteBusinessDayReport(id));
    }


    /**
     * 分页查询危险废物_经营日报_01基本信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_经营日报_01基本信息")
    public RestResult<PageT<BusinessDayReport>> getPage(PageT<BusinessDayReport> page, BusinessDayReport businessDayReport) {
        return RestResult.succeed(businessDayReportService.findBusinessDayReportPage(page, businessDayReport));
    }

    /**
     * 查询危险废物_经营日报_01基本信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_经营日报_01基本信息列表")
    public RestResult<List<BusinessDayReport>> getList(BusinessDayReport businessDayReport) {
        return RestResult.succeed(businessDayReportService.findBusinessDayReportList(businessDayReport));
    }

    /**
     * 获取危险废物_经营日报_01基本信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_经营日报_01基本信息详细信息")
    public RestResult<BusinessDayReport> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(businessDayReportService.findBusinessDayReportById(id));
    }
}
