package com.crcm.admin.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonRawValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName SysRouteConf
 * @Description 系统路由
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/5/8
 **/
@Data
@ApiModel(value = "系统路由")
@TableName("sys_gateway_route")
public class SysGatewayRoute implements Serializable {

    private static final long serialVersionUID = -7510295236476729254L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 路由ID
     */
    @ApiModelProperty(value = "路由代码")
    private String routeCode;
    /**
     * 路由名称
     */
    @ApiModelProperty(value = "路由名称")
    private String routeName;
    /**
     * 断言
     */
    @ApiModelProperty(value = "断言")
    @JsonRawValue
    private String routePredicates;
    /**
     * 过滤器
     */
    @ApiModelProperty(value = "过滤器")
    @JsonRawValue
    private String routeFilters;
    /**
     * 路由地址
     */
    @ApiModelProperty(value = "路由地址")
    private String routeUri;
    /**
     * 路由描述
     */
    @ApiModelProperty(value = "路由描述")
    private String routeDesc;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer routeOrder;
    /**
     * 状态 0 无效 1 有效
     */
    @ApiModelProperty(value = "是否启用 0未启用 1启用 -1 停用")
    private Integer enabled;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    /***
     * 是否删除
     */
    @TableLogic
    @ApiModelProperty(value = "是否删除")
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
