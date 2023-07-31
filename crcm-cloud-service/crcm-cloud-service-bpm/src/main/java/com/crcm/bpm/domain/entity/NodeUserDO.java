package com.crcm.bpm.domain.entity;



import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>Title:节点人员表 </p>
 * <p>Description:节点人员表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中再云图技术有限公司 </p>
 * @author ${Author}
 * @Date  2020-10-09
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("节点人员表")
@TableName("bpm_node_user")
public class NodeUserDO extends BaseEntity implements Serializable{
    private static final long serialVersionUID=1L;

    /**
     * 节点用户编号
     * id：int ==》 id：Integer
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 申请编号
     * apply_id：int ==》 applyId：Integer
     */
    @ApiModelProperty(value = "申请编号")
    private Integer applyId;
    /**
     * 流程实例编号
     * proc_inst_id：varchar(32) ==》 procInstId：String
     */
    @ApiModelProperty(value = "流程实例编号")
    private String procInstId;
    /**
     * 流程编号
     * process_id：int ==》 processId：Integer
     */
    @ApiModelProperty(value = "流程编号")
    private Integer processId;
    /**
     * 模型ID
     * model_id：int ==》 modelId：Long
     */
    @ApiModelProperty(value = "模型ID")
    private Long modelId;
    /**
     * 流程KEY
     * process_key：varchar(255) ==》 processKey：String
     */
    @ApiModelProperty(value = "流程KEY")
    private String processKey;
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
     * 父节点编号
     * parent_node_id：varchar(32) ==》 parentNodeId：String
     */
    @ApiModelProperty(value = "父节点编号")
    private String parentNodeId;
    /**
     * 父节点名称
     * parent_node_name：varchar(255) ==》 parentNodeName：String
     */
    @ApiModelProperty(value = "父节点名称")
    private String parentNodeName;
    /**
     * 流程定义编号
     * definition_id：varchar(32) ==》 definitionId：String
     */
    @ApiModelProperty(value = "流程定义编号")
    private String definitionId;
    /**
     * 租户id
     * tenant_id：varchar(32) ==》 tenantId：String
     */
    @ApiModelProperty(value = "租户id")
    private String tenantId;
    /**
     * 节点分配人员工号 多个人以 , 区分
     * assignee_userId_list：text ==》 assigneeUseridList：String
     */
    @ApiModelProperty(value = "节点分配人员工号 多个人以 , 区分")
    @TableField("assignee_user_Id_list")
    private String assigneeUserIdList;
    /**
     * 节点分配人员姓名 多个人以 , 区分
     * assignee_user_name_list：text ==》 assigneeUserNameList：String
     */
    @ApiModelProperty(value = "节点分配人员姓名 多个人以 , 区分")
    private String assigneeUserNameList;
    /**
     * 当处理策略为 节点找不到人时 跳过则为 1，否则为 0
     * skip：int(1) unsigned zerofill ==》 skip：Integer
     */
    @ApiModelProperty(value = "当处理策略为 节点找不到人时 跳过则为 1，否则为 0")
    private Integer skip;
    /**
     * 当处理策略为 节点找不到人时 管理员处理则为 1，否则为 0
     * default_set_admin：int(1) unsigned zerofill ==》 defaultSetAdmin：Integer
     */
    @ApiModelProperty(value = "当处理策略为 节点找不到人时 管理员处理则为 1，否则为 0")
    private Integer defaultSetAdmin;
    /**
     * 当处理策略为 节点找不到人时 抛出异常处理则为 1，否则为 0
     * error：int(1) unsigned zerofill ==》 error：Integer
     */
    @ApiModelProperty(value = "当处理策略为 节点找不到人时 抛出异常处理则为 1，否则为 0")
    private Integer error;
    /**
     * 备注
     * remarks：varchar(1000) ==》 remarks：String
     */
    @ApiModelProperty(value = "备注")
    private String remarks;





}