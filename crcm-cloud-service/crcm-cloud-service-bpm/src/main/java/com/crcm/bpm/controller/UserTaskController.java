package com.crcm.bpm.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.bpm.core.common.BpmError;
import com.crcm.bpm.domain.dto.ProcessCirculationDto;
import com.crcm.bpm.domain.dto.request.ClaimTaskDTO;
import com.crcm.bpm.domain.dto.request.UserTaskInfoQueryDTO;
import com.crcm.bpm.domain.vo.ClaimTaskVO;
import com.crcm.bpm.domain.vo.NodeQueryVO;
import com.crcm.bpm.domain.vo.UserTaskQueryVO;
import com.crcm.bpm.service.UserTaskService;
import com.crcm.bpm.utils.FormDataUtil;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import com.crcm.security.utils.SecurityUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = {"用户任务表接口"})
@RestController
@RequestMapping("/flow/userTask")
public class UserTaskController extends BaseController {

    @Autowired
    private UserTaskService userTaskService;

    @SysLog(title = "获取草稿列表")
    @ApiOperation(value = "获取草稿列表", notes = "获取草稿列表")
    @GetMapping("/getDraftList")
    public RestResult getDraftListByCondition(Page page, @Valid UserTaskQueryVO userTaskQueryVO) {
        if (userTaskQueryVO == null) {
            return RestResult.failed(BpmError.ILLEGAL_CHECK_ERROR.getCode(), BpmError.ILLEGAL_CHECK_ERROR.getMessage());
        }
        UserTaskInfoQueryDTO userTaskInfoQueryDTO = BeanUtil.copyProperties(userTaskQueryVO, UserTaskInfoQueryDTO.class);
        return RestResult.succeed(userTaskService.getDraftListByCondition(page, userTaskInfoQueryDTO));
    }

    @SysLog(title = "获取申请列表")
    @ApiOperation(value = "获取申请列表", notes = "获取申请列表")
    @GetMapping("/getApplyList")
    public RestResult getApplyList(Page page, @Valid UserTaskQueryVO userTaskQueryVO) {
        if (userTaskQueryVO == null) {
            return RestResult.failed(BpmError.ILLEGAL_CHECK_ERROR.getCode(), BpmError.ILLEGAL_CHECK_ERROR.getMessage());
        }
        UserTaskInfoQueryDTO userTaskInfoQueryDTO = BeanUtil.copyProperties(userTaskQueryVO, UserTaskInfoQueryDTO.class);
        return RestResult.succeed(userTaskService.getApplyListByCondition(page, userTaskInfoQueryDTO));
    }

    @SysLog(title = "获取已办列表")
    @ApiOperation(value = "获取已办列表", notes = "获取已办列表")
    @GetMapping("/getHaveDoList")
    public RestResult getHaveDoListByCondition(Page page, @Valid UserTaskQueryVO userTaskQueryVO) {
        if (userTaskQueryVO == null) {
            return RestResult.failed(BpmError.ILLEGAL_CHECK_ERROR.getCode(), BpmError.ILLEGAL_CHECK_ERROR.getMessage());
        }
        UserTaskInfoQueryDTO userTaskInfoQueryDTO = BeanUtil.copyProperties(userTaskQueryVO, UserTaskInfoQueryDTO.class);
        return RestResult.succeed(userTaskService.getHaveDoListByCondition(page, userTaskInfoQueryDTO));
    }

    @SysLog(title = "获取待办列表")
    @ApiOperation(value = "获取待办列表", notes = "获取待办列表")
    @GetMapping("/getToDoList")
    public RestResult getToDoListByCondition(Page page, @Valid UserTaskQueryVO userTaskQueryVO) {
        if (userTaskQueryVO == null) {
            return RestResult.failed(BpmError.ILLEGAL_CHECK_ERROR.getCode(), BpmError.ILLEGAL_CHECK_ERROR.getMessage());
        }
        UserTaskInfoQueryDTO userTaskInfoQueryDTO = BeanUtil.copyProperties(userTaskQueryVO, UserTaskInfoQueryDTO.class);
        return RestResult.succeed(userTaskService.getToDoListByCondition(page, userTaskInfoQueryDTO));
    }

    @SysLog(title = "完成任务")
    @ApiOperation(value = "完成任务", notes = "完成任务")
    @PostMapping("/completeTask")
    public RestResult completeTask(@RequestBody @Valid ProcessCirculationDto processCirculationDto) {
        if (processCirculationDto.getBusinessData() != null && processCirculationDto.getBusinessData().containsKey("formJson")
                && processCirculationDto.getBusinessData().get("formJson") != null) {
            processCirculationDto.getBusinessData().put("formJson", FormDataUtil.replaceFormAttribute(processCirculationDto.getBusinessData().get("formJson").toString()));
        }
        return RestResult.succeed(userTaskService.completeTask(processCirculationDto));
    }

    @SysLog(title = "签收任务")
    @ApiOperation(value = "签收任务", notes = "签收任务")
    @PostMapping("/claimTask")
    public RestResult claimTask(@RequestBody @Valid ClaimTaskVO claimTaskVO) {
        ClaimTaskDTO claimTaskDTO = BeanUtil.copyProperties(claimTaskVO, ClaimTaskDTO.class);
        claimTaskDTO.setClaimId(SecurityUtil.getCurrentUserId());
        claimTaskDTO.setClaimName(SecurityUtil.getCurrentUsername());
        claimTaskDTO.setBusinessData(claimTaskVO.getBusinessData());
        return RestResult.succeed(userTaskService.claimTask(claimTaskDTO));
    }

    @SysLog(title = "任务反签收")
    @RequestMapping(value = "/unClaim", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "任务反签收", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String")})
    public RestResult unclaim(String taskId) throws Exception {
        userTaskService.unClaimTask(taskId);
        return RestResult.succeed("任务反签收成功");
    }

    @SysLog(title = "获取下一节点信息")
    @ApiOperation(value = "获取下一节点信息", notes = "获取下一节点信息")
    @PostMapping("/getNextNodeUser")
    public RestResult getNextNodeUser(@RequestBody @Valid NodeQueryVO nodeQueryVO) {
        return RestResult.succeed(userTaskService.getNextNodeInfo(nodeQueryVO));
    }

    @SysLog(title = "获取下一节点审核人信息")
    @ApiOperation(value = "获取下一节点审核人信息", notes = "获取下一节点审核人信息")
    @PostMapping("/getNextNodeAssignees")
    public RestResult getNextNodeAssignees(@RequestBody @Valid NodeQueryVO nodeQueryVO) {
        return RestResult.succeed(userTaskService.getNextNodeAssignees(nodeQueryVO));
    }

    @SysLog(title = "获取意见流转")
    @ApiOperation(value = "获取意见流转", notes = "获取意见流转")
    @RequestMapping("/getApproveOpinionList")
    public RestResult getApproveOpinionList(String applyId) {
        return RestResult.succeed(userTaskService.getApproveOpinionList(applyId));
    }

    @SysLog(title = "获取可退回节点")
    @ApiOperation(value = "获取可退回节点", notes = "获取可退回节点")
    @RequestMapping("/getReturnNode/{taskId}")
    public RestResult getReturnNode(@ApiParam(required = true, name = "任务编号", value = "taskId", example = "1") @PathVariable("taskId") Long taskId) {
        return RestResult.succeed(userTaskService.getReturnNode(taskId));
    }

    @SysLog(title = "获取挂起列表")
    @ApiOperation(value = "获取挂起列表", notes = "获取挂起列表")
    @GetMapping("/getHangList")
    public RestResult getHangList(Page page, @Valid UserTaskQueryVO userTaskQueryVO) {
        if (userTaskQueryVO == null) {
            return RestResult.failed(BpmError.ILLEGAL_CHECK_ERROR.getCode(), BpmError.ILLEGAL_CHECK_ERROR.getMessage());
        }
        UserTaskInfoQueryDTO userTaskInfoQueryDTO = BeanUtil.copyProperties(userTaskQueryVO, UserTaskInfoQueryDTO.class);
        userTaskInfoQueryDTO.setTaskStatus(7);
        return RestResult.succeed(userTaskService.getToDoListByCondition(page, userTaskInfoQueryDTO));
    }

    @SysLog(title = "获取待办任务数")
    @ApiOperation(value = "获取待办任务数", notes = "获取待办任务数")
    @GetMapping("/getToDoNumber")
    public RestResult getToDoNumber() {
        return RestResult.succeed(userTaskService.getToDoNumber());
    }
}

