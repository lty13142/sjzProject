package com.zsgf.platform.controller.wasteReport;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.wasteReport.WasteMonthReportSelfUse;
import com.zsgf.platform.service.wasteReport.WasteMonthReportSelfUseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_产生月报_03废物自行利用信息Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteMonthReportSelfUse")
@Api(tags = "危险废物_产生月报_03废物自行利用信息")
public class WasteMonthReportSelfUseController extends BaseController {


    private final WasteMonthReportSelfUseService wasteMonthReportSelfUseService;

    /**
     * 新增危险废物_产生月报_03废物自行利用信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_产生月报_03废物自行利用信息")
    @SysLog(title = "新增危险废物_产生月报_03废物自行利用信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteMonthReportSelfUse wasteMonthReportSelfUse) {
        return RestResult.succeed(wasteMonthReportSelfUseService.saveWasteMonthReportSelfUse(wasteMonthReportSelfUse));
    }

    /**
     * 修改危险废物_产生月报_03废物自行利用信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_产生月报_03废物自行利用信息")
    @SysLog(title = "修改危险废物_产生月报_03废物自行利用信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteMonthReportSelfUse wasteMonthReportSelfUse) {
        return RestResult.succeed(wasteMonthReportSelfUseService.updateWasteMonthReportSelfUse(wasteMonthReportSelfUse));
    }

    /**
     * 删除危险废物_产生月报_03废物自行利用信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_产生月报_03废物自行利用信息")
    @SysLog(title = "删除危险废物_产生月报_03废物自行利用信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteMonthReportSelfUseService.deleteWasteMonthReportSelfUse(id));
    }


    /**
     * 分页查询危险废物_产生月报_03废物自行利用信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_产生月报_03废物自行利用信息")
    public RestResult<PageT<WasteMonthReportSelfUse>> getPage(PageT<WasteMonthReportSelfUse> page, WasteMonthReportSelfUse wasteMonthReportSelfUse) {
        return RestResult.succeed(wasteMonthReportSelfUseService.findWasteMonthReportSelfUsePage(page, wasteMonthReportSelfUse));
    }

    /**
     * 查询危险废物_产生月报_03废物自行利用信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_产生月报_03废物自行利用信息列表")
    public RestResult<List<WasteMonthReportSelfUse>> getList(WasteMonthReportSelfUse wasteMonthReportSelfUse) {
        return RestResult.succeed(wasteMonthReportSelfUseService.findWasteMonthReportSelfUseList(wasteMonthReportSelfUse));
    }

    /**
     * 获取危险废物_产生月报_03废物自行利用信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_产生月报_03废物自行利用信息详细信息")
    public RestResult<WasteMonthReportSelfUse> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteMonthReportSelfUseService.findWasteMonthReportSelfUseById(id));
    }
}
