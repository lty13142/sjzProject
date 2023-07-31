package com.zsgf.platform.service.impl.businessReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessReport.BusinessYearReportMapper;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReport;
import com.zsgf.platform.service.businessReport.BusinessYearReportService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营年报_01基本信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessYearReportServiceImpl extends ServiceImpl<BusinessYearReportMapper, BusinessYearReport> implements BusinessYearReportService {


    /**
     * 新增危险废物_经营年报_01基本信息
     *
     * @param businessYearReport 危险废物_经营年报_01基本信息
     * @return 结果
     */
    @Override
    public boolean saveBusinessYearReport(BusinessYearReport businessYearReport) {
        return this.save(businessYearReport);
    }

    /**
     * 修改危险废物_经营年报_01基本信息
     *
     * @param businessYearReport 危险废物_经营年报_01基本信息
     * @return 结果
     */
    @Override
    public boolean updateBusinessYearReport(BusinessYearReport businessYearReport) {
        return this.updateById(businessYearReport);
    }

    /**
     * 删除危险废物_经营年报_01基本信息信息
     *
     * @param id 危险废物_经营年报_01基本信息ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessYearReport(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营年报_01基本信息
     *
     * @param id 危险废物_经营年报_01基本信息ID
     * @return 危险废物_经营年报_01基本信息
     */
    @Override
    public BusinessYearReport findBusinessYearReportById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营年报_01基本信息列表
     *
     * @param businessYearReport 危险废物_经营年报_01基本信息
     * @return 危险废物_经营年报_01基本信息
     */
    @Override
    public List<BusinessYearReport> findBusinessYearReportList(BusinessYearReport businessYearReport) {
        LambdaQueryWrapper<BusinessYearReport> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营年报_01基本信息
     *
     * @param page               分页参数
     * @param businessYearReport 危险废物_经营年报_01基本信息
     * @return 危险废物_经营年报_01基本信息
     */
    @Override
    public PageT<BusinessYearReport> findBusinessYearReportPage(PageT<BusinessYearReport> page, BusinessYearReport businessYearReport) {
        LambdaQueryWrapper<BusinessYearReport> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
