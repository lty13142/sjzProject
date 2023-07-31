package com.crcm.bpm.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 审批意见表
 * </p>
 *
 * @author
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bpm_approve_opinion")
@ApiModel(value="ApproveOpinion对象", description="审批意见表")
public class ApproveOpinionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "任务id")
    private Long actTaskId;

    @ApiModelProperty(value = "任务名")
    private String taskName;

    @ApiModelProperty(value = "申请id")
    private Long applyId;

    @ApiModelProperty(value = "审批意见")
    private String approveOpinion;

    @ApiModelProperty(value = "审批操作")
    private String approveAction;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建人id")
    private String createUserId;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "修改人id")
    private String updateUserId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除状态")
    private Boolean deleted;

    /**
     * 头像
     */
    @TableField(exist = false)
    private String avatar;
}
