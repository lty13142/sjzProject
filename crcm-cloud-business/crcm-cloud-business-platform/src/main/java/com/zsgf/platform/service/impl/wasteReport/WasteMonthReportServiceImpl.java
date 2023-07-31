package com.zsgf.platform.service.impl.wasteReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.wasteReport.WasteMonthReportMapper;
import com.zsgf.platform.model.entity.wasteReport.WasteMonthReport;
import com.zsgf.platform.service.wasteReport.WasteMonthReportService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_产生月报_01基本信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class WasteMonthReportServiceImpl extends ServiceImpl<WasteMonthReportMapper, WasteMonthReport> implements WasteMonthReportService {


    /**
     * 新增危险废物_产生月报_01基本信息
     *
     * @param wasteMonthReport 危险废物_产生月报_01基本信息
     * @return 结果
     */
    @Override
    public boolean saveWasteMonthReport(WasteMonthReport wasteMonthReport) {
        return this.save(wasteMonthReport);
    }

    /**
     * 修改危险废物_产生月报_01基本信息
     *
     * @param wasteMonthReport 危险废物_产生月报_01基本信息
     * @return 结果
     */
    @Override
    public boolean updateWasteMonthReport(WasteMonthReport wasteMonthReport) {
        return this.updateById(wasteMonthReport);
    }

    /**
     * 删除危险废物_产生月报_01基本信息信息
     *
     * @param id 危险废物_产生月报_01基本信息ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteMonthReport(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_产生月报_01基本信息
     *
     * @param id 危险废物_产生月报_01基本信息ID
     * @return 危险废物_产生月报_01基本信息
     */
    @Override
    public WasteMonthReport findWasteMonthReportById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_产生月报_01基本信息列表
     *
     * @param wasteMonthReport 危险废物_产生月报_01基本信息
     * @return 危险废物_产生月报_01基本信息
     */
    @Override
    public List<WasteMonthReport> findWasteMonthReportList(WasteMonthReport wasteMonthReport) {
        LambdaQueryWrapper<WasteMonthReport> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_产生月报_01基本信息
     *
     * @param page             分页参数
     * @param wasteMonthReport 危险废物_产生月报_01基本信息
     * @return 危险废物_产生月报_01基本信息
     */
    @Override
    public PageT<WasteMonthReport> findWasteMonthReportPage(PageT<WasteMonthReport> page, WasteMonthReport wasteMonthReport) {
        LambdaQueryWrapper<WasteMonthReport> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
