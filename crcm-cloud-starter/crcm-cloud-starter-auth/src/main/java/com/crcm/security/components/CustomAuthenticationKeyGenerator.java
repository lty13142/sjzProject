package com.crcm.security.components;

import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @ClassName CustomAuthenticationKeyGenerator
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/30
 **/
public class CustomAuthenticationKeyGenerator extends DefaultAuthenticationKeyGenerator {
    private static final String SCOPE = "scope";
    private static final String USERNAME = "username";
    private static final String CLIENT_ID = "clientId";

    @Override
    public String extractKey(OAuth2Authentication authentication) {
        Map<String, String> values = new LinkedHashMap<>();
        OAuth2Request authorizationRequest = authentication.getOAuth2Request();

        // 单点登出需要去除clientId,后续优化
//        values.put(CLIENT_ID, authorizationRequest.getClientId());

        if (!authentication.isClientOnly()) {
            values.put(USERNAME, authentication.getName());
        }

        if (authorizationRequest.getScope() != null) {
            values.put(SCOPE, OAuth2Utils.formatParameterList(new TreeSet<>(authorizationRequest.getScope())));
        }
        // 如果是多租户系统，这里要区分租户ID 条件
        return generateKey(values);
    }
}
