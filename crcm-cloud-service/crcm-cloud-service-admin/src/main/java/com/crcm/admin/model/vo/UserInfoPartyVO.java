package com.crcm.admin.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.crcm.admin.utils.UtilDic;
import com.crcm.core.constant.SystemConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author xiangmx
 * @createTime 2023/4/6 14:50
 **/
@Data
public class UserInfoPartyVO implements Serializable {
    private static final long serialVersionUID = -5763477450421517616L;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 角色
     */
    @ApiModelProperty(value = "角色列表")
    private List<String> roles;
    /**
     * 菜单
     */
    @ApiModelProperty(value = "菜单列表")
    private List<MenuVO> menus;
    /**
     * 介绍
     */
    @ApiModelProperty(value = "用户简介")
    private String comment;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String id;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;
    /**
     * email地址
     */
    @ApiModelProperty(value = "email地址")
    private String email;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 用户类型（0--监管账号 1--企业账号）
     */
    @ApiModelProperty(value = "用户类型（0--监管账号 1--企业账号，字典：USER_TYPE）")
    private Integer userType;
    /**
     * 用户详细类型(字典：USER_DETAIL_TYPE)
     */
    @ApiModelProperty(value = "用户详细类型(字典：USER_DETAIL_TYPE)")
    private Integer userDetailType;
    /**
     * 是否是管理员
     */
    @TableField(exist = false)
    private int isManager;

    @TableField(exist = false)
    private Long orgId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getUserTypeCh() {
        return UtilDic.getDictName(SystemConstant.USER_TYPE.CODE, this.userType + "");
    }

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    public String getUserDetailTypeCh() {
//        return UtilDic.getDictName(Objects.equals(SystemConstant.USER_TYPE.ENTERPRISE_ACCOUNT, this.userType) ?
//                SystemConstant.ENTERPRISE_USER_TYPE.CODE : SystemConstant.SUPERVISE_USER_TYPE.CODE, this.getUserDetailTypeCh() + "");
//    }
}
