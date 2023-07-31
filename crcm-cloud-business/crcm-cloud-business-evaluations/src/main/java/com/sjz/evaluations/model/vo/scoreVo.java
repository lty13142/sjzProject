package com.sjz.evaluations.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yzw
 * @data 2023/4/10
 * @apiNote
 */
@Data
public class scoreVo {

    private String id;

    private String name;
    private Integer score;

    /** X坐标 */
    @ApiModelProperty(value = "X坐标")
    private String latitude;

    /** Y坐标 */
    @ApiModelProperty(value = "Y坐标")
    private String longitude;
}
