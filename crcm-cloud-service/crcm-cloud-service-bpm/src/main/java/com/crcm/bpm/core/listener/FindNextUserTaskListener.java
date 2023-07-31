package com.crcm.bpm.core.listener;

import cn.hutool.core.convert.Convert;
import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crcm.bpm.core.constant.BpmConstant;
import com.crcm.bpm.core.constant.TaskConstant;
import com.crcm.bpm.core.handler.TaskHandler;
import com.crcm.bpm.domain.dto.NodeAssigneesDTO;
import com.crcm.bpm.domain.entity.HistoryDO;
import com.crcm.bpm.service.HistoryService;
import com.crcm.bpm.utils.PrecessThreadLocalUtil;
import com.crcm.core.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.common.engine.impl.event.FlowableEntityEventImpl;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.TaskService;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName FindNextUserTaskListener
 * @Description：寻找下一个用户节点监听器
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：Administrator
 * @Date：2020/10/19
 **/
@Slf4j
@Component
public class FindNextUserTaskListener implements FlowableEventListener {

    @Lazy
    @Qualifier("processEngine")
    @Autowired
    private ProcessEngine processEngine;
    @Lazy
    @Autowired
    private TaskHandler taskHandler;
    @Autowired
    private HistoryService historyService;

    @Override
    public void onEvent(FlowableEvent event) {

        TaskService taskService = processEngine.getTaskService();
        // 当前节点任务实体
        TaskEntity taskEntity = (TaskEntity) ((FlowableEntityEventImpl) event).getEntity();
        log.info("FindNextUserTaskUsersListener ############taskEntity[{}][{}]#######start#######:", taskEntity.getId(), taskEntity.getEventName());
        // 节点业务数据
        Map<String, Object> processMap = taskEntity.getVariables();

        log.info("==============当前任务 ； {} =============", taskEntity.getName());
        // 当前节点任务实体
        long startTime = System.currentTimeMillis();
        log.info("initNextUserTask ############taskEntity[{}][{}]#######start#######:", taskEntity.getId(), taskEntity.getName());
        // 受理人集合
        List<String> assigneeList = new ArrayList<>();
        // 受理人集合
        List<String> assigneeNameList = new ArrayList<>();
        // 是否自动获取审核人 默认为true
        boolean isAuto = Convert.toInt(processMap.get(BpmConstant.ASSIGN_TASK_USER_WAY), 0) == BpmConstant.ASSIGN_TASK_USER_AUTO;
        // 是否退回
        boolean isReturn = TaskConstant.APPROVE_ACTION_RANDOM_RETURN.equals(processMap.get(TaskConstant.APPROVE_ACTION_DESC));
        // 是否撤回
        boolean isRecall = TaskConstant.APPROVE_ACTION_RECALL.equals(processMap.get(TaskConstant.APPROVE_ACTION_DESC));
        if (processMap.get(TaskConstant.ASSIGNEE_DATA) != null) {
            // 获取前端指定的审核人数据
            String assigneeData = JSON.toJSONString(processMap.get(TaskConstant.ASSIGNEE_DATA));
            List<NodeAssigneesDTO> assigneesDTOS = JSON.parseArray(assigneeData, NodeAssigneesDTO.class);
            // 如果当前节点有指定的审核人
            for (NodeAssigneesDTO assigneesDTO : assigneesDTOS) {
                if (Objects.equals(taskEntity.getTaskDefinitionKey(), assigneesDTO.getNodeId())) {
                    isAuto = false;
                    assigneeList.addAll(assigneesDTO.getAssigneeIds());
                    assigneeNameList.addAll(assigneesDTO.getAssigneeNames());
                }
            }
        }
        // 退回
        if (isReturn || isRecall) {
            HistoryDO historyDO;
            if (isReturn) {
                historyDO = historyService.getReturnMsg(processMap);
            } else {
                historyDO = historyService.getById(processMap.get(TaskConstant.TASK_ID).toString());
                historyDO.setTaskStatus(4);
                historyService.updateById(historyDO);
            }
            assigneeList.add(historyDO.getTaskAssigneeUserId());
            assigneeNameList.add(historyDO.getTaskAssigneeRealName());
            taskService.setVariable(taskEntity.getId(), TaskConstant.DEFAULT_ASSIGNEE_LIST_EXP, assigneeList);
            taskService.setVariable(taskEntity.getId(), TaskConstant.DEFAULT_ASSIGNEE_NAME_LIST_EXP, assigneeNameList);
        }
        // 任务用户获取方式  手动指定
        else if (!isAuto) {
            // 指定节点审核人
//            assigneeList.addAll(JSON.parseArray(JSON.toJSONString(processMap.get(TaskConstant.DEFAULT_ASSIGNEE_LIST_EXP)), String.class));
//            assigneeNameList.addAll(JSON.parseArray(JSON.toJSONString(processMap.get(TaskConstant.DEFAULT_ASSIGNEE_NAME_LIST_EXP)), String.class));
            // 设置下一节点审核人
            if(assigneeList == null || assigneeList.size() == 0) {
                CustomException businessException = new CustomException(HttpStatus.HTTP_PRECON_FAILED, "未获取到处理人，请联系管理员");
                PrecessThreadLocalUtil.THREAD_LOCAL.set(businessException);
            }
            taskService.setVariable(taskEntity.getId(), TaskConstant.DEFAULT_ASSIGNEE_LIST_EXP, assigneeList);
            taskService.setVariable(taskEntity.getId(), TaskConstant.DEFAULT_ASSIGNEE_NAME_LIST_EXP, assigneeNameList);
//            taskService.removeVariable(taskEntity.getId(),TaskConstant.ASSIGNEE_DATA);
        }
        // 如果是会签任务，由于MultiInstanceActivityListener 与计算过节点审核人，故跳过
//        else if (BpmConstant.TASK_TYPE_COUNTERSIGN.equals(Convert.toStr(processMap.get(TaskConstant.TASK_ACTION_TYPE)))) {
//
//        }
        else {
            processMap.put(BpmConstant.PROCESS_ID, taskEntity.getProcessInstanceId());
            taskHandler.setTargetTaskAssignee(taskEntity.getId(), taskEntity.getTaskDefinitionKey(), null, null, processMap);
        }
        log.info("############taskEntity assigneeList:{}", assigneeList);
        log.info("initNextUserTask ############taskEntity[{}][{}]#######end#######:", taskEntity.getId(), taskEntity.getName());
        log.info("############任务初始化完成，节点审核人为:{}", assigneeNameList);
        log.info("----------initNextUserTask-耗时---:" + (System.currentTimeMillis() - startTime));
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
