package com.crcm.admin.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SysRolePermissionVo
 * @Description
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/5/9 17:04
 **/
@Data
public class RolePermissionVO implements Serializable {
    private static final long serialVersionUID = 9036359632133065403L;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 角色授权标识
     */
    private String authorizedSigns;
    /**
     * 功能ID
     */
    private String actionId;
}
