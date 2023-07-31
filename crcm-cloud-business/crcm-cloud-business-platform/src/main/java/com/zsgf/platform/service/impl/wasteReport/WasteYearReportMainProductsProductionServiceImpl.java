package com.zsgf.platform.service.impl.wasteReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.wasteReport.WasteYearReportMainProductsProductionMapper;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReportMainProductsProduction;
import com.zsgf.platform.service.wasteReport.WasteYearReportMainProductsProductionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_产生年报_07主要产品生产情况Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class WasteYearReportMainProductsProductionServiceImpl
        extends ServiceImpl<WasteYearReportMainProductsProductionMapper, WasteYearReportMainProductsProduction>
        implements WasteYearReportMainProductsProductionService {


    /**
     * 新增危险废物_产生年报_07主要产品生产情况
     *
     * @param wasteYearReportMainProductsProduction 危险废物_产生年报_07主要产品生产情况
     * @return 结果
     */
    @Override
    public boolean saveWasteYearReportMainProductsProduction(WasteYearReportMainProductsProduction wasteYearReportMainProductsProduction) {
        return this.save(wasteYearReportMainProductsProduction);
    }

    /**
     * 修改危险废物_产生年报_07主要产品生产情况
     *
     * @param wasteYearReportMainProductsProduction 危险废物_产生年报_07主要产品生产情况
     * @return 结果
     */
    @Override
    public boolean updateWasteYearReportMainProductsProduction(WasteYearReportMainProductsProduction wasteYearReportMainProductsProduction) {
        return this.updateById(wasteYearReportMainProductsProduction);
    }

    /**
     * 删除危险废物_产生年报_07主要产品生产情况信息
     *
     * @param id 危险废物_产生年报_07主要产品生产情况ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteYearReportMainProductsProduction(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_产生年报_07主要产品生产情况
     *
     * @param id 危险废物_产生年报_07主要产品生产情况ID
     * @return 危险废物_产生年报_07主要产品生产情况
     */
    @Override
    public WasteYearReportMainProductsProduction findWasteYearReportMainProductsProductionById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_产生年报_07主要产品生产情况列表
     *
     * @param wasteYearReportMainProductsProduction 危险废物_产生年报_07主要产品生产情况
     * @return 危险废物_产生年报_07主要产品生产情况
     */
    @Override
    public List<WasteYearReportMainProductsProduction> findWasteYearReportMainProductsProductionList(WasteYearReportMainProductsProduction wasteYearReportMainProductsProduction) {
        LambdaQueryWrapper<WasteYearReportMainProductsProduction> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_产生年报_07主要产品生产情况
     *
     * @param page                                  分页参数
     * @param wasteYearReportMainProductsProduction 危险废物_产生年报_07主要产品生产情况
     * @return 危险废物_产生年报_07主要产品生产情况
     */
    @Override
    public PageT<WasteYearReportMainProductsProduction> findWasteYearReportMainProductsProductionPage(PageT<WasteYearReportMainProductsProduction> page, WasteYearReportMainProductsProduction wasteYearReportMainProductsProduction) {
        LambdaQueryWrapper<WasteYearReportMainProductsProduction> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
