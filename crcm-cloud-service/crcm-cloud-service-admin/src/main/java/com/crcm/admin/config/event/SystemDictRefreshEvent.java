package com.crcm.admin.config.event;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName SystemDictInitEvent
 * @Description 系统字典更新事件
 * @Copyright Copyright(c) 2020
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2020/06/2020/6/30
 **/
public class SystemDictRefreshEvent extends ApplicationEvent {
    public SystemDictRefreshEvent(Object source) {
        super(source);
    }
}
