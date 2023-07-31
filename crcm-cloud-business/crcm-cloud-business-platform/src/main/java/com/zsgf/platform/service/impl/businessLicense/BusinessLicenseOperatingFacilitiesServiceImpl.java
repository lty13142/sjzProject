package com.zsgf.platform.service.impl.businessLicense;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessLicense.BusinessLicenseOperatingFacilitiesMapper;
import com.zsgf.platform.model.entity.businessLicense.BusinessLicenseOperatingFacilities;
import com.zsgf.platform.service.businessLicense.BusinessLicenseOperatingFacilitiesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营许可证_02经营设施信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessLicenseOperatingFacilitiesServiceImpl extends ServiceImpl<BusinessLicenseOperatingFacilitiesMapper, BusinessLicenseOperatingFacilities> implements BusinessLicenseOperatingFacilitiesService {


    /**
     * 新增危险废物_经营许可证_02经营设施信息
     *
     * @param businessLicenseOperatingFacilities 危险废物_经营许可证_02经营设施信息
     * @return 结果
     */
    @Override
    public boolean saveBusinessLicenseOperatingFacilities(BusinessLicenseOperatingFacilities businessLicenseOperatingFacilities) {
        return this.save(businessLicenseOperatingFacilities);
    }

    /**
     * 修改危险废物_经营许可证_02经营设施信息
     *
     * @param businessLicenseOperatingFacilities 危险废物_经营许可证_02经营设施信息
     * @return 结果
     */
    @Override
    public boolean updateBusinessLicenseOperatingFacilities(BusinessLicenseOperatingFacilities businessLicenseOperatingFacilities) {
        return this.updateById(businessLicenseOperatingFacilities);
    }

    /**
     * 删除危险废物_经营许可证_02经营设施信息信息
     *
     * @param id 危险废物_经营许可证_02经营设施信息ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessLicenseOperatingFacilities(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营许可证_02经营设施信息
     *
     * @param id 危险废物_经营许可证_02经营设施信息ID
     * @return 危险废物_经营许可证_02经营设施信息
     */
    @Override
    public BusinessLicenseOperatingFacilities findBusinessLicenseOperatingFacilitiesById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营许可证_02经营设施信息列表
     *
     * @param businessLicenseOperatingFacilities 危险废物_经营许可证_02经营设施信息
     * @return 危险废物_经营许可证_02经营设施信息
     */
    @Override
    public List<BusinessLicenseOperatingFacilities> findBusinessLicenseOperatingFacilitiesList(BusinessLicenseOperatingFacilities businessLicenseOperatingFacilities) {
        LambdaQueryWrapper<BusinessLicenseOperatingFacilities> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营许可证_02经营设施信息
     *
     * @param page                               分页参数
     * @param businessLicenseOperatingFacilities 危险废物_经营许可证_02经营设施信息
     * @return 危险废物_经营许可证_02经营设施信息
     */
    @Override
    public PageT<BusinessLicenseOperatingFacilities> findBusinessLicenseOperatingFacilitiesPage(PageT<BusinessLicenseOperatingFacilities> page, BusinessLicenseOperatingFacilities businessLicenseOperatingFacilities) {
        LambdaQueryWrapper<BusinessLicenseOperatingFacilities> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
