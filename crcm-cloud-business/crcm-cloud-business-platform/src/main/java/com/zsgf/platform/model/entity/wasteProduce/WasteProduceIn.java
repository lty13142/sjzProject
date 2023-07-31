package com.zsgf.platform.model.entity.wasteProduce;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.util.Date;

/**
 * 产废入库信息对象 tbl_waste_produce_in
 *
 * @author zzyt
 * @date 2023-02-22
 */
@Setter
@Getter
@ToString
@ApiModel("产废入库信息")
@TableName("tbl_waste_produce_in")
public class WasteProduceIn extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @Size(max = 32, message = "id最多输入32个字符")
    private String id;

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id", required = true)
    @NotBlank(message = "企业id不能为空")
    @Size(max = 32, message = "企业id最多输入32个字符")
    private String companyId;

    /**
     * 批次号
     */
    @ApiModelProperty(value = "批次号")
    @Size(max = 255, message = "批次号最多输入255个字符")
    private String batchNo;

    /**
     * 单个批次号
     */
    @ApiModelProperty(value = "单个批次号")
    @Size(max = 255, message = "单个批次号最多输入255个字符")
    private String detailBatchNo;

    /**
     * 名录版本
     */
    @ApiModelProperty(value = "名录版本")
    @Size(max = 10, message = "名录版本最多输入10个字符")
    private String directoryVersion;

    /**
     * 废物名称
     */
    @ApiModelProperty(value = "废物名称", required = true)
    @NotBlank(message = "废物名称不能为空")
    @Size(max = 100, message = "废物名称最多输入100个字符")
    private String wasteName;

    /**
     * 废物种类 1：危废 2：固废 3:医废
     */
    @ApiModelProperty(value = "废物种类 1：危废 2：固废 3:医废", required = true)
    @NotNull(message = "废物种类 1：危废 2：固废 3:医废不能为空")
    private Long wasteCategory;

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
     * 危险废物形态
     */
    @ApiModelProperty(value = "危险废物形态")
    @Size(max = 100, message = "危险废物形态最多输入100个字符")
    private String shape;

    /**
     * 入库人
     */
    @ApiModelProperty(value = "入库人")
    @Size(max = 32, message = "入库人最多输入32个字符")
    private String inPerson;

    /**
     * 重量
     */
    @ApiModelProperty(value = "重量", required = true)
    @NotNull(message = "重量不能为空")
    @Digits(integer = 15, fraction = 4, message = "重量整数最多15位，小数最多4位")
    private BigDecimal weight;

    /**
     * 重量单位(字典：weight_unit)
     */
    @ApiModelProperty(value = "重量单位(字典：weight_unit)")
    @Size(max = 2, message = "重量单位(字典：weight_unit)最多输入2个字符")
    private String unit;

    /**
     * 包装规格(字典：packageType)
     */
    @ApiModelProperty(value = "包装规格(字典：packageType)")
    @Size(max = 2, message = "包装规格(字典：packageType)最多输入2个字符")
    private String packageType;

    /**
     * 包装桶个数
     */
    @ApiModelProperty(value = "包装桶个数")
    private Long bucNum;

    /**
     * 是否手动输入（0：否，1：是）
     */
    @ApiModelProperty(value = "是否手动输入（0：否，1：是）")
    private Long manualInput;

    /**
     * 库存重量
     */
    @ApiModelProperty(value = "库存重量", required = true)
    @NotNull(message = "库存重量不能为空")
    @Digits(integer = 15, fraction = 4, message = "库存重量整数最多15位，小数最多4位")
    private BigDecimal inventoryWeight;

    /**
     * 期限日期
     */
    @ApiModelProperty(value = "期限日期", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "期限日期不能为空")
    private Date limitDate;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Long status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 255, message = "备注最多输入255个字符")
    private String remark;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除", required = true)
    private Integer deleted;

}
