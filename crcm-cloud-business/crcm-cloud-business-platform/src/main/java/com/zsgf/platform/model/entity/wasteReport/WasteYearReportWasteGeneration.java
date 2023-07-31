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
 * 危险废物_产生年报_02废物产生情况对象 pp_wxfw_waste_year_report_waste_generation
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("危险废物_产生年报_02废物产生情况")
@TableName("pp_wxfw_waste_year_report_waste_generation")
public class WasteYearReportWasteGeneration implements Serializable {
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
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    @Size(max = 100, message = "单位ID最多输入100个字符")
    private String dwid;

    /**
     * 名录版本
     */
    @ApiModelProperty(value = "名录版本")
    @Size(max = 100, message = "名录版本最多输入100个字符")
    private String mlnf;

    /**
     * 废物类别
     */
    @ApiModelProperty(value = "废物类别")
    @Size(max = 100, message = "废物类别最多输入100个字符")
    private String fwdlbh;

    /**
     * 废物类别名称
     */
    @ApiModelProperty(value = "废物类别名称")
    @Size(max = 200, message = "废物类别名称最多输入200个字符")
    private String fwdlmc;

    /**
     * 废物代码
     */
    @ApiModelProperty(value = "废物代码")
    @Size(max = 100, message = "废物代码最多输入100个字符")
    private String fwxlbh;

    /**
     * 废物名称
     */
    @ApiModelProperty(value = "废物名称")
    @Size(max = 1000, message = "废物名称最多输入1000个字符")
    private String fwxlmc;

    /**
     * 废物详细名称
     */
    @ApiModelProperty(value = "废物详细名称")
    @Size(max = 2000, message = "废物详细名称最多输入2000个字符")
    private String fwxxmc;

    /**
     * 废物形态
     */
    @ApiModelProperty(value = "废物形态")
    @Size(max = 200, message = "废物形态最多输入200个字符")
    private String fwxt;

    /**
     * 危险废物描述
     */
    @ApiModelProperty(value = "危险废物描述")
    @Size(max = 2000, message = "危险废物描述最多输入2000个字符")
    private String fwms;

    /**
     * 申报当年产生数量
     */
    @ApiModelProperty(value = "申报当年产生数量")
    @Digits(integer = 15, fraction = 4, message = "申报当年产生数量整数最多15位，小数最多4位")
    private BigDecimal cssl;

    /**
     * 其他量
     */
    @ApiModelProperty(value = "其他量")
    @Digits(integer = 15, fraction = 4, message = "其他量整数最多15位，小数最多4位")
    private BigDecimal qtl;

    /**
     * 上年贮存数量[统计得出]
     */
    @ApiModelProperty(value = "上年贮存数量[统计得出]")
    @Digits(integer = 15, fraction = 4, message = "上年贮存数量[统计得出]整数最多15位，小数最多4位")
    private BigDecimal snzcl;

    /**
     * 数量单位
     */
    @ApiModelProperty(value = "数量单位")
    @Size(max = 200, message = "数量单位最多输入200个字符")
    private String sldw;

    /**
     * 其他备注说明
     */
    @ApiModelProperty(value = "其他备注说明")
    @Size(max = 2000, message = "其他备注说明最多输入2000个字符")
    private String bz;

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
