package com.zsgf.platform.service.businessReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessReport.BusinessMonthReport;

import java.util.List;

/**
 * 危险废物_经营月报_01基本信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessMonthReportService extends IService<BusinessMonthReport> {

    /**
     * 新增危险废物_经营月报_01基本信息
     *
     * @param businessMonthReport 危险废物_经营月报_01基本信息
     * @return 结果
     */
    boolean saveBusinessMonthReport(BusinessMonthReport businessMonthReport);

    /**
     * 修改危险废物_经营月报_01基本信息
     *
     * @param businessMonthReport 危险废物_经营月报_01基本信息
     * @return 结果
     */
    boolean updateBusinessMonthReport(BusinessMonthReport businessMonthReport);

    /**
     * 删除危险废物_经营月报_01基本信息信息
     *
     * @param id 危险废物_经营月报_01基本信息ID
     * @return 结果
     */
    boolean deleteBusinessMonthReport(String id);

    /**
     * 查询危险废物_经营月报_01基本信息
     *
     * @param id 危险废物_经营月报_01基本信息ID
     * @return 危险废物_经营月报_01基本信息
     */
    BusinessMonthReport findBusinessMonthReportById(String id);

    /**
     * 查询危险废物_经营月报_01基本信息列表
     *
     * @param businessMonthReport 危险废物_经营月报_01基本信息
     * @return 危险废物_经营月报_01基本信息集合
     */
    List<BusinessMonthReport> findBusinessMonthReportList(BusinessMonthReport businessMonthReport);

    /**
     * 分页查询危险废物_经营月报_01基本信息列表
     *
     * @param page                分页参数
     * @param businessMonthReport 危险废物_经营月报_01基本信息
     * @return 危险废物_经营月报_01基本信息集合
     */
    PageT<BusinessMonthReport> findBusinessMonthReportPage(PageT<BusinessMonthReport> page, BusinessMonthReport businessMonthReport);
}
