package com.sjz.partbuilding.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.cloud.start.data.utils.UtilPage;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.sjz.partbuilding.model.entity.PartyMemberScore;
import com.sjz.partbuilding.service.PartyMemberScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.crcm.core.response.RestResult;

@Api(tags = {"党员积分管理接口"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/partyMemberScore")
public class PartyMemberScoreController extends BaseController {

    @Autowired
    private PartyMemberScoreService partyMemberScoreService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @SysLog(title = "新增党员积分管理", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.INSERT)
    public RestResult save(@RequestBody PartyMemberScore PartyMemberScore){
        partyMemberScoreService.savePartyMemberScore(PartyMemberScore);
        return RestResult.succeed();
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改党员积分管理", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody PartyMemberScore PartyMemberScore){
        partyMemberScoreService.updatePartyMemberScore(PartyMemberScore);
        return RestResult.succeed();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @SysLog(title = "删除党员积分管理", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@RequestBody PartyMemberScore PartyMemberScore){
        partyMemberScoreService.deleteById(PartyMemberScore.getId());
        return RestResult.succeed();
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(String id){
        return RestResult.succeed(partyMemberScoreService.findById(id));
    }

    @GetMapping("/getList")
    @ApiOperation(value = "查询列表")
    public RestResult getList(PartyMemberScore PartyMemberScore){
        return RestResult.succeed(partyMemberScoreService.findList(PartyMemberScore));
    }


    @GetMapping("/getPage")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(PartyMemberScore PartyMemberScore, Page page){
        return RestResult.succeed(partyMemberScoreService.findPage(page,PartyMemberScore));
    }

    @GetMapping("/getScorePage")
    @ApiOperation(value = "分页查询--积分排名")
    public RestResult getScorePage(PartyMemberScore PartyMemberScore, Page page){
        return RestResult.succeed(partyMemberScoreService.getScorePage(page,PartyMemberScore));
    }
}

