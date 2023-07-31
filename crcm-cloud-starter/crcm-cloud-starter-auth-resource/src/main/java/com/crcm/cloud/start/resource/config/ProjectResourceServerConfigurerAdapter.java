package com.crcm.cloud.start.resource.config;

import com.crcm.cloud.start.resource.properties.Oauth2Properties;
import com.crcm.cloud.start.resource.properties.PermitAllUrlProperties;
import com.crcm.security.handle.AuthExceptionEntryPoint;
import com.crcm.security.handle.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @ClassName ResourceServerConfig
 * @Description 资源服务适配器
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/19
 **/
@Slf4j
@RequiredArgsConstructor
public class ProjectResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

    /**
     * 权限不足处理器
     */
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    /**
     * 注入配置的tokenStore
     */
    private final TokenStore tokenStore;

    @Setter
    private boolean details;

    /**
     * 用来解决匿名用户访问无权限资源时的异常
     */
    private final AuthExceptionEntryPoint authExceptionEntryPoint;
    /**
     * 认证配置
     */
    private final Oauth2Properties oauth2Properties;
    /**
     * 资源服务器对外直接暴露URL
     */
    private final PermitAllUrlProperties permitAllUrlProperties;



    /**
     * 资源服务配置
     *
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // 资源服务Id（简易配置文件形式管理）
        resources.resourceId(oauth2Properties.getResourceId())
                // 设置tokenStore
                .tokenStore(tokenStore).stateless(true)
                // 用来解决匿名用户访问无权限资源时的异常
                .authenticationEntryPoint(authExceptionEntryPoint);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        // 允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        http.headers().frameOptions().disable().and().cors();
        http.authorizeRequests()
                .antMatchers(permitAllUrlProperties.getUrls().toArray(new String[permitAllUrlProperties.getUrls().size()]))
                .permitAll()
                .anyRequest().authenticated()
                ;
        // 配置暴露不需要鉴权的URL
        http.csrf().disable();
        // 权限不足处理器
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
    }
}
