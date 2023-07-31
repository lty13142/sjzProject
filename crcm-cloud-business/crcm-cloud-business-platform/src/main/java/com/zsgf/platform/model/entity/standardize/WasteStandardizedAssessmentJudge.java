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
 * 规范化考核结果对象 tbl_waste_standardized_assessment_judge
 *
 * @author gzl
 * @date 2023-03-24
 */
@Setter
@Getter
@ToString
@ApiModel("规范化考核结果")
@TableName("tbl_waste_standardized_assessment_judge")
public class WasteStandardizedAssessmentJudge extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
    @Size(max = 32, message = "id最多输入32个字符")
    private String id;

    /**
     * 公司id
     */
    @ApiModelProperty(value = "公司id")
    @Size(max = 100, message = "公司id最多输入100个字符")
    private String companyId;

    /**
     * 填写年份
     */
    @ApiModelProperty(value = "填写年份")
    @Size(max = 8, message = "填写年份最多输入8个字符")
    private String judgeYear;

    /**
     * 考核类型 0-自评 1-镇区 2-环保局抽查
     */
    @ApiModelProperty(value = "考核类型 0-自评 1-镇区 2-环保局抽查")
    @Size(max = 10, message = "考核类型 0-自评 1-镇区 2-环保局抽查最多输入10个字符")
    private String checkType;

    /**
     * 是否发生突发事件
     */
    @ApiModelProperty(value = "是否发生突发事件")
    @Size(max = 10, message = "是否发生突发事件最多输入10个字符")
    private String emergency;

    /**
     * 处置设施情况 0-无 1-利用 2-处置 3-均有
     */
    @ApiModelProperty(value = "处置设施情况 0-无 1-利用 2-处置 3-均有")
    @Size(max = 20, message = "处置设施情况 0-无 1-利用 2-处置 3-均有最多输入20个字符")
    private String hasFacilities;

    /**
     * 批次号
     */
    @ApiModelProperty(value = "批次号")
    @Size(max = 100, message = "批次号最多输入100个字符")
    private String batchNo;

    /**
     * 评估分数
     */
    @ApiModelProperty(value = "评估分数")
    @Digits(integer = 8, fraction = 2, message = "评估分数整数最多8位，小数最多2位")
    private BigDecimal judgeScore;

    /**
     * 评估结果
     */
    @ApiModelProperty(value = "评估结果")
    @Size(max = 32, message = "评估结果最多输入32个字符")
    private String judgeResult;

    /**
     * 相关意见
     */
    @ApiModelProperty(value = "相关意见")
    @Size(max = 255, message = "相关意见最多输入255个字符")
    private String advice;

    /**
     * 未通过原因
     */
    @ApiModelProperty(value = "未通过原因")
    @Size(max = 2000, message = "未通过原因最多输入2000个字符")
    private String refuseReason;

    /**
     * 评估人
     */
    @ApiModelProperty(value = "评估人")
    @Size(max = 200, message = "评估人最多输入200个字符")
    private String createUser;

    /**
     * 评估时间
     */
    @ApiModelProperty(value = "评估时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date judgeTime;

    /**
     * 考核状态
     */
    @ApiModelProperty(value = "考核状态")
    private Long status;

    /**
     * 是否存在问题
     */
    @ApiModelProperty(value = "是否存在问题")
    private Long existProblem;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

}
