package com.zsgf.platform.service.businessReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportApprovedScale;

import java.util.List;

/**
 * 危险废物_经营年报_02许可证核准年经营规模Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessYearReportApprovedScaleService extends IService<BusinessYearReportApprovedScale> {

    /**
     * 新增危险废物_经营年报_02许可证核准年经营规模
     *
     * @param businessYearReportApprovedScale 危险废物_经营年报_02许可证核准年经营规模
     * @return 结果
     */
    boolean saveBusinessYearReportApprovedScale(BusinessYearReportApprovedScale businessYearReportApprovedScale);

    /**
     * 修改危险废物_经营年报_02许可证核准年经营规模
     *
     * @param businessYearReportApprovedScale 危险废物_经营年报_02许可证核准年经营规模
     * @return 结果
     */
    boolean updateBusinessYearReportApprovedScale(BusinessYearReportApprovedScale businessYearReportApprovedScale);

    /**
     * 删除危险废物_经营年报_02许可证核准年经营规模信息
     *
     * @param id 危险废物_经营年报_02许可证核准年经营规模ID
     * @return 结果
     */
    boolean deleteBusinessYearReportApprovedScale(String id);

    /**
     * 查询危险废物_经营年报_02许可证核准年经营规模
     *
     * @param id 危险废物_经营年报_02许可证核准年经营规模ID
     * @return 危险废物_经营年报_02许可证核准年经营规模
     */
    BusinessYearReportApprovedScale findBusinessYearReportApprovedScaleById(String id);

    /**
     * 查询危险废物_经营年报_02许可证核准年经营规模列表
     *
     * @param businessYearReportApprovedScale 危险废物_经营年报_02许可证核准年经营规模
     * @return 危险废物_经营年报_02许可证核准年经营规模集合
     */
    List<BusinessYearReportApprovedScale> findBusinessYearReportApprovedScaleList(BusinessYearReportApprovedScale businessYearReportApprovedScale);

    /**
     * 分页查询危险废物_经营年报_02许可证核准年经营规模列表
     *
     * @param page                            分页参数
     * @param businessYearReportApprovedScale 危险废物_经营年报_02许可证核准年经营规模
     * @return 危险废物_经营年报_02许可证核准年经营规模集合
     */
    PageT<BusinessYearReportApprovedScale> findBusinessYearReportApprovedScalePage(PageT<BusinessYearReportApprovedScale> page, BusinessYearReportApprovedScale businessYearReportApprovedScale);
}
