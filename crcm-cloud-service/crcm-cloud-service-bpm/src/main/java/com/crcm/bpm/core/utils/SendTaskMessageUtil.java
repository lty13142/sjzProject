package com.crcm.bpm.core.utils;

import com.crcm.bpm.core.constant.BpmConstant;
import com.crcm.bpm.core.constant.enums.SysMessageStatusEnum;
import com.crcm.bpm.core.constant.enums.SysMessageTypeEnum;
import com.crcm.bpm.domain.entity.ModelDO;
import com.crcm.bpm.domain.entity.UserTaskDO;
import com.crcm.bpm.domain.vo.SendDuplicateVo;
import com.crcm.bpm.service.ModelService;
import com.crcm.bpm.utils.ThreadLocalUtil;
import com.crcm.core.utils.SpringContextHolder;
import com.crcm.security.utils.SecurityUtil;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author
 */
public class SendTaskMessageUtil {

    private static SendTaskMessageUtilHelper sendTaskMessageUtilHelper = SpringContextHolder.getBean(SendTaskMessageUtilHelper.class);

    private static ModelService modelService = SpringContextHolder.getBean(ModelService.class);

    /**
     * 发送任务消息
     *
     * @param userTaskList    任务列表
     * @param str             消息内容
     * @param type            消息类型
     * @param status          消息状态
     * @param delete          删除状态
     * @param isDelete        是否为通知前端删除列表消息
     * @param isCandidateUser 是否发送候选人消息，否则发送审批人消息
     */
    public static void send(List<UserTaskDO> userTaskList, String str, Integer type, Integer status, Integer delete, Boolean isDelete, Boolean isCandidateUser, String applyTitle) {
        String userId = SecurityUtil.getCurrentUserId();
//        setRequestAttributes();
        sendTaskMessageUtilHelper.send(userTaskList, str, type, status, delete, isDelete, isCandidateUser, userId, applyTitle);
    }

    /**
    * @Description:  发送抄送消息
    * @Param:
    * @Author: dzl
    * @Date: 2021/3/30 14:53
    */
    public static void sendCopy(List<SendDuplicateVo> list, String str, Integer type, Integer status, Integer delete, Boolean isDelete, Boolean isCandidateUser) {
        String userId = SecurityUtil.getCurrentUserId();
        setRequestAttributes();
        sendTaskMessageUtilHelper.sendCopy(list, str, type, status, delete, isDelete, isCandidateUser, userId);
    }

    public static void sendFinish(String content, String msgTo) {
        String userId = SecurityUtil.getCurrentUserId();
        setRequestAttributes();
        sendTaskMessageUtilHelper.sendFinish(content, msgTo, userId);
    }

    public static void sendRevoke(String content, List<UserTaskDO> userTaskList) {
        String userId = SecurityUtil.getCurrentUserId();
        setRequestAttributes();
        sendTaskMessageUtilHelper.sendRevoke(content, userTaskList, userId);
    }

    /**
     * 删除任务消息
     *
     * @param type            消息类型
     * @param businessId      业务id
     */
    public static void delete(Integer type, String businessId) {
        String userId = SecurityUtil.getCurrentUserId();
        sendTaskMessageUtilHelper.delete(type, businessId, userId);
    }

    public static void sendMsg(UserTaskDO userTask, String ownerUserName){
        if (userTask.getCandidateUserList() != null && !BpmConstant.TASK_TYPE_START.equals(userTask.getTaskType())) {
            String str = ownerUserName + "发起的流程正待您审核";
            ModelDO modelDO = modelService.getById(userTask.getModelId());
            userTask.setFormType(modelDO.getFormType());
            userTask.setFormId(modelDO.getFormId());
            userTask.setProcessDefId(modelDO.getDefinitionId());
            List<UserTaskDO> taskDOList = new ArrayList<>();
            taskDOList.add(userTask);
            send(taskDOList, str, SysMessageTypeEnum.WORK_TO_DO.getValue(), SysMessageStatusEnum.UNREAD.getValue(), 0, false, true, null);
        }
    }

    // 将header信息放到容器中，以访服务间调用时header丢失
    private static void setRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        if (Objects.nonNull(attributes)) {
            HttpServletRequest request = attributes.getRequest();
            String token = request.getHeader("Authorization");
            ThreadLocalUtil.set("Authorization", token);
        }
        RequestContextHolder.setRequestAttributes(requestAttributes, true);
    }
}
