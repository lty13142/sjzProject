package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanInstitutionalConstructionRulesMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanInstitutionalConstructionRules;
import com.zsgf.platform.service.yearPlan.YearPlanInstitutionalConstructionRulesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_12制度建设规章制度情况Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanInstitutionalConstructionRulesServiceImpl
        extends ServiceImpl<YearPlanInstitutionalConstructionRulesMapper, YearPlanInstitutionalConstructionRules>
        implements YearPlanInstitutionalConstructionRulesService {


    /**
     * 新增危险废物_管理计划_12制度建设规章制度情况
     *
     * @param yearPlanInstitutionalConstructionRules 危险废物_管理计划_12制度建设规章制度情况
     * @return 结果
     */
    @Override
    public boolean saveYearPlanInstitutionalConstructionRules(YearPlanInstitutionalConstructionRules yearPlanInstitutionalConstructionRules) {
        return this.save(yearPlanInstitutionalConstructionRules);
    }

    /**
     * 修改危险废物_管理计划_12制度建设规章制度情况
     *
     * @param yearPlanInstitutionalConstructionRules 危险废物_管理计划_12制度建设规章制度情况
     * @return 结果
     */
    @Override
    public boolean updateYearPlanInstitutionalConstructionRules(YearPlanInstitutionalConstructionRules yearPlanInstitutionalConstructionRules) {
        return this.updateById(yearPlanInstitutionalConstructionRules);
    }

    /**
     * 删除危险废物_管理计划_12制度建设规章制度情况信息
     *
     * @param id 危险废物_管理计划_12制度建设规章制度情况ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanInstitutionalConstructionRules(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_12制度建设规章制度情况
     *
     * @param id 危险废物_管理计划_12制度建设规章制度情况ID
     * @return 危险废物_管理计划_12制度建设规章制度情况
     */
    @Override
    public YearPlanInstitutionalConstructionRules findYearPlanInstitutionalConstructionRulesById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_12制度建设规章制度情况列表
     *
     * @param yearPlanInstitutionalConstructionRules 危险废物_管理计划_12制度建设规章制度情况
     * @return 危险废物_管理计划_12制度建设规章制度情况
     */
    @Override
    public List<YearPlanInstitutionalConstructionRules> findYearPlanInstitutionalConstructionRulesList(YearPlanInstitutionalConstructionRules yearPlanInstitutionalConstructionRules) {
        LambdaQueryWrapper<YearPlanInstitutionalConstructionRules> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_12制度建设规章制度情况
     *
     * @param page                                   分页参数
     * @param yearPlanInstitutionalConstructionRules 危险废物_管理计划_12制度建设规章制度情况
     * @return 危险废物_管理计划_12制度建设规章制度情况
     */
    @Override
    public PageT<YearPlanInstitutionalConstructionRules> findYearPlanInstitutionalConstructionRulesPage(PageT<YearPlanInstitutionalConstructionRules> page, YearPlanInstitutionalConstructionRules yearPlanInstitutionalConstructionRules) {
        LambdaQueryWrapper<YearPlanInstitutionalConstructionRules> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
