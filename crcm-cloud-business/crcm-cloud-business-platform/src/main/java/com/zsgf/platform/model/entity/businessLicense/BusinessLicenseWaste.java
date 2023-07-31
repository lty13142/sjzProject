package com.zsgf.platform.model.entity.businessLicense;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 危险废物_经营许可证_04废物信息对象 pp_wxfw_business_license_waste
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_经营许可证_04废物信息")
@TableName("pp_wxfw_business_license_waste")
public class BusinessLicenseWaste extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键ID")
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
     * 名录版本
     */
    @ApiModelProperty(value = "名录版本")
    @Size(max = 100, message = "名录版本最多输入100个字符")
    private String directoryVersion;

    /**
     * 废物大类
     */
    @ApiModelProperty(value = "废物大类")
    @Size(max = 255, message = "废物大类最多输入255个字符")
    private String wasteType;

    /**
     * 废物大类名称
     */
    @ApiModelProperty(value = "废物大类名称")
    @Size(max = 200, message = "废物大类名称最多输入200个字符")
    private String wasteTypeName;

    /**
     * 废物小类
     */
    @ApiModelProperty(value = "废物小类")
    @Size(max = 255, message = "废物小类最多输入255个字符")
    private String wasteCode;

    /**
     * 废物小类名称
     */
    @ApiModelProperty(value = "废物小类名称")
    @Size(max = 500, message = "废物小类名称最多输入500个字符")
    private String wasteCodeName;

    /**
     * 省代码
     */
    @ApiModelProperty(value = "省代码")
    @Size(max = 40, message = "省代码最多输入40个字符")
    private String provincialCode;

    /**
     * 市代码
     */
    @ApiModelProperty(value = "市代码")
    @Size(max = 40, message = "市代码最多输入40个字符")
    private String cityCode;

    /**
     * 区代码
     */
    @ApiModelProperty(value = "区代码")
    @Size(max = 40, message = "区代码最多输入40个字符")
    private String zoneCode;

    /**
     * 数据来源（省平台数据、本平台数据、其他平台数据，字典：DATA_SOURCE）
     */
    @ApiModelProperty(value = "数据来源（省平台数据、本平台数据、其他平台数据，字典：DATA_SOURCE）")
    @Size(max = 2, message = "数据来源最多输入2个字符")
    private String dataSource;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;
}
