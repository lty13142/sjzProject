package com.sjz.education.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 积分规则管理
 *
 * @author: sssccc
 * @TableName edu_points_rule
 */
@TableName(value = "edu_points_rule")
@Data
@Accessors(chain = true)
public class EduPointsRule extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 规则名称
     */
    @NotBlank(message = "规则名称不能为空")
    @ApiModelProperty(value = "规则名称", required = true)
    private String ruleName;

    /**
     * 父级id
     */
    @ApiModelProperty(value = "父级id")
    private String pid;
    /**
     * 父级ids
     */
    @ApiModelProperty(value = "父级ids")
    private String pidCollect;

    /**
     * 层级编号
     */
    @ApiModelProperty(value = "层级编号")
    private Integer number;

    /**
     * 赋分方式
     */
    @ApiModelProperty(value = "赋分方式")
    private String scoringMethod;

    /**
     * 年度计分规则
     */
    @ApiModelProperty(value = "年度计分规则")
    private String ruleDetail;

    /**
     * 可获得积分
     */
    @ApiModelProperty(value = "可获得积分")
    private Integer earnPoints;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 状态：0：未发布，1：已发布
     */
    @ApiModelProperty(value = "状态：0：未发布，1：已发布")
    private Integer status;

    /**
     * 版本号  乐观锁注解 每次修改操作都必须传入，version不一致则无法修改，修改后自增
     */
//    @Version
//    @TableField(fill = FieldFill.INSERT_UPDATE, update = "%s+1")
//    @ApiModelProperty(value = "版本号")
//    private Integer version;
    /***
     * 逻辑删除 不使用isDelete，避免RPC框架在反向解析时报错
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;

    @TableField(exist = false)
    private List<String> pids;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}