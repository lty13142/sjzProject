package com.crcm.admin.config.event;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName SystemSettingRefreshEvent
 * @Description 系统设置刷新事件
 * @Author GZL
 * @Date 2023/2/24 9:34
 * @Version 1.0
 **/
public class SystemSettingRefreshEvent extends ApplicationEvent {
    public SystemSettingRefreshEvent(Object source) {
        super(source);
    }
}
