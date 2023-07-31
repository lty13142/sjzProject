package com.crcm.admin.api.feign.factory;

import com.crcm.admin.api.feign.RemoteOrgService;
import com.crcm.admin.api.feign.fallback.RemoteOrgServiceFallback;
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
public class RemoteOrgServiceFallbackFactory implements FallbackFactory<RemoteOrgService> {

    @Override
    public RemoteOrgService create(Throwable cause){
        RemoteOrgServiceFallback remoteOrgServiceFallback=new RemoteOrgServiceFallback();
        remoteOrgServiceFallback.setCause(cause);
        return remoteOrgServiceFallback;
    }

}
