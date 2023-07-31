package com.sjz.evaluations.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName AreaVo
 * @Description 行政区划
 * @Author GZL
 * @Date 2023/4/4 19:09
 * @Version 1.0
 **/
@Data
public class AreaVo {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private String id;

    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称")
    private String name;

    /**
     * 代码
     */
    @ApiModelProperty(value = "代码")
    private String code;

    /**
     * 类型 （1：镇  2：区域  3：村）
     */
    @ApiModelProperty(value = "类型（1：镇  2：区域  3：村）")
    private String type;

    /**
     * 父代码
     */
    @ApiModelProperty(value = "父代码")
    private String pcode;

    /** X坐标 */
    @ApiModelProperty(value = "X坐标")
    private String latitude;

    /** Y坐标 */
    @ApiModelProperty(value = "Y坐标")
    private String longitude;
}
