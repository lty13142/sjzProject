package com.crcm.develop.core.conf.security.filter;

import com.crcm.develop.core.conf.security.service.TokenService;
import com.crcm.develop.core.domain.UserContext;
import com.crcm.develop.core.domain.UserInfo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * token过滤器 验证token有效性
 *
 * @author zzyt
 */
@Component
@AllArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/login")) {
            chain.doFilter(request, response);
            return;
        }
        String userName = tokenService.getUserName(request);
        if (StringUtils.isNotBlank(userName)) {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(userName, response);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(userName);
            UserContext.setUserInfo(userInfo);
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String userName,  HttpServletResponse response) {
        // 权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_COMMON"));
        User principal = new User(userName, "", authorities);
        return new UsernamePasswordAuthenticationToken(principal,null,authorities);
    }
}
