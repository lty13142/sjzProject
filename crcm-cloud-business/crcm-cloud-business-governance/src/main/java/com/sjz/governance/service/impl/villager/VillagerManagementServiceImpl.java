package com.sjz.governance.service.impl.villager;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.office.utils.UtilEasyExcel;
import com.crcm.core.constant.DicConstant;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.dto.DictCacheDTO;
import com.crcm.core.exception.CustomException;
import com.crcm.core.utils.UtilDataFormat;
import com.crcm.core.utils.UtilECharts;
import com.crcm.core.vo.echarts.EChartsIntegerBar;
import com.crcm.core.vo.echarts.EChartsIntegerPie;
import com.crcm.core.vo.echarts.EChartsIntegerPieTotal;
import com.sjz.governance.excel.model.VillagerManagementModel;
import com.sjz.governance.excel.processors.VillagerManagementProcessor;
import com.sjz.governance.mapper.villager.VillagerManagementMapper;
import com.sjz.governance.model.dto.villager.VillagerManagementDTO;
import com.sjz.governance.model.entity.villager.VillagerManagement;
import com.sjz.governance.model.vo.AreaVo;
import com.sjz.governance.model.vo.villager.VillagerManagementVo;
import com.sjz.governance.model.vo.villager.VillagerStaticsAgeVo;
import com.sjz.governance.model.vo.villager.VillagerStaticsVo;
import com.sjz.governance.service.villager.VillagerManagementService;
import com.sjz.governance.utils.UtilDic;
import com.sjz.governance.utils.UtilSysArea;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * 村民信息管理Service业务层处理
 *
 * @author guozhilin
 * @date 2023-04-03
 */
@Service
public class VillagerManagementServiceImpl extends ServiceImpl<VillagerManagementMapper, VillagerManagement> implements VillagerManagementService {

    /**
     * 新增村民信息管理
     *
     * @param villagerManagement 村民信息管理
     * @return 结果
     */
    @Override
    public boolean saveVillagerManagement(VillagerManagement villagerManagement) {
        //身份证已存在
        if(findByIdNum(villagerManagement.getIdNum()) > 0){
            throw new CustomException("身份证号码已存在！");
        }
        return this.save(villagerManagement);
    }

    /**
     * 修改村民信息管理
     *
     * @param villagerManagement 村民信息管理
     * @return 结果
     */
    @Override
    public boolean updateVillagerManagement(VillagerManagement villagerManagement) {
        //身份证已存在
        VillagerManagement villager = getVillagerByIdNum(villagerManagement.getIdNum());
        if (Objects.nonNull(villager) && !Objects.equals(villagerManagement.getId(), villager.getId())) {
            throw new CustomException("身份证号码已存在！");
        }
        return this.updateById(villagerManagement);
    }

    /**
     * 删除村民信息管理信息
     *
     * @param id 村民信息管理ID
     * @return 结果
     */
    @Override
    public boolean deleteVillagerManagement(String id) {
        return this.removeById(id);
    }

    @Override
    public void deleteBatch(List<String> ids) {
        this.baseMapper.deleteBatchIds(ids);
    }

    /**
     * 查询村民信息管理
     *
     * @param id 村民信息管理ID
     * @return 村民信息管理
     */
    @Override
    public VillagerManagement findVillagerManagementById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询村民信息管理列表
     *
     * @param villagerManagement 村民信息管理
     * @return 村民信息管理
     */
    @Override
    public List<VillagerManagement> findVillagerManagementList(VillagerManagementDTO villagerManagement) {
        LambdaQueryWrapper<VillagerManagement> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(villagerManagement.getVillage()), VillagerManagement::getVillage, villagerManagement.getVillage());
        queryWrapper.eq(StringUtils.isNotBlank(villagerManagement.getType()), VillagerManagement::getType, villagerManagement.getType());
        queryWrapper.like(StringUtils.isNotBlank(villagerManagement.getVillagerName()), VillagerManagement::getVillagerName, villagerManagement.getVillagerName());
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询村民信息管理
     *
     * @param page               分页参数
     * @param villagerManagement 村民信息管理
     * @return 村民信息管理
     */
    @Override
    public PageT<VillagerManagementVo> findVillagerManagementPage(PageT<VillagerManagementVo> page, VillagerManagementDTO villagerManagement) {
        return this.baseMapper.findVillagerManagementPage(page, villagerManagement);
    }

    @Override
    public void exportVillager(VillagerManagementDTO villagerManagement, HttpServletRequest request, HttpServletResponse response) {
        List<VillagerManagementModel> list = this.baseMapper.getExportVillagerData(villagerManagement);
        if (CollectionUtil.isNotEmpty(list)) {
            // 所属区列表
            List<AreaVo> areaList = UtilSysArea.getAreaByType(SystemConstant.AREA_TYPE.AREA);
            // 所属村列表
            List<AreaVo> villageList = UtilSysArea.getAreaByType(SystemConstant.AREA_TYPE.VILLAGE);
            list.forEach(model -> {
                model.setSex(UtilDic.getDictName(DicConstant.SEX.CODE, model.getSex()));
                model.setType(UtilDic.getDictName(DicConstant.VILLAGER_TYPE.CODE, model.getType()));
                model.setVillage(UtilSysArea.getAreaName(villageList, model.getVillage()));
                model.setDistrict(UtilSysArea.getAreaName(areaList, model.getDistrict()));
            });
            try {
                UtilEasyExcel.downloadExcelByModel(response, list, "村民信息.xlsx", VillagerManagementModel.class);
            } catch (IOException e) {
                throw new CustomException("村民信息导出失败");
            }
        }
    }

    @Override
    public void importVillager(MultipartFile file) {
        try {
            UtilEasyExcel.readData(file.getInputStream(), new VillagerManagementProcessor(this), 100);
        } catch (IOException e) {
            throw new CustomException("导入失败");
        }
    }

    @Override
    public void exportModel(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<DictCacheDTO> sexList = UtilDic.getDicByCode(DicConstant.SEX.CODE);
            List<DictCacheDTO> typeList = UtilDic.getDicByCode(DicConstant.VILLAGER_TYPE.CODE);
            Map<Integer, List<String>> selectMap = new HashMap<>();
            selectMap.put(4, UtilDataFormat.listAttr(sexList, DictCacheDTO::getChName));
            selectMap.put(5, UtilDataFormat.listAttr(typeList, DictCacheDTO::getChName));
            // 所属区列表
            List<AreaVo> areaList = UtilSysArea.getAreaByType(SystemConstant.AREA_TYPE.AREA);
            selectMap.put(1, UtilDataFormat.listAttr(areaList, AreaVo::getName));
            // 所属村列表
            List<AreaVo> villageList = UtilSysArea.getAreaByType(SystemConstant.AREA_TYPE.VILLAGE);
            selectMap.put(2, UtilDataFormat.listAttr(villageList, AreaVo::getName));
            UtilEasyExcel.downloadByModel(response, 1, selectMap, VillagerManagementModel.class, "村民信息.xlsx");
        } catch (IOException e) {
            throw new CustomException("导出模板下载失败");
        }
    }

    @Override
    public List<EChartsIntegerPie> getVillagerStatisticsByArea(VillagerManagementDTO dto) {
        if(StringUtils.isBlank(dto.getVillage()) && StringUtils.isNotBlank(dto.getVillageName())){
            String areaId = UtilSysArea.getAreaId(SystemConstant.AREA_TYPE.VILLAGE, dto.getVillageName());
            if(StringUtils.isBlank(areaId)){
                throw new CustomException("该村不存在！！！");
            }
            dto.setVillage(areaId);
        }
        List<EChartsIntegerPie> statistics = this.baseMapper.getVillagerStatisticsByArea(dto);
        List<DictCacheDTO> dtoList = UtilDic.getDicByCode(DicConstant.VILLAGER_TYPE.CODE);
        List<EChartsIntegerPie> fullEChartsData = UtilECharts.getFullEChartsData(statistics, dtoList, DictCacheDTO::getValue, DictCacheDTO::getChName);
        fullEChartsData.forEach(data -> data.setIcon(UtilDic.getDictComment(dtoList, data.getTypeCode())));
        return fullEChartsData;
    }

    @Override
    public EChartsIntegerPieTotal getVillagerStatisticsByType() {
        List<EChartsIntegerPie> statistics = getVillagerStatisticsByArea(new VillagerManagementDTO());
        EChartsIntegerPieTotal total = new EChartsIntegerPieTotal();
        total.setTotal(UtilDataFormat.getListSum(statistics, EChartsIntegerPie::getValueData));
        total.setDataList(statistics);
        return total;
    }

    @Override
    public EChartsIntegerBar getStatisticsByVillageAndType(String type) {
        List<EChartsIntegerPie> dataList = this.baseMapper.getStatisticsByVillageAndType(type);
        List<AreaVo> areaByType = UtilSysArea.getAreaByType(SystemConstant.AREA_TYPE.VILLAGE);
        if (CollectionUtils.isEmpty(areaByType)) {
            return new EChartsIntegerBar();
        }
        List<EChartsIntegerPie> fullEChartsData = UtilECharts.getFullEChartsData(dataList, areaByType, AreaVo::getId, AreaVo::getName);
        // 按人数倒叙
        List<EChartsIntegerPie> sortList = UtilDataFormat.getListSortDesc(fullEChartsData, EChartsIntegerPie::getValueData);
        return UtilECharts.echartsPieToBar(sortList);
    }

    @Override
    public VillagerStaticsVo getStatisticsByStructure() {
        //获取男女总数
        int manNum = this.baseMapper.getAllNum("0");
        int woManNum = this.baseMapper.getAllNum("1");
        VillagerStaticsVo villagerStaticsVo = new VillagerStaticsVo();
        villagerStaticsVo.setManNum(manNum);
        villagerStaticsVo.setWoManNum(woManNum);
        List<VillagerStaticsAgeVo> resultList = new ArrayList<>();
        //获取所有村民信息
        List<VillagerManagementVo> allVillager = this.baseMapper.getAllVillager();
        //0-10岁人员架构
        VillagerStaticsAgeVo v1 = getVo("0-10", 0, 10, allVillager);
        resultList.add(v1);
        //10-20岁人员架构
        VillagerStaticsAgeVo v2 = getVo("10-20", 10, 20, allVillager);
        resultList.add(v2);
        //20-30岁人员架构
        VillagerStaticsAgeVo v3 = getVo("20-30", 20, 30, allVillager);
        resultList.add(v3);
        //30-40岁人员架构
        VillagerStaticsAgeVo v4 = getVo("30-40", 30, 40, allVillager);
        resultList.add(v4);
        //40-50岁人员架构
        VillagerStaticsAgeVo v5 = getVo("40-50", 40, 50, allVillager);
        resultList.add(v5);
        //50-60岁人员架构
        VillagerStaticsAgeVo v6 = getVo("50-60", 50, 60, allVillager);
        resultList.add(v6);
        //60岁以上人员架构
        VillagerStaticsAgeVo v7 = getVo("60以上", 60, allVillager);
        resultList.add(v7);
        villagerStaticsVo.setVoList(resultList);
        return villagerStaticsVo;
    }

    @Override
    public VillagerManagement findVillagerManagementByIdNum(String idNum) {
        return this.baseMapper.findVillagerManagementByIdNum(idNum);
    }

    public VillagerStaticsAgeVo getVo(String ageRange, int min, int max,List<VillagerManagementVo> allVillager){
        allVillager.stream().forEach(s -> s.setTheAge(Objects.nonNull(s.getBirthday()) ? Period.between(s.getBirthday(),LocalDate.now()).getYears() : 0));
        int c1 = (int)allVillager.stream().filter(s -> (s.getTheAge() > min && s.getTheAge() <= max && "0".equals(s.getSex()))).count();
        int c2 = (int)allVillager.stream().filter(s -> (s.getTheAge() > min && s.getTheAge() <= max && "1".equals(s.getSex()))).count();
        BigDecimal b1 = new BigDecimal(c1);
        BigDecimal b2 = new BigDecimal(c2);
        BigDecimal b3 = new BigDecimal(c1 + c2);
        VillagerStaticsAgeVo v1 = new VillagerStaticsAgeVo();
        v1.setMNum(c1);
        v1.setWmNum(c2);
        v1.setAgeRange(ageRange);
        v1.setMNumPercentage(!BigDecimal.valueOf(0).equals(b3) ? b1.divide(b3,2,BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)) + "%" : "0%");
        v1.setWmNumPercentage(!BigDecimal.valueOf(0).equals(b3) ? b2.divide(b3,2,BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)) + "%" : "0%");
        return v1;
    }

    public VillagerStaticsAgeVo getVo(String ageRange, int min, List<VillagerManagementVo> allVillager){
        allVillager.stream().forEach(s -> s.setTheAge(Objects.nonNull(s.getBirthday()) ? Period.between(s.getBirthday(),LocalDate.now()).getYears() : 0));
        int c1 = (int)allVillager.stream().filter(s -> (s.getTheAge() > min  && "0".equals(s.getSex()))).count();
        int c2 = (int)allVillager.stream().filter(s -> (s.getTheAge() > min  && "1".equals(s.getSex()))).count();
        BigDecimal b1 = new BigDecimal(c1);
        BigDecimal b2 = new BigDecimal(c2);
        BigDecimal b3 = new BigDecimal(c1 + c2);
        VillagerStaticsAgeVo v1 = new VillagerStaticsAgeVo();
        v1.setMNum(c1);
        v1.setWmNum(c2);
        v1.setAgeRange(ageRange);
        v1.setMNumPercentage(!BigDecimal.valueOf(0).equals(b3) ? b1.divide(b3,2,BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)) + "%" : "0%");
        v1.setWmNumPercentage(!BigDecimal.valueOf(0).equals(b3) ? b2.divide(b3,2,BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)) + "%" : "0%");
        return v1;
    }

    public int findByIdNum(String idNum){
        LambdaQueryWrapper<VillagerManagement> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(idNum), VillagerManagement::getIdNum, idNum);
        return this.baseMapper.selectCount(queryWrapper);
    }

    public VillagerManagement getVillagerByIdNum(String idNum){
        LambdaQueryWrapper<VillagerManagement> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(idNum), VillagerManagement::getIdNum, idNum);
        return this.baseMapper.selectOne(queryWrapper);
    }
}
