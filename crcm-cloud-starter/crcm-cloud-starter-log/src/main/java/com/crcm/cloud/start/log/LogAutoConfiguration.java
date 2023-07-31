package com.crcm.cloud.start.log;

import com.crcm.admin.api.feign.RemoteLogService;
import com.crcm.cloud.start.log.aspect.SysLogAspect;
import com.crcm.cloud.start.log.event.SysLogListener;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @ClassName LogAutoConfiguration
 * @Description 日志自动配置
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/22
 **/
@EnableAsync
@RequiredArgsConstructor
@ConditionalOnWebApplication
@Configuration(proxyBeanMethods = false)
public class LogAutoConfiguration {

    @Bean
    public SysLogListener sysLogListener(RemoteLogService remoteLogService) {
        return new SysLogListener(remoteLogService);
    }

    @Bean
    public SysLogAspect sysLogAspect() {
        return new SysLogAspect();
    }

}