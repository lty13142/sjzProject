package com.crcm.develop.core.conf.security.service;

import com.alibaba.fastjson.JSON;
import com.crcm.develop.common.exception.CustomException;
import com.crcm.develop.common.exception.user.UserPasswordNotMatchException;
import com.crcm.develop.core.conf.security.JwtProperties;
import com.crcm.develop.core.conf.security.domain.SecurityUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 登录校验方法
 *
 * @author zzyt
 */
@Component
public class SysLoginService {

    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProperties tokenProperties;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String uuid) {
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new UserPasswordNotMatchException();
            } else {
                throw new CustomException(e.getMessage());
            }
        }
        SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
        // 自定义参数
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(tokenProperties.getAuthorities(), JSON.toJSONString(userDetails.getAuthorities()));
        claims.put("username", userDetails.getUsername());
        claims.put("userId", userDetails.getId());
        //生成token
        String token = tokenProperties.getTokenHead() + tokenService.generateToken(username, claims, tokenProperties.getTokenExpireTime());
        // 生成token
        return token;
    }
}
