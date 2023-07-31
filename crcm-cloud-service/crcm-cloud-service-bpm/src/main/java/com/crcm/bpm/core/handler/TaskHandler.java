package com.crcm.bpm.core.handler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crcm.bpm.core.common.BpmError;
import com.crcm.bpm.core.config.ServiceFactory;
import com.crcm.bpm.core.constant.BpmConstant;
import com.crcm.bpm.core.constant.TaskConstant;
import com.crcm.bpm.core.exception.BpmException;
import com.crcm.bpm.core.ifs.ActTask;
import com.crcm.bpm.core.utils.BestBpmAsset;
import com.crcm.bpm.domain.dto.response.FlowUserTaskDTO;
import com.crcm.bpm.domain.entity.HistoryDO;
import com.crcm.bpm.domain.vo.TaskVO;
import com.crcm.bpm.service.HistoryService;
import com.crcm.bpm.service.NodeService;
import com.crcm.security.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.task.Comment;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 流程任务
 *
 * @author liuxz
 * @date 2019/08/30
 */
@Slf4j
@Component
public class TaskHandler extends ServiceFactory implements ActTask {
    @Autowired
    private TaskQueryHandler taskQueryHandler;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private HistoryService historyService;

    @Override
    public void claim(String taskId, String userId) {
        taskService.claim(taskId, userId);
    }

    @Override
    public void unclaim(String taskId) {
        taskService.unclaim(taskId);
    }

    @Override
    public void complete(String taskId) {

        this.complete(taskId, null);
        log.info("-----------任务ID：{},已完成-----------", taskId);
    }

    @Override
    public void complete(String taskId, Map<String, Object> variables) {
        taskService.complete(taskId, variables);
    }

    @Override
    public Map<String, Object> complete(String taskId, Map<String, Object> variables, boolean localScope) {
        TaskVO finishTask = taskQueryHandler.queryTaskVOById(taskId);
        taskService.complete(taskId, variables, localScope);
        Task nextTask = taskQueryHandler.processInstanceId(finishTask.getProcessInstanceId());
        TaskVO activeTask = null;
        if (nextTask != null) {
            // 设置下一审核人
            taskService.setAssignee(nextTask.getId(), SecurityUtil.getCurrentUserId());
            activeTask = BeanUtil.copyProperties(nextTask, TaskVO.class);
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("finish", finishTask);
        map.put("active", activeTask);
        return map;
    }

    @Override
    public void delegate(String taskId, String userId) {
        taskService.delegateTask(taskId, userId);
    }


    @Override
    public void resolveTask(String taskId) {

        taskService.resolveTask(taskId);
    }


    @Override
    public void setAssignee(String taskId, String userId) {
        taskService.setAssignee(taskId, userId);
    }

    @Override
    public void setOwner(String taskId, String userId) {
        taskService.setOwner(taskId, userId);
    }

    @Override
    public void delete(String taskId) {
        taskService.deleteTask(taskId);
    }

    @Override
    public void deleteWithReason(String taskId, String reason) {
        taskService.deleteTask(taskId, reason);
    }

    @Override
    public void addCandidateUser(String taskId, String userId) {

        taskService.addCandidateUser(taskId, userId);
    }

    @Override
    public Comment addComment(String taskId, String processInstanceId, String message) {

        return taskService.addComment(taskId, processInstanceId, message);
    }

    @Override
    public List<Comment> getTaskComments(String taskId) {

        return taskService.getTaskComments(taskId);
    }

    @Override
    public void withdraw(String processInstanceId, String currentActivityId, String newActivityId) {
        runtimeService.createChangeActivityStateBuilder()
                .processInstanceId(processInstanceId)
                .moveActivityIdTo(currentActivityId, newActivityId)
                .changeState();
    }

    @Override
    public void setVariableLocal(String taskId, String variableName, Object value) {
        taskService.setVariableLocal(taskId, variableName, value);
    }

    @Override
    public void setVariablesLocal(String taskId, Map<String, ? extends Object> variables) {
        taskService.setVariablesLocal(taskId, variables);
    }

    /**
     * 设置下一节点审核人
     *
     * @param taskId
     * @param assigneeList
     * @param assigneeNameList
     * @param processMap
     */
    public void setTaskAssignee(String taskId, List<String> assigneeList, List<String> assigneeNameList, Map<String, Object> processMap) {
        BestBpmAsset.isEmpty(taskId);
        // 直接指定下一节点审核人
        if (CollectionUtil.isNotEmpty(assigneeList)) {
            taskService.setVariable(taskId, TaskConstant.DEFAULT_ASSIGNEE_LIST_EXP, assigneeList);
            taskService.setVariable(taskId, TaskConstant.DEFAULT_ASSIGNEE_NAME_LIST_EXP, assigneeNameList);
        } else {
            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            // 自动指定下一节点审核人
            List<FlowUserTaskDTO> nextNodeList = nodeService.getNextNodeList(task.getProcessDefinitionId(), task.getTaskDefinitionKey(), processMap, false, true);
            for (FlowUserTaskDTO flowUserTaskDTO : nextNodeList) {
                this.setAssignee(taskId, flowUserTaskDTO, processMap);
            }
        }
    }

    /**
     * 设置目标节点审核人，用于退回
     *
     * @param taskId
     * @param targetNodeKey
     * @param assigneeList
     * @param assigneeNameList
     * @param processMap
     */
    public void setTargetTaskAssignee(String taskId, String targetNodeKey, List<String> assigneeList, List<String> assigneeNameList, Map<String, Object> processMap) {
        BestBpmAsset.isEmpty(taskId);
        BestBpmAsset.isEmpty(targetNodeKey);
        // 直接指定下一节点审核人
        if (CollectionUtil.isNotEmpty(assigneeList)) {
            taskService.setVariable(taskId, TaskConstant.DEFAULT_ASSIGNEE_LIST_EXP, assigneeList);
            taskService.setVariable(taskId, TaskConstant.DEFAULT_ASSIGNEE_NAME_LIST_EXP, assigneeNameList);
        } else {
            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            FlowUserTaskDTO flowUserTaskDTO = nodeService.calcNodeUsers(task.getProcessDefinitionId(), targetNodeKey, processMap, true);
            if (Objects.isNull(flowUserTaskDTO)) {
                throw new BpmException("目标节点未找到！", BpmError.DATA_NOT_FOUND_ERROR);
            }
            if (flowUserTaskDTO.getAssigneeIds().size() == 0 && flowUserTaskDTO.getFindUserType() == 3) {
                // 节点处理人由前端指派，但是自动流转到这里时，查询历史审核人
                setHistoryAssignee(taskId, processMap.get(BpmConstant.APPLY_ID).toString(), flowUserTaskDTO.getNodeId());
            } else {
                this.setAssignee(taskId, flowUserTaskDTO, processMap);
            }
        }
    }

    private void setAssignee(String taskId, FlowUserTaskDTO flowUserTaskDTO, Map<String, Object> processMap) {
        List assigneeList = new ArrayList<>();
        List assigneeNameList = new ArrayList<>();
        // 则设置审核人
        assigneeList.addAll(flowUserTaskDTO.getAssigneeIds());
        assigneeNameList.addAll(flowUserTaskDTO.getAssigneeNames());
        // 设置下一节点审核人
        taskService.setVariable(taskId, TaskConstant.DEFAULT_ASSIGNEE_LIST_EXP, assigneeList);
        taskService.setVariable(taskId, TaskConstant.DEFAULT_ASSIGNEE_NAME_LIST_EXP, assigneeNameList);
    }

    // 设置节点历史审核人
    private void setHistoryAssignee(String taskId, String applyId, String nodeId) {
        LambdaQueryWrapper<HistoryDO> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(HistoryDO::getApplyId, applyId);
        queryWrapper.orderByDesc(HistoryDO::getUpdateTime);
        List<HistoryDO> historyDOList = historyService.list(queryWrapper);

        // 会签串行执行的时候，不重新配置处理人
        if (historyDOList.get(0).getTaskNodeCode().equals(nodeId)) {
            return;
        }

        List assigneeList = new ArrayList<>();
        List assigneeNameList = new ArrayList<>();
        for (HistoryDO his : historyDOList) {
            if (his.getTaskNodeCode().equals(nodeId)) {
                if (assigneeList.indexOf(his.getTaskAssigneeUserId()) == -1) {
                    // 则设置审核人
                    assigneeList.add(his.getTaskAssigneeUserId());
                    assigneeNameList.add(his.getTaskAssigneeRealName());
                }
            }
        }
        // 设置下一节点审核人
        taskService.setVariable(taskId, TaskConstant.DEFAULT_ASSIGNEE_LIST_EXP, assigneeList);
        taskService.setVariable(taskId, TaskConstant.DEFAULT_ASSIGNEE_NAME_LIST_EXP, assigneeNameList);
    }
}
