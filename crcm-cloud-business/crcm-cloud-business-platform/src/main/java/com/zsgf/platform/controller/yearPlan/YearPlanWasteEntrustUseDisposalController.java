package com.zsgf.platform.controller.yearPlan;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteEntrustUseDisposal;
import com.zsgf.platform.service.yearPlan.YearPlanWasteEntrustUseDisposalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_管理计划_05危险废物委托利用处置Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yearPlanWasteEntrustUseDisposal")
@Api(tags = "危险废物_管理计划_05危险废物委托利用处置")
public class YearPlanWasteEntrustUseDisposalController extends BaseController {


    private final YearPlanWasteEntrustUseDisposalService yearPlanWasteEntrustUseDisposalService;

    /**
     * 新增危险废物_管理计划_05危险废物委托利用处置
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_管理计划_05危险废物委托利用处置")
    @SysLog(title = "新增危险废物_管理计划_05危险废物委托利用处置", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody YearPlanWasteEntrustUseDisposal yearPlanWasteEntrustUseDisposal) {
        return RestResult.succeed(yearPlanWasteEntrustUseDisposalService.saveYearPlanWasteEntrustUseDisposal(yearPlanWasteEntrustUseDisposal));
    }

    /**
     * 修改危险废物_管理计划_05危险废物委托利用处置
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_管理计划_05危险废物委托利用处置")
    @SysLog(title = "修改危险废物_管理计划_05危险废物委托利用处置", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody YearPlanWasteEntrustUseDisposal yearPlanWasteEntrustUseDisposal) {
        return RestResult.succeed(yearPlanWasteEntrustUseDisposalService.updateYearPlanWasteEntrustUseDisposal(yearPlanWasteEntrustUseDisposal));
    }

    /**
     * 删除危险废物_管理计划_05危险废物委托利用处置
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_管理计划_05危险废物委托利用处置")
    @SysLog(title = "删除危险废物_管理计划_05危险废物委托利用处置", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanWasteEntrustUseDisposalService.deleteYearPlanWasteEntrustUseDisposal(id));
    }


    /**
     * 分页查询危险废物_管理计划_05危险废物委托利用处置
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_管理计划_05危险废物委托利用处置")
    public RestResult<PageT<YearPlanWasteEntrustUseDisposal>> getPage(PageT<YearPlanWasteEntrustUseDisposal> page, YearPlanWasteEntrustUseDisposal yearPlanWasteEntrustUseDisposal) {
        return RestResult.succeed(yearPlanWasteEntrustUseDisposalService.findYearPlanWasteEntrustUseDisposalPage(page, yearPlanWasteEntrustUseDisposal));
    }

    /**
     * 查询危险废物_管理计划_05危险废物委托利用处置列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_管理计划_05危险废物委托利用处置列表")
    public RestResult<List<YearPlanWasteEntrustUseDisposal>> getList(YearPlanWasteEntrustUseDisposal yearPlanWasteEntrustUseDisposal) {
        return RestResult.succeed(yearPlanWasteEntrustUseDisposalService.findYearPlanWasteEntrustUseDisposalList(yearPlanWasteEntrustUseDisposal));
    }

    /**
     * 获取危险废物_管理计划_05危险废物委托利用处置详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_管理计划_05危险废物委托利用处置详细信息")
    public RestResult<YearPlanWasteEntrustUseDisposal> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanWasteEntrustUseDisposalService.findYearPlanWasteEntrustUseDisposalById(id));
    }
}
