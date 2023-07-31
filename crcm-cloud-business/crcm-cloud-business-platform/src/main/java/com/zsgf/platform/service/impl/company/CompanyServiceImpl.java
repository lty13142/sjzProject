package com.zsgf.platform.service.impl.company;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.company.CompanyMapper;
import com.zsgf.platform.model.entity.company.Company;
import com.zsgf.platform.service.company.CompanyService;
import com.zsgf.platform.vo.company.CompanySelectVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企业基本信息Service业务层处理
 *
 * @author zzyt
 * @date 2023-02-22
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {


    /**
     * 新增企业基本信息
     *
     * @param company 企业基本信息
     * @return 结果
     */
    @Override
    public boolean saveCompany(Company company) {
        return this.save(company);
    }

    /**
     * 修改企业基本信息
     *
     * @param company 企业基本信息
     * @return 结果
     */
    @Override
    public boolean updateCompany(Company company) {
        return this.updateById(company);
    }

    /**
     * 删除企业基本信息信息
     *
     * @param id 企业基本信息ID
     * @return 结果
     */
    @Override
    public boolean deleteCompany(String id) {
        return this.removeById(id);
    }

    /**
     * 查询企业基本信息
     *
     * @param id 企业基本信息ID
     * @return 企业基本信息
     */
    @Override
    public Company findCompanyById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询企业基本信息列表
     *
     * @param company 企业基本信息
     * @return 企业基本信息
     */
    @Override
    public List<Company> findCompanyList(Company company) {
        LambdaQueryWrapper<Company> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询企业基本信息
     *
     * @param page    分页参数
     * @param company 企业基本信息
     * @return 企业基本信息
     */
    @Override
    public PageT<Company> findCompanyPage(PageT<Company> page, Company company) {
        LambdaQueryWrapper<Company> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }

    @Override
    public List<CompanySelectVo> getSelectList(String companyLabel) {
        return this.baseMapper.getSelectList(companyLabel);
    }
}
