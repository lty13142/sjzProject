package com.sjz.evaluations.controller;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;

import com.sjz.evaluations.model.entity.Examine;
import com.sjz.evaluations.model.vo.ExamineVo;
import com.sjz.evaluations.service.ExamineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 考核Controller
 * 
 * @author guozhilin
 * @date 2023-04-06
 */
@RestController
@AllArgsConstructor
@RequestMapping("/examine")
@Api(tags = "考核")
public class ExamineController extends BaseController {


    private final ExamineService examineService;

    /**
     * 新增考核
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增考核")
    @SysLog(title = "新增考核", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody Examine examine) {
        return RestResult.succeed(examineService.saveExamine(examine));
    }

    /**
     * 修改考核
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改考核")
    @SysLog(title = "修改考核", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody Examine examine) {
        return RestResult.succeed(examineService.updateExamine(examine));
    }

    /**
     * 删除考核
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除考核")
    @SysLog(title = "删除考核", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(examineService.deleteExamine(id));
    }


    /**
     * 分页查询考核
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询考核")
    public RestResult<PageT<Examine>> getPage(PageT<Examine> page, Examine examine) {
        return RestResult.succeed(examineService.findExaminePage(page, examine));
    }

    /**
     * 查询考核列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询考核列表")
    public RestResult<List<Examine>> getList(Examine examine) {
        return RestResult.succeed(examineService.findExamineList(examine));
    }

    /**
     * 获取考核详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取考核详细信息")
    public RestResult<Examine> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(examineService.findExamineById(id));
    }


    @GetMapping("/getAssessedPage")
    @ApiOperation(value = "分页查询被考核列表考核列表")
    public RestResult<PageT<ExamineVo>> getAssessedPage(PageT<ExamineVo> page, Examine examine){
        return RestResult.succeed(examineService.getAssessedPage(page, examine));
    }
}
