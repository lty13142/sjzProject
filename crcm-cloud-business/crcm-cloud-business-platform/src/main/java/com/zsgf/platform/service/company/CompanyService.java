package com.zsgf.platform.service.company;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.company.Company;
import com.zsgf.platform.vo.company.CompanySelectVo;

import java.util.List;

/**
 * 企业基本信息Service接口
 *
 * @author zzyt
 * @date 2023-02-22
 */
public interface CompanyService extends IService<Company> {

    /**
     * 新增企业基本信息
     *
     * @param company 企业基本信息
     * @return 结果
     */
    boolean saveCompany(Company company);

    /**
     * 修改企业基本信息
     *
     * @param company 企业基本信息
     * @return 结果
     */
    boolean updateCompany(Company company);

    /**
     * 删除企业基本信息信息
     *
     * @param id 企业基本信息ID
     * @return 结果
     */
    boolean deleteCompany(String id);

    /**
     * 查询企业基本信息
     *
     * @param id 企业基本信息ID
     * @return 企业基本信息
     */
    Company findCompanyById(String id);

    /**
     * 查询企业基本信息列表
     *
     * @param company 企业基本信息
     * @return 企业基本信息集合
     */
    List<Company> findCompanyList(Company company);

    /**
     * 分页查询企业基本信息列表
     *
     * @param page    分页参数
     * @param company 企业基本信息
     * @return 企业基本信息集合
     */
    PageT<Company> findCompanyPage(PageT<Company> page, Company company);

    List<CompanySelectVo> getSelectList(String companyLabel);
}
