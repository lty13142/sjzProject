package com.crcm.admin.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * 网关插件对象 t_gateway_plugins
 * true
 *
 * @author zzyt
 * @date 2022-06-10
 */
@Setter
@Getter
@ToString
@TableName("t_gateway_plugins")
public class GatewayPlugins extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 组件名称
     */
    @NotBlank(message = "组件名称不能为空!")
    private String compName;

    /**
     * 组件过滤器名称
     */
    @NotBlank(message = "组件过滤器名称不能为空!")
    private String compFilterName;

    /**
     * 组件类型
     */
    @NotBlank(message = "组件类型不能为空!")
    private String compType;

    /**
     * 组件排序
     */
    private Long compOrder;

    /**
     * 组件描述
     */
    private String compDesc;

    /**
     * 作用方式
     */
    @NotBlank(message = "作用方式不能为空!")
    private String compMethod;

    /**
     * 组件参数表单
     */
    private String compArgs;

    /***
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    /**
     * 是否启用 0未启用 1启用 -1 停用
     */
    private Integer enabled;

}
