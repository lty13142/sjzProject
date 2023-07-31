package com.crcm.bpm.core.listener;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crcm.bpm.core.constant.TaskConstant;
import com.crcm.bpm.domain.dto.response.UserTaskDTO;
import com.crcm.bpm.domain.entity.HistoryDO;
import com.crcm.bpm.domain.entity.UserTaskDO;
import com.crcm.bpm.service.HistoryService;
import com.crcm.bpm.service.UserTaskService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.delegate.event.impl.FlowableMultiInstanceActivityCompletedEventImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MultiInstanceActivityCompletedWithConditionListener
 * @Description：带条件的多实例流程完成监听器
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/11/13
 **/
@Slf4j
@Component
public class MultiInstanceActivityCompletedWithConditionListener implements FlowableEventListener {


    @Autowired
    @Lazy
    @Qualifier("processEngine")
    private ProcessEngine processEngine;
    @Autowired
    private UserTaskService userTaskService;
    @Autowired
    private HistoryService historyService;

    @Override
    public void onEvent(FlowableEvent event) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 当前节点任务实体
//        TaskEntity taskEntity = (TaskEntity) ((FlowableMultiInstanceActivityCompletedEventImpl) event).getExecution();
        FlowableMultiInstanceActivityCompletedEventImpl fmiacei = (FlowableMultiInstanceActivityCompletedEventImpl) event;
        // 节点业务数据
        Map<String, Object> processMap = fmiacei.getExecution().getVariables();
        String outcome = Convert.toStr(processMap.get(TaskConstant.COUNTERSIGN_OUTCOME_DESC));
        if (TaskConstant.COUNTERSIGN_OUTCOME_STOP.equals(outcome)) {
            // 删除流程实例
            processMap.put(TaskConstant.APPROVE_ACTION_DESC, TaskConstant.APPROVE_ACTION_REJECT);
            runtimeService.deleteProcessInstance(fmiacei.getExecution().getProcessInstanceId(), TaskConstant.APPROVE_ACTION_FAIL);
            // 取消其余的多实例任务
            List<UserTaskDTO> userTasks = userTaskService.getUserTaskByProcInstId(fmiacei.getExecution().getProcessInstanceId());
            ArrayList<HistoryDO> historyDOS = new ArrayList<>();
            ArrayList<Long> taskIds = new ArrayList<>();
            userTasks.stream().filter(temp ->
                    temp.getTaskStatus() < TaskConstant.TASK_COMPLETED).forEach(
                    userTask -> {
                        String[] candidateUsers = userTask.getCandidateUserList().split(",");
                        String[] candidateUserNames = userTask.getCandidateUserNameList().split(",");
                        taskIds.add(userTask.getId());
                        HistoryDO historyDO = BeanUtil.copyProperties(userTask, HistoryDO.class);
                        historyDO.setApproveTime(LocalDateTime.now())
                                .setDueTime(LocalDateTime.now())
                                .setApproveOpinion("系统自动完成")
                                .setApproveResult(TaskConstant.APPROVE_ACTION_PASS)
                                .setTaskAssigneeUserId(ArrayUtil.isEmpty(candidateUsers) ? "" : candidateUsers[0])
                                .setTaskAssigneeRealName(ArrayUtil.isEmpty(candidateUserNames) ? "" : candidateUserNames[0])
                                .setTaskStatus(TaskConstant.TASK_DISABLE);
                        historyDOS.add(historyDO);
                    }
            );
            // 删除用户任务
            userTaskService.removeByIds(taskIds);
            // 添加流程历史记录
            historyService.saveBatch(historyDOS);

        } else if (TaskConstant.COUNTERSIGN_OUTCOME_PASS.equals(outcome) || TaskConstant.COUNTERSIGN_OUTCOME_REJECT.equals(outcome)) {
            // 删除多余的流程实例
            LambdaQueryWrapper<UserTaskDO> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(UserTaskDO::getProcInstId, fmiacei.getExecution().getProcessInstanceId());
            wrapper.eq(UserTaskDO::getTaskNodeCode, fmiacei.getExecution().getCurrentActivityId());
            userTaskService.remove(wrapper);
        }
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
