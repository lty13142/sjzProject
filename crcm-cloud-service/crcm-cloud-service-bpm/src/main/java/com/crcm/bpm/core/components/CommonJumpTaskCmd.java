package com.crcm.bpm.core.components;

import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.Process;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.FlowableEngineAgenda;
import org.flowable.engine.impl.cmd.NeedsActiveTaskCmd;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityManager;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.engine.impl.util.ProcessDefinitionUtil;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CommonJumpTaskCmd
 * @Description：通用跳转指令
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/11/12
 **/
public class CommonJumpTaskCmd  extends NeedsActiveTaskCmd<Boolean> {

    protected String processId;//执行实例id
    protected String targetNodeId;//目标节点
    protected Map<String, Object> variables;//变量
    protected String operationCode;
    public CommonJumpTaskCmd(String taskId, String processId, String targetNodeId, Map<String, Object> variables,String operationCode) {
        super(taskId);
        this.processId = processId;
        this.targetNodeId = targetNodeId;
        this.variables = variables;
        this.operationCode=operationCode;
    }


    @Override
    protected Boolean execute(CommandContext commandContext, TaskEntity task) {
        ExecutionEntityManager executionEntityManager = CommandContextUtil.getExecutionEntityManager();
        ExecutionEntity rootExecution=  executionEntityManager.findChildExecutionsByParentExecutionId(processId).get(0);

        CommandContextUtil.getTaskService().deleteTask(task, true);

        List<ExecutionEntity> executionEntityList= executionEntityManager.findChildExecutionsByParentExecutionId(rootExecution.getId());
        for(ExecutionEntity executionEntity:executionEntityList){
            List<ExecutionEntity> executionEntityList2= executionEntityManager.findChildExecutionsByParentExecutionId(executionEntity.getId());

            for(ExecutionEntity executionEntity2:executionEntityList2){
                CommandContextUtil.getTaskService().deleteTasksByExecutionId(executionEntity2.getId());
                executionEntityManager.deleteChildExecutions(executionEntity2,"delete",true);
                executionEntityManager.delete(executionEntity2);
                CommandContextUtil.getVariableService().deleteVariablesByExecutionId(executionEntity2.getId());
            }
            CommandContextUtil.getTaskService().deleteTasksByExecutionId(executionEntity.getId());
            executionEntityManager.deleteChildExecutions(executionEntity,"delete",true);
            executionEntityManager.delete(executionEntity);
            CommandContextUtil.getVariableService().deleteVariablesByExecutionId(executionEntity.getId());
        }




        Process process = ProcessDefinitionUtil.getProcess(rootExecution.getProcessDefinitionId());
        FlowElement targetFlowElement = process.getFlowElement(targetNodeId);
        rootExecution.setCurrentFlowElement(targetFlowElement);
        FlowableEngineAgenda agenda = CommandContextUtil.getAgenda();
        agenda.planContinueProcessInCompensation(rootExecution);

        return true;
    }


}
