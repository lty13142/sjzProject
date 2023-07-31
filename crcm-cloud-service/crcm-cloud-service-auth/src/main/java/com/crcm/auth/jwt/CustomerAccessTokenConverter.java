package com.crcm.auth.jwt;

import cn.hutool.extra.spring.SpringUtil;
import com.crcm.security.utils.CustomTokenInfoUtil;
import com.crcm.core.constant.AuthConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.Map;

/**
 * @ClassName CustomerAccessTokenConverter
 * @Description 加入自定义用户属性到token
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Slf4j
public class CustomerAccessTokenConverter extends DefaultAccessTokenConverter {

    public CustomerAccessTokenConverter() {
        log.info("Create --->  CustomerAccessTokenConverter ");
        super.setUserTokenConverter(new CustomerUserAuthenticationConverter());
    }

    private static class CustomerUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

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
            return CustomTokenInfoUtil.getAuthenticationUserInfo(authentication);
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
            if (map.containsKey(AuthConstants.USERNAME_PARAMETER_NAME)) {
                UserDetails user = SpringUtil.getApplicationContext().getBean(UserDetailsService.class)
                        .loadUserByUsername((String) map.get(AuthConstants.USERNAME_PARAMETER_NAME));
                return new UsernamePasswordAuthenticationToken(user, "N/A", user.getAuthorities());
            } else {
                return null;
            }
        }
    }
}
