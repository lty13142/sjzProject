package com.zsgf.platform.service.businessReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessReport.BusinessDayReportWasteSecondaryTransfer;

import java.util.List;

/**
 * 危险废物_经营日报_03接收废物二次转移信息Service接口
 * 
 * @author gzl;
 * @date 2023-02-09
 */
public interface BusinessDayReportWasteSecondaryTransferService extends IService<BusinessDayReportWasteSecondaryTransfer>{

    /**
     * 新增危险废物_经营日报_03接收废物二次转移信息
     * 
     * @param businessDayReportWasteSecondaryTransfer 危险废物_经营日报_03接收废物二次转移信息
     * @return 结果
     */
    boolean saveBusinessDayReportWasteSecondaryTransfer(BusinessDayReportWasteSecondaryTransfer businessDayReportWasteSecondaryTransfer);

    /**
     * 修改危险废物_经营日报_03接收废物二次转移信息
     * 
     * @param businessDayReportWasteSecondaryTransfer 危险废物_经营日报_03接收废物二次转移信息
     * @return 结果
     */
    boolean updateBusinessDayReportWasteSecondaryTransfer(BusinessDayReportWasteSecondaryTransfer businessDayReportWasteSecondaryTransfer);

    /**
     * 删除危险废物_经营日报_03接收废物二次转移信息信息
     * 
     * @param id 危险废物_经营日报_03接收废物二次转移信息ID
     * @return 结果
     */
    boolean deleteBusinessDayReportWasteSecondaryTransfer(String id);

    /**
     * 查询危险废物_经营日报_03接收废物二次转移信息
     *
     * @param id 危险废物_经营日报_03接收废物二次转移信息ID
     * @return 危险废物_经营日报_03接收废物二次转移信息
     */
    BusinessDayReportWasteSecondaryTransfer findBusinessDayReportWasteSecondaryTransferById(String id);

    /**
     * 查询危险废物_经营日报_03接收废物二次转移信息列表
     *
     * @param businessDayReportWasteSecondaryTransfer 危险废物_经营日报_03接收废物二次转移信息
     * @return 危险废物_经营日报_03接收废物二次转移信息集合
     */
    List<BusinessDayReportWasteSecondaryTransfer> findBusinessDayReportWasteSecondaryTransferList(BusinessDayReportWasteSecondaryTransfer businessDayReportWasteSecondaryTransfer);

    /**
     * 分页查询危险废物_经营日报_03接收废物二次转移信息列表
     * @param page 分页参数
     * @param businessDayReportWasteSecondaryTransfer 危险废物_经营日报_03接收废物二次转移信息
     * @return 危险废物_经营日报_03接收废物二次转移信息集合
     */
    PageT<BusinessDayReportWasteSecondaryTransfer> findBusinessDayReportWasteSecondaryTransferPage(PageT<BusinessDayReportWasteSecondaryTransfer> page, BusinessDayReportWasteSecondaryTransfer businessDayReportWasteSecondaryTransfer);
}
