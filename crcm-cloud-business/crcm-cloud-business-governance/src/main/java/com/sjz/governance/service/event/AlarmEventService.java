package com.sjz.governance.service.event;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.governance.model.dto.event.AlarmEventDTO;
import com.sjz.governance.model.dto.event.IntelligentRecognitionEventDTO;
import com.sjz.governance.model.entity.event.AlarmEvent;
import com.sjz.governance.model.vo.event.*;

import java.util.List;

/**
 * 综合治理_告警事件Service接口
 *
 * @author pengl
 * @date 2023-04-04
 */
public interface AlarmEventService extends IService<AlarmEvent> {

    /**
     * 新增综合治理_告警事件
     *
     * @param dto 综合治理_告警事件
     * @return 结果
     */
    boolean saveAlarmEvent(AlarmEventDTO dto);

    /**
     * 修改综合治理_告警事件
     *
     * @param dto 综合治理_告警事件
     * @return 结果
     */
    boolean updateAlarmEvent(AlarmEventDTO dto);

    /**
     * 删除综合治理_告警事件信息
     *
     * @param id 综合治理_告警事件ID
     * @return 结果
     */
    boolean deleteAlarmEvent(Integer id);

    /**
     * 查询综合治理_告警事件
     *
     * @param id 综合治理_告警事件ID
     * @return 综合治理_告警事件
     */
    AlarmEvent findAlarmEventById(Integer id);

    /**
     * 查询综合治理_告警事件列表
     *
     * @param alarmEvent 综合治理_告警事件
     * @return 综合治理_告警事件集合
     */
    List<AlarmEvent> findAlarmEventList(AlarmEvent alarmEvent);

    /**
     * 分页查询综合治理_告警事件列表
     *
     * @param page 分页参数
     * @param dto  综合治理_告警事件
     * @return 综合治理_告警事件集合
     */
    PageT<AlarmEventVO> findAlarmEventPage(PageT<AlarmEventVO> page, AlarmEventDTO dto);

    /**
     * 分页查询综合治理_告警事件列表_流转人查询
     *
     * @param page 分页参数
     * @param dto  综合治理_告警事件
     * @return 综合治理_告警事件集合
     */
    PageT<AlarmEventVO> findAlarmEventPageByDealPerson(PageT<AlarmEventVO> page, AlarmEventDTO dto);

    /**
     * 分页查询综合治理_告警事件列表_创建人查询
     *
     * @param page 分页参数
     * @param dto  综合治理_告警事件
     * @return 综合治理_告警事件集合
     */
    PageT<AlarmEventVO> pageByCreateBy(PageT<AlarmEventVO> page, AlarmEventDTO dto);

    /**
     * 综合治理_告警事件通过流程节点
     *
     * @param dto
     * @return
     */
    boolean passDealNode(AlarmEventDTO dto);

    /**
     * 综合治理_告警事件退回上一流程节点
     *
     * @param dto
     * @return
     */
    boolean backDealNode(AlarmEventDTO dto);

    /**
     * 告警事件统计分析
     * @param
     * @return
     */
    AlarmEventStatisticAnalysisVO statisticAnalysis();

    /**
     * 告警事件统计分析_月度统计
     * @param year
     * @return
     */
    AlarmEventStatisticAnalysisVO statisticAnalysisByMonth(String year);

    /**
     * 告警事件统计分析_村统计
     * @param villageId
     * @return
     */
    AlarmEventStatisticAnalysisVO statisticAnalysisByVillage(String villageId);

    List<AlarmEventStatisticHome> statisticEventHome();

    AlarmEventPageVo statisticEventPageVo();

    /**
     * 综合治理首页顶部-告警事件总数统计
     *
     * @return 告警事件总数统计
     * @Author GZL
     * @Date 2023/4/9 9:52
     **/
    GovernanceHomeEventTotalVo governanceHomeEventTotal();

    /**
     * 综合治理首页顶部-今日告警事件数量
     *
     * @return 今日告警事件数量
     * @Author GZL
     * @Date 2023/4/9 11:15
     **/
    Integer getTodayEventCount();

    /**
     * 综合治理首页_分页查询
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/4/9 11:38
     * @Param page 分页信息
     **/
    PageT<IntelligentRecognitionEventVo> governanceHomePage(PageT<IntelligentRecognitionEventVo> page);

    /**
     * 智能识别分页查询
     *
     * @return 智能识别数据
     * @Author GZL
     * @Date 2023/4/9 14:46
     * @Param page 分页信息
     * @Param queryDTO 查询条件
     **/
    PageT<IntelligentRecognitionEventVo> intelligentRecognitionPage(PageT<IntelligentRecognitionEventVo> page, IntelligentRecognitionEventDTO queryDTO);

    AlarmEventVO getInfoById(String id);
}
