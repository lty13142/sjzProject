package com.crcm.bpm.domain.dto.response;

import com.crcm.bpm.domain.dto.BaseResponseDTO;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName ApplyDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/10
 **/
@Data
@ToString
public class ApplyDTO extends BaseResponseDTO {

    private static final long serialVersionUID = 2655376422940194358L;

    private Long id;
    /**
     * 单据编号
     */
    private String applySn;
    /**
     * 流程实例编号
     */
    private String procInstId;
    /**
     * 申请标题
     */
    private String applyTitle;
    /**
     * 申请人工号
     */
    private String applyUserId;
    /**
     * 申请人姓名
     */
    private String applyRealName;
    /**
     * 部门编号
     */
    private String applyDeptId;
    /**
     * 申请部门工号
     */
    private String applyDeptCode;
    /**
     * 申请部门名称
     */
    private String applyDeptName;
    /**
     * 申请人公司编号
     */
    private String applyCompanyId;
    /**
     * 申请人公司编码
     */
    private String applyCompanyCode;
    /**
     * 申请人公司名称
     */
    private String applyCompanyName;
    /**
     * 租户编号
     */
    private String tenantId;
    /**
     * 流程编号
     */
    private Long processId;
    /**
     * 流程编码
     */
    private String processKey;
    /**
     * 流程名称
     */
    private String processName;
    /**
     * 发起流程版本ID
     */
    private String definitionId;

    private String formKey;
    /**
     * 来源系统
     */
    private String sourceSystem;
    /**
     * 来源编码
     */
    private String platform;
    /**
     * 父级申请编码
     */
    private Long parentApplyId;
    /**
     * 流程状态 1 草稿 2 审批中 3 审批通过 4 审批拒绝  5 已取消
     */
    private Integer applyStatus;
    /**
     * 备注
     */
    private String remarks;
}
