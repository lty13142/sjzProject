package com.sjz.education.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.api.dto.res.AreaDTO;
import com.crcm.admin.api.feign.RemoteAreaService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.sso.domain.AuthUser;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.exception.CustomException;
import com.sjz.education.mapper.EduExchangeRecordMapper;
import com.sjz.education.model.entity.EduExchangeRecord;
import com.sjz.education.model.entity.EduPersonnelManagement;
import com.sjz.education.model.entity.EduPointsRule;
import com.sjz.education.model.entity.EduReportRecord;
import com.sjz.education.model.vo.AreaReportVO;
import com.sjz.education.model.vo.EchartsVO;
import com.sjz.education.model.vo.ReportRecordVO;
import com.sjz.education.service.EduMessageService;
import com.sjz.education.service.EduPersonnelManagementService;
import com.sjz.education.service.EduPointsRuleService;
import com.sjz.education.service.EduReportRecordService;
import com.sjz.education.mapper.EduReportRecordMapper;
import com.sjz.education.utils.UtilPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SSSCCCC
 * @description 针对表【edu_report_record(德育积分_积分上报记录)】的数据库操作Service实现
 * @createDate 2023-04-04 14:36:50
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class EduReportRecordServiceImpl extends ServiceImpl<EduReportRecordMapper, EduReportRecord>
        implements EduReportRecordService {

    @Autowired
    private EduMessageService messageService;
    @Autowired
    private EduPointsRuleService pointsRuleService;
    @Autowired
    private EduPersonnelManagementService managementService;
    @Autowired
    private EduExchangeRecordMapper exchangeRecordMapper;


    @Autowired
    private RemoteAreaService areaService;

    private final static String REPEAT_VERIFY = "该记录已审核！";

    /**
     * 新增上报记录
     *
     * @param t
     * @return
     */
    @Override
    public boolean add(EduReportRecord t) {
        AuthUser user = SecurityUtil.getCurrentUser();
        t.setUserId(user.getUserId());
        t.setUserName(user.getUsername());
        t.setStatus(0);
        //获取当前登录用户的村
        EduPersonnelManagement currentUser = managementService.findById(user.getUserId());
        t.setVillageName(currentUser.getVillageName());
        return this.save(t);
    }

    /**
     * 修改上报记录
     *
     * @param t
     * @return
     */
    @Override
    public boolean edit(EduReportRecord t) {
        return this.updateById(t);
    }

    /**
     * 删除上报记录
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(String id) {
        return this.removeById(id);
    }

    /**
     * 审核通过上报记录
     *
     * @param t
     * @return
     */
    @Override
    public boolean pass(EduReportRecord t) {
        EduReportRecord record = this.baseMapper.selectById(t.getId());
        if (!record.getStatus().equals(0)) {
            throw new CustomException(REPEAT_VERIFY);
        }
        t.setStatus(1);
        boolean update = this.updateById(t);
        if (update) {
            EduPersonnelManagement management = managementService.findById(record.getUserId());
            management.setPoints(management.getPoints() + t.getEarnPoints());
            managementService.edit(management);
        }
        return update;
    }

    /**
     * 审核不通过上报记录
     *
     * @param t
     * @return
     */
    @Override
    public boolean reject(EduReportRecord t) {
        EduReportRecord record = this.baseMapper.selectById(t.getId());
        if (!record.getStatus().equals(0)) {
            throw new CustomException(REPEAT_VERIFY);
        }
        t.setStatus(-1);
        return this.updateById(t);
    }

    /**
     * 登录用户获取上报记录列表
     *
     * @param t
     * @return
     */
    @Override
    public List<ReportRecordVO> getListByUser(EduReportRecord t) {
        AuthUser user = SecurityUtil.getCurrentUser();
        LambdaQueryWrapper<EduReportRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduReportRecord::getUserId, user.getUserId());
        if (ObjectUtil.isNotEmpty(t.getStatus())) {
            wrapper.eq(t.getStatus().equals(0), EduReportRecord::getStatus, 0);
            wrapper.in(t.getStatus().equals(1), EduReportRecord::getStatus, -1, 1);
        }
        wrapper.orderByDesc(EduReportRecord::getCreateTime);
        List<EduReportRecord> records = this.baseMapper.selectList(wrapper);
        if (ObjectUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        List<String> ruleIds = records.stream().map(EduReportRecord::getRuleId).distinct().collect(Collectors.toList());
        List<EduPointsRule> rules = pointsRuleService.findByIds(ruleIds);
        List<String> pids = rules.stream().map(EduPointsRule::getPid).distinct().collect(Collectors.toList());
        List<EduPointsRule> byPids = pointsRuleService.findByIds(pids);

        List<ReportRecordVO> reportRecordVOS = new ArrayList<>();
        for (EduReportRecord record : records) {
            ReportRecordVO reportRecordVO = new ReportRecordVO();
            BeanUtil.copyProperties(record, reportRecordVO);
            reportRecordVO.setRecordId(record.getId());
            List<EduPointsRule> list = rules.stream().filter(n -> n.getId().equals(record.getRuleId())).collect(Collectors.toList());
            if (ObjectUtil.isNotEmpty(list)) {
                BeanUtil.copyProperties(list.get(0), reportRecordVO);
                List<EduPointsRule> collect = byPids.stream().filter(n -> n.getId().equals(list.get(0).getPid())).collect(Collectors.toList());
                if (ObjectUtil.isNotEmpty(collect)) {
                    reportRecordVO.setFatherRuleName(collect.get(0).getRuleName());
                }
            }
            reportRecordVOS.add(reportRecordVO);
        }
        return reportRecordVOS;
    }

    /**
     * 管理员获取上报记录列表
     *
     * @param t
     * @return
     */
    @Override
    public List<EduReportRecord> getList(EduReportRecord t) {
        //获取当前登录用户的村
        AuthUser user = SecurityUtil.getCurrentUser();
        EduPersonnelManagement currentUser = managementService.findById(user.getUserId());

        LambdaQueryWrapper<EduReportRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduReportRecord::getVillageName,currentUser.getVillageName());
        wrapper.like(ObjectUtil.isNotEmpty(t.getUserName()), EduReportRecord::getUserName, t.getUserName());
        wrapper.like(ObjectUtil.isNotEmpty(t.getRuleId()), EduReportRecord::getRuleId, t.getRuleId());
        if (ObjectUtil.isNotEmpty(t.getStatus())) {
            wrapper.eq(t.getStatus().equals(0), EduReportRecord::getStatus, 0);
            wrapper.in(t.getStatus().equals(1), EduReportRecord::getStatus, -1, 1);
        }
        wrapper.orderByDesc(EduReportRecord::getCreateTime);
        List<EduReportRecord> records = this.baseMapper.selectList(wrapper);
        if (ObjectUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        List<String> ruleIds = records.stream().map(EduReportRecord::getRuleId).distinct().collect(Collectors.toList());
        //获取对应积分规则及上级积分规则详情
        List<EduPointsRule> rules = pointsRuleService.findByIds(ruleIds);
        List<String> pids = rules.stream().map(EduPointsRule::getPid).distinct().collect(Collectors.toList());
        if(!ObjectUtil.isEmpty(pids)){
            List<EduPointsRule> byPids = pointsRuleService.findByIds(pids);
            for (EduReportRecord record : records) {
                List<EduPointsRule> list = rules.stream().filter(n -> n.getId().equals(record.getRuleId())).collect(Collectors.toList());
                if (ObjectUtil.isNotEmpty(list)) {
                    record.setPointsRule(list.get(0));
                    List<EduPointsRule> collect = byPids.stream().filter(n -> n.getId().equals(list.get(0).getPid())).collect(Collectors.toList());
                    if (ObjectUtil.isNotEmpty(collect)) {
                        record.setPointsRuleFather(collect.get(0));
                    }
                }
            }
        }
        return records;
    }

    /**
     * 管理员获取上报记录分页
     *
     * @param t
     * @return
     */
    @Override
    public IPage<EduReportRecord> getPage(PageT page, EduReportRecord t) {
        List<EduReportRecord> list = this.getList(t);
        return UtilPage.getPage(page, list);
    }

    /**
     * 按村进行兑换积分数据统计(当年)
     */
    @Override
    public List<EchartsVO> getRecordByVillage() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowYear = now.with(TemporalAdjusters.firstDayOfYear());
        LambdaQueryWrapper<EduExchangeRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(EduExchangeRecord::getCreateTime, nowYear);
        List<EduExchangeRecord> records = exchangeRecordMapper.selectList(wrapper);

        List<EduPersonnelManagement> personnels = managementService.getList();
        for (EduPersonnelManagement personnel : personnels) {
            Integer reduce = records.stream().filter(n -> n.getUserId().equals(personnel.getId()))
                    .map(EduExchangeRecord::getTotalPoint).reduce(0, (res, ele) -> res + ele);
            personnel.setExchangePointsYear(reduce);
        }
        List<AreaDTO> areaDTOS = areaService.findAreaByType("3", ServiceNameConstants.EDUCATION_SERVICE).getData();

        List<String> villages = areaDTOS.stream().filter(n -> StringUtils.isNotBlank(n.getName())).map(AreaDTO::getName).distinct().collect(Collectors.toList());
        List<EchartsVO> echartsVOS = new ArrayList<>();

        for (String village : villages) {
            EchartsVO echartsVO = new EchartsVO();
            echartsVO.setName(village);
            echartsVO.setValue(0);
            Integer total = personnels.stream().filter(n -> StringUtils.isNotBlank(n.getVillageName()) && n.getVillageName().equals(village))
                    .map(EduPersonnelManagement::getExchangePointsYear).reduce(0, (res, ele) -> res + ele);
            if (ObjectUtil.isNotEmpty(total)) {
                echartsVO.setValue(total);
            }
            echartsVOS.add(echartsVO);
        }
        echartsVOS = echartsVOS.stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).collect(Collectors.toList());
        return echartsVOS;
    }

    /**
     * 按村进行数据统计，并返回坐标信息(当年)
     */
    @Override
    public List<AreaReportVO> getRecordPosition() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowYear = now.with(TemporalAdjusters.firstDayOfYear());
        LambdaQueryWrapper<EduReportRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduReportRecord::getStatus, 1);
        wrapper.ge(EduReportRecord::getUpdateTime, nowYear);
        List<EduReportRecord> reportRecords = this.baseMapper.selectList(wrapper);

        LambdaQueryWrapper<EduExchangeRecord> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.ge(EduExchangeRecord::getCreateTime, nowYear);
        List<EduExchangeRecord> exchangeRecords = exchangeRecordMapper.selectList(wrapper1);

        List<EduPersonnelManagement> personnels = managementService.getList();
        for (EduPersonnelManagement personnel : personnels) {
            Integer reduce = reportRecords.stream().filter(n -> n.getUserId().equals(personnel.getId()))
                    .map(EduReportRecord::getEarnPoints).reduce(0, (res, ele) -> res + ele);
            personnel.setEarnPointsYear(reduce);

            Integer reduce1 = exchangeRecords.stream().filter(n -> n.getUserId().equals(personnel.getId()))
                    .map(EduExchangeRecord::getTotalPoint).reduce(0, (res, ele) -> res + ele);
            personnel.setExchangePointsYear(reduce1);
        }
        List<AreaDTO> areaDTOS = areaService.findAreaByType("3", ServiceNameConstants.EDUCATION_SERVICE).getData();

        List<String> villages = areaDTOS.stream().filter(n -> StringUtils.isNotBlank(n.getName())).map(AreaDTO::getName).distinct().collect(Collectors.toList());        List<AreaReportVO> areaReportVOS = new ArrayList<>();

        for (String village : villages) {
            AreaReportVO areaReportVO = new AreaReportVO();
            List<AreaDTO> areaDTO = areaDTOS.stream().filter(n -> n.getName().equals(village)).collect(Collectors.toList());
            BeanUtil.copyProperties(areaDTO.get(0), areaReportVO);
            areaReportVO.setEarnPoints(0);
            areaReportVO.setExchangePoints(0);
            areaReportVO.setNowPoints(0);

            Integer total1 = personnels.stream().filter(n -> StringUtils.isNotBlank(n.getVillageName()) && n.getVillageName().equals(village))
                    .map(EduPersonnelManagement::getEarnPointsYear).reduce(0, (res, ele) -> res + ele);
            if (ObjectUtil.isNotEmpty(total1)) {
                areaReportVO.setEarnPoints(total1);
            }
            Integer total2 = personnels.stream().filter(n -> StringUtils.isNotBlank(n.getVillageName()) && n.getVillageName().equals(village))
                    .map(EduPersonnelManagement::getExchangePointsYear).reduce(0, (res, ele) -> res + ele);
            if (ObjectUtil.isNotEmpty(total2)) {
                areaReportVO.setExchangePoints(total2);
            }
            Integer total3 = personnels.stream().filter(n -> StringUtils.isNotBlank(n.getVillageName()) && n.getVillageName().equals(village))
                    .map(EduPersonnelManagement::getPoints).reduce(0, (res, ele) -> res + ele);
            if (ObjectUtil.isNotEmpty(total3)) {
                areaReportVO.setNowPoints(total3);
            }
            areaReportVOS.add(areaReportVO);
        }
        areaReportVOS = areaReportVOS.stream().sorted((o1, o2) -> o2.getEarnPoints() - o1.getEarnPoints()).collect(Collectors.toList());
        return areaReportVOS;
    }

    /**
     * 获取当年统计的积分获得数据
     *
     * @return
     */
    @Override
    public List<EchartsVO> countByYear() {
        List<EchartsVO> echartsVOS = exchangeRecordMapper.countPointsByYear();
        List<EchartsVO> echartsVOS1 = this.baseMapper.countPointsByYear();
        List<EchartsVO> echartsVOS2 = new ArrayList<>();
        Year now = Year.now();

        for (int i = 1; i <= 12; i++) {
            String s = now.atMonth(i).toString();
            EchartsVO echartsVO = new EchartsVO();
            echartsVO.setName(s);
            echartsVO.setValue(0);
            echartsVO.setValue2(0);
            List<EchartsVO> collect = echartsVOS1.stream().filter(n -> n.getName().equals(s)).collect(Collectors.toList());
            if (ObjectUtil.isNotEmpty(collect)) {
                echartsVO.setValue(collect.get(0).getValue());
            }
            List<EchartsVO> collect1 = echartsVOS.stream().filter(n -> n.getName().equals(s)).collect(Collectors.toList());
            if (ObjectUtil.isNotEmpty(collect1)) {
                echartsVO.setValue2(collect1.get(0).getValue());
            }
            echartsVOS2.add(echartsVO);
        }
        return echartsVOS2;
    }
}




