package com.crcm.cloud.start.sso.components;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crcm.cloud.start.sso.config.properties.Oauth2Properties;
import com.crcm.cloud.start.sso.constants.Oauth2ExceptionEnum;
import com.crcm.cloud.start.sso.domain.AuthResult;
import com.crcm.cloud.start.sso.utils.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.oauth2.provider.error.AbstractOAuth2SecurityExceptionHandler;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName CustomOAuth2AuthenticationEntryPoint
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/7/27
 **/
@Slf4j
@RequiredArgsConstructor
public class OAuth2AuthenticationEntryPoint extends AbstractOAuth2SecurityExceptionHandler implements AuthenticationEntryPoint {

    private final Oauth2Properties oauth2Properties;
    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();
    private final RestTemplate restTemplate;

    @SneakyThrows
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        Throwable cause = authException.getCause();
        //解析异常
        AuthResult result = translate(authException);
        // 如果是token过期异常，则进行续期处理
        if (cause instanceof InvalidTokenException) {
            // 当存在刷新token时刷新令牌
            if (StringUtils.isNotBlank(getRefreshToken(request))) {
                try {
                    refreshToken(request, response);
                } catch (Exception e) {
                    int code = Oauth2ExceptionEnum.INVALID_AUTHENTICATION.getCode();
                    String msg = e.getMessage();
                    if (e instanceof OAuth2Exception) {
                        OAuth2Exception ex = (OAuth2Exception) e;
                        msg = ex.getMessage();
                    }
                    writeResponse(response, code, msg);
                    return;
                }
            }
        }
        // 输出自己定义的返回类型
        writeResponse(response, result.getCode(), result.getMessage());
    }

    private void writeResponse(HttpServletResponse response,
                               Integer code, String message) throws IOException {
        // 返回指定格式的错误信息
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=utf-8");
//        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().print(JSON.toJSONString(AuthResult.failed(code, message)));
        response.getWriter().flush();
    }

    private String getRefreshToken(HttpServletRequest request) {
        Cookie[] cookie = request.getCookies();
        if (Objects.isNull(cookie)) {
            return null;
        }
        for (Cookie coo : cookie) {
            if (coo.getName().equals("refresh_token")) {
                return coo.getValue();
            }
        }
        return null;
    }

    /**
     * 自动刷新令牌
     * //TODO token自动刷新逻辑得改一下
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", oauth2Properties.getClientId());
        formData.add("client_secret", oauth2Properties.getClientSecret());
        formData.add("grant_type", "refresh_token");
        formData.add("refresh_token", getRefreshToken(request));
        // 没有refresh_token直接返回错误信息
        if (StringUtils.isBlank(formData.getFirst("refresh_token"))) {
            throw new OAuth2Exception(Oauth2ExceptionEnum.INVALID_AUTHENTICATION.getMsg());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        JSONObject result = restTemplate.exchange(oauth2Properties.getAccessTokenUri(), HttpMethod.POST,
                new HttpEntity<MultiValueMap<String, String>>(formData, headers), JSONObject.class).getBody();
        //如果刷新异常,则坐进一步处理
        if (result.get("success") == null || !Boolean.parseBoolean("success")) {
            CookieUtil.removeCookie("access_token", request, response);
            CookieUtil.removeCookie("refresh_token", request, response);
            // 输出自己定义的返回类型
            writeResponse(response, result.getInteger("code"), result.getString("message"));
        } else {
            JSONObject data = JSON.parseObject(JSON.toJSONString(result.get("data")), JSONObject.class);
            //如果刷新成功则存储cookie并且跳转到原来需要访问的页面
            String accessToken = data.getString("access_token");
            response.addCookie(new Cookie("access_token", accessToken));
            String refreshToken = data.getString("refresh_token");
            response.addCookie(new Cookie("refresh_token", refreshToken));
            request.getRequestDispatcher(request.getRequestURI()).forward(request, response);
        }
    }

    public AuthResult translate(Exception e) throws Exception {
        // Try to extract a SpringSecurityException from the stacktrace
        Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);

        // 异常栈获取 OAuth2Exception 异常
        OAuth2Exception ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(
                OAuth2Exception.class, causeChain);
        if (ase != null) {
            return handleOauth2Exception(ase);
        }
        return AuthResult.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }


    private AuthResult<?> handleOauth2Exception(OAuth2Exception e) {
        String message = e.getMessage();
        int status = e.getHttpErrorCode();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        if (status == HttpStatus.UNAUTHORIZED.value() || (e instanceof InsufficientScopeException)) {
            headers.set("WWW-Authenticate", String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getSummary()));
        }

        log.error(message, e);
        AuthResult<Object> result = AuthResult.failed(status, message);
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
            if (StringUtils.containsIgnoreCase(e.getOAuth2ErrorCode(), "invalid_token")) {
                message = "令牌已失效，请重新登录(invalid_token)";
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
        return result;
    }

}
