package com.zsgf.platform.service.impl.provincial;

import com.crcm.core.constant.SystemConstant;
import com.crcm.core.utils.UtilDataFormat;
import com.zsgf.platform.dto.ProvincialDTO;
import com.zsgf.platform.dto.ProvincialRequestDTO;
import com.zsgf.platform.enums.ProvincialPlatformInterfaceEnum;
import com.zsgf.platform.model.entity.businessLicense.BusinessLicense;
import com.zsgf.platform.model.entity.businessLicense.BusinessLicenseMainEquipment;
import com.zsgf.platform.model.entity.businessLicense.BusinessLicenseOperatingFacilities;
import com.zsgf.platform.model.entity.businessLicense.BusinessLicenseWaste;
import com.zsgf.platform.model.entity.company.CompanyInfo;
import com.zsgf.platform.model.entity.company.CompanyRole;
import com.zsgf.platform.model.entity.waste.WasteBaseInfo;
import com.zsgf.platform.service.businessLicense.BusinessLicenseMainEquipmentService;
import com.zsgf.platform.service.businessLicense.BusinessLicenseOperatingFacilitiesService;
import com.zsgf.platform.service.businessLicense.BusinessLicenseService;
import com.zsgf.platform.service.businessLicense.BusinessLicenseWasteService;
import com.zsgf.platform.service.company.CompanyInfoService;
import com.zsgf.platform.service.company.CompanyRoleService;
import com.zsgf.platform.service.provincial.ProvincialPlatformService;
import com.zsgf.platform.service.waste.WasteBaseInfoService;
import com.zsgf.platform.utils.provincial.ProvincialPlatformUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName ProvincialPlatformServiceImpl
 * @Description 省平台接口回流实现类
 * @Author GZL
 * @Date 2023/3/17 14:17
 * @Version 1.0
 **/
@Service
@AllArgsConstructor
public class ProvincialPlatformServiceImpl implements ProvincialPlatformService {

    public final CompanyInfoService companyInfoService;

    public final CompanyRoleService companyRoleService;

    private final BusinessLicenseService businessLicenseService;

    private final BusinessLicenseMainEquipmentService businessLicenseMainEquipmentService;

    private final BusinessLicenseOperatingFacilitiesService businessLicenseOperatingFacilitiesService;

    private final BusinessLicenseWasteService businessLicenseWasteService;

    private final WasteBaseInfoService wasteBaseInfoService;

    /**
     * 省平台企业信息回流
     *
     * @Author GZL
     * @Date 2023/3/17 16:16
     * @Param dto 条件
     **/
    @Override
    public void syncCompanyInfo(ProvincialDTO dto) {
        ProvincialPlatformUtil.dataReturn(1, new ProvincialRequestDTO<>(dto, CompanyInfo.class, new ArrayList<>(),
                ProvincialPlatformInterfaceEnum.COMPANY), result -> companyInfoService.saveOrUpdateBatch(result, result.size()));

    }

    /**
     * 省平台企业角色信息回流
     *
     * @Author GZL
     * @Date 2023/3/17 16:16
     * @Param dto 条件
     **/
    @Override
    public void syncCompanyRole(ProvincialDTO dto) {
        ProvincialPlatformUtil.dataReturn(1, new ProvincialRequestDTO<>(dto, CompanyRole.class, new ArrayList<>(),
                ProvincialPlatformInterfaceEnum.COMPANY_ROLE), result -> {
            result.forEach(data -> data.setId(data.getDwid().concat("_").concat(data.getJsid())));
            companyRoleService.saveOrUpdateBatch(result, result.size());
        });
    }

    /**
     * 省平台经营许可信息回流
     *
     * @Author GZL
     * @Date 2023/3/17 16:16
     * @Param dto 条件
     **/
    @Override
    public void syncBusinessLicense(ProvincialDTO dto) {
        syncBusinessLicenseInfo(dto);
        syncBusinessLicenseOperatingFacilities(dto);
        syncBusinessLicenseMainEquipment(dto);
        syncBusinessLicenseWaste(dto);
    }

    /**
     * 省平台废物名录信息回流
     *
     * @Author GZL
     * @Date 2023/3/20 9:22
     * @Param dto 条件
     **/
    @Override
    public void syncWasteList(ProvincialDTO dto) {
        ProvincialPlatformUtil.dataReturnFormat(1, new ProvincialRequestDTO<>(dto, WasteBaseInfo.class, new ArrayList<>(),
                ProvincialPlatformInterfaceEnum.WASTE_LIST), data -> {
            WasteBaseInfo wasteBaseInfo = new WasteBaseInfo();
            wasteBaseInfo.setWasteCategory(SystemConstant.WASTE_CATEGORY.WASTE);
            wasteBaseInfo.setId(data.getString("ID"));
            wasteBaseInfo.setWasteCodeName(data.getString("FWMC"));
            wasteBaseInfo.setWasteType(data.getString("PCODE"));
            wasteBaseInfo.setWasteCode(data.getString("FWLB"));
            wasteBaseInfo.setHazards(data.getString("WXTX"));
            wasteBaseInfo.setWasteTypeName(data.getString("FWMC"));
            wasteBaseInfo.setDirectoryVersion(data.getString("MLNF"));
            wasteBaseInfo.setLevel(data.getInteger("LEV"));
            return wasteBaseInfo;
        }, result -> {
            List<WasteBaseInfo> saveList = new ArrayList<>();
            // 子级
            List<WasteBaseInfo> wasteBaseInfos = UtilDataFormat.listFilter(result, data ->
                    Objects.equals(SystemConstant.WASTE_BASE_LEVEL.CHILD, data.getLevel()));
            // 父级
            List<WasteBaseInfo> wasteTypeList = UtilDataFormat.listFilter(result, data ->
                    Objects.equals(SystemConstant.WASTE_BASE_LEVEL.PARENT, data.getLevel()));
            wasteTypeList.forEach(type -> type.setWasteTypeName("危废"));
            Map<String, String> typeMap = UtilDataFormat.listToMap(wasteTypeList, WasteBaseInfo::getWasteCode, WasteBaseInfo::getWasteCodeName);
            wasteBaseInfos.forEach(data -> data.setWasteTypeName(typeMap.getOrDefault(data.getWasteType(), data.getWasteTypeName())));
            if (!CollectionUtils.isEmpty(wasteTypeList)) {
                saveList.addAll(wasteTypeList);
            }
            if (!CollectionUtils.isEmpty(wasteBaseInfos)) {
                saveList.addAll(wasteBaseInfos);
            }
            wasteBaseInfoService.saveOrUpdateBatch(saveList, saveList.size());
        });
    }

    /**
     * 省平台 经营许可证_01基本信息 回流
     *
     * @Author GZL
     * @Date 2023/3/17 15:19
     * @Param dto 条件
     **/
    private void syncBusinessLicenseInfo(ProvincialDTO dto) {
        ProvincialPlatformUtil.dataReturnFormat(1, new ProvincialRequestDTO<>(dto, BusinessLicense.class, new ArrayList<>(),
                ProvincialPlatformInterfaceEnum.BUSINESS_LICENSE), data -> {
            BusinessLicense businessLicense = data.toJavaObject(BusinessLicense.class);
            businessLicense.setDataSource(SystemConstant.DATA_SOURCE.PROVINCIAL_PLATFORM);
            return businessLicense;
        }, result -> businessLicenseService.saveOrUpdateBatch(result, result.size()));
    }

    /**
     * 省平台 经营许可证_02经营设施信息 回流
     *
     * @Author GZL
     * @Date 2023/3/17 15:19
     * @Param dto 条件
     **/
    private void syncBusinessLicenseOperatingFacilities(ProvincialDTO dto) {
        ProvincialPlatformUtil.dataReturnFormat(1, new ProvincialRequestDTO<>(dto, BusinessLicenseOperatingFacilities.class, new ArrayList<>(),
                ProvincialPlatformInterfaceEnum.BUSINESS_LICENSE_OPERATING_FACILITIES), data -> {
            BusinessLicenseOperatingFacilities businessLicenseOperatingFacilities = data.toJavaObject(BusinessLicenseOperatingFacilities.class);
            businessLicenseOperatingFacilities.setDataSource(SystemConstant.DATA_SOURCE.PROVINCIAL_PLATFORM);
            return businessLicenseOperatingFacilities;
        }, result -> businessLicenseOperatingFacilitiesService.saveOrUpdateBatch(result, result.size()));
    }

    /**
     * 省平台 经营许可证_03主要设备装置情况 回流
     *
     * @Author GZL
     * @Date 2023/3/17 15:19
     * @Param dto 条件
     **/
    private void syncBusinessLicenseMainEquipment(ProvincialDTO dto) {
        ProvincialPlatformUtil.dataReturnFormat(1, new ProvincialRequestDTO<>(dto, BusinessLicenseMainEquipment.class, new ArrayList<>(),
                ProvincialPlatformInterfaceEnum.BUSINESS_LICENSE_MAIN_EQUIPMENT), data -> {
            BusinessLicenseMainEquipment businessLicenseMainEquipment = data.toJavaObject(BusinessLicenseMainEquipment.class);
            businessLicenseMainEquipment.setDataSource(SystemConstant.DATA_SOURCE.PROVINCIAL_PLATFORM);
            return businessLicenseMainEquipment;
        }, result ->businessLicenseMainEquipmentService.saveOrUpdateBatch(result, result.size()));
    }

    /**
     * 省平台 经营许可证_04废物信息 回流
     *
     * @Author GZL
     * @Date 2023/3/17 15:19
     * @Param dto 条件
     **/
    private void syncBusinessLicenseWaste(ProvincialDTO dto) {
        ProvincialPlatformUtil.dataReturnFormat(1, new ProvincialRequestDTO<>(dto, BusinessLicenseWaste.class, new ArrayList<>(),
                ProvincialPlatformInterfaceEnum.BUSINESS_LICENSE_WASTE), data -> {
            BusinessLicenseWaste businessLicenseWaste = data.toJavaObject(BusinessLicenseWaste.class);
            businessLicenseWaste.setDataSource(SystemConstant.DATA_SOURCE.PROVINCIAL_PLATFORM);
            return businessLicenseWaste;
        }, result -> businessLicenseWasteService.saveOrUpdateBatch(result, result.size()));
    }
}
