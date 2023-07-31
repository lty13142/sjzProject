package com.crcm.admin.api.feign.factory;

import com.crcm.admin.api.feign.RemoteLogService;
import com.crcm.admin.api.feign.fallback.RemoteLogServiceFallback;
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
public class RemoteLogServiceFallbackFactory implements FallbackFactory<RemoteLogService> {

    @Override
    public RemoteLogService create(Throwable cause) {
        RemoteLogServiceFallback remoteUserServiceFallback = new RemoteLogServiceFallback();
        remoteUserServiceFallback.setCause(cause);
        return remoteUserServiceFallback;
    }
}
