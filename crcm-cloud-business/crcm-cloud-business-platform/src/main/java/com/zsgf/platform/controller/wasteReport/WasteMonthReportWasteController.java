package com.zsgf.platform.controller.wasteReport;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.wasteReport.WasteMonthReportWaste;
import com.zsgf.platform.service.wasteReport.WasteMonthReportWasteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_产生月报_02产废信息Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteMonthReportWaste")
@Api(tags = "危险废物_产生月报_02产废信息")
public class WasteMonthReportWasteController extends BaseController {


    private final WasteMonthReportWasteService wasteMonthReportWasteService;

    /**
     * 新增危险废物_产生月报_02产废信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_产生月报_02产废信息")
    @SysLog(title = "新增危险废物_产生月报_02产废信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteMonthReportWaste wasteMonthReportWaste) {
        return RestResult.succeed(wasteMonthReportWasteService.saveWasteMonthReportWaste(wasteMonthReportWaste));
    }

    /**
     * 修改危险废物_产生月报_02产废信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_产生月报_02产废信息")
    @SysLog(title = "修改危险废物_产生月报_02产废信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteMonthReportWaste wasteMonthReportWaste) {
        return RestResult.succeed(wasteMonthReportWasteService.updateWasteMonthReportWaste(wasteMonthReportWaste));
    }

    /**
     * 删除危险废物_产生月报_02产废信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_产生月报_02产废信息")
    @SysLog(title = "删除危险废物_产生月报_02产废信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteMonthReportWasteService.deleteWasteMonthReportWaste(id));
    }


    /**
     * 分页查询危险废物_产生月报_02产废信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_产生月报_02产废信息")
    public RestResult<PageT<WasteMonthReportWaste>> getPage(PageT<WasteMonthReportWaste> page, WasteMonthReportWaste wasteMonthReportWaste) {
        return RestResult.succeed(wasteMonthReportWasteService.findWasteMonthReportWastePage(page, wasteMonthReportWaste));
    }

    /**
     * 查询危险废物_产生月报_02产废信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_产生月报_02产废信息列表")
    public RestResult<List<WasteMonthReportWaste>> getList(WasteMonthReportWaste wasteMonthReportWaste) {
        return RestResult.succeed(wasteMonthReportWasteService.findWasteMonthReportWasteList(wasteMonthReportWaste));
    }

    /**
     * 获取危险废物_产生月报_02产废信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_产生月报_02产废信息详细信息")
    public RestResult<WasteMonthReportWaste> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteMonthReportWasteService.findWasteMonthReportWasteById(id));
    }
}
