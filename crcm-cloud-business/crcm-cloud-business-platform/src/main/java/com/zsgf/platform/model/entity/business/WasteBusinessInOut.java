package com.zsgf.platform.model.entity.business;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 经营出入场记录对象 tbl_waste_business_in_out
 *
 * @author gzl
 * @date 2023-02-22
 */
@Setter
@Getter
@ToString
@ApiModel("经营出入场记录")
@TableName("tbl_waste_business_in_out")
public class WasteBusinessInOut extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @Size(max = 100, message = "最多输入100个字符")
    private String id;

    /**
     * 单位id
     */
    @ApiModelProperty(value = "单位id")
    @Size(max = 100, message = "单位id最多输入100个字符")
    private String companyId;

    /**
     * 运输单号
     */
    @ApiModelProperty(value = "运输单号")
    @Size(max = 30, message = "运输单号最多输入30个字符")
    private String transportNo;

    /**
     * 经办人
     */
    @ApiModelProperty(value = "经办人")
    @Size(max = 100, message = "经办人最多输入100个字符")
    private String createUser;

    /**
     * 司机姓名
     */
    @ApiModelProperty(value = "司机姓名")
    @Size(max = 100, message = "司机姓名最多输入100个字符")
    private String driverName;

    /**
     * 司机电话
     */
    @ApiModelProperty(value = "司机电话")
    @Size(max = 100, message = "司机电话最多输入100个字符")
    private String driverPhone;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    @Size(max = 50, message = "车牌号最多输入50个字符")
    private String vehiclesCode;

    /**
     * 净重
     */
    @ApiModelProperty(value = "净重")
    @Digits(integer = 8, fraction = 2, message = "净重整数最多8位，小数最多2位")
    private BigDecimal netWeight;

    /**
     * 毛重
     */
    @ApiModelProperty(value = "毛重")
    @Digits(integer = 8, fraction = 2, message = "毛重整数最多8位，小数最多2位")
    private BigDecimal crossWeight;

    /**
     * 皮重
     */
    @ApiModelProperty(value = "皮重")
    @Digits(integer = 8, fraction = 2, message = "皮重整数最多8位，小数最多2位")
    private BigDecimal tareWeight;

    /**
     * 重量单位
     */
    @ApiModelProperty(value = "重量单位")
    @Size(max = 5, message = "重量单位最多输入5个字符")
    private String unit;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除", required = true)
    private Integer deleted;

}
