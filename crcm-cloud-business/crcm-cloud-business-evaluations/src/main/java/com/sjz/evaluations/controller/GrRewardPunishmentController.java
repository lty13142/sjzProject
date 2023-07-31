package com.sjz.evaluations.controller;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.evaluations.model.entity.GrRewardPunishment;
import com.sjz.evaluations.model.vo.RewardPunishmentVo;
import com.sjz.evaluations.service.GrRewardPunishmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 奖惩事项Controller
 * 
 * @author guozhilin
 * @date 2023-04-04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/rewardPunishment")
@Api(tags = "奖惩事项")
public class GrRewardPunishmentController extends BaseController {


    private final GrRewardPunishmentService grRewardPunishmentService;

    /**
     * 新增奖惩事项
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增奖惩事项")
    @SysLog(title = "新增奖惩事项", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody GrRewardPunishment grRewardPunishment) {
        return RestResult.succeed(grRewardPunishmentService.saveGrRewardPunishment(grRewardPunishment));
    }

    /**
     * 修改奖惩事项
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改奖惩事项")
    @SysLog(title = "修改奖惩事项", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody GrRewardPunishment grRewardPunishment) {
        return RestResult.succeed(grRewardPunishmentService.updateGrRewardPunishment(grRewardPunishment));
    }

    /**
     * 删除奖惩事项
     */
    @PostMapping("/delete/{name}")
    @ApiOperation(value = "删除奖惩事项")
    @SysLog(title = "删除奖惩事项", serviceId = ServiceNameConstants.EVALUATIONS_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("name") String name) {
        return RestResult.succeed(grRewardPunishmentService.deleteGrRewardPunishment(name));
    }


    /**
     * 分页查询奖惩事项
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询奖惩事项")
    public RestResult<PageT<GrRewardPunishment>> getPage(PageT<GrRewardPunishment> page, GrRewardPunishment grRewardPunishment) {
        return RestResult.succeed(grRewardPunishmentService.findGrRewardPunishmentPage(page, grRewardPunishment));
    }

    /**
     * 查询奖惩事项列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询奖惩事项列表")
    public RestResult<List<GrRewardPunishment>> getList(GrRewardPunishment grRewardPunishment) {
        return RestResult.succeed(grRewardPunishmentService.findGrRewardPunishmentList(grRewardPunishment));
    }

    /**
     * 获取奖惩事项详细信息
     */
    @GetMapping(value = "/get/{name}")
    @ApiOperation(value = "获取奖惩事项详细信息")
    public RestResult<GrRewardPunishment> getInfo(@PathVariable("name") String name) {
        return RestResult.succeed(grRewardPunishmentService.findGrRewardPunishmentById(name));
    }

    @GetMapping("/penaltyStatistics")
    @ApiOperation(value = "大屏统计惩罚查询")
    public RestResult<List< Map<String,Object>>> penaltyStatistics(){
        return RestResult.succeed(grRewardPunishmentService.penaltyStatistics());
    }
}
