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
 * 数据共享_一般工业固体废物_综合利用设施信息对象 pp_ybgygf_solid_use_facilities
 *
 * @author gzl
 * @date 2023-03-27
 */
@Setter
@Getter
@ToString
@ApiModel("数据共享_一般工业固体废物_综合利用设施信息")
@TableName("pp_ybgygf_solid_use_facilities")
public class SolidUseFacilities implements Serializable {
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
    @Size(max = 400, message = "设施名称最多输入400个字符")
    private String ssmc;

    /**
     * 利用方式
     */
    @ApiModelProperty(value = "利用方式")
    @Size(max = 200, message = "利用方式最多输入200个字符")
    private String lyfs;

    /**
     * 利用能力(吨/年)
     */
    @ApiModelProperty(value = "利用能力(吨/年)")
    @Digits(integer = 15, fraction = 4, message = "利用能力(吨/年)整数最多15位，小数最多4位")
    private BigDecimal lynl;

    /**
     * 综合利用处置方式大类
     */
    @ApiModelProperty(value = "综合利用处置方式大类")
    @Size(max = 500, message = "综合利用处置方式大类最多输入500个字符")
    private String lyczfsdl;

    /**
     * 利用处置方式小类
     */
    @ApiModelProperty(value = "利用处置方式小类")
    @Size(max = 200, message = "利用处置方式小类最多输入200个字符")
    private String lyczfsxl;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyrq;

}
