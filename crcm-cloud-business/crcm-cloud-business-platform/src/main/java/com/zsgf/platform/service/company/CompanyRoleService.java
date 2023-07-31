package com.zsgf.platform.service.company;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.company.CompanyRole;

import java.util.List;

/**
 * 固废企业信息_企业角色类型Service接口
 *
 * @author gzl
 * @date 2023-02-10
 */
public interface CompanyRoleService extends IService<CompanyRole> {

    /**
     * 新增固废企业信息_企业角色类型
     *
     * @param companyRole 固废企业信息_企业角色类型
     * @return 结果
     */
    boolean saveCompanyRole(CompanyRole companyRole);

    /**
     * 修改固废企业信息_企业角色类型
     *
     * @param companyRole 固废企业信息_企业角色类型
     * @return 结果
     */
    boolean updateCompanyRole(CompanyRole companyRole);

    /**
     * 删除固废企业信息_企业角色类型信息
     *
     * @param id 固废企业信息_企业角色类型ID
     * @return 结果
     */
    boolean deleteCompanyRole(String id);

    /**
     * 查询固废企业信息_企业角色类型
     *
     * @param id 固废企业信息_企业角色类型ID
     * @return 固废企业信息_企业角色类型
     */
    CompanyRole findCompanyRoleById(String id);

    /**
     * 查询固废企业信息_企业角色类型列表
     *
     * @param companyRole 固废企业信息_企业角色类型
     * @return 固废企业信息_企业角色类型集合
     */
    List<CompanyRole> findCompanyRoleList(CompanyRole companyRole);

    /**
     * 分页查询固废企业信息_企业角色类型列表
     *
     * @param page        分页参数
     * @param companyRole 固废企业信息_企业角色类型
     * @return 固废企业信息_企业角色类型集合
     */
    PageT<CompanyRole> findCompanyRolePage(PageT<CompanyRole> page, CompanyRole companyRole);
}
