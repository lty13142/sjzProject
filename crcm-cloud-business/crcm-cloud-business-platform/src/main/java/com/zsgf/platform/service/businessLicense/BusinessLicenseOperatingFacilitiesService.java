package com.zsgf.platform.service.businessLicense;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessLicense.BusinessLicenseOperatingFacilities;

import java.util.List;

/**
 * 危险废物_经营许可证_02经营设施信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessLicenseOperatingFacilitiesService extends IService<BusinessLicenseOperatingFacilities> {

    /**
     * 新增危险废物_经营许可证_02经营设施信息
     *
     * @param businessLicenseOperatingFacilities 危险废物_经营许可证_02经营设施信息
     * @return 结果
     */
    boolean saveBusinessLicenseOperatingFacilities(BusinessLicenseOperatingFacilities businessLicenseOperatingFacilities);

    /**
     * 修改危险废物_经营许可证_02经营设施信息
     *
     * @param businessLicenseOperatingFacilities 危险废物_经营许可证_02经营设施信息
     * @return 结果
     */
    boolean updateBusinessLicenseOperatingFacilities(BusinessLicenseOperatingFacilities businessLicenseOperatingFacilities);

    /**
     * 删除危险废物_经营许可证_02经营设施信息信息
     *
     * @param id 危险废物_经营许可证_02经营设施信息ID
     * @return 结果
     */
    boolean deleteBusinessLicenseOperatingFacilities(String id);

    /**
     * 查询危险废物_经营许可证_02经营设施信息
     *
     * @param id 危险废物_经营许可证_02经营设施信息ID
     * @return 危险废物_经营许可证_02经营设施信息
     */
    BusinessLicenseOperatingFacilities findBusinessLicenseOperatingFacilitiesById(String id);

    /**
     * 查询危险废物_经营许可证_02经营设施信息列表
     *
     * @param businessLicenseOperatingFacilities 危险废物_经营许可证_02经营设施信息
     * @return 危险废物_经营许可证_02经营设施信息集合
     */
    List<BusinessLicenseOperatingFacilities> findBusinessLicenseOperatingFacilitiesList(BusinessLicenseOperatingFacilities businessLicenseOperatingFacilities);

    /**
     * 分页查询危险废物_经营许可证_02经营设施信息列表
     *
     * @param page                               分页参数
     * @param businessLicenseOperatingFacilities 危险废物_经营许可证_02经营设施信息
     * @return 危险废物_经营许可证_02经营设施信息集合
     */
    PageT<BusinessLicenseOperatingFacilities> findBusinessLicenseOperatingFacilitiesPage(PageT<BusinessLicenseOperatingFacilities> page, BusinessLicenseOperatingFacilities businessLicenseOperatingFacilities);
}
