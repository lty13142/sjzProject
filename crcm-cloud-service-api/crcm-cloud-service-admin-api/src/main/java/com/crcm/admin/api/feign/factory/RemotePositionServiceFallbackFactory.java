package com.crcm.admin.api.feign.factory;

import com.crcm.admin.api.feign.RemotePositionService;
import com.crcm.admin.api.feign.fallback.RemotePositionServiceFallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author yzw
 * @data 2023/4/11
 * @apiNote
 */
@Slf4j
@Component
public class RemotePositionServiceFallbackFactory implements FallbackFactory<RemotePositionService> {
    @Override
    public RemotePositionService create(Throwable cause) {
        RemotePositionServiceFallback remotePositionServiceFallback=new RemotePositionServiceFallback();
        remotePositionServiceFallback.setCause(cause);
        return remotePositionServiceFallback;
    }
}
