package com.crcm.bpm.domain.dto.request;

import com.crcm.bpm.domain.dto.BaseRequestDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @ClassName NodeUserSaveOrUpdateDTO
 * @Description：节点用户保存或修改DTO
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/9
 **/
@ToString
@Data
@EqualsAndHashCode(callSuper = false)
public class NodeUserSaveOrUpdateDTO extends BaseRequestDTO {
    private static final long serialVersionUID = -756402673679640383L;
    private Long id;

    /**
     * 申请编号
     */
    private Long applyId;

    /**
     * 流程实例编号
     */
    private String procInstId;

    /**
     * 流程编号
     */
    private Long processId;

    /**
     * 模型编号
     */
    private Long modelId;

    /**
     * 流程KEY
     */
    private String processKey;

    /**
     * 节点编号
     */
    private String nodeId;

    /**
     * 节点名称
     */
    private String nodeName;

    private String parentNodeId;

    /**
     * 父节点名称
     */
    private String parentNodeName;

    /**
     * 流程定义编号
     */
    private String definitionId;

    /**
     * 租户编号
     */
    private String tenantId;

    /**
     * 节点分配人员工号 多个人以 , 区分
     */
    private String assigneeUserIdList;

    /**
     * 节点分配人员姓名 多个人以 , 区分
     */
    private String assigneeUserNameList;

    /**
     * 当处理策略为 节点找不到人时 跳过则为 1，否则为 0
     */
    private Integer skip;

    /**
     * 当处理策略为 节点找不到人时 管理员处理则为 1，否则为 0
     */
    private Integer defaultSetAdmin;

    /**
     * 当处理策略为 节点找不到人时 抛出异常处理则为 1，否则为 0
     */
    private Integer error;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态 1 有效 0 失效
     */
    private Integer validState;

    /**
     * 操作人编号
     */
    private String operatorId;

    /**
     * 操作人姓名
     */
    private String operatorName;
}
