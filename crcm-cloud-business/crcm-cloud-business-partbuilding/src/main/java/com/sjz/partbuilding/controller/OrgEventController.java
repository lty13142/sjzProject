package com.sjz.partbuilding.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.partbuilding.model.entity.OrgEvent;
import com.sjz.partbuilding.service.OrgEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"组织活动表接口"})
@RestController
@RequestMapping("/orgEvent")
public class OrgEventController extends BaseController {

    @Autowired
    private OrgEventService orgEventService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @SysLog(title = "新增组织活动", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.INSERT)
    public RestResult save(@RequestBody OrgEvent orgEvent){
        orgEventService.saveOrgEvent(orgEvent);
        return RestResult.succeed();
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改组织活动", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody @Validated OrgEvent orgEvent){
        orgEventService.updateOrgEvent(orgEvent);
        return RestResult.succeed();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @SysLog(title = "删除组织活动", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@RequestBody OrgEvent orgEvent){
        orgEventService.deleteById(orgEvent.getId());
        return RestResult.succeed();
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(String id){
        return RestResult.succeed(orgEventService.findById(id));
    }

    @GetMapping("/getList")
    @ApiOperation(value = "查询列表")
    public RestResult getList(OrgEvent orgEvent){
        return RestResult.succeed(orgEventService.findList(orgEvent));
    }


    @GetMapping("/getPage")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(OrgEvent orgEvent, Page page){
        return RestResult.succeed(orgEventService.findPage(page,orgEvent));
    }

    @GetMapping("/getMeets")
    @ApiOperation(value = "获取类型为 0：党员大会， 1：支委会， 2：党小组会的全部数据")
    public RestResult getMeets(@RequestParam(value = "title", required = false) String title,  Page page){
        return RestResult.succeed(orgEventService.getMeets(page,title));
    }

    @GetMapping("/findTypeList")
    @ApiOperation(value = "数据统计查询各类活动数据量")
    public RestResult findTypeList(OrgEvent orgEvent){
        return RestResult.succeed(orgEventService.findTypeList(orgEvent));
    }

    @GetMapping("findTypeMap")
    @ApiOperation("统计当前月前6个月的数据量")
    public RestResult findTypeMap(){
       return RestResult.succeed(orgEventService.findTypeMap());
    }

    @GetMapping("/getStatisticsOrgEvent")
    @ApiOperation(value = "获取数据统计组织生活综合表")
    public RestResult getStatisticsOrgEvent(OrgEvent orgEvent) {
        return RestResult.succeed(orgEventService.getStatisticsOrgEvent(orgEvent));
    }

    @GetMapping("/getFileOrgEvent")
    public RestResult getFileOrgEvent(OrgEvent orgEvent, Page page) {
        return RestResult.succeed(orgEventService.getFileOrgEvent(orgEvent, page));
    }

    @GetMapping("/getParticipationRateMonthly")
    @ApiModelProperty("按月获取参训率")
    public RestResult getParticipationRateMonthly(@RequestParam("orgId") String orgId) {
        return RestResult.succeed(orgEventService.getParticipationRateMonthly(orgId));
    }

    /**
     * 两会一课参与次数和组织次数
     * @return
     */
    @GetMapping("/getOrgEventCensus")
    public RestResult getOrgEventCensus(String userId) {
        return RestResult.succeed(orgEventService.getOrgEventCensus(userId));
    }

}

