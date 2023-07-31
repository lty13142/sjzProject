package com.zsgf.platform.model.entity.area;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 系统行政区划(省平台代码一致)对象 pp_area_code
 *
 * @author gzl
 * @date 2023-02-09
 */
@Setter
@Getter
@ToString
@ApiModel("系统行政区划(省平台代码一致)")
@TableName("pp_area_code")
public class AreaCode implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @Size(max = 100, message = "ID最多输入100个字符")
    private String id;

    /**
     * 区域代码
     */
    @ApiModelProperty(value = "区域代码")
    @Size(max = 100, message = "区域代码最多输入100个字符")
    private String code;

    /**
     * 父级代码
     */
    @ApiModelProperty(value = "父级代码")
    @Size(max = 100, message = "父级代码最多输入100个字符")
    private String pcode;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @Size(max = 100, message = "名称最多输入100个字符")
    private String name;

    /**
     * 级别
     */
    @ApiModelProperty(value = "级别")
    @Size(max = 5, message = "级别最多输入5个字符")
    private String lev;

    /**
     * 是否为最后一级
     */
    @ApiModelProperty(value = "是否为最后一级")
    @Size(max = 5, message = "是否为最后一级最多输入5个字符")
    private String islast;

    /**
     * 业务代码
     */
    @ApiModelProperty(value = "业务代码")
    @Size(max = 20, message = "业务代码最多输入20个字符")
    private String businessCode;

}
