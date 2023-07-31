package com.crcm.bpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.bpm.domain.entity.NodeUserDO;
import com.crcm.bpm.service.NodeUserService;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"节点人员表接口"})
@RestController
@RequestMapping("/flow/nodeUser")
public class NodeUserController extends BaseController {

    @Autowired
    private NodeUserService nodeUserService;

    @SysLog(title = "新增节点人员")
    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public RestResult save(@RequestBody NodeUserDO nodeUserDO){
        nodeUserService.saveNodeUser(nodeUserDO);
        return RestResult.succeed();
    }

    @SysLog(title = "修改节点人员")
    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    public RestResult edit(@RequestBody NodeUserDO nodeUserDO){
        nodeUserService.updateNodeUser(nodeUserDO);
        return RestResult.succeed();
    }

    @SysLog(title = "删除节点人员")
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除")
    public RestResult delete(@PathVariable("id") String id){
        nodeUserService.deleteById(id);
        return RestResult.succeed();
    }

    @SysLog(title = "通过ID查询节点人员")
    @GetMapping("/{id}")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(@PathVariable("id") String id){
        return RestResult.succeed(nodeUserService.findById(id));
    }

    @SysLog(title = "查询列表节点人员")
    @GetMapping("/list")
    @ApiOperation(value = "查询列表")
    public RestResult getList(NodeUserDO nodeUserDO){
        return RestResult.succeed(nodeUserService.findList(nodeUserDO));
    }

    @SysLog(title = "分页查询节点人员")
    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(NodeUserDO nodeUserDO, Page page){
        return RestResult.succeed(nodeUserService.findPage(page, nodeUserDO));
    }

}

