package com.crcm.auth.config;

import com.crcm.auth.filter.CustomClientCredentialsTokenEndpointFilter;
import com.crcm.auth.service.CustomJdbcAuthorizationCodeService;
import com.crcm.auth.service.RedisAuthorizationCodeServices;
import com.crcm.auth.service.impl.CustomClientDetailsServiceImpl;
import com.crcm.auth.service.impl.UserLoginServiceImpl;
import com.crcm.core.constant.AuthConstants;
import com.crcm.security.components.CustomUserAuthenticationConverter;
import com.crcm.security.handle.AuthExceptionEntryPoint;
import com.crcm.security.handle.Oauth2WebResponseExceptionTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * @ClassName AuthServerConfig
 * @Description 统一认证服务器配置
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * 认证管理器
     */
    private final AuthenticationManager authenticationManager;

    /**
     * 客户端业务操作
     */
    private final CustomClientDetailsServiceImpl customClientDetailsServiceImpl;

    /**
     * 数据源
     */
    private final DataSource dataSource;

    /**
     * 查询认证用户信息
     */
    private final UserLoginServiceImpl userLoginServiceImpl;

    /**
     * tokenStore
     */
    private final TokenStore tokenStore;

    /**
     * 自定义token生成
     */
    @Autowired(required = false)
    @Qualifier(value = "accessTokenConverter")
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * 自定义token增强器
     */
    @Autowired(required = false)
    @Qualifier(value = "customTokenEnhancer")
    private TokenEnhancer tokenEnhancer;


    /**
     * 用来解决匿名用户访问无权限资源时的异常
     */
    private final AuthExceptionEntryPoint authExceptionEntryPoint;

    /**
     * redis
     */
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 配置客户端管理
     *
     * @param clients 客户端配置
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(customClientDetailsServiceImpl);
    }

    /**
     * 配置jdbc管理授权码
     *
     * @return org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new CustomJdbcAuthorizationCodeService(dataSource);
    }

    /**
     * 配置Redis管理授权码
     *
     * @return org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
     * @author qipp
     * @date 2020/1/17 16:38
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServicesRedis() {
        RedisAuthorizationCodeServices redisAuthorizationCodeServices = new RedisAuthorizationCodeServices(redisTemplate);
        return redisAuthorizationCodeServices;
    }


    /**
     * 配置token管理
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // redis方式
        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
        defaultAccessTokenConverter.setUserTokenConverter(new CustomUserAuthenticationConverter());
        endpoints.tokenStore(tokenStore).accessTokenConverter(defaultAccessTokenConverter);

        // 通过注入密码授权被打开AuthenticationManager
        endpoints.authenticationManager(authenticationManager)
                // 该字段设置设置refresh token是否重复使用,true:reuse;false:no reuse.
                .reuseRefreshTokens(false)
                // 刷新令牌授权将包含对用户详细信息的检查，以确保该帐户仍然活动,因此需要配置userDetailsService
                .userDetailsService(userLoginServiceImpl)
                // 配置管理授权码
                .authorizationCodeServices(authorizationCodeServicesRedis());
        // 最后一个参数为替换之后页面的url
        endpoints.pathMapping("/oauth/confirm_access", AuthConstants.CONFIRM_ACCESS);
        // 处理 ExceptionTranslationFilter 抛出的异常
        endpoints.exceptionTranslator(oauth2WebResponseExceptionTranslator());
    }

    /**
     * 对应于配置AuthorizationServer安全认证的相关信息，创建ClientCredentialsTokenEndpointFilter核心过滤器
     *
     * @param security 定义令牌端点上的安全约束。配置token获取合验证时的策略
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许所有人请求令牌
        security.tokenKeyAccess("permitAll()")
                // 已验证的客户端才能请求check_token端点
                .checkTokenAccess("permitAll()")
                // 允许表单登录
                .allowFormAuthenticationForClients();
        //配置ClientCredentialsTokenEndpointFilter自定义过滤器，并加入AuthenticationEntryPoint重写commence方法，自定义返回Oauth2异常信息。
        CustomClientCredentialsTokenEndpointFilter endpointFilter = new CustomClientCredentialsTokenEndpointFilter(security);
        endpointFilter.afterPropertiesSet();
        endpointFilter.setAuthenticationEntryPoint(authExceptionEntryPoint);
        //自定义异常过滤器和客户端端点过滤器
        security.addTokenEndpointAuthenticationFilter(endpointFilter);


    }


    /**
     * 配置默认token服务
     *
     * @return
     */
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);
        //token增强
        // redis方式
        DefaultAccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();
        tokenConverter.setUserTokenConverter(new CustomUserAuthenticationConverter());
        //令牌有效期12小时
        tokenServices.setAccessTokenValiditySeconds(43200);
        //刷新令牌有效期3天
        tokenServices.setRefreshTokenValiditySeconds(259200);
        return tokenServices;
    }

    @Autowired
    public Oauth2WebResponseExceptionTranslator oauth2WebResponseExceptionTranslator() {
        return new Oauth2WebResponseExceptionTranslator();
    }
}
