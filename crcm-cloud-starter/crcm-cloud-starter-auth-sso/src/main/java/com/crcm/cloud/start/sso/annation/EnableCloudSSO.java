package com.crcm.cloud.start.sso.annation;

import com.crcm.cloud.start.sso.config.DefaultTokenConfig;
import com.crcm.cloud.start.sso.config.SsoResourceConfigurerAdapter;
import com.crcm.cloud.start.sso.config.properties.Oauth2Properties;
import com.crcm.cloud.start.sso.config.properties.PermitAllUrlProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName EnableCloudSSO
 * @Description 启用单点登陆
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/24
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SsoResourceConfigurerAdapter.class, Oauth2Properties.class, PermitAllUrlProperties.class, DefaultTokenConfig.class})
public @interface EnableCloudSSO {
}
