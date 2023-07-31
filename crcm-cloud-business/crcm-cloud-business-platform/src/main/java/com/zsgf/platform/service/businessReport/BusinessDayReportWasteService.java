package com.zsgf.platform.service.businessReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessReport.BusinessDayReportWaste;

import java.util.List;

/**
 * 危险废物_经营日报_02接收废物信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessDayReportWasteService extends IService<BusinessDayReportWaste> {

    /**
     * 新增危险废物_经营日报_02接收废物信息
     *
     * @param businessDayReportWaste 危险废物_经营日报_02接收废物信息
     * @return 结果
     */
    boolean saveBusinessDayReportWaste(BusinessDayReportWaste businessDayReportWaste);

    /**
     * 修改危险废物_经营日报_02接收废物信息
     *
     * @param businessDayReportWaste 危险废物_经营日报_02接收废物信息
     * @return 结果
     */
    boolean updateBusinessDayReportWaste(BusinessDayReportWaste businessDayReportWaste);

    /**
     * 删除危险废物_经营日报_02接收废物信息信息
     *
     * @param id 危险废物_经营日报_02接收废物信息ID
     * @return 结果
     */
    boolean deleteBusinessDayReportWaste(String id);

    /**
     * 查询危险废物_经营日报_02接收废物信息
     *
     * @param id 危险废物_经营日报_02接收废物信息ID
     * @return 危险废物_经营日报_02接收废物信息
     */
    BusinessDayReportWaste findBusinessDayReportWasteById(String id);

    /**
     * 查询危险废物_经营日报_02接收废物信息列表
     *
     * @param businessDayReportWaste 危险废物_经营日报_02接收废物信息
     * @return 危险废物_经营日报_02接收废物信息集合
     */
    List<BusinessDayReportWaste> findBusinessDayReportWasteList(BusinessDayReportWaste businessDayReportWaste);

    /**
     * 分页查询危险废物_经营日报_02接收废物信息列表
     *
     * @param page                   分页参数
     * @param businessDayReportWaste 危险废物_经营日报_02接收废物信息
     * @return 危险废物_经营日报_02接收废物信息集合
     */
    PageT<BusinessDayReportWaste> findBusinessDayReportWastePage(PageT<BusinessDayReportWaste> page, BusinessDayReportWaste businessDayReportWaste);
}
