package com.crcm.bpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.bpm.domain.entity.CounterSignDO;
import com.crcm.bpm.service.CounterSignService;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import com.crcm.cloud.start.log.annation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"会签记录接口"})
@RestController
@RequestMapping("/counterSign")
public class CounterSignController extends BaseController {

    @Autowired
    private CounterSignService counterSignService;

    @SysLog(title = "新增会签记录")
    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public RestResult save(@RequestBody CounterSignDO counterSignDO){
        counterSignService.saveCounterSign(counterSignDO);
        return RestResult.succeed();
    }

    @SysLog(title = "修改会签记录")
    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    public RestResult edit(@RequestBody CounterSignDO counterSignDO){
        counterSignService.updateCounterSign(counterSignDO);
        return RestResult.succeed();
    }

    @SysLog(title = "删除会签记录")
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除")
    public RestResult delete(@PathVariable("id") String id){
        counterSignService.deleteById(id);
        return RestResult.succeed();
    }

    @SysLog(title = "通过ID查询会签记录")
    @GetMapping("/{id}")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(@PathVariable("id") String id){
        return RestResult.succeed(counterSignService.findById(id));
    }

    @SysLog(title = "查询列表会签记录")
    @GetMapping("/list")
    @ApiOperation(value = "查询列表")
    public RestResult getList(CounterSignDO counterSignDO){
        return RestResult.succeed(counterSignService.findList(counterSignDO));
    }

    @SysLog(title = "分页查询会签记录")
    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(CounterSignDO counterSignDO, Page page){
        return RestResult.succeed(counterSignService.findPage(page, counterSignDO));
    }

}

