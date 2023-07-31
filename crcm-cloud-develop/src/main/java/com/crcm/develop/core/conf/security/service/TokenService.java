package com.crcm.develop.core.conf.security.service;

import com.crcm.develop.core.conf.security.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * token验证处理
 *
 * @author zzyt
 */
@Component
public class TokenService {

    @Autowired
    private JwtProperties tokenProperties;

    //签名方式
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;


    /**
     * 通过token获取用户名
     *
     * @param token
     * @return
     */
    public String getUserName(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public String getUserName(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            Claims claims = parseToken(token);
            return claims.getSubject();
        }
        return null;
    }


    /***
     * 解析token 信息
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token) throws JwtException {
        Claims claims;
        claims = Jwts.parser()
                .setSigningKey(tokenProperties.getSecret())   //签名的key
                .parseClaimsJws(token.replace(tokenProperties.getTokenHead(), ""))   // 签名token
                .getBody();
        return claims;
    }

    /**
     * 生成失效时间
     *
     * @param expiration
     * @return
     */
    private Date generateExpirationDate(long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 60 * 1000);
    }

    /**
     * token 是否过期
     *
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 根据token 获取过期时间
     *
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * 重置(更新)token 过期时间
     *
     * @param token
     * @param expiration
     */
    public String restTokenExpired(String token, long expiration) throws JwtException {

        final Claims claims = getClaimsFromToken(token);
        Jwts.builder()
                .setClaims(claims)   //一个map 可以资源存放东西进去
                .setSubject(claims.getSubject()) //  用户名写入标题
                .setExpiration(new Date(expiration));
        //claims.setExpiration(new Date(expiration));
        // String refreshedToken = generateAccessToken(claims.getSubject(), claims,expiration);
        return "";
    }

    /**
     * 用户所拥有的资源权限
     *
     * @param authorities
     * @return
     */
    private List<?> authoritiesToArray(Collection<? extends GrantedAuthority> authorities) {
        List<String> list = new ArrayList<>();
        for (GrantedAuthority ga : authorities) {
            list.add(ga.getAuthority());
        }
        return list;
    }

    private Collection<? extends GrantedAuthority> parseArrayToAuthorities(List<?> roles) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority;
        for (Object role : roles) {
            authority = new SimpleGrantedAuthority(role.toString());
            authorities.add(authority);
        }
        return authorities;
    }

    /**
     * 生成token
     *
     * @param subject
     * @param claims
     * @param expiration
     * @return
     */
    public String generateToken(String subject, Map<String, Object> claims, long expiration) {
        return Jwts.builder()
                .setClaims(claims)   //一个map 可以资源存放东西进去
                .setSubject(subject) //  用户名写入标题
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate(expiration))  //过期时间,单位为分钟
                //.setNotBefore(now)              //系统时间之前的token都是不可以被承认的
                .signWith(SIGNATURE_ALGORITHM, tokenProperties.getSecret()) //数字签名
                .compact();
    }

    /**
     * token过期，设置token过期时间为现在
     *
     * @param token
     */
    public void discardToken(String token) throws JwtException {
        final Claims claims = getClaimsFromToken(token);
        Jwts.builder()
                .setClaims(claims)   //一个map 可以资源存放东西进去
                .setSubject(claims.getSubject()) //  用户名写入标题
                .setExpiration(new Date());
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(tokenProperties.getHeader());
        if (StringUtils.isNotEmpty(token) && token.startsWith(tokenProperties.getTokenHead())) {
            token = token.replace(tokenProperties.getTokenHead(), "");
        }
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(tokenProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

}
