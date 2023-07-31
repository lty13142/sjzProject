package com.crcm.bpm.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crcm.bpm.domain.entity.ProcessCollectionDO;
import com.crcm.bpm.service.ProcessCollectionService;
import com.crcm.core.response.RestResult;
import com.crcm.cloud.start.log.annation.SysLog;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 流程收藏表 前端控制器
 * </p>
 *
 * @author wxl
 * @since 2021-03-05
 */
@RestController
@RequestMapping("/process-collection")
public class ProcessCollectionController {

    @Resource
    private ProcessCollectionService processCollectionService;

    @SysLog(title = "添加收藏")
    @PostMapping("save")
    @ApiOperation("添加收藏")
    public RestResult save(@RequestBody ProcessCollectionDO processCollectionDO){
        return RestResult.succeed(processCollectionService.saveEntity(processCollectionDO));
    }

    @SysLog(title = "删除收藏")
    @PostMapping("delete/{processId}")
    @ApiOperation("删除收藏")
    public RestResult delete(@PathVariable Long processId){
        return RestResult.succeed(processCollectionService.remove(new LambdaQueryWrapper<ProcessCollectionDO>()
                .eq(ProcessCollectionDO::getProcessId, processId)));
    }
}
