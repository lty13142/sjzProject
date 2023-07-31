package com.zsgf.platform.service.impl.company;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.company.CompanyRoleMapper;
import com.zsgf.platform.model.entity.company.CompanyRole;
import com.zsgf.platform.service.company.CompanyRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 固废企业信息_企业角色类型Service业务层处理
 *
 * @author gzl
 * @date 2023-02-10
 */
@Service
public class CompanyRoleServiceImpl extends ServiceImpl<CompanyRoleMapper, CompanyRole> implements CompanyRoleService {


    /**
     * 新增固废企业信息_企业角色类型
     *
     * @param companyRole 固废企业信息_企业角色类型
     * @return 结果
     */
    @Override
    public boolean saveCompanyRole(CompanyRole companyRole) {
        return this.save(companyRole);
    }

    /**
     * 修改固废企业信息_企业角色类型
     *
     * @param companyRole 固废企业信息_企业角色类型
     * @return 结果
     */
    @Override
    public boolean updateCompanyRole(CompanyRole companyRole) {
        return this.updateById(companyRole);
    }

    /**
     * 删除固废企业信息_企业角色类型信息
     *
     * @param id 固废企业信息_企业角色类型ID
     * @return 结果
     */
    @Override
    public boolean deleteCompanyRole(String id) {
        return this.removeById(id);
    }

    /**
     * 查询固废企业信息_企业角色类型
     *
     * @param id 固废企业信息_企业角色类型ID
     * @return 固废企业信息_企业角色类型
     */
    @Override
    public CompanyRole findCompanyRoleById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询固废企业信息_企业角色类型列表
     *
     * @param companyRole 固废企业信息_企业角色类型
     * @return 固废企业信息_企业角色类型
     */
    @Override
    public List<CompanyRole> findCompanyRoleList(CompanyRole companyRole) {
        LambdaQueryWrapper<CompanyRole> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询固废企业信息_企业角色类型
     *
     * @param page        分页参数
     * @param companyRole 固废企业信息_企业角色类型
     * @return 固废企业信息_企业角色类型
     */
    @Override
    public PageT<CompanyRole> findCompanyRolePage(PageT<CompanyRole> page, CompanyRole companyRole) {
        LambdaQueryWrapper<CompanyRole> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }

}
