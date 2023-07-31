package com.zsgf.platform.controller.standardize;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentFeedback;
import com.zsgf.platform.service.standardize.WasteStandardizedAssessmentFeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 规范化考核整改反馈Controller
 *
 * @author gzl
 * @date 2023-03-24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteStandardizedAssessmentFeedback")
@Api(tags = "规范化考核整改反馈")
public class WasteStandardizedAssessmentFeedbackController extends BaseController {


    private final WasteStandardizedAssessmentFeedbackService wasteStandardizedAssessmentFeedbackService;

    /**
     * 新增规范化考核整改反馈
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增规范化考核整改反馈")
    @SysLog(title = "新增规范化考核整改反馈", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteStandardizedAssessmentFeedback wasteStandardizedAssessmentFeedback) {
        return RestResult.succeed(wasteStandardizedAssessmentFeedbackService.saveWasteStandardizedAssessmentFeedback(wasteStandardizedAssessmentFeedback));
    }

    /**
     * 修改规范化考核整改反馈
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改规范化考核整改反馈")
    @SysLog(title = "修改规范化考核整改反馈", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteStandardizedAssessmentFeedback wasteStandardizedAssessmentFeedback) {
        return RestResult.succeed(wasteStandardizedAssessmentFeedbackService.updateWasteStandardizedAssessmentFeedback(wasteStandardizedAssessmentFeedback));
    }

    /**
     * 删除规范化考核整改反馈
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除规范化考核整改反馈")
    @SysLog(title = "删除规范化考核整改反馈", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteStandardizedAssessmentFeedbackService.deleteWasteStandardizedAssessmentFeedback(id));
    }


    /**
     * 分页查询规范化考核整改反馈
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询规范化考核整改反馈")
    public RestResult<PageT<WasteStandardizedAssessmentFeedback>> getPage(PageT<WasteStandardizedAssessmentFeedback> page, WasteStandardizedAssessmentFeedback wasteStandardizedAssessmentFeedback) {
        return RestResult.succeed(wasteStandardizedAssessmentFeedbackService.findWasteStandardizedAssessmentFeedbackPage(page, wasteStandardizedAssessmentFeedback));
    }

    /**
     * 查询规范化考核整改反馈列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询规范化考核整改反馈列表")
    public RestResult<List<WasteStandardizedAssessmentFeedback>> getList(WasteStandardizedAssessmentFeedback wasteStandardizedAssessmentFeedback) {
        return RestResult.succeed(wasteStandardizedAssessmentFeedbackService.findWasteStandardizedAssessmentFeedbackList(wasteStandardizedAssessmentFeedback));
    }

    /**
     * 获取规范化考核整改反馈详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取规范化考核整改反馈详细信息")
    public RestResult<WasteStandardizedAssessmentFeedback> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteStandardizedAssessmentFeedbackService.findWasteStandardizedAssessmentFeedbackById(id));
    }
}
