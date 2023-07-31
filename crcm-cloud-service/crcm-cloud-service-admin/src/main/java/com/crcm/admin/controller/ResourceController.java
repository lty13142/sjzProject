package com.crcm.admin.controller;


import com.crcm.admin.model.entity.SysResource;
import com.crcm.admin.service.ResourceService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.dto.TreeView;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "系统资源接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("resource")
public class ResourceController extends BaseController {

    private final ResourceService resourceService;


    @PostMapping("/save")
    @ApiOperation(value = "新增资源")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public RestResult<Boolean> add(@RequestBody SysResource t) {
        return RestResult.succeed(resourceService.addResource(t));
    }

    @PostMapping("/saveList")
    @ApiOperation(value = "新增资源目录")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public RestResult<Boolean> addActionList(@RequestBody SysResource t) {
        return RestResult.succeed(resourceService.addResourceList(t));
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改功能（单指功能）")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public RestResult<Boolean> update(@RequestBody SysResource t) {
        return RestResult.succeed(resourceService.update(t));
    }


    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public RestResult<Boolean> delete(@PathVariable("id") Long id) {
        return RestResult.succeed(resourceService.deleteById(id));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "通过ID查询")
    public RestResult<SysResource> getById(@PathVariable("id") String id) {
        return RestResult.succeed(resourceService.findById(id));
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询列表")
    public RestResult<List<SysResource>> getList(SysResource t) {
        return RestResult.succeed(resourceService.findList(t));
    }


    @GetMapping("/page")
    @ApiOperation(value = "分页查询资源")
    public RestResult<PageT<SysResource>> getPage(SysResource t, PageT<SysResource> page) {
        t.setType(SystemConstant.RESOURCE_TYPE.RESOURCE);
        return RestResult.succeed(resourceService.findPage(page, t));
    }

    @GetMapping("/page/list")
    @ApiOperation(value = "分页查询资源目录")
    public RestResult<PageT<SysResource>> getPageList(SysResource t, PageT<SysResource> page) {
        t.setType(SystemConstant.RESOURCE_TYPE.LIST);
        return RestResult.succeed(resourceService.findPage(page, t));
    }

    @GetMapping("/tree")
    @ApiOperation(value = "查询功能树")
    public RestResult<List<TreeView>> getTree(SysResource t) {
        return RestResult.succeed(resourceService.findTree(t));
    }

}

