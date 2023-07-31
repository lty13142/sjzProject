package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanEnvironmentMonitoringMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanEnvironmentMonitoring;
import com.zsgf.platform.service.yearPlan.YearPlanEnvironmentMonitoringService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_17环境监测情况Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanEnvironmentMonitoringServiceImpl extends ServiceImpl<YearPlanEnvironmentMonitoringMapper, YearPlanEnvironmentMonitoring>
        implements YearPlanEnvironmentMonitoringService {


    /**
     * 新增危险废物_管理计划_17环境监测情况
     *
     * @param yearPlanEnvironmentMonitoring 危险废物_管理计划_17环境监测情况
     * @return 结果
     */
    @Override
    public boolean saveYearPlanEnvironmentMonitoring(YearPlanEnvironmentMonitoring yearPlanEnvironmentMonitoring) {
        return this.save(yearPlanEnvironmentMonitoring);
    }

    /**
     * 修改危险废物_管理计划_17环境监测情况
     *
     * @param yearPlanEnvironmentMonitoring 危险废物_管理计划_17环境监测情况
     * @return 结果
     */
    @Override
    public boolean updateYearPlanEnvironmentMonitoring(YearPlanEnvironmentMonitoring yearPlanEnvironmentMonitoring) {
        return this.updateById(yearPlanEnvironmentMonitoring);
    }

    /**
     * 删除危险废物_管理计划_17环境监测情况信息
     *
     * @param id 危险废物_管理计划_17环境监测情况ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanEnvironmentMonitoring(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_17环境监测情况
     *
     * @param id 危险废物_管理计划_17环境监测情况ID
     * @return 危险废物_管理计划_17环境监测情况
     */
    @Override
    public YearPlanEnvironmentMonitoring findYearPlanEnvironmentMonitoringById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_17环境监测情况列表
     *
     * @param yearPlanEnvironmentMonitoring 危险废物_管理计划_17环境监测情况
     * @return 危险废物_管理计划_17环境监测情况
     */
    @Override
    public List<YearPlanEnvironmentMonitoring> findYearPlanEnvironmentMonitoringList(YearPlanEnvironmentMonitoring yearPlanEnvironmentMonitoring) {
        LambdaQueryWrapper<YearPlanEnvironmentMonitoring> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_17环境监测情况
     *
     * @param page                          分页参数
     * @param yearPlanEnvironmentMonitoring 危险废物_管理计划_17环境监测情况
     * @return 危险废物_管理计划_17环境监测情况
     */
    @Override
    public PageT<YearPlanEnvironmentMonitoring> findYearPlanEnvironmentMonitoringPage(PageT<YearPlanEnvironmentMonitoring> page, YearPlanEnvironmentMonitoring yearPlanEnvironmentMonitoring) {
        LambdaQueryWrapper<YearPlanEnvironmentMonitoring> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
