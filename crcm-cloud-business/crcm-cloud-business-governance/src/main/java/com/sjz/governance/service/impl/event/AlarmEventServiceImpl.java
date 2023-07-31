package com.sjz.governance.service.impl.event;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;
import com.crcm.cloud.start.sso.domain.AuthUser;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.DicConstant;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.dto.DictCacheDTO;
import com.crcm.core.exception.Assert;
import com.crcm.core.exception.CustomException;
import com.crcm.core.response.RestResult;
import com.crcm.core.utils.UtilDataFormat;
import com.crcm.core.vo.echarts.EChartsIntegerPie;
import com.crcm.file.api.feign.RemoteFileService;
import com.sjz.governance.mapper.event.AlarmEventMapper;
import com.sjz.governance.model.dto.event.AlarmEventDTO;
import com.sjz.governance.model.dto.event.IntelligentRecognitionEventDTO;
import com.sjz.governance.model.entity.event.AlarmEvent;
import com.sjz.governance.model.entity.event.AlarmEventFlowNode;
import com.sjz.governance.model.vo.AreaVo;
import com.sjz.governance.model.vo.event.*;
import com.sjz.governance.service.event.AlarmEventFlowNodeService;
import com.sjz.governance.service.event.AlarmEventService;
import com.sjz.governance.utils.UtilDic;
import com.sjz.governance.utils.UtilSysArea;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 综合治理_告警事件Service业务层处理
 *
 * @author pengl
 * @date 2023-04-04
 */
@Service
@Transactional
public class AlarmEventServiceImpl extends ServiceImpl<AlarmEventMapper, AlarmEvent> implements AlarmEventService {

    @Resource
    private AlarmEventFlowNodeService alarmEventFlowNodeService;

    @Resource
    private RemoteFileService remoteFileService;

    /**
     * 新增综合治理_告警事件
     *
     * @param dto 综合治理_告警事件
     * @return 结果
     */
    @Override
    public boolean saveAlarmEvent(AlarmEventDTO dto) {
        //自动初始化字段（编号,流转节点）
        autoInitField(dto);

        //新增事件
        this.save(dto);

        //新增流程节点
        dto.setNextDealNode(dto.getDealNode());
        return saveAlarmEventFlowNode(dto);

    }

    /**
     * 新增流程节点
     *
     * @param dto
     * @return
     */
    private boolean saveAlarmEventFlowNode(AlarmEventDTO dto) {
        AlarmEventFlowNode node = new AlarmEventFlowNode();
        node.setEventId(dto.getId());
        String nextDealNode = dto.getNextDealNode();

        LambdaQueryWrapper<AlarmEventFlowNode> wrapper = Wrappers.<AlarmEventFlowNode>lambdaQuery();
        switch (nextDealNode) {
            case DicConstant.EVENT_DEAL_NODE.EVENT_DEAL_NODE_1:
                //镇指派
                node.setDealStatus(DicConstant.NODE_DEAL_STATUS.UNDEAL);
                node.setDealPerson(dto.getDealPerson());
                node.setDealPersonName(dto.getDealPersonName());
                break;
            case DicConstant.EVENT_DEAL_NODE.EVENT_DEAL_NODE_2:
                //管区指派
                node.setDealStatus(DicConstant.NODE_DEAL_STATUS.UNDEAL);
                node.setDealPerson(dto.getDealPerson());
                node.setDealPersonName(dto.getDealPersonName());
                break;
            case DicConstant.EVENT_DEAL_NODE.EVENT_DEAL_NODE_3:
                //接收
                node.setDealStatus(DicConstant.NODE_DEAL_STATUS.UNDEAL);
                node.setDealPerson(dto.getDealPerson());
                node.setDealPersonName(dto.getDealPersonName());
                break;
            case DicConstant.EVENT_DEAL_NODE.EVENT_DEAL_NODE_4:
                //处理
                AuthUser currentUser1 = SecurityUtil.getCurrentUser();
                if (ObjectUtils.isNull(currentUser1)){
                    throw new CustomException("当前用户未登录");
                }
                node.setDealPerson(currentUser1.getUserId());
                node.setDealPersonName(currentUser1.getNickName());
                node.setDealStatus(DicConstant.NODE_DEAL_STATUS.UNDEAL);
                break;
            case DicConstant.EVENT_DEAL_NODE.EVENT_DEAL_NODE_5:
                //反馈
                wrapper.clear();
                wrapper.eq(AlarmEventFlowNode::getEventId, dto.getId())
                        .orderByAsc(BaseEntity::getCreateTime)
                        .last("limit 1");
                AlarmEventFlowNode one_1 = alarmEventFlowNodeService.getOne(wrapper);
                node.setDealPerson(one_1.getDealPerson());
                node.setDealPersonName(one_1.getDealPersonName());
                node.setDealStatus(DicConstant.NODE_DEAL_STATUS.UNDEAL);
                break;
            default:
                //末尾节点
                AuthUser currentUser = SecurityUtil.getCurrentUser();
                if (ObjectUtils.isNull(currentUser)){
                    throw new CustomException("当前用户未登录");
                }
                node.setDealStatus(DicConstant.NODE_DEAL_STATUS.PASS);
                node.setDealPerson(currentUser.getUserId());
                node.setDealPersonName(currentUser.getNickName());
        }
        node.setDealNode(dto.getNextDealNode());
        return alarmEventFlowNodeService.save(node);
    }

    /**
     * 自动初始化字段（编号，流程流转节点）
     *
     * @param alarmEvent
     */
    private void autoInitField(AlarmEvent alarmEvent) {
        //例202304030002
        String eventNum = "";

        String now = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        LambdaQueryWrapper<AlarmEvent> wrapper = Wrappers.<AlarmEvent>lambdaQuery();

        wrapper.likeRight(AlarmEvent::getEventNumber, now)
                .orderByDesc(AlarmEvent::getEventNumber)
                .last("limit 1");

        AlarmEvent one = this.getOne(wrapper);

        if (one == null) {
            eventNum = now + "0001";
        } else {
            String eventNumber = one.getEventNumber();
            if (eventNumber == null) {
                eventNum = now + "0001";
            } else {
                eventNum = (Long.parseLong(eventNumber) + 1) + "";
            }
        }
        alarmEvent.setEventNumber(eventNum);

        alarmEvent.setDealNode(DicConstant.EVENT_DEAL_NODE.EVENT_DEAL_NODE_1);

        alarmEvent.setAlarmTime(alarmEvent.getAlarmTime() == null ? LocalDateTime.now() : alarmEvent.getAlarmTime());
    }

    /**
     * 修改综合治理_告警事件
     *
     * @param dto 综合治理_告警事件
     * @return 结果
     */
    @Override
    public boolean updateAlarmEvent(AlarmEventDTO dto) {
        //校验
        Assert.bool(!DicConstant.EVENT_DEAL_NODE.EVENT_DEAL_NODE_1.equals(dto.getDealNode()),"事件进行中，不能修改！");
        //删除节点
        alarmEventFlowNodeService.deleteByAlarmEventId(dto.getId());

        //新增流程节点
        dto.setNextDealNode(dto.getDealNode());
        saveAlarmEventFlowNode(dto);

        //修改事件
        return this.updateById(dto);
    }

    /**
     * 删除综合治理_告警事件信息
     *
     * @param id 综合治理_告警事件ID
     * @return 结果
     */
    @Override
    public boolean deleteAlarmEvent(Integer id) {
        alarmEventFlowNodeService.deleteByAlarmEventId(id);
        return this.removeById(id);
    }

    /**
     * 查询综合治理_告警事件
     *
     * @param id 综合治理_告警事件ID
     * @return 综合治理_告警事件
     */
    @Override
    public AlarmEvent findAlarmEventById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询综合治理_告警事件列表
     *
     * @param alarmEvent 综合治理_告警事件
     * @return 综合治理_告警事件
     */
    @Override
    public List<AlarmEvent> findAlarmEventList(AlarmEvent alarmEvent) {
        LambdaQueryWrapper<AlarmEvent> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询综合治理_告警事件
     *
     * @param page 分页参数
     * @param dto  综合治理_告警事件
     * @return 综合治理_告警事件
     */
    @Override
    public PageT<AlarmEventVO> findAlarmEventPage(PageT<AlarmEventVO> page, AlarmEventDTO dto) {
        //去掉权限
//        String currentUserId = SecurityUtil.getCurrentUserId();
//        if (StringUtils.isNotBlank(currentUserId)) {
//            dto.setDealPerson(currentUserId);
//        }
        return this.baseMapper.findAlarmEventPage(page, dto);
    }

    /**
     * 分页查询综合治理_告警事件_流转人
     *
     * @param page 分页参数
     * @param dto  综合治理_告警事件
     * @return 综合治理_告警事件
     */
    @Override
    public PageT<AlarmEventVO> findAlarmEventPageByDealPerson(PageT<AlarmEventVO> page, AlarmEventDTO dto) {
        String currentUserId = SecurityUtil.getCurrentUserId();
        if (StringUtils.isNotBlank(currentUserId)) {
            dto.setDealPerson(currentUserId);
        }
        return this.baseMapper.findAlarmEventPageByDealPerson(page, dto);
    }

    /**
     * 分页查询综合治理_告警事件_创建人
     *
     * @param page 分页参数
     * @param dto  综合治理_告警事件
     * @return 综合治理_告警事件
     */
    @Override
    public PageT<AlarmEventVO> pageByCreateBy(PageT<AlarmEventVO> page, AlarmEventDTO dto) {
        String currentUserName = SecurityUtil.getCurrentUsername();
        if (StringUtils.isNotBlank(currentUserName)) {
            dto.setCreateBy(currentUserName);
        }
        return this.baseMapper.pageByCreateBy(page, dto);
    }

    /**
     * 通过流程节点
     *
     * @param dto
     * @return 结果
     */
    @Override
    public boolean passDealNode(AlarmEventDTO dto) {

        //获取当前流转节点
        AlarmEventFlowNode currentNode = getCurrentDealNode(dto);
        if (ObjectUtils.isNull(currentNode)) {
            return false;
        }

        //获取下一流转节点
        dto.setNextDealNode(getNextNodePlus(currentNode));

        //更新事件中的节点
        updateAlarmEventDealNode(dto);

        //修改当前流程节点
        dto.setCurrentDealNodeId(currentNode.getId());
        updateAlarmEventFlowNode(dto, DicConstant.NODE_DEAL_STATUS.PASS);

        //新增流程节点
        return saveAlarmEventFlowNode(dto);
    }

    /**
     * 退回流程节点
     *
     * @param dto
     * @return 结果
     */
    @Override
    public boolean backDealNode(AlarmEventDTO dto) {

        //获取当前流转节点
        AlarmEventFlowNode currentNode = getCurrentDealNode(dto);
        if (ObjectUtils.isNull(currentNode)) {
            return false;
        }

//        //获取下一流转节点
//        dto.setNextDealNode(getNextNodeMinus(currentNode));
        //退回到初始节点
        LambdaQueryWrapper<AlarmEventFlowNode> wrapper = Wrappers.<AlarmEventFlowNode>lambdaQuery();
        wrapper.eq(AlarmEventFlowNode::getEventId, dto.getId())
                .orderByAsc(BaseEntity::getCreateTime)
                .last("limit 1");
        AlarmEventFlowNode one = alarmEventFlowNodeService.getOne(wrapper);
        dto.setNextDealNode(DicConstant.EVENT_DEAL_NODE.EVENT_DEAL_NODE_1);
        dto.setDealPerson(one.getDealPerson());
        dto.setDealPersonName(one.getDealPersonName());

        //更新事件中的节点
        updateAlarmEventDealNode(dto);

        //修改流程节点状态退回
        dto.setCurrentDealNodeId(currentNode.getId());
        updateAlarmEventFlowNode(dto, DicConstant.NODE_DEAL_STATUS.BACK);

        //新增流程节点
        return saveAlarmEventFlowNode(dto);
    }

    /**
     * 通过到下一流转节点
     *
     * @param currentNode
     * @return
     */
    private String getNextNodePlus(AlarmEventFlowNode currentNode) {
        return (Integer.parseInt(currentNode.getDealNode()) + 1) + "";
    }

    /**
     * 退回到下一流转节点
     *
     * @param currentNode
     * @return
     */
    private String getNextNodeMinus(AlarmEventFlowNode currentNode) {
        return (Integer.parseInt(currentNode.getDealNode()) - 1) + "";
    }


    /**
     * 获取当前流转节点
     *
     * @param dto
     * @return
     */
    private AlarmEventFlowNode getCurrentDealNode(AlarmEventDTO dto) {
        LambdaQueryWrapper<AlarmEventFlowNode> wrapper = Wrappers.<AlarmEventFlowNode>lambdaQuery();
        wrapper.eq(AlarmEventFlowNode::getEventId, dto.getId())
                .orderByDesc(BaseEntity::getCreateTime)
                .last("limit 1");
        return alarmEventFlowNodeService.getOne(wrapper);
    }

    /**
     * 修改流程节点状态
     *
     * @param dto
     * @param nodeDealStatus
     * @return
     */
    private boolean updateAlarmEventFlowNode(AlarmEventDTO dto, String nodeDealStatus) {
        AlarmEventFlowNode currentNode = new AlarmEventFlowNode();
        currentNode.setId(dto.getCurrentDealNodeId());
        currentNode.setDealStatus(nodeDealStatus);
        currentNode.setDealExplain(dto.getDealExplain());
        currentNode.setDealFileUrl(dto.getDealFileUrl());
        return alarmEventFlowNodeService.updateById(currentNode);
    }

    /**
     * 更新事件中的节点
     *
     * @param dto
     * @return
     */
    private boolean updateAlarmEventDealNode(AlarmEventDTO dto) {
        AlarmEvent alarmEvent = new AlarmEvent();
        alarmEvent.setId(dto.getId());
        alarmEvent.setDealNode(dto.getNextDealNode());
        return this.updateById(alarmEvent);
    }

    /**
     * 告警事件统计分析
     *
     * @param
     * @return
     */
    @Override
    public AlarmEventStatisticAnalysisVO statisticAnalysis() {
        AlarmEventStatisticAnalysisVO statisticAnalysisVO = new AlarmEventStatisticAnalysisVO();
        //基础数据
        LambdaQueryWrapper<AlarmEvent> wrapper = Wrappers.<AlarmEvent>lambdaQuery();
        wrapper.eq(AlarmEvent::getIsMisinformation, DicConstant.IS_MISINFORMATION.NO);
        List<AlarmEvent> alarmEvents = this.baseMapper.selectList(wrapper);
        //时间： 总计， 今日， 上周， 上月
        setByTimeVO(statisticAnalysisVO, alarmEvents);
        //类型：字典
        setByTypeVO(statisticAnalysisVO, alarmEvents);
        //处理节点
        setByNodeVO(statisticAnalysisVO, alarmEvents);

        return statisticAnalysisVO;
    }

    @Override
    public AlarmEventStatisticAnalysisVO statisticAnalysisByMonth(String year) {
        if (StringUtils.isBlank(year)){
            year = LocalDate.now().getYear() + "";
        }
        AlarmEventStatisticAnalysisVO statisticAnalysisVO = new AlarmEventStatisticAnalysisVO();
        //基础数据
        LambdaQueryWrapper<AlarmEvent> wrapper = Wrappers.<AlarmEvent>lambdaQuery();
        wrapper.eq(AlarmEvent::getIsMisinformation, DicConstant.IS_MISINFORMATION.NO)
                .likeRight(BaseEntity::getCreateTime,year);
        List<AlarmEvent> alarmEvents = this.baseMapper.selectList(wrapper);
        //月度统计类型
        setByMonthVO(statisticAnalysisVO, alarmEvents);
        return statisticAnalysisVO;
    }

    @Override
    public AlarmEventStatisticAnalysisVO statisticAnalysisByVillage(String villageId) {
        AlarmEventStatisticAnalysisVO statisticAnalysisVO = new AlarmEventStatisticAnalysisVO();
        //基础数据
        LambdaQueryWrapper<AlarmEvent> wrapper = Wrappers.<AlarmEvent>lambdaQuery();
        wrapper.eq(AlarmEvent::getIsMisinformation, DicConstant.IS_MISINFORMATION.NO)
                .eq(StringUtils.isNotBlank(villageId),AlarmEvent::getVillageId,villageId);
        List<AlarmEvent> alarmEvents = this.baseMapper.selectList(wrapper);
        //村统计类型
        setByTypeVO(statisticAnalysisVO, alarmEvents);
        return statisticAnalysisVO;
    }

    @Override
    public List<AlarmEventStatisticHome> statisticEventHome() {
        List<AlarmEventStatisticHome> alarmEventStatisticHomes = this.baseMapper.statisticEventHome();
        List<AreaVo> areaList = UtilSysArea.getAreaByType(SystemConstant.AREA_TYPE.VILLAGE);
        for (AlarmEventStatisticHome home : alarmEventStatisticHomes) {
            // 所属区列表
            AreaVo area = getArea(areaList, home.getVillageCode());
            home.setVillageName(area.getName());
        }
        return alarmEventStatisticHomes;
    }

    @Override
    public AlarmEventPageVo statisticEventPageVo() {
        AlarmEventPageVo alarmEventPageVo = new AlarmEventPageVo();
        //基础数据
        LambdaQueryWrapper<AlarmEvent> wrapper = Wrappers.<AlarmEvent>lambdaQuery();
        wrapper.eq(AlarmEvent::getIsMisinformation, DicConstant.IS_MISINFORMATION.NO);
        List<AlarmEvent> alarmEvents = this.baseMapper.selectList(wrapper);
        List<AlarmEventStatisticAnalysisByTypeVO> list = setByTypeVO2(alarmEvents);
        alarmEventPageVo.setAllNum(alarmEvents.size());
        alarmEventPageVo.setDataList(list);
        return alarmEventPageVo;
    }

    @Override
    public GovernanceHomeEventTotalVo governanceHomeEventTotal() {
        GovernanceHomeEventTotalVo totalVo = new GovernanceHomeEventTotalVo();
        List<EChartsIntegerPie> dataList = this.baseMapper.getEventCountByStatus();
        totalVo.setTotal(UtilDataFormat.getListSum(dataList, EChartsIntegerPie::getValueData));
        totalVo.setHandelCount(UtilDataFormat.getListSum(dataList, data -> Objects.equals(DicConstant.EVENT_DEAL_NODE.EVENT_DEAL_NODE_6, data.getTypeCode())
                , EChartsIntegerPie::getValueData));
        totalVo.setNoHandelCount(totalVo.getTotal() - totalVo.getHandelCount());
        return totalVo;
    }

    @Override
    public Integer getTodayEventCount() {
        LambdaQueryWrapper<AlarmEvent> wrapper = Wrappers.lambdaQuery();
        wrapper.ge(AlarmEvent::getCreateTime, LocalDate.now());
        return this.baseMapper.selectCount(wrapper);
    }

    @Override
    public PageT<IntelligentRecognitionEventVo> governanceHomePage(PageT<IntelligentRecognitionEventVo> page) {
        PageT<IntelligentRecognitionEventVo> pageInfo = this.baseMapper.governanceHomePage(page);
        return setEventRealFileUrl(pageInfo);
    }

    @Override
    public PageT<IntelligentRecognitionEventVo> intelligentRecognitionPage(PageT<IntelligentRecognitionEventVo> page, IntelligentRecognitionEventDTO queryDTO) {
        PageT<IntelligentRecognitionEventVo> pageInfo = this.baseMapper.intelligentRecognitionPage(page, queryDTO);
        return setEventRealFileUrl(pageInfo);
    }

    @Override
    public AlarmEventVO getInfoById(String id) {
        return this.baseMapper.getInfoById(id);
    }

    /**
     * 智能识别图片路径设置
     *
     * @return 新数据
     * @Author GZL
     * @Date 2023/4/9 15:00
     * @Param pageInfo 原数据
     **/
    private PageT<IntelligentRecognitionEventVo> setEventRealFileUrl(PageT<IntelligentRecognitionEventVo> pageInfo) {
        if (CollectionUtils.isEmpty(pageInfo.getRecords())) {
            return pageInfo;
        }
        pageInfo.getRecords().forEach(data -> {
            if (StringUtils.isBlank(data.getEventUrl())) {
                return;
            }
            RestResult<String> filePath = remoteFileService.getFilePath(data.getEventUrl(), AuthConstants.FROM_IN);
            data.setEventUrl(filePath.isSuccess() ? filePath.getData() : "");
        });
        return pageInfo;
    }

    public AreaVo getArea(List<AreaVo> areaList, String Code) {
        for (AreaVo vo : areaList) {
            if (Code.equals(vo.getCode())) {
                return vo;
            }
        }
        return null;
    }

    /**
     * 根据事件流转节点统计事件
     *
     * @param statisticAnalysisVO
     * @param alarmEvents
     */
    private void setByMonthVO(AlarmEventStatisticAnalysisVO statisticAnalysisVO, List<AlarmEvent> alarmEvents) {
        List<DictCacheDTO> typeList = UtilDic.getDicByCode(DicConstant.CAMERA_CAPTURE_TYPE.CODE);
        //根据事件类型和月份区分
        Map<String, Map<String, Long>> collect = alarmEvents.stream()
                .map(e -> {
                    AlarmEventVO alarmEventVO = new AlarmEventVO();
                    BeanUtils.copyProperties(e, alarmEventVO);
                    alarmEventVO.setMonth(alarmEventVO.getCreateTime().getMonth().getValue() + "");
                    return alarmEventVO;
                })
                .collect(Collectors.groupingBy(AlarmEventVO::getEventType, Collectors.groupingBy(AlarmEventVO::getMonth, Collectors.counting())));

        List<AlarmEventStatisticAnalysisByTypeMonthVO> typeVOList = new ArrayList<>();
        for (DictCacheDTO dictCacheDTO : typeList) {
            String value = dictCacheDTO.getValue();
            AlarmEventStatisticAnalysisByTypeMonthVO typeVO = new AlarmEventStatisticAnalysisByTypeMonthVO();

            Map<String, Long> typeAndEventMap = collect.get(value);
            typeVO.setEventType(value);
            typeVO.setEventTypeCh(dictCacheDTO.getName());
            List<AlarmEventStatisticAnalysisByMonthVO> monthVOList = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                String month = (i + 1) + "";
                AlarmEventStatisticAnalysisByMonthVO monthVO = new AlarmEventStatisticAnalysisByMonthVO();
                monthVO.setMonth(month + "月");
                if (ObjectUtils.isNotNull(typeAndEventMap) && ObjectUtils.isNotNull(typeAndEventMap.get(month))){
                    monthVO.setEventCount(typeAndEventMap.get(month));
                }else {
                    monthVO.setEventCount(0L);
                }
                monthVOList.add(monthVO);
            }
            typeVO.setAlarmEventStatisticAnalysisByMonthVOList(monthVOList);
            typeVOList.add(typeVO);
        }
        typeVOList.sort(Comparator.comparing(AlarmEventStatisticAnalysisByTypeMonthVO::getEventType));
        statisticAnalysisVO.setAlarmEventStatisticAnalysisByTypeMonthVOList(typeVOList);
    }
    /**
     * 根据事件流转节点统计事件
     *
     * @param statisticAnalysisVO
     * @param alarmEvents
     */
    private void setByNodeVO(AlarmEventStatisticAnalysisVO statisticAnalysisVO, List<AlarmEvent> alarmEvents) {
        List<DictCacheDTO> dealNodeList = UtilDic.getDicByCode(DicConstant.EVENT_DEAL_NODE.CODE);
        Map<String, Long> eventCountMap = alarmEvents.stream().collect(Collectors.groupingBy(AlarmEvent::getDealNode, Collectors.counting()));
        int total = alarmEvents.size();

        List<AlarmEventStatisticAnalysisByDealNodeVO> nodeVOArrayList = new ArrayList<>();
        for (DictCacheDTO dictCacheDTO : dealNodeList) {
            String value = dictCacheDTO.getValue();
            AlarmEventStatisticAnalysisByDealNodeVO byNodeVO = new AlarmEventStatisticAnalysisByDealNodeVO();
            Long eventCount = eventCountMap.get(value) == null ? 0L : eventCountMap.get(value);
            byNodeVO.setDealNode(dictCacheDTO.getValue());
            byNodeVO.setDealNodeCh(dictCacheDTO.getName());
            byNodeVO.setEventCount(eventCount);
            byNodeVO.setPercent(getPercent(total + "", eventCount+""));

            nodeVOArrayList.add(byNodeVO);
        }
        nodeVOArrayList.sort(Comparator.comparing(AlarmEventStatisticAnalysisByDealNodeVO::getDealNode));
        statisticAnalysisVO.setAlarmEventStatisticAnalysisByDealNodeVOList(nodeVOArrayList);
    }

    /**
     * 计算百分比
     * @param total
     * @param count
     */
    private String getPercent(String total , String count) {
        String percent = "0";
        if (new BigDecimal(total).compareTo(BigDecimal.ZERO) <=0 ){
            percent = "0.00";
        }else {
            percent = new BigDecimal(count)
                    .divide(new BigDecimal(total), 4, BigDecimal.ROUND_HALF_DOWN)
                    .multiply(new BigDecimal("100"))
                    .setScale(2,BigDecimal.ROUND_HALF_DOWN)
                    .toString();
        }
        return percent;
    }

    /**
     * 根据事件类型统计事件
     *
     * @param statisticAnalysisVO
     * @param alarmEvents
     */
    private void setByTypeVO(AlarmEventStatisticAnalysisVO statisticAnalysisVO, List<AlarmEvent> alarmEvents) {
        List<DictCacheDTO> eventTypeList = UtilDic.getDicByCode(DicConstant.CAMERA_CAPTURE_TYPE.CODE);
        Map<String, Long> eventCountMap = alarmEvents.stream().collect(Collectors.groupingBy(AlarmEvent::getEventType, Collectors.counting()));
        int total = alarmEvents.size();

        List<AlarmEventStatisticAnalysisByTypeVO> typeVOArrayList = new ArrayList<>();
        for (DictCacheDTO dictCacheDTO : eventTypeList) {
            String value = dictCacheDTO.getValue();
            AlarmEventStatisticAnalysisByTypeVO byTypeVO = new AlarmEventStatisticAnalysisByTypeVO();
            Long eventCount = eventCountMap.get(value) == null ? 0L : eventCountMap.get(value);
            byTypeVO.setEventType(dictCacheDTO.getValue());
            byTypeVO.setEventTypeCh(dictCacheDTO.getName());
            byTypeVO.setPercent(getPercent(total + "", eventCount+""));
            byTypeVO.setEventCount(eventCount);
            typeVOArrayList.add(byTypeVO);
        }
        typeVOArrayList.sort(Comparator.comparing(AlarmEventStatisticAnalysisByTypeVO::getEventType));
        statisticAnalysisVO.setAlarmEventStatisticAnalysisByTypeVOList(typeVOArrayList);
    }

    /**
     * 根据事件类型统计事件
     *
     * @param alarmEvents
     */
    private List<AlarmEventStatisticAnalysisByTypeVO> setByTypeVO2(List<AlarmEvent> alarmEvents) {
        List<DictCacheDTO> eventTypeList = UtilDic.getDicByCode(DicConstant.CAMERA_CAPTURE_TYPE.CODE);
        Map<String, Long> eventCountMap = alarmEvents.stream().collect(Collectors.groupingBy(AlarmEvent::getEventType, Collectors.counting()));
        int total = alarmEvents.size();

        List<AlarmEventStatisticAnalysisByTypeVO> typeVOArrayList = new ArrayList<>();
        for (DictCacheDTO dictCacheDTO : eventTypeList) {
            String value = dictCacheDTO.getValue();
            AlarmEventStatisticAnalysisByTypeVO byTypeVO = new AlarmEventStatisticAnalysisByTypeVO();
            Long eventCount = eventCountMap.get(value) == null ? 0L : eventCountMap.get(value);
            byTypeVO.setEventType(dictCacheDTO.getValue());
            byTypeVO.setEventTypeCh(dictCacheDTO.getName());
            byTypeVO.setPercent(getPercent(total + "", eventCount+""));
            byTypeVO.setEventCount(eventCount);
            typeVOArrayList.add(byTypeVO);
        }
        typeVOArrayList.sort(Comparator.comparing(AlarmEventStatisticAnalysisByTypeVO::getEventType));
        return typeVOArrayList;
    }

    /**
     * 根据时间统计事件
     *
     * @param statisticAnalysisVO
     * @param alarmEvents
     */
    private void setByTimeVO(AlarmEventStatisticAnalysisVO statisticAnalysisVO, List<AlarmEvent> alarmEvents) {
        AlarmEventStatisticAnalysisByTimeVO byTimeVO = new AlarmEventStatisticAnalysisByTimeVO();
        //总计
        byTimeVO.setTotalEventCount((long) alarmEvents.size());
        //今日
        LocalDate now = LocalDate.now();
        long todayCount = alarmEvents.stream().filter(e -> now.compareTo(e.getCreateTime().toLocalDate()) <= 0).count();
        byTimeVO.setTodayEventCount(todayCount);
        //上周
        WeekFields weekFields = WeekFields.ISO;
        LocalDate thisMonday = now.with(weekFields.dayOfWeek(), 1L);
        LocalDate lastMonday = thisMonday.minusWeeks(1);
        LocalDate lastSunday = lastMonday.plusDays(6);
        long lastWeekCount = alarmEvents.stream().filter(e -> {
            LocalDate localDate = e.getCreateTime().toLocalDate();
            return (localDate.compareTo(lastMonday) >= 0 && localDate.compareTo(lastSunday) <= 0);
        }).count();
        byTimeVO.setLastWeekEventCount(lastWeekCount);
        //上月
        LocalDate lastMonth = now.minusMonths(1);
        LocalDate lastMonthFirstDay = lastMonth.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastMonthEndDay = lastMonth.with(TemporalAdjusters.lastDayOfMonth());
        long lastMonthCount = alarmEvents.stream().filter(e -> {
            LocalDate localDate = e.getCreateTime().toLocalDate();
            return (localDate.compareTo(lastMonthFirstDay) >= 0 && localDate.compareTo(lastMonthEndDay) <= 0);
        }).count();
        byTimeVO.setLastMontheventCount(lastMonthCount);
        statisticAnalysisVO.setAlarmEventStatisticAnalysisByTimeVO(byTimeVO);
    }
}
