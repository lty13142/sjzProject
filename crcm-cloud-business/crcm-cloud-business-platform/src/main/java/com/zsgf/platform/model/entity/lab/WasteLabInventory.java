package com.zsgf.platform.model.entity.lab;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 实验室台账库存对象 tbl_waste_lab_inventory
 *
 * @author gzl
 * @date 2023-03-23
 */
@Setter
@Getter
@ToString
@ApiModel("实验室台账库存")
@TableName("tbl_waste_lab_inventory")
public class WasteLabInventory extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
    @Size(max = 100, message = "最多输入100个字符")
    private String id;

    /**
     * 公司id
     */
    @ApiModelProperty(value = "公司id")
    @Size(max = 64, message = "公司id最多输入64个字符")
    private String companyId;

    /**
     * 废物名称
     */
    @ApiModelProperty(value = "废物名称", required = true)
    @NotBlank(message = "废物名称不能为空")
    @Size(max = 100, message = "废物名称最多输入100个字符")
    private String wasteName;

    /**
     * 废物类别
     */
    @ApiModelProperty(value = "废物类别", required = true)
    @NotBlank(message = "废物类别不能为空")
    @Size(max = 255, message = "废物类别最多输入255个字符")
    private String wasteType;

    /**
     * 废物类别名称
     */
    @ApiModelProperty(value = "废物类别名称", required = true)
    @NotBlank(message = "废物类别名称不能为空")
    @Size(max = 255, message = "废物类别名称最多输入255个字符")
    private String wasteTypeName;

    /**
     * 废物代码
     */
    @ApiModelProperty(value = "废物代码", required = true)
    @NotBlank(message = "废物代码不能为空")
    @Size(max = 100, message = "废物代码最多输入100个字符")
    private String wasteCode;

    /**
     * 废物代码名称
     */
    @ApiModelProperty(value = "废物代码名称")
    @Size(max = 500, message = "废物代码名称最多输入500个字符")
    private String wasteCodeName;

    /**
     * 库存量
     */
    @ApiModelProperty(value = "库存量", required = true)
    @NotNull(message = "库存量不能为空")
    @Digits(integer = 15, fraction = 4, message = "库存量整数最多15位，小数最多4位")
    private BigDecimal inventoryWeight;

    /**
     * 重量单位(字典：weight_unit)
     */
    @ApiModelProperty(value = "重量单位(字典：weight_unit)")
    @Size(max = 2, message = "重量单位(字典：weight_unit)最多输入2个字符")
    private String unit;

    /**
     * 最新操作人
     */
    @ApiModelProperty(value = "最新操作人")
    @Size(max = 100, message = "最新操作人最多输入100个字符")
    private String username;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除", required = true)
    @NotNull(message = "是否删除不能为空")
    private Integer deleted;

}
