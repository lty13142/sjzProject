package com.crcm.bpm.core.listener;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crcm.bpm.core.constant.BpmConstant;
import com.crcm.bpm.core.constant.TaskConstant;
import com.crcm.bpm.core.utils.CustomFormUtil;
import com.crcm.bpm.core.utils.SendTaskMessageUtil;
import com.crcm.bpm.domain.dto.response.UserTaskDTO;
import com.crcm.bpm.domain.entity.ApplyDO;
import com.crcm.bpm.domain.entity.HistoryDO;
import com.crcm.bpm.domain.entity.ModelDO;
import com.crcm.bpm.service.*;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.common.engine.impl.event.FlowableEngineEventImpl;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.task.service.impl.persistence.entity.TaskEntityImpl;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @ClassName ProcessEndListener
 * @Description：流程结束监听器
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/8/10:20
 **/
@Slf4j
@Component
public class ProcessCompleteListener implements FlowableEventListener {

    @Lazy
    @Qualifier("processEngine")
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private FormDataService formDataService;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private UserTaskService userTaskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ModelService modelService;

    @Override
    public void onEvent(FlowableEvent event) {

        RuntimeService runtimeService = processEngine.getRuntimeService();

        FlowableEngineEventImpl flowableEntityEvent = (FlowableEngineEventImpl) event;
        String processInstanceId = flowableEntityEvent.getProcessInstanceId();
        String approveAction = null;
        TaskEntityImpl taskEntity = new TaskEntityImpl();
        boolean clearFormDataFlag = true;
        Map<String, Object> variables = Collections.synchronizedMap(new HashMap<>());
        try {
            // 如果有加事务，则可以在这里获取
            variables = runtimeService.getVariables(flowableEntityEvent.getExecutionId());
            approveAction = variables.getOrDefault(TaskConstant.APPROVE_ACTION_DESC, TaskConstant.APPROVE_ACTION_PASS).toString();
        } catch (Exception e) {

            // 如果没有加事务，则 通过历史记录获取
            HistoricVariableInstance historicVariableInstance = processEngine.getHistoryService().createHistoricVariableInstanceQuery()
                    .processInstanceId(processInstanceId).variableName(TaskConstant.APPROVE_ACTION_DESC).singleResult();
            approveAction = historicVariableInstance.getValue().toString();
        }
        if (TaskConstant.APPROVE_ACTION_PASS.equals(approveAction)) {
            updateApplyStatusByProInsId(processInstanceId, BpmConstant.APPLY_STATUS_PASS);
        } else if (TaskConstant.APPROVE_ACTION_COUNTER_SIGN.equals(approveAction)) {
            updateApplyStatusByProInsId(processInstanceId, BpmConstant.APPLY_STATUS_PASS);
            updateUserTaskCompleted(processInstanceId);
        } else if (TaskConstant.APPROVE_ACTION_FAIL.equals(approveAction)) {
            updateApplyStatusByProInsId(processInstanceId, BpmConstant.APPLY_STATUS_REJECT);
        } else if (TaskConstant.APPROVE_ACTION_REJECT.equals(approveAction)) {
            updateApplyStatusByProInsId(processInstanceId, BpmConstant.APPLY_STATUS_REJECT);
        } else {
            updateApplyStatusByProInsId(processInstanceId, BpmConstant.APPLY_STATUS_DISABLE);
        }

        log.info("流程实例{}已完成", processInstanceId);

        // 流程完成后删除流程数据表里面当前流程数据（数据已存入历史数据表）
        if (clearFormDataFlag) {
            formDataService.deleteProcessData(processInstanceId);
        }
//        SpringContextUtils.getApplicationContext().publishEvent(new ProcessEndEvent(engineEvent,engineEvent.getProcessInstanceId()));

        // 推送消息给流程发起人，提示流程已结束
        LambdaQueryWrapper<ApplyDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ApplyDO::getProcInstId, processInstanceId);
        List<ApplyDO> applyDOList = applyService.list(wrapper);
        if (applyDOList != null && applyDOList.size() > 0) {
            ApplyDO applyDO = applyDOList.get(0);
            StringBuilder sb = new StringBuilder()
                    .append("您发起的<")
                    .append(applyDO.getProcessName())
                    .append(">流程已结束，流程编号：")
                    .append(applyDO.getId());
            SendTaskMessageUtil.sendFinish(sb.toString(), applyDO.getApplyUserId());

            customFormFinish(applyDO.getId(), applyDO.getModelId());
        }

    }

    private void customFormFinish(Long applyId, Long modelId) {
        ModelDO modelDO = modelService.getById(modelId);
        CustomFormUtil.customFormFinish(modelDO.getFormCode(), applyId);
    }

    /**
     * 修改申请记录的状态
     *
     * @param processInstanceId
     * @param status
     */
    private void updateApplyStatusByProInsId(String processInstanceId, Integer status) {

        ApplyDO applyDO = new ApplyDO();
        applyDO.setProcInstId(processInstanceId);
        applyDO.setApplyStatus(status);
        applyDO.setCompleteTime(LocalDateTime.now());
        applyService.updateApplyStatusByProInsId(applyDO);
    }

    /**
     * 设置其余任务为已完成
     *
     * @param processInstanceId
     */
    private void updateUserTaskCompleted(String processInstanceId) {
        List<UserTaskDTO> userTasks = userTaskService.getUserTaskByProcInstId(processInstanceId);
        ArrayList<HistoryDO> historyDOS = new ArrayList<>();
        ArrayList<Long> taskIds = new ArrayList<>();
        userTasks.stream().forEach(userTaskDTO -> {
            String[] candidateUsers = userTaskDTO.getCandidateUserList().split(",");
            String[] candidateUserNames = userTaskDTO.getCandidateUserNameList().split(",");
            taskIds.add(userTaskDTO.getId());
            HistoryDO historyDO = BeanUtil.copyProperties(userTaskDTO, HistoryDO.class);
            historyDO.setApproveTime(LocalDateTime.now())
                    .setDueTime(LocalDateTime.now())
                    .setApproveOpinion("系统自动完成")
                    .setApproveResult(TaskConstant.APPROVE_ACTION_PASS)
                    .setTaskAssigneeUserId(ArrayUtil.isEmpty(candidateUsers) ? "" : candidateUsers[0])
                    .setTaskAssigneeRealName(ArrayUtil.isEmpty(candidateUserNames) ? "" : candidateUserNames[0])
                    .setTaskStatus(TaskConstant.TASK_COMPLETED);
            historyDOS.add(historyDO);
        });
        // 删除用户任务
        userTaskService.removeByIds(taskIds);
        // 添加流程历史记录
        historyService.saveBatch(historyDOS);

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
