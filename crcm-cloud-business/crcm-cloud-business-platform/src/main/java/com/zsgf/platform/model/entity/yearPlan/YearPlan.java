package com.zsgf.platform.model.entity.yearPlan;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * 危险废物_管理计划_01基本信息对象 pp_wxfw_year_plan
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_管理计划_01基本信息")
@TableName("pp_wxfw_year_plan")
public class YearPlan implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Size(max = 100, message = "主键最多输入100个字符")
    private String id;

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    @Size(max = 100, message = "企业id最多输入100个字符")
    private String dwid;

    /**
     * 管理计划年份
     */
    @ApiModelProperty(value = "管理计划年份")
    @Size(max = 100, message = "管理计划年份最多输入100个字符")
    private String year;

    /**
     * 制定日期
     */
    @ApiModelProperty(value = "制定日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date zdrq;

    /**
     * 计划期限开始
     */
    @ApiModelProperty(value = "计划期限开始")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jhqxks;

    /**
     * 计划期限结束
     */
    @ApiModelProperty(value = "计划期限结束")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jhqxjs;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    @Size(max = 200, message = "单位名称最多输入200个字符")
    private String dwmc;

    /**
     * 单位注册地址
     */
    @ApiModelProperty(value = "单位注册地址")
    @Size(max = 1000, message = "单位注册地址最多输入1000个字符")
    private String dwzcadress;

    /**
     * 生产设施地址
     */
    @ApiModelProperty(value = "生产设施地址")
    @Size(max = 1000, message = "生产设施地址最多输入1000个字符")
    private String scssadress;

    /**
     * 法定代表人
     */
    @ApiModelProperty(value = "法定代表人")
    @Size(max = 200, message = "法定代表人最多输入200个字符")
    private String dbr;

    /**
     * 行业类别代码
     */
    @ApiModelProperty(value = "行业类别代码")
    @Size(max = 200, message = "行业类别代码最多输入200个字符")
    private String hylbdm;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    @Size(max = 200, message = "联系人最多输入200个字符")
    private String lxr;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    @Size(max = 200, message = "联系电话最多输入200个字符")
    private String phone;

    /**
     * 占地面积
     */
    @ApiModelProperty(value = "占地面积")
    @Digits(integer = 15, fraction = 4, message = "占地面积整数最多15位，小数最多4位")
    private BigDecimal area;

    /**
     * 单位网址
     */
    @ApiModelProperty(value = "单位网址")
    @Size(max = 200, message = "单位网址最多输入200个字符")
    private String dwurl;

    /**
     * 职工人数
     */
    @ApiModelProperty(value = "职工人数")
    @Digits(integer = 15, fraction = 4, message = "职工人数整数最多15位，小数最多4位")
    private BigDecimal zgrs;

    /**
     * 环保部门负责人
     */
    @ApiModelProperty(value = "环保部门负责人")
    @Size(max = 200, message = "环保部门负责人最多输入200个字符")
    private String hbbmfzr;

    /**
     * 传真
     */
    @ApiModelProperty(value = "传真")
    @Size(max = 200, message = "传真最多输入200个字符")
    private String cz;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value = "电子邮箱")
    @Size(max = 200, message = "电子邮箱最多输入200个字符")
    private String email;

    /**
     * 邮编
     */
    @ApiModelProperty(value = "邮编")
    @Size(max = 200, message = "邮编最多输入200个字符")
    private String postcode;

    /**
     * 总投资
     */
    @ApiModelProperty(value = "总投资")
    @Digits(integer = 15, fraction = 4, message = "总投资整数最多15位，小数最多4位")
    private BigDecimal tzcount;

    /**
     * 总产值
     */
    @ApiModelProperty(value = "总产值")
    @Digits(integer = 15, fraction = 4, message = "总产值整数最多15位，小数最多4位")
    private BigDecimal czcount;

    /**
     * 状态(3-审核完毕;4-作废 )
     */
    @ApiModelProperty(value = "状态(3-审核完毕;4-作废 )")
    @Size(max = 100, message = "状态(3-审核完毕;4-作废 )最多输入100个字符")
    private String state;

    /**
     * 是否是最新
     */
    @ApiModelProperty(value = "是否是最新")
    @Size(max = 100, message = "是否是最新最多输入100个字符")
    private String isnew;

    /**
     * 备案标识
     */
    @ApiModelProperty(value = "备案标识")
    @Size(max = 200, message = "备案标识最多输入200个字符")
    private String baflag;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 1000, message = "备注最多输入1000个字符")
    private String remark;

    /**
     * 管理计划填写人
     */
    @ApiModelProperty(value = "管理计划填写人")
    @Size(max = 200, message = "管理计划填写人最多输入200个字符")
    private String gljhtxr;

    /**
     * 填写人联系电话
     */
    @ApiModelProperty(value = "填写人联系电话")
    @Size(max = 200, message = "填写人联系电话最多输入200个字符")
    private String bmlxdh;

    /**
     * 变更原因
     */
    @ApiModelProperty(value = "变更原因")
    @Size(max = 1000, message = "变更原因最多输入1000个字符")
    private String bgyy;

    /**
     * 所属省
     */
    @ApiModelProperty(value = "所属省")
    @Size(max = 40, message = "所属省最多输入40个字符")
    private String sssheng;

    /**
     * 所属市
     */
    @ApiModelProperty(value = "所属市")
    @Size(max = 40, message = "所属市最多输入40个字符")
    private String ssshi;

    /**
     * 所属区
     */
    @ApiModelProperty(value = "所属区")
    @Size(max = 40, message = "所属区最多输入40个字符")
    private String ssqu;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyrq;

}
