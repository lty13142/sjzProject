package com.zsgf.platform.service.wasteReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReportMainAuxiliaryMaterials;

import java.util.List;

/**
 * 危险废物_产生年报_06主要原辅材料及能源Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface WasteYearReportMainAuxiliaryMaterialsService extends IService<WasteYearReportMainAuxiliaryMaterials> {

    /**
     * 新增危险废物_产生年报_06主要原辅材料及能源
     *
     * @param wasteYearReportMainAuxiliaryMaterials 危险废物_产生年报_06主要原辅材料及能源
     * @return 结果
     */
    boolean saveWasteYearReportMainAuxiliaryMaterials(WasteYearReportMainAuxiliaryMaterials wasteYearReportMainAuxiliaryMaterials);

    /**
     * 修改危险废物_产生年报_06主要原辅材料及能源
     *
     * @param wasteYearReportMainAuxiliaryMaterials 危险废物_产生年报_06主要原辅材料及能源
     * @return 结果
     */
    boolean updateWasteYearReportMainAuxiliaryMaterials(WasteYearReportMainAuxiliaryMaterials wasteYearReportMainAuxiliaryMaterials);

    /**
     * 删除危险废物_产生年报_06主要原辅材料及能源信息
     *
     * @param id 危险废物_产生年报_06主要原辅材料及能源ID
     * @return 结果
     */
    boolean deleteWasteYearReportMainAuxiliaryMaterials(String id);

    /**
     * 查询危险废物_产生年报_06主要原辅材料及能源
     *
     * @param id 危险废物_产生年报_06主要原辅材料及能源ID
     * @return 危险废物_产生年报_06主要原辅材料及能源
     */
    WasteYearReportMainAuxiliaryMaterials findWasteYearReportMainAuxiliaryMaterialsById(String id);

    /**
     * 查询危险废物_产生年报_06主要原辅材料及能源列表
     *
     * @param wasteYearReportMainAuxiliaryMaterials 危险废物_产生年报_06主要原辅材料及能源
     * @return 危险废物_产生年报_06主要原辅材料及能源集合
     */
    List<WasteYearReportMainAuxiliaryMaterials> findWasteYearReportMainAuxiliaryMaterialsList(WasteYearReportMainAuxiliaryMaterials wasteYearReportMainAuxiliaryMaterials);

    /**
     * 分页查询危险废物_产生年报_06主要原辅材料及能源列表
     *
     * @param page                                  分页参数
     * @param wasteYearReportMainAuxiliaryMaterials 危险废物_产生年报_06主要原辅材料及能源
     * @return 危险废物_产生年报_06主要原辅材料及能源集合
     */
    PageT<WasteYearReportMainAuxiliaryMaterials> findWasteYearReportMainAuxiliaryMaterialsPage(PageT<WasteYearReportMainAuxiliaryMaterials> page, WasteYearReportMainAuxiliaryMaterials wasteYearReportMainAuxiliaryMaterials);
}
