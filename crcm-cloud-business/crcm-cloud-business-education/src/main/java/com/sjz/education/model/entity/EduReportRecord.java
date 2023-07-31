package com.sjz.education.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 德育积分_积分上报记录
 * @TableName edu_report_record
 * @author: sssccc
 */
@TableName(value ="edu_report_record")
@Data
@Accessors(chain = true)
public class EduReportRecord extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 村民id
     */
    private String userId;

    /**
     * 村民姓名
     */
    private String userName;

    /**
     * 积分规则id
     */
    @NotBlank(message = "积分规则不能为空")
    private String ruleId;

    /**
     * 文字说明
     */
    @NotBlank(message = "文字说明不能为空")
    private String textDescription;

    /**
     * 上传图片，可多个
     */
    @NotBlank(message = "现场图片不能为空")
    private String imagePath;

    /**
     * 审核状态：0：待审核，1：通过，：-1：不通过
     */
    private Integer status;

    /**
     * 审核意见
     */
    private String reviewComment;

    /**
     * 获得积分
     */
    private Integer earnPoints;

    /**
     * 所属村庄
     */
    @ApiModelProperty(value = "所属村庄")
    private String villageName;
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

    /**
     * 积分规则详情
     */
    @TableField(exist = false)
    private EduPointsRule pointsRule;

    /**
     * 积分规则详情上级
     */
    @TableField(exist = false)
    private EduPointsRule pointsRuleFather;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}