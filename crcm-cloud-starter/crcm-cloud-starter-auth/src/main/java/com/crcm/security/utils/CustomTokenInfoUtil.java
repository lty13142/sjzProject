package com.crcm.security.utils;

import com.crcm.security.bean.Authority;
import com.crcm.security.bean.Role;
import com.crcm.security.bean.UserAccount;
import com.crcm.security.constants.Oauth2ExceptionEnum;
import com.crcm.core.constant.CustomTokenInfoConstants;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @ClassName CustomTokenInfoUtil
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
public class CustomTokenInfoUtil {
    /**
     * 匿名用户{@value}
     */
    private static final String ANONYMOUS_USER = "anonymousUser";
    public static Map<String, Object> getAuthenticationUserInfo(Authentication authentication) {

        // 拿到用户认证信息
        Object principal = authentication.getPrincipal();
        // 如果是匿名用户
        if(ANONYMOUS_USER.equals(principal.toString())){
            // 用户未登录
            throw new UsernameNotFoundException(Oauth2ExceptionEnum.USER_NOT_LOGGED_IN.getMsg());
        }
        UserAccount userAccount = (UserAccount) principal;

        // 用户ID
        String userId = userAccount.getUserId();
        // 自定义token信息中添加的信息
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        // 用户名
        response.put(CustomTokenInfoConstants.USERNAME, userAccount.getUsername());
        // 手机号
        response.put(CustomTokenInfoConstants.PHONE, userAccount.getPhone());
        // 是否设置密码
        response.put(CustomTokenInfoConstants.PASSWORD, StringUtils.isNotEmpty(userAccount.getPassword()));

        //判断权限集合是否为空
        if (!CollectionUtils.isEmpty(authentication.getAuthorities())) {
            // 处理角色权限到一个集合中
            Set<String> authenticationStringSet = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

            // 设置角色权限
            response.put(CustomTokenInfoConstants.AUTHORITIES, authenticationStringSet);
            response.put(CustomTokenInfoConstants.SIMPLE_AUTHORITIES, createSimpleAuthorities(authentication.getAuthorities()));
            //游客不装载用户ID
            response.put(CustomTokenInfoConstants.USER_ID, authenticationStringSet.contains(CustomTokenInfoConstants.TOURIST_ROLE) ? null : userId);
        } else {
            response.put(CustomTokenInfoConstants.USER_ID, userId);
        }
        return response;
    }

    /**
     * 获取简易角色权限
     * @param authorities 权限集合
     * @return
     */
    private static List<String> createSimpleAuthorities(Collection<? extends GrantedAuthority> authorities) {
        List<String> simpleAuthorities = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority instanceof Role) {
                simpleAuthorities.add(((Role) grantedAuthority).getValue());
                continue;
            }
            if (grantedAuthority instanceof Authority) {
                simpleAuthorities.add(((Authority) grantedAuthority).getValue());
            }
        }
        return simpleAuthorities;
    }
}
