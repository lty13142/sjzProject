package com.zsgf.platform.service.impl.standardize;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.standardize.WasteStandardizedAssessmentReportMapper;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentReport;
import com.zsgf.platform.service.standardize.WasteStandardizedAssessmentReportService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标准化考核整改报告Service业务层处理
 *
 * @author gzl
 * @date 2023-03-24
 */
@Service
public class WasteStandardizedAssessmentReportServiceImpl extends ServiceImpl<WasteStandardizedAssessmentReportMapper, WasteStandardizedAssessmentReport> implements WasteStandardizedAssessmentReportService {


    /**
     * 新增标准化考核整改报告
     *
     * @param wasteStandardizedAssessmentReport 标准化考核整改报告
     * @return 结果
     */
    @Override
    public boolean saveWasteStandardizedAssessmentReport(WasteStandardizedAssessmentReport wasteStandardizedAssessmentReport) {
        return this.save(wasteStandardizedAssessmentReport);
    }

    /**
     * 修改标准化考核整改报告
     *
     * @param wasteStandardizedAssessmentReport 标准化考核整改报告
     * @return 结果
     */
    @Override
    public boolean updateWasteStandardizedAssessmentReport(WasteStandardizedAssessmentReport wasteStandardizedAssessmentReport) {
        return this.updateById(wasteStandardizedAssessmentReport);
    }

    /**
     * 删除标准化考核整改报告信息
     *
     * @param id 标准化考核整改报告ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteStandardizedAssessmentReport(String id) {
        return this.removeById(id);
    }

    /**
     * 查询标准化考核整改报告
     *
     * @param id 标准化考核整改报告ID
     * @return 标准化考核整改报告
     */
    @Override
    public WasteStandardizedAssessmentReport findWasteStandardizedAssessmentReportById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询标准化考核整改报告列表
     *
     * @param wasteStandardizedAssessmentReport 标准化考核整改报告
     * @return 标准化考核整改报告
     */
    @Override
    public List<WasteStandardizedAssessmentReport> findWasteStandardizedAssessmentReportList(WasteStandardizedAssessmentReport wasteStandardizedAssessmentReport) {
        LambdaQueryWrapper<WasteStandardizedAssessmentReport> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询标准化考核整改报告
     *
     * @param page                              分页参数
     * @param wasteStandardizedAssessmentReport 标准化考核整改报告
     * @return 标准化考核整改报告
     */
    @Override
    public PageT<WasteStandardizedAssessmentReport> findWasteStandardizedAssessmentReportPage(PageT<WasteStandardizedAssessmentReport> page, WasteStandardizedAssessmentReport wasteStandardizedAssessmentReport) {
        LambdaQueryWrapper<WasteStandardizedAssessmentReport> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
