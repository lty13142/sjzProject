package com.zsgf.platform.service.businessReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReport;

import java.util.List;

/**
 * 危险废物_经营年报_01基本信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessYearReportService extends IService<BusinessYearReport> {

    /**
     * 新增危险废物_经营年报_01基本信息
     *
     * @param businessYearReport 危险废物_经营年报_01基本信息
     * @return 结果
     */
    boolean saveBusinessYearReport(BusinessYearReport businessYearReport);

    /**
     * 修改危险废物_经营年报_01基本信息
     *
     * @param businessYearReport 危险废物_经营年报_01基本信息
     * @return 结果
     */
    boolean updateBusinessYearReport(BusinessYearReport businessYearReport);

    /**
     * 删除危险废物_经营年报_01基本信息信息
     *
     * @param id 危险废物_经营年报_01基本信息ID
     * @return 结果
     */
    boolean deleteBusinessYearReport(String id);

    /**
     * 查询危险废物_经营年报_01基本信息
     *
     * @param id 危险废物_经营年报_01基本信息ID
     * @return 危险废物_经营年报_01基本信息
     */
    BusinessYearReport findBusinessYearReportById(String id);

    /**
     * 查询危险废物_经营年报_01基本信息列表
     *
     * @param businessYearReport 危险废物_经营年报_01基本信息
     * @return 危险废物_经营年报_01基本信息集合
     */
    List<BusinessYearReport> findBusinessYearReportList(BusinessYearReport businessYearReport);

    /**
     * 分页查询危险废物_经营年报_01基本信息列表
     *
     * @param page               分页参数
     * @param businessYearReport 危险废物_经营年报_01基本信息
     * @return 危险废物_经营年报_01基本信息集合
     */
    PageT<BusinessYearReport> findBusinessYearReportPage(PageT<BusinessYearReport> page, BusinessYearReport businessYearReport);
}
