package com.crcm.bpm.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName NodeAssigneesDTO
 * @Description：节点审核人信息
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/12/22
 **/
@Data
public class NodeAssigneesDTO implements Serializable {

    private static final long serialVersionUID = 8311956818026274039L;
    /**
     * 审核人名称集合
     */
    private List<String> assigneeNames;
    /**
     * 审核人ID集合
     */
    private List<String> assigneeIds;
    /**
     * 节点ID
     */
    private String nodeId;
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 任务类型 当前任务类型  start 起草 approve 审批 countersign 会签  record  备案 archive 归档
     */
    private String taskType;
    /**
     * 节点审核人指派方式，0:自动指派 1:手动指派
     */
    private String assignMode;
    /**
     * 查询人员方式(1:用户选择器，2：固定人员，3：前端指定。4：角色。5：岗位，6：同指定节点人员，7：角色（同申请人部门）)
     */
    private String findUserType;

}
