package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanWasteGenerationOverviewMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteGenerationOverview;
import com.zsgf.platform.service.yearPlan.YearPlanWasteGenerationOverviewService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_02危险废物产生概况信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanWasteGenerationOverviewServiceImpl extends ServiceImpl<YearPlanWasteGenerationOverviewMapper, YearPlanWasteGenerationOverview>
        implements YearPlanWasteGenerationOverviewService {


    /**
     * 新增危险废物_管理计划_02危险废物产生概况信息
     *
     * @param yearPlanWasteGenerationOverview 危险废物_管理计划_02危险废物产生概况信息
     * @return 结果
     */
    @Override
    public boolean saveYearPlanWasteGenerationOverview(YearPlanWasteGenerationOverview yearPlanWasteGenerationOverview) {
        return this.save(yearPlanWasteGenerationOverview);
    }

    /**
     * 修改危险废物_管理计划_02危险废物产生概况信息
     *
     * @param yearPlanWasteGenerationOverview 危险废物_管理计划_02危险废物产生概况信息
     * @return 结果
     */
    @Override
    public boolean updateYearPlanWasteGenerationOverview(YearPlanWasteGenerationOverview yearPlanWasteGenerationOverview) {
        return this.updateById(yearPlanWasteGenerationOverview);
    }

    /**
     * 删除危险废物_管理计划_02危险废物产生概况信息信息
     *
     * @param id 危险废物_管理计划_02危险废物产生概况信息ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanWasteGenerationOverview(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_02危险废物产生概况信息
     *
     * @param id 危险废物_管理计划_02危险废物产生概况信息ID
     * @return 危险废物_管理计划_02危险废物产生概况信息
     */
    @Override
    public YearPlanWasteGenerationOverview findYearPlanWasteGenerationOverviewById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_02危险废物产生概况信息列表
     *
     * @param yearPlanWasteGenerationOverview 危险废物_管理计划_02危险废物产生概况信息
     * @return 危险废物_管理计划_02危险废物产生概况信息
     */
    @Override
    public List<YearPlanWasteGenerationOverview> findYearPlanWasteGenerationOverviewList(YearPlanWasteGenerationOverview yearPlanWasteGenerationOverview) {
        LambdaQueryWrapper<YearPlanWasteGenerationOverview> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_02危险废物产生概况信息
     *
     * @param page                            分页参数
     * @param yearPlanWasteGenerationOverview 危险废物_管理计划_02危险废物产生概况信息
     * @return 危险废物_管理计划_02危险废物产生概况信息
     */
    @Override
    public PageT<YearPlanWasteGenerationOverview> findYearPlanWasteGenerationOverviewPage(PageT<YearPlanWasteGenerationOverview> page, YearPlanWasteGenerationOverview yearPlanWasteGenerationOverview) {
        LambdaQueryWrapper<YearPlanWasteGenerationOverview> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
