package com.zsgf.platform.model.entity.wasteReport;

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
 * 危险废物_产生年报_01基本信息对象 pp_wxfw_waste_year_report
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_产生年报_01基本信息")
@TableName("pp_wxfw_waste_year_report")
public class WasteYearReport implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @Size(max = 100, message = "主键ID最多输入100个字符")
    private String id;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    @Size(max = 100, message = "单位ID最多输入100个字符")
    private String dwid;

    /**
     * 申报年度
     */
    @ApiModelProperty(value = "申报年度")
    @Size(max = 100, message = "申报年度最多输入100个字符")
    private String djnf;

    /**
     * 填写时间
     */
    @ApiModelProperty(value = "填写时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date txsj;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    @Size(max = 1000, message = "备注信息最多输入1000个字符")
    private String bz;

    /**
     * 申请填写人
     */
    @ApiModelProperty(value = "申请填写人")
    @Size(max = 200, message = "申请填写人最多输入200个字符")
    private String txr;

    /**
     * 申报状态[0-未提交,1-审核中,2-被退回,3-已通过,4-作废]
     */
    @ApiModelProperty(value = "申报状态[0-未提交,1-审核中,2-被退回,3-已通过,4-作废]")
    @Size(max = 100, message = "申报状态[0-未提交,1-审核中,2-被退回,3-已通过,4-作废]最多输入100个字符")
    private String state;

    /**
     * 总产值(万元)
     */
    @ApiModelProperty(value = "总产值(万元)")
    @Digits(integer = 15, fraction = 4, message = "总产值(万元)整数最多15位，小数最多4位")
    private BigDecimal zcz;

    /**
     * 产生规模代码
     */
    @ApiModelProperty(value = "产生规模代码")
    @Size(max = 200, message = "产生规模代码最多输入200个字符")
    private String csgmdm;

    /**
     * 危险废物转移联单执行情况
     */
    @ApiModelProperty(value = "危险废物转移联单执行情况")
    @Size(max = 200, message = "危险废物转移联单执行情况最多输入200个字符")
    private String zxqk;

    /**
     * 制定意外事故的防范措施和应急预案情况
     */
    @ApiModelProperty(value = "制定意外事故的防范措施和应急预案情况")
    @Size(max = 200, message = "制定意外事故的防范措施和应急预案情况最多输入200个字符")
    private String yaqk;

    /**
     * 执行申报登记制度情况
     */
    @ApiModelProperty(value = "执行申报登记制度情况")
    @Size(max = 200, message = "执行申报登记制度情况最多输入200个字符")
    private String zdqk;

    /**
     * 工业危险废物提供或委托外单位处理情况
     */
    @ApiModelProperty(value = "工业危险废物提供或委托外单位处理情况")
    @Size(max = 200, message = "工业危险废物提供或委托外单位处理情况最多输入200个字符")
    private String clqk;

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
     * 电话
     */
    @ApiModelProperty(value = "电话")
    @Size(max = 200, message = "电话最多输入200个字符")
    private String dw;

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
    private String sj;

    /**
     * 申报产生量是否为0
     */
    @ApiModelProperty(value = "申报产生量是否为0")
    @Size(max = 200, message = "申报产生量是否为0最多输入200个字符")
    private String cslsfwl;

    /**
     * 申报是否停产
     */
    @ApiModelProperty(value = "申报是否停产")
    @Size(max = 200, message = "申报是否停产最多输入200个字符")
    private String sbsftc;

    /**
     * 停产时间
     */
    @ApiModelProperty(value = "停产时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tcsj;

    /**
     * 停产原因
     */
    @ApiModelProperty(value = "停产原因")
    @Size(max = 1000, message = "停产原因最多输入1000个字符")
    private String tcyy;

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
