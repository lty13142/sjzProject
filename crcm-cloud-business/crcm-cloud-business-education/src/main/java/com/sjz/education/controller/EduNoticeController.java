package com.sjz.education.controller;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.education.model.entity.EduNotice;
import com.sjz.education.model.vo.BaseQueryVO;
import com.sjz.education.service.EduNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: sjz-project
 * @description: 通知公告controller
 * @author: sssccc
 * @create: 2023-04-03 15:48
 **/
@RestController
@RequestMapping("/notice")
@Api(tags = "活动公告管理")
public class EduNoticeController extends BaseController {

    @Autowired
    private EduNoticeService noticeService;

    /**
     * 新增活动公告
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增活动公告")
    @SysLog(title = "新增活动公告", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.INSERT)
    public RestResult add(@Validated @RequestBody EduNotice t) {
        return RestResult.succeed(noticeService.add(t));
    }

    /**
     * 编辑活动公告
     */
    @PostMapping("/edit")
    @ApiOperation(value = "编辑活动公告")
    @SysLog(title = "编辑活动公告", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody EduNotice t) {
        return RestResult.succeed(noticeService.edit(t));
    }

    /**
     * 删除活动公告
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除活动公告")
    @SysLog(title = "编辑活动公告", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@PathVariable("id") String id) {
        return RestResult.succeed(noticeService.deleteById(id));
    }

    /**
     * 活动公告列表
     */
    @GetMapping("/getList")
    @ApiOperation(value = "活动公告列表")
    public RestResult getList(EduNotice t) {
        return RestResult.succeed(noticeService.getList(t));
    }

    /**
     * 活动公告分页
     */
    @GetMapping("/getPage")
    @ApiOperation(value = "活动公告分页")
    public RestResult getPage(PageT page, EduNotice t) {
        return RestResult.succeed(noticeService.getPage(page, t));
    }

    /**
     * 发布活动公告
     */
    @PostMapping("/publish")
    @ApiOperation(value = "活动公告发布")
    @SysLog(title = "活动公告发布", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult publish(@RequestBody EduNotice t) {
        return RestResult.succeed(noticeService.publish(t.getId()));
    }

    /**
     * 批量发布活动公告
     */
    @PostMapping("/batchPublish")
    @ApiOperation(value = "批量发布活动公告")
    @SysLog(title = "批量发布活动公告", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult batchPublish(@RequestBody BaseQueryVO t) {
        return RestResult.succeed(noticeService.batchPublish(t.getIds()));
    }

//    /**
//     * 不通过活动公告
//     */
//    @PostMapping("/reject")
//    @ApiOperation(value = "不通过活动公告")
//    @SysLog(title = "不通过活动公告", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
//    public RestResult reject(String id) {
//        return RestResult.succeed(noticeService.reject(id));
//    }
//
//    /**
//     * 批量不通过活动公告
//     */
//    @PostMapping("/batchReject")
//    @ApiOperation(value = "批量不通过活动公告")
//    @SysLog(title = "批量不通过活动公告", serviceId = ServiceNameConstants.EDUCATION_SERVICE, businessType = BusinessType.UPDATE)
//    public RestResult batchReject(@RequestBody BaseQueryVO t) {
//        return RestResult.succeed(noticeService.batchReject(t.getIds()));
//    }
}
