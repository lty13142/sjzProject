package com.crcm.admin.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserRoleEditVO
 * @Description 用户角色修改类
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/4/13
 **/
@Data
public class UserRoleEditVO implements Serializable {
    private static final long serialVersionUID = -6114035176497467482L;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;
    /**
     * 逗号分隔的角色ID
     */
    @ApiModelProperty(value = "逗号分隔的角色ID")
    private String roleIds;
}
