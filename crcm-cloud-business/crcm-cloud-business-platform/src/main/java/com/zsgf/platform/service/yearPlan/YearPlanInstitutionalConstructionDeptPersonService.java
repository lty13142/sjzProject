package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanInstitutionalConstructionDeptPerson;

import java.util.List;

/**
 * 危险废物_管理计划_11制度建设部门人员信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanInstitutionalConstructionDeptPersonService extends IService<YearPlanInstitutionalConstructionDeptPerson> {

    /**
     * 新增危险废物_管理计划_11制度建设部门人员信息
     *
     * @param yearPlanInstitutionalConstructionDeptPerson 危险废物_管理计划_11制度建设部门人员信息
     * @return 结果
     */
    boolean saveYearPlanInstitutionalConstructionDeptPerson(YearPlanInstitutionalConstructionDeptPerson yearPlanInstitutionalConstructionDeptPerson);

    /**
     * 修改危险废物_管理计划_11制度建设部门人员信息
     *
     * @param yearPlanInstitutionalConstructionDeptPerson 危险废物_管理计划_11制度建设部门人员信息
     * @return 结果
     */
    boolean updateYearPlanInstitutionalConstructionDeptPerson(YearPlanInstitutionalConstructionDeptPerson yearPlanInstitutionalConstructionDeptPerson);

    /**
     * 删除危险废物_管理计划_11制度建设部门人员信息信息
     *
     * @param id 危险废物_管理计划_11制度建设部门人员信息ID
     * @return 结果
     */
    boolean deleteYearPlanInstitutionalConstructionDeptPerson(String id);

    /**
     * 查询危险废物_管理计划_11制度建设部门人员信息
     *
     * @param id 危险废物_管理计划_11制度建设部门人员信息ID
     * @return 危险废物_管理计划_11制度建设部门人员信息
     */
    YearPlanInstitutionalConstructionDeptPerson findYearPlanInstitutionalConstructionDeptPersonById(String id);

    /**
     * 查询危险废物_管理计划_11制度建设部门人员信息列表
     *
     * @param yearPlanInstitutionalConstructionDeptPerson 危险废物_管理计划_11制度建设部门人员信息
     * @return 危险废物_管理计划_11制度建设部门人员信息集合
     */
    List<YearPlanInstitutionalConstructionDeptPerson> findYearPlanInstitutionalConstructionDeptPersonList(YearPlanInstitutionalConstructionDeptPerson yearPlanInstitutionalConstructionDeptPerson);

    /**
     * 分页查询危险废物_管理计划_11制度建设部门人员信息列表
     *
     * @param page                                        分页参数
     * @param yearPlanInstitutionalConstructionDeptPerson 危险废物_管理计划_11制度建设部门人员信息
     * @return 危险废物_管理计划_11制度建设部门人员信息集合
     */
    PageT<YearPlanInstitutionalConstructionDeptPerson> findYearPlanInstitutionalConstructionDeptPersonPage(PageT<YearPlanInstitutionalConstructionDeptPerson> page, YearPlanInstitutionalConstructionDeptPerson yearPlanInstitutionalConstructionDeptPerson);
}
