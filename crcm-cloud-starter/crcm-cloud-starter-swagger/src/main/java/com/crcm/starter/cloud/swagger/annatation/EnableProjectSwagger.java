package com.crcm.starter.cloud.swagger.annatation;

import com.crcm.starter.cloud.swagger.Knife4jConfigurationAdapter;
import com.crcm.starter.cloud.swagger.SwaggerBeanPostProcessor;
import com.crcm.starter.cloud.swagger.properties.ApiDocProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName EnableProjectSwagger
 * @Description 启用系统默认配置的swagger
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2023/1/31
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({Knife4jConfigurationAdapter.class, SwaggerBeanPostProcessor.class})
public @interface EnableProjectSwagger {
}
