package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanWasteStorageFacilitiesStatusMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteStorageFacilitiesStatus;
import com.zsgf.platform.service.yearPlan.YearPlanWasteStorageFacilitiesStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_08危险废物贮存设施现状Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanWasteStorageFacilitiesStatusServiceImpl
        extends ServiceImpl<YearPlanWasteStorageFacilitiesStatusMapper, YearPlanWasteStorageFacilitiesStatus>
        implements YearPlanWasteStorageFacilitiesStatusService {


    /**
     * 新增危险废物_管理计划_08危险废物贮存设施现状
     *
     * @param yearPlanWasteStorageFacilitiesStatus 危险废物_管理计划_08危险废物贮存设施现状
     * @return 结果
     */
    @Override
    public boolean saveYearPlanWasteStorageFacilitiesStatus(YearPlanWasteStorageFacilitiesStatus yearPlanWasteStorageFacilitiesStatus) {
        return this.save(yearPlanWasteStorageFacilitiesStatus);
    }

    /**
     * 修改危险废物_管理计划_08危险废物贮存设施现状
     *
     * @param yearPlanWasteStorageFacilitiesStatus 危险废物_管理计划_08危险废物贮存设施现状
     * @return 结果
     */
    @Override
    public boolean updateYearPlanWasteStorageFacilitiesStatus(YearPlanWasteStorageFacilitiesStatus yearPlanWasteStorageFacilitiesStatus) {
        return this.updateById(yearPlanWasteStorageFacilitiesStatus);
    }

    /**
     * 删除危险废物_管理计划_08危险废物贮存设施现状信息
     *
     * @param id 危险废物_管理计划_08危险废物贮存设施现状ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanWasteStorageFacilitiesStatus(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_08危险废物贮存设施现状
     *
     * @param id 危险废物_管理计划_08危险废物贮存设施现状ID
     * @return 危险废物_管理计划_08危险废物贮存设施现状
     */
    @Override
    public YearPlanWasteStorageFacilitiesStatus findYearPlanWasteStorageFacilitiesStatusById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_08危险废物贮存设施现状列表
     *
     * @param yearPlanWasteStorageFacilitiesStatus 危险废物_管理计划_08危险废物贮存设施现状
     * @return 危险废物_管理计划_08危险废物贮存设施现状
     */
    @Override
    public List<YearPlanWasteStorageFacilitiesStatus> findYearPlanWasteStorageFacilitiesStatusList(YearPlanWasteStorageFacilitiesStatus yearPlanWasteStorageFacilitiesStatus) {
        LambdaQueryWrapper<YearPlanWasteStorageFacilitiesStatus> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_08危险废物贮存设施现状
     *
     * @param page                                 分页参数
     * @param yearPlanWasteStorageFacilitiesStatus 危险废物_管理计划_08危险废物贮存设施现状
     * @return 危险废物_管理计划_08危险废物贮存设施现状
     */
    @Override
    public PageT<YearPlanWasteStorageFacilitiesStatus> findYearPlanWasteStorageFacilitiesStatusPage(PageT<YearPlanWasteStorageFacilitiesStatus> page, YearPlanWasteStorageFacilitiesStatus yearPlanWasteStorageFacilitiesStatus) {
        LambdaQueryWrapper<YearPlanWasteStorageFacilitiesStatus> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
