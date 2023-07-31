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
import java.time.LocalDate;

/**
 * 危险废物_经营许可证_01基本信息对象 pp_wxfw_business_license
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_经营许可证_01基本信息")
@TableName("pp_wxfw_business_license")
public class BusinessLicense extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键ID")
    @Size(max = 100, message = "主键ID最多输入100个字符")
    private String id;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    @Size(max = 100, message = "单位ID最多输入100个字符")
    private String companyId;

    /**
     * 核准经营方式
     */
    @ApiModelProperty(value = "核准经营方式")
    @Size(max = 255, message = "核准经营方式最多输入255个字符")
    private String operateWay;

    /**
     * 经营规模
     */
    @ApiModelProperty(value = "经营规模")
    @Digits(integer = 15, fraction = 4, message = "经营规模整数最多15位，小数最多4位")
    private BigDecimal operateScale;

    /**
     * 发证机构
     */
    @ApiModelProperty(value = "发证机构")
    @Size(max = 255, message = "发证机构最多输入255个字符")
    private String certAuth;

    /**
     * 发证机构级别
     */
    @ApiModelProperty(value = "发证机构级别")
    @Size(max = 255, message = "发证机构级别最多输入255个字符")
    private String certAuthLev;

    /**
     * 许可证编号
     */
    @ApiModelProperty(value = "许可证编号")
    @Size(max = 255, message = "许可证编号最多输入255个字符")
    private String licenseCode;

    /**
     * 初次发证日期
     */
    @ApiModelProperty(value = "初次发证日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate firstTime;

    /**
     * 发证日期
     */
    @ApiModelProperty(value = "发证日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate certTime;

    /**
     * 许可证有效期开始
     */
    @ApiModelProperty(value = "许可证有效期开始")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validStart;

    /**
     * 许可证有效期结束
     */
    @ApiModelProperty(value = "许可证有效期结束")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validEnd;

    /**
     * 经营设施地址
     */
    @ApiModelProperty(value = "经营设施地址")
    @Size(max = 255, message = "经营设施地址最多输入255个字符")
    private String facilitiesAddress;

    /**
     * 经营单位类别
     */
    @ApiModelProperty(value = "经营单位类别")
    @Size(max = 255, message = "经营单位类别最多输入255个字符")
    private String companyType;

    /**
     * 许可证类型
     */
    @ApiModelProperty(value = "许可证类型")
    @Size(max = 255, message = "许可证类型最多输入255个字符")
    private String permitType;

    /**
     * 作废日期
     */
    @ApiModelProperty(value = "作废日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate nullifyTime;

    /**
     * 是否限制区域
     */
    @ApiModelProperty(value = "是否限制区域")
    @Size(max = 255, message = "是否限制区域最多输入255个字符")
    private String limitArea;

    /**
     * 是否限制单位类型
     */
    @ApiModelProperty(value = "是否限制单位类型")
    @Size(max = 255, message = "是否限制单位类型最多输入255个字符")
    private String limitCompanyType;

    /**
     * 申报状态 （0-填写;1-审核;2-被退回;3-审核完毕;4-作废;5-续证;6-过期;7-暂停）
     */
    @ApiModelProperty(value = "申报状态 （0-填写;1-审核;2-被退回;3-审核完毕;4-作废;5-续证;6-过期;7-暂停）")
    @Size(max = 100, message = "申报状态 （0-填写;1-审核;2-被退回;3-审核完毕;4-作废;5-续证;6-过期;7-暂停）最多输入100个字符")
    private String state;

    /**
     * 核准经营危险废物类别
     */
    @ApiModelProperty(value = "核准经营危险废物类别")
    @Size(max = 3000, message = "核准经营危险废物类别最多输入3000个字符")
    private String wasteType;

    /**
     * 数据来源（省平台数据、本平台数据、其他平台数据，字典：DATA_SOURCE）
     */
    @ApiModelProperty(value = "数据来源（省平台数据、本平台数据、其他平台数据，字典：DATA_SOURCE）")
    @Size(max = 2, message = "数据来源最多输入2个字符")
    private String dataSource;

    /**
     * 经营规模附件
     */
    @ApiModelProperty(value = "经营规模附件")
    @Size(max = 100, message = "经营规模附件最多输入100个字符")
    private String operateScaleFile;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;
}
