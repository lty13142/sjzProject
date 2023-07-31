package com.crcm.bpm.core.listener;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crcm.bpm.core.components.MultiInstanceCompleteTask;
import com.crcm.bpm.core.constant.BpmConstant;
import com.crcm.bpm.core.constant.TaskConstant;
import com.crcm.bpm.domain.dto.NodeAssigneesDTO;
import com.crcm.bpm.domain.dto.response.FlowUserTaskDTO;
import com.crcm.bpm.domain.entity.HistoryDO;
import com.crcm.bpm.service.HistoryService;
import com.crcm.bpm.service.NodeService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.event.impl.FlowableMultiInstanceActivityEventImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName MultiInstanceActivityListener
 * @Description：多实例任务初始化监听器
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/11/23
 **/
@Slf4j
@Component
public class MultiInstanceActivityListener implements FlowableEventListener {


    @Autowired
    @Lazy
    @Qualifier("processEngine")
    private ProcessEngine processEngine;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private HistoryService historyService;

    @Override
    public void onEvent(FlowableEvent event) {
        TaskService taskService = processEngine.getTaskService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        FlowableMultiInstanceActivityEventImpl multiInstanceActivityEvent = (FlowableMultiInstanceActivityEventImpl) event;
        String executionId = multiInstanceActivityEvent.getExecutionId();
        String activityId = multiInstanceActivityEvent.getActivityId();
        String activityName = multiInstanceActivityEvent.getActivityName();
        String processDefinitionId = multiInstanceActivityEvent.getProcessDefinitionId();
        // 当前节点任务实体
        log.info("MultiInstanceActivityListener ############MultiInstanceActivity[{}][{}]#######start#######:", activityId, activityName);
        // 节点业务数据
        Map<String, Object> processMap = runtimeService.getVariables(executionId);
        log.info("==============多实例任务初始化 ； {} =============", activityName);
        // 受理人集合
        List<String> assigneeList = new ArrayList<>();
        // 受理人集合
        List<String> assigneeNameList = new ArrayList<>();

        // 任务用户获取方式  手动指定
//        if (Convert.toInt(processMap.get(BpmConstant.ASSIGN_TASK_USER_WAY),0) == BpmConstant.ASSIGN_TASK_USER_MANUAL) {
//            // 指定节点审核人
//            assigneeList.addAll(JSON.parseArray(JSON.toJSONString(processMap.get(TaskConstant.DEFAULT_ASSIGNEE_LIST_EXP)), String.class));
//            assigneeNameList.addAll(JSON.parseArray(JSON.toJSONString(processMap.get(TaskConstant.DEFAULT_ASSIGNEE_NAME_LIST_EXP)), String.class));
//        }
        if (processMap.get(TaskConstant.ASSIGNEE_DATA) != null) {
            // 获取前端指定的审核人数据
            String assigneeData = JSON.toJSONString(processMap.get(TaskConstant.ASSIGNEE_DATA));
            List<NodeAssigneesDTO> assigneesDTOS = JSON.parseArray(assigneeData, NodeAssigneesDTO.class);
            for (NodeAssigneesDTO assigneesDTO : assigneesDTOS) {
                assigneeList.addAll(assigneesDTO.getAssigneeIds());
                assigneeNameList.addAll(assigneesDTO.getAssigneeNames());
            }
        } else {
            processMap.put(BpmConstant.PROCESS_ID, multiInstanceActivityEvent.getProcessInstanceId());
            FlowUserTaskDTO flowUserTaskDTO = nodeService.calcNodeUsers(processDefinitionId, activityId, processMap, true);
            if (Objects.nonNull(flowUserTaskDTO)) {
                // 则设置审核人
                assigneeList.addAll(flowUserTaskDTO.getAssigneeIds());
                assigneeNameList.addAll(flowUserTaskDTO.getAssigneeNames());
            }
        }

        // 添加多实例流转判断任务 multiInstance
        runtimeService.setVariable(executionId, "multiInstance", new MultiInstanceCompleteTask());

        // 自动流转到会签任务时，若没有获取到处理人，从历史中获取
        // 适用场景，会签审核后，有自动流转到会签节点
        if (assigneeList.size() == 0) {
            LambdaQueryWrapper<HistoryDO> queryWrapper = new LambdaQueryWrapper();
            queryWrapper.eq(HistoryDO::getApplyId, processMap.get(BpmConstant.APPLY_ID).toString());
            queryWrapper.orderByDesc(HistoryDO::getUpdateTime);
            List<HistoryDO> historyDOList = historyService.list(queryWrapper);

            for(HistoryDO his : historyDOList) {
                if (his.getTaskNodeCode().equals(activityId)) {
                    if (assigneeList.indexOf(his.getTaskAssigneeUserId()) == -1) {
                        // 则设置审核人
                        assigneeList.add(his.getTaskAssigneeUserId());
                        assigneeNameList.add(his.getTaskAssigneeRealName());
                    }
                }
            }
        }


        // 设置下一节点审核人
        runtimeService.setVariable(executionId, TaskConstant.DEFAULT_ASSIGNEE_LIST_EXP, assigneeList);
        runtimeService.setVariable(executionId, TaskConstant.DEFAULT_ASSIGNEE_NAME_LIST_EXP, assigneeNameList);

        // 重置会签节点审核数量
        runtimeService.setVariable(executionId, activityId + TaskConstant.COUNTERSIGN_PASS_COUNT, 0);
        runtimeService.setVariable(executionId, activityId + TaskConstant.COUNTERSIGN_NO_PASS_COUNT, 0);

        log.info("############多实例任务初始化完成，节点审核人为:{}", assigneeNameList);
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
