package com.zsgf.platform.controller.businessReport;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.businessReport.BusinessMonthReportWaste;
import com.zsgf.platform.service.businessReport.BusinessMonthReportWasteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_经营月报_02经营废物信息Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/businessMonthReportWaste")
@Api(tags = "危险废物_经营月报_02经营废物信息")
public class BusinessMonthReportWasteController extends BaseController {


    private final BusinessMonthReportWasteService businessMonthReportWasteService;

    /**
     * 新增危险废物_经营月报_02经营废物信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_经营月报_02经营废物信息")
    @SysLog(title = "新增危险废物_经营月报_02经营废物信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody BusinessMonthReportWaste businessMonthReportWaste) {
        return RestResult.succeed(businessMonthReportWasteService.saveBusinessMonthReportWaste(businessMonthReportWaste));
    }

    /**
     * 修改危险废物_经营月报_02经营废物信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_经营月报_02经营废物信息")
    @SysLog(title = "修改危险废物_经营月报_02经营废物信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody BusinessMonthReportWaste businessMonthReportWaste) {
        return RestResult.succeed(businessMonthReportWasteService.updateBusinessMonthReportWaste(businessMonthReportWaste));
    }

    /**
     * 删除危险废物_经营月报_02经营废物信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_经营月报_02经营废物信息")
    @SysLog(title = "删除危险废物_经营月报_02经营废物信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(businessMonthReportWasteService.deleteBusinessMonthReportWaste(id));
    }


    /**
     * 分页查询危险废物_经营月报_02经营废物信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_经营月报_02经营废物信息")
    public RestResult<PageT<BusinessMonthReportWaste>> getPage(PageT<BusinessMonthReportWaste> page, BusinessMonthReportWaste businessMonthReportWaste) {
        return RestResult.succeed(businessMonthReportWasteService.findBusinessMonthReportWastePage(page, businessMonthReportWaste));
    }

    /**
     * 查询危险废物_经营月报_02经营废物信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_经营月报_02经营废物信息列表")
    public RestResult<List<BusinessMonthReportWaste>> getList(BusinessMonthReportWaste businessMonthReportWaste) {
        return RestResult.succeed(businessMonthReportWasteService.findBusinessMonthReportWasteList(businessMonthReportWaste));
    }

    /**
     * 获取危险废物_经营月报_02经营废物信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_经营月报_02经营废物信息详细信息")
    public RestResult<BusinessMonthReportWaste> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(businessMonthReportWasteService.findBusinessMonthReportWasteById(id));
    }
}
