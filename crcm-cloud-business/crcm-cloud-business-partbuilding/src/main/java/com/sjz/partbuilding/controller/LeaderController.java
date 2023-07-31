package com.sjz.partbuilding.controller;


import com.crcm.cloud.start.data.utils.UtilPage;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.crcm.core.vo.PageVO;
import com.sjz.partbuilding.model.entity.Leader;
import com.sjz.partbuilding.service.LeaderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 领导班子controller
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/9/24 16:47
 */
@Api(tags = {"领导班子表接口"})
@RestController
@RequestMapping("/leader")
public class LeaderController extends BaseController {

    @Resource
    private LeaderService leaderService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @SysLog(title = "新增领导班子", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.INSERT)
    public RestResult save(@RequestBody Leader leader){
        return RestResult.succeed(leaderService.saveLeader(leader));
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改领导班子", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody Leader leader){
        return RestResult.succeed(leaderService.updateLeader(leader));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @SysLog(title = "删除领导班子", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@RequestBody Leader leader){
        leaderService.deleteById(leader.getId());
        return RestResult.succeed();
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(String id){
        return RestResult.succeed(leaderService.findById(id));
    }

    @GetMapping("/getList")
    @ApiOperation(value = "查询列表")
    public RestResult getList(Leader leader){
        return RestResult.succeed(leaderService.findList(leader));
    }

    @GetMapping("/getPage")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(Leader leader, PageVO pageVo){
        return RestResult.succeed(leaderService.findPage(UtilPage.initPage(pageVo),leader));
    }

    @GetMapping("/getLeaderByOrgId")
    @ApiOperation(value = "查询当前登录人是否是当前组织支委会成员")
    public RestResult getLeaderByOrgId(Leader leader){
        return RestResult.succeed(leaderService.getLeaderByOrgId(leader));
    }

}

