package com.crcm.admin.runner;

import com.crcm.admin.config.event.SystemMenuRefreshEvent;
import com.crcm.admin.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @ClassName SystemMenuInitRunner
 * @Description 系统菜单初始化
 * @Author GZL
 * @Date 2023/2/15 11:38
 * @Version 1.0
 **/
@Slf4j
@Configuration
@RequiredArgsConstructor
public class SystemMenuInitRunner {

    private final MenuService menuService;

    @Async
    @Order
    @EventListener({WebServerInitializedEvent.class, SystemMenuRefreshEvent.class})
    public void initMenu() {
        long start = System.currentTimeMillis();
        log.info("初始化系统菜单缓存");
        menuService.initMenuIconPathRedis();
        log.info("菜单缓存加载完成,用时{}毫秒", (System.currentTimeMillis() - start));
    }
}
