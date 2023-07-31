package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanWasteEntrustUseDisposalMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteEntrustUseDisposal;
import com.zsgf.platform.service.yearPlan.YearPlanWasteEntrustUseDisposalService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_05危险废物委托利用处置Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanWasteEntrustUseDisposalServiceImpl extends ServiceImpl<YearPlanWasteEntrustUseDisposalMapper, YearPlanWasteEntrustUseDisposal>
        implements YearPlanWasteEntrustUseDisposalService {


    /**
     * 新增危险废物_管理计划_05危险废物委托利用处置
     *
     * @param yearPlanWasteEntrustUseDisposal 危险废物_管理计划_05危险废物委托利用处置
     * @return 结果
     */
    @Override
    public boolean saveYearPlanWasteEntrustUseDisposal(YearPlanWasteEntrustUseDisposal yearPlanWasteEntrustUseDisposal) {
        return this.save(yearPlanWasteEntrustUseDisposal);
    }

    /**
     * 修改危险废物_管理计划_05危险废物委托利用处置
     *
     * @param yearPlanWasteEntrustUseDisposal 危险废物_管理计划_05危险废物委托利用处置
     * @return 结果
     */
    @Override
    public boolean updateYearPlanWasteEntrustUseDisposal(YearPlanWasteEntrustUseDisposal yearPlanWasteEntrustUseDisposal) {
        return this.updateById(yearPlanWasteEntrustUseDisposal);
    }

    /**
     * 删除危险废物_管理计划_05危险废物委托利用处置信息
     *
     * @param id 危险废物_管理计划_05危险废物委托利用处置ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanWasteEntrustUseDisposal(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_05危险废物委托利用处置
     *
     * @param id 危险废物_管理计划_05危险废物委托利用处置ID
     * @return 危险废物_管理计划_05危险废物委托利用处置
     */
    @Override
    public YearPlanWasteEntrustUseDisposal findYearPlanWasteEntrustUseDisposalById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_05危险废物委托利用处置列表
     *
     * @param yearPlanWasteEntrustUseDisposal 危险废物_管理计划_05危险废物委托利用处置
     * @return 危险废物_管理计划_05危险废物委托利用处置
     */
    @Override
    public List<YearPlanWasteEntrustUseDisposal> findYearPlanWasteEntrustUseDisposalList(YearPlanWasteEntrustUseDisposal yearPlanWasteEntrustUseDisposal) {
        LambdaQueryWrapper<YearPlanWasteEntrustUseDisposal> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_05危险废物委托利用处置
     *
     * @param page                            分页参数
     * @param yearPlanWasteEntrustUseDisposal 危险废物_管理计划_05危险废物委托利用处置
     * @return 危险废物_管理计划_05危险废物委托利用处置
     */
    @Override
    public PageT<YearPlanWasteEntrustUseDisposal> findYearPlanWasteEntrustUseDisposalPage(PageT<YearPlanWasteEntrustUseDisposal> page, YearPlanWasteEntrustUseDisposal yearPlanWasteEntrustUseDisposal) {
        LambdaQueryWrapper<YearPlanWasteEntrustUseDisposal> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
