package com.zsgf.platform.controller.yearPlan;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.yearPlan.YearPlanLastYearReview;
import com.zsgf.platform.service.yearPlan.YearPlanLastYearReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 危险废物_管理计划_20上年度管理计划回顾Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yearPlanLastYearReview")
@Api(tags = "危险废物_管理计划_20上年度管理计划回顾")
public class YearPlanLastYearReviewController extends BaseController {


    private final YearPlanLastYearReviewService yearPlanLastYearReviewService;

    /**
     * 新增危险废物_管理计划_20上年度管理计划回顾
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增危险废物_管理计划_20上年度管理计划回顾")
    @SysLog(title = "新增危险废物_管理计划_20上年度管理计划回顾", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody YearPlanLastYearReview yearPlanLastYearReview) {
        return RestResult.succeed(yearPlanLastYearReviewService.saveYearPlanLastYearReview(yearPlanLastYearReview));
    }

    /**
     * 修改危险废物_管理计划_20上年度管理计划回顾
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改危险废物_管理计划_20上年度管理计划回顾")
    @SysLog(title = "修改危险废物_管理计划_20上年度管理计划回顾", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody YearPlanLastYearReview yearPlanLastYearReview) {
        return RestResult.succeed(yearPlanLastYearReviewService.updateYearPlanLastYearReview(yearPlanLastYearReview));
    }

    /**
     * 删除危险废物_管理计划_20上年度管理计划回顾
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除危险废物_管理计划_20上年度管理计划回顾")
    @SysLog(title = "删除危险废物_管理计划_20上年度管理计划回顾", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanLastYearReviewService.deleteYearPlanLastYearReview(id));
    }


    /**
     * 分页查询危险废物_管理计划_20上年度管理计划回顾
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询危险废物_管理计划_20上年度管理计划回顾")
    public RestResult<PageT<YearPlanLastYearReview>> getPage(PageT<YearPlanLastYearReview> page, YearPlanLastYearReview yearPlanLastYearReview) {
        return RestResult.succeed(yearPlanLastYearReviewService.findYearPlanLastYearReviewPage(page, yearPlanLastYearReview));
    }

    /**
     * 查询危险废物_管理计划_20上年度管理计划回顾列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询危险废物_管理计划_20上年度管理计划回顾列表")
    public RestResult<List<YearPlanLastYearReview>> getList(YearPlanLastYearReview yearPlanLastYearReview) {
        return RestResult.succeed(yearPlanLastYearReviewService.findYearPlanLastYearReviewList(yearPlanLastYearReview));
    }

    /**
     * 获取危险废物_管理计划_20上年度管理计划回顾详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取危险废物_管理计划_20上年度管理计划回顾详细信息")
    public RestResult<YearPlanLastYearReview> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(yearPlanLastYearReviewService.findYearPlanLastYearReviewById(id));
    }
}
