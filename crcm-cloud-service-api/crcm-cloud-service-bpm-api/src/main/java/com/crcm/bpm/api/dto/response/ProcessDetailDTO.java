package com.crcm.bpm.api.dto.response;

import com.crcm.bpm.api.dto.BaseResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ProcessDetailDTO
 * @Description：基础响应DTO
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/2020/9/24/14:03
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ProcessDetailDTO extends BaseResponseDTO {
    private static final long serialVersionUID = 4082391968531869186L;
    /**
     * 流程ID
     */
    private Long id;
    /**
     * 流程编号
     */
    private String processNo;
    /**
     * 流程名称
     */
    private String processName;
    /**
     * 所属公司
     */
    private Long companyId;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 排序
     */
    private Integer sortOrder;
    /**
     * 流程状态
     */
    private Integer processStatus;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 0-正常，1-删除
     */
    private Integer  deleted;
    /**
     * 流程模型ID
     */
    private Long modelId;
    /**
     * 申请标题规则
     */
    private String applyTitleRule;
    /**
     * 流程到期时间
     */
    private Date applyDueDate;
    /**
     * 图标
     */
    private String icon;
}
