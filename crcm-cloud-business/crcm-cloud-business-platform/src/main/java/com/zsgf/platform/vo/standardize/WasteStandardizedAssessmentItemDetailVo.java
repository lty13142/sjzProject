package com.zsgf.platform.vo.standardize;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName WasteStandardizedAssessmentItemDetailVo
 * @Description 规范化管理小类VO
 * @Author GZL
 * @Date 2023/3/21 10:36
 * @Version 1.0
 **/
@Data
public class WasteStandardizedAssessmentItemDetailVo {


    /**
     *
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 分类，产废-0，经营-1
     */
    @ApiModelProperty(value = "分类，产废-0，经营-1")
    private String itemSort;

    /**
     * 检查项id
     */
    @ApiModelProperty(value = "检查项id")
    private String itemId;

    /**
     * 检查项
     */
    @ApiModelProperty(value = "检查项")
    private String itemName;

    /**
     * 主要检查内容
     */
    @ApiModelProperty(value = "主要检查内容")
    private String title;

    /**
     * 达标标准
     */
    @ApiModelProperty(value = "达标标准")
    private String standard;

    /**
     * 检查方式
     */
    @ApiModelProperty(value = "检查方式")
    private String checkWay;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 评分细则
     */
    @ApiModelProperty(value = "评分细则")
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
    @ApiModelProperty(value = "是否删除", required = true)
    private Integer deleted;

}
