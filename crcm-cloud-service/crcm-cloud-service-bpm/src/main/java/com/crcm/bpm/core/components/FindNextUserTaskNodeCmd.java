package com.crcm.bpm.core.components;

import org.apache.commons.collections.CollectionUtils;
import org.flowable.bpmn.model.*;
import org.flowable.common.engine.impl.interceptor.Command;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.impl.util.condition.ConditionUtil;

import java.util.List;
import java.util.Map;

/**
 * @ClassName FindNextUserTaskNodeCmd
 * @Description：寻找下一个用户节点指令
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/11/12
 **/
public class FindNextUserTaskNodeCmd implements Command<UserTask> {

    private final ExecutionEntity execution;
    private final BpmnModel bpmnModel;
    private Map<String, Object> vars;
    /**
     * 返回下一用户节点
     */
    private UserTask nextUserTask;

    /**
     * @param execution 当前执行实例
     * @param bpmnModel 当前执行实例的模型
     * @param vars      参与计算流程条件的变量
     */
    public FindNextUserTaskNodeCmd(ExecutionEntity execution, BpmnModel bpmnModel, Map<String, Object> vars) {
        this.execution = execution;
        this.bpmnModel = bpmnModel;
        this.vars = vars;
    }

    /**
     * @param execution 当前执行实例
     * @param bpmnModel 当前执行实例的模型
     */
    public FindNextUserTaskNodeCmd(ExecutionEntity execution, BpmnModel bpmnModel) {
        this.execution = execution;
        this.bpmnModel = bpmnModel;
    }

    @Override
    public UserTask execute(CommandContext commandContext) {
        execution.setVariables(vars);
        FlowElement currentNode = bpmnModel.getFlowElement(execution.getActivityId());
        List<SequenceFlow> outgoingFlows = ((FlowNode) currentNode).getOutgoingFlows();
        if (CollectionUtils.isNotEmpty(outgoingFlows)) {
            this.findNextUserTaskNode(outgoingFlows, execution);
        }
        return nextUserTask;
    }


    void findNextUserTaskNode(List<SequenceFlow> outgoingFlows, ExecutionEntity execution) {
        sw:
        for (SequenceFlow outgoingFlow : outgoingFlows) {
            if (ConditionUtil.hasTrueCondition(outgoingFlow, execution)) {
                if (outgoingFlow.getTargetFlowElement() instanceof ExclusiveGateway) {
                    //只有排他网关才继续
                    ExclusiveGateway exclusiveGateway = (ExclusiveGateway) outgoingFlow.getTargetFlowElement();
                    findNextUserTaskNode(exclusiveGateway.getOutgoingFlows(), execution);
                } else if (outgoingFlow.getTargetFlowElement() instanceof UserTask) {
                    nextUserTask = (UserTask) outgoingFlow.getTargetFlowElement();
                    //找到第一个符合条件的userTask就跳出循环
                    break sw;
                }
            }
        }
    }
}
