package com.zsgf.platform.service.impl.solid.report;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.solid.report.SolidYearReportMapper;
import com.zsgf.platform.model.entity.solid.report.SolidYearReport;
import com.zsgf.platform.service.solid.report.SolidYearReportService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据共享_一般工业固体废物_产生年报_01基本信息Service业务层处理
 *
 * @author gzl
 * @date 2023-03-27
 */
@Service
public class SolidYearReportServiceImpl extends ServiceImpl<SolidYearReportMapper, SolidYearReport> implements SolidYearReportService {


    /**
     * 新增数据共享_一般工业固体废物_产生年报_01基本信息
     *
     * @param solidYearReport 数据共享_一般工业固体废物_产生年报_01基本信息
     * @return 结果
     */
    @Override
    public boolean saveSolidYearReport(SolidYearReport solidYearReport) {
        return this.save(solidYearReport);
    }

    /**
     * 修改数据共享_一般工业固体废物_产生年报_01基本信息
     *
     * @param solidYearReport 数据共享_一般工业固体废物_产生年报_01基本信息
     * @return 结果
     */
    @Override
    public boolean updateSolidYearReport(SolidYearReport solidYearReport) {
        return this.updateById(solidYearReport);
    }

    /**
     * 删除数据共享_一般工业固体废物_产生年报_01基本信息信息
     *
     * @param id 数据共享_一般工业固体废物_产生年报_01基本信息ID
     * @return 结果
     */
    @Override
    public boolean deleteSolidYearReport(String id) {
        return this.removeById(id);
    }

    /**
     * 查询数据共享_一般工业固体废物_产生年报_01基本信息
     *
     * @param id 数据共享_一般工业固体废物_产生年报_01基本信息ID
     * @return 数据共享_一般工业固体废物_产生年报_01基本信息
     */
    @Override
    public SolidYearReport findSolidYearReportById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询数据共享_一般工业固体废物_产生年报_01基本信息列表
     *
     * @param solidYearReport 数据共享_一般工业固体废物_产生年报_01基本信息
     * @return 数据共享_一般工业固体废物_产生年报_01基本信息
     */
    @Override
    public List<SolidYearReport> findSolidYearReportList(SolidYearReport solidYearReport) {
        LambdaQueryWrapper<SolidYearReport> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询数据共享_一般工业固体废物_产生年报_01基本信息
     *
     * @param page            分页参数
     * @param solidYearReport 数据共享_一般工业固体废物_产生年报_01基本信息
     * @return 数据共享_一般工业固体废物_产生年报_01基本信息
     */
    @Override
    public PageT<SolidYearReport> findSolidYearReportPage(PageT<SolidYearReport> page, SolidYearReport solidYearReport) {
        LambdaQueryWrapper<SolidYearReport> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
