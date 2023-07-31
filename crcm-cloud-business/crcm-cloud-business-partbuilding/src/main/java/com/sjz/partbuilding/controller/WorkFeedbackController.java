package com.sjz.partbuilding.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.cloud.start.data.utils.UtilPage;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.exception.SystemException;
import com.crcm.core.response.RestResult;
import com.crcm.core.vo.PageVO;
import com.sjz.partbuilding.model.entity.WorkFeedback;
import com.sjz.partbuilding.model.vo.WorkFeedBackVo;
import com.sjz.partbuilding.service.WorkFeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 工作反馈controller
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/17 11:29
 */
@Api(tags = {"工作反馈表接口"})
@RestController
@RequestMapping("/workFeedback")
public class WorkFeedbackController extends BaseController {

    @Resource
    private WorkFeedbackService workFeedbackService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @SysLog(title = "新增工作反馈", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.INSERT)
    public RestResult save(@RequestBody WorkFeedback workFeedback) {
        workFeedbackService.saveWorkFeedback(workFeedback);
        return RestResult.succeed();
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改工作反馈", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody WorkFeedback workFeedback) {
        workFeedbackService.updateWorkFeedback(workFeedback);
        return RestResult.succeed();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @SysLog(title = "删除工作反馈", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@RequestBody WorkFeedback workFeedback) {
        workFeedbackService.deleteById(workFeedback.getId());
        return RestResult.succeed();
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(String id) {
        return RestResult.succeed(workFeedbackService.findById(id));
    }

    @GetMapping("/getList")
    @ApiOperation(value = "查询列表")
    public RestResult getList(WorkFeedback workFeedback) {
        return RestResult.succeed(workFeedbackService.findList(workFeedback));
    }


    @GetMapping("/getPage")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(WorkFeedback workFeedback, PageVO pageVo) {
        return RestResult.succeed(workFeedbackService.findPage(UtilPage.initPage(pageVo), workFeedback));
    }

    @GetMapping("/getWorkReportingSituation")
    @ApiOperation(value = "获取工作部署的提报情况")
    public RestResult getWorkReportingSituation(PageVO pageVo, WorkFeedBackVo workFeedbackVo) {
        IPage iPage = workFeedbackService.getWorkReportingSituation(UtilPage.initPage(pageVo), workFeedbackVo);
        return RestResult.succeed(iPage);
    }

    @GetMapping("/judgeWorkFeedBack")
    @ApiOperation(value = "判断是否可以反馈")
    public RestResult judgeWorkFeedBack(WorkFeedback workFeedback) {
        WorkFeedback feedback = null;
        try {
            feedback = workFeedbackService.judgeWorkFeedBack(workFeedback);
        } catch (SystemException e) {
            return RestResult.failed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestResult.succeed(feedback);
    }


    @GetMapping("/getStatisticsOfWorkFeedback")
    @ApiOperation(value = "获取工作反馈情况统计")
    public RestResult getStatisticsOfWorkFeedback(WorkFeedback workFeedback) {
        Map<String, Object> resultMap = workFeedbackService.getStatisticsOfWorkFeedback(workFeedback);
        return RestResult.succeed(resultMap);
    }

    @GetMapping("/getAccumulationOfWorkFeedback")
    @ApiOperation(value = "获取工作反馈的累计情况")
    public RestResult getAccumulationOfWorkFeedback(WorkFeedback workFeedback) {
        Map<String, Object> resultMap = workFeedbackService.getAccumulationOfWorkFeedbackSum(workFeedback);
        return RestResult.succeed(resultMap);
    }

    @GetMapping("getDataStatisticsWorkSupervision")
    @ApiOperation(value = "获取数据统计工作督办")
    public RestResult getStatisticsWorkSupervision(Page page, WorkFeedBackVo workFeedBackVo) {
        List<Map<String, Object>> resultList = workFeedbackService.getDataStatisticsWorkSupervision(page,workFeedBackVo);
        return RestResult.succeed(resultList);
    }

}

