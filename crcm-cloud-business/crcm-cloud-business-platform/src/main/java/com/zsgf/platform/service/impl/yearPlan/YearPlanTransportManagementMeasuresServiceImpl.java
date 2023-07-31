package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanTransportManagementMeasuresMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanTransportManagementMeasures;
import com.zsgf.platform.service.yearPlan.YearPlanTransportManagementMeasuresService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_10运输管理措施Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanTransportManagementMeasuresServiceImpl
        extends ServiceImpl<YearPlanTransportManagementMeasuresMapper, YearPlanTransportManagementMeasures>
        implements YearPlanTransportManagementMeasuresService {


    /**
     * 新增危险废物_管理计划_10运输管理措施
     *
     * @param yearPlanTransportManagementMeasures 危险废物_管理计划_10运输管理措施
     * @return 结果
     */
    @Override
    public boolean saveYearPlanTransportManagementMeasures(YearPlanTransportManagementMeasures yearPlanTransportManagementMeasures) {
        return this.save(yearPlanTransportManagementMeasures);
    }

    /**
     * 修改危险废物_管理计划_10运输管理措施
     *
     * @param yearPlanTransportManagementMeasures 危险废物_管理计划_10运输管理措施
     * @return 结果
     */
    @Override
    public boolean updateYearPlanTransportManagementMeasures(YearPlanTransportManagementMeasures yearPlanTransportManagementMeasures) {
        return this.updateById(yearPlanTransportManagementMeasures);
    }

    /**
     * 删除危险废物_管理计划_10运输管理措施信息
     *
     * @param id 危险废物_管理计划_10运输管理措施ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanTransportManagementMeasures(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_10运输管理措施
     *
     * @param id 危险废物_管理计划_10运输管理措施ID
     * @return 危险废物_管理计划_10运输管理措施
     */
    @Override
    public YearPlanTransportManagementMeasures findYearPlanTransportManagementMeasuresById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_10运输管理措施列表
     *
     * @param yearPlanTransportManagementMeasures 危险废物_管理计划_10运输管理措施
     * @return 危险废物_管理计划_10运输管理措施
     */
    @Override
    public List<YearPlanTransportManagementMeasures> findYearPlanTransportManagementMeasuresList(YearPlanTransportManagementMeasures yearPlanTransportManagementMeasures) {
        LambdaQueryWrapper<YearPlanTransportManagementMeasures> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_10运输管理措施
     *
     * @param page                                分页参数
     * @param yearPlanTransportManagementMeasures 危险废物_管理计划_10运输管理措施
     * @return 危险废物_管理计划_10运输管理措施
     */
    @Override
    public PageT<YearPlanTransportManagementMeasures> findYearPlanTransportManagementMeasuresPage(PageT<YearPlanTransportManagementMeasures> page, YearPlanTransportManagementMeasures yearPlanTransportManagementMeasures) {
        LambdaQueryWrapper<YearPlanTransportManagementMeasures> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
