package com.crcm.bpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.bpm.domain.entity.HistoryDO;
import com.crcm.bpm.service.HistoryService;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"流程审批记录接口"})
@RestController
@RequestMapping("/flow/history")
public class HistoryController extends BaseController {

    @Autowired
    private HistoryService historyService;

    @SysLog(title = "新增流程审批记录")
    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public RestResult save(@RequestBody HistoryDO historyDO){
        historyService.saveHistory(historyDO);
        return RestResult.succeed();
    }

    @SysLog(title = "修改流程审批记录")
    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    public RestResult edit(@RequestBody HistoryDO historyDO){
        historyService.updateHistory(historyDO);
        return RestResult.succeed();
    }

    @SysLog(title = "删除流程审批记录")
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除")
    public RestResult delete(@PathVariable("id") String id){
        historyService.deleteById(id);
        return RestResult.succeed();
    }

    @SysLog(title = "通过ID查询流程审批记录")
    @GetMapping("/{id}")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(@PathVariable("id") String id){
        return RestResult.succeed(historyService.findById(id));
    }

    @SysLog(title = "查询列表流程审批记录")
    @GetMapping("/list")
    @ApiOperation(value = "查询列表")
    public RestResult getList(HistoryDO historyDO){
        return RestResult.succeed(historyService.findList(historyDO));
    }

    @SysLog(title = "分页查询流程审批记录")
    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(HistoryDO historyDO, Page page){
        return RestResult.succeed(historyService.findPage(page, historyDO));
    }

}

