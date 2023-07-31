package com.sjz.education.controller;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.education.model.entity.EduPointsRule;
import com.sjz.education.model.vo.BaseQueryVO;
import com.sjz.education.service.EduPointsRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: sjz-project
 * @description: 积分规则管理controller
 * @author: sssccc
 * @create: 2023-04-03 17:28
 **/
@RestController
@RequestMapping("/pointsRule")
@Api(tags = "积分规则管理")
public class EduPointsRuleController extends BaseController {

    @Autowired
    private EduPointsRuleService pointsRuleService;

    /**
     * 新增积分规则
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增积分规则")
    @SysLog(title = "新增积分规则", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.INSERT)
    public RestResult add(@Validated @RequestBody EduPointsRule t) {
        return RestResult.succeed(pointsRuleService.add(t));
    }
    
    /**
     * 修改积分规则
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改积分规则")
    @SysLog(title = "修改积分规则", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody EduPointsRule t) {
        return RestResult.succeed(pointsRuleService.edit(t));
    }

    /**
     * 根据id进行删除
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "根据id进行删除")
    @SysLog(title = "根据id进行删除", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@PathVariable("id") String id) {
        boolean flag = pointsRuleService.deleteById(id);
        if (!flag){
            return RestResult.failed("有子级无法删除，先删除子级后才可以删除上级");
        }
        return RestResult.succeed(flag);
    }

    /**
     * 通过id查询
     */
    @GetMapping("/getById/{id}")
    @ApiOperation(value = "通过id查询")
    public RestResult getById(@PathVariable("id") String id) {
        return RestResult.succeed(pointsRuleService.findById(id));
    }

    /**
     * 获取积分规则列表
     */
    @GetMapping("/getList")
    @ApiOperation(value = "获取积分规则列表")
    public RestResult getList(EduPointsRule t) {
        return RestResult.succeed(pointsRuleService.getList(t));
    }

    /**
     * 获取积分规则树状图
     */
    @GetMapping("/getTree")
    @ApiOperation(value = "获取积分规则树状图")
    public RestResult getTree(EduPointsRule t) {
        return RestResult.succeed(pointsRuleService.getTree(t));
    }

    /**
     * 获取积分规则分页
     */
    @GetMapping("/getPage")
    @ApiOperation(value = "获取积分规则分页")
    public RestResult getPage(PageT page, EduPointsRule t) {
        return RestResult.succeed(pointsRuleService.getPage(page, t));
    }

    /**
     * 发布
     */
    @PostMapping("/publish")
    @ApiOperation(value = "发布")
    @SysLog(title = "发布", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult publish(@RequestBody EduPointsRule t) {
        return RestResult.succeed(pointsRuleService.publish(t.getId()));
    }

    /**
     * 批量发布
     */
    @PostMapping("/batchPublish")
    @ApiOperation(value = "批量发布")
    @SysLog(title = "批量发布", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult batchPublish(@RequestBody BaseQueryVO t) {
        return RestResult.succeed(pointsRuleService.batchPublish(t.getIds()));
    }
//
//    /**
//     * 审核不通过
//     */
//    @PostMapping("/reject")
//    @ApiOperation(value = "审核不通过")
//    @SysLog(title = "审核不通过", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
//    public RestResult reject(String id) {
//        return RestResult.succeed(pointsRuleService.reject(id));
//    }
//
//    /**
//     * 批量审核不通过
//     */
//    @PostMapping("/batchReject")
//    @ApiOperation(value = "批量审核不通过")
//    @SysLog(title = "批量审核不通过", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
//    public RestResult batchReject(@RequestBody BaseQueryVO t) {
//        return RestResult.succeed(pointsRuleService.batchReject(t.getIds()));
//    }
}
