package com.crcm.develop.core.conf.security.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @ClassName SecurityUserDetails
 * @Description 权限用户信息
 * @Copyright Copyright(c) 2020
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2020/5/14
 **/
@Data
@Builder
public class SecurityUserDetails implements UserDetails {
    /**
     * 用户ID
     */
    private String id;
    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private final Set<SecurityRole> authorities;
    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 租户ID
     */
    private String tenantId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}

