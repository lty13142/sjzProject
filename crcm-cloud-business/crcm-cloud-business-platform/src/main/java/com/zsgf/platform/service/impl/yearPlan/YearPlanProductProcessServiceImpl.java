package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanProductProcessMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanProductProcess;
import com.zsgf.platform.service.yearPlan.YearPlanProductProcessService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_16产品生产工艺说明Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanProductProcessServiceImpl extends ServiceImpl<YearPlanProductProcessMapper, YearPlanProductProcess>
        implements YearPlanProductProcessService {


    /**
     * 新增危险废物_管理计划_16产品生产工艺说明
     *
     * @param yearPlanProductProcess 危险废物_管理计划_16产品生产工艺说明
     * @return 结果
     */
    @Override
    public boolean saveYearPlanProductProcess(YearPlanProductProcess yearPlanProductProcess) {
        return this.save(yearPlanProductProcess);
    }

    /**
     * 修改危险废物_管理计划_16产品生产工艺说明
     *
     * @param yearPlanProductProcess 危险废物_管理计划_16产品生产工艺说明
     * @return 结果
     */
    @Override
    public boolean updateYearPlanProductProcess(YearPlanProductProcess yearPlanProductProcess) {
        return this.updateById(yearPlanProductProcess);
    }

    /**
     * 删除危险废物_管理计划_16产品生产工艺说明信息
     *
     * @param id 危险废物_管理计划_16产品生产工艺说明ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanProductProcess(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_16产品生产工艺说明
     *
     * @param id 危险废物_管理计划_16产品生产工艺说明ID
     * @return 危险废物_管理计划_16产品生产工艺说明
     */
    @Override
    public YearPlanProductProcess findYearPlanProductProcessById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_16产品生产工艺说明列表
     *
     * @param yearPlanProductProcess 危险废物_管理计划_16产品生产工艺说明
     * @return 危险废物_管理计划_16产品生产工艺说明
     */
    @Override
    public List<YearPlanProductProcess> findYearPlanProductProcessList(YearPlanProductProcess yearPlanProductProcess) {
        LambdaQueryWrapper<YearPlanProductProcess> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_16产品生产工艺说明
     *
     * @param page                   分页参数
     * @param yearPlanProductProcess 危险废物_管理计划_16产品生产工艺说明
     * @return 危险废物_管理计划_16产品生产工艺说明
     */
    @Override
    public PageT<YearPlanProductProcess> findYearPlanProductProcessPage(PageT<YearPlanProductProcess> page, YearPlanProductProcess yearPlanProductProcess) {
        LambdaQueryWrapper<YearPlanProductProcess> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
