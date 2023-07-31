package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanInstitutionalConstructionDeptPersonMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanInstitutionalConstructionDeptPerson;
import com.zsgf.platform.service.yearPlan.YearPlanInstitutionalConstructionDeptPersonService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_11制度建设部门人员信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanInstitutionalConstructionDeptPersonServiceImpl
        extends ServiceImpl<YearPlanInstitutionalConstructionDeptPersonMapper, YearPlanInstitutionalConstructionDeptPerson>
        implements YearPlanInstitutionalConstructionDeptPersonService {


    /**
     * 新增危险废物_管理计划_11制度建设部门人员信息
     *
     * @param yearPlanInstitutionalConstructionDeptPerson 危险废物_管理计划_11制度建设部门人员信息
     * @return 结果
     */
    @Override
    public boolean saveYearPlanInstitutionalConstructionDeptPerson(YearPlanInstitutionalConstructionDeptPerson yearPlanInstitutionalConstructionDeptPerson) {
        return this.save(yearPlanInstitutionalConstructionDeptPerson);
    }

    /**
     * 修改危险废物_管理计划_11制度建设部门人员信息
     *
     * @param yearPlanInstitutionalConstructionDeptPerson 危险废物_管理计划_11制度建设部门人员信息
     * @return 结果
     */
    @Override
    public boolean updateYearPlanInstitutionalConstructionDeptPerson(YearPlanInstitutionalConstructionDeptPerson yearPlanInstitutionalConstructionDeptPerson) {
        return this.updateById(yearPlanInstitutionalConstructionDeptPerson);
    }

    /**
     * 删除危险废物_管理计划_11制度建设部门人员信息信息
     *
     * @param id 危险废物_管理计划_11制度建设部门人员信息ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanInstitutionalConstructionDeptPerson(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_11制度建设部门人员信息
     *
     * @param id 危险废物_管理计划_11制度建设部门人员信息ID
     * @return 危险废物_管理计划_11制度建设部门人员信息
     */
    @Override
    public YearPlanInstitutionalConstructionDeptPerson findYearPlanInstitutionalConstructionDeptPersonById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_11制度建设部门人员信息列表
     *
     * @param yearPlanInstitutionalConstructionDeptPerson 危险废物_管理计划_11制度建设部门人员信息
     * @return 危险废物_管理计划_11制度建设部门人员信息
     */
    @Override
    public List<YearPlanInstitutionalConstructionDeptPerson> findYearPlanInstitutionalConstructionDeptPersonList(YearPlanInstitutionalConstructionDeptPerson yearPlanInstitutionalConstructionDeptPerson) {
        LambdaQueryWrapper<YearPlanInstitutionalConstructionDeptPerson> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_11制度建设部门人员信息
     *
     * @param page                                        分页参数
     * @param yearPlanInstitutionalConstructionDeptPerson 危险废物_管理计划_11制度建设部门人员信息
     * @return 危险废物_管理计划_11制度建设部门人员信息
     */
    @Override
    public PageT<YearPlanInstitutionalConstructionDeptPerson> findYearPlanInstitutionalConstructionDeptPersonPage(PageT<YearPlanInstitutionalConstructionDeptPerson> page, YearPlanInstitutionalConstructionDeptPerson yearPlanInstitutionalConstructionDeptPerson) {
        LambdaQueryWrapper<YearPlanInstitutionalConstructionDeptPerson> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
