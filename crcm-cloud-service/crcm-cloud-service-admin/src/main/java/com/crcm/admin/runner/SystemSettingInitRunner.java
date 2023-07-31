package com.crcm.admin.runner;

import com.crcm.admin.config.event.SystemSettingRefreshEvent;
import com.crcm.admin.service.SysSettingsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @ClassName SystemSettingInitRunner
 * @Description 系统设置初始化
 * @Author GZL
 * @Date 2023/2/24 9:35
 * @Version 1.0
 **/
@Slf4j
@Configuration
@RequiredArgsConstructor
public class SystemSettingInitRunner {

    private final SysSettingsService sysSettingsService;

    @Async
    @Order
    @EventListener({WebServerInitializedEvent.class, SystemSettingRefreshEvent.class})
    public void initMenu() {
        long start = System.currentTimeMillis();
        log.info("初始化系统设置缓存");
        sysSettingsService.initSystemSettingRedis();
        log.info("系统设置缓存加载完成,用时{}毫秒", (System.currentTimeMillis() - start));
    }
}
