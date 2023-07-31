package com.crcm.bpm.core.components;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.Process;
import org.flowable.common.engine.impl.interceptor.Command;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.impl.persistence.deploy.DeploymentManager;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.engine.impl.util.ProcessDefinitionUtil;

import java.io.Serializable;

/**
 * @ClassName GetProcessCmd
 * @Description：功能描述: 获取流程定义
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/16
 **/
public class GetProcessCmd implements Command<Process>, Serializable {

    String processDefinitionId;

    public GetProcessCmd(String processDefinitionId) {

        this.processDefinitionId = processDefinitionId;
    }

    @Override
    public Process execute(CommandContext commandContext) {
        DeploymentManager deploymentManager = CommandContextUtil.getProcessEngineConfiguration().getDeploymentManager();
        deploymentManager.getProcessDefinitionCache().remove(processDefinitionId);
        BpmnModel bpmnModel = ProcessDefinitionUtil.getBpmnModel(processDefinitionId);
        return bpmnModel.getProcesses().get(0);
    }
}
