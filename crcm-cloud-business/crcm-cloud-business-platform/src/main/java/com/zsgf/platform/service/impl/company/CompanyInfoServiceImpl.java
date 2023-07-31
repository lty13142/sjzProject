package com.zsgf.platform.service.impl.company;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.company.CompanyInfoMapper;
import com.zsgf.platform.model.entity.company.CompanyInfo;
import com.zsgf.platform.service.company.CompanyInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企业信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class CompanyInfoServiceImpl extends ServiceImpl<CompanyInfoMapper, CompanyInfo> implements CompanyInfoService {


    /**
     * 新增企业信息
     *
     * @param companyInfo 企业信息
     * @return 结果
     */
    @Override
    public boolean saveCompanyInfo(CompanyInfo companyInfo) {
        return this.save(companyInfo);
    }

    /**
     * 修改企业信息
     *
     * @param companyInfo 企业信息
     * @return 结果
     */
    @Override
    public boolean updateCompanyInfo(CompanyInfo companyInfo) {
        return this.updateById(companyInfo);
    }

    /**
     * 删除企业信息信息
     *
     * @param id 企业信息ID
     * @return 结果
     */
    @Override
    public boolean deleteCompanyInfo(String id) {
        return this.removeById(id);
    }

    /**
     * 查询企业信息
     *
     * @param id 企业信息ID
     * @return 企业信息
     */
    @Override
    public CompanyInfo findCompanyInfoById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询企业信息列表
     *
     * @param companyInfo 企业信息
     * @return 企业信息
     */
    @Override
    public List<CompanyInfo> findCompanyInfoList(CompanyInfo companyInfo) {
        LambdaQueryWrapper<CompanyInfo> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询企业信息
     *
     * @param page        分页参数
     * @param companyInfo 企业信息
     * @return 企业信息
     */
    @Override
    public PageT<CompanyInfo> findCompanyInfoPage(PageT<CompanyInfo> page, CompanyInfo companyInfo) {
        LambdaQueryWrapper<CompanyInfo> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
