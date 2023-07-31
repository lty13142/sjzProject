package com.crcm.admin.api.feign.factory;

import com.crcm.admin.api.feign.RemoteUserService;
import com.crcm.admin.api.feign.fallback.RemoteUserServiceFallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName RemoteUserServiceFallbackFactory
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/1
 **/
@Slf4j
@Component
public class RemoteUserServiceFallbackFactory implements FallbackFactory<RemoteUserService> {

    @Override
    public RemoteUserService create(Throwable cause) {
        RemoteUserServiceFallback remoteUserServiceFallback = new RemoteUserServiceFallback();
        remoteUserServiceFallback.setCause(cause);
        return remoteUserServiceFallback;
    }
}
