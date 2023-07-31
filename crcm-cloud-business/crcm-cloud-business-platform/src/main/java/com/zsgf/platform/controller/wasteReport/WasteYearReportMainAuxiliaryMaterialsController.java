package com.zsgf.platform.controller.wasteReport;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReportMainAuxiliaryMaterials;
import com.zsgf.platform.service.wasteReport.WasteYearReportMainAuxiliaryMaterialsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_产生年报_06主要原辅材料及能源Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteYearReportMainAuxiliaryMaterials")
@Api(tags = "危险废物_产生年报_06主要原辅材料及能源")
public class WasteYearReportMainAuxiliaryMaterialsController extends BaseController {


    private final WasteYearReportMainAuxiliaryMaterialsService wasteYearReportMainAuxiliaryMaterialsService;

    /**
     * 新增危险废物_产生年报_06主要原辅材料及能源
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_产生年报_06主要原辅材料及能源")
    @SysLog(title = "新增危险废物_产生年报_06主要原辅材料及能源", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteYearReportMainAuxiliaryMaterials wasteYearReportMainAuxiliaryMaterials) {
        return RestResult.succeed(wasteYearReportMainAuxiliaryMaterialsService.saveWasteYearReportMainAuxiliaryMaterials(wasteYearReportMainAuxiliaryMaterials));
    }

    /**
     * 修改危险废物_产生年报_06主要原辅材料及能源
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_产生年报_06主要原辅材料及能源")
    @SysLog(title = "修改危险废物_产生年报_06主要原辅材料及能源", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteYearReportMainAuxiliaryMaterials wasteYearReportMainAuxiliaryMaterials) {
        return RestResult.succeed(wasteYearReportMainAuxiliaryMaterialsService.updateWasteYearReportMainAuxiliaryMaterials(wasteYearReportMainAuxiliaryMaterials));
    }

    /**
     * 删除危险废物_产生年报_06主要原辅材料及能源
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_产生年报_06主要原辅材料及能源")
    @SysLog(title = "删除危险废物_产生年报_06主要原辅材料及能源", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteYearReportMainAuxiliaryMaterialsService.deleteWasteYearReportMainAuxiliaryMaterials(id));
    }


    /**
     * 分页查询危险废物_产生年报_06主要原辅材料及能源
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_产生年报_06主要原辅材料及能源")
    public RestResult<PageT<WasteYearReportMainAuxiliaryMaterials>> getPage(PageT<WasteYearReportMainAuxiliaryMaterials> page, WasteYearReportMainAuxiliaryMaterials wasteYearReportMainAuxiliaryMaterials) {
        return RestResult.succeed(wasteYearReportMainAuxiliaryMaterialsService.findWasteYearReportMainAuxiliaryMaterialsPage(page, wasteYearReportMainAuxiliaryMaterials));
    }

    /**
     * 查询危险废物_产生年报_06主要原辅材料及能源列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_产生年报_06主要原辅材料及能源列表")
    public RestResult<List<WasteYearReportMainAuxiliaryMaterials>> getList(WasteYearReportMainAuxiliaryMaterials wasteYearReportMainAuxiliaryMaterials) {
        return RestResult.succeed(wasteYearReportMainAuxiliaryMaterialsService.findWasteYearReportMainAuxiliaryMaterialsList(wasteYearReportMainAuxiliaryMaterials));
    }

    /**
     * 获取危险废物_产生年报_06主要原辅材料及能源详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_产生年报_06主要原辅材料及能源详细信息")
    public RestResult<WasteYearReportMainAuxiliaryMaterials> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteYearReportMainAuxiliaryMaterialsService.findWasteYearReportMainAuxiliaryMaterialsById(id));
    }
}
