package com.zsgf.platform.controller.businessReport;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.businessReport.BusinessDayReportWasteSecondaryTransfer;
import com.zsgf.platform.service.businessReport.BusinessDayReportWasteSecondaryTransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_经营日报_03接收废物二次转移信息Controller
 *
 * @author gzl;
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/BusinessDayReportWasteSecondaryTransfer")
@Api(tags = "危险废物_经营日报_03接收废物二次转移信息")
public class BusinessDayReportWasteSecondaryTransferController extends BaseController {


    private final BusinessDayReportWasteSecondaryTransferService businessDayReportWasteSecondaryTransferService;

    /**
     * 新增危险废物_经营日报_03接收废物二次转移信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_经营日报_03接收废物二次转移信息")
    @SysLog(title = "新增危险废物_经营日报_03接收废物二次转移信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody BusinessDayReportWasteSecondaryTransfer businessDayReportWasteSecondaryTransfer) {
        return RestResult.succeed(businessDayReportWasteSecondaryTransferService.saveBusinessDayReportWasteSecondaryTransfer(businessDayReportWasteSecondaryTransfer));
    }

    /**
     * 修改危险废物_经营日报_03接收废物二次转移信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_经营日报_03接收废物二次转移信息")
    @SysLog(title = "修改危险废物_经营日报_03接收废物二次转移信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody BusinessDayReportWasteSecondaryTransfer businessDayReportWasteSecondaryTransfer) {
        return RestResult.succeed(businessDayReportWasteSecondaryTransferService.updateBusinessDayReportWasteSecondaryTransfer(businessDayReportWasteSecondaryTransfer));
    }

    /**
     * 删除危险废物_经营日报_03接收废物二次转移信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_经营日报_03接收废物二次转移信息")
    @SysLog(title = "删除危险废物_经营日报_03接收废物二次转移信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(businessDayReportWasteSecondaryTransferService.deleteBusinessDayReportWasteSecondaryTransfer(id));
    }


    /**
     * 分页查询危险废物_经营日报_03接收废物二次转移信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_经营日报_03接收废物二次转移信息")
    public RestResult<PageT<BusinessDayReportWasteSecondaryTransfer>> getPage(PageT<BusinessDayReportWasteSecondaryTransfer> page, BusinessDayReportWasteSecondaryTransfer businessDayReportWasteSecondaryTransfer) {
        return RestResult.succeed(businessDayReportWasteSecondaryTransferService.findBusinessDayReportWasteSecondaryTransferPage(page, businessDayReportWasteSecondaryTransfer));
    }

    /**
     * 查询危险废物_经营日报_03接收废物二次转移信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_经营日报_03接收废物二次转移信息列表")
    public RestResult<List<BusinessDayReportWasteSecondaryTransfer>> getList(BusinessDayReportWasteSecondaryTransfer businessDayReportWasteSecondaryTransfer) {
        return RestResult.succeed(businessDayReportWasteSecondaryTransferService.findBusinessDayReportWasteSecondaryTransferList(businessDayReportWasteSecondaryTransfer));
    }

    /**
     * 获取危险废物_经营日报_03接收废物二次转移信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_经营日报_03接收废物二次转移信息详细信息")
    public RestResult<BusinessDayReportWasteSecondaryTransfer> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(businessDayReportWasteSecondaryTransferService.findBusinessDayReportWasteSecondaryTransferById(id));
    }
}
