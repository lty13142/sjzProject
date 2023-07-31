package com.crcm.bpm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.bpm.domain.vo.SendDuplicateVo;
import com.crcm.bpm.service.SendDuplicateService;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.response.RestResult;
import com.crcm.security.utils.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: open-ai-center
 * @description:
 * @author: zxb
 * @create: 2021-03-30 10:58
 **/
@Api(tags = {"抄送接口"})
@RestController
@RequestMapping("/flow/sendDuplicate")
public class SendDuplicateContorller {

    @Autowired
    private SendDuplicateService sendDuplicateService;

    /**
     * 分页查询接收的抄送信息
     */
    @SysLog(title = "分页查询接收的抄送信息")
    @GetMapping("/page")
    public RestResult getPage(Page page, SendDuplicateVo sendDuplicate) {
        return RestResult.succeed(sendDuplicateService.getPage(page, sendDuplicate));
    }

    @SysLog(title = "设置抄送为已读")
    @PostMapping("/setCopyReaded")
    public RestResult setCopyReaded(@RequestBody SendDuplicateVo sendDuplicate) {
        sendDuplicateService.setCopyReaded(sendDuplicate);
        return RestResult.succeed();
    }

    /**
     * 分页查询发起的抄送信息
     */
    @SysLog(title = "分页查询发起的抄送信息")
    @GetMapping("/sendPage")
    public RestResult sendPage(Page page, SendDuplicateVo sendDuplicate) {
        return RestResult.succeed(sendDuplicateService.sendPage(page, sendDuplicate));
    }

    @ApiOperation(value = "获取抄送未查看任务数", notes = "获取抄送未查看任务数")
    @GetMapping("/getSendDuplicateNumber")
    public RestResult getSendDuplicateNumber() {
        LambdaQueryWrapper<SendDuplicateVo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SendDuplicateVo::getTaskAssigneeUserId, SecurityUtil.getCurrentUserId());
        wrapper.eq(SendDuplicateVo::getTaskStatus, 1);
        return RestResult.succeed(sendDuplicateService.count(wrapper));
    }

    @GetMapping("/getAllPage")
    public RestResult getAllPage(Page page, SendDuplicateVo sendDuplicate) {
        return RestResult.succeed(sendDuplicateService.getAllPage(page, sendDuplicate));
    }

    @SysLog(title = "删除抄送")
    @PostMapping("/delete")
    public RestResult delete(@RequestBody SendDuplicateVo sendDuplicate) {
        return RestResult.succeed(sendDuplicateService.deleteById(sendDuplicate.getId()));
    }

}
