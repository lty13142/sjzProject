package com.zsgf.platform.service.impl.wasteReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.wasteReport.WasteMonthReportStorageMapper;
import com.zsgf.platform.model.entity.wasteReport.WasteMonthReportStorage;
import com.zsgf.platform.service.wasteReport.WasteMonthReportStorageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_产生月报_05产废贮存信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class WasteMonthReportStorageServiceImpl extends ServiceImpl<WasteMonthReportStorageMapper, WasteMonthReportStorage>
        implements WasteMonthReportStorageService {


    /**
     * 新增危险废物_产生月报_05产废贮存信息
     *
     * @param wasteMonthReportStorage 危险废物_产生月报_05产废贮存信息
     * @return 结果
     */
    @Override
    public boolean saveWasteMonthReportStorage(WasteMonthReportStorage wasteMonthReportStorage) {
        return this.save(wasteMonthReportStorage);
    }

    /**
     * 修改危险废物_产生月报_05产废贮存信息
     *
     * @param wasteMonthReportStorage 危险废物_产生月报_05产废贮存信息
     * @return 结果
     */
    @Override
    public boolean updateWasteMonthReportStorage(WasteMonthReportStorage wasteMonthReportStorage) {
        return this.updateById(wasteMonthReportStorage);
    }

    /**
     * 删除危险废物_产生月报_05产废贮存信息信息
     *
     * @param id 危险废物_产生月报_05产废贮存信息ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteMonthReportStorage(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_产生月报_05产废贮存信息
     *
     * @param id 危险废物_产生月报_05产废贮存信息ID
     * @return 危险废物_产生月报_05产废贮存信息
     */
    @Override
    public WasteMonthReportStorage findWasteMonthReportStorageById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_产生月报_05产废贮存信息列表
     *
     * @param wasteMonthReportStorage 危险废物_产生月报_05产废贮存信息
     * @return 危险废物_产生月报_05产废贮存信息
     */
    @Override
    public List<WasteMonthReportStorage> findWasteMonthReportStorageList(WasteMonthReportStorage wasteMonthReportStorage) {
        LambdaQueryWrapper<WasteMonthReportStorage> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_产生月报_05产废贮存信息
     *
     * @param page                    分页参数
     * @param wasteMonthReportStorage 危险废物_产生月报_05产废贮存信息
     * @return 危险废物_产生月报_05产废贮存信息
     */
    @Override
    public PageT<WasteMonthReportStorage> findWasteMonthReportStoragePage(PageT<WasteMonthReportStorage> page, WasteMonthReportStorage wasteMonthReportStorage) {
        LambdaQueryWrapper<WasteMonthReportStorage> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
