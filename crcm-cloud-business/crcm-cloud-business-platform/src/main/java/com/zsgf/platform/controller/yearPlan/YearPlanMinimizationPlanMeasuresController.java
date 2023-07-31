package com.zsgf.platform.controller.yearPlan;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.yearPlan.YearPlanMinimizationPlanMeasures;
import com.zsgf.platform.service.yearPlan.YearPlanMinimizationPlanMeasuresService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_管理计划_19减量化计划和措施Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yearPlanMinimizationPlanMeasures")
@Api(tags = "危险废物_管理计划_19减量化计划和措施")
public class YearPlanMinimizationPlanMeasuresController extends BaseController {


    private final YearPlanMinimizationPlanMeasuresService yearPlanMinimizationPlanMeasuresService;

    /**
     * 新增危险废物_管理计划_19减量化计划和措施
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_管理计划_19减量化计划和措施")
    @SysLog(title = "新增危险废物_管理计划_19减量化计划和措施", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody YearPlanMinimizationPlanMeasures yearPlanMinimizationPlanMeasures) {
        return RestResult.succeed(yearPlanMinimizationPlanMeasuresService.saveYearPlanMinimizationPlanMeasures(yearPlanMinimizationPlanMeasures));
    }

    /**
     * 修改危险废物_管理计划_19减量化计划和措施
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_管理计划_19减量化计划和措施")
    @SysLog(title = "修改危险废物_管理计划_19减量化计划和措施", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody YearPlanMinimizationPlanMeasures yearPlanMinimizationPlanMeasures) {
        return RestResult.succeed(yearPlanMinimizationPlanMeasuresService.updateYearPlanMinimizationPlanMeasures(yearPlanMinimizationPlanMeasures));
    }

    /**
     * 删除危险废物_管理计划_19减量化计划和措施
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_管理计划_19减量化计划和措施")
    @SysLog(title = "删除危险废物_管理计划_19减量化计划和措施", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanMinimizationPlanMeasuresService.deleteYearPlanMinimizationPlanMeasures(id));
    }


    /**
     * 分页查询危险废物_管理计划_19减量化计划和措施
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_管理计划_19减量化计划和措施")
    public RestResult<PageT<YearPlanMinimizationPlanMeasures>> getPage(PageT<YearPlanMinimizationPlanMeasures> page, YearPlanMinimizationPlanMeasures yearPlanMinimizationPlanMeasures) {
        return RestResult.succeed(yearPlanMinimizationPlanMeasuresService.findYearPlanMinimizationPlanMeasuresPage(page, yearPlanMinimizationPlanMeasures));
    }

    /**
     * 查询危险废物_管理计划_19减量化计划和措施列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_管理计划_19减量化计划和措施列表")
    public RestResult<List<YearPlanMinimizationPlanMeasures>> getList(YearPlanMinimizationPlanMeasures yearPlanMinimizationPlanMeasures) {
        return RestResult.succeed(yearPlanMinimizationPlanMeasuresService.findYearPlanMinimizationPlanMeasuresList(yearPlanMinimizationPlanMeasures));
    }

    /**
     * 获取危险废物_管理计划_19减量化计划和措施详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_管理计划_19减量化计划和措施详细信息")
    public RestResult<YearPlanMinimizationPlanMeasures> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanMinimizationPlanMeasuresService.findYearPlanMinimizationPlanMeasuresById(id));
    }
}
