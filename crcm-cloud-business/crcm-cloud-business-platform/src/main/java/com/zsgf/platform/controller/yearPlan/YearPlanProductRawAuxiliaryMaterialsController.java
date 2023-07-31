package com.zsgf.platform.controller.yearPlan;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.yearPlan.YearPlanProductRawAuxiliaryMaterials;
import com.zsgf.platform.service.yearPlan.YearPlanProductRawAuxiliaryMaterialsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_管理计划_13产品生产主要原辅材料Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yearPlanProductRawAuxiliaryMaterials")
@Api(tags = "危险废物_管理计划_13产品生产主要原辅材料")
public class YearPlanProductRawAuxiliaryMaterialsController extends BaseController {


    private final YearPlanProductRawAuxiliaryMaterialsService yearPlanProductRawAuxiliaryMaterialsService;

    /**
     * 新增危险废物_管理计划_13产品生产主要原辅材料
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_管理计划_13产品生产主要原辅材料")
    @SysLog(title = "新增危险废物_管理计划_13产品生产主要原辅材料", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody YearPlanProductRawAuxiliaryMaterials yearPlanProductRawAuxiliaryMaterials) {
        return RestResult.succeed(yearPlanProductRawAuxiliaryMaterialsService.saveYearPlanProductRawAuxiliaryMaterials(yearPlanProductRawAuxiliaryMaterials));
    }

    /**
     * 修改危险废物_管理计划_13产品生产主要原辅材料
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_管理计划_13产品生产主要原辅材料")
    @SysLog(title = "修改危险废物_管理计划_13产品生产主要原辅材料", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody YearPlanProductRawAuxiliaryMaterials yearPlanProductRawAuxiliaryMaterials) {
        return RestResult.succeed(yearPlanProductRawAuxiliaryMaterialsService.updateYearPlanProductRawAuxiliaryMaterials(yearPlanProductRawAuxiliaryMaterials));
    }

    /**
     * 删除危险废物_管理计划_13产品生产主要原辅材料
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_管理计划_13产品生产主要原辅材料")
    @SysLog(title = "删除危险废物_管理计划_13产品生产主要原辅材料", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanProductRawAuxiliaryMaterialsService.deleteYearPlanProductRawAuxiliaryMaterials(id));
    }


    /**
     * 分页查询危险废物_管理计划_13产品生产主要原辅材料
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_管理计划_13产品生产主要原辅材料")
    public RestResult<PageT<YearPlanProductRawAuxiliaryMaterials>> getPage(PageT<YearPlanProductRawAuxiliaryMaterials> page, YearPlanProductRawAuxiliaryMaterials yearPlanProductRawAuxiliaryMaterials) {
        return RestResult.succeed(yearPlanProductRawAuxiliaryMaterialsService.findYearPlanProductRawAuxiliaryMaterialsPage(page, yearPlanProductRawAuxiliaryMaterials));
    }

    /**
     * 查询危险废物_管理计划_13产品生产主要原辅材料列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_管理计划_13产品生产主要原辅材料列表")
    public RestResult<List<YearPlanProductRawAuxiliaryMaterials>> getList(YearPlanProductRawAuxiliaryMaterials yearPlanProductRawAuxiliaryMaterials) {
        return RestResult.succeed(yearPlanProductRawAuxiliaryMaterialsService.findYearPlanProductRawAuxiliaryMaterialsList(yearPlanProductRawAuxiliaryMaterials));
    }

    /**
     * 获取危险废物_管理计划_13产品生产主要原辅材料详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_管理计划_13产品生产主要原辅材料详细信息")
    public RestResult<YearPlanProductRawAuxiliaryMaterials> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanProductRawAuxiliaryMaterialsService.findYearPlanProductRawAuxiliaryMaterialsById(id));
    }
}
