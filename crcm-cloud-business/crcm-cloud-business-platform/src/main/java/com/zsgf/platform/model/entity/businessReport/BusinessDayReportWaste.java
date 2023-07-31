package com.zsgf.platform.model.entity.businessReport;

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
 * 危险废物_经营日报_02接收废物信息对象 pp_wxfw_business_day_report_waste
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_经营日报_02接收废物信息")
@TableName("pp_wxfw_business_day_report_waste")
public class BusinessDayReportWaste implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @Size(max = 100, message = "主键ID最多输入100个字符")
    private String id;

    /**
     * 申报基本信息ID
     */
    @ApiModelProperty(value = "申报基本信息ID")
    @Size(max = 100, message = "申报基本信息ID最多输入100个字符")
    private String mainid;

    /**
     * 设施类型
     */
    @ApiModelProperty(value = "设施类型")
    @Size(max = 255, message = "设施类型最多输入255个字符")
    private String sslx;

    /**
     * 设施名称
     */
    @ApiModelProperty(value = "设施名称")
    @Size(max = 255, message = "设施名称最多输入255个字符")
    private String czssmc;

    /**
     * 处置方式大类
     */
    @ApiModelProperty(value = "处置方式大类")
    @Size(max = 255, message = "处置方式大类最多输入255个字符")
    private String czfsdl;

    /**
     * 处置方式小类
     */
    @ApiModelProperty(value = "处置方式小类")
    @Size(max = 255, message = "处置方式小类最多输入255个字符")
    private String czfsxl;

    /**
     * 名录版本
     */
    @ApiModelProperty(value = "名录版本")
    @Size(max = 100, message = "名录版本最多输入100个字符")
    private String mlnf;

    /**
     * 废物大类编号
     */
    @ApiModelProperty(value = "废物大类编号")
    @Size(max = 255, message = "废物大类编号最多输入255个字符")
    private String fwdlbh;

    /**
     * 废物大类名称
     */
    @ApiModelProperty(value = "废物大类名称")
    @Size(max = 255, message = "废物大类名称最多输入255个字符")
    private String fwdlmc;

    /**
     * 废物小类编号
     */
    @ApiModelProperty(value = "废物小类编号")
    @Size(max = 255, message = "废物小类编号最多输入255个字符")
    private String fwxlbh;

    /**
     * 废物小类名称
     */
    @ApiModelProperty(value = "废物小类名称")
    @Size(max = 1000, message = "废物小类名称最多输入1000个字符")
    private String fwxlmc;

    /**
     * 利用处置外来废物重量
     */
    @ApiModelProperty(value = "利用处置外来废物重量")
    @Digits(integer = 15, fraction = 4, message = "利用处置外来废物重量整数最多15位，小数最多4位")
    private BigDecimal lyczsl;

    /**
     * 接收量
     */
    @ApiModelProperty(value = "接收量")
    @Digits(integer = 15, fraction = 4, message = "接收量整数最多15位，小数最多4位")
    private BigDecimal jsl;

    /**
     * 二次转移
     */
    @ApiModelProperty(value = "二次转移")
    @Digits(integer = 15, fraction = 4, message = "二次转移整数最多15位，小数最多4位")
    private BigDecimal eczy;

    /**
     * 数量单位
     */
    @ApiModelProperty(value = "数量单位")
    @Size(max = 255, message = "数量单位最多输入255个字符")
    private String sldw;

    /**
     * 申报状态
     */
    @ApiModelProperty(value = "申报状态")
    @Size(max = 255, message = "申报状态最多输入255个字符")
    private String state;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 1000, message = "备注最多输入1000个字符")
    private String bz;

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
