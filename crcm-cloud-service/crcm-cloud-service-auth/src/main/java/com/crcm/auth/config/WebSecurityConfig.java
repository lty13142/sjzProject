package com.crcm.auth.config;

import com.crcm.auth.service.impl.UserLoginServiceImpl;
import com.crcm.core.constant.AuthConstants;
import com.crcm.security.components.CustomPasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @ClassName WebSecurityConfig
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 获取用户认证信息Service
     */
    private final UserLoginServiceImpl userLoginServiceImpl;
    /**
     * 认证成功处理器
     */
    private final AuthenticationSuccessHandler loginSuccessHandler;
    /**
     * 认证失败处理器
     */
    private final AuthenticationFailureHandler loginFailureHandler;
    /**
     * 自定义权限不足处理器
     */
    private final AccessDeniedHandler accessDeniedHandler;

    /**
     * Spring Security中HttpSecurity配置程序允许的受保护资源的其他定制
     * <p>注意:如果您的授权服务器也是一个资源服务器，那么还有另一个安全过滤器链，
     * 它的优先级较低，控制了API资源。 对于那些需要通过访问令牌来保护的请求，您需要它们的路径不能与主用户所面对的过滤器链中的那些相匹配，
     * 所以一定要包含一个请求matcher，它只挑选出下面的WebSecurityConfigurer中的非Api资源。
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 自定义权限不足处理器
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        /* 开启授权认证 */
//        http
//                // http security 要拦截的url，这里这拦截 无需 http.csrf().disable();
//                .requestMatchers()
//                .antMatchers("/oauth/**", AuthConstants.LOGIN_PAGE, AuthConstants.LOGIN_PROCESS_URL)
//                .and()
//                .authorizeRequests()
//                .antMatchers(
//                        "/oauth/client/**"
//                        , AuthConstants.LOGIN_PAGE
//                        , AuthConstants.LOGIN_PROCESS_URL
//                        , AuthConstants.AUTHORIZE_CODE).permitAll()
//                .anyRequest().authenticated()
//        ;
        /* 登录配置 */
        http.formLogin()
                // （授权码、sso）网页认证通过处理
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                // （授权码、sso）网页认证错误处理（json 交互时开启）
                //.failureHandler(loginFailureHandler)
                // 请求 {用户名} 参数名称
                .usernameParameter(AuthConstants.USERNAME_PARAMETER_NAME)
                // 请求 {密码} 参数名
                .passwordParameter(AuthConstants.PASSWORD_PARAMETER_NAME)
                // 登录页面
                .loginPage(AuthConstants.LOGIN_PAGE)
                // 登录处理url
                .loginProcessingUrl(AuthConstants.LOGIN_PROCESS_URL);
        // 暂时不使用用Basic
        http.httpBasic().disable();
        /* session 设置为 IF_REQUIRED 有需要才生成 */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }

    /**
     * 全局用户信息
     *
     * @param auth 认证管理
     * @throws Exception 用户认证异常信息
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userLoginServiceImpl).passwordEncoder(passwordEncoder());
    }

    /**
     * 静态资源开放
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/fonts/**", "/css/**", "/js/**", "/img/**", "/favicon.ico");
    }

    /**
     * 认证管理(授权服务配置需要用到这个)
     *
     * @return 认证管理对象
     * @throws Exception 认证异常信息
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置密码加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }


}
