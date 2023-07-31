package com.crcm.cloud.start.sso.components;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.crcm.cloud.start.sso.constants.Oauth2ExceptionEnum;
import com.crcm.cloud.start.sso.domain.AuthUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @ClassName UserAuthenticationConverter
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/7/26
 **/
public class UserAuthenticationConverter implements org.springframework.security.oauth2.provider.token.UserAuthenticationConverter {

    private static final String PASSWORD = "password";
    private static final String N_A = "N/A";
    private static final String USERNAME = "user_name";
    private static final String AUTHORITIES = "authorities";
    /**
     * 匿名用户{@value}
     */
    private static final String ANONYMOUS_USER = "anonymousUser";
    /**
     * 自定义token中用户信息
     *
     * @param authentication 用户认证信息
     * @return java.util.Map<java.lang.String, ?>
     * @author qipp
     * @date 2020/1/17 11:56
     */
    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        return getAuthenticationUserInfo(authentication);
    }

    /**
     * 刷新token时无 Authentication 对象
     * 需要实现此接口返回Authentication 对象
     *
     * @param map 用户认证信息
     * @return org.springframework.security.core.Authentication
     * @author qipp
     * @date 2020/2/28 15:37
     */
    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey(USERNAME)) {
            Collection<? extends GrantedAuthority> authorities = getAuthorities(map);
            Set<SimpleGrantedAuthority> authoritySet = new HashSet<>();
            if (!CollectionUtils.isEmpty(authorities)) {
                authorities.stream().forEach(authority ->
                        authoritySet.add(new SimpleGrantedAuthority(authority.getAuthority()))
                );
            }
            CopyOptions options = CopyOptions.create();
            options.setIgnoreProperties(AUTHORITIES);
            AuthUser account = BeanUtil.mapToBean(map, AuthUser.class, false, options);
            account.setAuthorities(authoritySet);
            return new UsernamePasswordAuthenticationToken(account, N_A, authorities);
        }
        return null;
    }

    public static Map<String, Object> getAuthenticationUserInfo(Authentication authentication) {
        // 自定义token信息中添加的信息
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        // 拿到用户认证信息
        Object principal = authentication.getPrincipal();
        // 如果是匿名用户
        if(ANONYMOUS_USER.equals(principal.toString())){
            response.put("principal",principal);
            return response;
        }
        Map<String, Object> map = BeanUtil.beanToMap(principal);
        response.putAll(map);
        //判断权限集合是否为空
        if (!CollectionUtils.isEmpty(authentication.getAuthorities())) {
            // 处理角色权限到一个集合中
            Set<String> authenticationStringSet = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
            // 设置角色权限
            response.put(AUTHORITIES, authenticationStringSet);
        }
        return response;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
        Object authorities = map.get(AUTHORITIES);
        if (authorities instanceof String) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
        }
        if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
                    .collectionToCommaDelimitedString((Collection<?>) authorities));
        }

        throw new OAuth2Exception(Oauth2ExceptionEnum.USER_PERMISSION_DENIED.getMsg());
//        throw new AccessDeniedException("Authorities must be either a String or a Collection");
    }
}
