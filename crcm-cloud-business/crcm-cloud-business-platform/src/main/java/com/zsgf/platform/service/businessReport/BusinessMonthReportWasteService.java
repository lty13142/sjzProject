package com.zsgf.platform.service.businessReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessReport.BusinessMonthReportWaste;

import java.util.List;

/**
 * 危险废物_经营月报_02经营废物信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessMonthReportWasteService extends IService<BusinessMonthReportWaste> {

    /**
     * 新增危险废物_经营月报_02经营废物信息
     *
     * @param businessMonthReportWaste 危险废物_经营月报_02经营废物信息
     * @return 结果
     */
    boolean saveBusinessMonthReportWaste(BusinessMonthReportWaste businessMonthReportWaste);

    /**
     * 修改危险废物_经营月报_02经营废物信息
     *
     * @param businessMonthReportWaste 危险废物_经营月报_02经营废物信息
     * @return 结果
     */
    boolean updateBusinessMonthReportWaste(BusinessMonthReportWaste businessMonthReportWaste);

    /**
     * 删除危险废物_经营月报_02经营废物信息信息
     *
     * @param id 危险废物_经营月报_02经营废物信息ID
     * @return 结果
     */
    boolean deleteBusinessMonthReportWaste(String id);

    /**
     * 查询危险废物_经营月报_02经营废物信息
     *
     * @param id 危险废物_经营月报_02经营废物信息ID
     * @return 危险废物_经营月报_02经营废物信息
     */
    BusinessMonthReportWaste findBusinessMonthReportWasteById(String id);

    /**
     * 查询危险废物_经营月报_02经营废物信息列表
     *
     * @param businessMonthReportWaste 危险废物_经营月报_02经营废物信息
     * @return 危险废物_经营月报_02经营废物信息集合
     */
    List<BusinessMonthReportWaste> findBusinessMonthReportWasteList(BusinessMonthReportWaste businessMonthReportWaste);

    /**
     * 分页查询危险废物_经营月报_02经营废物信息列表
     *
     * @param page                     分页参数
     * @param businessMonthReportWaste 危险废物_经营月报_02经营废物信息
     * @return 危险废物_经营月报_02经营废物信息集合
     */
    PageT<BusinessMonthReportWaste> findBusinessMonthReportWastePage(PageT<BusinessMonthReportWaste> page, BusinessMonthReportWaste businessMonthReportWaste);
}
