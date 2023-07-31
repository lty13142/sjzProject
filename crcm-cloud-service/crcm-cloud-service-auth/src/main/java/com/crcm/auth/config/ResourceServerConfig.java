package com.crcm.auth.config;

import com.crcm.core.constant.AuthConstants;
import com.crcm.security.handle.AuthExceptionEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @ClassName ResourceServerConfig
 * @Description 资源服务器配置
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/8/13
 **/
@Configuration
@EnableResourceServer
@RequiredArgsConstructor
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    /**
     * 注入配置的tokenStore
     */
    private final TokenStore tokenStore;
    public final DefaultTokenServices tokenService;
    /**
     * 自定义权限不足处理器
     */
    private final AccessDeniedHandler customAccessDeniedHandler;
    /**
     * 自定义异常处理
     */
    private final AuthExceptionEntryPoint authExceptionEntryPoint;


    @Value("${security.oauth2.resource.id}")
    private String clientId;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 自定义权限不足处理器
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
        /* 开启授权认证 */
        http
                // http security 要拦截的url，这里这拦截 无需 http.csrf().disable();
                .requestMatchers()
                .antMatchers("/oauth/**", AuthConstants.LOGIN_PAGE, AuthConstants.LOGIN_PROCESS_URL)
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/oauth/client/**"
                        , AuthConstants.LOGIN_PAGE
                        , AuthConstants.LOGIN_PROCESS_URL
                        , AuthConstants.AUTHORIZE_CODE
                        ,"/open/**"
                        ,"/v2/api-docs"
                ).permitAll()
                .anyRequest().authenticated()
        ;
    }

    /**
     * 资源服务配置
     *
     * @param resources 资源服务
     * @author qipp
     * @date 2020/1/10 17:47
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        // 资源服务Id（简易配置文件形式管理）
        resources.resourceId(clientId)
                // 设置tokenStore
                .tokenStore(tokenStore)
                // 用来解决匿名用户访问无权限资源时的异常
                .authenticationEntryPoint(new AuthExceptionEntryPoint());
        //认证异常处理类
        resources.authenticationEntryPoint(authExceptionEntryPoint);
        //权限异常处理类
        resources.accessDeniedHandler(customAccessDeniedHandler);
    }
}
