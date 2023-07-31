package com.crcm.bpm.domain.vo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zwj
 * @date 2020-11-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="流程按钮", description="")
public class ModelButtonVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String buttonName;

    private Integer buttonValue;
}