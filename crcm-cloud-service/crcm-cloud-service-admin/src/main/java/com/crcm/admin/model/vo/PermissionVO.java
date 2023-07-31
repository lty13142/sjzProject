package com.crcm.admin.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName PermissionVO
 * @Description 权限数据传输类
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/5/21 10:38
 **/
@Data
public class PermissionVO implements Serializable {

    private static final long serialVersionUID = 2257499569660672934L;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", required = true)
    private Long roleId;
    /**
     * 资源ID
     */
    @ApiModelProperty(value = "资源ID", hidden = true)
    private Long permissionId;
    /**
     * 逗号分隔的菜单/资源ID
     */
    @ApiModelProperty(value = "逗号分隔选中的菜单id", required = true)
    private String ids;
    /**
     * 逗号分隔的菜单/资源ID
     */
    @ApiModelProperty(value = "逗号分隔半选中的菜单id")
    private String halfCheckIds;
    /**
     * 权限类型
     */
    @ApiModelProperty(value = "权限类型", hidden = true)
    private Integer type;

    /**
     * 菜单所属
     */
    @ApiModelProperty(value = "菜单所属 0:PC  1:APP")
    private Integer menuBelong;

}
