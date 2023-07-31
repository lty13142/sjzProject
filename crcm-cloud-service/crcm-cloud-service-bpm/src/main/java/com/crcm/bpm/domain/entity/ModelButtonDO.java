package com.crcm.bpm.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 流程节点按钮对象
 * true
 * @author zwj
 * @date 2020-11-02
 */
@Setter
@Getter
@ToString
@TableName("bpm_model_button")
public class ModelButtonDO extends BaseEntity {
    private static final Long serialVersionUID = 1L;

    /** 表单编号 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 节点编号 */
    private String nodeId;

    /** 流程部署ID */
    private String definitionId;

    /** 流程模型唯一KEY */
    private String processKey;

    /**流程模型版本*/
    private Integer modelVersion;

    /** 按钮名称(字典表按钮别名) */
    private String buttonName;

    /** 按钮值(字典表按钮值) */
    private String buttonValue;

    /** 状态*/
    private Integer status;

    /** 备注 */
    private String remark;

    /***
     * 逻辑删除 不使用isDelete，避免RPC框架在反向解析时报错
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
