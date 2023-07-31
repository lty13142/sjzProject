package com.crcm.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title:系统用户-角色表 </p>
 * <p>Description:系统用户-角色表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 * @author diaoy
 * @version 1.0
 * @Date 2019-04-29
 */
@Data
@ApiModel("系统用户-角色表")
@TableName("sys_user_role")
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     * user_id：varchar(32) ==》 userId：String
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;
    /**
     * 角色ID
     * role_id：varchar(32) ==》 roleId：String
     */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    public SysUserRole(String userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public SysUserRole() {
    }
}