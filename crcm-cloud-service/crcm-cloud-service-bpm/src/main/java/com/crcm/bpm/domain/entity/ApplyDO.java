package com.crcm.bpm.domain.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @ClassName ApplyDO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/3/16:37
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("申请单管理")
@TableName("bpm_apply")
public class ApplyDO extends BaseEntity {
    private static final long serialVersionUID=1L;

    /**
     * 申请编号
     * id：int ==》 id：Long
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 单据编号
     * apply_sn：varchar(32) ==》 applySn：String
     */
    @ApiModelProperty(value = "单据编号")
    private String applySn;
    /**
     * 流程实例编号
     * proc_inst_id：varchar(32) ==》 procInstId：String
     */
    @ApiModelProperty(value = "流程实例编号")
    private String procInstId;
    /**
     * 申请标题
     * apply_title：varchar(64) ==》 applyTitle：String
     */
    @ApiModelProperty(value = "申请标题")
    private String applyTitle;
    /**
     * 申请人ID
     * apply_user_id：int ==》 applyUserId：Integer
     */
    @ApiModelProperty(value = "申请人ID")
    private String applyUserId;
    /**
     * 申请人姓名
     * apply_real_name：varchar(32) ==》 applyRealName：String
     */
    @ApiModelProperty(value = "申请人姓名")
    private String applyRealName;
    /**
     * 申请人部门编号
     * apply_dept_id：int ==》 applyDeptId：Integer
     */
    @ApiModelProperty(value = "申请人部门编号")
    private String applyDeptId;
    /**
     * 申请人公司编号
     * apply_company_id：int ==》 applyCompanyId：Integer
     */
    @ApiModelProperty(value = "申请人公司编号")
    private String applyCompanyId;
    /**
     * 申请人部门编号
     * apply_dept_code：varchar(32) ==》 applyDeptCode：String
     */
    @ApiModelProperty(value = "申请人部门编号")
    private String applyDeptCode;
    /**
     * 申请人部门名称
     * apply_dept_name：varchar(64) ==》 applyDeptName：String
     */
    @ApiModelProperty(value = "申请人部门名称")
    private String applyDeptName;
    /**
     * 申请人公司编号
     * apply_company_code：varchar(32) ==》 applyCompanyCode：String
     */
    @ApiModelProperty(value = "申请人公司编号")
    private String applyCompanyCode;
    /**
     * 申请人公司名称
     * apply_company_name：varchar(64) ==》 applyCompanyName：String
     */
    @ApiModelProperty(value = "申请人公司名称")
    private String applyCompanyName;
    /**
     * 租户编号
     * tenant_id：varchar(32) ==》 tenantId：String
     */
    @ApiModelProperty(value = "租户编号")
    private String tenantId;
    /**
     * modelId
     * model_id：int ==》 modelId：Integer
     */
    @ApiModelProperty(value = "modelId")
    private Long modelId;
    /**
     * 流程编号
     * process_id：int ==》 processId：Integer
     */
    @ApiModelProperty(value = "流程编号")
    private Long processId;
    /**
     * 流程编码
     * process_key：varchar(32) ==》 processKey：String
     */
    @ApiModelProperty(value = "流程编码")
    private String processKey;
    /**
     * 流程名称
     * process_name：varchar(64) ==》 processName：String
     */
    @ApiModelProperty(value = "流程名称")
    private String processName;
    /**
     * 发起流程版本ID
     * definition_id：varchar(32) ==》 definitionId：String
     */
    @ApiModelProperty(value = "发起流程版本ID")
    private String definitionId;
    /**
     * 流程表单key
     * form_key：varchar(32) ==》 formKey：String
     */
    @ApiModelProperty(value = "流程表单key")
    private String formKey;
    /**
     * 来源系统
     * sourceSystem：varchar(32) ==》 sourceSystem：String
     */
    @ApiModelProperty(value = "来源系统")
    private String sourceSystem;
    /**
     * 来源编码
     * platform：varchar(32) ==》 platform：String
     */
    @ApiModelProperty(value = "来源编码")
    private String platform;
    /**
     * 父级申请编码
     * parent_apply_id：int ==》 parentApplyId：Integer
     */
    @ApiModelProperty(value = "父级申请编码")
    private Integer parentApplyId;
    /**
     * 流程状态 1 草稿 2 审批中 3 审批通过 4 审批拒绝  5 已取消
     * apply_status：int ==》 applyStatus：Integer
     */
    @ApiModelProperty(value = "流程状态 1 草稿 2 审批中 3 审批通过 4 审批拒绝  5 已取消 6：挂起")
    private Integer applyStatus;
    /**
     * 备注
     * remarks：varchar(1000) ==》 remarks：String
     */
    @ApiModelProperty(value = "备注")
    private String remarks;
    /**
     * 审批完成时间
     * complete_time：datetime ==》 completeTime：Date
     */
    @ApiModelProperty(value = "审批完成时间")
    private LocalDateTime completeTime;
    /**
     * 申请流程类型
     * flow_type：varchar(16) ==》 flowType：String
     */
    @ApiModelProperty(value = "申请流程类型")
    private String flowType;

}
