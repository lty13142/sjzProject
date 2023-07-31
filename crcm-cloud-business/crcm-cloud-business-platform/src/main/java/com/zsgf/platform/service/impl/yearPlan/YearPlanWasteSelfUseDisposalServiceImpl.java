package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanWasteSelfUseDisposalMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteSelfUseDisposal;
import com.zsgf.platform.service.yearPlan.YearPlanWasteSelfUseDisposalService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_04危废自行利用处置信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanWasteSelfUseDisposalServiceImpl extends ServiceImpl<YearPlanWasteSelfUseDisposalMapper, YearPlanWasteSelfUseDisposal>
        implements YearPlanWasteSelfUseDisposalService {


    /**
     * 新增危险废物_管理计划_04危废自行利用处置信息
     *
     * @param yearPlanWasteSelfUseDisposal 危险废物_管理计划_04危废自行利用处置信息
     * @return 结果
     */
    @Override
    public boolean saveYearPlanWasteSelfUseDisposal(YearPlanWasteSelfUseDisposal yearPlanWasteSelfUseDisposal) {
        return this.save(yearPlanWasteSelfUseDisposal);
    }

    /**
     * 修改危险废物_管理计划_04危废自行利用处置信息
     *
     * @param yearPlanWasteSelfUseDisposal 危险废物_管理计划_04危废自行利用处置信息
     * @return 结果
     */
    @Override
    public boolean updateYearPlanWasteSelfUseDisposal(YearPlanWasteSelfUseDisposal yearPlanWasteSelfUseDisposal) {
        return this.updateById(yearPlanWasteSelfUseDisposal);
    }

    /**
     * 删除危险废物_管理计划_04危废自行利用处置信息信息
     *
     * @param id 危险废物_管理计划_04危废自行利用处置信息ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanWasteSelfUseDisposal(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_04危废自行利用处置信息
     *
     * @param id 危险废物_管理计划_04危废自行利用处置信息ID
     * @return 危险废物_管理计划_04危废自行利用处置信息
     */
    @Override
    public YearPlanWasteSelfUseDisposal findYearPlanWasteSelfUseDisposalById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_04危废自行利用处置信息列表
     *
     * @param yearPlanWasteSelfUseDisposal 危险废物_管理计划_04危废自行利用处置信息
     * @return 危险废物_管理计划_04危废自行利用处置信息
     */
    @Override
    public List<YearPlanWasteSelfUseDisposal> findYearPlanWasteSelfUseDisposalList(YearPlanWasteSelfUseDisposal yearPlanWasteSelfUseDisposal) {
        LambdaQueryWrapper<YearPlanWasteSelfUseDisposal> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_04危废自行利用处置信息
     *
     * @param page                         分页参数
     * @param yearPlanWasteSelfUseDisposal 危险废物_管理计划_04危废自行利用处置信息
     * @return 危险废物_管理计划_04危废自行利用处置信息
     */
    @Override
    public PageT<YearPlanWasteSelfUseDisposal> findYearPlanWasteSelfUseDisposalPage(PageT<YearPlanWasteSelfUseDisposal> page, YearPlanWasteSelfUseDisposal yearPlanWasteSelfUseDisposal) {
        LambdaQueryWrapper<YearPlanWasteSelfUseDisposal> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
