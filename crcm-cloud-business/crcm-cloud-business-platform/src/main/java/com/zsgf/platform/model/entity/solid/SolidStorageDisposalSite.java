package com.zsgf.platform.model.entity.solid;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 数据共享_一般工业固体废物_贮存处置场信息对象 pp_ybgygf_solid_storage_disposal_site
 *
 * @author gzl
 * @date 2023-03-27
 */
@Setter
@Getter
@ToString
@ApiModel("数据共享_一般工业固体废物_贮存处置场信息")
@TableName("pp_ybgygf_solid_storage_disposal_site")
public class SolidStorageDisposalSite implements Serializable {
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
     * 设施编号
     */
    @ApiModelProperty(value = "设施编号")
    @Size(max = 200, message = "设施编号最多输入200个字符")
    private String ssbh;

    /**
     * 设施名称
     */
    @ApiModelProperty(value = "设施名称")
    @Size(max = 200, message = "设施名称最多输入200个字符")
    private String ssmc;

    /**
     * 设施类型
     */
    @ApiModelProperty(value = "设施类型")
    @Size(max = 200, message = "设施类型最多输入200个字符")
    private String sslx;

    /**
     * 设施-经度-度
     */
    @ApiModelProperty(value = "设施-经度-度")
    @Digits(integer = 15, fraction = 4, message = "设施-经度-度整数最多15位，小数最多4位")
    private BigDecimal ssLngD;

    /**
     * 设施-经度-分
     */
    @ApiModelProperty(value = "设施-经度-分")
    @Digits(integer = 15, fraction = 4, message = "设施-经度-分整数最多15位，小数最多4位")
    private BigDecimal ssLngF;

    /**
     * 设施-经度-秒
     */
    @ApiModelProperty(value = "设施-经度-秒")
    @Digits(integer = 15, fraction = 4, message = "设施-经度-秒整数最多15位，小数最多4位")
    private BigDecimal ssLngM;

    /**
     * 设施-纬度-度
     */
    @ApiModelProperty(value = "设施-纬度-度")
    @Digits(integer = 15, fraction = 4, message = "设施-纬度-度整数最多15位，小数最多4位")
    private BigDecimal ssLatD;

    /**
     * 设施-纬度-分
     */
    @ApiModelProperty(value = "设施-纬度-分")
    @Digits(integer = 15, fraction = 4, message = "设施-纬度-分整数最多15位，小数最多4位")
    private BigDecimal ssLatF;

    /**
     * 设施-纬度-秒
     */
    @ApiModelProperty(value = "设施-纬度-秒")
    @Digits(integer = 15, fraction = 4, message = "设施-纬度-秒整数最多15位，小数最多4位")
    private BigDecimal ssLatM;

    /**
     * 处置场设计容量(立方米)
     */
    @ApiModelProperty(value = "处置场设计容量(立方米)")
    @Digits(integer = 15, fraction = 4, message = "处置场设计容量(立方米)整数最多15位，小数最多4位")
    private BigDecimal czcsjrl;

    /**
     * 处置场设计处置能力(吨/年)
     */
    @ApiModelProperty(value = "处置场设计处置能力(吨/年)")
    @Digits(integer = 15, fraction = 4, message = "处置场设计处置能力(吨/年)整数最多15位，小数最多4位")
    private BigDecimal czcsjcznl;

    /**
     * 尾矿库环境风险等级
     */
    @ApiModelProperty(value = "尾矿库环境风险等级")
    @Size(max = 200, message = "尾矿库环境风险等级最多输入200个字符")
    private String wkkhjfxdj;

    /**
     * 尾矿库环境风险等级划定年份
     */
    @ApiModelProperty(value = "尾矿库环境风险等级划定年份")
    @Size(max = 200, message = "尾矿库环境风险等级划定年份最多输入200个字符")
    private String wkkhjfxdjhdnf;

    /**
     * 处置场已填容量(年份)
     */
    @ApiModelProperty(value = "处置场已填容量(年份)")
    @Digits(integer = 15, fraction = 4, message = "处置场已填容量(年份)整数最多15位，小数最多4位")
    private BigDecimal czcytrj;

    /**
     * 修改时间戳
     */
    @ApiModelProperty(value = "修改时间戳")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyrq;

    /**
     * 贮存处置方式大类
     */
    @ApiModelProperty(value = "贮存处置方式大类")
    @Size(max = 500, message = "贮存处置方式大类最多输入500个字符")
    private String zcczfsdl;

    /**
     * 贮存处置方式小类
     */
    @ApiModelProperty(value = "贮存处置方式小类")
    @Size(max = 200, message = "贮存处置方式小类最多输入200个字符")
    private String zcczfsxl;

}
