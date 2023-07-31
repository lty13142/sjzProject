package com.crcm.bpm.domain.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>Title:流程节点表 </p>
 * <p>Description:流程节点表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中再云图技术有限公司 </p>
 * @author ${Author}
 * @Date  2020-09-22
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@ApiModel("流程节点表")
@TableName("bpm_node")
public class NodeDO extends BaseEntity implements Serializable{
    private static final long serialVersionUID=1L;

    /**
     * 节点编号
     * id：int ==》 id：Integer
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 节点编号
     * node_id：varchar(32) ==》 nodeId：String
     */
    @ApiModelProperty(value = "节点编号")
    private String nodeId;
    /**
     * 节点名称
     * node_name：varchar(255) ==》 nodeName：String
     */
    @ApiModelProperty(value = "节点名称")
    private String nodeName;
    /**
     * 流程部署ID
     * definition_id：varchar(32) ==》 definitionId：String
     */
    @ApiModelProperty(value = "流程部署ID")
    private String definitionId;
    /**
     * 模型ID
     * model_id：int ==》 modelId：Long
     */
    @ApiModelProperty(value = "模型ID")
    private Long modelId;
    /**
     * 流程KEY
     * process_key：varchar(32) ==》 processKey：String
     */
    @ApiModelProperty(value = "流程KEY")
    private String processKey;
    /**
     * 租户ID
     * tenant_id：varchar(32) ==》 tenantId：String
     */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
     * 节点类型
     * node_type：varchar(16) ==》 nodeType：String
     */
    @ApiModelProperty(value = "节点类型")
    private String nodeType;
    /**
     * 任务类型 当前任务类型  start 起草 approve 审批  record  备案 archive 归档
     * task_type：varchar(16) ==》 taskType：String
     */
    @ApiModelProperty(value = "任务类型 当前任务类型  start 起草 approve 审批  record  备案 archive 归档")
    private String taskType;
    /**
     * 优先级
     * priority：int ==》 priority：Integer
     */
    @ApiModelProperty(value = "优先级")
    private Integer priority;
    /**
     * 关联表单key
     * form_key：varchar(32) ==》 formKey：String
     */
    @ApiModelProperty(value = "关联表单key")
    private String formKey;
    /**
     * 节点人员选择器
     * user_selectors：varchar(1000) ==》 userSelectors：String
     */
    @ApiModelProperty(value = "节点人员选择器")
    private String userSelectors;
    /**
     * 节点人员ID列表
     * user_id_list：text ==》 userIdList：String
     */
    @ApiModelProperty(value = "节点人员ID列表")
    private String userIdList;
    /**
     * 节点人员名称列表，。多个人以 , 区分
     * user_name_list：text ==》 userNameList：String
     */
    @ApiModelProperty(value = "节点人员名称列表，。多个人以 , 区分")
    private String userNameList;
    /**
     * 节点角色组编号
     * role_group_code：varchar(32) ==》 roleGroupCode：String
     */
    @ApiModelProperty(value = "节点角色组编号")
    private String roleGroupCode;
    /**
     * 节点角色组名称
     * role_group_name：varchar(64) ==》 roleGroupName：String
     */
    @ApiModelProperty(value = "节点角色组名称")
    private String roleGroupName;
    /**
     * 节点角色列表
     * role_list：text ==》 roleList：String
     */
    @ApiModelProperty(value = "节点角色列表")
    private String roleList;
    /**
     * 节点角色名称列表
     * role_name_list：text ==》 roleNameList：String
     */
    @ApiModelProperty(value = "节点角色名称列表")
    private String roleNameList;
    /**
     * 节点用户查找类型
     * find_user_type：int ==》 findUserType：Integer
     */
    @ApiModelProperty(value = "节点用户查找类型 ")
    private Integer findUserType;
    /**
     * 组合方式：1 异常(找不到节点人员提示异常) 2 正常（找不到节点人员就跳过当前环节）
     * combine_type：int ==》 combineType：Integer
     */
    @ApiModelProperty(value = "组合方式：1 异常(找不到节点人员提示异常) 2 正常（找不到节点人员就跳过当前环节） ")
    private Integer combineType;
    /**
     * 用户节点人员分配字段名称
     * assignee_field：varchar(255) ==》 assigneeField：String
     */
    @ApiModelProperty(value = "用户节点人员分配字段名称")
    private String assigneeField;
    /**
     * 是否选择路径 0 否 1 是
     * select_path：int ==》 selectPath：Integer
     */
    @ApiModelProperty(value = "是否选择路径 0 否 1 是")
    private Integer selectPath;
    /**
     * 节点处理策略 skip: 执行人为空跳过,admin: 为空时管理员处理,error:为空时报错
     * handler_strategy：varchar(16) ==》 handlerStrategy：String
     */
    @ApiModelProperty(value = "节点处理策略 skip: 执行人为空跳过,admin: 为空时管理员处理,error:为空时报错")
    private String handlerStrategy;
    /**
     * 依赖节点ID
     * relation_node_id：varchar(32) ==》 relationNodeId：String
     */
    @ApiModelProperty(value = "依赖节点ID")
    private String relationNodeId;
    /**
     * 节点动作集合，用于控制按钮
     * action_list：varchar(1000) ==》 actionList：String
     */
    @ApiModelProperty(value = "节点动作集合，用于控制按钮")
    private String actionList;
    /**
     * 用户任务条件跳过表达式
     * skip_expression：varchar(1000) ==》 skipExpression：String
     */
    @ApiModelProperty(value = "用户任务条件跳过表达式")
    private String skipExpression;
    /**
     * 连线表达式
     * expression：varchar(255) ==》 expression：String
     */
    @ApiModelProperty(value = "连线表达式")
    private String expression;
    /**
     * 连线来源节点NodeId
     * source_ref：varchar(32) ==》 sourceRef：String
     */
    @ApiModelProperty(value = "连线来源节点NodeId")
    private String sourceRef;
    /**
     * 连线目标节点NodeId
     * target_ref：varchar(32) ==》 targetRef：String
     */
    @ApiModelProperty(value = "连线目标节点NodeId")
    private String targetRef;
    /**
     * 用户任务 多实例属性 parallel 并行审批，sequential 串行审批
     * sequential：varchar(16) ==》 sequential：String
     */
    @ApiModelProperty(value = "用户任务 多实例属性 parallel 并行审批，sequential 串行审批")
    private String sequential;
    /**
     * 通过比例
     * proportion：varchar(8) ==》 proportion：String
     */
    @ApiModelProperty(value = "通过比例")
    private String proportion;
    /**
     * 备注
     * remarks：varchar(1000) ==》 remarks：String
     */
    @ApiModelProperty(value = "备注")
    private String remarks;
    /**
     * 状态 1 有效 0 失效
     * valid_state：int ==》 validState：Integer
     */
    @ApiModelProperty(value = "状态 1 有效 0 失效")
    private Integer validState;

    /**
     * 流程表单可编辑字段
     * form_edit_field：text ==》 formEditField：String
     */
    @ApiModelProperty(value = "流程表单可编辑字段")
    private String formEditField;

    /**
     * 节点岗位列表，多个岗位以 , 区分
     */
    @ApiModelProperty(value = "节点岗位")
    private String postId;

    /**
     * 节点岗位名称列表，多个岗位以 , 区分
     */
    @ApiModelProperty(value = "节点岗位")
    private String postName;

    /**
     * 会签是否全部执行 0 否 1是
     * whole_deal：varchar(8) ==》 wholeDeal：String
     */
    @ApiModelProperty(value = "会签是否全部执行 0 否 1是")
    private String wholeDeal;

    /**
     * 会签通过跳转 1:下一节点 2:退回上一节点
     * pass_skip：varchar(8) ==》 pass_skip：String
     */
    @ApiModelProperty(value = "会签通过跳转 1:下一节点 2:退回上一节点")
    private String passSkip;

    /**
     * 会签未通过跳转 1:下一节点 2:退回上一节点
     * proportion：varchar(8) ==》 proportion：String
     */
    @ApiModelProperty(value = "会签未通过跳转 1:下一节点 2:退回上一节点")
    private String noPassSkip;
    /**
     * 节点审核人指派方式，0:自动指派 1:手动指派
     * assign_mode：varchar(8) ==》 assignMode：String
     */
    @ApiModelProperty(value = "节点审核人指派方式，0:自动指派 1:手动指派")
    private String assignMode;
    /**
     * 节点抄送
     * copy_send：varchar(8) ==》 copySend：String
     */
    @ApiModelProperty(value = "节点抄送")
    private String copySend;
    /***
     * 逻辑删除 不使用isDelete，避免RPC框架在反向解析时报错
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer  deleted;

}
