package com.zsgf.platform.model.entity.bill;

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
 * 电子联单废物信息对象 tbl_bill_waste
 *
 * @author zzyt
 * @date 2023-02-22
 */
@Setter
@Getter
@ToString
@ApiModel("电子联单废物信息")
@TableName("tbl_bill_waste")
public class BillWaste extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @Size(max = 200, message = "主键最多输入200个字符")
    private String id;

    /**
     * 电子联单主键
     */
    @ApiModelProperty(value = "电子联单主键")
    @Size(max = 200, message = "电子联单主键最多输入200个字符")
    private String billId;

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
     * 危险特性 腐蚀性C 毒性T 易燃性I 反应性R 感染性 In
     */
    @ApiModelProperty(value = "危险特性 腐蚀性C 毒性T 易燃性I 反应性R 感染性 In")
    @Size(max = 100, message = "危险特性 腐蚀性C 毒性T 易燃性I 反应性R 感染性 In最多输入100个字符")
    private String hazards;

    /**
     * 主要成分
     */
    @ApiModelProperty(value = "主要成分", required = true)
    @NotBlank(message = "主要成分不能为空")
    @Size(max = 255, message = "主要成分最多输入255个字符")
    private String component;

    /**
     * 危险废物形态
     */
    @ApiModelProperty(value = "危险废物形态")
    @Size(max = 100, message = "危险废物形态最多输入100个字符")
    private String shape;

    /**
     * 危险废物产生环节
     */
    @ApiModelProperty(value = "危险废物产生环节")
    @Size(max = 200, message = "危险废物产生环节最多输入200个字符")
    private String origin;

    /**
     * 禁忌与应急措施
     */
    @ApiModelProperty(value = "禁忌与应急措施")
    @Size(max = 1000, message = "禁忌与应急措施最多输入1000个字符")
    private String careful;

    /**
     * 处置方式大类
     */
    @ApiModelProperty(value = "处置方式大类")
    @Size(max = 100, message = "处置方式大类最多输入100个字符")
    private String disposalMajor;

    /**
     * 处置方式小类
     */
    @ApiModelProperty(value = "处置方式小类")
    @Size(max = 100, message = "处置方式小类最多输入100个字符")
    private String disposalSmall;

    /**
     * 危险类别（数据字典）
     */
    @ApiModelProperty(value = "危险类别（数据字典）")
    @Size(max = 255, message = "危险类别（数据字典）最多输入255个字符")
    private String dangerousType;

    /**
     * 数据来源，出库等
     */
    @ApiModelProperty(value = "数据来源，出库等")
    @Size(max = 10, message = "数据来源，出库等最多输入10个字符")
    private String dataSource;

    /**
     * 计划转移数量
     */
    @ApiModelProperty(value = "计划转移数量")
    @Digits(integer = 15, fraction = 4, message = "计划转移数量整数最多15位，小数最多4位")
    private BigDecimal planTransferWeight;

    /**
     * 转移数量
     */
    @ApiModelProperty(value = "转移数量")
    @Digits(integer = 15, fraction = 4, message = "转移数量整数最多15位，小数最多4位")
    private BigDecimal transferWeight;

    /**
     * 废物数量单位
     */
    @ApiModelProperty(value = "废物数量单位")
    @Size(max = 2, message = "废物数量单位最多输入2个字符")
    private String unit;

    /**
     * 包装方式
     */
    @ApiModelProperty(value = "包装方式")
    @Size(max = 2, message = "包装方式最多输入2个字符")
    private String packageType;

    /**
     * 包装数量
     */
    @ApiModelProperty(value = "包装数量")
    private Long bucNum;

    /**
     * 包装方式介绍
     */
    @ApiModelProperty(value = "包装方式介绍")
    @Size(max = 200, message = "包装方式介绍最多输入200个字符")
    private String packageTypeIntroduce;

    /**
     * 外运目的
     */
    @ApiModelProperty(value = "外运目的")
    @Size(max = 200, message = "外运目的最多输入200个字符")
    private String outTransportPurpose;

    /**
     * 确认废物数量
     */
    @ApiModelProperty(value = "确认废物数量")
    @Digits(integer = 15, fraction = 4, message = "确认废物数量整数最多15位，小数最多4位")
    private BigDecimal confirmWeight;

    /**
     * 处理意见
     */
    @ApiModelProperty(value = "处理意见")
    @Size(max = 200, message = "处理意见最多输入200个字符")
    private String handleOpinion;

    /**
     * 是否存在重大差异
     */
    @ApiModelProperty(value = "是否存在重大差异")
    @Size(max = 200, message = "是否存在重大差异最多输入200个字符")
    private String significantDifferences;

    /** 重大差异简述 */
    @ApiModelProperty(value = "重大差异简述")
    @Size(max = 200, message = "重大差异简述最多输入200个字符")
    private String significantDifferencesResume;

    /**
     * 产废单位备注
     */
    @ApiModelProperty(value = "产废单位备注")
    @Size(max = 2000, message = "产废单位备注最多输入2000个字符")
    private String produceCompanyRemark;

    /**
     * 接收单位备注
     */
    @ApiModelProperty(value = "接收单位备注")
    @Size(max = 2000, message = "接收单位备注最多输入2000个字符")
    private String receiveCompanyRemark;

    /**
     * 共享标识 省平台数据、本平台数据、其他平台数据
     */
    @ApiModelProperty(value = "共享标识 省平台数据、本平台数据、其他平台数据")
    @Size(max = 2, message = "共享标识 省平台数据、本平台数据、其他平台数据最多输入2个字符")
    private String dataFlag;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除", required = true)
    private Integer deleted;

}
