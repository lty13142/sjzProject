package com.zsgf.platform.model.entity.company;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 企业废物信息对象 tbl_waste_info
 *
 * @author zzyt
 * @date 2023-02-22
 */
@Setter
@Getter
@ToString
@ApiModel("企业废物信息")
@TableName("tbl_waste_info")
public class WasteInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
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
     * 名录版本
     */
    @ApiModelProperty(value = "名录版本")
    @Size(max = 10, message = "名录版本最多输入10个字符")
    private String directoryVersion;

    /**
     * 废物名称
     */
    @ApiModelProperty(value = "废物名称", required = true)
    @NotBlank(message = "废物名称不能为空")
    @Size(max = 255, message = "废物名称最多输入255个字符")
    private String wasteName;

    /**
     * 废物种类
     */
    @ApiModelProperty(value = "废物种类", required = true)
    @NotNull(message = "废物种类不能为空")
    private Integer wasteCategory;

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
    @ApiModelProperty(value = "废物类别名称", required = true)
    @NotBlank(message = "废物类别名称不能为空")
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
     * 危险特性 腐蚀性C 毒性T 易燃性I 反应性R 感染性 In
     */
    @ApiModelProperty(value = "危险特性 腐蚀性C 毒性T 易燃性I 反应性R 感染性 In")
    @Size(max = 100, message = "危险特性 腐蚀性C 毒性T 易燃性I 反应性R 感染性 In最多输入100个字符")
    private String hazards;

    /**
     * 主要成分
     */
    @ApiModelProperty(value = "主要成分", required = true)
    @NotBlank(message = "主要成分不能为空")
    @Size(max = 255, message = "主要成分最多输入255个字符")
    private String component;

    /**
     * 有害成分
     */
    @ApiModelProperty(value = "有害成分", required = true)
    @NotBlank(message = "有害成分不能为空")
    @Size(max = 255, message = "有害成分最多输入255个字符")
    private String hazardousIngredients;

    /**
     * 危险废物形态
     */
    @ApiModelProperty(value = "危险废物形态")
    @Size(max = 100, message = "危险废物形态最多输入100个字符")
    private String shape;

    /**
     * 危险废物产生环节
     */
    @ApiModelProperty(value = "危险废物产生环节")
    @Size(max = 200, message = "危险废物产生环节最多输入200个字符")
    private String origin;

    /**
     * 禁忌与应急措施
     */
    @ApiModelProperty(value = "禁忌与应急措施")
    @Size(max = 1000, message = "禁忌与应急措施最多输入1000个字符")
    private String careful;

    /**
     * 处置方式大类
     */
    @ApiModelProperty(value = "处置方式大类")
    @Size(max = 100, message = "处置方式大类最多输入100个字符")
    private String disposalMajor;

    /**
     * 处置方式小类
     */
    @ApiModelProperty(value = "处置方式小类")
    @Size(max = 100, message = "处置方式小类最多输入100个字符")
    private String disposalSmall;

    /**
     * 危险类别（数据字典）
     */
    @ApiModelProperty(value = "危险类别（数据字典）")
    @Size(max = 255, message = "危险类别（数据字典）最多输入255个字符")
    private String dangerousType;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除", required = true)
    private Integer deleted;

}
