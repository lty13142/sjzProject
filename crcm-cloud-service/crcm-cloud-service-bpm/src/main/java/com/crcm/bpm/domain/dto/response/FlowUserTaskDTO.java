package com.crcm.bpm.domain.dto.response;

import com.crcm.bpm.domain.dto.BaseResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @ClassName FlowUserTaskDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/9
 **/
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class FlowUserTaskDTO extends BaseResponseDTO {
    private static final long serialVersionUID = 2303010644881455471L;


    private String parentNodeId;

    private String parentNodeName;

    private String nodeId;

    private String nodeName;

    private Integer findUserType;

    private String roleGroupCode;

    private String roleGroupName;

    private String roleCode;

    /* 如果角色组对应多个角色，则取第一个 */
    private String roleName;

    private List<String> owners;

    /**
     * 审核人名称集合
     */
    private List<String> assigneeNames;
    /**
     * 审核人ID集合
     */
    private List<String> assigneeIds;

    private Integer priority;

    private String formKey;

    /**
     * 节点处理策略 skip: 执行人为空跳过,admin: 为空时管理员处理,error:为空时报错
     */
    private String handlerStrategy;

    private boolean error;

    private boolean skip;

    private boolean defaultSetAdmin;

    private String errorMessage;
    /**
     * 节点可编辑字段
     */
    private List<String> formEditFields;

    /**
     * 用户任务 多实例属性 parallel 并行审批，sequential 串行审批
     */
    private String sequential;
    /**
     * 通过比例
     */
    private String proportion;

    /**
     * 任务类型 当前任务类型  start 起草 approve 审批 countersign 会签  record  备案 archive 归档
     */
    private String taskType;
    /**
     * 节点审核人指派方式，0:自动指派 1:手动指派
     */
    private String assignMode;

}
