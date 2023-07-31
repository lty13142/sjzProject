package com.crcm.cloud.start.sso.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

/**
 * @ClassName AuthUser
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/7/27
 **/
@Data
public class AuthUser implements UserDetails {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 电话号
     */
    private String phone;
    /**
     * 权限
     */
    private Set<? extends GrantedAuthority> authorities;

    /**
     * 组织ID
     */
    private Set<String> orgIds;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 用户类型（0--监管账号 1--企业账号，字典：USER_TYPE）
     */
    private Integer userType;
    /**
     * 用户详细类型(字典：USER_DETAIL_TYPE)
     */
    private Integer userDetailType;
    /**
     * 所属镇区行政区划代码
     */
    private String areaCode;
    /**
     * 所属镇区
     */
    private String area;

    /**
     * 昵称
     */
    private String nickName;


    private String villageCode;

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
