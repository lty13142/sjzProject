package com.zsgf.platform.service.solid.report;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.solid.report.SolidYearReportSelfUse;

import java.util.List;

/**
 * 数据共享_一般工业固体废物_产生年报_03自行利用情况Service接口
 *
 * @author gzl
 * @date 2023-03-27
 */
public interface SolidYearReportSelfUseService extends IService<SolidYearReportSelfUse> {

    /**
     * 新增数据共享_一般工业固体废物_产生年报_03自行利用情况
     *
     * @param solidYearReportSelfUse 数据共享_一般工业固体废物_产生年报_03自行利用情况
     * @return 结果
     */
    boolean saveSolidYearReportSelfUse(SolidYearReportSelfUse solidYearReportSelfUse);

    /**
     * 修改数据共享_一般工业固体废物_产生年报_03自行利用情况
     *
     * @param solidYearReportSelfUse 数据共享_一般工业固体废物_产生年报_03自行利用情况
     * @return 结果
     */
    boolean updateSolidYearReportSelfUse(SolidYearReportSelfUse solidYearReportSelfUse);

    /**
     * 删除数据共享_一般工业固体废物_产生年报_03自行利用情况信息
     *
     * @param id 数据共享_一般工业固体废物_产生年报_03自行利用情况ID
     * @return 结果
     */
    boolean deleteSolidYearReportSelfUse(String id);

    /**
     * 查询数据共享_一般工业固体废物_产生年报_03自行利用情况
     *
     * @param id 数据共享_一般工业固体废物_产生年报_03自行利用情况ID
     * @return 数据共享_一般工业固体废物_产生年报_03自行利用情况
     */
    SolidYearReportSelfUse findSolidYearReportSelfUseById(String id);

    /**
     * 查询数据共享_一般工业固体废物_产生年报_03自行利用情况列表
     *
     * @param solidYearReportSelfUse 数据共享_一般工业固体废物_产生年报_03自行利用情况
     * @return 数据共享_一般工业固体废物_产生年报_03自行利用情况集合
     */
    List<SolidYearReportSelfUse> findSolidYearReportSelfUseList(SolidYearReportSelfUse solidYearReportSelfUse);

    /**
     * 分页查询数据共享_一般工业固体废物_产生年报_03自行利用情况列表
     *
     * @param page                   分页参数
     * @param solidYearReportSelfUse 数据共享_一般工业固体废物_产生年报_03自行利用情况
     * @return 数据共享_一般工业固体废物_产生年报_03自行利用情况集合
     */
    PageT<SolidYearReportSelfUse> findSolidYearReportSelfUsePage(PageT<SolidYearReportSelfUse> page, SolidYearReportSelfUse solidYearReportSelfUse);
}
