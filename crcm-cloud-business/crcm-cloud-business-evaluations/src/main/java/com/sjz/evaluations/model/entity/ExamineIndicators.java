package com.sjz.evaluations.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 考核指标对象 kh_examine_indicators
 *
 * @author zzyt
 * @date 2023-04-25
 */
@Data
@Accessors(chain = true)
@TableName("kh_examine_indicators")
public class ExamineIndicators extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 考核任务id
     */
    @NotBlank(message = "考核任务id不能为空")
    @ApiModelProperty(value = "考核任务id")
    private String examineId;

    /**
     * 指标名称
     */
    @NotBlank(message = "指标名称不能为空")
    @Length(max = 128, message = "指标名称长度不能超过128位")
    @ApiModelProperty(value = "指标名称")
    private String indicatorsName;

    /**
     * 指标内容
     */
    @NotBlank(message = "指标内容不能为空")
    @ApiModelProperty(value = "指标内容")
    private String indicatorsContent;

    /**
     * 满分
     */
    @ApiModelProperty(value = "满分")
    private Integer fullMarks;

    /**
     * 附件
     */
    @ApiModelProperty(value = "附件")
    private String attr;

    /**
     * 附件名称
     */
    @ApiModelProperty(value = "附件名称")
    private String attrName;

    /**
     * 负责部门id
     */
    @NotNull(message = "负责部门不能为空")
    @ApiModelProperty(value = "负责部门id")
    private Long orgId;

    /**
     * 负责部门名称
     */
    @ApiModelProperty(value = "负责部门名称")
    private String orgName;

    /**
     * 负责人名称
     */
    @ApiModelProperty(value = "负责人名称")
    private String nikeName;

    /**
     * 指标类型(0:定性指标 1:定量指标)
     */
    @NotNull(message = "指标类型不能为空")
    @ApiModelProperty(value = "指标类型(0:定性指标 1:定量指标)")
    private Integer type;


}
