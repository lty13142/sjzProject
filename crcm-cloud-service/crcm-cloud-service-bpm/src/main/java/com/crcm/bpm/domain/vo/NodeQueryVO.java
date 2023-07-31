package com.crcm.bpm.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName NodeQueryVO
 * @Description：节点查询VO
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/11/3
 **/
@Data
public class NodeQueryVO implements Serializable {

    /**
     * 流程定义ID
     */
    private String processDefId;
    /**
     * 节点ID
     */
    private String taskNodeId;
    /**
     * 任务ID
     */
    private String taskId;
    /**
     * 业务判断数据
     */
    private Map<String, Object> dataMap;
}
