package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanProductYieldMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanProductYield;
import com.zsgf.platform.service.yearPlan.YearPlanProductYieldService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_15产品生产产量Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanProductYieldServiceImpl extends ServiceImpl<YearPlanProductYieldMapper, YearPlanProductYield>
        implements YearPlanProductYieldService {


    /**
     * 新增危险废物_管理计划_15产品生产产量
     *
     * @param yearPlanProductYield 危险废物_管理计划_15产品生产产量
     * @return 结果
     */
    @Override
    public boolean saveYearPlanProductYield(YearPlanProductYield yearPlanProductYield) {
        return this.save(yearPlanProductYield);
    }

    /**
     * 修改危险废物_管理计划_15产品生产产量
     *
     * @param yearPlanProductYield 危险废物_管理计划_15产品生产产量
     * @return 结果
     */
    @Override
    public boolean updateYearPlanProductYield(YearPlanProductYield yearPlanProductYield) {
        return this.updateById(yearPlanProductYield);
    }

    /**
     * 删除危险废物_管理计划_15产品生产产量信息
     *
     * @param id 危险废物_管理计划_15产品生产产量ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanProductYield(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_15产品生产产量
     *
     * @param id 危险废物_管理计划_15产品生产产量ID
     * @return 危险废物_管理计划_15产品生产产量
     */
    @Override
    public YearPlanProductYield findYearPlanProductYieldById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_15产品生产产量列表
     *
     * @param yearPlanProductYield 危险废物_管理计划_15产品生产产量
     * @return 危险废物_管理计划_15产品生产产量
     */
    @Override
    public List<YearPlanProductYield> findYearPlanProductYieldList(YearPlanProductYield yearPlanProductYield) {
        LambdaQueryWrapper<YearPlanProductYield> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_15产品生产产量
     *
     * @param page                 分页参数
     * @param yearPlanProductYield 危险废物_管理计划_15产品生产产量
     * @return 危险废物_管理计划_15产品生产产量
     */
    @Override
    public PageT<YearPlanProductYield> findYearPlanProductYieldPage(PageT<YearPlanProductYield> page, YearPlanProductYield yearPlanProductYield) {
        LambdaQueryWrapper<YearPlanProductYield> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
