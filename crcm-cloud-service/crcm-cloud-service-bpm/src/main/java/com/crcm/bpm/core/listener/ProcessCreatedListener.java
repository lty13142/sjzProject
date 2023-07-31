package com.crcm.bpm.core.listener;

import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.engine.delegate.event.impl.FlowableEntityEventImpl;
import org.flowable.engine.delegate.event.impl.FlowableProcessEventImpl;
import org.springframework.stereotype.Component;

/**
 * @ClassName ProcessStartedListener
 * @Description：流程创建监听器
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/8/10:33
 **/
@Slf4j
@Component
public class ProcessCreatedListener implements FlowableEventListener {

    @Override
    public void onEvent(FlowableEvent event) {
        log.info("----------流程创建事件----------");
        FlowableProcessEventImpl createEvent = (FlowableProcessEventImpl) event;
        String executionId = createEvent.getExecutionId();
        String processDefinitionId = createEvent.getProcessDefinitionId();
        FlowableEntityEventImpl entityEvent = (FlowableEntityEventImpl) event;
        Object entity = entityEvent.getEntity();
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
