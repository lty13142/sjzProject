package com.crcm.admin.controller;

import com.crcm.admin.model.entity.GatewayPlugins;
import com.crcm.admin.service.GatewayPluginsService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 网关插件Controller
 *
 * @author zzyt
 * @date 2022-06-10
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/gateway/plugins")
public class GatewayPluginsController extends BaseController {

    private final GatewayPluginsService gatewayPluginsService;

    /**
     * 新增网关插件
     */
    // @Log(title = "网关插件", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    @SysLog(title = "新增网关插件", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody GatewayPlugins gatewayPlugins) {
        return RestResult.succeed(gatewayPluginsService.saveGatewayPlugins(gatewayPlugins));
    }

    /**
     * 修改网关插件
     */
    // @Log(title = "网关插件", businessType = BusinessType.UPDATE)
//    @PreAuthorize("hasAuthority('gateway:gatewayPlugins:edit')")
    @PostMapping("/edit")
    @SysLog(title = "修改网关插件", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody GatewayPlugins gatewayPlugins) {
        return RestResult.succeed(gatewayPluginsService.updateGatewayPlugins(gatewayPlugins));
    }

    /**
     * 删除网关插件
     */
    // @Log(title = "网关插件", businessType = BusinessType.UPDATE)
//    @PreAuthorize("hasAuthority('gateway:gatewayPlugins:delete')")
    @PostMapping("/delete/{id}")
    @SysLog(title = "删除网关插件", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(gatewayPluginsService.deleteGatewayPlugins(id));
    }


    /**
     * 分页查询网关插件
     */
//    @PreAuthorize("hasAuthority('gateway:gatewayPlugins:view')")
    @GetMapping("/page")
    public RestResult<PageT<GatewayPlugins>> getPage(PageT<GatewayPlugins> page, GatewayPlugins gatewayPlugins) {
        return RestResult.succeed(gatewayPluginsService.findGatewayPluginsPage(page, gatewayPlugins));
    }

    /**
     * 查询网关插件列表
     */
    @GetMapping("/list")
    public RestResult<List<GatewayPlugins>> getList(GatewayPlugins gatewayPlugins) {
        return RestResult.succeed(gatewayPluginsService.findGatewayPluginsList(gatewayPlugins));
    }

    /**
     * 获取网关插件详细信息
     */
    @GetMapping(value = "/{id}")
    public RestResult<GatewayPlugins> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(gatewayPluginsService.findGatewayPluginsById(id));
    }

    /**
     * 获取网关插件详细信息
     */
    @GetMapping("/findByCompFilterName")
    public RestResult<GatewayPlugins> findByCompFilterName(String compFilterName) {
        return RestResult.succeed(gatewayPluginsService.findByCompFilterName(compFilterName));
    }
}
