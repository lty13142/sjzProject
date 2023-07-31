package com.zsgf.platform.service.impl.wasteReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.wasteReport.WasteYearReportWasteGenerationMapper;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReportWasteGeneration;
import com.zsgf.platform.service.wasteReport.WasteYearReportWasteGenerationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_产生年报_02废物产生情况Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class WasteYearReportWasteGenerationServiceImpl extends ServiceImpl<WasteYearReportWasteGenerationMapper, WasteYearReportWasteGeneration>
        implements WasteYearReportWasteGenerationService {


    /**
     * 新增危险废物_产生年报_02废物产生情况
     *
     * @param wasteYearReportWasteGeneration 危险废物_产生年报_02废物产生情况
     * @return 结果
     */
    @Override
    public boolean saveWasteYearReportWasteGeneration(WasteYearReportWasteGeneration wasteYearReportWasteGeneration) {
        return this.save(wasteYearReportWasteGeneration);
    }

    /**
     * 修改危险废物_产生年报_02废物产生情况
     *
     * @param wasteYearReportWasteGeneration 危险废物_产生年报_02废物产生情况
     * @return 结果
     */
    @Override
    public boolean updateWasteYearReportWasteGeneration(WasteYearReportWasteGeneration wasteYearReportWasteGeneration) {
        return this.updateById(wasteYearReportWasteGeneration);
    }

    /**
     * 删除危险废物_产生年报_02废物产生情况信息
     *
     * @param id 危险废物_产生年报_02废物产生情况ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteYearReportWasteGeneration(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_产生年报_02废物产生情况
     *
     * @param id 危险废物_产生年报_02废物产生情况ID
     * @return 危险废物_产生年报_02废物产生情况
     */
    @Override
    public WasteYearReportWasteGeneration findWasteYearReportWasteGenerationById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_产生年报_02废物产生情况列表
     *
     * @param wasteYearReportWasteGeneration 危险废物_产生年报_02废物产生情况
     * @return 危险废物_产生年报_02废物产生情况
     */
    @Override
    public List<WasteYearReportWasteGeneration> findWasteYearReportWasteGenerationList(WasteYearReportWasteGeneration wasteYearReportWasteGeneration) {
        LambdaQueryWrapper<WasteYearReportWasteGeneration> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_产生年报_02废物产生情况
     *
     * @param page                           分页参数
     * @param wasteYearReportWasteGeneration 危险废物_产生年报_02废物产生情况
     * @return 危险废物_产生年报_02废物产生情况
     */
    @Override
    public PageT<WasteYearReportWasteGeneration> findWasteYearReportWasteGenerationPage(PageT<WasteYearReportWasteGeneration> page, WasteYearReportWasteGeneration wasteYearReportWasteGeneration) {
        LambdaQueryWrapper<WasteYearReportWasteGeneration> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
