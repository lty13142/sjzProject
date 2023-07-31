package com.zsgf.platform.service.impl.businessLicense;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.sso.domain.AuthUser;
import com.crcm.core.constant.SystemConstant;
import com.zsgf.platform.dto.businessLicense.BusinessLicenseDTO;
import com.zsgf.platform.mapper.businessLicense.BusinessLicenseMapper;
import com.zsgf.platform.model.entity.businessLicense.BusinessLicense;
import com.zsgf.platform.service.businessLicense.BusinessLicenseService;
import com.zsgf.platform.utils.SecurityBusinessUtil;
import com.zsgf.platform.vo.businessLicense.BusinessLicenseVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 危险废物_经营许可证_01基本信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessLicenseServiceImpl extends ServiceImpl<BusinessLicenseMapper, BusinessLicense> implements BusinessLicenseService {


    /**
     * 新增危险废物_经营许可证_01基本信息
     *
     * @param businessLicense 危险废物_经营许可证_01基本信息
     * @return 结果
     */
    @Override
    public boolean saveBusinessLicense(BusinessLicense businessLicense) {
        AuthUser currentUser = SecurityBusinessUtil.getCurrentUserInfo();
        if (Objects.equals(SystemConstant.USER_TYPE.ENTERPRISE_ACCOUNT, currentUser.getUserType())) {
//            businessLicense.setCompanyId(currentUser.getCompanyId());
        }
        return this.save(businessLicense);
    }

    /**
     * 修改危险废物_经营许可证_01基本信息
     *
     * @param businessLicense 危险废物_经营许可证_01基本信息
     * @return 结果
     */
    @Override
    public boolean updateBusinessLicense(BusinessLicense businessLicense) {
        return this.updateById(businessLicense);
    }

    /**
     * 删除危险废物_经营许可证_01基本信息信息
     *
     * @param id 危险废物_经营许可证_01基本信息ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessLicense(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营许可证_01基本信息
     *
     * @param id 危险废物_经营许可证_01基本信息ID
     * @return 危险废物_经营许可证_01基本信息
     */
    @Override
    public BusinessLicense findBusinessLicenseById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营许可证_01基本信息列表
     *
     * @param businessLicense 危险废物_经营许可证_01基本信息
     * @return 危险废物_经营许可证_01基本信息
     */
    @Override
    public List<BusinessLicense> findBusinessLicenseList(BusinessLicenseDTO businessLicense) {
        LambdaQueryWrapper<BusinessLicense> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营许可证_01基本信息
     *
     * @param page            分页参数
     * @param businessLicense 危险废物_经营许可证_01基本信息
     * @return 危险废物_经营许可证_01基本信息
     */
    @Override
    public PageT<BusinessLicenseVo> findBusinessLicensePage(PageT<BusinessLicenseVo> page, BusinessLicenseDTO businessLicense) {
        AuthUser currentUser = SecurityBusinessUtil.getCurrentUserInfo();
        if (Objects.equals(SystemConstant.USER_TYPE.ENTERPRISE_ACCOUNT, currentUser.getUserType())) {
//            businessLicense.setCompanyId(currentUser.getCompanyId());
        }
        return this.baseMapper.findBusinessLicensePage(page, businessLicense);
    }
}
