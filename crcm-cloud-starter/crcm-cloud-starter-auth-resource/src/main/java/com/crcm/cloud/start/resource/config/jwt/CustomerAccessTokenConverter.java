package com.crcm.cloud.start.resource.config.jwt;

import com.crcm.core.constant.CustomTokenInfoConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @ClassName CustomerAccessTokenConverter
 * @Description 加入自定义用户属性到token
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/19
 **/
@Slf4j
public class CustomerAccessTokenConverter extends DefaultAccessTokenConverter {

    public CustomerAccessTokenConverter() {
        log.info("Create --->  CustomerAccessTokenConverter ");
        super.setUserTokenConverter(new CustomerUserAuthenticationConverter());
    }

    /**
     * 自定义生成token的转换器
     */
    private static class CustomerUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

        /**
         * 提取token中的认证信息
         * @param map token解密后的对象
         * @return org.springframework.security.core.Authentication
         */
        @Override
        public Authentication extractAuthentication(Map<String, ?> map) {
            // 提取生成token时设置的权限
            Collection<? extends GrantedAuthority> authorities = this.getAuthorities(map);
            return new UsernamePasswordAuthenticationToken(map, "N/A", authorities);
        }

        /**
         * 提取生成token时设置的权限
         *
         * @param map token中全部信息
         * @return java.util.Collection<? extends org.springframework.security.core.GrantedAuthority>
         */
        private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
            if (!map.containsKey(CustomTokenInfoConstants.AUTHORITIES)) {
                //参数不包含任何权限存入1长度的权限数组标识单纯的普通用户
                return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.arrayToCommaDelimitedString(new String[]{"ONLY_USER"}));
            } else {
                Object authorities = map.get(CustomTokenInfoConstants.AUTHORITIES);
                if (authorities instanceof String) {
                    return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
                } else if (authorities instanceof Collection) {
                    return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.collectionToCommaDelimitedString((Collection) authorities));
                } else {
                    throw new IllegalArgumentException("Authorities must be either a String or a Collection");
                }
            }
        }
    }
}
