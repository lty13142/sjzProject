package com.crcm.security.handle;

import com.crcm.core.response.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;

/**
 * @ClassName Oauth2WebResponseExceptionTranslator
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Slf4j
public class Oauth2WebResponseExceptionTranslator implements WebResponseExceptionTranslator {

    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    /**
     * 无效的刷新令牌{@value}
     */
    private static final String INVALID_REFRESH_TOKEN = "Invalid refresh token";

    /**
     * 无效的授权码{@value}
     */
    private static final String INVALID_AUTHORIZATION_CODE = "Invalid authorization code";
    /**
     * 无效的作用域
     */
    private static final String INVALID_SCOPE = "invalid_scope";

    /**
     * 无效的准予(授权码或refresh token无效){@value}
     */
    private static final String INVALID_GRANT = "invalid_grant";

    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        // Try to extract a SpringSecurityException from the stacktrace
        Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);

        // 异常栈获取 OAuth2Exception 异常
        OAuth2Exception ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(
                OAuth2Exception.class, causeChain);
        if (ase != null) {
            return handleOauth2Exception(ase);
        }
        RestResult<Object> result = RestResult.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
    private ResponseEntity<RestResult<?>> handleOauth2Exception(OAuth2Exception e) {
        String message = e.getMessage();
        int status = e.getHttpErrorCode();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        if (status == HttpStatus.UNAUTHORIZED.value() || (e instanceof InsufficientScopeException)) {
            headers.set("WWW-Authenticate", String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getSummary()));
        }

        log.error(message, e);
        RestResult<Object> result = RestResult.failed(status, message);
        if (e instanceof UnsupportedGrantTypeException) {
            message = "不支持该认证类型";
        }
        if (e instanceof BadClientCredentialsException) {
            message = "客户端凭据错误(Bad client credentials)";
        } else if (e instanceof InvalidTokenException) {
            if (StringUtils.containsIgnoreCase(e.getMessage(), "Invalid refresh token (expired)")) {
                message = "刷新令牌已过期，请重新登录(Invalid refresh token (expired))";
            }
            if (StringUtils.containsIgnoreCase(e.getMessage(), "Token was not recognised")) {
                message = "令牌已失效，请重新登录(Token was not recognised)";
            }
            if (StringUtils.containsIgnoreCase(e.getMessage(), "Token has expired")) {
                message = "令牌已失效，请重新登录(Token has expired)";
            }
        } else if (e instanceof InvalidScopeException) {
            message = "不是有效的scope值";
        } else if (e instanceof InvalidGrantException) {
            if (StringUtils.containsIgnoreCase(e.getMessage(), "Invalid refresh token")) {
                message = "refresh token无效(Invalid refresh token)";
            } else if (StringUtils.containsIgnoreCase(e.getMessage(), "Invalid authorization code")) {
                message = "authorization code无效(Invalid authorization code)";
            } else if (StringUtils.containsIgnoreCase(e.getMessage(), "locked")) {
                message = "用户已被锁定，请联系管理员";
            } else {
                message = e.getMessage();
            }
        }
        if (HttpStatus.BAD_REQUEST.value() == result.getCode()) {
            result.setCode(HttpStatus.UNAUTHORIZED.value());
        }
        result.setMessage(message);
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
}
