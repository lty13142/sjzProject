package com.sjz.education.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
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
import com.crcm.core.response.RestResult;
import com.sjz.education.model.dto.EduPointExchangeDTO;
import com.sjz.education.model.entity.*;
import com.sjz.education.model.vo.BaseQueryVO;
import com.sjz.education.model.vo.EchartsVO;
import com.sjz.education.model.vo.ExchangeRecordVO;
import com.sjz.education.model.vo.StatisticsVO;
import com.sjz.education.service.EduExchangeRecordService;
import com.sjz.education.mapper.EduExchangeRecordMapper;
import com.sjz.education.service.EduMessageService;
import com.sjz.education.service.EduPersonnelManagementService;
import com.sjz.education.service.EduProductsService;
import com.sjz.education.utils.UtilDate;
import com.sjz.education.utils.UtilList;
import com.sjz.education.utils.UtilPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author SSSCCCC
 * @description 针对表【edu_exchange_record(德育积分_积分兑换记录)】的数据库操作Service实现
 * @createDate 2023-04-06 10:31:37
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class EduExchangeRecordServiceImpl extends ServiceImpl<EduExchangeRecordMapper, EduExchangeRecord>
        implements EduExchangeRecordService {
    @Autowired
    private EduMessageService messageService;
    @Autowired
    private EduProductsService productsService;
    @Autowired
    private EduPersonnelManagementService managementService;
    @Autowired
    private RemoteAreaService areaService;

    private final static String POINTS_POORLY = "积分不足！";
    private final static String STOCK_POORLY = "库存不足！";
    private final static String REPEAT_WRITE_OFF = "该记录已核销！";

    /**
     * 新增积分兑换记录
     *
     * @param t
     * @return
     */
    @Override
    public String add(EduExchangeRecord t) {
        AuthUser user = SecurityUtil.getCurrentUser();
        EduProducts products = productsService.findById(t.getProductsId());
        EduPersonnelManagement personnelManagement = managementService.getById(user.getUserId());
        Integer myPoints = personnelManagement.getPoints();
        Integer exchangeNumber = t.getExchangeNumber();
        if (exchangeNumber > products.getStock()) {
            throw new CustomException(STOCK_POORLY);
        }
        Integer totalNeedPoints = products.getNeedPoints() * exchangeNumber;
        if (totalNeedPoints > myPoints) {
            throw new CustomException(POINTS_POORLY);
        }
        t.setUserId(user.getUserId());
        t.setUserName(user.getUsername());
        t.setNeedPoint(products.getNeedPoints());
        t.setExchangeCode("EX" + RandomUtil.randomStringUpper(10));
        t.setTotalPoint(totalNeedPoints);
        this.save(t);
        EduPersonnelManagement personnel = new EduPersonnelManagement()
                .setId(user.getUserId())
                .setPoints(myPoints - totalNeedPoints);
        managementService.edit(personnel);
        //兑换数量
        products.setExchangeNumber(products.getExchangeNumber() + exchangeNumber);
        //兑换次数
        products.setExchangeCount(products.getExchangeCount() + 1);

        products.setStock(products.getStock() - exchangeNumber);
        productsService.edit(products);
        return t.getExchangeCode();
    }

    /**
     * 编辑积分兑换记录
     *
     * @param t
     * @return
     */
    @Override
    public boolean edit(EduExchangeRecord t) {
        return this.updateById(t);
    }

    /**
     * 根据id进行删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(String id) {
        return this.removeById(id);
    }

    /**
     * 核销兑换申请
     *
     * @param id
     * @return
     */
    @Override
    public boolean pass(String id) {
        AuthUser user = SecurityUtil.getCurrentUser();
        EduExchangeRecord t = this.baseMapper.selectById(id);
        //核销时间
        t.setUpdateTime(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        //核销人
        t.setUpdateBy(user.getUserId());
        if (StringUtils.isNotBlank(t.getWriteOffCode())) {
            throw new CustomException(REPEAT_WRITE_OFF);
        }
        t.setWriteOffCode(t.getExchangeCode());
        return this.updateById(t);
    }

    /**
     * 批量核销兑换申请
     *
     * @param t
     * @return
     */
    @Override
    public boolean batchPass(BaseQueryVO t) {
        LambdaQueryWrapper<EduExchangeRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(EduExchangeRecord::getId, t.getIds());
        List<EduExchangeRecord> records = this.baseMapper.selectList(wrapper);
        for (EduExchangeRecord record : records) {
            if (StringUtils.isNotBlank(record.getWriteOffCode())) {
                throw new CustomException(REPEAT_WRITE_OFF);
            }
            record.setWriteOffCode("WR" + RandomUtil.randomStringUpper(10));
        }
        return this.updateBatchById(records);
    }

    /**
     * 登录用户获取积分兑换记录列表
     *
     * @param t
     * @return
     */
    @Override
    public List<ExchangeRecordVO> getListByUser(EduExchangeRecord t) {
        AuthUser user = SecurityUtil.getCurrentUser();
        LambdaQueryWrapper<EduExchangeRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduExchangeRecord::getUserId, user.getUserId());
        if (ObjectUtil.isNotEmpty(t.getStatus())) {
            if (t.getStatus().equals(0)) {
                wrapper.isNull(EduExchangeRecord::getWriteOffCode);
            } else {
                wrapper.isNotNull(EduExchangeRecord::getWriteOffCode);
            }
        }
        List<EduExchangeRecord> records = this.baseMapper.selectList(wrapper);
        if (ObjectUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        List<String> productsIds = records.stream().map(EduExchangeRecord::getProductsId).distinct().collect(Collectors.toList());
        List<EduProducts> products = productsService.findByIds(productsIds);
        List<ExchangeRecordVO> recordVOS = new ArrayList<>();
        for (EduExchangeRecord record : records) {
            ExchangeRecordVO recordVO = new ExchangeRecordVO();
            BeanUtil.copyProperties(record, recordVO);
            recordVO.setRecordId(record.getId());
            List<EduProducts> list = products.stream().filter(n -> n.getId().equals(record.getProductsId())).collect(Collectors.toList());
            BeanUtil.copyProperties(list.get(0), recordVO);
            recordVOS.add(recordVO);
        }
        return recordVOS;
    }

    @Override
    public List<ExchangeRecordVO> getPassList() {
        AuthUser user = SecurityUtil.getCurrentUser();
        List<EduExchangeRecord> records = new ArrayList<>();
        EduPersonnelManagement personnelManagement = managementService.getById(user.getUserId());
        if(personnelManagement.getRoles().contains("系统管理员")){
            records = this.baseMapper.selectAllPass();
        } else {
            records = this.baseMapper.selectOwnPass(user.getUserId());
        }
        if (ObjectUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        List<String> productsIds = records.stream().map(EduExchangeRecord::getProductsId).distinct().collect(Collectors.toList());
        List<EduProducts> products = productsService.findByIds(productsIds);
        List<ExchangeRecordVO> recordVOS = new ArrayList<>();
        for (EduExchangeRecord record : records) {
            ExchangeRecordVO recordVO = new ExchangeRecordVO();
            BeanUtil.copyProperties(record, recordVO);
            recordVO.setRecordId(record.getId());
            List<EduProducts> list = products.stream().filter(n -> n.getId().equals(record.getProductsId())).collect(Collectors.toList());
            if(!ObjectUtil.isEmpty(list)){
                BeanUtil.copyProperties(list.get(0), recordVO);
                recordVOS.add(recordVO);
            }
        }
        return recordVOS;
    }

    /**
     * 管理员获取积分兑换记录列表
     *
     * @param t
     * @return
     */
    @Override
    public List<EduExchangeRecord> getList(EduExchangeRecord t) {
        LambdaQueryWrapper<EduExchangeRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ObjectUtil.isNotEmpty(t.getUserName()), EduExchangeRecord::getUserName, t.getUserName());
        wrapper.like(ObjectUtil.isNotEmpty(t.getProductsId()), EduExchangeRecord::getProductsId, t.getProductsId());
        if (ObjectUtil.isNotEmpty(t.getStatus())) {
            if (t.getStatus().equals(0)) {
                wrapper.isNull(EduExchangeRecord::getWriteOffCode);
            } else {
                wrapper.isNotNull(EduExchangeRecord::getWriteOffCode);
            }
        }
        List<EduExchangeRecord> records = this.baseMapper.selectList(wrapper);
        if (ObjectUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        List<String> productsIds = records.stream().map(EduExchangeRecord::getProductsId).distinct().collect(Collectors.toList());
        List<EduProducts> products = productsService.findByIds(productsIds);
        for (EduExchangeRecord record : records) {
            List<EduProducts> list = products.stream().filter(n -> n.getId().equals(record.getProductsId())).collect(Collectors.toList());
            if (list.size() > 0){
                record.setProducts(list.get(0));
            }
            record.setStatus(0);
            if (StringUtils.isNotBlank(record.getWriteOffCode())) {
                record.setStatus(1);
            }
        }
        return records;
    }

    /**
     * 管理员获取积分兑换记录分页
     *
     * @param t
     * @return
     */
    @Override
    public IPage<EduExchangeRecord> getPage(PageT page, EduExchangeRecord t) {
        List<EduExchangeRecord> list = this.getList(t);
        return UtilPage.getPage(page, list);
    }

    /**
     * 获取当月按天统计的积分兑换数据
     *
     * @return
     */
    @Override
    public List<EchartsVO> countByMonth() {
        //获取当月所有日期
        Calendar cd = Calendar.getInstance();
        int year = cd.get(Calendar.YEAR);
        int month = cd.get(Calendar.MONTH);
        int[] monDays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int day = monDays[month];
        //如果是闰年2月
        if (((year) % 4 == 0 && (year) % 100 != 0) || (year) % 400 == 0) {
            if ((month + 1) == 2) {
                day = 29;
            }
        }
        //找出当月兑换积分
        LambdaQueryWrapper<EduExchangeRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(EduExchangeRecord::getCreateTime, UtilDate.firstDayOfMonth());
        wrapper.le(EduExchangeRecord::getCreateTime, UtilDate.lastDayOfMonth());
        List<EduExchangeRecord> exchangeRecords = this.baseMapper.selectList(wrapper);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        List<EchartsVO> echartsVOS = new ArrayList<>();
        for (int i = 1; i <= day; i++) {
            String date = year + "-" + (month + 1) + "-" + i;
            System.out.println(date);
            EchartsVO echartsVO = new EchartsVO();
            echartsVO.setName((month + 1) + "-" + i);
            List<EduExchangeRecord> collect = exchangeRecords.stream()
                    .filter(n -> dateTimeFormatter.format(n.getCreateTime()).equals(date)).collect(Collectors.toList());
            if (ObjectUtil.isEmpty(collect)) {
                echartsVO.setValue(0);
            }
            Integer reduce = collect.stream().map(EduExchangeRecord::getTotalPoint).reduce(0, (ele, res) -> ele + res);
            echartsVO.setValue(reduce);
            echartsVOS.add(echartsVO);
        }
        return echartsVOS;
//        return this.baseMapper.countPointsByMonth();
    }

    /**
     * 获取当年统计的积分兑换数据
     *
     * @return
     */
    @Override
    public List<EchartsVO> countByYear() {
        return this.baseMapper.countPointsByYear();
    }

    @Override
    public Map<String, Object> pointExchange(EduPointExchangeDTO dto) {
        Map<String, Object> map = new HashMap<>();
//        // 累积积分兑换次数
//        // 查询总兑换次数
//        int allCount = this.baseMapper.selectAllCount();
//        map.put("allCount",allCount);
//        // 本月兑换次数
//        int thisCount = this.baseMapper.selectThisCount(LocalDate.now().toString());
//        map.put("thisCount",allCount);
//        // 上月兑换次数
//        int lastCount = this.baseMapper.selectLastCount(LocalDate.now().minusMonths(1).toString());
//        BigDecimal linkRelativeRatio = BigDecimal.ZERO;
//        if (0!=lastCount) {
//            // 月环比
//            linkRelativeRatio = BigDecimal.valueOf(thisCount).subtract(BigDecimal.valueOf(lastCount))
//                    .divide(BigDecimal.valueOf(lastCount)).setScale(4,BigDecimal.ROUND_UP)
//                    .multiply(BigDecimal.valueOf(100));
//        }
//        map.put("linkRelative",linkRelativeRatio);
//        // 上年度本月兑换次数
//        int lastYearCount = this.baseMapper.selectLastYearCount(LocalDate.now().minusYears(1).toString());
//        BigDecimal yearBasis = BigDecimal.ZERO;
//        if (0!=lastYearCount){
//            // 上年度同比
//            yearBasis = BigDecimal.valueOf(thisCount).subtract(BigDecimal.valueOf(lastYearCount))
//                    .divide(BigDecimal.valueOf(lastYearCount)).setScale(4,BigDecimal.ROUND_UP)
//                    .multiply(BigDecimal.valueOf(100));
//        }
//        map.put("yearBasis",yearBasis);
        // 近一月积分兑换
        if (StringUtils.equals("2", dto.getQueryType())) {
            // 本周
            List<StatisticsVO> weekDate = this.baseMapper.selectStaticList(dto);
            List<StatisticsVO> weekList = UtilDate.weekDays(LocalDate.parse(dto.getStartTime()), LocalDate.parse(dto.getEndTime()));
            List<StatisticsVO> statisticsVOS = UtilList.sortBASC(weekList, StatisticsVO::getName);
            List<Map<String, Object>> weekLists = UtilList.supplementaryValue(weekDate, statisticsVOS, StatisticsVO::getName,
                    StatisticsVO::getName, StatisticsVO::getValue, "0");
            map.put("statics", weekLists);
        } else if (StringUtils.equals("3", dto.getQueryType())) {
            // 本月
            List<StatisticsVO> monthDate = this.baseMapper.selectStaticList(dto);
            List<StatisticsVO> monthList = UtilDate.monthDays();
            List<StatisticsVO> statisticsVOS = UtilList.sortBASC(monthList, StatisticsVO::getName);
            List<Map<String, Object>> monthLists = UtilList.supplementaryValue(monthDate, statisticsVOS, StatisticsVO::getName,
                    StatisticsVO::getName, StatisticsVO::getValue, "0");
            map.put("statics", monthLists);
        } else {
            // 本年
            List<StatisticsVO> yearDate = this.baseMapper.selectStaticList(dto);
            List<StatisticsVO> yearList = UtilDate.yearDays();
            List<StatisticsVO> statisticsVOS = UtilList.sortBASC(yearList, StatisticsVO::getName);
            List<Map<String, Object>> yearLists = UtilList.supplementaryValue(yearDate, statisticsVOS, StatisticsVO::getName,
                    StatisticsVO::getName, StatisticsVO::getValue, "0");
            map.put("statics", yearLists);
        }

        return map;
    }

    /**
     * 获取各村使用积分用户统计
     *
     * @return
     */
    @Override
    public List<EchartsVO> countByVillage() {
        List<EduExchangeRecord> records = this.getList(new EduExchangeRecord());
        List<EduPersonnelManagement> personnels = managementService.getList();
        for (EduExchangeRecord record : records) {
            List<EduPersonnelManagement> collect = personnels.stream().filter(n -> n.getId().equals(record.getUserId())).collect(Collectors.toList());
            record.setVillageName(collect.get(0).getVillageName());
        }
        List<AreaDTO> areaDTOS = areaService.findAreaByType("3", ServiceNameConstants.EDUCATION_SERVICE).getData();

        List<String> villages = areaDTOS.stream().filter(n -> StringUtils.isNotBlank(n.getName())).map(AreaDTO::getName).distinct().collect(Collectors.toList());
        List<EchartsVO> echartsVOS = new ArrayList<>();

        for (String village : villages) {
            EchartsVO echartsVO = new EchartsVO();
            echartsVO.setName(village);
            echartsVO.setValue(0);
            long count = records.stream().filter(n -> StringUtils.isNotBlank(n.getVillageName()) && n.getVillageName().equals(village))
                    .map(EduExchangeRecord::getUserId).distinct().count();
            if (ObjectUtil.isNotEmpty(count)) {
                echartsVO.setValue((int) count);
            }
            echartsVOS.add(echartsVO);
        }
        echartsVOS = echartsVOS.stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).limit(5).collect(Collectors.toList());
        return echartsVOS;
    }

    /**
     * 按商品分类使用积分用户统计
     *
     * @return
     */
    @Override
    public List<EchartsVO> countByProducts() {
        List<EduExchangeRecord> records = this.getList(new EduExchangeRecord());
        for (EduExchangeRecord record : records) {
            if (Objects.nonNull(record.getProducts())){
                record.setProductsType(record.getProducts().getProductType());
            }
        }
        List<EduProducts> list = productsService.getList(new EduProducts());
        List<String> types = list.stream().map(EduProducts::getProductType).distinct().collect(Collectors.toList());
        List<EchartsVO> echartsVOS = new ArrayList<>();

        for (String type : types) {
            EchartsVO echartsVO = new EchartsVO();
            echartsVO.setName(type);
            echartsVO.setValue(0);
            Integer reduce = records.stream().filter(n ->Objects.equals(n.getProductsType(),type))
                    .map(EduExchangeRecord::getTotalPoint).reduce(0, (res, ele) -> res + ele);
            if (ObjectUtil.isNotEmpty(reduce)) {
                echartsVO.setValue(reduce);
            }
            echartsVOS.add(echartsVO);
        }
        echartsVOS = echartsVOS.stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).collect(Collectors.toList());
        return echartsVOS;
    }
}




