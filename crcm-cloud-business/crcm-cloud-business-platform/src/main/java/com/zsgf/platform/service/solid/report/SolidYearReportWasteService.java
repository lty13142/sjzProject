package com.zsgf.platform.service.solid.report;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.solid.report.SolidYearReportWaste;

import java.util.List;

/**
 * 数据共享_一般工业固体废物_产生年报_02产废信息Service接口
 *
 * @author gzl
 * @date 2023-03-27
 */
public interface SolidYearReportWasteService extends IService<SolidYearReportWaste> {

    /**
     * 新增数据共享_一般工业固体废物_产生年报_02产废信息
     *
     * @param solidYearReportWaste 数据共享_一般工业固体废物_产生年报_02产废信息
     * @return 结果
     */
    boolean saveSolidYearReportWaste(SolidYearReportWaste solidYearReportWaste);

    /**
     * 修改数据共享_一般工业固体废物_产生年报_02产废信息
     *
     * @param solidYearReportWaste 数据共享_一般工业固体废物_产生年报_02产废信息
     * @return 结果
     */
    boolean updateSolidYearReportWaste(SolidYearReportWaste solidYearReportWaste);

    /**
     * 删除数据共享_一般工业固体废物_产生年报_02产废信息信息
     *
     * @param id 数据共享_一般工业固体废物_产生年报_02产废信息ID
     * @return 结果
     */
    boolean deleteSolidYearReportWaste(String id);

    /**
     * 查询数据共享_一般工业固体废物_产生年报_02产废信息
     *
     * @param id 数据共享_一般工业固体废物_产生年报_02产废信息ID
     * @return 数据共享_一般工业固体废物_产生年报_02产废信息
     */
    SolidYearReportWaste findSolidYearReportWasteById(String id);

    /**
     * 查询数据共享_一般工业固体废物_产生年报_02产废信息列表
     *
     * @param solidYearReportWaste 数据共享_一般工业固体废物_产生年报_02产废信息
     * @return 数据共享_一般工业固体废物_产生年报_02产废信息集合
     */
    List<SolidYearReportWaste> findSolidYearReportWasteList(SolidYearReportWaste solidYearReportWaste);

    /**
     * 分页查询数据共享_一般工业固体废物_产生年报_02产废信息列表
     *
     * @param page                 分页参数
     * @param solidYearReportWaste 数据共享_一般工业固体废物_产生年报_02产废信息
     * @return 数据共享_一般工业固体废物_产生年报_02产废信息集合
     */
    PageT<SolidYearReportWaste> findSolidYearReportWastePage(PageT<SolidYearReportWaste> page, SolidYearReportWaste solidYearReportWaste);
}
