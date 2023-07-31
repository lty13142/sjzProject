package com.zsgf.platform.model.entity.sheets;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 设备确认单详情对象 tbl_equipment_sheet_detail
 *
 * @author gzl
 * @date 2023-03-30
 */
@Setter
@Getter
@ToString
@ApiModel("设备确认单详情")
@TableName("tbl_equipment_sheet_detail")
public class EquipmentSheetDetail extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    @Size(max = 100, message = "企业id最多输入100个字符")
    private String companyId;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    @Size(max = 100, message = "企业名称最多输入100个字符")
    private String companyName;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备类型")
    @Size(max = 2, message = "设备类型最多输入2个字符")
    private String equipmentType;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    @Size(max = 32, message = "设备名称最多输入32个字符")
    private String equipmentName;

    /**
     * 品牌及型号
     */
    @ApiModelProperty(value = "品牌及型号")
    @Size(max = 100, message = "品牌及型号最多输入100个字符")
    private String brandModel;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer equipmentNumber;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    @Size(max = 10, message = "单位最多输入10个字符")
    private String unit;

    /**
     * 序列号
     */
    @ApiModelProperty(value = "序列号")
    private String serialNumber;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除", required = true)
    @NotNull(message = "是否删除不能为空")
    private Integer deleted;

}
