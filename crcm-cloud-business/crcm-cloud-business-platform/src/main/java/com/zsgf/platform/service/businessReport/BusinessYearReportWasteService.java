package com.zsgf.platform.service.businessReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportWaste;

import java.util.List;

/**
 * 危险废物_经营年报_03经营废物信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessYearReportWasteService extends IService<BusinessYearReportWaste> {

    /**
     * 新增危险废物_经营年报_03经营废物信息
     *
     * @param businessYearReportWaste 危险废物_经营年报_03经营废物信息
     * @return 结果
     */
    boolean saveBusinessYearReportWaste(BusinessYearReportWaste businessYearReportWaste);

    /**
     * 修改危险废物_经营年报_03经营废物信息
     *
     * @param businessYearReportWaste 危险废物_经营年报_03经营废物信息
     * @return 结果
     */
    boolean updateBusinessYearReportWaste(BusinessYearReportWaste businessYearReportWaste);

    /**
     * 删除危险废物_经营年报_03经营废物信息信息
     *
     * @param id 危险废物_经营年报_03经营废物信息ID
     * @return 结果
     */
    boolean deleteBusinessYearReportWaste(String id);

    /**
     * 查询危险废物_经营年报_03经营废物信息
     *
     * @param id 危险废物_经营年报_03经营废物信息ID
     * @return 危险废物_经营年报_03经营废物信息
     */
    BusinessYearReportWaste findBusinessYearReportWasteById(String id);

    /**
     * 查询危险废物_经营年报_03经营废物信息列表
     *
     * @param businessYearReportWaste 危险废物_经营年报_03经营废物信息
     * @return 危险废物_经营年报_03经营废物信息集合
     */
    List<BusinessYearReportWaste> findBusinessYearReportWasteList(BusinessYearReportWaste businessYearReportWaste);

    /**
     * 分页查询危险废物_经营年报_03经营废物信息列表
     *
     * @param page                    分页参数
     * @param businessYearReportWaste 危险废物_经营年报_03经营废物信息
     * @return 危险废物_经营年报_03经营废物信息集合
     */
    PageT<BusinessYearReportWaste> findBusinessYearReportWastePage(PageT<BusinessYearReportWaste> page, BusinessYearReportWaste businessYearReportWaste);
}
