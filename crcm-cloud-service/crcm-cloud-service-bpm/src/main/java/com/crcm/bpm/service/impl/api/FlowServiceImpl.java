package com.crcm.bpm.service.impl.api;

import com.crcm.bpm.service.api.FlowService;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.*;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FlowServiceImpl
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/4/17:04
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class FlowServiceImpl implements FlowService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private IdentityService identityService;
    /**
     * 启动流程
     *
     * @param procDefKey 流程定义Key
     * @param bizKey     业务数据ID
     * @param title      标题
     * @param appUser    流程启动人
     * @param comment    审批意见
     * @param params        流程变量
     * @return 任务实例ID
     */
    @Override
    public String startProcess(String procDefKey, String bizKey, String title, String comment, String appUser,String tenantId, Map<String, Object> params) {
        // 添加流程发起人
        if(params !=null){
            params.put("applyUser",appUser);
        } else{
            params=new HashMap<>();
            params.put("applyUser",appUser);
        }
        //启动流程
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKeyAndTenantId(procDefKey, bizKey, params, tenantId);
        String processInstanceId = processInstance.getId();
        // 任务接受人
        params.put("assignee","11111");
        // 任务候选用户
        params.put("candidateUsers", Arrays.asList(new String[]{"1111","2222"}));
        // 任务候选用户组
        params.put("CandidateGroups", Arrays.asList(new String[]{"部门负责人","项目经理"}));
        //自动完成第一个提交节点
        Task task=taskService.createTaskQuery().processInstanceId(processInstanceId).active().singleResult();
        complete(processInstanceId, task.getId(), title, comment, params);
        return processInstanceId;
    }

    /**
     * 审批任务
     * @param procInsId 流程实例ID
     * @param taskId    任务实例ID
     * @param title     标题
     * @param comment   审批意见
     * @param var       流程变量
     */
    @Override
    public void complete(String procInsId, String taskId, String title, String comment, Map<String, Object> var) {
        var.put("title",title);
        complete(procInsId,taskId,comment,var);
    }
    /**
     * 审批任务
     * @param procInsId 流程实例ID
     * @param taskId    任务实例ID
     * @param comment   审批意见
     * @param var       流程变量
     */
    @Override
    public void complete(String procInsId, String taskId, String comment, Map<String, Object> var) {
        //设置审批意见
        if (StringUtils.isNotEmpty(comment)) {
            taskService.addComment(taskId, procInsId, comment);
        }
        taskService.complete(taskId, var);
    }

}
