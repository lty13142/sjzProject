package com.crcm.bpm.core.listener;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crcm.bpm.core.common.BpmError;
import com.crcm.bpm.core.constant.BpmConstant;
import com.crcm.bpm.core.constant.CopySendConstant;
import com.crcm.bpm.core.constant.TaskConstant;
import com.crcm.bpm.core.exception.BpmException;
import com.crcm.bpm.core.utils.CommonUtils;
import com.crcm.bpm.domain.dto.ProcessCirculationDto;
import com.crcm.bpm.domain.dto.request.CountersignCompletedDTO;
import com.crcm.bpm.domain.dto.request.HistoryFormDataSaveDTO;
import com.crcm.bpm.domain.dto.response.FlowUserTaskDTO;
import com.crcm.bpm.domain.dto.response.NodeInfoDTO;
import com.crcm.bpm.domain.dto.response.PostUserDTO;
import com.crcm.bpm.domain.dto.response.UserTaskDTO;
import com.crcm.bpm.domain.entity.HistoryDO;
import com.crcm.bpm.domain.entity.UserTaskDO;
import com.crcm.bpm.fegin.RemotePostService;
import com.crcm.bpm.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.*;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.delegate.event.impl.FlowableEntityEventImpl;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.task.api.Task;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @ClassName TaskCompletedListener
 * @Description：任务完成监听器
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/11/9
 **/
@Slf4j
@Component
public class TaskCompletedListener implements FlowableEventListener {

    @Lazy
    @Qualifier("processEngine")
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private CounterSignService counterSignService;
    @Autowired
    private UserTaskService userTaskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private HistoryFormDataService historyFormDataService;
    @Autowired
    private SendDuplicateService sendDuplicateService;
    @Autowired
    private RemotePostService postService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void onEvent(FlowableEvent event) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 当前节点任务实体
        TaskEntity taskEntity = (TaskEntity) ((FlowableEntityEventImpl) event).getEntity();
        // 节点业务数据
        Map<String, Object> processMap = taskEntity.getVariables();
        log.info("==============签订任务 ； {} =============", taskEntity.getName());

        // 获取操作类型
        String actionType = Convert.toStr(processMap.get(TaskConstant.APPROVE_ACTION_DESC));
        NodeInfoDTO nodeInfo = nodeService.getNodeInfoByNodeIdAndDefinitionId(taskEntity.getTaskDefinitionKey(), taskEntity.getProcessDefinitionId());
        // 判断是否是会签操作
        if (TaskConstant.APPROVE_ACTION_COUNTER_SIGN.equals(actionType)) {
            // 审批结果
            String approveResult = Convert.toStr(runtimeService.getVariable(taskEntity.getExecutionId(), TaskConstant.APPROVE_RESULT_DESC));
            // 审批意见
            String approveOpinion = Convert.toStr(runtimeService.getVariable(taskEntity.getExecutionId(), TaskConstant.APPROVE_OPINION_DESC));
            String assign = Convert.toStr(runtimeService.getVariable(taskEntity.getExecutionId(), TaskConstant.ASSIGNEE_USER_EXP));
            // 修改会签表状态
            CountersignCompletedDTO countersignCompletedDTO = new CountersignCompletedDTO()
                    .setApproveOpinion(approveOpinion)
                    .setApproveResult(approveResult)
                    .setApproveTime(LocalDateTime.now())
                    .setTaskId(CommonUtils.evalLong(taskEntity.getId()))
                    .setProcInstId(taskEntity.getProcessInstanceId())
                    .setAssignee(assign)
                    .setStatus(TaskConstant.TASK_STATUS_HAS_DONE);
            counterSignService.doCounterSign(countersignCompletedDTO);

            // 任务完成比例
            Integer proportion = StringUtils.isBlank(nodeInfo.getProportion()) ? 0 : Integer.parseInt(nodeInfo.getProportion());
            // 是否全部执行

            String wholeDeal = nodeInfo.getWholeDeal();
            // 成功跳转
            String passSkip = nodeInfo.getPassSkip();
            // 未通过跳转
            String noPassSkip = nodeInfo.getNoPassSkip();

            Map<String, Object> variables = processEngine.getTaskService().getVariables(taskEntity.getId());
            variables.put(TaskConstant.COUNTERSIGN_WHOLEDEAL, wholeDeal);
            variables.put(TaskConstant.COUNTERSIGN_PASSSKIP, passSkip);
            variables.put(TaskConstant.COUNTERSIGN_NOPASSSKIP, noPassSkip);
            variables.put(TaskConstant.COUNTERSIGN_APPROVERESULT, approveResult);
            variables.put(TaskConstant.COUNTERSIGN_PROPORTION, proportion);
            processEngine.getTaskService().setVariables(taskEntity.getId(), variables);
        }

        // 自动抄送
        copySend(nodeInfo, taskEntity.getId(), taskEntity.getProcessInstanceId());

        // 保存历史记录
        List<UserTaskDTO> userTasks = userTaskService.getUserTaskByProcInstId(taskEntity.getProcessInstanceId());
        List<UserTaskDTO> currentTasks = userTasks.stream().filter(s -> taskEntity.getTaskDefinitionKey().equals(s.getTaskNodeCode())
                && taskEntity.getId().equals(s.getActTaskId())).collect(Collectors.toList());
        currentTasks.forEach(userTaskDTO -> {
            HistoryDO historyDO = new HistoryDO();
            BeanUtil.copyProperties(userTaskDTO, historyDO);
            // 保存历史记录
            historyService.insertHistory(historyDO);
            HistoryFormDataSaveDTO historyFormDataSaveDTO = new HistoryFormDataSaveDTO()
                    .setApplyId(userTaskDTO.getApplyId())
                    .setProcessId(userTaskDTO.getProcessId())
                    .setProcInstId(userTaskDTO.getProcInstId())
                    .setTaskNodeId(userTaskDTO.getTaskNodeCode())
                    .setTenantId(userTaskDTO.getTenantId())
                    .setProcessMap(processMap);
            // 保存或修改历史流程数据
            historyFormDataService.saveOrUpdateFormData(historyFormDataSaveDTO);
            // 删除已完成的任务
            userTaskService.deleteById(userTaskDTO.getId());
        });
    }

    /**
     * 获取任务节点
     *
     * @param taskId 任务id
     */
    private boolean hasNextFlowNode(String taskId) {
        Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
        ExecutionEntity ee = (ExecutionEntity) processEngine.getRuntimeService().createExecutionQuery()
                .executionId(task.getExecutionId()).singleResult();
        // 当前审批节点
        String currentActivityId = ee.getActivityId();
        BpmnModel bpmnModel = processEngine.getRepositoryService().getBpmnModel(task.getProcessDefinitionId());
        FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(currentActivityId);
        // 输出连线
        List<SequenceFlow> outFlows = flowNode.getOutgoingFlows();
        for (SequenceFlow sequenceFlow : outFlows) {
            // 下一个审批节点
            FlowElement targetFlow = sequenceFlow.getTargetFlowElement();
            if (targetFlow instanceof UserTask) {
                System.out.println("下一节点: id=" + targetFlow.getId() + ",name=" + targetFlow.getName());
                return true;
            }
            // 如果下个审批节点为结束节点
            if (targetFlow instanceof EndEvent) {
                System.out.println("下一节点为结束节点：id=" + targetFlow.getId() + ",name=" + targetFlow.getName());
                return false;
            }
        }
        return false;
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

    /**
     * 根据配置抄送
     * @param nodeInfo      节点信息
     * @param actTaskId     任务实例id
     * @param piid          流程实例id
     */
    private void copySend(NodeInfoDTO nodeInfo, String actTaskId, String piid) {
        String copySend = nodeInfo.getCopySend();
        if (StringUtils.isEmpty(copySend)) {
            return;
        }
        String[] receivingObject = copySend.split(",");

        List<String> userIds = new ArrayList<>();
        List<String> userNames = new ArrayList<>();
        List<String> nodeCodes = new ArrayList<>();
        List<String> postIds = new ArrayList<>();
        for (String receiving : receivingObject) {
            switch (receiving.substring(0, receiving.indexOf(":"))) {
                case CopySendConstant.USER: // 指定用户
                    userIds.add(receiving.substring(receiving.indexOf(":") + 1, receiving.indexOf("|")));
                    userNames.add(receiving.substring(receiving.indexOf("|") + 1));
                    break;
                case CopySendConstant.NODE: // 指定节点
                    nodeCodes.add(receiving.substring(receiving.indexOf(":") + 1, receiving.indexOf("|")));
                    break;
                case CopySendConstant.POST: // 指定岗位
                    postIds.add(receiving.substring(receiving.indexOf(":") + 1, receiving.indexOf("|")));
                    break;
            }
        }
        if (nodeCodes.size() > 0) {
            // 历史任务记录
            LambdaQueryWrapper<HistoryDO> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(HistoryDO::getProcInstId, piid);
            wrapper.in(HistoryDO::getTaskNodeCode, nodeCodes);
            List<HistoryDO> historyDOList = historyService.list(wrapper);
            for (HistoryDO historyDO : historyDOList) {
                if (userIds.indexOf(historyDO.getTaskAssigneeUserId()) < 0) {
                    userIds.add(historyDO.getTaskAssigneeUserId());
                    userNames.add(historyDO.getTaskAssigneeRealName());
                }
            }

            // 待办任务记录
            LambdaQueryWrapper<UserTaskDO> taskWrapper = new LambdaQueryWrapper<>();
            taskWrapper.eq(UserTaskDO::getProcInstId, piid);
            taskWrapper.in(UserTaskDO::getTaskNodeCode, nodeCodes);
            List<UserTaskDO> userTaskList = userTaskService.list(taskWrapper);
            for (UserTaskDO userTaskDO : userTaskList) {
                if (!StringUtils.isEmpty(userTaskDO.getTaskAssigneeUserId()) && userIds.indexOf(userTaskDO.getTaskAssigneeUserId()) < 0) {
                    userIds.add(userTaskDO.getTaskAssigneeUserId());
                    userNames.add(userTaskDO.getTaskAssigneeRealName());
                }
            }
        }

        if (postIds.size() > 0) {
            List<PostUserDTO> postUserList = postService.getPostUsersByPostIds(String.join(",", postIds).split(","));
            if (postUserList != null && postUserList.size() > 0) {
                for (PostUserDTO postUser : postUserList) {
                    if (userIds.indexOf(postUser.getUserId()) < 0) {
                        userIds.add(postUser.getUserId());
                        userNames.add(postUser.getUserName());
                    }
                }
            }
        }

        ProcessCirculationDto processCirculationDto = new ProcessCirculationDto();
        processCirculationDto.setActTaskId(Long.parseLong(actTaskId));
        processCirculationDto.setAssigneeList(userIds);
        processCirculationDto.setAssigneeNameList(userNames);
        sendDuplicateService.saveSendDuplicate(processCirculationDto);
    }
}
