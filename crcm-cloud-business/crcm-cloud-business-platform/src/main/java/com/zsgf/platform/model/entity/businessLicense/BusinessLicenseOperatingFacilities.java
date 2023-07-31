package com.zsgf.platform.model.entity.businessLicense;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 危险废物_经营许可证_02经营设施信息对象 pp_wxfw_business_license_operating_facilities
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_经营许可证_02经营设施信息")
@TableName("pp_wxfw_business_license_operating_facilities")
public class BusinessLicenseOperatingFacilities extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    @Size(max = 100, message = "主键ID最多输入100个字符")
    private String id;

    /**
     * 许可证表ID
     */
    @ApiModelProperty(value = "许可证表ID")
    @Size(max = 100, message = "许可证表ID最多输入100个字符")
    private String licenseId;
    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    @Size(max = 100, message = "单位ID最多输入100个字符")
    private String companyId;
    /**
     * 登记时间
     */
    @ApiModelProperty(value = "登记时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerTime;
    /**
     * 设施名称
     */
    @ApiModelProperty(value = "设施名称")
    @Size(max = 400, message = "设施名称最多输入400个字符")
    private String facilityName;
    /**
     * 处置方式
     */
    @ApiModelProperty(value = "处置方式")
    @Size(max = 255, message = "处置方式最多输入255个字符")
    private String disposalMajor;
    /**
     * 处置方式小类
     */
    @ApiModelProperty(value = "处置方式小类")
    @Size(max = 255, message = "处置方式小类最多输入255个字符")
    private String disposalSmall;
    /**
     * 设计能力
     */
    @ApiModelProperty(value = "设计能力")
    @Digits(integer = 15, fraction = 4, message = "设计能力整数最多15位，小数最多4位")
    private BigDecimal designCapability;
    /**
     * 核准规模
     */
    @ApiModelProperty(value = "核准规模")
    @Digits(integer = 15, fraction = 4, message = "核准规模整数最多15位，小数最多4位")
    private BigDecimal approvedScale;
    /**
     * 计量单位
     */
    @ApiModelProperty(value = "计量单位")
    @Size(max = 255, message = "计量单位最多输入255个字符")
    private String measurementUnit;
    /**
     * 是否限量
     */
    @ApiModelProperty(value = "是否限量")
    @Size(max = 255, message = "是否限量最多输入255个字符")
    private String limitFlag;

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
