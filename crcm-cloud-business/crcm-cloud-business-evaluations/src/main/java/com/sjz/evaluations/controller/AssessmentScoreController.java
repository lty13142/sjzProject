package com.sjz.evaluations.controller;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.evaluations.model.entity.AssessmentScore;
import com.sjz.evaluations.model.vo.AssessmentStatics;
import com.sjz.evaluations.model.vo.scoreVo;
import com.sjz.evaluations.service.AssessmentScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 考核人员分数表Controller
 * 
 * @author guozhilin
 * @date 2023-04-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/assessmentScore")
@Api(tags = "考核人员分数表")
public class AssessmentScoreController extends BaseController {


    private final AssessmentScoreService assessmentScoreService;

    /**
     * 新增考核人员分数确认
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增考核人员分数确认")
    @SysLog(title = "新增考核人员分数确认", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody AssessmentScore assessmentScore) {
        return RestResult.succeed(assessmentScoreService.saveAssessmentScore(assessmentScore));
    }

    /**
     * 修改考核人员分数和附件
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改考核人员分数和附件")
    @SysLog(title = "修改考核人员分数和附件", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@RequestBody AssessmentScore assessmentScore) {
        return RestResult.succeed(assessmentScoreService.edit(assessmentScore));
    }

    @PostMapping("/updateAssessmentScore")
    @ApiOperation(value = "修改状态确认/完成")
    @SysLog(title = "修改状态确认/完成", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Integer> updateAssessmentScore(@RequestBody AssessmentScore assessmentScore) {
        return RestResult.succeed(assessmentScoreService.updateAssessmentScore(assessmentScore));
    }

    /**
     * 删除考核人员分数确认
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除考核人员分数确认")
    @SysLog(title = "删除考核人员分数确认", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(assessmentScoreService.deleteAssessmentScore(id));
    }


    /**
     * 分页查询考核人员分数确认
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询考核人员分数确认")
    public RestResult<PageT<AssessmentScore>> getPage(PageT<AssessmentScore> page, AssessmentScore assessmentScore) {
        return RestResult.succeed(assessmentScoreService.findAssessmentScorePage(page, assessmentScore));
    }

    /**
     * 查询考核人员分数确认列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询考核人员分数确认列表")
    public RestResult<List<AssessmentScore>> getList(AssessmentScore assessmentScore) {
        return RestResult.succeed(assessmentScoreService.findAssessmentScoreList(assessmentScore));
    }

    /**
     * 获取考核人员分数确认详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取考核人员分数确认详细信息")
    public RestResult<AssessmentScore> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(assessmentScoreService.findAssessmentScoreById(id));
    }


    /**
     * 大屏考核得分根据年度查询
     */
    @GetMapping(value = "/assessmentScorePage")
    @ApiOperation(value = "大屏考核得分根据年度查询")
    public RestResult<PageT<scoreVo>> assessmentScorePage(PageT<AssessmentScore> page, AssessmentScore assessmentScore){
        return RestResult.succeed(assessmentScoreService.assessmentScorePage(page,assessmentScore));
    }

    @GetMapping(value = "/lastStatistics")
    @ApiOperation(value = "查询最后5名排名")
    public RestResult<List<AssessmentScore>> lastStatistics(){
        return RestResult.succeed(assessmentScoreService.lastStatistics());
    }
    @GetMapping(value = "/scoreCount")
    @ApiOperation(value = "根据村查询考核组织分数统计")
    public RestResult<List<Map<String,Object>>> scoreCount(String areaId,String yearly){
        return RestResult.succeed(assessmentScoreService.scoreCount(areaId,yearly));
    }

    @GetMapping(value = "/indicatorCategoryStatistics")
    @ApiOperation(value = "根据村查询考核组织分数统计")
    public RestResult<List<Map<String,Object>>> indicatorCategoryStatistics(String yearly){
        return RestResult.succeed(assessmentScoreService.indicatorCategoryStatistics(yearly));
    }


    @GetMapping(value = "/assessmentStatics")
    @ApiOperation(value = "大屏-考核完成情况排名")
    public RestResult<List<AssessmentStatics>> assessmentStatics(){
        return RestResult.succeed(assessmentScoreService.assessmentStatics());
    }



}
