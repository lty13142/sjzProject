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
 * 危险废物_经营日报_03接收废物二次转移信息对象 pp_wxfw_business_day_report_waste_secondary_transfer
 *
 * @author gzl;
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_经营日报_03接收废物二次转移信息")
@TableName("pp_wxfw_business_day_report_waste_secondary_transfer")
public class BusinessDayReportWasteSecondaryTransfer implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @Size(max = 100, message = "主键ID最多输入100个字符")
    private String id;

    /**
     * 接收废物信息ID
     */
    @ApiModelProperty(value = "接收废物信息ID")
    @Size(max = 100, message = "接收废物信息ID最多输入100个字符")
    private String childid;

    /**
     * 申报基本信息ID
     */
    @ApiModelProperty(value = "申报基本信息ID")
    @Size(max = 100, message = "申报基本信息ID最多输入100个字符")
    private String mainid;

    /**
     * 委外单位名称
     */
    @ApiModelProperty(value = "委外单位名称")
    @Size(max = 200, message = "委外单位名称最多输入200个字符")
    private String czdwmc;

    /**
     * 委外单位许可证编号
     */
    @ApiModelProperty(value = "委外单位许可证编号")
    @Size(max = 100, message = "委外单位许可证编号最多输入100个字符")
    private String xkzbh;

    /**
     * 转移联单编号
     */
    @ApiModelProperty(value = "转移联单编号")
    @Size(max = 100, message = "转移联单编号最多输入100个字符")
    private String zyldbh;

    /**
     * 转移数量
     */
    @ApiModelProperty(value = "转移数量")
    @Digits(integer = 15, fraction = 4, message = "转移数量整数最多15位，小数最多4位")
    private BigDecimal fwsl;

    /**
     * 转移数量计量单位
     */
    @ApiModelProperty(value = "转移数量计量单位")
    @Size(max = 100, message = "转移数量计量单位最多输入100个字符")
    private String sldw;

    /**
     * 包装方式
     */
    @ApiModelProperty(value = "包装方式")
    @Size(max = 100, message = "包装方式最多输入100个字符")
    private String bzfs;

    /**
     * 利用/处置方式大类
     */
    @ApiModelProperty(value = "利用/处置方式大类")
    @Size(max = 255, message = "利用/处置方式大类最多输入255个字符")
    private String czfs;

    /**
     * 利用/处置方式小类
     */
    @ApiModelProperty(value = "利用/处置方式小类")
    @Size(max = 255, message = "利用/处置方式小类最多输入255个字符")
    private String czfsxl;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 1000, message = "备注最多输入1000个字符")
    private String bz;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyrq;

}
