package com.zsgf.platform.service.impl.wasteReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.wasteReport.WasteYearReportStorageMapper;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReportStorage;
import com.zsgf.platform.service.wasteReport.WasteYearReportStorageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_产生年报_05贮存情况Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class WasteYearReportStorageServiceImpl extends ServiceImpl<WasteYearReportStorageMapper, WasteYearReportStorage>
        implements WasteYearReportStorageService {


    /**
     * 新增危险废物_产生年报_05贮存情况
     *
     * @param wasteYearReportStorage 危险废物_产生年报_05贮存情况
     * @return 结果
     */
    @Override
    public boolean saveWasteYearReportStorage(WasteYearReportStorage wasteYearReportStorage) {
        return this.save(wasteYearReportStorage);
    }

    /**
     * 修改危险废物_产生年报_05贮存情况
     *
     * @param wasteYearReportStorage 危险废物_产生年报_05贮存情况
     * @return 结果
     */
    @Override
    public boolean updateWasteYearReportStorage(WasteYearReportStorage wasteYearReportStorage) {
        return this.updateById(wasteYearReportStorage);
    }

    /**
     * 删除危险废物_产生年报_05贮存情况信息
     *
     * @param id 危险废物_产生年报_05贮存情况ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteYearReportStorage(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_产生年报_05贮存情况
     *
     * @param id 危险废物_产生年报_05贮存情况ID
     * @return 危险废物_产生年报_05贮存情况
     */
    @Override
    public WasteYearReportStorage findWasteYearReportStorageById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_产生年报_05贮存情况列表
     *
     * @param wasteYearReportStorage 危险废物_产生年报_05贮存情况
     * @return 危险废物_产生年报_05贮存情况
     */
    @Override
    public List<WasteYearReportStorage> findWasteYearReportStorageList(WasteYearReportStorage wasteYearReportStorage) {
        LambdaQueryWrapper<WasteYearReportStorage> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_产生年报_05贮存情况
     *
     * @param page                   分页参数
     * @param wasteYearReportStorage 危险废物_产生年报_05贮存情况
     * @return 危险废物_产生年报_05贮存情况
     */
    @Override
    public PageT<WasteYearReportStorage> findWasteYearReportStoragePage(PageT<WasteYearReportStorage> page, WasteYearReportStorage wasteYearReportStorage) {
        LambdaQueryWrapper<WasteYearReportStorage> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
