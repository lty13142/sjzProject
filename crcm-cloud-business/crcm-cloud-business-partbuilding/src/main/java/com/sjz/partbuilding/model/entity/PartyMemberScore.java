package com.sjz.partbuilding.model.entity;



import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.sjz.partbuilding.enums.MemberScoreTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>Title:党员积分管理 </p>
 * @Date  2023-04-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("党员积分管理")
@TableName("dj_party_member_score")
public class PartyMemberScore extends BaseEntity implements Serializable{
    private static final long serialVersionUID=1L;

    /**
     * ID
     * id：varchar(32) ==》 id：String
     */
    @ApiModelProperty(value = "ID")
        @TableId(value = "id", type = IdType.UUID)
        private String id;
    /**
     * 党员姓名
     * name：varchar(32) ==》 name：String
     */
    @ApiModelProperty(value = "党员姓名")
    private String name;
    /**
     * 党员ID
     * user_id：varchar(32) ==》 userId：String
     */
    @ApiModelProperty(value = "党员ID")
    private String userId;
    /**
     * 加减分类型：1-加分，2-减分
     * score_type：varchar(10) ==》 scoreType：String
     */
    @ApiModelProperty(value = "加减分类型：1-加分，2-减分")
    private String scoreType;

    @TableField(exist = false)
    private String scoreTypeCh;

    /**
     * 原因描述
     * reason：varchar(100) ==》 reason：String
     */
    @ApiModelProperty(value = "原因描述")
    private String reason;
    /**
     * 分值
     * score：decimal(12,2) ==》 score：BigDecimal
     */
    @ApiModelProperty(value = "分值", example = "0")
    private BigDecimal score;
    /**
     * 指标ID
     * target_id：varchar(32) ==》 targetId：String
     */
    @ApiModelProperty(value = "指标ID")
    private String targetId;
    /**
     * 指标名称
     * target_name：varchar(64) ==》 targetName：String
     */
    @ApiModelProperty(value = "指标名称")
    private String targetName;
    /**
     * 备注
     * remark：varchar(400) ==》 remark：String
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 附件
     * attachment：varchar(500) ==》 attachment：String
     */
    @ApiModelProperty(value = "附件")
    private String attachment;

    @TableLogic
    private Integer deleted;


    public String getScoreTypeCh() {
        return MemberScoreTypeEnum.getValueByCode(Integer.parseInt(this.scoreType));
    }

}