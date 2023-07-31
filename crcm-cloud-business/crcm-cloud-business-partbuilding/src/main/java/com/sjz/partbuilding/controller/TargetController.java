package com.sjz.partbuilding.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.partbuilding.model.entity.Target;
import com.sjz.partbuilding.service.TargetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"党员积分指标管理接口"})
@RestController
@RequestMapping("/target")
public class TargetController extends BaseController {

    @Autowired
    private TargetService targetService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @SysLog(title = "新增党员积分指标", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.INSERT)
    public RestResult save(@RequestBody Target Target){
        targetService.saveTarget(Target);
        return RestResult.succeed();
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改党员积分指标", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody Target Target){
        targetService.updateTarget(Target);
        return RestResult.succeed();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @SysLog(title = "删除党员积分指标", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@RequestBody Target Target){
        targetService.deleteById(Target.getId());
        return RestResult.succeed();
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(String id){
        return RestResult.succeed(targetService.findById(id));
    }

    @GetMapping("/getList")
    @ApiOperation(value = "查询列表")
    public RestResult getList(Target Target){
        return RestResult.succeed(targetService.findList(Target));
    }


    @GetMapping("/getPage")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(Target Target, Page page){
        return RestResult.succeed(targetService.findPage(page,Target));
    }

}

