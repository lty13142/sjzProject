package com.crcm.cloud.start.sso.config;

import com.crcm.cloud.start.sso.components.CustomAccessDeniedHandler;
import com.crcm.cloud.start.sso.components.CustomTokenExtractor;
import com.crcm.cloud.start.sso.components.OAuth2AuthenticationEntryPoint;
import com.crcm.cloud.start.sso.config.properties.Oauth2Properties;
import com.crcm.cloud.start.sso.config.properties.PermitAllUrlProperties;
import com.crcm.cloud.start.sso.handler.SSOLogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * @ClassName CUASResourceConfigurerAdapter
 * @Description cuas资源服务配置
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/7/27
 **/
@Slf4j
@Configuration
@EnableResourceServer
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({DefaultTokenConfig.class})
public class SsoResourceConfigurerAdapter extends ResourceServerConfigurerAdapter {

    private final Oauth2Properties oauth2Properties;
    private final RestTemplate restTemplate;
    private final PermitAllUrlProperties permitAllUrlProperties;
    private final ResourceServerTokenServices tokenServices;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                // 设置session在有需要的时候才生成
                .sessionManagement().sessionCreationPolicy((SessionCreationPolicy.IF_REQUIRED))
                //允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
                .and().headers().frameOptions().disable()
                .and().requestMatchers().anyRequest()          // 添加白名单去除请求头过滤器
                //.and().addFilterBefore(new PermitUrlsRemoveTokenFilter(permitAllUrlProperties), LogoutFilter.class)
                // 配置权限过滤
                .and()
                .authorizeRequests().antMatchers(permitAllUrlProperties.getAllIgnoredUrls()).permitAll()
                // 其它接口需要权限
                .anyRequest().authenticated()
                // 登出配置
                .and().logout().logoutSuccessHandler(new SSOLogoutSuccessHandler(oauth2Properties)).permitAll()
                // 其它接口需要权限
                //.and().authorizeRequests().anyRequest().authenticated()
                // 认证异常处理
                .and().exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .authenticationEntryPoint(new OAuth2AuthenticationEntryPoint(oauth2Properties,restTemplate))
        ;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(oauth2Properties.getResourceId())
                // 设置tokenStore
                .tokenServices(tokenServices)
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                // 设置请求token获取
                .tokenExtractor(new CustomTokenExtractor())
                .authenticationEntryPoint(new OAuth2AuthenticationEntryPoint(oauth2Properties, restTemplate));
    }


//    @Bean
//    @Primary
//    public AuthorizationCodeResourceDetails oauth2RemoteResource() {
//        return new AuthorizationCodeResourceDetails();
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许跨域
        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
        //允许方法
        corsConfiguration.setAllowedMethods(Arrays.asList("GET","PUT","POST","DELETE","PATCH","OPTIONS"));
        //允许携带凭证
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //对所有URL生效
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }
}
