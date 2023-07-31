package com.crcm.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.admin.model.entity.GatewayPlugins;
import com.crcm.cloud.start.data.mybatis.bean.PageT;

import java.util.List;

/**
 * 网关插件Service接口
 *
 * @author zzyt
 * @date 2022-06-10
 */
public interface GatewayPluginsService extends IService<GatewayPlugins> {

    /**
     * 新增网关插件
     *
     * @param gatewayPlugins 网关插件
     * @return 结果
     */
    boolean saveGatewayPlugins(GatewayPlugins gatewayPlugins);

    /**
     * 修改网关插件
     *
     * @param gatewayPlugins 网关插件
     * @return 结果
     */
    boolean updateGatewayPlugins(GatewayPlugins gatewayPlugins);

    /**
     * 删除网关插件信息
     *
     * @param id 网关插件ID
     * @return 结果
     */
    boolean deleteGatewayPlugins(String id);

    /**
     * 查询网关插件
     *
     * @param id 网关插件ID
     * @return 网关插件
     */
    GatewayPlugins findGatewayPluginsById(String id);

    /**
     * 查询网关插件列表
     *
     * @param gatewayPlugins 网关插件
     * @return 网关插件集合
     */
    List<GatewayPlugins> findGatewayPluginsList(GatewayPlugins gatewayPlugins);

    /**
     * 分页查询网关插件列表
     *
     * @param page           分页参数
     * @param gatewayPlugins 网关插件
     * @return 网关插件集合
     */
    PageT<GatewayPlugins> findGatewayPluginsPage(PageT<GatewayPlugins> page, GatewayPlugins gatewayPlugins);

    GatewayPlugins findByCompFilterName(String compFilterName);
}
