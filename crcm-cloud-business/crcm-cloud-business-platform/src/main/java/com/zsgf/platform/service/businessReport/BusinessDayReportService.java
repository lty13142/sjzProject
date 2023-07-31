package com.zsgf.platform.service.businessReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessReport.BusinessDayReport;

import java.util.List;

/**
 * 危险废物_经营日报_01基本信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessDayReportService extends IService<BusinessDayReport> {

    /**
     * 新增危险废物_经营日报_01基本信息
     *
     * @param businessDayReport 危险废物_经营日报_01基本信息
     * @return 结果
     */
    boolean saveBusinessDayReport(BusinessDayReport businessDayReport);

    /**
     * 修改危险废物_经营日报_01基本信息
     *
     * @param businessDayReport 危险废物_经营日报_01基本信息
     * @return 结果
     */
    boolean updateBusinessDayReport(BusinessDayReport businessDayReport);

    /**
     * 删除危险废物_经营日报_01基本信息信息
     *
     * @param id 危险废物_经营日报_01基本信息ID
     * @return 结果
     */
    boolean deleteBusinessDayReport(String id);

    /**
     * 查询危险废物_经营日报_01基本信息
     *
     * @param id 危险废物_经营日报_01基本信息ID
     * @return 危险废物_经营日报_01基本信息
     */
    BusinessDayReport findBusinessDayReportById(String id);

    /**
     * 查询危险废物_经营日报_01基本信息列表
     *
     * @param businessDayReport 危险废物_经营日报_01基本信息
     * @return 危险废物_经营日报_01基本信息集合
     */
    List<BusinessDayReport> findBusinessDayReportList(BusinessDayReport businessDayReport);

    /**
     * 分页查询危险废物_经营日报_01基本信息列表
     *
     * @param page              分页参数
     * @param businessDayReport 危险废物_经营日报_01基本信息
     * @return 危险废物_经营日报_01基本信息集合
     */
    PageT<BusinessDayReport> findBusinessDayReportPage(PageT<BusinessDayReport> page, BusinessDayReport businessDayReport);
}
