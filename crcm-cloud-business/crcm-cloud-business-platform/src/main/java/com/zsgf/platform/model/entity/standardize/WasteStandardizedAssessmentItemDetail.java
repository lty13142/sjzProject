package com.zsgf.platform.model.entity.standardize;

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
import java.math.BigDecimal;

/**
 * 规范化考核小项对象 tbl_waste_standardized_assessment_item_detail
 *
 * @author gzl
 * @date 2023-03-20
 */
@Setter
@Getter
@ToString
@ApiModel("规范化考核小项")
@TableName("tbl_waste_standardized_assessment_item_detail")
public class WasteStandardizedAssessmentItemDetail extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @Size(max = 100, message = "最多输入100个字符")
    private String id;

    /**
     * 分类，产废-0，经营-1
     */
    @ApiModelProperty(value = "分类，产废-0，经营-1")
    @NotBlank(message = "分类不能为空")
    @Size(max = 10, message = "分类，产废-0，经营-1最多输入10个字符")
    private String itemSort;

    /**
     * 检查项id
     */
    @ApiModelProperty(value = "检查项id")
    @Size(max = 100, message = "检查项id最多输入100个字符")
    @NotBlank(message = "请选择检查项")
    private String itemId;

    /**
     * 主要检查内容
     */
    @ApiModelProperty(value = "主要检查内容")
    @Size(max = 2000, message = "主要检查内容最多输入2000个字符")
    @NotBlank(message = "主要检查内容不能为空")
    private String title;

    /**
     * 达标标准
     */
    @ApiModelProperty(value = "达标标准")
    @Size(max = 2000, message = "达标标准最多输入2000个字符")
    private String standard;

    /**
     * 检查方式
     */
    @ApiModelProperty(value = "检查方式")
    @Size(max = 1000, message = "检查方式最多输入1000个字符")
    private String checkWay;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 2000, message = "备注最多输入2000个字符")
    private String remark;

    /**
     * 评分细则
     */
    @ApiModelProperty(value = "评分细则")
    @Size(max = 2000, message = "评分细则最多输入2000个字符")
    private String detailRule;

    /**
     * 满分
     */
    @ApiModelProperty(value = "满分")
    private BigDecimal totalScore;

    /**
     * 是否为否决项
     */
    @ApiModelProperty(value = "是否为否决项")
    private Integer isVeto;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer orderNo;

    /**
     * 附件是否必传
     */
    @ApiModelProperty(value = "附件是否必传")
    private Integer attNes;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除", required = true)
    private Integer deleted;

}
