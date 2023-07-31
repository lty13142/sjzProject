package com.crcm.cloud.start.sso.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CookieUtil
 * @Description cookie工具
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/7/28
 **/
public class CookieUtil {


    /**
     * 设置一个cookie
     * @param response HttpServletResponse
     * @param name cookie的名称
     * @param value cookie的内容
     * @param maxAge cookie的持续时间
     */
    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 查找cookie
     * @param request HttpServletRequest
     * @param name 要查找的cookie的名称
     * @return
     */
    public static Cookie get(HttpServletRequest request,
                             String name) {
        //1.将cookies放到map中去
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        //2.查找是否存在cookie,是则返回查找到的cookie
        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        } else {
            return null;
        }
    }

    /**
     * 清空cookie
     * @param request
     * @param response
     */
    public static void clearCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }

    /**
     * 移除指定token
     * @param cookieName 移除的cookie名称
     * @param request
     * @param response
     */
    public static void removeCookie(String cookieName,HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equalsIgnoreCase(cookie.getName(),cookieName)) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }
}
