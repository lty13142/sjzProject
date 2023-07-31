package com.crcm.auth.jwt;

import com.crcm.security.bean.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CustomTokenEnhancer
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/17
 **/
@Slf4j
public class CustomTokenEnhancer implements TokenEnhancer {
    /**
     * 用户id属性
     */
    private static final String USER_ID = "userId";

    /**
     * 用户密码属性
     */
    private static final String USER_PASSWORD = "password";

    /**
     * 用户名属性
     */
    private static final String USER_USERNAME = "username";
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        log.info("enhance --->  CustomTokenEnhancer ");
        final Map<String, Object> additionalInfo = new HashMap<>(8);
        if (null == oAuth2Authentication.getDetails()) {
            // 用户账户ID
            additionalInfo.put(USER_ID, null);
            // 是否设置密码
            additionalInfo.put(USER_PASSWORD, false);
            // 用户名
            additionalInfo.put(USER_USERNAME, null);
        } else {
            // 当前用户详情
            UserAccount userAccount = (UserAccount) oAuth2Authentication.getPrincipal();

            // 用户账户ID
            additionalInfo.put(USER_ID, userAccount.getUserId());
            // 是否设置密码
            additionalInfo.put(USER_PASSWORD, StringUtils.isNotEmpty(userAccount.getPassword()));
            // 用户名
            additionalInfo.put(USER_USERNAME, userAccount.getUsername());
        }
        // 设置到token中
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        return oAuth2AccessToken;
    }
}
