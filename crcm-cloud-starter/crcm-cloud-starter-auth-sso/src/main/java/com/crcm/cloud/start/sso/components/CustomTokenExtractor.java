package com.crcm.cloud.start.sso.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @ClassName CustomTokenExtractor
 * @Description 自定义token异常解析器
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/7/26
 **/
@Slf4j
public class CustomTokenExtractor implements TokenExtractor {


    @Override
    public Authentication extract(HttpServletRequest request) {
        String tokenValue = this.extractToken(request);
        if (tokenValue != null) {
            return new PreAuthenticatedAuthenticationToken(tokenValue, "");
        } else {
            return null;
        }
    }

    public String extractToken(HttpServletRequest request) {
        String token = this.extractHeaderToken(request);
        if (token == null) {
            log.debug("Token not found in headers. Trying request parameters.");
            token = request.getParameter("access_token");
            if (token == null) {
                log.debug("Token not found in request parameters.  Trying request cookies.");

                token = extractCookieToken(request);
                if (token == null) {
                    log.debug("Token not found in cookies.  Not an OAuth2 request.");
                } else {
                    request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, "Bearer");
                }
            }
        }
        return token;
    }

    private String extractHeaderToken(HttpServletRequest request) {
        Enumeration headers = request.getHeaders("Authorization");

        String value;
        do {
            if (!headers.hasMoreElements()) {
                return null;
            }

            value = (String) headers.nextElement();
        } while (!value.toLowerCase().startsWith("Bearer".toLowerCase()));

        String authHeaderValue = value.substring("Bearer".length()).trim();
        request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, value.substring(0, "Bearer".length()).trim());
        int commaIndex = authHeaderValue.indexOf(44);
        if (commaIndex > 0) {
            authHeaderValue = authHeaderValue.substring(0, commaIndex);
        }
        if (authHeaderValue.equals("") || authHeaderValue.equals("undefined")) {
            return null;
        }
        return authHeaderValue;
    }

    private String extractCookieToken(HttpServletRequest request) {

        String cookieToken = null;
        //根据请求数据，找到cookie数组
        Cookie[] cookies = request.getCookies();

        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (null != cookie.getName() && "access_token".equalsIgnoreCase(cookie.getName().trim())) {
                    cookieToken = cookie.getValue().trim();
                    break;
                }
            }
        }
        return cookieToken;
    }
}