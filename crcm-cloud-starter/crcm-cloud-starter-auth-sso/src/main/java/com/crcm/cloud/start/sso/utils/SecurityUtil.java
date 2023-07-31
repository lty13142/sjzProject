package com.crcm.cloud.start.sso.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crcm.cloud.start.sso.domain.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName SecurityUtil
 * @Description 系统权限工具
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/25
 **/
@Slf4j
public class SecurityUtil {

    private static final String ROLE_PREFIX = "ROLE_";

    /**
     * 获取在线用户信息
     *
     * @return CurrentUser 当前用户信息
     */
    public static AuthUser getCurrentUser() {
        try {
            Object principal = getOauth2Authentication().getPrincipal();
            if (principal instanceof UserDetails) {
                return BeanUtil.copyProperties(principal, AuthUser.class);
            } else if (principal instanceof String) {
                AuthUser user = new AuthUser();
                user.setUsername((String) principal);
                return user;
            }
        } catch (Exception e) {
            log.error("获取当前用户信息失败", e);
        }
        return null;
    }

    /**
     * 获取当前用户ID
     *
     * @return String 用户ID
     */
    public static String getCurrentUserId() {
        try {
            return getCurrentUser().getUserId();
        } catch (Exception e) {
            log.error("获取当前用户ID失败", e);
        }
        return null;
    }

    /**
     * 获取当前用户名称
     *
     * @return String 用户名
     */
    public static String getCurrentUsername() {
        try {
            Object principal = getOauth2Authentication().getPrincipal();
            if (principal == null) {
                return null;
            }
            if (principal instanceof UserDetails) {
                return BeanUtil.copyProperties(principal, AuthUser.class).getUsername();
            }
            return (String) principal;
        } catch (Exception e) {
            log.error("获取当前用户名失败", e);
        }
        return null;
    }

    /**
     * 获取用户角色信息
     *
     * @return 角色集合
     */
    public static List<String> getCurrentUserRoles() {
        Authentication authentication = getOauth2Authentication();
        if (authentication == null) {
            return new ArrayList<>();
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (CollectionUtil.isEmpty(authorities)) {
            return new ArrayList<>();
        }
        List<String> roles = new ArrayList<>();
        authorities.stream()
                .filter(granted -> StrUtil.startWith(granted.getAuthority(), ROLE_PREFIX))
                .forEach(granted -> {
                    roles.add(StrUtil.removePrefix(granted.getAuthority(), ROLE_PREFIX));
                });
        return roles;
    }

    /**
     * 获取用户角色信息
     *
     * @return 角色集合
     */
    public static List<String> getCurrentUserRoleNames() {
        Authentication authentication = getOauth2Authentication();
        if (authentication == null) {
            return new ArrayList<>();
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (CollectionUtil.isEmpty(authorities)) {
            return new ArrayList<>();
        }
        List<String> roleNames = new ArrayList<>();

        authorities.stream()
                .filter(granted -> StrUtil.startWith(granted.getAuthority(), ROLE_PREFIX))
                .forEach(granted -> {
                    JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(granted));
                    roleNames.add(jsonObject.getString("name"));
                });
        return roleNames;
    }

    /**
     * 获取当前用户权限集
     *
     * @return Collection<GrantedAuthority>权限集
     */
    public static Collection<GrantedAuthority> getCurrentUserAuthority() {
        return getOauth2Authentication().getAuthorities();
    }

    /**
     * 获取当前令牌内容
     *
     * @return String 令牌内容
     */
    public static String getCurrentTokenValue() {
        try {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) getOauth2Authentication().getDetails();
            return details.getTokenValue();
        } catch (Exception ignore) {
            return null;
        }
    }


    private static OAuth2Authentication getOauth2Authentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            AnonymousAuthenticationToken anonymousToken = (AnonymousAuthenticationToken) authentication;
            return new OAuth2Authentication(null, anonymousToken);
        }
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
            return new OAuth2Authentication(null, token);
        }
        return (OAuth2Authentication) authentication;
    }

    @SuppressWarnings("all")
    private static LinkedHashMap<String, Object> getAuthenticationDetails() {
        return (LinkedHashMap<String, Object>) getOauth2Authentication().getUserAuthentication().getDetails();
    }

    public static OAuth2Authentication getOAuth2Authentication() {
        return getOauth2Authentication();
    }
}
