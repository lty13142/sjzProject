package com.zsgf.platform.controller.yearPlan;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteSelfUseDisposal;
import com.zsgf.platform.service.yearPlan.YearPlanWasteSelfUseDisposalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_管理计划_04危废自行利用处置信息Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yearPlanWasteSelfUseDisposal")
@Api(tags = "危险废物_管理计划_04危废自行利用处置信息")
public class YearPlanWasteSelfUseDisposalController extends BaseController {


    private final YearPlanWasteSelfUseDisposalService yearPlanWasteSelfUseDisposalService;

    /**
     * 新增危险废物_管理计划_04危废自行利用处置信息
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_管理计划_04危废自行利用处置信息")
    @SysLog(title = "新增危险废物_管理计划_04危废自行利用处置信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody YearPlanWasteSelfUseDisposal yearPlanWasteSelfUseDisposal) {
        return RestResult.succeed(yearPlanWasteSelfUseDisposalService.saveYearPlanWasteSelfUseDisposal(yearPlanWasteSelfUseDisposal));
    }

    /**
     * 修改危险废物_管理计划_04危废自行利用处置信息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_管理计划_04危废自行利用处置信息")
    @SysLog(title = "修改危险废物_管理计划_04危废自行利用处置信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody YearPlanWasteSelfUseDisposal yearPlanWasteSelfUseDisposal) {
        return RestResult.succeed(yearPlanWasteSelfUseDisposalService.updateYearPlanWasteSelfUseDisposal(yearPlanWasteSelfUseDisposal));
    }

    /**
     * 删除危险废物_管理计划_04危废自行利用处置信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_管理计划_04危废自行利用处置信息")
    @SysLog(title = "删除危险废物_管理计划_04危废自行利用处置信息", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanWasteSelfUseDisposalService.deleteYearPlanWasteSelfUseDisposal(id));
    }


    /**
     * 分页查询危险废物_管理计划_04危废自行利用处置信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_管理计划_04危废自行利用处置信息")
    public RestResult<PageT<YearPlanWasteSelfUseDisposal>> getPage(PageT<YearPlanWasteSelfUseDisposal> page, YearPlanWasteSelfUseDisposal yearPlanWasteSelfUseDisposal) {
        return RestResult.succeed(yearPlanWasteSelfUseDisposalService.findYearPlanWasteSelfUseDisposalPage(page, yearPlanWasteSelfUseDisposal));
    }

    /**
     * 查询危险废物_管理计划_04危废自行利用处置信息列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_管理计划_04危废自行利用处置信息列表")
    public RestResult<List<YearPlanWasteSelfUseDisposal>> getList(YearPlanWasteSelfUseDisposal yearPlanWasteSelfUseDisposal) {
        return RestResult.succeed(yearPlanWasteSelfUseDisposalService.findYearPlanWasteSelfUseDisposalList(yearPlanWasteSelfUseDisposal));
    }

    /**
     * 获取危险废物_管理计划_04危废自行利用处置信息详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_管理计划_04危废自行利用处置信息详细信息")
    public RestResult<YearPlanWasteSelfUseDisposal> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanWasteSelfUseDisposalService.findYearPlanWasteSelfUseDisposalById(id));
    }
}
