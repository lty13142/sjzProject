package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanMinimizationPlanMeasuresMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanMinimizationPlanMeasures;
import com.zsgf.platform.service.yearPlan.YearPlanMinimizationPlanMeasuresService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_19减量化计划和措施Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanMinimizationPlanMeasuresServiceImpl extends ServiceImpl<YearPlanMinimizationPlanMeasuresMapper, YearPlanMinimizationPlanMeasures>
        implements YearPlanMinimizationPlanMeasuresService {


    /**
     * 新增危险废物_管理计划_19减量化计划和措施
     *
     * @param yearPlanMinimizationPlanMeasures 危险废物_管理计划_19减量化计划和措施
     * @return 结果
     */
    @Override
    public boolean saveYearPlanMinimizationPlanMeasures(YearPlanMinimizationPlanMeasures yearPlanMinimizationPlanMeasures) {
        return this.save(yearPlanMinimizationPlanMeasures);
    }

    /**
     * 修改危险废物_管理计划_19减量化计划和措施
     *
     * @param yearPlanMinimizationPlanMeasures 危险废物_管理计划_19减量化计划和措施
     * @return 结果
     */
    @Override
    public boolean updateYearPlanMinimizationPlanMeasures(YearPlanMinimizationPlanMeasures yearPlanMinimizationPlanMeasures) {
        return this.updateById(yearPlanMinimizationPlanMeasures);
    }

    /**
     * 删除危险废物_管理计划_19减量化计划和措施信息
     *
     * @param id 危险废物_管理计划_19减量化计划和措施ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanMinimizationPlanMeasures(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_19减量化计划和措施
     *
     * @param id 危险废物_管理计划_19减量化计划和措施ID
     * @return 危险废物_管理计划_19减量化计划和措施
     */
    @Override
    public YearPlanMinimizationPlanMeasures findYearPlanMinimizationPlanMeasuresById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_19减量化计划和措施列表
     *
     * @param yearPlanMinimizationPlanMeasures 危险废物_管理计划_19减量化计划和措施
     * @return 危险废物_管理计划_19减量化计划和措施
     */
    @Override
    public List<YearPlanMinimizationPlanMeasures> findYearPlanMinimizationPlanMeasuresList(YearPlanMinimizationPlanMeasures yearPlanMinimizationPlanMeasures) {
        LambdaQueryWrapper<YearPlanMinimizationPlanMeasures> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_19减量化计划和措施
     *
     * @param page                             分页参数
     * @param yearPlanMinimizationPlanMeasures 危险废物_管理计划_19减量化计划和措施
     * @return 危险废物_管理计划_19减量化计划和措施
     */
    @Override
    public PageT<YearPlanMinimizationPlanMeasures> findYearPlanMinimizationPlanMeasuresPage(PageT<YearPlanMinimizationPlanMeasures> page, YearPlanMinimizationPlanMeasures yearPlanMinimizationPlanMeasures) {
        LambdaQueryWrapper<YearPlanMinimizationPlanMeasures> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
