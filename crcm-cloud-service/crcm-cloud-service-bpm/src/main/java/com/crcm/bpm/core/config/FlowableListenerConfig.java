package com.crcm.bpm.core.config;

import com.crcm.bpm.core.listener.*;
import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FlowableListenerConfig
 * @Description： flowable 全局监听器配置类
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/10/11:32
 **/
@Configuration
public class FlowableListenerConfig {

    @Autowired
    private ProcessCreatedListener processCreatedListener;
    @Autowired
    private ProcessStartedListener processStartedListener;
    @Autowired
    private ProcessCompleteListener processCompleteListener;
    @Autowired
    private FindNextUserTaskListener findNextUserTaskListener;
    @Autowired
    private CreateUserTaskListener createUserTaskListener;
    @Autowired
    private TaskCompletedListener taskCompletedListener;
    @Autowired
    private MultiInstanceActivityListener multiInstanceActivityListener;
    @Autowired
    private MultiInstanceActivityCompletedWithConditionListener multiInstanceActivityCompletedWithConditionListener;

    // flowable监听级别参照 {@link FlowableEngineEventType} org.flowable.common.engine.api.delegate.event
    /**
     * 任务节点前置监听
     */

    // 自己建立监听类实现FlowableEventListener接口

    /**
     * 将自定义监听器纳入flowable监听
     *
     * @param
     * @return org.flowable.spring.boot.EngineConfigurationConfigurer<org.flowable.spring.SpringProcessEngineConfiguration>
     * @author: Lu Yang
     * @date: 2019/5/4 21:05
     */
    @Bean
    public EngineConfigurationConfigurer<SpringProcessEngineConfiguration> globalListenerConfigurer() {
        return engineConfiguration -> {
            engineConfiguration.setEventListeners(this.getGlobalFlowableEventListener());
            engineConfiguration.setTypedEventListeners(this.getGlobalTypeFlowableEventListener());
        };
    }

    /**
     * 全局条件监听事件
     *
     * @return
     */
    private Map<String, List<FlowableEventListener>> getGlobalTypeFlowableEventListener() {
        Map<String, List<FlowableEventListener>> typedListeners = new HashMap<>();
        // 流程实例创建 监听组
        List<FlowableEventListener> processCreateList = new ArrayList<>();
        processCreateList.add(processCreatedListener);
        // 流程实例创建监听器
        typedListeners.put(FlowableEngineEventType.PROCESS_CREATED.name(), processCreateList);
        // 流程开始 监听组
        List<FlowableEventListener> processStartList = new ArrayList<>();
        processStartList.add(processStartedListener);
        typedListeners.put(FlowableEngineEventType.PROCESS_STARTED.name(), processStartList);
        // 流程结束 监听组
        List<FlowableEventListener> processCompleteList = new ArrayList<>();
        processCompleteList.add(processCompleteListener);
        typedListeners.put(FlowableEngineEventType.PROCESS_COMPLETED.name(), processCompleteList);


        // 多实例开始监听监听组
        List<FlowableEventListener>  multiInstanceTaskList = new ArrayList<>();
        // 节点连线监听器
        multiInstanceTaskList.add(multiInstanceActivityListener);
        typedListeners.put(FlowableEngineEventType.MULTI_INSTANCE_ACTIVITY_STARTED.name(), multiInstanceTaskList);



        // 任务创建 监听组
        List<FlowableEventListener> taskBeforeList = new ArrayList<>();
        taskBeforeList.add(findNextUserTaskListener);
        // 创建用户节点监听器
        taskBeforeList.add(createUserTaskListener);
        typedListeners.put(FlowableEngineEventType.TASK_CREATED.name(), taskBeforeList);

        // 节点任务分配监听组
        List<FlowableEventListener> taskAssignList = new ArrayList<>();
        taskAssignList.add(new TaskAssignListener());
        typedListeners.put(FlowableEngineEventType.TASK_ASSIGNED.name(), taskAssignList);
        // 节点任务完成监听组
        List<FlowableEventListener> taskCompletedList = new ArrayList<>();
        taskCompletedList.add(taskCompletedListener);
        typedListeners.put(FlowableEngineEventType.TASK_COMPLETED.name(), taskCompletedList);


        // 多实例会签完成监听组
        List<FlowableEventListener> multiInstanceCompletedList = new ArrayList<>();
        multiInstanceCompletedList.add(multiInstanceActivityCompletedWithConditionListener);
        typedListeners.put(FlowableEngineEventType.MULTI_INSTANCE_ACTIVITY_COMPLETED_WITH_CONDITION.name(), multiInstanceCompletedList);


        return typedListeners;
    }

    /**
     * 全局监听事件
     *
     * @return
     */
    private List<FlowableEventListener> getGlobalFlowableEventListener() {
        List<FlowableEventListener> list = new ArrayList<>();
        list.add(new FlowableWholeEventListener());
        return list;
    }

}
