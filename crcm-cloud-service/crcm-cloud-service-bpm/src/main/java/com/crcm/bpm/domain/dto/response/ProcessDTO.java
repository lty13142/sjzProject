package com.crcm.bpm.domain.dto.response;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName ProcessDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/24/10:00
 **/
@Data
@ToString
public class ProcessDTO {

    private static final long serialVersionUID = 6226380949612837849L;

    private Long id;

    private String processKey;

    private String processName;

    private Long processMenuId;

    private String processAbbr;

    private String companyId;

    private String companyCode;

    private String tenantId;

    private Integer sort;

    private Long processDetailId;

    private Integer processType;

    private Integer processStatus;

    private String remarks;

    private Integer validState;

    private String operatorId;

    private String operatorName;

}
