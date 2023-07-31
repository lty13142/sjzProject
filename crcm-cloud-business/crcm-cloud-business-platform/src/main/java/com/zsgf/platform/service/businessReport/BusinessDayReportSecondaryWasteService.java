package com.zsgf.platform.service.businessReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessReport.BusinessDayReportSecondaryWaste;

import java.util.List;

/**
 * 危险废物_经营日报_04次生废物信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessDayReportSecondaryWasteService extends IService<BusinessDayReportSecondaryWaste> {

    /**
     * 新增危险废物_经营日报_04次生废物信息
     *
     * @param businessDayReportSecondaryWaste 危险废物_经营日报_04次生废物信息
     * @return 结果
     */
    boolean saveBusinessDayReportSecondaryWaste(BusinessDayReportSecondaryWaste businessDayReportSecondaryWaste);

    /**
     * 修改危险废物_经营日报_04次生废物信息
     *
     * @param businessDayReportSecondaryWaste 危险废物_经营日报_04次生废物信息
     * @return 结果
     */
    boolean updateBusinessDayReportSecondaryWaste(BusinessDayReportSecondaryWaste businessDayReportSecondaryWaste);

    /**
     * 删除危险废物_经营日报_04次生废物信息信息
     *
     * @param id 危险废物_经营日报_04次生废物信息ID
     * @return 结果
     */
    boolean deleteBusinessDayReportSecondaryWaste(String id);

    /**
     * 查询危险废物_经营日报_04次生废物信息
     *
     * @param id 危险废物_经营日报_04次生废物信息ID
     * @return 危险废物_经营日报_04次生废物信息
     */
    BusinessDayReportSecondaryWaste findBusinessDayReportSecondaryWasteById(String id);

    /**
     * 查询危险废物_经营日报_04次生废物信息列表
     *
     * @param businessDayReportSecondaryWaste 危险废物_经营日报_04次生废物信息
     * @return 危险废物_经营日报_04次生废物信息集合
     */
    List<BusinessDayReportSecondaryWaste> findBusinessDayReportSecondaryWasteList(BusinessDayReportSecondaryWaste businessDayReportSecondaryWaste);

    /**
     * 分页查询危险废物_经营日报_04次生废物信息列表
     *
     * @param page                            分页参数
     * @param businessDayReportSecondaryWaste 危险废物_经营日报_04次生废物信息
     * @return 危险废物_经营日报_04次生废物信息集合
     */
    PageT<BusinessDayReportSecondaryWaste> findBusinessDayReportSecondaryWastePage(PageT<BusinessDayReportSecondaryWaste> page, BusinessDayReportSecondaryWaste businessDayReportSecondaryWaste);
}
