package com.sjz.evaluations.controller;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.evaluations.model.dto.ExamineResultDTO;
import com.sjz.evaluations.model.dto.ExamineScoreDTO;
import com.sjz.evaluations.model.dto.RegionScoreDTO;
import com.sjz.evaluations.model.dto.VillageScoreDTO;
import com.sjz.evaluations.model.entity.ExamineTask;
import com.sjz.evaluations.service.ExamineTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 考核任务Controller
 *
 * @author zzyt
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/examineTask")
@Api(tags = "考核任务")
public class ExamineTaskController extends BaseController {

    @Autowired
    private ExamineTaskService examineTaskService;

    /**
     * 新增考核
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增考核")
    @SysLog(title = "新增考核", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.INSERT)
    public RestResult add(@Validated @RequestBody ExamineTask examineTask) {
        return RestResult.succeed(examineTaskService.saveExamineTask(examineTask));
    }

    /**
     * 修改考核
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改考核")
    @SysLog(title = "修改考核", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@Validated @RequestBody ExamineTask examineTask) {
        return RestResult.succeed(examineTaskService.updateExamineTask(examineTask));
    }

    /**
     * 删除考核
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除考核")
    @SysLog(title = "删除考核", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@PathVariable("id") String id) {
        return RestResult.succeed(examineTaskService.deleteExamineTask(id));
    }

    /**
     * 获取考核详细信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取考核详细信息")
    public RestResult getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(examineTaskService.findExamineTaskById(id));
    }

    /**
     * 查询考核列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询考核列表")
    public RestResult getList(ExamineTask examineTask) {
        return RestResult.succeed(examineTaskService.findExamineTaskList(examineTask));
    }

    /**
     * 分页查询考核
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询考核")
    public RestResult getPage(PageT page, ExamineTask examineTask) {
        return RestResult.succeed(examineTaskService.findExamineTaskPage(page, examineTask));
    }

    @GetMapping(value = "/getRegionExamineTaskPage")
    @ApiOperation(value = "查询管区考核任务分页")
    public RestResult getRegionExamineTaskPage(PageT page, ExamineTask examineTask) {
        return RestResult.succeed(examineTaskService.findRegionExamineTaskPage(page, examineTask));
    }

    @GetMapping(value = "/getVillageExamineTaskPage")
    @ApiOperation(value = "查询村级考核任务分页")
    public RestResult getVillageExamineTaskPage(PageT page, ExamineTask examineTask) {
        return RestResult.succeed(examineTaskService.findVillageExamineTaskPage(page, examineTask));
    }

    @GetMapping(value = "/getRegionScorePage")
    @ApiOperation(value = "查询管区的考核任务评分")
    public RestResult getRegionScorePage(PageT page, ExamineScoreDTO examineScoreDTO) {
        return RestResult.succeed(examineTaskService.findRegionScorePage(page, examineScoreDTO));
    }

    @GetMapping(value = "/getVillageScorePage")
    @ApiOperation(value = "查询村级考核评分")
    public RestResult getVillageScorePage(PageT page, VillageScoreDTO villageScoreDTO) {
        return RestResult.succeed(examineTaskService.findVillageScorePage(page, villageScoreDTO));
    }

    @GetMapping(value = "/getTownScorePage")
    @ApiOperation(value = "查询镇级考核任务评分")
    public RestResult getTownScorePage(PageT page, ExamineScoreDTO examineScoreDTO) {
        return RestResult.succeed(examineTaskService.findTownScorePage(page, examineScoreDTO));
    }

    @GetMapping(value = "/getTaskRegionScorePage")
    @ApiOperation(value = "查询考核任务下所有管区的评分情况分页")
    public RestResult getTaskRegionScorePage(PageT page, RegionScoreDTO regionScoreDTO) {
        return RestResult.succeed(examineTaskService.findTaskRegionScorePage(page, regionScoreDTO));
    }

    @GetMapping(value = "/getTownExamineResult")
    @ApiOperation(value = "/查询镇级考核结果")
    public RestResult getTownExamineResult(ExamineResultDTO examineResultDTO) {
        return RestResult.succeed(examineTaskService.findTownExamineResult(examineResultDTO));
    }

    @GetMapping(value = "/getRegionExamineResult")
    @ApiOperation(value = "查询管区考核结果")
    public RestResult getRegionExamineResult(ExamineResultDTO examineResultDTO) {
        return RestResult.succeed(examineTaskService.findRegionExamineResult(examineResultDTO));
    }

    @GetMapping(value = "/getVillageExamineResult")
    @ApiOperation(value = "查询村级考核结果")
    public RestResult getVillageExamineResult(PageT page, VillageScoreDTO villageScoreDTO) {
        return RestResult.succeed(examineTaskService.findVillageExamineResult(page, villageScoreDTO));
    }

}
