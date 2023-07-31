package com.crcm.security.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.exception.CustomException;
import com.crcm.security.bean.UserAccount;
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
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/25
 **/
@Slf4j
public class SecurityUtil {
    /**
     * 获取在线用户信息
     *
     * @return CurrentUser 当前用户信息
     */
    public static UserAccount getCurrentUser() {
        return getUser(getOauth2Authentication());
    }

    /**
     * 获取在线用户信息
     *
     * @return CurrentUser 当前用户信息
     */
    public static UserAccount getCurrentUserNoNull() {
        Authentication authentication = getOauth2Authentication();
        if (authentication == null) {
            throw new CustomException(HttpStatus.HTTP_UNAUTHORIZED, "请登录后再进行操作!");
        }
        return getUser(authentication);
    }

    /**
     * 获取用户
     *
     * @param authentication <p>
     *                       获取当前用户的全部信息
     */
    public static UserAccount getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserAccount) {
            return (UserAccount) principal;
        } else if (principal instanceof UserDetails) {
            return (UserAccount) principal;
        } else if (principal instanceof String) {
            UserAccount userAccount = new UserAccount();
            userAccount.setUsername((String) principal);
            return userAccount;
        }
        return  null;
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
            if (principal instanceof UserAccount) {
                return ((UserAccount) principal).getUsername();
            }
            return (String) principal;
        } catch (Exception e) {
            log.error("获取当前用户名失败", e);
        }
        return null;
    }

    /**
     * 获取当前用户名称
     *
     * @return String 用户名
     */
    public static String getCurrentUserId() {
        try {
            Object principal = getOauth2Authentication().getPrincipal();
            if (principal == null) {
                return null;
            }
            if (principal instanceof UserAccount) {
                return ((UserAccount) principal).getUserId();
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
        List<String> roleIds = new ArrayList<>();
        authorities.stream()
                .filter(granted -> StrUtil.startWith(granted.getAuthority(), AuthConstants.ROLE_PREFIX))
                .forEach(granted -> {
                    roleIds.add(StrUtil.removePrefix(granted.getAuthority(), AuthConstants.ROLE_PREFIX));
                });
        return roleIds;
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
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken anonymousToken = (UsernamePasswordAuthenticationToken) authentication;
            return new OAuth2Authentication(null, anonymousToken);
        }
        return (OAuth2Authentication) authentication;
    }

    @SuppressWarnings("all")
    private static LinkedHashMap<String, Object> getAuthenticationDetails() {
        return (LinkedHashMap<String, Object>) getOauth2Authentication().getUserAuthentication().getDetails();
    }
}
