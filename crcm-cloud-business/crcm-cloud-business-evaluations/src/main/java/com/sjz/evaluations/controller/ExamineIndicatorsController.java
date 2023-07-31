package com.sjz.evaluations.controller;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.evaluations.model.dto.ExamineIndicatorsSaveDTO;
import com.sjz.evaluations.model.entity.ExamineIndicators;
import com.sjz.evaluations.model.entity.ExamineTask;
import com.sjz.evaluations.service.ExamineIndicatorsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 考核指标Controller
 *
 * @author zzyt
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/examineIndicators")
@Api(tags = "考核指标")
public class ExamineIndicatorsController extends BaseController {

    @Autowired
    private ExamineIndicatorsService examineIndicatorsService;

    /**
     * 新增考核指标
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增考核指标")
    @SysLog(title = "新增考核指标", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.INSERT)
    public RestResult add(@Validated @RequestBody ExamineIndicatorsSaveDTO examineIndicatorsSaveDTO) {
        return RestResult.succeed(examineIndicatorsService.saveExamineIndicators(examineIndicatorsSaveDTO));
    }

    /**
     * 修改考核指标
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改考核指标")
    @SysLog(title = "修改考核指标", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@Validated @RequestBody ExamineIndicatorsSaveDTO examineIndicatorsSaveDTO) {
        return RestResult.succeed(examineIndicatorsService.updateExamineIndicators(examineIndicatorsSaveDTO));
    }

    /**
     * 删除考核指标
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除考核指标")
    @SysLog(title = "删除考核指标", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@PathVariable("id") String id) {
        return RestResult.succeed(examineIndicatorsService.deleteExamineIndicators(id));
    }

    /**
     * 获取考核指标详细信息
     */
    @GetMapping(value = "/getById")
    @ApiOperation(value = "获取考核指标详细信息")
    public RestResult getInfo(String id) {
        return RestResult.succeed(examineIndicatorsService.findExamineIndicatorsById(id));
    }

    /**
     * 查询考核指标列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询考核指标列表")
    public RestResult getList(ExamineIndicators examineIndicators) {
        return RestResult.succeed(examineIndicatorsService.findExamineIndicatorsList(examineIndicators));
    }

    /**
     * 分页查询考核指标
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询考核指标")
    public RestResult getPage(PageT page, ExamineIndicators examineIndicators) {
        return RestResult.succeed(examineIndicatorsService.findExamineIndicatorsPage(page, examineIndicators));
    }

    @GetMapping(value = "/getRegionIndicatorsPage")
    @ApiOperation(value = "查询管区考核指标分页")
    public RestResult getRegionIndicatorsPage(PageT page, ExamineIndicators examineIndicators) {
        return RestResult.succeed(examineIndicatorsService.findRegionIndicatorsPage(page, examineIndicators));
    }

    @GetMapping(value = "/getVillageIndicatorsPage")
    @ApiOperation(value = "查询村级考核指标分页")
    public RestResult getVillageIndicatorsPage(PageT page, ExamineIndicators examineIndicators) {
        return RestResult.succeed(examineIndicatorsService.findVillageIndicatorsPage(page, examineIndicators));
    }

}
