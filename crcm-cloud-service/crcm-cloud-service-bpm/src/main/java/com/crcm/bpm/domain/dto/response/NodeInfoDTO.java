package com.crcm.bpm.domain.dto.response;

import com.crcm.bpm.domain.dto.BaseResponseDTO;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName NodeInfoDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/10
 **/
@Data
@Accessors(chain = true)
@ToString
public class NodeInfoDTO extends BaseResponseDTO {
    private static final long serialVersionUID = -2208067065818555187L;
    private Integer id;

    /**
     * 节点编号
     */
    private String nodeId;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 父节点编号
     */
    private String parentNodeId;

    /**
     * 父节点名称
     */
    private String parentNodeName;

    /**
     * 版本号
     */
    private String definitionId;

    /**
     * 流程编号
     */
    private Long processId;
    /**
     * 模型编号
     */
    private Long modelId;

    /**
     * 流程key
     */
    private String processKey;

    /**
     * 租户编号
     */
    private String tenantId;

    /**
     * 节点类型
     */
    private String nodeType;

    /**
     * 任务类型 当前任务类型  start 起草 approve 审批  record  备案 archive 归档
     */
    private String taskType;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 关联表单KEY
     */
    private String formKey;

    /**
     * 表单名称
     */
    private String formName;

    /**
     * 节点人员编号列表
     */
    private String userIdList;

    /**
     * 节点人员名称列表，。多个人以 , 区分
     */
    private String userNameList;

    /**
     * 节点角色组编号
     */
    private String roleGroupCode;

    /**
     * 角色组名称
     */
    private String roleGroupName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 节点用户查找类型
     */
    private Integer findUserType;

    /**
     * 组合方式：1 正常(找不到节点人员提示异常) 2 正常（找不到节点人员就跳过当前环节）
     */
    private Integer combineType;

    /**
     * 用户节点人员分配字段名称
     */
    private String assigneeField;

    /**
     * 是否选择路径 0 否 1 是
     */
    private Integer selectPath;

    /**
     * 节点处理策略 skip: 执行人为空跳过,admin: 为空时管理员处理,error:为空时报错
     */
    private String handlerStrategy;

    /**
     * 依赖节点
     */
    private String relationNodeId;

    /**
     * 动作集合
     */
    private String actionList;

    /**
     * 用户任务条件跳过表达式
     */
    private String skipExpression;

    /**
     * 连线表达式
     */
    private String expression;

    /**
     * 连线来源节点NodeId
     */
    private String sourceRef;

    /**
     * 连线目标节点NodeId
     */
    private String targetRef;

    /**
     * 用户任务 多实例属性 parallel 并行审批，sequential 串行审批
     */
    private String sequential;

    /**
     * 通过比例
     */
    private String proportion;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 节点人员选择器
     */
    private String userSelectors;

    /**
     * 流程表单可编辑字段
     */
    private String formEditField;

    /**
     * 节点岗位
     */
    private String postId;

    /**
     * 节点岗位
     */
    private String postName;

    /**
     * 是否全部执行
     */
    private String wholeDeal;

    /**
     * 会签同意跳转
     */
    private String passSkip;

    /**
     * 会签不同意跳转
     */
    private String noPassSkip;

    /**
     * 角色编码列表
     */
    private List<String> roleList;

    /**
     * 角色名称列表
     */
    private List<String> roleNameList;
    /**
     * 节点审核人指派方式
     */
    private String assignMode;
    /**
     * 节点抄送
     */
    private String copySend;
}
