package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanProductProcess;

import java.util.List;

/**
 * 危险废物_管理计划_16产品生产工艺说明Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanProductProcessService extends IService<YearPlanProductProcess> {

    /**
     * 新增危险废物_管理计划_16产品生产工艺说明
     *
     * @param yearPlanProductProcess 危险废物_管理计划_16产品生产工艺说明
     * @return 结果
     */
    boolean saveYearPlanProductProcess(YearPlanProductProcess yearPlanProductProcess);

    /**
     * 修改危险废物_管理计划_16产品生产工艺说明
     *
     * @param yearPlanProductProcess 危险废物_管理计划_16产品生产工艺说明
     * @return 结果
     */
    boolean updateYearPlanProductProcess(YearPlanProductProcess yearPlanProductProcess);

    /**
     * 删除危险废物_管理计划_16产品生产工艺说明信息
     *
     * @param id 危险废物_管理计划_16产品生产工艺说明ID
     * @return 结果
     */
    boolean deleteYearPlanProductProcess(String id);

    /**
     * 查询危险废物_管理计划_16产品生产工艺说明
     *
     * @param id 危险废物_管理计划_16产品生产工艺说明ID
     * @return 危险废物_管理计划_16产品生产工艺说明
     */
    YearPlanProductProcess findYearPlanProductProcessById(String id);

    /**
     * 查询危险废物_管理计划_16产品生产工艺说明列表
     *
     * @param yearPlanProductProcess 危险废物_管理计划_16产品生产工艺说明
     * @return 危险废物_管理计划_16产品生产工艺说明集合
     */
    List<YearPlanProductProcess> findYearPlanProductProcessList(YearPlanProductProcess yearPlanProductProcess);

    /**
     * 分页查询危险废物_管理计划_16产品生产工艺说明列表
     *
     * @param page                   分页参数
     * @param yearPlanProductProcess 危险废物_管理计划_16产品生产工艺说明
     * @return 危险废物_管理计划_16产品生产工艺说明集合
     */
    PageT<YearPlanProductProcess> findYearPlanProductProcessPage(PageT<YearPlanProductProcess> page, YearPlanProductProcess yearPlanProductProcess);
}
