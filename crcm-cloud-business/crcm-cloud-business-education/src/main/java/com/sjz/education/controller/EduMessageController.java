package com.sjz.education.controller;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.education.model.entity.EduMessage;
import com.sjz.education.model.vo.BaseQueryVO;
import com.sjz.education.service.EduMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: sjz-project
 * @description: 通知controller
 * @author: sssccc
 * @create: 2023-04-06 10:03
 **/
@RestController
@RequestMapping("/message")
@Api(tags = "通知管理")
public class EduMessageController extends BaseController {

    @Autowired
    private EduMessageService messageService;

    /**
     * 新增消息通知
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增消息通知")
    @SysLog(title = "新增消息通知", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.INSERT)
    public RestResult add(@Validated @RequestBody EduMessage t) {
        return RestResult.succeed(messageService.add(t));
    }

    /**
     * 编辑消息通知
     */
    @PostMapping("/edit")
    @ApiOperation(value = "编辑消息通知")
    @SysLog(title = "编辑消息通知", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody EduMessage t) {
        return RestResult.succeed(messageService.edit(t));
    }

    /**
     * 删除消息通知
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除消息通知")
    @SysLog(title = "编辑消息通知", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@PathVariable("id") String id) {
        return RestResult.succeed(messageService.deleteById(id));
    }

    /**
     * 消息通知列表
     */
    @GetMapping("/getList")
    @ApiOperation(value = "消息通知列表")
    public RestResult getList(EduMessage t) {
        return RestResult.succeed(messageService.getList(t));
    }


    /**
     * 我的消息通知列表
     */
    @GetMapping("/myList")
    @ApiOperation(value = "我的消息通知列表")
    public RestResult myList(EduMessage t) {
        return RestResult.succeed(messageService.findByUser(t));
    }

    /**
     * 我的消息通知分页
     */
    @GetMapping("/myPage")
    @ApiOperation(value = "我的消息通知分页")
    public RestResult myPage(PageT page, EduMessage t) {
        return RestResult.succeed(messageService.findByUser(page, t));
    }

    /**
     * 消息通知分页
     */
    @GetMapping("/getPage")
    @ApiOperation(value = "消息通知分页")
    public RestResult getPage(PageT page, EduMessage t) {
        return RestResult.succeed(messageService.getPage(page, t));
    }

    /**
     * 发布消息通知
     */
    @PostMapping("/publish")
    @ApiOperation(value = "消息通知发布")
    @SysLog(title = "消息通知发布", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult publish(@RequestBody EduMessage t) {
        return RestResult.succeed(messageService.publish(t.getId()));
    }

    /**
     * 批量发布消息通知
     */
    @PostMapping("/batchPublish")
    @ApiOperation(value = "批量发布消息通知")
    @SysLog(title = "批量发布消息通知", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult batchPublish(@RequestBody BaseQueryVO t) {
        return RestResult.succeed(messageService.batchPublish(t.getIds()));
    }

//    /**
//     * 不通过活动公告
//     */
//    @PostMapping("/reject")
//    @ApiOperation(value = "不通过活动公告")
//    @SysLog(title = "不通过活动公告", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
//    public RestResult reject(String id) {
//        return RestResult.succeed(messageService.reject(id));
//    }
//
//    /**
//     * 批量不通过活动公告
//     */
//    @PostMapping("/batchReject")
//    @ApiOperation(value = "批量不通过活动公告")
//    @SysLog(title = "批量不通过活动公告", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
//    public RestResult batchReject(@RequestBody BaseQueryVO t) {
//        return RestResult.succeed(messageService.batchReject(t.getIds()));
//    }
}
