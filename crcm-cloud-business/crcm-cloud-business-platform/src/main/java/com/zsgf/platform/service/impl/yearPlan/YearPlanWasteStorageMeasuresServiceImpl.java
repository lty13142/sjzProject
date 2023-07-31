package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanWasteStorageMeasuresMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteStorageMeasures;
import com.zsgf.platform.service.yearPlan.YearPlanWasteStorageMeasuresService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_07危险废物贮存措施Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanWasteStorageMeasuresServiceImpl extends ServiceImpl<YearPlanWasteStorageMeasuresMapper, YearPlanWasteStorageMeasures>
        implements YearPlanWasteStorageMeasuresService {


    /**
     * 新增危险废物_管理计划_07危险废物贮存措施
     *
     * @param yearPlanWasteStorageMeasures 危险废物_管理计划_07危险废物贮存措施
     * @return 结果
     */
    @Override
    public boolean saveYearPlanWasteStorageMeasures(YearPlanWasteStorageMeasures yearPlanWasteStorageMeasures) {
        return this.save(yearPlanWasteStorageMeasures);
    }

    /**
     * 修改危险废物_管理计划_07危险废物贮存措施
     *
     * @param yearPlanWasteStorageMeasures 危险废物_管理计划_07危险废物贮存措施
     * @return 结果
     */
    @Override
    public boolean updateYearPlanWasteStorageMeasures(YearPlanWasteStorageMeasures yearPlanWasteStorageMeasures) {
        return this.updateById(yearPlanWasteStorageMeasures);
    }

    /**
     * 删除危险废物_管理计划_07危险废物贮存措施信息
     *
     * @param id 危险废物_管理计划_07危险废物贮存措施ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanWasteStorageMeasures(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_07危险废物贮存措施
     *
     * @param id 危险废物_管理计划_07危险废物贮存措施ID
     * @return 危险废物_管理计划_07危险废物贮存措施
     */
    @Override
    public YearPlanWasteStorageMeasures findYearPlanWasteStorageMeasuresById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_07危险废物贮存措施列表
     *
     * @param yearPlanWasteStorageMeasures 危险废物_管理计划_07危险废物贮存措施
     * @return 危险废物_管理计划_07危险废物贮存措施
     */
    @Override
    public List<YearPlanWasteStorageMeasures> findYearPlanWasteStorageMeasuresList(YearPlanWasteStorageMeasures yearPlanWasteStorageMeasures) {
        LambdaQueryWrapper<YearPlanWasteStorageMeasures> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_07危险废物贮存措施
     *
     * @param page                         分页参数
     * @param yearPlanWasteStorageMeasures 危险废物_管理计划_07危险废物贮存措施
     * @return 危险废物_管理计划_07危险废物贮存措施
     */
    @Override
    public PageT<YearPlanWasteStorageMeasures> findYearPlanWasteStorageMeasuresPage(PageT<YearPlanWasteStorageMeasures> page, YearPlanWasteStorageMeasures yearPlanWasteStorageMeasures) {
        LambdaQueryWrapper<YearPlanWasteStorageMeasures> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
