package com.crcm.file.api.feign.fallback;

import com.crcm.file.api.feign.RemoteFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName RemoteFileServiceFallbackFactory
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/7/1
 **/
@Slf4j
@Component
public class RemoteFileServiceFallbackFactory implements FallbackFactory<RemoteFileService> {
    @Override
    public RemoteFileService create(Throwable cause) {
        RemoteFileServiceFallback remoteUserServiceFallback = new RemoteFileServiceFallback();
        remoteUserServiceFallback.setCause(cause);
        return remoteUserServiceFallback;
    }
}