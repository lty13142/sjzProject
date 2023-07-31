package com.sjz.partbuilding.controller;


import com.crcm.cloud.start.data.utils.UtilPage;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.crcm.core.vo.PageVO;
import com.sjz.partbuilding.model.entity.WorkDeploy;
import com.sjz.partbuilding.service.WorkDeployService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 工作部署controller
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/17 11:29
 */
@Api(tags = {"工作部署表接口"})
@RestController
@RequestMapping("/workDeploy")
public class WorkDeployController extends BaseController {

    @Resource
    private WorkDeployService workDeployService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @SysLog(title = "新增工作部署", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.INSERT)
    public RestResult save(@RequestBody WorkDeploy workDeploy){
        workDeployService.saveWorkDeploy(workDeploy);
        return RestResult.succeed();
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改工作部署", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody WorkDeploy workDeploy){
        workDeployService.updateWorkDeploy(workDeploy);
        return RestResult.succeed();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @SysLog(title = "删除工作部署", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@RequestBody WorkDeploy workDeploy){
        workDeployService.deleteById(workDeploy.getId());
        return RestResult.succeed();
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(String id){
        return RestResult.succeed(workDeployService.findById(id));
    }

    @GetMapping("/getList")
    @ApiOperation(value = "查询列表")
    public RestResult getList(WorkDeploy workDeploy){
        return RestResult.succeed(workDeployService.findList(workDeploy));
    }


    @GetMapping("/getPage")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(WorkDeploy workDeploy,PageVO pageVo){
        return RestResult.succeed(workDeployService.findPage(UtilPage.initPage(pageVo),workDeploy));
    }

    @GetMapping("/getWorkFeedbackPage")
    @ApiOperation(value = "获取工作反馈分页查询")
    public RestResult getWorkFeedbackPage(WorkDeploy workDeploy, PageVO pageVo){
        return RestResult.succeed(workDeployService.getWorkFeedbackPage(UtilPage.initPage(pageVo),workDeploy));
    }

}

