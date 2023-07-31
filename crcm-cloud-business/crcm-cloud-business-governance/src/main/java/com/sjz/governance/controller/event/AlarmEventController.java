package com.sjz.governance.controller.event;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.governance.model.dto.event.AlarmEventDTO;
import com.sjz.governance.model.dto.event.IntelligentRecognitionEventDTO;
import com.sjz.governance.model.entity.event.AlarmEvent;
import com.sjz.governance.model.vo.event.*;
import com.sjz.governance.service.event.AlarmEventService;
import com.sjz.governance.service.event.AlarmEventSyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 综合治理_告警事件Controller
 *
 * @author pengl
 * @date 2023-04-04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/alarmEvent")
@Api(tags = "综合治理_告警事件")
public class AlarmEventController extends BaseController {


    private final AlarmEventService alarmEventService;

    private final AlarmEventSyncService alarmEventSyncService;

    /**
     * 新增综合治理_告警事件
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增综合治理_告警事件")
    @SysLog(title = "新增综合治理_告警事件", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody AlarmEventDTO dto) {
        alarmEventSyncService.saveAlarmEvent(dto);
        return RestResult.succeed();
    }

    /**
     * 修改综合治理_告警事件
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改综合治理_告警事件")
    @SysLog(title = "修改综合治理_告警事件", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody AlarmEventDTO dto) {
        return RestResult.succeed(alarmEventService.updateAlarmEvent(dto));
    }

    /**
     * 删除综合治理_告警事件
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除综合治理_告警事件")
    @SysLog(title = "删除综合治理_告警事件", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") Integer id) {
        return RestResult.succeed(alarmEventService.deleteAlarmEvent(id));
    }

    /**
     * 分页查询综合治理_告警事件
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询综合治理_告警事件")
    public RestResult<PageT<AlarmEventVO>> getPage(PageT<AlarmEventVO> page, AlarmEventDTO dto) {
        return RestResult.succeed(alarmEventService.findAlarmEventPage(page, dto));
    }

    /**
     * 分页查询综合治理_告警事件_流转人查询
     */
    @GetMapping("/pageByDealPerson")
    @ApiOperation(value = "分页查询综合治理_告警事件_流转人查询")
    public RestResult<PageT<AlarmEventVO>> pageByDealPerson(PageT<AlarmEventVO> page, AlarmEventDTO dto) {
        return RestResult.succeed(alarmEventService.findAlarmEventPageByDealPerson(page, dto));
    }

    /**
     * 分页查询综合治理_告警事件_创建人查询
     */
    @GetMapping("/pageByCreateBy")
    @ApiOperation(value = "分页查询综合治理_告警事件_创建人查询")
    public RestResult<PageT<AlarmEventVO>> pageByCreateBy(PageT<AlarmEventVO> page, AlarmEventDTO dto) {
        return RestResult.succeed(alarmEventService.pageByCreateBy(page, dto));
    }

    /**
     * 查询综合治理_告警事件列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询综合治理_告警事件列表")
    public RestResult<List<AlarmEvent>> getList(AlarmEvent alarmEvent) {
        return RestResult.succeed(alarmEventService.findAlarmEventList(alarmEvent));
    }

    /**
     * 获取综合治理_告警事件详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取综合治理_告警事件详细信息")
    public RestResult<AlarmEvent> getInfo(@PathVariable("id") Integer id) {
        return RestResult.succeed(alarmEventService.findAlarmEventById(id));
    }

    /**
     * 综合治理_告警事件流程节点通过（流程流转节点dealNode: 1.镇派发 2.管区派发 3.待接收 4.待处理 5.待反馈 6.已完成）
     */
    @PostMapping(value = "/passDealNode")
    @ApiOperation(value = " 综合治理_告警事件新增流程节点（流程流转节点dealNode: 1.镇派发 2.管区派发 3.待接收 4.待处理 5.待反馈 6.已完成）")
    @SysLog(title = "综合治理_告警事件流程节点通过", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> passDealNode(@RequestBody AlarmEventDTO dto) {
        return RestResult.succeed(alarmEventService.passDealNode(dto));
    }

    /**
     * 综合治理_告警事件退回上一流程节点（流程流转节点dealNode: 1.镇派发 2.管区派发 3.待接收 4.待处理 5.待反馈 6.已完成）
     */
    @PostMapping(value = "/backDealNode")
    @ApiOperation(value = " 综合治理_告警事件返回上一流程节点（流程流转节点dealNode: 1.镇派发 2.管区派发 3.待接收 4.待处理 5.待反馈 6.已完成）")
    @SysLog(title = "综合治理_告警事件退回上一流程节点", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> backDealNode(@RequestBody AlarmEventDTO dto) {
        return RestResult.succeed(alarmEventService.backDealNode(dto));
    }


    /**
     * 综合治理_告警事件统计分析
     */
    @GetMapping(value = "/statisticAnalysis")
    @ApiOperation(value = "综合治理_告警事件统计分析")
    public RestResult<AlarmEventStatisticAnalysisVO> statisticAnalysis() {
        return RestResult.succeed(alarmEventService.statisticAnalysis());
    }

    /**
     * 综合治理_告警事件统计分析_月度统计
     */
    @GetMapping(value = "/statisticAnalysisByMonth")
    @ApiOperation(value = "综合治理_告警事件统计分析_月度统计")
    public RestResult<AlarmEventStatisticAnalysisVO> statisticAnalysisByMonth(String year) {
        return RestResult.succeed(alarmEventService.statisticAnalysisByMonth(year));
    }

    /**
     * 综合治理_告警事件统计分析_村统计
     */
    @GetMapping(value = "/statisticAnalysisByVillage")
    @ApiOperation(value = "综合治理_告警事件统计分析_村统计")
    public RestResult<AlarmEventStatisticAnalysisVO> statisticAnalysisByVillage(String villageId) {
        return RestResult.succeed(alarmEventService.statisticAnalysisByVillage(villageId));
    }

    /**
     * 大屏_告警事件数量按村名排名
     */
    @GetMapping(value = "/statisticEventHome")
    @ApiOperation(value = "大屏_告警事件数量按村名排名")
    public RestResult<List<AlarmEventStatisticHome>> statisticEventHome() {
        return RestResult.succeed(alarmEventService.statisticEventHome());
    }

    /**
     * 大屏_告警事件统计
     */
    @GetMapping(value = "/statisticEventPageVo")
    @ApiOperation(value = "大屏_告警事件统计")
    public RestResult<AlarmEventPageVo> statisticEventPageVo() {
        return RestResult.succeed(alarmEventService.statisticEventPageVo());
    }

    /**
     * 综合治理首页顶部-告警事件总数统计
     */
    @GetMapping(value = "/governanceHomeEventTotal")
    @ApiOperation(value = "综合治理首页顶部-告警事件总数统计")
    public RestResult<GovernanceHomeEventTotalVo> governanceHomeEventTotal() {
        return RestResult.succeed(alarmEventService.governanceHomeEventTotal());
    }

    /**
     * 综合治理首页顶部-今日告警事件数量
     */
    @GetMapping(value = "/getTodayEventCount")
    @ApiOperation(value = "综合治理首页顶部-今日告警事件数量")
    public RestResult<Integer> getTodayEventCount() {
        return RestResult.succeed(alarmEventService.getTodayEventCount());
    }

    /**
     * 综合治理首页_分页查询
     */
    @GetMapping("/governanceHomePage")
    @ApiOperation(value = "综合治理首页_分页查询")
    public RestResult<PageT<IntelligentRecognitionEventVo>> governanceHomePage(PageT<IntelligentRecognitionEventVo> page) {
        return RestResult.succeed(alarmEventService.governanceHomePage(page));
    }

    /**
     * 智能识别分页查询
     */
    @GetMapping("/intelligentRecognitionPage")
    @ApiOperation(value = "智能识别分页查询")
    public RestResult<PageT<IntelligentRecognitionEventVo>> intelligentRecognitionPage(PageT<IntelligentRecognitionEventVo> page
            , IntelligentRecognitionEventDTO queryDTO) {
        return RestResult.succeed(alarmEventService.intelligentRecognitionPage(page, queryDTO));
    }


    /**
     * 获取综合治理_告警事件详细信息
     */
    @GetMapping(value = "/getInfoById/{id}")
    @ApiOperation(value = "获取综合治理_告警事件详细信息")
    public RestResult<AlarmEvent> getInfoById(@PathVariable("id") String id) {
        return RestResult.succeed(alarmEventService.getInfoById(id));
    }


}
