package com.crcm.admin.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName UserDTO
 * @Description 系统用户数据
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/1
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "系统用户信息")
public class UserAccountDTO implements Serializable {
    private static final long serialVersionUID = 9216189456214940227L;
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private String id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码")
    private String password;
    /**
     * 电话号码
     */
    @ApiModelProperty(value = "电话号码")
    private String phone;
    /**
     * 邮箱地址
     */
    @ApiModelProperty(value = "邮箱地址")
    private String email;

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
     * 是否过期
     */
    @ApiModelProperty(value = "是否过期")
    private Integer expired;
    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;
    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String avatar;
    /**
     * 角色集合
     */
    @ApiModelProperty(value = "角色集合")
    private List<RoleDTO> roles;
    /**
     * 系统资源集合（权限匹配）
     */
    @ApiModelProperty(value = "系统资源集合（权限匹配）")
    private List<ResourceDTO> resources;
    /**
     * 组织ID
     */
    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    /**
     * 用户类型（0--监管账号 1--企业账号，字典：USER_TYPE）
     */
    @ApiModelProperty(value = "用户类型（0--监管账号 1--企业账号，字典：USER_TYPE）")
    private Integer userType;
    /**
     * 用户详细类型(字典：USER_DETAIL_TYPE)
     */
    @ApiModelProperty(value = "用户详细类型(字典：USER_DETAIL_TYPE)")
    private Integer userDetailType;
    /**
     * 所属镇区行政区划代码
     */
    @ApiModelProperty(value = "所属镇区行政区划代码")
    private String areaCode;
    /**
     * 所属镇区
     */
    @ApiModelProperty(value = "所属镇区")
    private String area;
    /**
     * 微信唯一id
     */
    @ApiModelProperty(value = "微信唯一id")
    private String openId;

    @ApiModelProperty(value = "村庄代码")
    private String villageCode;
    /**
     * selectedCode
     */
    @ApiModelProperty(value = "选中的区域code")
    private String selectedCode;
}
