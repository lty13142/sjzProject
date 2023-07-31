package com.crcm.cloud.start.sso.filter;

import com.crcm.cloud.start.sso.config.properties.PermitAllUrlProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName PermitUrlsRemoveTokenFilter
 * @Description 白名单路径移除TOKEN请求头
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/8/2
 **/
@RequiredArgsConstructor
public class PermitUrlsRemoveTokenFilter extends OncePerRequestFilter {

    private final PermitAllUrlProperties permitAllUrlProperties;
    private static final String TOKEN_HEADER_NAME = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        //白名单路径移除TOKEN请求头
        Set<String> ignoreUrls = permitAllUrlProperties.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri)) {
                chain.doFilter(filterRequest(request), response);
                return;
            }
        }
        chain.doFilter(request, response);
    }


    private HttpServletRequest filterRequest(HttpServletRequest request) {
        return new HttpServletRequestWrapper(request) {
            private Set<String> headerNameSet;

            @Override
            public Enumeration<String> getHeaderNames() {
                if (headerNameSet == null) {
                    // first time this method is called, cache the wrapped request's header names:
                    headerNameSet = new HashSet<>();
                    Enumeration<String> wrappedHeaderNames = super.getHeaderNames();
                    while (wrappedHeaderNames.hasMoreElements()) {
                        String headerName = wrappedHeaderNames.nextElement();
                        if (!"Authorization".equalsIgnoreCase(headerName)) {
                            headerNameSet.add(headerName);
                        }
                    }
                }
                return Collections.enumeration(headerNameSet);
            }

            @Override
            public Enumeration<String> getHeaders(String name) {
                if (TOKEN_HEADER_NAME.equalsIgnoreCase(name)) {
                    return Collections.<String>emptyEnumeration();
                }
                return super.getHeaders(name);
            }

            @Override
            public String getHeader(String name) {
                if (TOKEN_HEADER_NAME.equalsIgnoreCase(name)) {
                    return null;
                }
                return super.getHeader(name);
            }
        };
    }
}
