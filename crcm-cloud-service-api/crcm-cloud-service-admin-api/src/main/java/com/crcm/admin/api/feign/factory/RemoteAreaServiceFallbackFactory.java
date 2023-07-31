package com.crcm.admin.api.feign.factory;

import com.crcm.admin.api.feign.RemoteAreaService;
import com.crcm.admin.api.feign.fallback.RemoteAreaServiceFallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RemoteAreaServiceFallbackFactory implements FallbackFactory<RemoteAreaService> {

    @Override
    public RemoteAreaService create(Throwable cause) {
        RemoteAreaServiceFallback remoteAreaServiceFallback = new RemoteAreaServiceFallback();
        remoteAreaServiceFallback.setCause(cause);
        return remoteAreaServiceFallback;
    }
}
