package com.crcm.bpm.core.utils;

import com.crcm.bpm.domain.entity.UserTaskDO;
import com.crcm.bpm.domain.vo.SendDuplicateVo;
import com.crcm.bpm.service.ApplyService;
import com.crcm.bpm.service.ModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/11/19 16:29 <br>
 * @Author: <a>bot</a>
 */
@Slf4j
@Component
public class SendTaskMessageUtilHelper {

//    @Resource
//    private FeignSysMessageService feignSysMessageService;

    @Resource
    private ApplyService applyService;

    @Resource
    private ModelService modelService;

    /**
     * 发送任务消息
     *
     * @param userTaskList    任务列表
     * @param str             消息内容
     * @param status          消息状态
     * @param delete          删除状态
     * @param isDelete        是否为通知前端删除列表消息
     * @param isCandidateUser 是否发送候选人消息，否则发送审批人消息
     * @param msgFrom         消息发送人
     */
//    @Async
    public void send(List<UserTaskDO> userTaskList, String str, Integer type, Integer status, Integer delete,
                     Boolean isDelete, Boolean isCandidateUser, String msgFrom, String applyTitle) {
//        try {
//            if (userTaskList != null && userTaskList.size() != 0) {
//                List<SysMessage> sysMessageList = new ArrayList<>();
//                ApplyDO applyDO = applyService.getById(userTaskList.get(0).getApplyId());
//                ModelDO modelDO = modelService.findModelById(applyDO.getModelId() + "");
//                userTaskList.forEach(userTaskDO -> {
//                    UserTaskMsgDto userTaskMsgDto = new UserTaskMsgDto();
//                    if (!isDelete) {
//                        BeanUtil.copyProperties(userTaskDO, userTaskMsgDto);
//                        if (!StringUtils.isEmpty(applyTitle)) {
//                            userTaskMsgDto.setApplyTitle(applyTitle);
//                        } else {
//                            userTaskMsgDto.setApplyTitle(applyDO.getApplyTitle());
//                        }
//                    }
//                    userTaskMsgDto.setFormType(modelDO.getFormType());
//                    userTaskMsgDto.setFormId(modelDO.getFormId());
//                    userTaskMsgDto.setFormCode(modelDO.getFormCode());
//                    getMsgTo(userTaskDO, isCandidateUser).forEach(user -> {
//                        SysMessage sysMessage = new SysMessage();
//                        sysMessage.setMark(SysMessageMarkEnum.PROCESS_AUDIT.getValue());
//                        sysMessage.setContent(str);
//                        sysMessage.setType(type);
//                        sysMessage.setStatus(status);
//                        sysMessage.setDeleted(delete);
//                        sysMessage.setParame(JSONObject.toJSONString(userTaskMsgDto));
//                        sysMessage.setMsgFrom(msgFrom);
//                        sysMessage.setMsgTo(user);
//                        sysMessage.setIsDelete(isDelete);
//                        sysMessage.setBussinessId(userTaskDO.getId().toString());
//                        sysMessageList.add(sysMessage);
//                        if (!isDelete) {
//                            try {
//                                String count = String.valueOf(feignSysMessageService.getUnreadCount(sysMessage.getMsgTo()).getData());
//                                AppPush.sendMessageToAppByEmployeeId(sysMessage.getMsgTo(), "您有新的流程待处理", str, JSON.toJSONString(sysMessage), count);
//                            } catch (Exception e) {
//                                log.error("SendTaskMessageUtil.send,发送移动端个推消息失败", e);
//                            }
//                        }
//                    });
//                });
//                feignSysMessageService.sendMessageToUser(sysMessageList);
//            }
//        } catch (Exception e) {
//            log.error("SendTaskMessageUtil.send,发送消息失败", e);
//        }

    }

    /**
     * @Description: 推送抄送消息
     * @Param:
     * @Author: dzl
     * @Date: 2021/3/30 14:47
     */
    @Async
    public void sendCopy(List<SendDuplicateVo> list, String str, Integer type, Integer status, Integer delete,
                         Boolean isDelete, Boolean isCandidateUser, String msgFrom) {
//        try {
//            if (list != null && list.size() != 0) {
//                List<SysMessage> sysMessageList = new ArrayList<>();
//                ApplyDO applyDO = applyService.getById(list.get(0).getApplyId());
//                ModelDO modelDO = modelService.findModelById(applyDO.getModelId() + "");
//                list.forEach(userTaskDO -> {
//                    UserTaskMsgDto userTaskMsgDto = new UserTaskMsgDto();
//                    if (!isDelete) {
//                        BeanUtil.copyProperties(userTaskDO, userTaskMsgDto);
//                        userTaskMsgDto.setApplyTitle(applyDO.getApplyTitle());
//                    }
//                    userTaskMsgDto.setFormType(modelDO.getFormType());
//                    userTaskMsgDto.setFormId(modelDO.getFormId());
//                    SysMessage sysMessage = new SysMessage();
//                    sysMessage.setMark(SysMessageMarkEnum.COPY_SEND.getValue());
//                    sysMessage.setContent(str);
//                    sysMessage.setType(type);
//                    sysMessage.setStatus(status);
//                    sysMessage.setDeleted(delete);
//                    sysMessage.setParame(JSONObject.toJSONString(userTaskMsgDto));
//                    sysMessage.setMsgFrom(msgFrom);
//                    sysMessage.setMsgTo(userTaskDO.getTaskAssigneeUserId());
//                    sysMessage.setIsDelete(isDelete);
//                    sysMessage.setBussinessId(userTaskDO.getId().toString());
//                    sysMessageList.add(sysMessage);
//                    if (!isDelete) {
//                        try {
//                            String count = String.valueOf(feignSysMessageService.getUnreadCount(sysMessage.getMsgTo()).getData());
//                            AppPush.sendMessageToAppByEmployeeId(sysMessage.getMsgTo(), "您有新的抄送待查看", str, JSON.toJSONString(sysMessage), count);
//                        } catch (Exception e) {
//                            log.error("SendTaskMessageUtil.send,发送移动端个推消息失败", e);
//                        }
//                    }
//                });
//                feignSysMessageService.sendMessageToUser(sysMessageList);
//            }
//        } catch (Exception e) {
//            log.error("SendTaskMessageUtil.send,发送消息失败", e);
//        }

    }

    public static List<String> getMsgTo(UserTaskDO userTask, Boolean isCandidateUser) {
        List<String> list = new ArrayList<>();
        if (isCandidateUser) {
            Collections.addAll(list, userTask.getCandidateUserList().split(","));
        } else {
            list.add(userTask.getTaskAssigneeUserId());
        }
        return list;
    }

    public void delete(Integer type, String businessId, String userId) {
//        List<SysMessage> sysMessageList = new ArrayList<>();
//        SysMessage sysMessage = new SysMessage();
//        sysMessage.setMark(SysMessageMarkEnum.PROCESS_AUDIT.getValue());
//        sysMessage.setType(type);
//        sysMessage.setStatus(SysMessageStatusEnum.READ.getValue());
//        // 删除
//        sysMessage.setDeleted(1);
//        sysMessage.setMsgTo(userId);
//        sysMessage.setIsDelete(true);
//        sysMessage.setBussinessId(businessId);
//        sysMessageList.add(sysMessage);
//        feignSysMessageService.sendMessageToUser(sysMessageList);
    }

    @Async
    public void sendFinish(String content, String msgTo, String msgFrom) {
//        try {
//            List<SysMessage> sysMessageList = new ArrayList<>();
//            SysMessage sysMessage = new SysMessage();
//            sysMessage.setMark(SysMessageMarkEnum.FLOW_FINISH.getValue());
//            sysMessage.setContent(content);
//            sysMessage.setType(SysMessageTypeEnum.WORK_TO_READ.getValue());
//            sysMessage.setStatus(SysMessageStatusEnum.UNREAD.getValue());
//            sysMessage.setDeleted(0);
//            sysMessage.setParame("");
//            sysMessage.setMsgFrom(msgFrom);
//            sysMessage.setMsgTo(msgTo);
//            sysMessage.setIsDelete(false);
//            sysMessage.setBussinessId(null);
//            sysMessageList.add(sysMessage);
//            feignSysMessageService.sendMessageToUser(sysMessageList);
//            // APP消息
//            String count = String.valueOf(feignSysMessageService.getUnreadCount(sysMessage.getMsgTo()).getData());
//            AppPush.sendMessageToAppByEmployeeId(sysMessage.getMsgTo(), "流程结束通知", content, JSON.toJSONString(sysMessage), count);
//        } catch (Exception e) {
//            log.error("SendTaskMessageUtil.send,发送消息失败", e);
//        }
    }

    @Async
    public void sendRevoke(String content, List<UserTaskDO> userTaskList, String msgFrom) {
//        try {
//            List<SysMessage> sysMessageList = new ArrayList<>();
//            for (UserTaskDO userTaskDO : userTaskList) {
//                SysMessage sysMessage = new SysMessage();
//                sysMessage.setMark(SysMessageMarkEnum.FLOW_REVOKE.getValue());
//                sysMessage.setContent(content);
//                sysMessage.setType(SysMessageTypeEnum.WORK_TO_READ.getValue());
//                sysMessage.setStatus(SysMessageStatusEnum.UNREAD.getValue());
//                sysMessage.setDeleted(0);
//                sysMessage.setParame("");
//                sysMessage.setMsgFrom(msgFrom);
//                sysMessage.setMsgTo(userTaskDO.getTaskAssigneeUserId());
//                sysMessage.setIsDelete(false);
//                sysMessage.setBussinessId(null);
//                sysMessageList.add(sysMessage);
//                // APP消息
//                String count = String.valueOf(feignSysMessageService.getUnreadCount(sysMessage.getMsgTo()).getData());
//                AppPush.sendMessageToAppByEmployeeId(sysMessage.getMsgTo(), "流程撤销通知", content, JSON.toJSONString(sysMessage), count);
//            }
//            feignSysMessageService.sendMessageToUser(sysMessageList);
//        } catch (Exception e) {
//            log.error("SendTaskMessageUtil.send,发送消息失败", e);
//        }
    }
}
