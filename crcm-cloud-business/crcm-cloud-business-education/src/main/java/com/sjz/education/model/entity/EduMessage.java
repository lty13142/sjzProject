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
 * 德育积分_消息通知管理
 *
 * @author: sssccc
 * @TableName edu_message
 */
@TableName(value = "edu_message")
@Data
@Accessors(chain = true)
public class EduMessage extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 正文
     */
    @NotBlank(message = "正文不能为空")
    private String content;

    /**
     * 消息类型：1：全体通知，2：部分通知
     */
    @NotNull(message = "消息类型不能为空")
    private Integer msgType;

    /**
     * 通知对象id
     */
    private String targetId;

    /**
     * 状态：0：未发布，1：已发布
     */
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
    private List<String> targetIds;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}