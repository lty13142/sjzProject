package com.zsgf.platform.service.businessReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessReport.BusinessMonthReportSecondaryWaste;

import java.util.List;

/**
 * 危险废物_经营月报_03次生废物信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessMonthReportSecondaryWasteService extends IService<BusinessMonthReportSecondaryWaste> {

    /**
     * 新增危险废物_经营月报_03次生废物信息
     *
     * @param businessMonthReportSecondaryWaste 危险废物_经营月报_03次生废物信息
     * @return 结果
     */
    boolean saveBusinessMonthReportSecondaryWaste(BusinessMonthReportSecondaryWaste businessMonthReportSecondaryWaste);

    /**
     * 修改危险废物_经营月报_03次生废物信息
     *
     * @param businessMonthReportSecondaryWaste 危险废物_经营月报_03次生废物信息
     * @return 结果
     */
    boolean updateBusinessMonthReportSecondaryWaste(BusinessMonthReportSecondaryWaste businessMonthReportSecondaryWaste);

    /**
     * 删除危险废物_经营月报_03次生废物信息信息
     *
     * @param id 危险废物_经营月报_03次生废物信息ID
     * @return 结果
     */
    boolean deleteBusinessMonthReportSecondaryWaste(String id);

    /**
     * 查询危险废物_经营月报_03次生废物信息
     *
     * @param id 危险废物_经营月报_03次生废物信息ID
     * @return 危险废物_经营月报_03次生废物信息
     */
    BusinessMonthReportSecondaryWaste findBusinessMonthReportSecondaryWasteById(String id);

    /**
     * 查询危险废物_经营月报_03次生废物信息列表
     *
     * @param businessMonthReportSecondaryWaste 危险废物_经营月报_03次生废物信息
     * @return 危险废物_经营月报_03次生废物信息集合
     */
    List<BusinessMonthReportSecondaryWaste> findBusinessMonthReportSecondaryWasteList(BusinessMonthReportSecondaryWaste businessMonthReportSecondaryWaste);

    /**
     * 分页查询危险废物_经营月报_03次生废物信息列表
     *
     * @param page                              分页参数
     * @param businessMonthReportSecondaryWaste 危险废物_经营月报_03次生废物信息
     * @return 危险废物_经营月报_03次生废物信息集合
     */
    PageT<BusinessMonthReportSecondaryWaste> findBusinessMonthReportSecondaryWastePage(PageT<BusinessMonthReportSecondaryWaste> page, BusinessMonthReportSecondaryWaste businessMonthReportSecondaryWaste);
}
