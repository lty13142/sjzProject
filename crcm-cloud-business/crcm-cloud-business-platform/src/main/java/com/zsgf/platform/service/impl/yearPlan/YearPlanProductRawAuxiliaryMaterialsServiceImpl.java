package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanProductRawAuxiliaryMaterialsMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanProductRawAuxiliaryMaterials;
import com.zsgf.platform.service.yearPlan.YearPlanProductRawAuxiliaryMaterialsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_13产品生产主要原辅材料Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanProductRawAuxiliaryMaterialsServiceImpl
        extends ServiceImpl<YearPlanProductRawAuxiliaryMaterialsMapper, YearPlanProductRawAuxiliaryMaterials>
        implements YearPlanProductRawAuxiliaryMaterialsService {


    /**
     * 新增危险废物_管理计划_13产品生产主要原辅材料
     *
     * @param yearPlanProductRawAuxiliaryMaterials 危险废物_管理计划_13产品生产主要原辅材料
     * @return 结果
     */
    @Override
    public boolean saveYearPlanProductRawAuxiliaryMaterials(YearPlanProductRawAuxiliaryMaterials yearPlanProductRawAuxiliaryMaterials) {
        return this.save(yearPlanProductRawAuxiliaryMaterials);
    }

    /**
     * 修改危险废物_管理计划_13产品生产主要原辅材料
     *
     * @param yearPlanProductRawAuxiliaryMaterials 危险废物_管理计划_13产品生产主要原辅材料
     * @return 结果
     */
    @Override
    public boolean updateYearPlanProductRawAuxiliaryMaterials(YearPlanProductRawAuxiliaryMaterials yearPlanProductRawAuxiliaryMaterials) {
        return this.updateById(yearPlanProductRawAuxiliaryMaterials);
    }

    /**
     * 删除危险废物_管理计划_13产品生产主要原辅材料信息
     *
     * @param id 危险废物_管理计划_13产品生产主要原辅材料ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanProductRawAuxiliaryMaterials(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_13产品生产主要原辅材料
     *
     * @param id 危险废物_管理计划_13产品生产主要原辅材料ID
     * @return 危险废物_管理计划_13产品生产主要原辅材料
     */
    @Override
    public YearPlanProductRawAuxiliaryMaterials findYearPlanProductRawAuxiliaryMaterialsById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_13产品生产主要原辅材料列表
     *
     * @param yearPlanProductRawAuxiliaryMaterials 危险废物_管理计划_13产品生产主要原辅材料
     * @return 危险废物_管理计划_13产品生产主要原辅材料
     */
    @Override
    public List<YearPlanProductRawAuxiliaryMaterials> findYearPlanProductRawAuxiliaryMaterialsList(YearPlanProductRawAuxiliaryMaterials yearPlanProductRawAuxiliaryMaterials) {
        LambdaQueryWrapper<YearPlanProductRawAuxiliaryMaterials> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_13产品生产主要原辅材料
     *
     * @param page                                 分页参数
     * @param yearPlanProductRawAuxiliaryMaterials 危险废物_管理计划_13产品生产主要原辅材料
     * @return 危险废物_管理计划_13产品生产主要原辅材料
     */
    @Override
    public PageT<YearPlanProductRawAuxiliaryMaterials> findYearPlanProductRawAuxiliaryMaterialsPage(PageT<YearPlanProductRawAuxiliaryMaterials> page, YearPlanProductRawAuxiliaryMaterials yearPlanProductRawAuxiliaryMaterials) {
        LambdaQueryWrapper<YearPlanProductRawAuxiliaryMaterials> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
