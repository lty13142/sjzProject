package com.crcm.security.components;

import com.crcm.security.utils.CustomTokenInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.ExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CustomRedisTokenStore
 * @Description redis集群存储token
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Slf4j
public class CustomRedisTokenStore implements TokenStore {
    private static final String ACCESS = "access:";
    private static final String AUTH_TO_ACCESS = "auth_to_access:";
    private static final String AUTH = "auth:";
    private static final String REFRESH_AUTH = "refresh_auth:";
    private static final String ACCESS_TO_REFRESH = "access_to_refresh:";
    private static final String REFRESH = "refresh:";
    private static final String REFRESH_TO_ACCESS = "refresh_to_access:";
    private static final String CLIENT_ID_TO_ACCESS = "client_id_to_access:";
    private static final String UNAME_TO_ACCESS = "uname_to_access:";
    private static final String TOKEN = "token:";
    private static final int TIPS_TIME = 600;

    private RedisTemplate<String, Object> redisTemplate;

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        log.info("setRedisTemplate --->  {} ", redisTemplate);
        this.redisTemplate = redisTemplate;
    }

    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    public void setAuthenticationKeyGenerator(AuthenticationKeyGenerator authenticationKeyGenerator) {
        this.authenticationKeyGenerator = authenticationKeyGenerator;
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        String key = authenticationKeyGenerator.extractKey(authentication);
        OAuth2AccessToken accessToken = (OAuth2AccessToken) redisTemplate.opsForValue().get(AUTH_TO_ACCESS + key);
        if (accessToken != null
                && !key.equals(authenticationKeyGenerator.extractKey(readAuthentication(accessToken.getValue())))) {
            // 保持存储一致（也许这个身份验证代表了同一个用户，但是细节已经改变了)
            storeAccessToken(accessToken, authentication);
        }
        return accessToken;
    }


    /**
     * 根据token读取认证信息
     *
     * @param oAuth2AccessToken
     * @return
     */
    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken oAuth2AccessToken) {
        return readAuthentication(oAuth2AccessToken.getValue());
    }

    /**
     * 根据token从redis中获取认证信息
     *
     * @param token
     * @return
     */
    @Override
    public OAuth2Authentication readAuthentication(String token) {
        return (OAuth2Authentication) this.redisTemplate.opsForValue().get(AUTH + token);
    }


    /**
     * 重写根据refresh_token从redis中获取认证信息
     *
     * @param token
     * @return
     */
    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        return readAuthenticationForRefreshToken(token.getValue());
    }


    /**
     * 根据refresh_token从redis中获取认证信息
     *
     * @param token
     * @return
     */
    private OAuth2Authentication readAuthenticationForRefreshToken(String token) {
        return (OAuth2Authentication) this.redisTemplate.opsForValue().get(REFRESH_AUTH + token);
    }

    /**
     * 存储toekn 以及认证信息
     *
     * @param token
     * @param authentication
     */
    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        log.info("storeAccessToken ---> {}", token);
        OAuth2AccessToken existingAccessToken = this.getAccessToken(authentication);
        this.redisTemplate.opsForValue().set(ACCESS + token.getValue(), token);
        this.redisTemplate.opsForValue().set(AUTH + token.getValue(), authentication);
        this.redisTemplate.opsForValue().set(AUTH_TO_ACCESS + authenticationKeyGenerator.extractKey(authentication), token);
        Map<String, Object> params = new HashMap<>(16);
        // 客户端模式不自定义token中用户信息
        if (!"client_credentials".equals(authentication.getOAuth2Request().getRequestParameters().get("grant_type"))) {
            // 自定义token中用户信息
            params = CustomTokenInfoUtil.getAuthenticationUserInfo(authentication);
        }

        params.put("clientId", authentication.getOAuth2Request().getClientId());

        if (!params.isEmpty()) {
            this.redisTemplate.opsForValue().set(TOKEN + token.getValue(), params);
        }
        if (!authentication.isClientOnly()) {
            if (existingAccessToken != null) {
                if (!existingAccessToken.isExpired()) {
                    int seconds = token.getExpiresIn();
                    redisTemplate.expire(UNAME_TO_ACCESS + authentication.getOAuth2Request().getClientId(), seconds, TimeUnit.SECONDS);
                } else {
                    redisTemplate.opsForList().rightPush(UNAME_TO_ACCESS + getApprovalKey(authentication), token);
                }
            } else {
                redisTemplate.opsForList().rightPush(UNAME_TO_ACCESS + getApprovalKey(authentication), token);
            }
        }
        if (existingAccessToken != null) {
            if (!existingAccessToken.isExpired()) {
                int seconds = token.getExpiresIn();
                redisTemplate.expire(CLIENT_ID_TO_ACCESS + authentication.getOAuth2Request().getClientId(), seconds, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForList().rightPush(CLIENT_ID_TO_ACCESS + authentication.getOAuth2Request().getClientId(), token);
            }
        } else {
            redisTemplate.opsForList().rightPush(CLIENT_ID_TO_ACCESS + authentication.getOAuth2Request().getClientId(), token);
        }

        if (token.getExpiration() != null) {
            int seconds = token.getExpiresIn();
            redisTemplate.expire(ACCESS + token.getValue(), seconds, TimeUnit.SECONDS);
            redisTemplate.expire(AUTH + token.getValue(), seconds, TimeUnit.SECONDS);
            redisTemplate.expire(TOKEN + token.getValue(), seconds, TimeUnit.SECONDS);
            redisTemplate.expire(AUTH_TO_ACCESS + authenticationKeyGenerator.extractKey(authentication), seconds, TimeUnit.SECONDS);
            redisTemplate.expire(CLIENT_ID_TO_ACCESS + authentication.getOAuth2Request().getClientId(), seconds, TimeUnit.SECONDS);
            redisTemplate.expire(UNAME_TO_ACCESS + getApprovalKey(authentication), seconds, TimeUnit.SECONDS);
        }
        OAuth2RefreshToken refreshToken = token.getRefreshToken();
        if (token.getRefreshToken() != null && token.getRefreshToken().getValue() != null) {
            this.redisTemplate.opsForValue().set(REFRESH_TO_ACCESS + token.getRefreshToken().getValue(), token.getValue());
            this.redisTemplate.opsForValue().set(ACCESS_TO_REFRESH + token.getValue(), token.getRefreshToken().getValue());
            if (refreshToken instanceof ExpiringOAuth2RefreshToken) {
                ExpiringOAuth2RefreshToken expiringRefreshToken = (ExpiringOAuth2RefreshToken) refreshToken;
                Date expiration = expiringRefreshToken.getExpiration();
                if (expiration != null) {
                    int seconds = Long.valueOf((expiration.getTime() - System.currentTimeMillis()) / 1000L)
                            .intValue();
                    redisTemplate.expire(REFRESH_TO_ACCESS + token.getRefreshToken().getValue(), seconds, TimeUnit.SECONDS);
                    redisTemplate.expire(ACCESS_TO_REFRESH + token.getValue(), seconds, TimeUnit.SECONDS);
                }
            }
        }
    }

    /**
     * 根据用户名存token
     *
     * @param authentication 认证信息
     * @return java.lang.String
     * @author qipp
     * @date 2019/6/27 10:08
     */
    private String getApprovalKey(OAuth2Authentication authentication) {
        String userName = authentication.getUserAuthentication() == null ? "" : authentication.getUserAuthentication()
                .getName();
        return getApprovalKey(authentication.getOAuth2Request().getClientId(), userName);
    }

    /**
     * 根据用户名存token
     *
     * @param clientId 客户端ID
     * @param userName 用户名
     * @return java.lang.String
     * @author qipp
     * @date 2019/6/27 10:17
     */
    private String getApprovalKey(String clientId, String userName) {
        return clientId + (userName == null ? "" : ":" + userName);
    }


    /**
     * 读取token信息
     * 每次访问接口都会走此方法，根据token获取认证信息、令牌信息！如果即将过期给予刷新
     * @param tokenValue
     * @return
     */
    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        OAuth2Authentication oauth2Authentication = (OAuth2Authentication) this.redisTemplate.opsForValue()
                .get(AUTH + tokenValue);
        OAuth2AccessToken oauth2AccessToken = (OAuth2AccessToken) this.redisTemplate.opsForValue()
                .get(ACCESS + tokenValue);
        if (oauth2Authentication != null) {
            String authToAccess = authenticationKeyGenerator.extractKey(oauth2Authentication);
            if (oauth2AccessToken != null) {
                if (oauth2AccessToken.getExpiresIn() < TIPS_TIME) {
                    if (oauth2AccessToken instanceof DefaultOAuth2AccessToken) {
                        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) oauth2AccessToken;
                        // 自动续费 30分钟
                        LocalDateTime t1 = LocalDateTime.now().plusMinutes(30);
                        ZoneId zone = ZoneId.systemDefault();
                        Instant instant = t1.atZone(zone).toInstant();
                        Date date = Date.from(instant);
                        token.setExpiration(date);
                        int seconds = token.getExpiresIn();
                        redisTemplate.opsForValue().set(AUTH_TO_ACCESS + authToAccess, token, seconds, TimeUnit.SECONDS);
                        redisTemplate.opsForValue().set(ACCESS + token.getValue(), token, seconds, TimeUnit.SECONDS);
                        redisTemplate.expire(AUTH + token.getValue(), seconds, TimeUnit.SECONDS);
                        redisTemplate.expire(TOKEN + token.getValue(), seconds, TimeUnit.SECONDS);
                        redisTemplate.expire(
                                CLIENT_ID_TO_ACCESS + oauth2Authentication.getOAuth2Request().getClientId(), seconds,
                                TimeUnit.SECONDS);
                        redisTemplate.expire(UNAME_TO_ACCESS + getApprovalKey(oauth2Authentication), seconds,
                                TimeUnit.SECONDS);
                    }

                }
            }
        }
        return oauth2AccessToken;
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken accessToken) {
        removeAccessToken(accessToken.getValue());
    }

    /**
     * 删除整套token除refresh token
     *
     * @param tokenValue 令牌
     * @author qipp
     * @date 2019/6/26 18:40
     */
    private void removeAccessToken(String tokenValue) {
        // Don't remove the refresh token - it's up to the caller to do that
        OAuth2Authentication authentication = (OAuth2Authentication) this.redisTemplate.opsForValue().get(AUTH + tokenValue);
        this.redisTemplate.delete(AUTH + tokenValue);
        redisTemplate.delete(ACCESS + tokenValue);
        redisTemplate.delete(TOKEN + tokenValue);
        this.redisTemplate.delete(ACCESS_TO_REFRESH + tokenValue);
        if (authentication != null) {
            this.redisTemplate.delete(AUTH_TO_ACCESS + authenticationKeyGenerator.extractKey(authentication));
            String clientId = authentication.getOAuth2Request().getClientId();
//			redisTemplate.opsForList().rightPush("UNAME_TO_ACCESS:"+getApprovalKey(authentication), token) ;
            redisTemplate.opsForList().leftPop(UNAME_TO_ACCESS + getApprovalKey(clientId, authentication.getName()));
            redisTemplate.opsForList().leftPop(CLIENT_ID_TO_ACCESS + clientId);
        }
    }

    /**
     * 存储refresh_token
     *
     * @param refreshToken
     * @param authentication
     */
    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        this.redisTemplate.opsForValue().set(REFRESH + refreshToken.getValue(), refreshToken);
        this.redisTemplate.opsForValue().set(REFRESH_AUTH + refreshToken.getValue(), authentication);
    }

    /**
     * 读取refresh_token
     *
     * @param tokenValue
     * @return
     */
    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        return (OAuth2RefreshToken) this.redisTemplate.opsForValue().get(REFRESH + tokenValue);

    }


    /**
     * 重写移除refresh_token接口
     *
     * @param refreshToken
     */
    @Override
    public void removeRefreshToken(OAuth2RefreshToken refreshToken) {
        removeRefreshToken(refreshToken.getValue());
    }

    /**
     * 移除redis中的refresh_token相关
     *
     * @param tokenValue 令牌
     */
    private void removeRefreshToken(String tokenValue) {
        this.redisTemplate.delete(REFRESH + tokenValue);
        this.redisTemplate.delete(REFRESH_AUTH + tokenValue);
        this.redisTemplate.delete(REFRESH_TO_ACCESS + tokenValue);
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        removeAccessTokenUsingRefreshToken(refreshToken.getValue());
    }

    /**
     * 刷新token时删除AccessToken
     *
     * @param refreshToken 刷新token
     * @return void
     */
    private void removeAccessTokenUsingRefreshToken(String refreshToken) {
        String token = (String) this.redisTemplate.opsForValue().get(REFRESH_TO_ACCESS + refreshToken);
        // 删除映射关系refresh_to_access
        this.redisTemplate.delete(REFRESH_TO_ACCESS + refreshToken);
        if (token != null) {
            // 删除access_token
            removeAccessToken(token);
        }
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        List<Object> result = redisTemplate.opsForList().range(UNAME_TO_ACCESS + getApprovalKey(clientId, userName), 0, -1);
        return getoAuth2AccessTokens(result);
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        List<Object> result = redisTemplate.opsForList().range((CLIENT_ID_TO_ACCESS + clientId), 0, -1);
        return getoAuth2AccessTokens(result);
    }

    private Collection<OAuth2AccessToken> getoAuth2AccessTokens(List<Object> result) {
        if (CollectionUtils.isEmpty(result)) {
            return Collections.emptySet();
        }
        List<OAuth2AccessToken> accessTokens = new ArrayList<>(result.size());

        for (Object o : result) {
            OAuth2AccessToken accessToken = (OAuth2AccessToken) o;
            accessTokens.add(accessToken);
        }

        return Collections.unmodifiableCollection(accessTokens);
    }
}
