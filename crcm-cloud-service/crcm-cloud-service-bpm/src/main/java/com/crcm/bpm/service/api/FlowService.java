package com.crcm.bpm.service.api;

import java.util.Map;

/**
 * @ClassName FlowService
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/4/17:04
 **/
public interface FlowService {

    /**
     * 启动流程
     *
     * @param procDefKey 流程定义Key
     * @param bizKey     业务数据ID
     * @param title      标题
     * @param comment    审批意见
     * @param var        流程变量
     * @return 任务实例ID
     */
    String startProcess(String procDefKey, String bizKey, String title, String comment, String appUser,String tenantId, Map<String, Object> var);
    /**
     * 审批任务
     *
     * @param procInsId 流程实例ID
     * @param taskId    任务实例ID
     * @param title     标题
     * @param comment   审批意见
     * @param var       流程变量
     */
    void complete(String procInsId, String taskId, String title, String comment, Map<String, Object> var);

    /**
     * 审批任务
     *
     * @param procInsId 流程实例ID
     * @param taskId    任务实例ID
     * @param comment   审批意见
     * @param var       流程变量
     */
    void complete(String procInsId, String taskId, String comment, Map<String, Object> var);

}
