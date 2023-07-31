package com.crcm.admin.controller;


import com.crcm.admin.model.dto.QueryDTO;
import com.crcm.admin.model.entity.SysLog;
import com.crcm.admin.service.SysLogService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.sso.annation.Inner;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = {"日志信息接口"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
public class SysLogController extends BaseController {

    private final SysLogService sysLogService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @Inner
    public RestResult<Integer> save(@RequestBody SysLog sysLog) {
        return RestResult.succeed(sysLogService.saveSysLog(sysLog));
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    public RestResult<Integer> edit(@RequestBody SysLog sysLog) {
        return RestResult.succeed(sysLogService.updateSysLog(sysLog));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    public RestResult<Integer> delete(@RequestBody SysLog sysLog) {
        return RestResult.succeed(sysLogService.deleteById(sysLog.getId()));
    }

    @GetMapping("/getList")
    @ApiOperation(value = "查询列表")
    public RestResult<List<SysLog>> getList(SysLog sysLog) {
        return RestResult.succeed(sysLogService.findList(sysLog));
    }


    @GetMapping("/getPage")
    @ApiOperation(value = "分页查询")
    public RestResult<PageT<SysLog>> getPage(SysLog sysLog, PageT<SysLog> page, QueryDTO queryDTO) {
        return RestResult.succeed(sysLogService.findPage(page, sysLog, queryDTO));
    }

}

