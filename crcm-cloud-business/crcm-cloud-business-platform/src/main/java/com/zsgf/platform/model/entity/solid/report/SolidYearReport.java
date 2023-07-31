package com.zsgf.platform.model.entity.solid.report;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据共享_一般工业固体废物_产生年报_01基本信息对象 pp_ybgygf_solid_year_report
 *
 * @author gzl
 * @date 2023-03-27
 */
@Setter
@Getter
@ToString
@ApiModel("数据共享_一般工业固体废物_产生年报_01基本信息")
@TableName("pp_ybgygf_solid_year_report")
public class SolidYearReport implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
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
     * 年份
     */
    @ApiModelProperty(value = "年份")
    @Size(max = 10, message = "年份最多输入10个字符")
    private String nf;

    /**
     * 申报时间
     */
    @ApiModelProperty(value = "申报时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sbsj;

    /**
     * 填写人
     */
    @ApiModelProperty(value = "填写人")
    @Size(max = 200, message = "填写人最多输入200个字符")
    private String txr;

    /**
     * 填写人电话
     */
    @ApiModelProperty(value = "填写人电话")
    @Size(max = 20, message = "填写人电话最多输入20个字符")
    private String txrphone;

    /**
     * 申报登记产生量是否为0
     */
    @ApiModelProperty(value = "申报登记产生量是否为0")
    @Size(max = 10, message = "申报登记产生量是否为0最多输入10个字符")
    private String cslsfwl;

    /**
     * 申报登记时是否停产
     */
    @ApiModelProperty(value = "申报登记时是否停产")
    @Size(max = 0, message = "申报登记时是否停产最多输入0个字符")
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
     * 是否产生一般固废
     */
    @ApiModelProperty(value = "是否产生一般固废")
    @Size(max = 10, message = "是否产生一般固废最多输入10个字符")
    private String sfcsybgf;

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
    @Size(max = 400, message = "区县代码最多输入400个字符")
    private String ssqu;

    /**
     * 业务状态（３审核完毕４作废）
     */
    @ApiModelProperty(value = "业务状态（３审核完毕４作废）")
    @Size(max = 10, message = "业务状态（３审核完毕４作废）最多输入10个字符")
    private String state;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    @Size(max = 1000, message = "备注信息最多输入1000个字符")
    private String remark;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyrq;

}
