package com.crcm.cloud.start.resource.annatation;

import com.crcm.cloud.start.resource.config.ProjectResourceServerAutoConfiguration;
import com.crcm.cloud.start.resource.config.ProjectSecurityBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * @ClassName EnableProjectResourceServer
 * @Description 资源服务器启动配置
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ProjectResourceServerAutoConfiguration.class, ProjectSecurityBeanDefinitionRegistrar.class})
public @interface EnableProjectResourceServer {
    /**
     * false：上下文获取用户名
     * true： 上下文获取全部用户信息
     *
     * @return
     */
    boolean details() default true;
}
