package com.crcm.admin.model.dto.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ResourceDTO
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/4/20
 **/
@Data
public class ResourceDTO implements Serializable {
    private static final long serialVersionUID = 7561093967159468924L;
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;
    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称")
    private String name;
    /**
     * 备注
     */
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用 0未启用，1启用，-1停用")
    private String enabled;
    /**
     * 资源URL
     */
    @ApiModelProperty(value = "资源URL")
    private String url;
    /**
     * 允许访问资源的角色，逗号分隔
     */
    private String roles;

}
