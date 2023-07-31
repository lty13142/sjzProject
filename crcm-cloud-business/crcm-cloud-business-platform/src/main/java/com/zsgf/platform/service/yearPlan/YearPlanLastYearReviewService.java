package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanLastYearReview;

import java.util.List;

/**
 * 危险废物_管理计划_20上年度管理计划回顾Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanLastYearReviewService extends IService<YearPlanLastYearReview> {

    /**
     * 新增危险废物_管理计划_20上年度管理计划回顾
     *
     * @param yearPlanLastYearReview 危险废物_管理计划_20上年度管理计划回顾
     * @return 结果
     */
    boolean saveYearPlanLastYearReview(YearPlanLastYearReview yearPlanLastYearReview);

    /**
     * 修改危险废物_管理计划_20上年度管理计划回顾
     *
     * @param yearPlanLastYearReview 危险废物_管理计划_20上年度管理计划回顾
     * @return 结果
     */
    boolean updateYearPlanLastYearReview(YearPlanLastYearReview yearPlanLastYearReview);

    /**
     * 删除危险废物_管理计划_20上年度管理计划回顾信息
     *
     * @param id 危险废物_管理计划_20上年度管理计划回顾ID
     * @return 结果
     */
    boolean deleteYearPlanLastYearReview(String id);

    /**
     * 查询危险废物_管理计划_20上年度管理计划回顾
     *
     * @param id 危险废物_管理计划_20上年度管理计划回顾ID
     * @return 危险废物_管理计划_20上年度管理计划回顾
     */
    YearPlanLastYearReview findYearPlanLastYearReviewById(String id);

    /**
     * 查询危险废物_管理计划_20上年度管理计划回顾列表
     *
     * @param yearPlanLastYearReview 危险废物_管理计划_20上年度管理计划回顾
     * @return 危险废物_管理计划_20上年度管理计划回顾集合
     */
    List<YearPlanLastYearReview> findYearPlanLastYearReviewList(YearPlanLastYearReview yearPlanLastYearReview);

    /**
     * 分页查询危险废物_管理计划_20上年度管理计划回顾列表
     *
     * @param page                   分页参数
     * @param yearPlanLastYearReview 危险废物_管理计划_20上年度管理计划回顾
     * @return 危险废物_管理计划_20上年度管理计划回顾集合
     */
    PageT<YearPlanLastYearReview> findYearPlanLastYearReviewPage(PageT<YearPlanLastYearReview> page, YearPlanLastYearReview yearPlanLastYearReview);
}
