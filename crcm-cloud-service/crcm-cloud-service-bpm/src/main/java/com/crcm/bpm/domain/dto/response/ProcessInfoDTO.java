package com.crcm.bpm.domain.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName ProcessInfoDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/24/10:00
 **/
@Data
@ToString
public class ProcessInfoDTO extends ProcessDTO {

    private static final long serialVersionUID = 6963706717223778411L;
    /**
     * 流程名称
     */
    private String processName;
    /**
     * 流程编号
     */
    private String processNo;
    /**
     * 流程标题生成规则
     */
    private String applyTitleRule;
    /**
     * 模型ID
     */
    private Long modelId;
    /**
     * 模型版本
     */
    private Integer modelVersion;
    /**
     * 所属公司
     */
    private String companyId;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 流程状态
     */
    private Integer processStatus;
    /**
     * 备注
     */
    private String remarks;

}
