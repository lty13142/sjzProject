package com.crcm.bpm.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.bpm.domain.dto.request.ProcessInfoReqDTO;
import com.crcm.bpm.domain.entity.ProcessDO;
import com.crcm.bpm.domain.vo.ProcessInfoReqVO;
import com.crcm.bpm.service.ProcessService;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"流程表接口"})
@RestController
@RequestMapping("/flow/process")
public class ProcessController extends BaseController {

    @Autowired
    private ProcessService processService;

    @SysLog(title = "新增流程")
    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public RestResult save(@RequestBody ProcessDO processDO){
        processService.saveProcess(processDO);
        return RestResult.succeed();
    }

    @SysLog(title = "修改流程")
    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    public RestResult edit(@RequestBody ProcessDO processDO){
        processService.updateProcess(processDO);
        return RestResult.succeed();
    }

    @SysLog(title = "删除流程")
    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    public RestResult delete(@RequestBody ProcessDO processDO){
        processService.deleteById(processDO.getId());
        return RestResult.succeed();
    }

    @SysLog(title = "通过ID查询流程")
    @GetMapping("/{id}")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(@PathVariable("id") String id){
        return RestResult.succeed(processService.findById(id));
    }

    @SysLog(title = "查询列表流程")
    @GetMapping("/list")
    @ApiOperation(value = "查询列表")
    public RestResult getList(ProcessDO processDO){
        return RestResult.succeed(processService.findList(processDO));
    }

    @SysLog(title = "分页查询流程")
    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(ProcessDO processDO, Page page){
        return RestResult.succeed(processService.findPage(page, processDO));
    }

    @SysLog(title = "获取流程开始数据")
    @GetMapping("/getStartInfo")
    @ApiOperation(value = "获取流程开始数据")
    public RestResult getStartInfo(ProcessInfoReqVO process){
        ProcessInfoReqDTO processInfoDTO = BeanUtil.copyProperties(process, ProcessInfoReqDTO.class);
        return RestResult.succeed(processService.getStartInfo(processInfoDTO));
    }

    @SysLog(title = "分类查询流程列表")
    @GetMapping("/getSortList")
    @ApiOperation(value = "分类查询流程列表")
    public RestResult getSortList(String flowType, String processName){
        return RestResult.succeed(processService.getSortList(flowType, processName));
    }

    @SysLog(title = "获取我收藏的流程列表")
    @GetMapping("/getMyCollection")
    @ApiOperation(value = "获取我收藏的流程列表")
    public RestResult getMyCollection(){
        return RestResult.succeed(processService.getMyCollection());
    }
}

