package com.crcm.admin.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName SysRolePermissionVo
 * @Description 系统角色权限数据
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/5/9 17:04
 **/
@Data
@Accessors(chain = true)
public class SysRolePermissionVO implements Serializable {
    private static final long serialVersionUID = 9036359632133065403L;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色授权标识
     */
    private String value;
    /**
     * 角色组织
     */
    private String organize;
    /**
     * 权限ID
     */
    private String permissionId;
}
