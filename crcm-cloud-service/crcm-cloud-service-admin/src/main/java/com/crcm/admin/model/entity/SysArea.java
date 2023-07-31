package com.crcm.admin.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.admin.utils.UtilDic;
import com.crcm.core.constant.SystemConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;

import lombok.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * 区域对象 sys_area
 *
 * @author cb
 * @date 2023-04-04
 */
@Setter
@Getter
@ToString
@ApiModel("区域")
@TableName("sys_area")
public class SysArea extends BaseEntity implements Serializable {
private static final long serialVersionUID = 1L;

    /** 主键id */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键id")
    private String id;

    /** 父id */
    @ApiModelProperty(value = "父id")
    private String pid;

    @ApiModelProperty(value = "父name")
    @TableField(exist = false)
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

    /** 删除标志 */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getTypeCh() {
        if (Objects.isNull(this.getType())){
            return null;
        }
        return UtilDic.getDictName(SystemConstant.AREA_TYPE.CODE, this.getType() + "");
    }

    /** X坐标 */
    @ApiModelProperty(value = "X坐标")
    private String latitude;

    /** Y坐标 */
    @ApiModelProperty(value = "Y坐标")
    private String longitude;
}
