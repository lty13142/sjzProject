package com.crcm.cloud.start.gateway.support;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName DynamicRouteInitEvent
 * @Description 路由初始化事件
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/5/8
 **/
public class DynamicRouteInitEvent extends ApplicationEvent {
    public DynamicRouteInitEvent(Object source) {
        super(source);
    }
}
