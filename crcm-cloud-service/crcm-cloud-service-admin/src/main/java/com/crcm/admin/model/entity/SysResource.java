package com.crcm.admin.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.admin.utils.UtilDic;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.crcm.core.constant.SystemConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>Title:系统功能表 </p>
 * <p>Description:系统功能表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 *
 * @author ${USER}
 * @version 1.0
 * @Date 2019-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("系统资源")
@TableName("sys_resource")
public class SysResource extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id：bigint(20) ==》 id：Long
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 资源名称
     * name：varchar(64) ==》 name：String
     */
    @ApiModelProperty(value = "资源名称")
    @NotBlank(message = "资源名称 不能为空")
    private String name;
    /**
     * 上级ID
     * pid：bigint(20) ==》 pid：Long
     */
    @ApiModelProperty(value = "上级ID")
    private Long pid;
    /**
     * 备注
     * description：varchar(255) ==》 description：String
     */
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 是否启用
     * enabled：varchar(16) ==》 enabled：String
     */
    @ApiModelProperty(value = "是否启用 0未启用，1启用，-1停用")
    private String enabled;
    /**
     * 资源URL
     * value：varchar(1000) ==》 value：String
     */
    @ApiModelProperty(value = "资源值")
    private String value;
    /**
     * 类型
     * type：tinyint(1) ==》 type：Integer
     */
    @ApiModelProperty(value = "类型，0 功能，1 目录")
    private Integer type;
    /**
     * 鉴权方式 1 权限匹配，2 路径匹配
     * auth_type：tinyint(1) ==》 authType：Integer
     */
    @ApiModelProperty(value = "鉴权方式 1 权限匹配，2 路径匹配")
    private Integer authType;

    /***
     * 逻辑删除 0 未删除 1删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer  deleted;

    /**
     * 获取翻译
     * @return
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getEnabledCh() {
        return UtilDic.getDictName(SystemConstant.ENABLE_STATUS.CODE,this.enabled);
    }
    /**
     * 获取翻译
     * @return
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getAuthTypeCh() {
        return UtilDic.getDictName(SystemConstant.RESOURCE_AUTH_TYPE.CODE,this.authType +"");
    }
}