package com.crcm.bpm.domain.dto.response;

import com.crcm.bpm.domain.dto.BaseResponseDTO;
import lombok.Data;

/**
 * @ClassName FormDataTemplateDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/10
 **/
@Data
public class FormDataTemplateDTO extends BaseResponseDTO {
    private static final long serialVersionUID = 7302261020652439802L;

    private String type;

    private String icon;

    private String label;

    private Object options;

    private String model;

    private String key;

    private String rule;
}
