package com.crcm.admin.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName AddUserRoleVO
 * @Description 添加用户角色对象
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/4/19
 **/
@Data
public class AddUserRoleVO implements Serializable {
    private static final long serialVersionUID = 1330358070333873463L;
    /**
     * 角色ID
     */
    @NotNull(message = "角色ID不能为空")
    private Long roleId;
    /**
     * 用户ID，逗号分隔
     */
    @NotBlank(message = "用户ID不能为空")
    private String userIds;
}
