package com.zsgf.platform.model.entity.businessLicense;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 危险废物_经营许可证_03主要设备装置情况对象 pp_wxfw_business_license_main_equipment
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_经营许可证_03主要设备装置情况")
@TableName("pp_wxfw_business_license_main_equipment")
public class BusinessLicenseMainEquipment extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    @Size(max = 100, message = "主键ID最多输入100个字符")
    private String id;

    /**
     * 许可证主键
     */
    @ApiModelProperty(value = "许可证主键")
    @Size(max = 100, message = "许可证主键最多输入100个字符")
    private String licenseId;

    /**
     * 设施表主键
     */
    @ApiModelProperty(value = "设施表主键")
    @Size(max = 100, message = "设施表主键最多输入100个字符")
    private String facilitiesId;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    @Size(max = 100, message = "单位ID最多输入100个字符")
    private String companyId;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    @Size(max = 255, message = "设备名称最多输入255个字符")
    private String equipmentName;

    /**
     * 规格型号
     */
    @ApiModelProperty(value = "规格型号")
    @Size(max = 255, message = "规格型号最多输入255个字符")
    private String specificationModel;

    /**
     * 设计处理能力
     */
    @ApiModelProperty(value = "设计处理能力")
    @Digits(integer = 15, fraction = 4, message = "设计处理能力整数最多15位，小数最多4位")
    private BigDecimal designProcessingCapacity;

    /**
     * 处理能力单位
     */
    @ApiModelProperty(value = "处理能力单位")
    @Size(max = 255, message = "处理能力单位最多输入255个字符")
    private String processingCapacityUnit;
    /**
     * 设备数量
     */
    @ApiModelProperty(value = "设备数量")
    @Digits(integer = 15, fraction = 4, message = "设备数量整数最多15位，小数最多4位")
    private BigDecimal equipmentNumber;
    /**
     * 设备数量单位
     */
    @ApiModelProperty(value = "设备数量单位")
    @Size(max = 255, message = "设备数量单位最多输入255个字符")
    private String equipmentNumberUnit;
    /**
     * 其他参数信息
     */
    @ApiModelProperty(value = "其他参数信息")
    @Size(max = 800, message = "其他参数信息最多输入800个字符")
    private String otherParams;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 800, message = "备注最多输入800个字符")
    private String remark;

    /**
     * 数据来源（省平台数据、本平台数据、其他平台数据，字典：DATA_SOURCE）
     */
    @ApiModelProperty(value = "数据来源（省平台数据、本平台数据、其他平台数据，字典：DATA_SOURCE）")
    private String dataSource;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除", required = true)
    private Integer deleted;
}
