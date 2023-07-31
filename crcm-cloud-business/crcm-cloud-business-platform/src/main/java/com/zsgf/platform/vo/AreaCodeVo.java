package com.zsgf.platform.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName AreaCodeVo
 * @Description 行政区划VO
 * @Author GZL
 * @Date 2023/2/9 17:26
 * @Version 1.0
 **/
@Setter
@Getter
@ToString
@ApiModel("系统行政区划")
public class AreaCodeVo {

    /**
     * 区域代码
     */
    @ApiModelProperty(value = "区域代码")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 业务代码
     */
    @ApiModelProperty(value = "业务代码")
    private String businessCode;
}
