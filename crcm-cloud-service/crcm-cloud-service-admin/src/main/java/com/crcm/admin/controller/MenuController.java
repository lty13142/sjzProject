package com.crcm.admin.controller;

import com.crcm.admin.model.dto.MenuEnabledDTO;
import com.crcm.admin.model.dto.MenuQueryDTO;
import com.crcm.admin.model.entity.SysMenu;
import com.crcm.admin.service.MenuService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.dto.TreeView;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "系统菜单接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController extends BaseController {

    private final MenuService menuService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @ApiOperation(value = "保存系统菜单")
    @SysLog(title = "保存系统菜单", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<String> save(@Valid @RequestBody SysMenu t) {
        String result = menuService.saveMenu(t);
        return StringUtils.isBlank(result) ? RestResult.succeed() : RestResult.failed(result);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @ApiOperation(value = "修改系统菜单")
    @SysLog(title = "修改系统菜单", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<String> update(@Valid @RequestBody SysMenu t) {
        String result = menuService.updateMenu(t);
        return StringUtils.isBlank(result) ? RestResult.succeed() : RestResult.failed(result);
    }

    @PostMapping("/enabled")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @ApiOperation(value = "菜单启用/停用")
    @SysLog(title = "菜单启用/停用", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<String> enabled(@Valid @RequestBody MenuEnabledDTO t) {
        menuService.updateMenuEnabled(t);
        return RestResult.succeed();
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @ApiOperation(value = "删除系统菜单")
    @SysLog(title = "删除系统菜单", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Integer> delete(@PathVariable("id") Long id) {
        return RestResult.succeed(menuService.deleteById(id));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "通过ID查询系统菜单")
    public RestResult<SysMenu> getById(@PathVariable("id") String id) {
        return RestResult.succeed(menuService.findById(id));
    }


    @GetMapping("/page")
    @ApiOperation(value = "分页查询系统菜单")
    public RestResult<PageT<SysMenu>> getPage(MenuQueryDTO t, PageT<SysMenu> page) {
        return RestResult.succeed(menuService.findPage(page, t));
    }

    @GetMapping("/tree")
    @ApiOperation(value = "查询系统菜单树")
    public RestResult<List<TreeView>> getTree(MenuQueryDTO t) {
        return RestResult.succeed(menuService.findTree(t));
    }

    @GetMapping("/menuBtnTree")
    @ApiOperation(value = "查询系统菜单按钮树")
    public RestResult<List<TreeView>> getMenuBtnTree(MenuQueryDTO t) {
        return RestResult.succeed(menuService.findMenuBtnTree(t));
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询系统菜单列表")
    public RestResult<List<SysMenu>> getListMenus(MenuQueryDTO t) {
        return RestResult.succeed(menuService.findListMenus(t));
    }

}

