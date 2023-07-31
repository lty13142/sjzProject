package com.zsgf.platform.model.entity.company;

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
 * 企业基本信息对象 tbl_company
 *
 * @author zzyt
 * @date 2023-02-22
 */
@Setter
@Getter
@ToString
@ApiModel("企业基本信息")
@TableName("tbl_company")
public class Company extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @Size(max = 64, message = "id最多输入64个字符")
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @Size(max = 50, message = "用户名最多输入50个字符")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @Size(max = 100, message = "密码最多输入100个字符")
    private String password;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    @Size(max = 255, message = "企业名称最多输入255个字符")
    private String companyName;

    /**
     * 曾用名
     */
    @ApiModelProperty(value = "曾用名")
    @Size(max = 500, message = "曾用名最多输入500个字符")
    private String usedName;

    /**
     * 企业类型
     */
    @ApiModelProperty(value = "企业类型")
    @Size(max = 64, message = "企业类型最多输入64个字符")
    private String companyType;

    /**
     * 企业标签 0-产废，1-经营，2-运输，3-医疗，-1-其他
     */
    @ApiModelProperty(value = "企业标签 0-产废，1-经营，2-运输，3-医疗，-1-其他")
    @Size(max = 64, message = "企业标签最多输入64个字符")
    private String companyLabel;

    /**
     * 单位证件类型
     */
    @ApiModelProperty(value = "单位证件类型")
    @Size(max = 10, message = "单位证件类型最多输入10个字符")
    private String unitCertificateType;

    /**
     * 单位证件号码
     */
    @ApiModelProperty(value = "单位证件号码")
    @Size(max = 64, message = "单位证件号码最多输入64个字符")
    private String unitCertificateCode;

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
     * 街道代码
     */
    @ApiModelProperty(value = "街道代码")
    @Size(max = 40, message = "街道代码最多输入40个字符")
    private String streetCode;

    /**
     * 单位详细地址
     */
    @ApiModelProperty(value = "单位详细地址")
    @Size(max = 1000, message = "单位详细地址最多输入1000个字符")
    private String detailAddress;

    /**
     * 企业规模
     */
    @ApiModelProperty(value = "企业规模")
    @Size(max = 20, message = "企业规模最多输入20个字符")
    private String companyScale;

    /**
     * 企业运行状态
     */
    @ApiModelProperty(value = "企业运行状态")
    @Size(max = 20, message = "企业运行状态最多输入20个字符")
    private String runStatus;

    /**
     * 行业类型
     */
    @ApiModelProperty(value = "行业类型")
    @Size(max = 255, message = "行业类型最多输入255个字符")
    private String industryType;

    /**
     * 登记注册类型
     */
    @ApiModelProperty(value = "登记注册类型")
    @Size(max = 20, message = "登记注册类型最多输入20个字符")
    private String registerType;

    /**
     * 环保隶属关系
     */
    @ApiModelProperty(value = "环保隶属关系")
    @Size(max = 60, message = "环保隶属关系最多输入60个字符")
    private String environmentalProtectionSubordination;

    /**
     * 投产日期
     */
    @ApiModelProperty(value = "投产日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date productionDate;

    /**
     * 法人代表名称
     */
    @ApiModelProperty(value = "法人代表名称")
    @Size(max = 64, message = "法人代表名称最多输入64个字符")
    private String legalRepresentativeName;

    /**
     * 法人代表证件类型
     */
    @ApiModelProperty(value = "法人代表证件类型")
    @Size(max = 64, message = "法人代表证件类型最多输入64个字符")
    private String legalRepresentativeCardType;

    /**
     * 法人代表有效证件号
     */
    @ApiModelProperty(value = "法人代表有效证件号")
    @Size(max = 64, message = "法人代表有效证件号最多输入64个字符")
    private String legalRepresentativeValidIdNo;

    /**
     * 本单位环保部门名称
     */
    @ApiModelProperty(value = "本单位环保部门名称")
    @Size(max = 255, message = "本单位环保部门名称最多输入255个字符")
    private String environmentalProtectionDepartment;

    /**
     * 联系人名称
     */
    @ApiModelProperty(value = "联系人名称")
    @Size(max = 200, message = "联系人名称最多输入200个字符")
    private String contactsName;

    /**
     * 联系人证件类型
     */
    @ApiModelProperty(value = "联系人证件类型")
    @Size(max = 32, message = "联系人证件类型最多输入32个字符")
    private String contactsCardType;

    /**
     * 联系人证件号
     */
    @ApiModelProperty(value = "联系人证件号")
    @Size(max = 64, message = "联系人证件号最多输入64个字符")
    private String contactsCardNo;

    /**
     * 联系人手机号码
     */
    @ApiModelProperty(value = "联系人手机号码")
    @Size(max = 20, message = "联系人手机号码最多输入20个字符")
    private String contactsMobile;

    /**
     * 联系人座机
     */
    @ApiModelProperty(value = "联系人座机")
    @Size(max = 25, message = "联系人座机最多输入25个字符")
    private String contactsPhone;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    @Digits(integer = 9, fraction = 10, message = "经度整数最多9位，小数最多10位")
    private BigDecimal longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    @Digits(integer = 9, fraction = 10, message = "纬度整数最多9位，小数最多10位")
    private BigDecimal latitude;

    /**
     * 单位网站
     */
    @ApiModelProperty(value = "单位网站")
    @Size(max = 255, message = "单位网站最多输入255个字符")
    private String companyWebsite;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 500, message = "备注最多输入500个字符")
    private String remark;

    /**
     * 填写状态
     */
    @ApiModelProperty(value = "填写状态")
    @Size(max = 20, message = "填写状态最多输入20个字符")
    private String fillStatus;

    /**
     * 注册日期
     */
    @ApiModelProperty(value = "注册日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerDate;

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
    @ApiModelProperty(value = "是否删除")
    private Long deleted;

}
