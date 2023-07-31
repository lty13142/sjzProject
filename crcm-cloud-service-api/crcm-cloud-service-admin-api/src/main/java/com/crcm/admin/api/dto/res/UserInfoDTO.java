package com.crcm.admin.api.dto.res;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName UserInfoDRO
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/26
 **/
@Data
public class UserInfoDTO implements Serializable {
    private static final long serialVersionUID = -3875020682871633512L;
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
    private List<MenuDTO> menus;
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
