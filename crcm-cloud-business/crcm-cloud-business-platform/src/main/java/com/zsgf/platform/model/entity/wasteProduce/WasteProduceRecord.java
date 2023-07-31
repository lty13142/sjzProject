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
 * 产废产生台账对象 tbl_waste_produce_record
 *
 * @author zzyt
 * @date 2023-02-22
 */
@Setter
@Getter
@ToString
@ApiModel("产废产生台账")
@TableName("tbl_waste_produce_record")
public class WasteProduceRecord extends BaseEntity implements Serializable {
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
     * 记录日期
     */
    @ApiModelProperty(value = "记录日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recordDate;

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
     * 上期末存储量
     */
    @ApiModelProperty(value = "上期末存储量")
    @Digits(integer = 15, fraction = 4, message = "上期末存储量整数最多15位，小数最多4位")
    private BigDecimal totalStorageLastMonthEnd;

    /**
     * 当期产生量
     */
    @ApiModelProperty(value = "当期产生量")
    @Digits(integer = 15, fraction = 4, message = "当期产生量整数最多15位，小数最多4位")
    private BigDecimal totalProduceThisMonth;

    /**
     * 当期自行处置量
     */
    @ApiModelProperty(value = "当期自行处置量")
    @Digits(integer = 15, fraction = 4, message = "当期自行处置量整数最多15位，小数最多4位")
    private BigDecimal totalSelfDealThisMonth;

    /**
     * 当期委外处置量
     */
    @ApiModelProperty(value = "当期委外处置量")
    @Digits(integer = 15, fraction = 4, message = "当期委外处置量整数最多15位，小数最多4位")
    private BigDecimal totalOtherDealThisMonth;

    /**
     * 本期末存储量
     */
    @ApiModelProperty(value = "本期末存储量")
    @Digits(integer = 15, fraction = 4, message = "本期末存储量整数最多15位，小数最多4位")
    private BigDecimal totalStorageThisMonthEnd;

    /**
     * 单个批次号
     */
    @ApiModelProperty(value = "单个批次号")
    @Size(max = 100, message = "单个批次号最多输入100个字符")
    private String detailBatchNo;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除", required = true)
    private Integer deleted;

}
