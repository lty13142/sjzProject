package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanInstitutionalConstructionRules;

import java.util.List;

/**
 * 危险废物_管理计划_12制度建设规章制度情况Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanInstitutionalConstructionRulesService extends IService<YearPlanInstitutionalConstructionRules> {

    /**
     * 新增危险废物_管理计划_12制度建设规章制度情况
     *
     * @param yearPlanInstitutionalConstructionRules 危险废物_管理计划_12制度建设规章制度情况
     * @return 结果
     */
    boolean saveYearPlanInstitutionalConstructionRules(YearPlanInstitutionalConstructionRules yearPlanInstitutionalConstructionRules);

    /**
     * 修改危险废物_管理计划_12制度建设规章制度情况
     *
     * @param yearPlanInstitutionalConstructionRules 危险废物_管理计划_12制度建设规章制度情况
     * @return 结果
     */
    boolean updateYearPlanInstitutionalConstructionRules(YearPlanInstitutionalConstructionRules yearPlanInstitutionalConstructionRules);

    /**
     * 删除危险废物_管理计划_12制度建设规章制度情况信息
     *
     * @param id 危险废物_管理计划_12制度建设规章制度情况ID
     * @return 结果
     */
    boolean deleteYearPlanInstitutionalConstructionRules(String id);

    /**
     * 查询危险废物_管理计划_12制度建设规章制度情况
     *
     * @param id 危险废物_管理计划_12制度建设规章制度情况ID
     * @return 危险废物_管理计划_12制度建设规章制度情况
     */
    YearPlanInstitutionalConstructionRules findYearPlanInstitutionalConstructionRulesById(String id);

    /**
     * 查询危险废物_管理计划_12制度建设规章制度情况列表
     *
     * @param yearPlanInstitutionalConstructionRules 危险废物_管理计划_12制度建设规章制度情况
     * @return 危险废物_管理计划_12制度建设规章制度情况集合
     */
    List<YearPlanInstitutionalConstructionRules> findYearPlanInstitutionalConstructionRulesList(YearPlanInstitutionalConstructionRules yearPlanInstitutionalConstructionRules);

    /**
     * 分页查询危险废物_管理计划_12制度建设规章制度情况列表
     *
     * @param page                                   分页参数
     * @param yearPlanInstitutionalConstructionRules 危险废物_管理计划_12制度建设规章制度情况
     * @return 危险废物_管理计划_12制度建设规章制度情况集合
     */
    PageT<YearPlanInstitutionalConstructionRules> findYearPlanInstitutionalConstructionRulesPage(PageT<YearPlanInstitutionalConstructionRules> page, YearPlanInstitutionalConstructionRules yearPlanInstitutionalConstructionRules);
}
