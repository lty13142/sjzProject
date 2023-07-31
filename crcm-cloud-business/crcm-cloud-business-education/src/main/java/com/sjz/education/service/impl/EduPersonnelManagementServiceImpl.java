package com.sjz.education.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.api.dto.res.RoleDTO;
import com.crcm.admin.api.dto.res.AreaDTO;
import com.crcm.admin.api.dto.res.UserAccountDTO;
import com.crcm.admin.api.dto.res.UserBaseInfoVO;
import com.crcm.admin.api.feign.RemoteAreaService;
import com.crcm.admin.api.feign.RemoteUserService;
import com.crcm.cloud.start.sso.domain.AuthUser;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.exception.CustomException;
import com.crcm.core.response.RestResult;
import com.sjz.education.mapper.EduExchangeRecordMapper;
import com.sjz.education.mapper.EduReportRecordMapper;
import com.sjz.education.model.entity.*;
import com.sjz.education.model.vo.BaseQueryVO;
import com.sjz.education.model.vo.ExchangeRecordVO;
import com.sjz.education.model.vo.RecordVO;
import com.sjz.education.model.vo.ReportRecordVO;
import com.sjz.education.service.EduPersonnelManagementService;
import com.sjz.education.mapper.EduPersonnelManagementMapper;
import com.sjz.education.service.EduPointsRuleService;
import com.sjz.education.service.EduProductsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author SSSCCCC
 * @description 针对表【edu_personnel_management(人员管理)】的数据库操作Service实现
 * @createDate 2023-04-04 15:59:39
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class EduPersonnelManagementServiceImpl extends ServiceImpl<EduPersonnelManagementMapper, EduPersonnelManagement>
        implements EduPersonnelManagementService {

    @Autowired
    private EduReportRecordMapper reportRecordMapper;

    @Autowired
    private EduExchangeRecordMapper exchangeRecordMapper;

    @Autowired
    private EduPointsRuleService pointsRuleService;
    @Autowired
    private EduProductsService productsService;

    @Autowired
    private RemoteUserService remoteUserService;
    @Autowired
    private RemoteAreaService remoteAreaService;

    /**
     * 新增用户
     *
     * @param t
     * @return
     */
    @Override
    public boolean add(EduPersonnelManagement t) {
        return this.save(t);
    }

    /**
     * 编辑用户
     *
     * @param t
     * @return
     */
    @Override
    public boolean edit(EduPersonnelManagement t) {
        if (StringUtils.isNotBlank(t.getVillageCode())) {
            AreaDTO village = remoteAreaService.getInfoByCode(t.getVillageCode(), ServiceNameConstants.EDUCATION_SERVICE).getData();
            AreaDTO area = remoteAreaService.getInfoByCode(village.getPcode(), ServiceNameConstants.EDUCATION_SERVICE).getData();
            t.setAreaCode(area.getCode());
            t.setTownCode(area.getPcode());
        }
        this.updateById(t);
        UserBaseInfoVO userDataVO = new UserBaseInfoVO();
        EduPersonnelManagement management = this.baseMapper.selectById(t.getId());
        BeanUtil.copyProperties(management, userDataVO);
        userDataVO.setUsername(management.getUserName());
        RestResult<Object> update = remoteUserService.editUserInfo(userDataVO, ServiceNameConstants.EDUCATION_SERVICE);
        if (!update.getCode().equals(HttpResponseStatus.OK.code())) {
            throw new CustomException(update.getMessage());
        }
        log.info(update.toString());
        return true;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(String id) {
        return this.removeById(id);
    }

    /**
     * 获取登录用户详细信息
     * 根据sys_user表数据进行复制，如果没登陆过，则先新增
     *
     * @return
     */
    @Override
    public EduPersonnelManagement findByUser() {
        AuthUser user = SecurityUtil.getCurrentUser();
        EduPersonnelManagement personnelManagement = this.baseMapper.selectById(user.getUserId());

        UserAccountDTO userAccountDTO = remoteUserService.getByPhoneNumber(user.getPhone(), ServiceNameConstants.EDUCATION_SERVICE).getData();
        String roles = null;
        if (Objects.nonNull(userAccountDTO)){
            roles = StringUtils.strip(userAccountDTO.getRoles().stream().map(RoleDTO::getName).collect(Collectors.toList()).toString(), "[]");
            roles = roles.replaceAll(" ", "");
        }
        UserBaseInfoVO baseInfo = remoteUserService.getUserBaseInfo(user.getUserId(), ServiceNameConstants.EDUCATION_SERVICE).getData();
        AreaDTO village = new AreaDTO();
        AreaDTO area = new AreaDTO();
        AreaDTO town = new AreaDTO();
        if (ObjectUtil.isNotEmpty(baseInfo.getVillageCode())) {
            village = remoteAreaService.getInfoByCode(baseInfo.getVillageCode(), ServiceNameConstants.EDUCATION_SERVICE).getData();
        }
        if (ObjectUtil.isNotEmpty(baseInfo.getAreaCode())) {
            area = remoteAreaService.getInfoByCode(baseInfo.getAreaCode(), ServiceNameConstants.EDUCATION_SERVICE).getData();
        }
        if (ObjectUtil.isNotEmpty(baseInfo.getTownCode())) {
            town = remoteAreaService.getInfoByCode(baseInfo.getTownCode(), ServiceNameConstants.EDUCATION_SERVICE).getData();
        }
        if (ObjectUtil.isEmpty(personnelManagement)) {
            EduPersonnelManagement management = new EduPersonnelManagement();
            BeanUtil.copyProperties(user, management);
            BeanUtil.copyProperties(userAccountDTO, management);
            BeanUtil.copyProperties(baseInfo, management);
            management.setId(user.getUserId());
            management.setUserName(user.getUsername());
            management.setPoints(0);
            management.setRoles(roles);
            management.setVillageName(village.getName());
            management.setAreaName(area.getName());
            management.setTownName(town.getName());
            this.save(management);
        } else {
            //实时同步admin中的用户信息
            BeanUtil.copyProperties(user, personnelManagement);
            BeanUtil.copyProperties(userAccountDTO, personnelManagement);
            BeanUtil.copyProperties(baseInfo, personnelManagement);
            personnelManagement.setUserName(user.getUsername());
            personnelManagement.setRoles(roles);
            personnelManagement.setVillageName(village.getName());
            personnelManagement.setAreaName(area.getName());
            personnelManagement.setTownName(town.getName());
            this.updateById(personnelManagement);
        }
        personnelManagement = this.baseMapper.selectById(user.getUserId());
        personnelManagement.setRoleLists(userAccountDTO.getRoles());
        return personnelManagement;
    }

    /**
     * 获取列表
     *
     * @return
     */
    @Override
    public List<EduPersonnelManagement> getList() {
        return this.baseMapper.selectList(null);
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @Override
    public EduPersonnelManagement findById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 根据ids查询用户
     *
     * @param ids
     * @return
     */
    @Override
    public List<EduPersonnelManagement> findByIds(List<String> ids) {
        LambdaQueryWrapper<EduPersonnelManagement> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(EduPersonnelManagement::getId, ids);
        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 根据用户名查询用户
     *
     * @param userNames
     * @return
     */
    @Override
    public List<EduPersonnelManagement> findByUserNames(List<String> userNames) {
        LambdaQueryWrapper<EduPersonnelManagement> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(EduPersonnelManagement::getUserName, userNames);
        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 所有用户积分排名
     *
     * @return
     */
    @Override
    public List<EduPersonnelManagement> ranking() {
        LambdaQueryWrapper<EduPersonnelManagement> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(EduPersonnelManagement::getPoints);
        List<EduPersonnelManagement> personnel = this.baseMapper.selectList(wrapper);
        int count = 1;
//        List<EduPersonnelManagement> rank = new ArrayList<>();
        for (EduPersonnelManagement management : personnel) {
            management.setRanking(count++);
            //角色包含村民的才参与排行
//            String managementRoles = management.getRoles();
//            if (StringUtils.isNotBlank(managementRoles)) {
//                List<String> roles = Arrays.asList(managementRoles.split(","));
//                if (roles.contains("村民")) {
//                    management.setRanking(count++);
//                    rank.add(management);
//                }
//            }
        }
        return personnel;
    }


    /**
     * 按村隔离进行用户积分排名
     *
     * @return
     */
    @Override
    public List<EduPersonnelManagement> rankingByVillage() {
        //获取当前登录用户的村
        AuthUser user = SecurityUtil.getCurrentUser();
        EduPersonnelManagement currentUser = this.baseMapper.selectById(user.getUserId());

        LambdaQueryWrapper<EduPersonnelManagement> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduPersonnelManagement::getVillageName,currentUser.getVillageName());
        wrapper.orderByDesc(EduPersonnelManagement::getPoints);
        List<EduPersonnelManagement> personnel = this.baseMapper.selectList(wrapper);
        int count = 1;
//        List<EduPersonnelManagement> rank = new ArrayList<>();
        for (EduPersonnelManagement management : personnel) {
            management.setRanking(count++);
            //角色包含村民的才参与排行
//            String managementRoles = management.getRoles();
//            if (StringUtils.isNotBlank(managementRoles)) {
//                List<String> roles = Arrays.asList(managementRoles.split(","));
//                if (roles.contains("村民")) {
//                    management.setRanking(count++);
//                    rank.add(management);
//                }
//            }
        }
        return personnel;
    }

    /**
     * 登录用户个人积分排名
     *
     * @return
     */
    @Override
    public EduPersonnelManagement myRanking() {
        AuthUser user = SecurityUtil.getCurrentUser();
        List<EduPersonnelManagement> ranking = this.rankingByVillage();
        List<EduPersonnelManagement> collect = ranking.stream().filter(n -> n.getId().equals(user.getUserId())).collect(Collectors.toList());
        return collect.get(0);
    }

    /**
     * 我的积分获取记录（包括获得总积分，消费总积分，获得积分的详细流水，消费积分流水）
     *
     * @return
     */
    @Override
    public RecordVO myRecord(BaseQueryVO t) {
        //此处前端传值格式为“2023-01”
        if (ObjectUtil.isNotEmpty(t.getStartTime())) {
            String startTime = t.getStartTime();
            String[] split = startTime.split("-");
            t.setStartTime(startTime + "-01");
            LocalDate currDate = LocalDate.of(Integer.valueOf(split[0]), Integer.valueOf(split[1]), 1);
            // 当月最后一天
            LocalDate lastDayOfMonth = currDate.with(TemporalAdjusters.lastDayOfMonth());
            t.setEndTime(lastDayOfMonth.toString());
        }
        AuthUser user = SecurityUtil.getCurrentUser();
        RecordVO recordVO = new RecordVO();
        recordVO.setUserId(user.getUserId());
        recordVO.setUserName(user.getUsername());
        recordVO.setEarnPoints(0);
        recordVO.setConsumePoints(0);
        //上报记录查询
        LambdaQueryWrapper<EduReportRecord> reportWrapper = new LambdaQueryWrapper<>();
        reportWrapper.eq(EduReportRecord::getUserId, user.getUserId());
        reportWrapper.eq(EduReportRecord::getStatus, 1);
        reportWrapper.ge(ObjectUtil.isNotEmpty(t.getStartTime()), EduReportRecord::getUpdateTime, t.getStartTime());
        reportWrapper.le(ObjectUtil.isNotEmpty(t.getStartTime()), EduReportRecord::getUpdateTime, t.getEndTime());
        reportWrapper.orderByDesc(EduReportRecord::getUpdateTime);
        List<EduReportRecord> records = reportRecordMapper.selectList(reportWrapper);
        if (ObjectUtil.isNotEmpty(records)) {
            List<String> ruleIds = records.stream().map(EduReportRecord::getRuleId).distinct().collect(Collectors.toList());
            List<EduPointsRule> rules = pointsRuleService.findByIds(ruleIds);
            List<String> pids = rules.stream().map(EduPointsRule::getPid).distinct().collect(Collectors.toList());
            List<EduPointsRule> byPids = pointsRuleService.findByIds(pids);

            List<ReportRecordVO> reportRecordVOS = new ArrayList<>();
            for (EduReportRecord record : records) {
                ReportRecordVO reportRecordVO = new ReportRecordVO();
                reportRecordVO.setRecordId(record.getId());
                BeanUtil.copyProperties(record, reportRecordVO);
                recordVO.setEarnPoints(recordVO.getEarnPoints() + record.getEarnPoints());
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
            recordVO.setEarnPointsList(reportRecordVOS);
        }

        //兑换记录查询
        LambdaQueryWrapper<EduExchangeRecord> exchangeWrapper = new LambdaQueryWrapper<>();
        exchangeWrapper.eq(EduExchangeRecord::getUserId, user.getUserId());
        exchangeWrapper.ge(ObjectUtil.isNotEmpty(t.getStartTime()), EduExchangeRecord::getCreateTime, t.getStartTime());
        exchangeWrapper.le(ObjectUtil.isNotEmpty(t.getStartTime()), EduExchangeRecord::getCreateTime, t.getEndTime());

        exchangeWrapper.orderByDesc(EduExchangeRecord::getUpdateTime);
        List<EduExchangeRecord> records1 = exchangeRecordMapper.selectList(exchangeWrapper);
        if (ObjectUtil.isNotEmpty(records1)) {
            List<String> productIds = records1.stream().map(EduExchangeRecord::getProductsId).distinct().collect(Collectors.toList());
            List<EduProducts> products = productsService.findByIds(productIds);
            List<ExchangeRecordVO> exchangeRecordVOS = new ArrayList<>();
            for (EduExchangeRecord record : records1) {
                ExchangeRecordVO exchangeRecordVO = new ExchangeRecordVO();
                exchangeRecordVO.setRecordId(record.getId());
                BeanUtil.copyProperties(record, exchangeRecordVO);
                recordVO.setConsumePoints(recordVO.getConsumePoints() + record.getTotalPoint());
                List<EduProducts> list = products.stream().filter(n -> n.getId().equals(record.getProductsId())).collect(Collectors.toList());
                BeanUtil.copyProperties(list.get(0), exchangeRecordVO);
                exchangeRecordVOS.add(exchangeRecordVO);
            }
            recordVO.setConsumePointsList(exchangeRecordVOS);
        }
        return recordVO;
    }
}




