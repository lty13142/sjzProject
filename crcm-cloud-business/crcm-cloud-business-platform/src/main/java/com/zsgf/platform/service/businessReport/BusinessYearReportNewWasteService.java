package com.zsgf.platform.service.businessReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportNewWaste;

import java.util.List;

/**
 * 危险废物_经营年报_07新产生危废情况Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessYearReportNewWasteService extends IService<BusinessYearReportNewWaste> {

    /**
     * 新增危险废物_经营年报_07新产生危废情况
     *
     * @param businessYearReportNewWaste 危险废物_经营年报_07新产生危废情况
     * @return 结果
     */
    boolean saveBusinessYearReportNewWaste(BusinessYearReportNewWaste businessYearReportNewWaste);

    /**
     * 修改危险废物_经营年报_07新产生危废情况
     *
     * @param businessYearReportNewWaste 危险废物_经营年报_07新产生危废情况
     * @return 结果
     */
    boolean updateBusinessYearReportNewWaste(BusinessYearReportNewWaste businessYearReportNewWaste);

    /**
     * 删除危险废物_经营年报_07新产生危废情况信息
     *
     * @param id 危险废物_经营年报_07新产生危废情况ID
     * @return 结果
     */
    boolean deleteBusinessYearReportNewWaste(String id);

    /**
     * 查询危险废物_经营年报_07新产生危废情况
     *
     * @param id 危险废物_经营年报_07新产生危废情况ID
     * @return 危险废物_经营年报_07新产生危废情况
     */
    BusinessYearReportNewWaste findBusinessYearReportNewWasteById(String id);

    /**
     * 查询危险废物_经营年报_07新产生危废情况列表
     *
     * @param businessYearReportNewWaste 危险废物_经营年报_07新产生危废情况
     * @return 危险废物_经营年报_07新产生危废情况集合
     */
    List<BusinessYearReportNewWaste> findBusinessYearReportNewWasteList(BusinessYearReportNewWaste businessYearReportNewWaste);

    /**
     * 分页查询危险废物_经营年报_07新产生危废情况列表
     *
     * @param page                       分页参数
     * @param businessYearReportNewWaste 危险废物_经营年报_07新产生危废情况
     * @return 危险废物_经营年报_07新产生危废情况集合
     */
    PageT<BusinessYearReportNewWaste> findBusinessYearReportNewWastePage(PageT<BusinessYearReportNewWaste> page, BusinessYearReportNewWaste businessYearReportNewWaste);
}
