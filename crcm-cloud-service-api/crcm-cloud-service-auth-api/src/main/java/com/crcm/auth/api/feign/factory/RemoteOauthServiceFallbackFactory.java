package com.crcm.auth.api.feign.factory;

import com.crcm.auth.api.feign.RemoteOauthService;
import com.crcm.auth.api.feign.fallback.RemoteOauthServiceFallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName RemoteOauthServiceFallbackFactory
 * @Description 远程授权服务工厂
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Slf4j
@Component
public class RemoteOauthServiceFallbackFactory implements FallbackFactory<RemoteOauthService> {
    @Override
    public RemoteOauthService create(Throwable cause) {
        RemoteOauthServiceFallback remoteUserServiceFallback = new RemoteOauthServiceFallback();
        remoteUserServiceFallback.setCause(cause);
        return remoteUserServiceFallback;
    }
}
