package com.crcm.cloud.start.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

/**
 * @ClassName IGatewayRouteService
 * @Description 网关路由缓存服务 封装 RouteDefinitionRepository 接口,对外提供对网关路由缓存的刷新.
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/5/11
 **/
public interface IGatewayRouteService {
    /**
     * 添加路由信息
     * @param routeDefinition
     */
    void addInMemoryRouteRefresh(RouteDefinition routeDefinition);
}
