package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanWasteSelfMeasuresMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteSelfMeasures;
import com.zsgf.platform.service.yearPlan.YearPlanWasteSelfMeasuresService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_06危废自行利用/处置措施Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanWasteSelfMeasuresServiceImpl extends ServiceImpl<YearPlanWasteSelfMeasuresMapper, YearPlanWasteSelfMeasures>
        implements YearPlanWasteSelfMeasuresService {


    /**
     * 新增危险废物_管理计划_06危废自行利用/处置措施
     *
     * @param yearPlanWasteSelfMeasures 危险废物_管理计划_06危废自行利用/处置措施
     * @return 结果
     */
    @Override
    public boolean saveYearPlanWasteSelfMeasures(YearPlanWasteSelfMeasures yearPlanWasteSelfMeasures) {
        return this.save(yearPlanWasteSelfMeasures);
    }

    /**
     * 修改危险废物_管理计划_06危废自行利用/处置措施
     *
     * @param yearPlanWasteSelfMeasures 危险废物_管理计划_06危废自行利用/处置措施
     * @return 结果
     */
    @Override
    public boolean updateYearPlanWasteSelfMeasures(YearPlanWasteSelfMeasures yearPlanWasteSelfMeasures) {
        return this.updateById(yearPlanWasteSelfMeasures);
    }

    /**
     * 删除危险废物_管理计划_06危废自行利用/处置措施信息
     *
     * @param id 危险废物_管理计划_06危废自行利用/处置措施ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanWasteSelfMeasures(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_06危废自行利用/处置措施
     *
     * @param id 危险废物_管理计划_06危废自行利用/处置措施ID
     * @return 危险废物_管理计划_06危废自行利用/处置措施
     */
    @Override
    public YearPlanWasteSelfMeasures findYearPlanWasteSelfMeasuresById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_06危废自行利用/处置措施列表
     *
     * @param yearPlanWasteSelfMeasures 危险废物_管理计划_06危废自行利用/处置措施
     * @return 危险废物_管理计划_06危废自行利用/处置措施
     */
    @Override
    public List<YearPlanWasteSelfMeasures> findYearPlanWasteSelfMeasuresList(YearPlanWasteSelfMeasures yearPlanWasteSelfMeasures) {
        LambdaQueryWrapper<YearPlanWasteSelfMeasures> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_06危废自行利用/处置措施
     *
     * @param page                      分页参数
     * @param yearPlanWasteSelfMeasures 危险废物_管理计划_06危废自行利用/处置措施
     * @return 危险废物_管理计划_06危废自行利用/处置措施
     */
    @Override
    public PageT<YearPlanWasteSelfMeasures> findYearPlanWasteSelfMeasuresPage(PageT<YearPlanWasteSelfMeasures> page, YearPlanWasteSelfMeasures yearPlanWasteSelfMeasures) {
        LambdaQueryWrapper<YearPlanWasteSelfMeasures> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
