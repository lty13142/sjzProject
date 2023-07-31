package com.zsgf.platform.model.entity.company;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 危险化学品备案信息对象 tbl_hazardous_chemicals
 *
 * @author gzl
 * @date 2023-03-24
 */
@Setter
@Getter
@ToString
@ApiModel("危险化学品备案信息")
@TableName("tbl_hazardous_chemicals")
public class HazardousChemicals extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
    @Size(max = 32, message = "id最多输入32个字符")
    private String id;

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id", required = true)
    @NotBlank(message = "企业id不能为空")
    @Size(max = 64, message = "企业id最多输入64个字符")
    private String companyId;

    /**
     * 备案日期
     */
    @ApiModelProperty(value = "备案日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recordDate;

    /**
     * 有效日期
     */
    @ApiModelProperty(value = "有效日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date limitDate;

    /**
     * 废物名称
     */
    @ApiModelProperty(value = "废物名称", required = true)
    @NotBlank(message = "废物名称不能为空")
    @Size(max = 255, message = "废物名称最多输入255个字符")
    private String wasteName;

    /**
     * 废物类别
     */
    @ApiModelProperty(value = "废物类别", required = true)
    @NotBlank(message = "废物类别不能为空")
    @Size(max = 255, message = "废物类别最多输入255个字符")
    private String wasteType;

    /**
     * 废物类别名称
     */
    @ApiModelProperty(value = "废物类别名称")
    @Size(max = 255, message = "废物类别名称最多输入255个字符")
    private String wasteTypeName;

    /**
     * 废物代码
     */
    @ApiModelProperty(value = "废物代码", required = true)
    @NotBlank(message = "废物代码不能为空")
    @Size(max = 100, message = "废物代码最多输入100个字符")
    private String wasteCode;

    /**
     * 废物代码名称
     */
    @ApiModelProperty(value = "废物代码名称")
    @Size(max = 500, message = "废物代码名称最多输入500个字符")
    private String wasteCodeName;

    /**
     * 危险类别（数据字典）
     */
    @ApiModelProperty(value = "危险类别（数据字典）")
    @Size(max = 255, message = "危险类别（数据字典）最多输入255个字符")
    private String dangerousType;

    /**
     * 主要危害成分
     */
    @ApiModelProperty(value = "主要危害成分")
    @Size(max = 255, message = "主要危害成分最多输入255个字符")
    private String component;

    /**
     * 禁忌与应急措施
     */
    @ApiModelProperty(value = "禁忌与应急措施")
    @Size(max = 1000, message = "禁忌与应急措施最多输入1000个字符")
    private String careful;

    /**
     * 危险废物形态
     */
    @ApiModelProperty(value = "危险废物形态")
    @Size(max = 100, message = "危险废物形态最多输入100个字符")
    private String shape;

    /**
     * 危险废物气味
     */
    @ApiModelProperty(value = "危险废物气味")
    @Size(max = 20, message = "危险废物气味最多输入20个字符")
    private String smell;

    /**
     * 危险废物颜色
     */
    @ApiModelProperty(value = "危险废物颜色")
    @Size(max = 20, message = "危险废物颜色最多输入20个字符")
    private String color;

    /**
     * 危险废物产生环节
     */
    @ApiModelProperty(value = "危险废物产生环节")
    @Size(max = 200, message = "危险废物产生环节最多输入200个字符")
    private String origin;

    /**
     * 去向： 自行 委外
     */
    @ApiModelProperty(value = "去向： 自行 委外")
    @Size(max = 10, message = "去向： 自行 委外最多输入10个字符")
    private String toWhere;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

}
