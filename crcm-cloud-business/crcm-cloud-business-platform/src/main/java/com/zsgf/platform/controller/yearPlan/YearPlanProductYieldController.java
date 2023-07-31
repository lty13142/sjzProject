package com.zsgf.platform.controller.yearPlan;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.yearPlan.YearPlanProductYield;
import com.zsgf.platform.service.yearPlan.YearPlanProductYieldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_管理计划_15产品生产产量Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yearPlanProductYield")
@Api(tags = "危险废物_管理计划_15产品生产产量")
public class YearPlanProductYieldController extends BaseController {


    private final YearPlanProductYieldService yearPlanProductYieldService;

    /**
     * 新增危险废物_管理计划_15产品生产产量
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_管理计划_15产品生产产量")
    @SysLog(title = "新增危险废物_管理计划_15产品生产产量", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody YearPlanProductYield yearPlanProductYield) {
        return RestResult.succeed(yearPlanProductYieldService.saveYearPlanProductYield(yearPlanProductYield));
    }

    /**
     * 修改危险废物_管理计划_15产品生产产量
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_管理计划_15产品生产产量")
    @SysLog(title = "修改危险废物_管理计划_15产品生产产量", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody YearPlanProductYield yearPlanProductYield) {
        return RestResult.succeed(yearPlanProductYieldService.updateYearPlanProductYield(yearPlanProductYield));
    }

    /**
     * 删除危险废物_管理计划_15产品生产产量
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_管理计划_15产品生产产量")
    @SysLog(title = "删除危险废物_管理计划_15产品生产产量", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanProductYieldService.deleteYearPlanProductYield(id));
    }


    /**
     * 分页查询危险废物_管理计划_15产品生产产量
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_管理计划_15产品生产产量")
    public RestResult<PageT<YearPlanProductYield>> getPage(PageT<YearPlanProductYield> page, YearPlanProductYield yearPlanProductYield) {
        return RestResult.succeed(yearPlanProductYieldService.findYearPlanProductYieldPage(page, yearPlanProductYield));
    }

    /**
     * 查询危险废物_管理计划_15产品生产产量列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_管理计划_15产品生产产量列表")
    public RestResult<List<YearPlanProductYield>> getList(YearPlanProductYield yearPlanProductYield) {
        return RestResult.succeed(yearPlanProductYieldService.findYearPlanProductYieldList(yearPlanProductYield));
    }

    /**
     * 获取危险废物_管理计划_15产品生产产量详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_管理计划_15产品生产产量详细信息")
    public RestResult<YearPlanProductYield> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanProductYieldService.findYearPlanProductYieldById(id));
    }
}
