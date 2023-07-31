package com.crcm.develop.system.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName UserInfoVo
 * @Description 前端用户信息
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/4/30 13:38
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
}
