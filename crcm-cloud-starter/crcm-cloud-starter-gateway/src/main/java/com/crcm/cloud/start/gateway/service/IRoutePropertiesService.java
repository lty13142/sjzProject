package com.crcm.cloud.start.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

/**
 * @ClassName IRoutePropertiesService
 * @Description 数据库路由服务接口 封装 路由表配置服务接口以及路由参数表配置接口, 对外层提供对数据库路由信息的操作.
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/5/11
 **/
public interface IRoutePropertiesService {
    List<RouteDefinition> getRouteDefinitionList();
}
