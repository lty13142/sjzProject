package com.sjz.governance.excel.processors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.excel.annotation.ExcelProperty;
import com.crcm.cloud.start.office.easyexcel.BaseProcessor;
import com.crcm.core.constant.DicConstant;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.dto.DictCacheDTO;
import com.crcm.core.exception.CustomException;
import com.sjz.governance.excel.model.VillagerManagementModel;
import com.sjz.governance.model.entity.villager.VillagerManagement;
import com.sjz.governance.model.vo.AreaVo;
import com.sjz.governance.service.villager.VillagerManagementService;
import com.sjz.governance.utils.UtilDic;
import com.sjz.governance.utils.UtilSysArea;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @ClassName VillagerManagementProcessor
 * @Description 村民导入数据处理器
 * @Author GZL
 * @Date 2023/4/4 15:30
 * @Version 1.0
 **/
public class VillagerManagementProcessor implements BaseProcessor {

    private final VillagerManagementService villagerManagementService;

    public VillagerManagementProcessor(VillagerManagementService villagerManagementService) {
        this.villagerManagementService = villagerManagementService;
    }

    @Override
    public void invoke(List<Map<Integer, String>> list) {
        List<VillagerManagement> dataList = new ArrayList<>();
        List<DictCacheDTO> sexList = UtilDic.getDicByCode(DicConstant.SEX.CODE);
        List<DictCacheDTO> villagerList = UtilDic.getDicByCode(DicConstant.VILLAGER_TYPE.CODE);
        // 所属区列表
        List<AreaVo> areaList = UtilSysArea.getAreaByType(SystemConstant.AREA_TYPE.AREA);
        // 所属村列表
        List<AreaVo> villageList = UtilSysArea.getAreaByType(SystemConstant.AREA_TYPE.VILLAGE);
        list.forEach(map -> {
            Class<VillagerManagementModel> villagerManagementModelClass = VillagerManagementModel.class;
            Field[] declaredFields = villagerManagementModelClass.getDeclaredFields();
            Map<String, Object> data = new HashMap<>();
            for (Field field : declaredFields) {
                ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
                if (Objects.isNull(excelProperty)) {
                    continue;
                }
                data.put(field.getName(), map.getOrDefault(excelProperty.index(), null));
            }
            if (!data.keySet().isEmpty()) {
                // 类型转换
                VillagerManagement villagerManagement = BeanUtil.mapToBean(data, VillagerManagement.class, true, new CopyOptions());
                villagerManagement.setSex(StringUtils.isNotBlank(villagerManagement.getSex()) ? sexList.stream()
                        .filter(dict -> Objects.equals(dict.getName().trim(),
                                villagerManagement.getSex().trim())).findFirst().orElse(new DictCacheDTO()).getValue() : null);
                villagerManagement.setType(StringUtils.isNotBlank(villagerManagement.getType()) ? villagerList.stream()
                        .filter(dict -> Objects.equals(dict.getName().trim(),
                                villagerManagement.getType().trim())).findFirst().orElse(new DictCacheDTO()).getValue() : null);
                villagerManagement.setVillage(UtilSysArea.getAreaId(villageList, villagerManagement.getVillage()));
                villagerManagement.setDistrict(UtilSysArea.getAreaId(areaList, villagerManagement.getDistrict()));
                if (StringUtils.isEmpty(villagerManagement.getVillagerName()) || StringUtils.isEmpty(villagerManagement.getType())
                    || StringUtils.isEmpty(villagerManagement.getDistrict()) || StringUtils.isEmpty(villagerManagement.getVillage())){
                    throw new CustomException("必填项不能为空");
                }
                // 身份证唯一效验
                VillagerManagement villager = villagerManagementService.findVillagerManagementByIdNum(villagerManagement.getIdNum());
                if (Objects.nonNull(villager)){
                    throw new CustomException("身份证号相同，请检查！");
                }
                dataList.add(villagerManagement);
            }
        });
        if (CollectionUtils.isNotEmpty(dataList)) {
            villagerManagementService.saveBatch(dataList, dataList.size());
        }
    }
}
