package com.crcm.bpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import com.crcm.bpm.domain.entity.NodeDO;
import com.crcm.bpm.service.NodeService;
import com.crcm.cloud.start.log.annation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"流程节点表接口"})
@RestController
@RequestMapping("/flow/node")
public class NodeController extends BaseController {

    @Autowired
    private NodeService nodeService;

    @SysLog(title = "新增流程节点")
    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public RestResult save(@RequestBody NodeDO nodeDO){
        nodeService.saveNode(nodeDO);
        return RestResult.succeed();
    }

    @SysLog(title = "修改流程节点")
    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    public RestResult edit(@RequestBody NodeDO nodeDO){
        nodeService.updateNode(nodeDO);
        return RestResult.succeed();
    }

    @SysLog(title = "删除流程节点")
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除")
    public RestResult delete(@PathVariable("id") String id){
        nodeService.deleteById(id);
        return RestResult.succeed();
    }

    @SysLog(title = "通过ID查询流程节点")
    @GetMapping("/{id}")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(@PathVariable("id") String id){
        return RestResult.succeed(nodeService.findById(id));
    }

    @SysLog(title = "查询列表流程节点")
    @GetMapping("/list")
    @ApiOperation(value = "查询列表")
    public RestResult getList(NodeDO nodeDO){
        return RestResult.succeed(nodeService.findList(nodeDO));
    }

    @SysLog(title = "分页查询流程节点")
    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(NodeDO nodeDO, Page page){
        return RestResult.succeed(nodeService.findPage(page, nodeDO));
    }

}

