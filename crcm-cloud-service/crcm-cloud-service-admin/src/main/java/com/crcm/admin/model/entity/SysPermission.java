package com.crcm.admin.model.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @ClassName SysPermission
 * @Description 系统权限
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/1
 **/
@Data
@ApiModel("系统权限")
@TableName("sys_permission")
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     * role_id：bigint(20) ==》 role_id：Long
     */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
    /**
     * 权限ID
     * permission_id：bigint(20) ==》 permission_id：Long
     */
    @ApiModelProperty(value = "权限ID")
    private Long permissionId;
    /**
     * 权限类型
     * type：varchar(16) ==》 type：String
     */
    @ApiModelProperty(value = "权限类型")
    private Integer type;

    /**
     * 是否选中（0：未选中，1选中）
     * check_flag：int ==》 checkFlag：Integer
     */
    @ApiModelProperty(value = "是否选中（0：未选中，1：已选中）")
    private Integer checkFlag;
    public SysPermission(Long roleId, Long permissionId, Integer type) {
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.type = type;
    }

    public SysPermission(Long roleId, Long permissionId, Integer checkFlag, Integer type) {
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.type = type;
        this.checkFlag = checkFlag;
    }


}