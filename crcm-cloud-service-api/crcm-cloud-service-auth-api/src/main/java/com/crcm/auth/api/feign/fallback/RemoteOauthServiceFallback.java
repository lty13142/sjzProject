package com.crcm.auth.api.feign.fallback;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crcm.auth.api.feign.RemoteOauthService;
import com.crcm.core.response.RestResult;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

/**
 * @ClassName RemoteOauthServiceFallback
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Slf4j
public class RemoteOauthServiceFallback implements RemoteOauthService {
    @Setter
    private Throwable cause;

    @Override
    public JSONObject getToken(String type, String username, String password, String clientId, String secret, String scope, String authType,
                               String userType, String verificationCode, String key) {
        RestResult<Object> result = RestResult.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), cause.getMessage());
        return JSON.parseObject(JSON.toJSONString(result), JSONObject.class);
    }

    @Override
    public JSONObject getTokenByPhone(String type, String authType, String phoneNumber, String phoneCode, String clientId, String secret, String scope) {
        return null;
    }

    @Override
    public JSONObject getTokenByAuthorizationCode(String type, String code, String redirectUri, String clientId, String clientSecret) {
        return null;
    }

    @Override
    public JSONObject getTokenByRefreshToken(String grantModeRefreshToken, String refreshToken, String clientId, String clientSecret, String scope) {
        return null;
    }

    @Override
    public JSONObject getTokenByWeChat(String type, String authType, String unionId, String clientId, String secret, String scope) {
        return null;
    }

    @Override
    public JSONObject logout(String token) {
        return null;
    }
}
