package com.sjz.governance.model.entity.key;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 综合治理_重点车辆管理对象 ct_key_vehicle_management
 *
 * @author pengl
 * @date 2023-04-03
 */
@Setter
@Getter
@ToString
@ApiModel("综合治理_重点车辆管理")
@TableName("ct_key_vehicle_management")
public class KeyVehicleManagement extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 车辆品牌
     */
    @ApiModelProperty(value = "车辆品牌")
    @Size(max = 100, message = "车辆品牌最多输入100个字符")
    private String vehicleBrand;

    /**
     * 颜色
     */
    @ApiModelProperty(value = "颜色")
    @Size(max = 100, message = "颜色最多输入100个字符")
    private String color;

    /**
     * 车牌号码
     */
    @ApiModelProperty(value = "车牌号码")
    @Size(max = 100, message = "车牌号码最多输入100个字符")
    private String plateNumber;

    /**
     * 车辆照片
     */
    @ApiModelProperty(value = "车辆照片")
    @Size(max = 100, message = "车辆照片最多输入100个字符")
    private String vehicleUrl;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 100, message = "备注最多输入100个字符")
    private String remark;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

}
