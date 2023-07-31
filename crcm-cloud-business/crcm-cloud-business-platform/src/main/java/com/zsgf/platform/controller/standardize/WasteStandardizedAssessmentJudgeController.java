package com.zsgf.platform.controller.standardize;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentJudge;
import com.zsgf.platform.service.standardize.WasteStandardizedAssessmentJudgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 规范化考核结果Controller
 *
 * @author gzl
 * @date 2023-03-24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wasteStandardizedAssessmentJudge")
@Api(tags = "规范化考核结果")
public class WasteStandardizedAssessmentJudgeController extends BaseController {


    private final WasteStandardizedAssessmentJudgeService wasteStandardizedAssessmentJudgeService;

    /**
     * 新增规范化考核结果
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增规范化考核结果")
    @SysLog(title = "新增规范化考核结果", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody WasteStandardizedAssessmentJudge wasteStandardizedAssessmentJudge) {
        return RestResult.succeed(wasteStandardizedAssessmentJudgeService.saveWasteStandardizedAssessmentJudge(wasteStandardizedAssessmentJudge));
    }

    /**
     * 修改规范化考核结果
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改规范化考核结果")
    @SysLog(title = "修改规范化考核结果", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody WasteStandardizedAssessmentJudge wasteStandardizedAssessmentJudge) {
        return RestResult.succeed(wasteStandardizedAssessmentJudgeService.updateWasteStandardizedAssessmentJudge(wasteStandardizedAssessmentJudge));
    }

    /**
     * 删除规范化考核结果
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除规范化考核结果")
    @SysLog(title = "删除规范化考核结果", serviceId = ServiceNameConstants.PLATFORM_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(wasteStandardizedAssessmentJudgeService.deleteWasteStandardizedAssessmentJudge(id));
    }


    /**
     * 分页查询规范化考核结果
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询规范化考核结果")
    public RestResult<PageT<WasteStandardizedAssessmentJudge>> getPage(PageT<WasteStandardizedAssessmentJudge> page, WasteStandardizedAssessmentJudge wasteStandardizedAssessmentJudge) {
        return RestResult.succeed(wasteStandardizedAssessmentJudgeService.findWasteStandardizedAssessmentJudgePage(page, wasteStandardizedAssessmentJudge));
    }

    /**
     * 查询规范化考核结果列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询规范化考核结果列表")
    public RestResult<List<WasteStandardizedAssessmentJudge>> getList(WasteStandardizedAssessmentJudge wasteStandardizedAssessmentJudge) {
        return RestResult.succeed(wasteStandardizedAssessmentJudgeService.findWasteStandardizedAssessmentJudgeList(wasteStandardizedAssessmentJudge));
    }

    /**
     * 获取规范化考核结果详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取规范化考核结果详细信息")
    public RestResult<WasteStandardizedAssessmentJudge> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(wasteStandardizedAssessmentJudgeService.findWasteStandardizedAssessmentJudgeById(id));
    }
}
