package com.zsgf.platform.model.entity.businessReport;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * 危险废物_经营年报_01基本信息对象 pp_wxfw_business_year_report
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_经营年报_01基本信息")
@TableName("pp_wxfw_business_year_report")
public class BusinessYearReport extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @Size(max = 100, message = "主键ID最多输入100个字符")
    private String id;

    /**
     * 年份
     */
    @ApiModelProperty(value = "年份")
    @Size(max = 100, message = "年份最多输入100个字符")
    private String nf;

    /**
     * 单位id
     */
    @ApiModelProperty(value = "单位id")
    @Size(max = 100, message = "单位id最多输入100个字符")
    private String dwid;

    /**
     * 申报状态（0-填写中，1-审核中，2-被退回，3-审核完成，4-作废）
     */
    @ApiModelProperty(value = "申报状态（0-填写中，1-审核中，2-被退回，3-审核完成，4-作废）")
    @Size(max = 100, message = "申报状态（0-填写中，1-审核中，2-被退回，3-审核完成，4-作废）最多输入100个字符")
    private String state;

    /**
     * 总产值（万元）
     */
    @ApiModelProperty(value = "总产值（万元）")
    @Size(max = 200, message = "总产值（万元）最多输入200个字符")
    private String zcz;

    /**
     * 职工总人数
     */
    @ApiModelProperty(value = "职工总人数")
    @Size(max = 200, message = "职工总人数最多输入200个字符")
    private String zgzrs;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    @Size(max = 200, message = "联系人最多输入200个字符")
    private String lxr;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value = "电子邮箱")
    @Size(max = 200, message = "电子邮箱最多输入200个字符")
    private String dzyx;

    /**
     * 固定电话
     */
    @ApiModelProperty(value = "固定电话")
    @Size(max = 200, message = "固定电话最多输入200个字符")
    private String gddh;

    /**
     * 传真
     */
    @ApiModelProperty(value = "传真")
    @Size(max = 200, message = "传真最多输入200个字符")
    private String cz;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    @Size(max = 200, message = "手机最多输入200个字符")
    private String phone;

    /**
     * 企业类型
     */
    @ApiModelProperty(value = "企业类型")
    @Size(max = 200, message = "企业类型最多输入200个字符")
    private String qylx;

    /**
     * 其他企业类型
     */
    @ApiModelProperty(value = "其他企业类型")
    @Size(max = 200, message = "其他企业类型最多输入200个字符")
    private String qtqylx;

    /**
     * 年报备注
     */
    @ApiModelProperty(value = "年报备注")
    @Size(max = 200, message = "年报备注最多输入200个字符")
    private String yearnote;

    /**
     * 上报日期
     */
    @ApiModelProperty(value = "上报日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sbrq;

    /**
     * 实际经营情况-制定意外事故的防范措施和应急预案情况
     */
    @ApiModelProperty(value = "实际经营情况-制定意外事故的防范措施和应急预案情况")
    @Size(max = 200, message = "实际经营情况-制定意外事故的防范措施和应急预案情况最多输入200个字符")
    private String yjya;

    /**
     * 实际经营情况-实际经营演练情况
     */
    @ApiModelProperty(value = "实际经营情况-实际经营演练情况")
    @Size(max = 200, message = "实际经营情况-实际经营演练情况最多输入200个字符")
    private String ylqk;

    /**
     * 实际经营情况-建立危险废物经营情况记录簿情况
     */
    @ApiModelProperty(value = "实际经营情况-建立危险废物经营情况记录簿情况")
    @Size(max = 200, message = "实际经营情况-建立危险废物经营情况记录簿情况最多输入200个字符")
    private String jlqk;

    /**
     * 实际经营情况-事故发生次数
     */
    @ApiModelProperty(value = "实际经营情况-事故发生次数")
    @Size(max = 200, message = "实际经营情况-事故发生次数最多输入200个字符")
    private String sgfscs;

    /**
     * 实际经营情况-实际经营规模（吨/年）
     */
    @ApiModelProperty(value = "实际经营情况-实际经营规模（吨/年）")
    @Size(max = 200, message = "实际经营情况-实际经营规模（吨/年）最多输入200个字符")
    private String sjyjgm;

    /**
     * 填埋场容量
     */
    @ApiModelProperty(value = "填埋场容量")
    @Digits(integer = 15, fraction = 4, message = "填埋场容量整数最多15位，小数最多4位")
    private BigDecimal tmcrl;

    /**
     * 已填埋容量
     */
    @ApiModelProperty(value = "已填埋容量")
    @Digits(integer = 15, fraction = 4, message = "已填埋容量整数最多15位，小数最多4位")
    private BigDecimal ytmrl;

    /**
     * 许可证主键
     */
    @ApiModelProperty(value = "许可证主键")
    @Size(max = 100, message = "许可证主键最多输入100个字符")
    private String xkzbh;

    /**
     * 经营单位类型
     */
    @ApiModelProperty(value = "经营单位类型")
    @Size(max = 200, message = "经营单位类型最多输入200个字符")
    private String jydwlx;

    /**
     * 许可证类型
     */
    @ApiModelProperty(value = "许可证类型")
    @Size(max = 200, message = "许可证类型最多输入200个字符")
    private String xkzlx;

    /**
     * 审核人[作废]
     */
    @ApiModelProperty(value = "审核人[作废]")
    @Size(max = 200, message = "审核人[作废]最多输入200个字符")
    private String shr;

    /**
     * 许可证编号
     */
    @ApiModelProperty(value = "许可证编号")
    @Size(max = 200, message = "许可证编号最多输入200个字符")
    private String jyxkzbh;

    /**
     * 许可证审批通过时间
     */
    @ApiModelProperty(value = "许可证审批通过时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date xkzSubmitTime;

    /**
     * 许可证绑定id
     */
    @ApiModelProperty(value = "许可证绑定id")
    @Size(max = 100, message = "许可证绑定id最多输入100个字符")
    private String xkzBid;

    /**
     * 许可证发证机关
     */
    @ApiModelProperty(value = "许可证发证机关")
    @Size(max = 200, message = "许可证发证机关最多输入200个字符")
    private String xkzFzjg;

    /**
     * 许可证发证日期
     */
    @ApiModelProperty(value = "许可证发证日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date xkzFzrq;

    /**
     * 许可证有效期开始时间
     */
    @ApiModelProperty(value = "许可证有效期开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date xkzYxsjSta;

    /**
     * 许可证有效期结束时间
     */
    @ApiModelProperty(value = "许可证有效期结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date xkzYxsjEnd;

    /**
     * 许可证核准经营方式
     */
    @ApiModelProperty(value = "许可证核准经营方式")
    @Size(max = 200, message = "许可证核准经营方式最多输入200个字符")
    private String xkzHzjyfs;

    /**
     * 许可证状态[0-填写;1-审核;2-被退回;3-审核完毕;4-作废;5-续证;6-过期;7-暂停]
     */
    @ApiModelProperty(value = "许可证状态[0-填写;1-审核;2-被退回;3-审核完毕;4-作废;5-续证;6-过期;7-暂停]")
    @Size(max = 200, message = "许可证状态[0-填写;1-审核;2-被退回;3-审核完毕;4-作废;5-续证;6-过期;7-暂停]最多输入200个字符")
    private String xkzState;

    /**
     * 许可证-是否填埋类型
     */
    @ApiModelProperty(value = "许可证-是否填埋类型")
    @Size(max = 200, message = "许可证-是否填埋类型最多输入200个字符")
    private String xkzSftm;

    /**
     * 省代码
     */
    @ApiModelProperty(value = "省代码")
    @Size(max = 40, message = "省代码最多输入40个字符")
    private String sssheng;

    /**
     * 地市代码
     */
    @ApiModelProperty(value = "地市代码")
    @Size(max = 40, message = "地市代码最多输入40个字符")
    private String ssshi;

    /**
     * 区县代码
     */
    @ApiModelProperty(value = "区县代码")
    @Size(max = 40, message = "区县代码最多输入40个字符")
    private String ssqu;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyrq;

}
