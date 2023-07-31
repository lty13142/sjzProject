package com.zsgf.platform.service.solid.report;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.solid.report.SolidYearReport;

import java.util.List;

/**
 * 数据共享_一般工业固体废物_产生年报_01基本信息Service接口
 *
 * @author gzl
 * @date 2023-03-27
 */
public interface SolidYearReportService extends IService<SolidYearReport> {

    /**
     * 新增数据共享_一般工业固体废物_产生年报_01基本信息
     *
     * @param solidYearReport 数据共享_一般工业固体废物_产生年报_01基本信息
     * @return 结果
     */
    boolean saveSolidYearReport(SolidYearReport solidYearReport);

    /**
     * 修改数据共享_一般工业固体废物_产生年报_01基本信息
     *
     * @param solidYearReport 数据共享_一般工业固体废物_产生年报_01基本信息
     * @return 结果
     */
    boolean updateSolidYearReport(SolidYearReport solidYearReport);

    /**
     * 删除数据共享_一般工业固体废物_产生年报_01基本信息信息
     *
     * @param id 数据共享_一般工业固体废物_产生年报_01基本信息ID
     * @return 结果
     */
    boolean deleteSolidYearReport(String id);

    /**
     * 查询数据共享_一般工业固体废物_产生年报_01基本信息
     *
     * @param id 数据共享_一般工业固体废物_产生年报_01基本信息ID
     * @return 数据共享_一般工业固体废物_产生年报_01基本信息
     */
    SolidYearReport findSolidYearReportById(String id);

    /**
     * 查询数据共享_一般工业固体废物_产生年报_01基本信息列表
     *
     * @param solidYearReport 数据共享_一般工业固体废物_产生年报_01基本信息
     * @return 数据共享_一般工业固体废物_产生年报_01基本信息集合
     */
    List<SolidYearReport> findSolidYearReportList(SolidYearReport solidYearReport);

    /**
     * 分页查询数据共享_一般工业固体废物_产生年报_01基本信息列表
     *
     * @param page            分页参数
     * @param solidYearReport 数据共享_一般工业固体废物_产生年报_01基本信息
     * @return 数据共享_一般工业固体废物_产生年报_01基本信息集合
     */
    PageT<SolidYearReport> findSolidYearReportPage(PageT<SolidYearReport> page, SolidYearReport solidYearReport);
}
