package com.crcm.admin.api.dto.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 区域对象 sys_area
 *
 * @author cb
 * @date 2023-04-04
 */
@Setter
@Getter
@ToString
public class AreaDTO implements Serializable {
private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    private String id;

    /** 父id */
    @ApiModelProperty(value = "父id")
    private String pid;

    @ApiModelProperty(value = "父name")
    private String pname;


    /** 父代码 */
    @ApiModelProperty(value = "父代码")
    private String pcode;

    /** 区域名称 */
    @ApiModelProperty(value = "区域名称")
    private String name;

    /** 代码 */
    @ApiModelProperty(value = "代码")
    private String code;

    /** 类型 */
    @ApiModelProperty(value = "类型（1：镇  2：区域  3：村）")
    private String type;

    /** 户籍人口数 */
    @ApiModelProperty(value = "户籍人口数")
    private String registeredPopulation;

    /** 常驻人口数 */
    @ApiModelProperty(value = "常驻人口数")
    private String residentPopulation;

    /** X坐标 */
    @ApiModelProperty(value = "X坐标")
    private String latitude;

    /** Y坐标 */
    @ApiModelProperty(value = "Y坐标")
    private String longitude;

}
