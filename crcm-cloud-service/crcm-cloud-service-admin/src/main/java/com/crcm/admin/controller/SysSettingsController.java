package com.crcm.admin.controller;

import com.crcm.admin.model.entity.SysSettings;
import com.crcm.admin.service.SysSettingsService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统设置Controller
 *
 * @author zzyt
 * @date 2021-06-25
 */
@Api(tags = {"系统设置接口"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/settings")
public class SysSettingsController extends BaseController {

    private final SysSettingsService sysSettingsService;

    /**
     * 新增系统设置
     */
    // @Log(title = "系统设置", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public RestResult<Boolean> add(@Validated @RequestBody SysSettings sysSettings) {
        return RestResult.succeed(sysSettingsService.saveSysSettings(sysSettings));
    }

    /**
     * 修改系统设置
     */
    // @Log(title = "系统设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public RestResult<Boolean> edit(@Validated @RequestBody SysSettings sysSettings) {
        return RestResult.succeed(sysSettingsService.updateSysSettings(sysSettings));
    }

    /**
     * 删除系统设置
     */
    // @Log(title = "系统设置", businessType = BusinessType.UPDATE)
    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public RestResult<Boolean> delete(@PathVariable("id") Long id) {
        return RestResult.succeed(sysSettingsService.deleteSysSettings(id));
    }


    /**
     * 分页查询系统设置
     */
    @GetMapping("/page")
    public RestResult<PageT<SysSettings>> getPage(PageT<SysSettings> page, SysSettings sysSettings) {
        return RestResult.succeed(sysSettingsService.findSysSettingsPage(page, sysSettings));
    }

    /**
     * 查询系统设置列表
     */
    @GetMapping("/list")
    public RestResult<List<SysSettings>> getList(SysSettings sysSettings) {
        return RestResult.succeed(sysSettingsService.findSysSettingsList(sysSettings));
    }

    /**
     * 获取系统设置详细信息
     */
    @GetMapping(value = "/get/{id}")
    public RestResult<SysSettings> getInfo(@PathVariable("id") Long id) {
        return RestResult.succeed(sysSettingsService.findSysSettingsById(id));
    }

    @GetMapping("/code/{code}")
    @ApiOperation(value = "根据code查询值")
    public RestResult<SysSettings> getList(@PathVariable("code") String code) {
        return RestResult.succeed(sysSettingsService.getValueByCode(code));
    }
}
