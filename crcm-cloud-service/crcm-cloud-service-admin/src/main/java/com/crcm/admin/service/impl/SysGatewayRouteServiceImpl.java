
package com.crcm.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.api.feign.RemoteGatewayService;
import com.crcm.admin.mapper.SysGatewayRouteMapper;
import com.crcm.admin.model.entity.SysGatewayRoute;
import com.crcm.admin.model.excel.SysGatewayRouteModel;
import com.crcm.admin.service.SysGatewayRouteService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.gateway.dto.RouteDefinitionDTO;
import com.crcm.cloud.start.office.utils.UtilEasyExcel;
import com.crcm.core.constant.SystemBaseConstants;
import com.crcm.core.response.RestResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 动态路由处理类
 */
@Slf4j
@AllArgsConstructor
@Service("sysRouteConfService")
public class SysGatewayRouteServiceImpl extends ServiceImpl<SysGatewayRouteMapper, SysGatewayRoute> implements SysGatewayRouteService {
    private final RedisTemplate redisTemplate;

    private final RemoteGatewayService remoteGatewayService;


    /**
     * 获取全部路由
     * <p>
     * RedisRouteDefinitionWriter.java
     * PropertiesRouteDefinitionLocator.java
     *
     * @return
     */
    @Override
    public List<SysGatewayRoute> routes() {
        return this.list(Wrappers.emptyWrapper());
    }

    @Override
    public boolean saveRoute(SysGatewayRoute routeConf) {
        return this.save(routeConf);
    }

    @Override
    public boolean updateRoute(SysGatewayRoute routeConf) {
        return this.updateById(routeConf);
    }

    @Override
    public boolean deleteRoute(Serializable id) {
        return this.removeById(id);
    }

    @Override
    public RestResult replaceRoutes() {
        Boolean result = redisTemplate.delete(SystemBaseConstants.ROUTE_KEY);
        log.info("清空网关路由 {} ", result);
        if (result) {
            routes().forEach(route -> {
                RouteDefinitionDTO dto = new RouteDefinitionDTO();
                dto.setRouteName(route.getRouteName());
                dto.setId(route.getRouteCode());
                dto.setFilters(route.getRouteFilters());
                dto.setPredicates(route.getRoutePredicates());
                dto.setUri(route.getRouteUri());
                dto.setOrder(route.getRouteOrder());
                log.info("加载路由ID：{},{}", route.getRouteCode(), dto);
                redisTemplate.opsForHash().put(SystemBaseConstants.ROUTE_KEY, route.getRouteCode(), dto);
            });
        }
        // 远程调用刷新路由
        return remoteGatewayService.replaceRoute();
    }

    /**
     * 查询路由配置
     *
     * @param id 路由配置ID
     * @return 路由配置
     */
    @Override
    public SysGatewayRoute findSysGatewayRouteById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询路由配置列表
     *
     * @param sysGatewayRoute 路由配置
     * @return 路由配置
     */
    @Override
    public List<SysGatewayRoute> findSysGatewayRouteList(SysGatewayRoute sysGatewayRoute) {
        LambdaQueryWrapper<SysGatewayRoute> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询路由配置
     *
     * @param page            分页参数
     * @param sysGatewayRoute 路由配置
     * @return 路由配置
     */
    @Override
    public PageT<SysGatewayRoute> findSysGatewayRoutePage(PageT<SysGatewayRoute> page, SysGatewayRoute sysGatewayRoute) {
        LambdaQueryWrapper<SysGatewayRoute> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }

    @Override
    public void export(SysGatewayRoute sysGatewayRoute, HttpServletRequest request, HttpServletResponse response) {
        List<SysGatewayRoute> list = findSysGatewayRouteList(sysGatewayRoute);
        if (CollectionUtil.isNotEmpty(list)) {
            List<SysGatewayRouteModel> data = list.stream().map(r -> BeanUtil.copyProperties(r, SysGatewayRouteModel.class)).collect(Collectors.toList());
            try {
                String fileName = "路由配置.xlsx";
                UtilEasyExcel.downloadExcelByModel(response, data, fileName, SysGatewayRouteModel.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
