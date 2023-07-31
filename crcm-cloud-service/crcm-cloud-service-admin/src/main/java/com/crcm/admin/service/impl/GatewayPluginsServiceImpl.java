package com.crcm.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.mapper.GatewayPluginsMapper;
import com.crcm.admin.model.entity.GatewayPlugins;
import com.crcm.admin.service.GatewayPluginsService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 网关插件Service业务层处理
 *
 * @author zzyt
 * @date 2022-06-10
 */
@Service
public class GatewayPluginsServiceImpl extends ServiceImpl<GatewayPluginsMapper, GatewayPlugins> implements GatewayPluginsService {


    /**
     * 新增网关插件
     *
     * @param gatewayPlugins 网关插件
     * @return 结果
     */
    @Override
    public boolean saveGatewayPlugins(GatewayPlugins gatewayPlugins) {
        return this.save(gatewayPlugins);
    }

    /**
     * 修改网关插件
     *
     * @param gatewayPlugins 网关插件
     * @return 结果
     */
    @Override
    public boolean updateGatewayPlugins(GatewayPlugins gatewayPlugins) {
        return this.updateById(gatewayPlugins);
    }

    /**
     * 删除网关插件信息
     *
     * @param id 网关插件ID
     * @return 结果
     */
    @Override
    public boolean deleteGatewayPlugins(String id) {
        return this.removeById(id);
    }

    /**
     * 查询网关插件
     *
     * @param id 网关插件ID
     * @return 网关插件
     */
    @Override
    public GatewayPlugins findGatewayPluginsById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询网关插件列表
     *
     * @param plugins 网关插件
     * @return 网关插件
     */
    @Override
    public List<GatewayPlugins> findGatewayPluginsList(GatewayPlugins plugins) {
        LambdaQueryWrapper<GatewayPlugins> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(plugins.getCompName()), GatewayPlugins::getCompName, plugins.getCompName())
                .like(StringUtils.isNotBlank(plugins.getCompFilterName()), GatewayPlugins::getCompFilterName, plugins.getCompFilterName())
                .eq(plugins.getEnabled() != null, GatewayPlugins::getEnabled, plugins.getEnabled())
                .eq(StringUtils.isNotBlank(plugins.getId()), GatewayPlugins::getId, plugins.getId())
                .eq(StringUtils.isNotBlank(plugins.getCompMethod()), GatewayPlugins::getCompMethod, plugins.getCompMethod())
                .eq(StringUtils.isNotBlank(plugins.getCompType()), GatewayPlugins::getCompMethod, plugins.getCompType());
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询网关插件
     *
     * @param page    分页参数
     * @param plugins 网关插件
     * @return 网关插件
     */
    @Override
    public PageT<GatewayPlugins> findGatewayPluginsPage(PageT<GatewayPlugins> page, GatewayPlugins plugins) {
        LambdaQueryWrapper<GatewayPlugins> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(plugins.getCompName()), GatewayPlugins::getCompName, plugins.getCompName())
                .like(StringUtils.isNotBlank(plugins.getCompFilterName()), GatewayPlugins::getCompFilterName, plugins.getCompFilterName())
                .eq(plugins.getEnabled() != null, GatewayPlugins::getEnabled, plugins.getEnabled())
                .eq(StringUtils.isNotBlank(plugins.getCompMethod()), GatewayPlugins::getCompMethod, plugins.getCompMethod())
                .eq(StringUtils.isNotBlank(plugins.getCompType()), GatewayPlugins::getCompMethod, plugins.getCompType());
        return this.page(page, queryWrapper);
    }

    @Override
    public GatewayPlugins findByCompFilterName(String compFilterName) {
        LambdaQueryWrapper<GatewayPlugins> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GatewayPlugins::getCompFilterName, compFilterName).last("limit 1");
        return this.getOne(queryWrapper);
    }
}
