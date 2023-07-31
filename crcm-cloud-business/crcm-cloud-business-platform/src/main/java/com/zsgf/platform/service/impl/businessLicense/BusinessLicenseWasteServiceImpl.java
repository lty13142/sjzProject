package com.zsgf.platform.service.impl.businessLicense;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessLicense.BusinessLicenseWasteMapper;
import com.zsgf.platform.model.entity.businessLicense.BusinessLicenseWaste;
import com.zsgf.platform.service.businessLicense.BusinessLicenseWasteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营许可证_04废物信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessLicenseWasteServiceImpl extends ServiceImpl<BusinessLicenseWasteMapper, BusinessLicenseWaste>
        implements BusinessLicenseWasteService {


    /**
     * 新增危险废物_经营许可证_04废物信息
     *
     * @param businessLicenseWaste 危险废物_经营许可证_04废物信息
     * @return 结果
     */
    @Override
    public boolean saveBusinessLicenseWaste(BusinessLicenseWaste businessLicenseWaste) {
        return this.save(businessLicenseWaste);
    }

    /**
     * 修改危险废物_经营许可证_04废物信息
     *
     * @param businessLicenseWaste 危险废物_经营许可证_04废物信息
     * @return 结果
     */
    @Override
    public boolean updateBusinessLicenseWaste(BusinessLicenseWaste businessLicenseWaste) {
        return this.updateById(businessLicenseWaste);
    }

    /**
     * 删除危险废物_经营许可证_04废物信息信息
     *
     * @param id 危险废物_经营许可证_04废物信息ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessLicenseWaste(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营许可证_04废物信息
     *
     * @param id 危险废物_经营许可证_04废物信息ID
     * @return 危险废物_经营许可证_04废物信息
     */
    @Override
    public BusinessLicenseWaste findBusinessLicenseWasteById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营许可证_04废物信息列表
     *
     * @param businessLicenseWaste 危险废物_经营许可证_04废物信息
     * @return 危险废物_经营许可证_04废物信息
     */
    @Override
    public List<BusinessLicenseWaste> findBusinessLicenseWasteList(BusinessLicenseWaste businessLicenseWaste) {
        LambdaQueryWrapper<BusinessLicenseWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营许可证_04废物信息
     *
     * @param page                 分页参数
     * @param businessLicenseWaste 危险废物_经营许可证_04废物信息
     * @return 危险废物_经营许可证_04废物信息
     */
    @Override
    public PageT<BusinessLicenseWaste> findBusinessLicenseWastePage(PageT<BusinessLicenseWaste> page, BusinessLicenseWaste businessLicenseWaste) {
        LambdaQueryWrapper<BusinessLicenseWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
