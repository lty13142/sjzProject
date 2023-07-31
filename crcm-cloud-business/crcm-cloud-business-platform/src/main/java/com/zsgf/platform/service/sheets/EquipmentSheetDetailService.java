package com.zsgf.platform.service.sheets;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.sheets.EquipmentSheetDetail;

import java.util.List;

/**
 * 设备确认单详情Service接口
 *
 * @author gzl
 * @date 2023-03-30
 */
public interface EquipmentSheetDetailService extends IService<EquipmentSheetDetail> {

    /**
     * 新增设备确认单详情
     *
     * @param equipmentSheetDetail 设备确认单详情
     * @return 结果
     */
    boolean saveEquipmentSheetDetail(EquipmentSheetDetail equipmentSheetDetail);

    /**
     * 修改设备确认单详情
     *
     * @param equipmentSheetDetail 设备确认单详情
     * @return 结果
     */
    boolean updateEquipmentSheetDetail(EquipmentSheetDetail equipmentSheetDetail);

    /**
     * 删除设备确认单详情信息
     *
     * @param id 设备确认单详情ID
     * @return 结果
     */
    boolean deleteEquipmentSheetDetail(Long id);

    /**
     * 查询设备确认单详情
     *
     * @param id 设备确认单详情ID
     * @return 设备确认单详情
     */
    EquipmentSheetDetail findEquipmentSheetDetailById(Long id);

    /**
     * 查询设备确认单详情列表
     *
     * @param equipmentSheetDetail 设备确认单详情
     * @return 设备确认单详情集合
     */
    List<EquipmentSheetDetail> findEquipmentSheetDetailList(EquipmentSheetDetail equipmentSheetDetail);

    /**
     * 分页查询设备确认单详情列表
     *
     * @param page                 分页参数
     * @param equipmentSheetDetail 设备确认单详情
     * @return 设备确认单详情集合
     */
    PageT<EquipmentSheetDetail> findEquipmentSheetDetailPage(PageT<EquipmentSheetDetail> page, EquipmentSheetDetail equipmentSheetDetail);

    /**
     * 删除设备确认单详情信息
     *
     * @param companyId 企业ID
     */
    void deleteByCompanyId(String companyId);
}
