package com.sjz.evaluations.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 村级考核实体对象 kh_village_examine
 * true
 *
 * @author zzyt
 * @date 2023-04-25
 */
@Data
@Accessors(chain = true)
@TableName("kh_village_examine")
public class VillageExamine extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 指标id
     */
    @ApiModelProperty(value = "指标id")
    private String indicatorsId;

    /**
     * 管区考核id
     */
    @ApiModelProperty(value = "管区考核id")
    private String regionExamineId;

    /**
     * 村级区域code
     */
    @ApiModelProperty(value = "村级区域code")
    private String villageAreaCode;

    /**
     * 村级区域名称
     */
    @ApiModelProperty(value = "村级区域名称")
    private String villageAreaName;

    /**
     * 定性/定量目标
     */
    @ApiModelProperty(value = "定性/定量目标")
    private String target;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;

    /**
     * 完成状态(0:未完成 1:已完成)
     */
    @ApiModelProperty(value = "完成状态(0:未完成 1:已完成)")
    private Integer completeStatus;

    /**
     * 进度目标/完成情况
     */
    @ApiModelProperty(value = "进度目标/完成情况")
    private String completeValue;

    /**
     * 反馈内容
     */
    @ApiModelProperty(value = "反馈内容")
    private String feedbackContent;

    /**
     * 反馈附件
     */
    @ApiModelProperty(value = "反馈附件")
    private String feedbackAttr;

    /**
     * 反馈附件名称
     */
    @ApiModelProperty(value = "反馈附件名称")
    private String feedbackAttrName;

    /**
     * 核实值
     */
    @ApiModelProperty(value = "核实值")
    private BigDecimal score;

    /**
     * 核实内容
     */
    @ApiModelProperty(value = "核实内容")
    private String scoreContent;

    /**
     * 核实完成状态(0:未完成 1:已完成)
     */
    @ApiModelProperty(value = "核实完成状态(0:未完成 1:已完成)")
    private Integer verifyCompleteStatus;

    /**
     * 考核状态(0：待反馈 1：已反馈 2：已核实 3：已完成)
     */
    @ApiModelProperty(value = "考核状态(0：待反馈 1：已反馈 2：已核实 3：已完成 4: 待分配)")
    private Integer status;

}
