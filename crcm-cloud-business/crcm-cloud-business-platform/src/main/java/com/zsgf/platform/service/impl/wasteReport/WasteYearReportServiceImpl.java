package com.zsgf.platform.service.impl.wasteReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.wasteReport.WasteYearReportMapper;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReport;
import com.zsgf.platform.service.wasteReport.WasteYearReportService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_产生年报_01基本信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class WasteYearReportServiceImpl extends ServiceImpl<WasteYearReportMapper, WasteYearReport> implements WasteYearReportService {


    /**
     * 新增危险废物_产生年报_01基本信息
     *
     * @param wasteYearReport 危险废物_产生年报_01基本信息
     * @return 结果
     */
    @Override
    public boolean saveWasteYearReport(WasteYearReport wasteYearReport) {
        return this.save(wasteYearReport);
    }

    /**
     * 修改危险废物_产生年报_01基本信息
     *
     * @param wasteYearReport 危险废物_产生年报_01基本信息
     * @return 结果
     */
    @Override
    public boolean updateWasteYearReport(WasteYearReport wasteYearReport) {
        return this.updateById(wasteYearReport);
    }

    /**
     * 删除危险废物_产生年报_01基本信息信息
     *
     * @param id 危险废物_产生年报_01基本信息ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteYearReport(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_产生年报_01基本信息
     *
     * @param id 危险废物_产生年报_01基本信息ID
     * @return 危险废物_产生年报_01基本信息
     */
    @Override
    public WasteYearReport findWasteYearReportById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_产生年报_01基本信息列表
     *
     * @param wasteYearReport 危险废物_产生年报_01基本信息
     * @return 危险废物_产生年报_01基本信息
     */
    @Override
    public List<WasteYearReport> findWasteYearReportList(WasteYearReport wasteYearReport) {
        LambdaQueryWrapper<WasteYearReport> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_产生年报_01基本信息
     *
     * @param page            分页参数
     * @param wasteYearReport 危险废物_产生年报_01基本信息
     * @return 危险废物_产生年报_01基本信息
     */
    @Override
    public PageT<WasteYearReport> findWasteYearReportPage(PageT<WasteYearReport> page, WasteYearReport wasteYearReport) {
        LambdaQueryWrapper<WasteYearReport> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
