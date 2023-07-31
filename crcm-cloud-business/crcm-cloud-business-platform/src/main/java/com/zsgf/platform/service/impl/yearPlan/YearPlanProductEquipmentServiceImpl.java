package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanProductEquipmentMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanProductEquipment;
import com.zsgf.platform.service.yearPlan.YearPlanProductEquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_14产品生产主要生产设备Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanProductEquipmentServiceImpl extends ServiceImpl<YearPlanProductEquipmentMapper, YearPlanProductEquipment>
        implements YearPlanProductEquipmentService {


    /**
     * 新增危险废物_管理计划_14产品生产主要生产设备
     *
     * @param yearPlanProductEquipment 危险废物_管理计划_14产品生产主要生产设备
     * @return 结果
     */
    @Override
    public boolean saveYearPlanProductEquipment(YearPlanProductEquipment yearPlanProductEquipment) {
        return this.save(yearPlanProductEquipment);
    }

    /**
     * 修改危险废物_管理计划_14产品生产主要生产设备
     *
     * @param yearPlanProductEquipment 危险废物_管理计划_14产品生产主要生产设备
     * @return 结果
     */
    @Override
    public boolean updateYearPlanProductEquipment(YearPlanProductEquipment yearPlanProductEquipment) {
        return this.updateById(yearPlanProductEquipment);
    }

    /**
     * 删除危险废物_管理计划_14产品生产主要生产设备信息
     *
     * @param id 危险废物_管理计划_14产品生产主要生产设备ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanProductEquipment(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_14产品生产主要生产设备
     *
     * @param id 危险废物_管理计划_14产品生产主要生产设备ID
     * @return 危险废物_管理计划_14产品生产主要生产设备
     */
    @Override
    public YearPlanProductEquipment findYearPlanProductEquipmentById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_14产品生产主要生产设备列表
     *
     * @param yearPlanProductEquipment 危险废物_管理计划_14产品生产主要生产设备
     * @return 危险废物_管理计划_14产品生产主要生产设备
     */
    @Override
    public List<YearPlanProductEquipment> findYearPlanProductEquipmentList(YearPlanProductEquipment yearPlanProductEquipment) {
        LambdaQueryWrapper<YearPlanProductEquipment> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_14产品生产主要生产设备
     *
     * @param page                     分页参数
     * @param yearPlanProductEquipment 危险废物_管理计划_14产品生产主要生产设备
     * @return 危险废物_管理计划_14产品生产主要生产设备
     */
    @Override
    public PageT<YearPlanProductEquipment> findYearPlanProductEquipmentPage(PageT<YearPlanProductEquipment> page, YearPlanProductEquipment yearPlanProductEquipment) {
        LambdaQueryWrapper<YearPlanProductEquipment> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
