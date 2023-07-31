package com.sjz.partbuilding.model.vo.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName UserInfoVo
 * @Description：前端用户信息
 * @Copyright：Copyright(c) 2019
 * @Company：中再云图科技有限公司
 **/
@Data
public class UserInfoVo implements Serializable {
    private static final long serialVersionUID = -5763477450421517616L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 角色
     */
    private List<String> roles;
    /**
     * 菜单
     */
//    private List<MenuVo> menus;
    /**
     * 介绍
     */
    private String comment;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户ID
     */
    private String id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * email地址
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;

    /**
     * 组织类别
     */
    private Boolean orgType;
    /**
     * 组织id
     */
    private String orgId;

    /**
     * “1”是系统管理员(包括子系统管理员)，0不是系统管理员
     */
    private int isManager;
}
