package com.zsgf.platform.model.entity.company;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 固废企业信息_企业角色类型对象 pp_company_role
 *
 * @author gzl
 * @date 2023-02-10
 */
@Setter
@Getter
@ToString
@ApiModel("固废企业信息_企业角色类型")
@TableName("pp_company_role")
public class CompanyRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    @Size(max = 300, message = "ID最多输入300个字符")
    private String id;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    @Size(max = 100, message = "单位ID最多输入100个字符")
    private String dwid;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    @Size(max = 100, message = "角色ID最多输入100个字符")
    private String jsid;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    @Size(max = 200, message = "角色名称最多输入200个字符")
    private String jsmc;

    /**
     * 角色类型(包括：危险废物产生单位、危险废物收集单位、危险废物经营单位等)
     */
    @ApiModelProperty(value = "角色类型(包括：危险废物产生单位、危险废物收集单位、危险废物经营单位等)")
    @Size(max = 100, message = "角色类型(包括：危险废物产生单位、危险废物收集单位、危险废物经营单位等)最多输入100个字符")
    private String jslx;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    private Long jsxh;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
