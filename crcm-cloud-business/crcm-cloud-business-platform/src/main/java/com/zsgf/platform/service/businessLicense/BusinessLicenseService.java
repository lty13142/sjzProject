package com.zsgf.platform.service.businessLicense;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.dto.businessLicense.BusinessLicenseDTO;
import com.zsgf.platform.model.entity.businessLicense.BusinessLicense;
import com.zsgf.platform.vo.businessLicense.BusinessLicenseVo;

import java.util.List;

/**
 * 危险废物_经营许可证_01基本信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessLicenseService extends IService<BusinessLicense> {

    /**
     * 新增危险废物_经营许可证_01基本信息
     *
     * @param businessLicense 危险废物_经营许可证_01基本信息
     * @return 结果
     */
    boolean saveBusinessLicense(BusinessLicense businessLicense);

    /**
     * 修改危险废物_经营许可证_01基本信息
     *
     * @param businessLicense 危险废物_经营许可证_01基本信息
     * @return 结果
     */
    boolean updateBusinessLicense(BusinessLicense businessLicense);

    /**
     * 删除危险废物_经营许可证_01基本信息信息
     *
     * @param id 危险废物_经营许可证_01基本信息ID
     * @return 结果
     */
    boolean deleteBusinessLicense(String id);

    /**
     * 查询危险废物_经营许可证_01基本信息
     *
     * @param id 危险废物_经营许可证_01基本信息ID
     * @return 危险废物_经营许可证_01基本信息
     */
    BusinessLicense findBusinessLicenseById(String id);

    /**
     * 查询危险废物_经营许可证_01基本信息列表
     *
     * @param businessLicense 危险废物_经营许可证_01基本信息
     * @return 危险废物_经营许可证_01基本信息集合
     */
    List<BusinessLicense> findBusinessLicenseList(BusinessLicenseDTO businessLicense);

    /**
     * 分页查询危险废物_经营许可证_01基本信息列表
     *
     * @param page            分页参数
     * @param businessLicense 危险废物_经营许可证_01基本信息
     * @return 危险废物_经营许可证_01基本信息集合
     */
    PageT<BusinessLicenseVo> findBusinessLicensePage(PageT<BusinessLicenseVo> page, BusinessLicenseDTO businessLicense);
}
