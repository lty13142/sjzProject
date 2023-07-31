package com.crcm.admin.model.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UserQueryDTO
 * @Description 用户查询 DTO
 * @Author GZL
 * @Date 2023/2/8 14:40
 * @Version 1.0
 **/
@Data
public class UserQueryDTO {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    private Integer enabled;
    /**
     * 是否锁定
     */
    @ApiModelProperty(value = "是否锁定")
    private Integer locked;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "用户姓名")
    private String nickName;

    /**
     * 所属镇区行政区划代码
     */
    @ApiModelProperty(value = "所属镇区行政区划代码")
    private String areaCode;


    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型", hidden = true)
    private Integer userType;

    /**
     * 用户详细类型
     */
    @ApiModelProperty(value = "用户详细类型", hidden = true)
    private Integer userDetailType;

    /**
     * 角色标识
     */
    @ApiModelProperty(value = "角色标识", hidden = true)
    private String roleValue;

}
