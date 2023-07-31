package com.zsgf.platform.service.company;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.company.CompanyInfo;

import java.util.List;

/**
 * 企业信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface CompanyInfoService extends IService<CompanyInfo> {

    /**
     * 新增企业信息
     *
     * @param companyInfo 企业信息
     * @return 结果
     */
    boolean saveCompanyInfo(CompanyInfo companyInfo);

    /**
     * 修改企业信息
     *
     * @param companyInfo 企业信息
     * @return 结果
     */
    boolean updateCompanyInfo(CompanyInfo companyInfo);

    /**
     * 删除企业信息信息
     *
     * @param id 企业信息ID
     * @return 结果
     */
    boolean deleteCompanyInfo(String id);

    /**
     * 查询企业信息
     *
     * @param id 企业信息ID
     * @return 企业信息
     */
    CompanyInfo findCompanyInfoById(String id);

    /**
     * 查询企业信息列表
     *
     * @param companyInfo 企业信息
     * @return 企业信息集合
     */
    List<CompanyInfo> findCompanyInfoList(CompanyInfo companyInfo);

    /**
     * 分页查询企业信息列表
     *
     * @param page        分页参数
     * @param companyInfo 企业信息
     * @return 企业信息集合
     */
    PageT<CompanyInfo> findCompanyInfoPage(PageT<CompanyInfo> page, CompanyInfo companyInfo);
}
