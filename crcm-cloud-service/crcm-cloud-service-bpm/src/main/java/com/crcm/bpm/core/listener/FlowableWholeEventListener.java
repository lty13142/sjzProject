package com.crcm.bpm.core.listener;

import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.common.engine.api.delegate.event.FlowableEventType;

/**
 * @ClassName FlowableWholeEventListener
 * @Description：flowable全局事件监听器
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/16/17:06
 **/
@Slf4j
public class FlowableWholeEventListener implements FlowableEventListener {
    @Override
    public void onEvent(FlowableEvent event) {
        FlowableEventType type = event.getType();
        log.info("==================流程事件：{}",type);
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
