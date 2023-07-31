package com.zsgf.platform.model.entity.standardize;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 规范化考核大项对象 tbl_waste_standardized_assessment_item
 *
 * @author gzl
 * @date 2023-03-20
 */
@Setter
@Getter
@ToString
@ApiModel("规范化考核大项")
@TableName("tbl_waste_standardized_assessment_item")
public class WasteStandardizedAssessmentItem extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @Size(max = 100, message = "最多输入100个字符")
    private String id;

    /**
     * 检查项目
     */
    @ApiModelProperty(value = "检查项目")
    @NotBlank(message = "检查项目不能为空")
    @Size(max = 255, message = "检查项目最多输入255个字符")
    private String name;

    /**
     * 分类，产废-0，经营-1
     */
    @ApiModelProperty(value = "分类，产废-0，经营-1")
    @NotBlank(message = "分类不能为空")
    @Size(max = 10, message = "分类，产废-0，经营-1最多输入10个字符")
    private String itemSort;

    /**
     * 顺序
     */
    @ApiModelProperty(value = "顺序")
    private Integer orderNo;

    /**
     * 设施情况 0-无 1-有利用 2-有处置
     */
    @ApiModelProperty(value = "设施情况 0-无 1-有利用 2-有处置 ")
    private String facilityStatus;

    /**
     * 是否为过期版本
     */
    @ApiModelProperty(value = "是否为过期版本")
    private Integer isOld;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除", required = true)
    private Integer deleted;

}
