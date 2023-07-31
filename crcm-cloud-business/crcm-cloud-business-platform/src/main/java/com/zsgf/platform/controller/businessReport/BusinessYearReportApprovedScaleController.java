package com.zsgf.platform.controller.businessReport;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportApprovedScale;
import com.zsgf.platform.service.businessReport.BusinessYearReportApprovedScaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_经营年报_02许可证核准年经营规模Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/businessYearReportApprovedScale")
@Api(tags = "危险废物_经营年报_02许可证核准年经营规模")
public class BusinessYearReportApprovedScaleController extends BaseController {


    private final BusinessYearReportApprovedScaleService businessYearReportApprovedScaleService;

    /**
     * 新增危险废物_经营年报_02许可证核准年经营规模
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_经营年报_02许可证核准年经营规模")
    @SysLog(title = "新增危险废物_经营年报_02许可证核准年经营规模", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody BusinessYearReportApprovedScale businessYearReportApprovedScale) {
        return RestResult.succeed(businessYearReportApprovedScaleService.saveBusinessYearReportApprovedScale(businessYearReportApprovedScale));
    }

    /**
     * 修改危险废物_经营年报_02许可证核准年经营规模
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_经营年报_02许可证核准年经营规模")
    @SysLog(title = "修改危险废物_经营年报_02许可证核准年经营规模", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody BusinessYearReportApprovedScale businessYearReportApprovedScale) {
        return RestResult.succeed(businessYearReportApprovedScaleService.updateBusinessYearReportApprovedScale(businessYearReportApprovedScale));
    }

    /**
     * 删除危险废物_经营年报_02许可证核准年经营规模
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_经营年报_02许可证核准年经营规模")
    @SysLog(title = "删除危险废物_经营年报_02许可证核准年经营规模", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(businessYearReportApprovedScaleService.deleteBusinessYearReportApprovedScale(id));
    }


    /**
     * 分页查询危险废物_经营年报_02许可证核准年经营规模
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_经营年报_02许可证核准年经营规模")
    public RestResult<PageT<BusinessYearReportApprovedScale>> getPage(PageT<BusinessYearReportApprovedScale> page, BusinessYearReportApprovedScale businessYearReportApprovedScale) {
        return RestResult.succeed(businessYearReportApprovedScaleService.findBusinessYearReportApprovedScalePage(page, businessYearReportApprovedScale));
    }

    /**
     * 查询危险废物_经营年报_02许可证核准年经营规模列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_经营年报_02许可证核准年经营规模列表")
    public RestResult<List<BusinessYearReportApprovedScale>> getList(BusinessYearReportApprovedScale businessYearReportApprovedScale) {
        return RestResult.succeed(businessYearReportApprovedScaleService.findBusinessYearReportApprovedScaleList(businessYearReportApprovedScale));
    }

    /**
     * 获取危险废物_经营年报_02许可证核准年经营规模详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_经营年报_02许可证核准年经营规模详细信息")
    public RestResult<BusinessYearReportApprovedScale> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(businessYearReportApprovedScaleService.findBusinessYearReportApprovedScaleById(id));
    }
}
