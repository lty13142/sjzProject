package com.crcm.bpm.core.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.delegate.event.impl.FlowableProcessStartedEventImpl;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName ProcessCreatedListener
 * @Description：流程开始事件监听器
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/16/17:54
 **/
@Slf4j
@Component
public class ProcessStartedListener implements FlowableEventListener {


    @Override
    public void onEvent(FlowableEvent event) {
        log.info("----------流程开始事件----------");
        FlowableProcessStartedEventImpl startedEvent = (FlowableProcessStartedEventImpl) event;
        // 执行参数
        Map params = startedEvent.getVariables();
        // 实例执行id
        String executionId1 = startedEvent.getExecutionId();
        // 实例ID
        String processDefinitionId = startedEvent.getProcessDefinitionId();
        // 执行任务类型
        FlowableEngineEventType type = startedEvent.getType();
        // 首个任务节点 开始节点
        ExecutionEntityImpl entity = (ExecutionEntityImpl) startedEvent.getEntity();

        // TODO 获取到了taskEntity 自己做每个节点的前置操作
//        runtimeService.setVariable(eventImpl.getExecutionId(), Constant.PROCESS_FORM_SKIP_EBABLE, true);

        //获取流程启动是设置的变量对象
//        FormRelevant formRelevant = (FormRelevant) runtimeService.getVariable(eventImpl.getExecutionId(), Constant.PROCESS_FORM_RELEVANT_KEY);
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