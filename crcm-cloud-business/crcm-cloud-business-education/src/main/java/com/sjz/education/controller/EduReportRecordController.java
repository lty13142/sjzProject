package com.sjz.education.controller;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.education.model.entity.EduReportRecord;
import com.sjz.education.service.EduReportRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: sjz-project
 * @description: 积分上报管理controller
 * @author: sssccc
 * @create: 2023-04-06 14:44
 **/
@RestController
@RequestMapping("/reportRecord")
@Api(tags = "积分上报管理")
public class EduReportRecordController extends BaseController {

    @Autowired
    private EduReportRecordService reportRecordService;

    /**
     * 新增积分上报
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增积分上报")
    @SysLog(title = "新增积分上报", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.INSERT)
    public RestResult add(@Validated @RequestBody EduReportRecord t) {
        return RestResult.succeed(reportRecordService.add(t));
    }

    /**
     * 修改积分上报
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改积分上报")
    @SysLog(title = "修改积分上报", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody EduReportRecord t) {
        return RestResult.succeed(reportRecordService.edit(t));
    }

    /**
     * 根据id进行删除
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "根据id进行删除")
    @SysLog(title = "根据id进行删除", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@PathVariable("id") String id) {
        return RestResult.succeed(reportRecordService.deleteById(id));
    }

    /**
     * 根据id查找
     */
    @GetMapping("/getById/{id}")
    @ApiOperation(value = "根据id查找")
    public RestResult getById(@PathVariable("id") String id) {
        return RestResult.succeed(reportRecordService.getById(id));
    }

    /**
     * 我的积分上报列表
     */
    @GetMapping("/myList")
    @ApiOperation(value = "我的积分上报列表")
    public RestResult myList(EduReportRecord t) {
        return RestResult.succeed(reportRecordService.getListByUser(t));
    }


    /**
     * 积分上报列表
     */
    @GetMapping("/getList")
    @ApiOperation(value = "积分上报列表")
    public RestResult getList(EduReportRecord t) {
        return RestResult.succeed(reportRecordService.getList(t));
    }

    /**
     * 积分上报分页
     */
    @GetMapping("/getPage")
    @ApiOperation(value = "积分上报分页")
    public RestResult getPage(PageT page, EduReportRecord t) {
        return RestResult.succeed(reportRecordService.getPage(page, t));
    }

    /**
     * 通过
     */
    @PostMapping("/pass")
    @ApiOperation(value = "通过")
    @SysLog(title = "通过", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult pass(@RequestBody EduReportRecord t) {
        return RestResult.succeed(reportRecordService.pass(t));
    }

    /**
     * 不通过
     */
    @PostMapping("/reject")
    @ApiOperation(value = "不通过")
    @SysLog(title = "不通过", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult reject(@RequestBody EduReportRecord t) {
        return RestResult.succeed(reportRecordService.reject(t));
    }

    /**
     * 按村进行数据统计(当年)
     */
    @GetMapping("/getRecordByVillage")
    @ApiOperation(value = "按村进行数据统计(当年)")
    public RestResult getRecordByVillage() {
        return RestResult.succeed(reportRecordService.getRecordByVillage());
    }

    /**
     *按村进行数据统计(当年),并返回村庄明细
     */
    @GetMapping("/getRecordPosition")
    @ApiOperation(value = "按村进行数据统计(当年),并返回村庄明细")
    public RestResult getRecordPosition() {
        return RestResult.succeed(reportRecordService.getRecordPosition());
    }

    /**
     * 积分获得记录折线图(当年)
     */
    @GetMapping("/countByYear")
    @ApiOperation(value = "积分获得记录折线图(当年)")
    public RestResult countByYear() {
        return RestResult.succeed(reportRecordService.countByYear());
    }
}
