package com.sjz.evaluations.controller;

import java.util.List;

import cn.hutool.core.lang.tree.Tree;
import com.sjz.evaluations.model.vo.AssessmentExamineVo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.sjz.evaluations.model.entity.AssessmentExamine;
import com.sjz.evaluations.service.AssessmentExamineService;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;

/**
 * 考核指标Controller
 * 
 * @author guozhilin
 * @date 2023-04-06
 */
@RestController
@AllArgsConstructor
@RequestMapping("/assessmentExamine")
@Api(tags = "考核指标")
public class AssessmentExamineController extends BaseController {


    private final AssessmentExamineService assessmentExamineService;

    /**
     * 新增考核指标
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增考核指标")
    @SysLog(title = "新增考核指标", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody AssessmentExamine assessmentExamine) {
        return RestResult.succeed(assessmentExamineService.saveAssessmentExamine(assessmentExamine));
    }

    /**
     * 发布考核指标
     */
    @PostMapping("/conference")
    @ApiOperation(value = "发布考核指标")
    @SysLog(title = "发布考核指标", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> conference(@Validated @RequestBody AssessmentExamine assessmentExamine) {
      if (!assessmentExamineService.conference(assessmentExamine)){
          return RestResult.failed("考核指标不能为空！");
      }
        return RestResult.succeed();
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改考核指标")
    @SysLog(title = "修改考核指标", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody AssessmentExamine assessmentExamine) {
        return RestResult.succeed(assessmentExamineService.updateAssessmentExamine(assessmentExamine));
    }

    /**
     * 删除考核指标
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除考核指标")
    @SysLog(title = "删除考核指标", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(assessmentExamineService.deleteAssessmentExamine(id));
    }

    @PostMapping("/deleteByLastId/{id}")
    @ApiOperation(value = "根据最末一级指标删除")
    @SysLog(title = "删除考核指标", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Integer> deleteByLastId(@PathVariable("id") String id) {
        return RestResult.succeed(assessmentExamineService.deleteByLastId(id));
    }


    /**
     * 分页查询考核指标
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询考核指标")
    public RestResult<PageT<AssessmentExamineVo>> getPage(PageT<AssessmentExamine> page, AssessmentExamine assessmentExamine) {
        return RestResult.succeed(assessmentExamineService.findAssessmentExaminePage(page, assessmentExamine));
    }

    /**
     * 查询考核指标列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询考核指标列表")
    public RestResult<List<AssessmentExamine>> getList(AssessmentExamine assessmentExamine) {
        return RestResult.succeed(assessmentExamineService.findAssessmentExamineList(assessmentExamine));
    }

    /**
     * 获取考核指标详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取考核指标详细信息")
    public RestResult<AssessmentExamine> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(assessmentExamineService.findAssessmentExamineById(id));
    }
}
