package com.crcm.bpm.core.listener;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crcm.bpm.core.constant.BpmConstant;
import com.crcm.bpm.core.constant.TaskConstant;
import com.crcm.bpm.core.utils.CommonUtils;
import com.crcm.bpm.domain.dto.response.FlowUserTaskDTO;
import com.crcm.bpm.domain.dto.response.NodeInfoDTO;
import com.crcm.bpm.domain.entity.CounterSignDO;
import com.crcm.bpm.domain.entity.HistoryDO;
import com.crcm.bpm.domain.entity.UserTaskDO;
import com.crcm.bpm.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.common.engine.impl.event.FlowableEntityEventImpl;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.impl.context.Context;
import org.flowable.engine.impl.history.HistoryManager;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CreateUserTaskListener
 * @Description：创建用户任务监听器（节点前置监听器）
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/10/11:32
 **/
@Slf4j
@Component
public class CreateUserTaskListener implements FlowableEventListener {

    @Autowired
    private NodeService nodeService;
    @Autowired
    private CounterSignService counterSignService;
    @Autowired
    private UserTaskService userTaskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    @Lazy
    @Qualifier("processEngine")
    private ProcessEngine processEngine;
    @Autowired
    private ModelService modelService;

    @Override
    public void onEvent(FlowableEvent event) {

        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 当前节点任务实体
        TaskEntity taskEntity = (TaskEntity) ((FlowableEntityEventImpl) event).getEntity();
        log.info("当前任务：{}", taskEntity.getName());
        // 节点业务数据
        Map<String, Object> processMap = taskEntity.getVariables();
        /* 如果是 加签，则直接优先获取 加签人员 */
        String userTaskAssignee = processMap.get(TaskConstant.DEFAULT_ASSIGNEE_USER_EXP) != null ? processMap.get(BpmConstant.DEFAULT_ASSIGNEE_USER_EXP).toString() : (String) processMap.get(BpmConstant.ASSIGNEE_USER_EXP);
        String currentApproveAction = processMap.get(TaskConstant.APPROVE_ACTION_DESC) != null ? processMap.get(TaskConstant.APPROVE_ACTION_DESC).toString() : TaskConstant.APPROVE_ACTION_PASS;
        String ownerUserId = (String) processMap.get(TaskConstant.OWNER_USER_EXP);
        String ownerUserName = (String) processMap.get(TaskConstant.OWNER_USER_NAME_EXP);
        List<String> assigneeList = processMap.get(TaskConstant.DEFAULT_ASSIGNEE_LIST_EXP) == null ? new ArrayList<>() : JSON.parseArray(JSON.toJSONString(processMap.get(TaskConstant.DEFAULT_ASSIGNEE_LIST_EXP)), String.class);
        List<String> assigneeNameList = processMap.get(TaskConstant.DEFAULT_ASSIGNEE_NAME_LIST_EXP) == null ? new ArrayList<>() : JSON.parseArray(JSON.toJSONString(processMap.get(TaskConstant.DEFAULT_ASSIGNEE_NAME_LIST_EXP)), String.class);
        if (StringUtils.isNotBlank(taskEntity.getTaskDefinitionKey())) {
            // 获取节点信息
            NodeInfoDTO nodeInfoDTO = nodeService.getNodeInfoByNodeIdAndDefinitionId(taskEntity.getTaskDefinitionKey(), taskEntity.getProcessDefinitionId());

            // 判断任务类型
            if (BpmConstant.TASK_TYPE_START.equals(nodeInfoDTO.getTaskType())) { // 开始任务
                // 自动指定
                FlowUserTaskDTO flowUserTaskDTO = nodeService.calcNodeUsers(taskEntity.getProcessDefinitionId(),
                        taskEntity.getTaskDefinitionKey(), processMap, true);
                insertUserTask(taskEntity, nodeInfoDTO, processMap,
                        flowUserTaskDTO.getAssigneeIds(), flowUserTaskDTO.getAssigneeNames(), ownerUserId, ownerUserName);
            }
            // 会签任务，有多少人审核就会循环调用改监听器多少次
            else if (BpmConstant.TASK_TYPE_COUNTERSIGN.equals(nodeInfoDTO.getTaskType())) {

                // 当前会签任务负责人
                String assignee = String.valueOf(processMap.get(BpmConstant.ASSIGNEE_USER_EXP));
                if(!assigneeList.contains(assignee)){
                    return;
                }
                String assigneeName = "";
                int sort = 0;
                for (int i = 0; i < assigneeList.size(); i++) {
                    if (assigneeList.get(i).equals(assignee)) {
                        assigneeName = assigneeNameList.get(i);
                        sort = i;
                    }
                }
                // 保存会签任务
                insertCounterSignUserTask(taskEntity, nodeInfoDTO, processMap, assignee,
                        assigneeName, ownerUserId, ownerUserName);
                CounterSignDO counterSignDO = new CounterSignDO()
                        .setApplyId(CommonUtils.evalLong(processMap.get(BpmConstant.APPLY_ID), 0))
                        .setAssignee(assignee)
                        .setAssigneeName(assigneeName)
                        .setTaskId(CommonUtils.evalLong(taskEntity.getId()))
                        .setProcessId(CommonUtils.evalLong(processMap.get(BpmConstant.PROCESS_ID)))
                        .setProcInstId(taskEntity.getProcessInstanceId())
                        .setDefaultSort(sort)
                        .setStatus(TaskConstant.TASK_STATUS_NOT_DONE);
                counterSignService.save(counterSignDO);
            } else {
                // 创建用户任务
                insertUserTask(taskEntity, nodeInfoDTO, processMap, assigneeList, assigneeNameList, ownerUserId, ownerUserName);
            }
            // 设置任务委托人
            taskEntity.setOwner(ownerUserId);
            // 添加候选用户
            taskEntity.addCandidateUsers(assigneeList);
            if (BpmConstant.TASK_TYPE_START.equals(nodeInfoDTO.getTaskType()) && assigneeList.size() > 0 && assigneeList.contains(userTaskAssignee)) {
                taskEntity.setAssignee(userTaskAssignee);
            }
            // 修复历史记录表
            HistoryManager historyManager = Context.getProcessEngineConfiguration().getHistoryManager();
            historyManager.recordTaskInfoChange(taskEntity, taskEntity.getProcessInstanceId(), new Date());
            log.info("#####CreateUserTaskListener#####[{}][{}]###end###", taskEntity.getId(), taskEntity.getName());
        }
    }

    /**
     * 保存用户任务
     *
     * @param taskEntity
     * @param nodeInfoDTO
     * @param processMap
     * @param assigneeList
     * @param assigneeNameList
     * @param ownerUserId
     * @param ownerUserName
     * @return
     */
    private boolean insertUserTask(TaskEntity taskEntity, NodeInfoDTO nodeInfoDTO, Map<String, Object> processMap
            , List<String> assigneeList, List<String> assigneeNameList, String ownerUserId, String ownerUserName) {
        Long parentTaskId = processMap.get(BpmConstant.TASK_ID) == null ? null : Long.valueOf(processMap.get(BpmConstant.TASK_ID).toString());

        // 判断是否第一个发起节点
        int applyNode = BpmConstant.NOT_APPLY_NODE;
        if (BpmConstant.TASK_TYPE_START.equals(nodeInfoDTO.getTaskType())) { // 开始任务
            LambdaQueryWrapper<HistoryDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(HistoryDO::getApplyId, CommonUtils.evalLong(processMap.get(BpmConstant.APPLY_ID), 0L));
            int len = historyService.count(lambdaQueryWrapper);
            if (len == 0) {
                applyNode = BpmConstant.IS_APPLY_NODE;
            }
        }

        UserTaskDO userTask = new UserTaskDO()
                .setModelId(nodeInfoDTO.getModelId())
                .setProcessId(CommonUtils.evalLong(processMap.get(BpmConstant.PROCESS_ID)))
                .setActTaskId(taskEntity.getId())
                .setTaskOwnerUserId(ownerUserId)
                .setTaskOwnerRealName(ownerUserName)
                .setFormKey(nodeInfoDTO.getFormKey())
                .setTenantId(taskEntity.getTenantId())
                .setProcInstId(taskEntity.getProcessInstanceId())
                .setTaskName(taskEntity.getName())
                .setTaskNodeCode(taskEntity.getTaskDefinitionKey())
                .setTaskPriority(nodeInfoDTO.getPriority())
                .setTaskStatus(TaskConstant.TASK_UN_CLAIM)
                .setApplyId(CommonUtils.evalLong(processMap.get(BpmConstant.APPLY_ID), 0L))
                .setProcessId(Convert.toLong(processMap.get(BpmConstant.PROCESS_ID), 0L))
                .setTaskType(nodeInfoDTO.getTaskType())
                .setActionList(nodeInfoDTO.getActionList())
                .setFormEditField(nodeInfoDTO.getFormEditField())
                .setCandidateUserList(String.join(",", assigneeList))
                .setCandidateUserNameList(String.join(",", assigneeNameList))
                .setSourceAction(processMap.containsKey(BpmConstant.APPROVE_ACTION) ? processMap.get(BpmConstant.APPROVE_ACTION).toString() : null)
                .setApplyNode(applyNode);
        Boolean flag = userTaskService.save(userTask);
        return flag;
    }

    /**
     * 保存用户任务
     *
     * @param taskEntity
     * @param nodeInfoDTO
     * @param processMap
     * @param assignee
     * @param assigneeName
     * @param ownerUserId
     * @param ownerUserName
     * @return
     */
    private boolean insertCounterSignUserTask(TaskEntity taskEntity, NodeInfoDTO nodeInfoDTO, Map<String, Object> processMap
            , String assignee, String assigneeName, String ownerUserId, String ownerUserName) {
        UserTaskDO userTask = new UserTaskDO()
                .setModelId(nodeInfoDTO.getModelId())
                .setProcessId(CommonUtils.evalLong(processMap.get(BpmConstant.PROCESS_ID)))
                .setActTaskId(taskEntity.getId())
                .setTaskOwnerUserId(ownerUserId)
                .setTaskOwnerRealName(ownerUserName)
                .setFormKey(nodeInfoDTO.getFormKey())
                .setTenantId(taskEntity.getTenantId())
                .setProcInstId(taskEntity.getProcessInstanceId())
                .setTaskName(taskEntity.getName())
                .setTaskNodeCode(taskEntity.getTaskDefinitionKey())
                .setTaskPriority(nodeInfoDTO.getPriority())
                .setTaskStatus(TaskConstant.TASK_UN_CLAIM)
//                .setClaimTime(LocalDateTime.now())
                .setApplyId(Convert.toLong(processMap.get(BpmConstant.APPLY_ID), 0L))
                .setProcessId(Convert.toLong(processMap.get(BpmConstant.PROCESS_ID), 0L))
                .setTaskType(nodeInfoDTO.getTaskType())
                .setActionList(nodeInfoDTO.getActionList())
                .setFormEditField(nodeInfoDTO.getFormEditField())
//                .setTaskAssigneeUserId(assignee)
//                .setTaskAssigneeRealName(assigneeName)
                .setCandidateUserList(assignee)
                .setCandidateUserNameList(assigneeName)
                .setSourceAction(processMap.containsKey(BpmConstant.APPROVE_ACTION) ? processMap.get(BpmConstant.APPROVE_ACTION).toString() : null);
        Boolean flag = userTaskService.save(userTask);
        return flag;
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }

    @Override
    public boolean isFireOnTransactionLifecycleEvent() {
        return false;
    }

    @Override
    public String getOnTransaction() {
        return null;
    }
}
