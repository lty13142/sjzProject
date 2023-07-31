package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanLastYearReviewMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanLastYearReview;
import com.zsgf.platform.service.yearPlan.YearPlanLastYearReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_20上年度管理计划回顾Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanLastYearReviewServiceImpl extends ServiceImpl<YearPlanLastYearReviewMapper, YearPlanLastYearReview>
        implements YearPlanLastYearReviewService {


    /**
     * 新增危险废物_管理计划_20上年度管理计划回顾
     *
     * @param yearPlanLastYearReview 危险废物_管理计划_20上年度管理计划回顾
     * @return 结果
     */
    @Override
    public boolean saveYearPlanLastYearReview(YearPlanLastYearReview yearPlanLastYearReview) {
        return this.save(yearPlanLastYearReview);
    }

    /**
     * 修改危险废物_管理计划_20上年度管理计划回顾
     *
     * @param yearPlanLastYearReview 危险废物_管理计划_20上年度管理计划回顾
     * @return 结果
     */
    @Override
    public boolean updateYearPlanLastYearReview(YearPlanLastYearReview yearPlanLastYearReview) {
        return this.updateById(yearPlanLastYearReview);
    }

    /**
     * 删除危险废物_管理计划_20上年度管理计划回顾信息
     *
     * @param id 危险废物_管理计划_20上年度管理计划回顾ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanLastYearReview(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_20上年度管理计划回顾
     *
     * @param id 危险废物_管理计划_20上年度管理计划回顾ID
     * @return 危险废物_管理计划_20上年度管理计划回顾
     */
    @Override
    public YearPlanLastYearReview findYearPlanLastYearReviewById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_20上年度管理计划回顾列表
     *
     * @param yearPlanLastYearReview 危险废物_管理计划_20上年度管理计划回顾
     * @return 危险废物_管理计划_20上年度管理计划回顾
     */
    @Override
    public List<YearPlanLastYearReview> findYearPlanLastYearReviewList(YearPlanLastYearReview yearPlanLastYearReview) {
        LambdaQueryWrapper<YearPlanLastYearReview> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_20上年度管理计划回顾
     *
     * @param page                   分页参数
     * @param yearPlanLastYearReview 危险废物_管理计划_20上年度管理计划回顾
     * @return 危险废物_管理计划_20上年度管理计划回顾
     */
    @Override
    public PageT<YearPlanLastYearReview> findYearPlanLastYearReviewPage(PageT<YearPlanLastYearReview> page, YearPlanLastYearReview yearPlanLastYearReview) {
        LambdaQueryWrapper<YearPlanLastYearReview> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
