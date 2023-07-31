package com.zsgf.platform.controller.yearPlan;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteStorageMeasures;
import com.zsgf.platform.service.yearPlan.YearPlanWasteStorageMeasuresService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_管理计划_07危险废物贮存措施Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yearPlanWasteStorageMeasures")
@Api(tags = "危险废物_管理计划_07危险废物贮存措施")
public class YearPlanWasteStorageMeasuresController extends BaseController {


    private final YearPlanWasteStorageMeasuresService yearPlanWasteStorageMeasuresService;

    /**
     * 新增危险废物_管理计划_07危险废物贮存措施
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_管理计划_07危险废物贮存措施")
    @SysLog(title = "新增危险废物_管理计划_07危险废物贮存措施", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody YearPlanWasteStorageMeasures yearPlanWasteStorageMeasures) {
        return RestResult.succeed(yearPlanWasteStorageMeasuresService.saveYearPlanWasteStorageMeasures(yearPlanWasteStorageMeasures));
    }

    /**
     * 修改危险废物_管理计划_07危险废物贮存措施
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_管理计划_07危险废物贮存措施")
    @SysLog(title = "修改危险废物_管理计划_07危险废物贮存措施", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody YearPlanWasteStorageMeasures yearPlanWasteStorageMeasures) {
        return RestResult.succeed(yearPlanWasteStorageMeasuresService.updateYearPlanWasteStorageMeasures(yearPlanWasteStorageMeasures));
    }

    /**
     * 删除危险废物_管理计划_07危险废物贮存措施
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_管理计划_07危险废物贮存措施")
    @SysLog(title = "删除危险废物_管理计划_07危险废物贮存措施", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanWasteStorageMeasuresService.deleteYearPlanWasteStorageMeasures(id));
    }


    /**
     * 分页查询危险废物_管理计划_07危险废物贮存措施
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_管理计划_07危险废物贮存措施")
    public RestResult<PageT<YearPlanWasteStorageMeasures>> getPage(PageT<YearPlanWasteStorageMeasures> page, YearPlanWasteStorageMeasures yearPlanWasteStorageMeasures) {
        return RestResult.succeed(yearPlanWasteStorageMeasuresService.findYearPlanWasteStorageMeasuresPage(page, yearPlanWasteStorageMeasures));
    }

    /**
     * 查询危险废物_管理计划_07危险废物贮存措施列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_管理计划_07危险废物贮存措施列表")
    public RestResult<List<YearPlanWasteStorageMeasures>> getList(YearPlanWasteStorageMeasures yearPlanWasteStorageMeasures) {
        return RestResult.succeed(yearPlanWasteStorageMeasuresService.findYearPlanWasteStorageMeasuresList(yearPlanWasteStorageMeasures));
    }

    /**
     * 获取危险废物_管理计划_07危险废物贮存措施详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_管理计划_07危险废物贮存措施详细信息")
    public RestResult<YearPlanWasteStorageMeasures> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanWasteStorageMeasuresService.findYearPlanWasteStorageMeasuresById(id));
    }
}
