package com.crcm.admin.config.event;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName SystemMenuRefreshEvent
 * @Description 系统菜单更新事件
 * @Author GZL
 * @Date 2023/2/15 11:37
 * @Version 1.0
 **/
public class SystemMenuRefreshEvent extends ApplicationEvent {

    public SystemMenuRefreshEvent(Object source) {
        super(source);
    }
}
