package com.crcm.admin.controller;

import com.crcm.admin.model.entity.SysGatewayRoute;
import com.crcm.admin.service.SysGatewayRouteService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 路由配置Controller
 *
 * @author diaoy
 * @date 2021-06-24
 */
@Api(tags = "网关控制器")
@RestController
@RequiredArgsConstructor
@RequestMapping("/gatewayRoute")
public class GatewayRouteController extends BaseController {

    private final SysGatewayRouteService sysGatewayRouteService;

    /**
     * 新增路由配置
     */
    // @Log(title = "路由配置", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    @ApiOperation(value = "新增路由配置")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @SysLog(title = "新增路由配置", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody SysGatewayRoute sysGatewayRoute) {
        return RestResult.succeed(sysGatewayRouteService.saveRoute(sysGatewayRoute));
    }

    /**
     * 修改路由配置
     */
    // @Log(title = "路由配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ApiOperation(value = "修改路由配置")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @SysLog(title = "修改路由配置", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> edit(@Validated @RequestBody SysGatewayRoute sysGatewayRoute) {
        return RestResult.succeed(sysGatewayRouteService.updateRoute(sysGatewayRoute));
    }

    /**
     * 删除路由配置
     */
    // @Log(title = "路由配置", businessType = BusinessType.UPDATE)
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除路由配置")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @SysLog(title = "删除路由配置", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(sysGatewayRouteService.deleteRoute(id));
    }


    /**
     * 分页查询路由配置
     */
    @ApiOperation(value = "分页查询路由配置")
    @GetMapping("/page")
    public RestResult<PageT<SysGatewayRoute>> getPage(PageT<SysGatewayRoute> page, SysGatewayRoute sysGatewayRoute) {
        return RestResult.succeed(sysGatewayRouteService.findSysGatewayRoutePage(page, sysGatewayRoute));
    }

    /**
     * 查询路由配置列表
     */
    @ApiOperation(value = "查询路由配置列表")
    @GetMapping("/list")
    public RestResult<List<SysGatewayRoute>> getList(SysGatewayRoute sysGatewayRoute) {
        return RestResult.succeed(sysGatewayRouteService.findSysGatewayRouteList(sysGatewayRoute));
    }

    /**
     * 获取路由配置详细信息
     */
    @ApiOperation(value = "根据id获取路由配置详细信息")
    @GetMapping(value = "/get/{id}")
    public RestResult<SysGatewayRoute> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(sysGatewayRouteService.findSysGatewayRouteById(id));
    }

    /**
     * 获取路由配置详细信息
     */
    @ApiOperation(value = "获取路由配置详细信息")
    @GetMapping(value = "/export")
    public void export(SysGatewayRoute sysGatewayRoute) {

        sysGatewayRouteService.export(sysGatewayRoute, request, response);

    }

    /**
     * 刷新系统路由
     */
    @ApiOperation(value = "刷新系统路由")
    @PostMapping(value = "/replace")
    public RestResult reload(SysGatewayRoute sysGatewayRoute) {
        return sysGatewayRouteService.replaceRoutes();
    }
}
