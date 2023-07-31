package com.crcm.admin.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName AddPermissionVO
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/4/19
 **/
@Data
public class AddPermissionVO implements Serializable {
    private static final long serialVersionUID = -6270748955867756231L;
    /**
     * 权限ID
     */
    @NotBlank(message = "权限ID不能为空")
    private String permissionId;
    /**
     * 角色ID，逗号分隔
     */
    @NotBlank(message = "角色ID不能为空")
    private String roleIds;
    /**
     * 权限类型
     */
    private Integer type;
}
