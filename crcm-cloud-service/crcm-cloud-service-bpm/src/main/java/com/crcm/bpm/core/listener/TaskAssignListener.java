package com.crcm.bpm.core.listener;

import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.engine.delegate.event.impl.FlowableEntityEventImpl;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.springframework.stereotype.Component;

/**
 * @ClassName TaskAssignListener
 * @Description：任务分配监听器
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：Administrator
 * @Date：2020/10/14
 **/
@Slf4j
@Component
public class TaskAssignListener  implements FlowableEventListener {
    @Override
    public void onEvent(FlowableEvent event) {
        // 当前节点任务实体
        TaskEntity taskEntity = (TaskEntity) ((FlowableEntityEventImpl) event).getEntity();
        // 节点业务数据
//        Map<String, Object> processMap = taskEntity.getVariables();
        log.info("任务 {} 已由id为 {} 的用户签收",taskEntity.getName(),taskEntity.getAssignee());
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
