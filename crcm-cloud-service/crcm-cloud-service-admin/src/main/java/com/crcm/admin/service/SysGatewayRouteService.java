
package com.crcm.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.admin.model.entity.SysGatewayRoute;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.response.RestResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

/**
 * 路由
 */
public interface SysGatewayRouteService extends IService<SysGatewayRoute> {

    /**
     * 获取全部路由
     * <p>
     * RedisRouteDefinitionWriter.java
     * PropertiesRouteDefinitionLocator.java
     *
     * @return 路由列表
     */
    List<SysGatewayRoute> routes();

    /**
     * 添加路由
     *
     * @param routeConf 路由
     * @return 结果
     */
    boolean saveRoute(SysGatewayRoute routeConf);

    /**
     * 修改路由
     *
     * @param routeConf 路由
     * @return 结果
     */
    boolean updateRoute(SysGatewayRoute routeConf);

    /**
     * 根据ID删除路由
     *
     * @param id 路由id
     * @return 结果
     */
    boolean deleteRoute(Serializable id);

    /**
     * 刷新缓存
     *
     * @return 结果
     */
    RestResult replaceRoutes();

    /**
     * 查询路由配置
     *
     * @param id 路由配置ID
     * @return 路由配置
     */
    SysGatewayRoute findSysGatewayRouteById(String id);

    /**
     * 查询路由配置列表
     *
     * @param sysGatewayRoute 路由配置
     * @return 路由配置集合
     */
    List<SysGatewayRoute> findSysGatewayRouteList(SysGatewayRoute sysGatewayRoute);

    /**
     * 分页查询路由配置列表
     *
     * @param page            分页参数
     * @param sysGatewayRoute 路由配置
     * @return 路由配置集合
     */
    PageT<SysGatewayRoute> findSysGatewayRoutePage(PageT<SysGatewayRoute> page, SysGatewayRoute sysGatewayRoute);

    /**
     * 导出
     *
     * @param sysGatewayRoute 对象
     * @param request         请求
     * @param response        响应
     */
    void export(SysGatewayRoute sysGatewayRoute, HttpServletRequest request, HttpServletResponse response);
}

