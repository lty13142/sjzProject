package com.crcm.admin.model.dto;


import com.crcm.admin.model.entity.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * 角色表查询DTO
 */
@Data
@ApiModel("角色表查询DTO")
public class SysRoleQueryDTO extends QueryDTO {

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 授权标识
     */
    @ApiModelProperty(value = "授权标识")
    private String value;

    /**
     * 机构
     */
    @ApiModelProperty(value = "机构")
    private String organize;

    /**
     * 是否可用 1:是 0:否
     */
    @ApiModelProperty(value = "是否可用")
    private Integer enabled;

}
