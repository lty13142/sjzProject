package com.crcm.bpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.bpm.domain.entity.HistoryFormDataDO;
import com.crcm.bpm.service.HistoryFormDataService;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import com.crcm.cloud.start.log.annation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"流程历史数据表接口"})
@RestController
@RequestMapping("/historyFormData")
public class HistoryFormDataController extends BaseController {

    @Autowired
    private HistoryFormDataService historyFormDataService;

    @SysLog(title = "新增流程历史数据")
    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public RestResult save(@RequestBody HistoryFormDataDO historyFormDataDO){
        historyFormDataService.saveHistoryFormData(historyFormDataDO);
        return RestResult.succeed();
    }

    @SysLog(title = "修改流程历史数据")
    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    public RestResult edit(@RequestBody HistoryFormDataDO historyFormDataDO){
        historyFormDataService.updateHistoryFormData(historyFormDataDO);
        return RestResult.succeed();
    }

    @SysLog(title = "删除流程历史数据")
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除")
    public RestResult delete(@PathVariable("id") String id){
        historyFormDataService.deleteById(id);
        return RestResult.succeed();
    }

    @SysLog(title = "通过ID查询流程历史数据")
    @GetMapping("/{id}")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(@PathVariable("id") String id){
        return RestResult.succeed(historyFormDataService.findById(id));
    }

    @SysLog(title = "查询列表流程历史数据")
    @GetMapping("/list")
    @ApiOperation(value = "查询列表")
    public RestResult getList(HistoryFormDataDO historyFormDataDO){
        return RestResult.succeed(historyFormDataService.findList(historyFormDataDO));
    }

    @SysLog(title = "分页查询流程历史数据")
    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(HistoryFormDataDO historyFormDataDO, Page page){
        return RestResult.succeed(historyFormDataService.findPage(page, historyFormDataDO));
    }

}

