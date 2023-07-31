package com.zsgf.platform.service.businessLicense;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessLicense.BusinessLicenseWaste;

import java.util.List;

/**
 * 危险废物_经营许可证_04废物信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessLicenseWasteService extends IService<BusinessLicenseWaste> {

    /**
     * 新增危险废物_经营许可证_04废物信息
     *
     * @param businessLicenseWaste 危险废物_经营许可证_04废物信息
     * @return 结果
     */
    boolean saveBusinessLicenseWaste(BusinessLicenseWaste businessLicenseWaste);

    /**
     * 修改危险废物_经营许可证_04废物信息
     *
     * @param businessLicenseWaste 危险废物_经营许可证_04废物信息
     * @return 结果
     */
    boolean updateBusinessLicenseWaste(BusinessLicenseWaste businessLicenseWaste);

    /**
     * 删除危险废物_经营许可证_04废物信息信息
     *
     * @param id 危险废物_经营许可证_04废物信息ID
     * @return 结果
     */
    boolean deleteBusinessLicenseWaste(String id);

    /**
     * 查询危险废物_经营许可证_04废物信息
     *
     * @param id 危险废物_经营许可证_04废物信息ID
     * @return 危险废物_经营许可证_04废物信息
     */
    BusinessLicenseWaste findBusinessLicenseWasteById(String id);

    /**
     * 查询危险废物_经营许可证_04废物信息列表
     *
     * @param businessLicenseWaste 危险废物_经营许可证_04废物信息
     * @return 危险废物_经营许可证_04废物信息集合
     */
    List<BusinessLicenseWaste> findBusinessLicenseWasteList(BusinessLicenseWaste businessLicenseWaste);

    /**
     * 分页查询危险废物_经营许可证_04废物信息列表
     *
     * @param page                 分页参数
     * @param businessLicenseWaste 危险废物_经营许可证_04废物信息
     * @return 危险废物_经营许可证_04废物信息集合
     */
    PageT<BusinessLicenseWaste> findBusinessLicenseWastePage(PageT<BusinessLicenseWaste> page, BusinessLicenseWaste businessLicenseWaste);
}
