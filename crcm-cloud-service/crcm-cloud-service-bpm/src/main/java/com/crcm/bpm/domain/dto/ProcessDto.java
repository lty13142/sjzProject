package com.crcm.bpm.domain.dto;


import com.crcm.bpm.domain.entity.ProcessDO;
import lombok.Data;


/**
 * @author
 */
@Data
public class ProcessDto extends ProcessDO {

    /**
     * 模板绑定表单类型
     */
    private Integer formType;

    /**
     * 模板绑定表单id
     */
    private String formId;

    /**
     * 自定表单标识code
     */
    private String formCode;


    /**
     * 是否被收藏
     */
    private Boolean isCollection = false;
}
