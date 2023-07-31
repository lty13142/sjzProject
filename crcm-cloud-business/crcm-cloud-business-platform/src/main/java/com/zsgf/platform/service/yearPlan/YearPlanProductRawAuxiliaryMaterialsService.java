package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanProductRawAuxiliaryMaterials;

import java.util.List;

/**
 * 危险废物_管理计划_13产品生产主要原辅材料Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanProductRawAuxiliaryMaterialsService extends IService<YearPlanProductRawAuxiliaryMaterials> {

    /**
     * 新增危险废物_管理计划_13产品生产主要原辅材料
     *
     * @param yearPlanProductRawAuxiliaryMaterials 危险废物_管理计划_13产品生产主要原辅材料
     * @return 结果
     */
    boolean saveYearPlanProductRawAuxiliaryMaterials(YearPlanProductRawAuxiliaryMaterials yearPlanProductRawAuxiliaryMaterials);

    /**
     * 修改危险废物_管理计划_13产品生产主要原辅材料
     *
     * @param yearPlanProductRawAuxiliaryMaterials 危险废物_管理计划_13产品生产主要原辅材料
     * @return 结果
     */
    boolean updateYearPlanProductRawAuxiliaryMaterials(YearPlanProductRawAuxiliaryMaterials yearPlanProductRawAuxiliaryMaterials);

    /**
     * 删除危险废物_管理计划_13产品生产主要原辅材料信息
     *
     * @param id 危险废物_管理计划_13产品生产主要原辅材料ID
     * @return 结果
     */
    boolean deleteYearPlanProductRawAuxiliaryMaterials(String id);

    /**
     * 查询危险废物_管理计划_13产品生产主要原辅材料
     *
     * @param id 危险废物_管理计划_13产品生产主要原辅材料ID
     * @return 危险废物_管理计划_13产品生产主要原辅材料
     */
    YearPlanProductRawAuxiliaryMaterials findYearPlanProductRawAuxiliaryMaterialsById(String id);

    /**
     * 查询危险废物_管理计划_13产品生产主要原辅材料列表
     *
     * @param yearPlanProductRawAuxiliaryMaterials 危险废物_管理计划_13产品生产主要原辅材料
     * @return 危险废物_管理计划_13产品生产主要原辅材料集合
     */
    List<YearPlanProductRawAuxiliaryMaterials> findYearPlanProductRawAuxiliaryMaterialsList(YearPlanProductRawAuxiliaryMaterials yearPlanProductRawAuxiliaryMaterials);

    /**
     * 分页查询危险废物_管理计划_13产品生产主要原辅材料列表
     *
     * @param page                                 分页参数
     * @param yearPlanProductRawAuxiliaryMaterials 危险废物_管理计划_13产品生产主要原辅材料
     * @return 危险废物_管理计划_13产品生产主要原辅材料集合
     */
    PageT<YearPlanProductRawAuxiliaryMaterials> findYearPlanProductRawAuxiliaryMaterialsPage(PageT<YearPlanProductRawAuxiliaryMaterials> page, YearPlanProductRawAuxiliaryMaterials yearPlanProductRawAuxiliaryMaterials);
}
