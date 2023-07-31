package com.zsgf.platform.service.impl.wasteReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.wasteReport.WasteYearReportMainAuxiliaryMaterialsMapper;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReportMainAuxiliaryMaterials;
import com.zsgf.platform.service.wasteReport.WasteYearReportMainAuxiliaryMaterialsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_产生年报_06主要原辅材料及能源Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class WasteYearReportMainAuxiliaryMaterialsServiceImpl
        extends ServiceImpl<WasteYearReportMainAuxiliaryMaterialsMapper, WasteYearReportMainAuxiliaryMaterials>
        implements WasteYearReportMainAuxiliaryMaterialsService {


    /**
     * 新增危险废物_产生年报_06主要原辅材料及能源
     *
     * @param wasteYearReportMainAuxiliaryMaterials 危险废物_产生年报_06主要原辅材料及能源
     * @return 结果
     */
    @Override
    public boolean saveWasteYearReportMainAuxiliaryMaterials(WasteYearReportMainAuxiliaryMaterials wasteYearReportMainAuxiliaryMaterials) {
        return this.save(wasteYearReportMainAuxiliaryMaterials);
    }

    /**
     * 修改危险废物_产生年报_06主要原辅材料及能源
     *
     * @param wasteYearReportMainAuxiliaryMaterials 危险废物_产生年报_06主要原辅材料及能源
     * @return 结果
     */
    @Override
    public boolean updateWasteYearReportMainAuxiliaryMaterials(WasteYearReportMainAuxiliaryMaterials wasteYearReportMainAuxiliaryMaterials) {
        return this.updateById(wasteYearReportMainAuxiliaryMaterials);
    }

    /**
     * 删除危险废物_产生年报_06主要原辅材料及能源信息
     *
     * @param id 危险废物_产生年报_06主要原辅材料及能源ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteYearReportMainAuxiliaryMaterials(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_产生年报_06主要原辅材料及能源
     *
     * @param id 危险废物_产生年报_06主要原辅材料及能源ID
     * @return 危险废物_产生年报_06主要原辅材料及能源
     */
    @Override
    public WasteYearReportMainAuxiliaryMaterials findWasteYearReportMainAuxiliaryMaterialsById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_产生年报_06主要原辅材料及能源列表
     *
     * @param wasteYearReportMainAuxiliaryMaterials 危险废物_产生年报_06主要原辅材料及能源
     * @return 危险废物_产生年报_06主要原辅材料及能源
     */
    @Override
    public List<WasteYearReportMainAuxiliaryMaterials> findWasteYearReportMainAuxiliaryMaterialsList(WasteYearReportMainAuxiliaryMaterials wasteYearReportMainAuxiliaryMaterials) {
        LambdaQueryWrapper<WasteYearReportMainAuxiliaryMaterials> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_产生年报_06主要原辅材料及能源
     *
     * @param page                                  分页参数
     * @param wasteYearReportMainAuxiliaryMaterials 危险废物_产生年报_06主要原辅材料及能源
     * @return 危险废物_产生年报_06主要原辅材料及能源
     */
    @Override
    public PageT<WasteYearReportMainAuxiliaryMaterials> findWasteYearReportMainAuxiliaryMaterialsPage(PageT<WasteYearReportMainAuxiliaryMaterials> page, WasteYearReportMainAuxiliaryMaterials wasteYearReportMainAuxiliaryMaterials) {
        LambdaQueryWrapper<WasteYearReportMainAuxiliaryMaterials> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
