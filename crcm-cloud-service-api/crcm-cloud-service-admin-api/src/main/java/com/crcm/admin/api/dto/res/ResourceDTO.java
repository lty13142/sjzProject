package com.crcm.admin.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ResourceDTO
 * @Description 系统资源数据DTO
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/28
 **/
@Data
@ApiModel("系统资源数据DTO")
public class ResourceDTO implements Serializable {
    private static final long serialVersionUID = 4164872868564421870L;
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
     * 资源URL
     */
    @ApiModelProperty(value = "资源值")
    private String value;
}
