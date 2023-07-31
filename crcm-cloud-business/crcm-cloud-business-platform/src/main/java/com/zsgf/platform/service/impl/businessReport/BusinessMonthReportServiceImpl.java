package com.zsgf.platform.service.impl.businessReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessReport.BusinessMonthReportMapper;
import com.zsgf.platform.model.entity.businessReport.BusinessMonthReport;
import com.zsgf.platform.service.businessReport.BusinessMonthReportService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营月报_01基本信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessMonthReportServiceImpl extends ServiceImpl<BusinessMonthReportMapper, BusinessMonthReport> implements BusinessMonthReportService {


    /**
     * 新增危险废物_经营月报_01基本信息
     *
     * @param businessMonthReport 危险废物_经营月报_01基本信息
     * @return 结果
     */
    @Override
    public boolean saveBusinessMonthReport(BusinessMonthReport businessMonthReport) {
        return this.save(businessMonthReport);
    }

    /**
     * 修改危险废物_经营月报_01基本信息
     *
     * @param businessMonthReport 危险废物_经营月报_01基本信息
     * @return 结果
     */
    @Override
    public boolean updateBusinessMonthReport(BusinessMonthReport businessMonthReport) {
        return this.updateById(businessMonthReport);
    }

    /**
     * 删除危险废物_经营月报_01基本信息信息
     *
     * @param id 危险废物_经营月报_01基本信息ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessMonthReport(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营月报_01基本信息
     *
     * @param id 危险废物_经营月报_01基本信息ID
     * @return 危险废物_经营月报_01基本信息
     */
    @Override
    public BusinessMonthReport findBusinessMonthReportById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营月报_01基本信息列表
     *
     * @param businessMonthReport 危险废物_经营月报_01基本信息
     * @return 危险废物_经营月报_01基本信息
     */
    @Override
    public List<BusinessMonthReport> findBusinessMonthReportList(BusinessMonthReport businessMonthReport) {
        LambdaQueryWrapper<BusinessMonthReport> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营月报_01基本信息
     *
     * @param page                分页参数
     * @param businessMonthReport 危险废物_经营月报_01基本信息
     * @return 危险废物_经营月报_01基本信息
     */
    @Override
    public PageT<BusinessMonthReport> findBusinessMonthReportPage(PageT<BusinessMonthReport> page, BusinessMonthReport businessMonthReport) {
        LambdaQueryWrapper<BusinessMonthReport> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
