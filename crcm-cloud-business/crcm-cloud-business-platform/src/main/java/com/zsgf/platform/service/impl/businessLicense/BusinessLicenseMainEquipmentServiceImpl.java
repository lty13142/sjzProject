package com.zsgf.platform.service.impl.businessLicense;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessLicense.BusinessLicenseMainEquipmentMapper;
import com.zsgf.platform.model.entity.businessLicense.BusinessLicenseMainEquipment;
import com.zsgf.platform.service.businessLicense.BusinessLicenseMainEquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营许可证_03主要设备装置情况Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessLicenseMainEquipmentServiceImpl extends ServiceImpl<BusinessLicenseMainEquipmentMapper, BusinessLicenseMainEquipment> implements BusinessLicenseMainEquipmentService {


    /**
     * 新增危险废物_经营许可证_03主要设备装置情况
     *
     * @param businessLicenseMainEquipment 危险废物_经营许可证_03主要设备装置情况
     * @return 结果
     */
    @Override
    public boolean saveBusinessLicenseMainEquipment(BusinessLicenseMainEquipment businessLicenseMainEquipment) {
        return this.save(businessLicenseMainEquipment);
    }

    /**
     * 修改危险废物_经营许可证_03主要设备装置情况
     *
     * @param businessLicenseMainEquipment 危险废物_经营许可证_03主要设备装置情况
     * @return 结果
     */
    @Override
    public boolean updateBusinessLicenseMainEquipment(BusinessLicenseMainEquipment businessLicenseMainEquipment) {
        return this.updateById(businessLicenseMainEquipment);
    }

    /**
     * 删除危险废物_经营许可证_03主要设备装置情况信息
     *
     * @param id 危险废物_经营许可证_03主要设备装置情况ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessLicenseMainEquipment(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营许可证_03主要设备装置情况
     *
     * @param id 危险废物_经营许可证_03主要设备装置情况ID
     * @return 危险废物_经营许可证_03主要设备装置情况
     */
    @Override
    public BusinessLicenseMainEquipment findBusinessLicenseMainEquipmentById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营许可证_03主要设备装置情况列表
     *
     * @param businessLicenseMainEquipment 危险废物_经营许可证_03主要设备装置情况
     * @return 危险废物_经营许可证_03主要设备装置情况
     */
    @Override
    public List<BusinessLicenseMainEquipment> findBusinessLicenseMainEquipmentList(BusinessLicenseMainEquipment businessLicenseMainEquipment) {
        LambdaQueryWrapper<BusinessLicenseMainEquipment> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营许可证_03主要设备装置情况
     *
     * @param page                         分页参数
     * @param businessLicenseMainEquipment 危险废物_经营许可证_03主要设备装置情况
     * @return 危险废物_经营许可证_03主要设备装置情况
     */
    @Override
    public PageT<BusinessLicenseMainEquipment> findBusinessLicenseMainEquipmentPage(PageT<BusinessLicenseMainEquipment> page, BusinessLicenseMainEquipment businessLicenseMainEquipment) {
        LambdaQueryWrapper<BusinessLicenseMainEquipment> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
