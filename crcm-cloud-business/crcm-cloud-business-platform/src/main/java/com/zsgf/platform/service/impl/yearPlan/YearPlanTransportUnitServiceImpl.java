package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanTransportUnitMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanTransportUnit;
import com.zsgf.platform.service.yearPlan.YearPlanTransportUnitService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_09运输单位信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanTransportUnitServiceImpl extends ServiceImpl<YearPlanTransportUnitMapper, YearPlanTransportUnit>
        implements YearPlanTransportUnitService {


    /**
     * 新增危险废物_管理计划_09运输单位信息
     *
     * @param yearPlanTransportUnit 危险废物_管理计划_09运输单位信息
     * @return 结果
     */
    @Override
    public boolean saveYearPlanTransportUnit(YearPlanTransportUnit yearPlanTransportUnit) {
        return this.save(yearPlanTransportUnit);
    }

    /**
     * 修改危险废物_管理计划_09运输单位信息
     *
     * @param yearPlanTransportUnit 危险废物_管理计划_09运输单位信息
     * @return 结果
     */
    @Override
    public boolean updateYearPlanTransportUnit(YearPlanTransportUnit yearPlanTransportUnit) {
        return this.updateById(yearPlanTransportUnit);
    }

    /**
     * 删除危险废物_管理计划_09运输单位信息信息
     *
     * @param id 危险废物_管理计划_09运输单位信息ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanTransportUnit(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_09运输单位信息
     *
     * @param id 危险废物_管理计划_09运输单位信息ID
     * @return 危险废物_管理计划_09运输单位信息
     */
    @Override
    public YearPlanTransportUnit findYearPlanTransportUnitById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_09运输单位信息列表
     *
     * @param yearPlanTransportUnit 危险废物_管理计划_09运输单位信息
     * @return 危险废物_管理计划_09运输单位信息
     */
    @Override
    public List<YearPlanTransportUnit> findYearPlanTransportUnitList(YearPlanTransportUnit yearPlanTransportUnit) {
        LambdaQueryWrapper<YearPlanTransportUnit> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_09运输单位信息
     *
     * @param page                  分页参数
     * @param yearPlanTransportUnit 危险废物_管理计划_09运输单位信息
     * @return 危险废物_管理计划_09运输单位信息
     */
    @Override
    public PageT<YearPlanTransportUnit> findYearPlanTransportUnitPage(PageT<YearPlanTransportUnit> page, YearPlanTransportUnit yearPlanTransportUnit) {
        LambdaQueryWrapper<YearPlanTransportUnit> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
