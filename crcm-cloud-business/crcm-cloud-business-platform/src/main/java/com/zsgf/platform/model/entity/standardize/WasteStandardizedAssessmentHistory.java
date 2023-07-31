package com.zsgf.platform.model.entity.standardize;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
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
 * 规范化考核评分记录对象 tbl_waste_standardized_assessment_history
 *
 * @author gzl
 * @date 2023-03-24
 */
@Setter
@Getter
@ToString
@ApiModel("规范化考核评分记录")
@TableName("tbl_waste_standardized_assessment_history")
public class WasteStandardizedAssessmentHistory extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
    @Size(max = 100, message = "id最多输入100个字符")
    private String id;

    /**
     * 规范化考核id
     */
    @ApiModelProperty(value = "规范化考核id")
    @Size(max = 100, message = "规范化考核id最多输入100个字符")
    private String judgeId;

    /**
     * 检查项
     */
    @ApiModelProperty(value = "检查项")
    @Size(max = 100, message = "检查项最多输入100个字符")
    private String itemName;

    /**
     * 主要检查内容
     */
    @ApiModelProperty(value = "主要检查内容")
    @Size(max = 2000, message = "主要检查内容最多输入2000个字符")
    private String itemDetail;

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
     * 评分细则
     */
    @ApiModelProperty(value = "评分细则")
    @Size(max = 2000, message = "评分细则最多输入2000个字符")
    private String detailRule;

    /**
     * 满分
     */
    @ApiModelProperty(value = "满分")
    @Digits(integer = 3, fraction = 1, message = "满分整数最多3位，小数最多1位")
    private BigDecimal totalScore;

    /**
     * 是否为否决项
     */
    @ApiModelProperty(value = "是否为否决项")
    private Long isVeto;

    /**
     * 附件是否必传
     */
    @ApiModelProperty(value = "附件是否必传")
    private Integer attNes;

    /**
     * 大项排序序号
     */
    @ApiModelProperty(value = "大项排序序号")
    private Long itemOrderNo;

    /**
     * 小项排序序号
     */
    @ApiModelProperty(value = "小项排序序号")
    private Long detailOrderNo;

    /**
     * 评估人
     */
    @ApiModelProperty(value = "评估人")
    @Size(max = 100, message = "评估人最多输入100个字符")
    private String checkUser;

    /**
     * 评估时间
     */
    @ApiModelProperty(value = "评估时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkTime;

    /**
     * 评估分数
     */
    @ApiModelProperty(value = "评估分数")
    @Digits(integer = 3, fraction = 1, message = "评估分数整数最多3位，小数最多1位")
    private BigDecimal lastScore;

    /**
     * 评估备注
     */
    @ApiModelProperty(value = "评估备注")
    @Size(max = 2000, message = "评估备注最多输入2000个字符")
    private String remark;

    /**
     * 评估附件地址
     */
    @ApiModelProperty(value = "评估附件地址")
    @Size(max = 255, message = "评估附件地址最多输入255个字符")
    private String attachmentUrl;

    /**
     * 评估达标情况
     */
    @ApiModelProperty(value = "评估达标情况")
    @Size(max = 100, message = "评估达标情况最多输入100个字符")
    private String lastResult;

    /**
     * 复评分数
     */
    @ApiModelProperty(value = "复评分数")
    @Digits(integer = 3, fraction = 1, message = "复评分数整数最多3位，小数最多1位")
    private BigDecimal reAssessmentScore;

    /**
     * 复评备注
     */
    @ApiModelProperty(value = "复评备注")
    @Size(max = 2000, message = "复评备注最多输入2000个字符")
    private String reAssessmentRemark;

    /**
     * 复评附件
     */
    @ApiModelProperty(value = "复评附件")
    @Size(max = 255, message = "复评附件最多输入255个字符")
    private String reAssessmentAttachmentUrl;

    /**
     * 复评达标情况
     */
    @ApiModelProperty(value = "复评达标情况")
    @Size(max = 100, message = "复评达标情况最多输入100个字符")
    private String reAssessmentLastResult;

    /**
     * 是否存在问题
     */
    @ApiModelProperty(value = "是否存在问题")
    private Long existProblem;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Long status;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

}
