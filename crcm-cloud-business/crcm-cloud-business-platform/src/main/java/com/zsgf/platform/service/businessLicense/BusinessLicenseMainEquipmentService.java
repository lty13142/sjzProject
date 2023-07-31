package com.zsgf.platform.service.businessLicense;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessLicense.BusinessLicenseMainEquipment;

import java.util.List;

/**
 * 危险废物_经营许可证_03主要设备装置情况Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessLicenseMainEquipmentService extends IService<BusinessLicenseMainEquipment> {

    /**
     * 新增危险废物_经营许可证_03主要设备装置情况
     *
     * @param businessLicenseMainEquipment 危险废物_经营许可证_03主要设备装置情况
     * @return 结果
     */
    boolean saveBusinessLicenseMainEquipment(BusinessLicenseMainEquipment businessLicenseMainEquipment);

    /**
     * 修改危险废物_经营许可证_03主要设备装置情况
     *
     * @param businessLicenseMainEquipment 危险废物_经营许可证_03主要设备装置情况
     * @return 结果
     */
    boolean updateBusinessLicenseMainEquipment(BusinessLicenseMainEquipment businessLicenseMainEquipment);

    /**
     * 删除危险废物_经营许可证_03主要设备装置情况信息
     *
     * @param id 危险废物_经营许可证_03主要设备装置情况ID
     * @return 结果
     */
    boolean deleteBusinessLicenseMainEquipment(String id);

    /**
     * 查询危险废物_经营许可证_03主要设备装置情况
     *
     * @param id 危险废物_经营许可证_03主要设备装置情况ID
     * @return 危险废物_经营许可证_03主要设备装置情况
     */
    BusinessLicenseMainEquipment findBusinessLicenseMainEquipmentById(String id);

    /**
     * 查询危险废物_经营许可证_03主要设备装置情况列表
     *
     * @param businessLicenseMainEquipment 危险废物_经营许可证_03主要设备装置情况
     * @return 危险废物_经营许可证_03主要设备装置情况集合
     */
    List<BusinessLicenseMainEquipment> findBusinessLicenseMainEquipmentList(BusinessLicenseMainEquipment businessLicenseMainEquipment);

    /**
     * 分页查询危险废物_经营许可证_03主要设备装置情况列表
     *
     * @param page                         分页参数
     * @param businessLicenseMainEquipment 危险废物_经营许可证_03主要设备装置情况
     * @return 危险废物_经营许可证_03主要设备装置情况集合
     */
    PageT<BusinessLicenseMainEquipment> findBusinessLicenseMainEquipmentPage(PageT<BusinessLicenseMainEquipment> page, BusinessLicenseMainEquipment businessLicenseMainEquipment);
}
