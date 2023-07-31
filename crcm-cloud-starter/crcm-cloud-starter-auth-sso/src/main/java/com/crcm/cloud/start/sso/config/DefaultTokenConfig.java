package com.crcm.cloud.start.sso.config;

import com.crcm.cloud.start.sso.components.UserAuthenticationConverter;
import com.crcm.cloud.start.sso.config.properties.Oauth2Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName DefaultTokenConfig
 * @Description 默认token验证配置
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/8/16
 **/
@Slf4j
@RequiredArgsConstructor
@Configuration
public class DefaultTokenConfig {

    private final Oauth2Properties oauth2Properties;
    private final RestTemplate restTemplate;

    @Bean
    @Primary
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setClientId(oauth2Properties.getClientId());
        tokenServices.setClientSecret(oauth2Properties.getClientSecret());
        tokenServices.setCheckTokenEndpointUrl(oauth2Properties.getTokenInfoUri());
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(new UserAuthenticationConverter());
        tokenServices.setAccessTokenConverter(accessTokenConverter);
        tokenServices.setRestTemplate(restTemplate);
        log.debug("设置远程token验证 resourceServerTokenServices");
        return tokenServices;
    }
}
