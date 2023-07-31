package com.zsgf.platform.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName AreaCodeQueryDTO
 * @Description 行政区划查询DTO
 * @Author GZL
 * @Date 2023/2/9 17:04
 * @Version 1.0
 **/
@Setter
@Getter
@ToString
public class AreaCodeQueryDTO {

    /**
     * 级别
     */
    @ApiModelProperty(value = "级别")
    private String lev;

    /**
     * 父级代码
     */
    @ApiModelProperty(value = "父级代码")
    private String pcode;

    /**
     * 是否为最后一级
     */
    @ApiModelProperty(value = "是否为最后一级")
    private String islast;
}
