package com.zsgf.platform.service.impl.businessReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessReport.BusinessDayReportMapper;
import com.zsgf.platform.model.entity.businessReport.BusinessDayReport;
import com.zsgf.platform.service.businessReport.BusinessDayReportService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营日报_01基本信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessDayReportServiceImpl extends ServiceImpl<BusinessDayReportMapper, BusinessDayReport> implements BusinessDayReportService {


    /**
     * 新增危险废物_经营日报_01基本信息
     *
     * @param businessDayReport 危险废物_经营日报_01基本信息
     * @return 结果
     */
    @Override
    public boolean saveBusinessDayReport(BusinessDayReport businessDayReport) {
        return this.save(businessDayReport);
    }

    /**
     * 修改危险废物_经营日报_01基本信息
     *
     * @param businessDayReport 危险废物_经营日报_01基本信息
     * @return 结果
     */
    @Override
    public boolean updateBusinessDayReport(BusinessDayReport businessDayReport) {
        return this.updateById(businessDayReport);
    }

    /**
     * 删除危险废物_经营日报_01基本信息信息
     *
     * @param id 危险废物_经营日报_01基本信息ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessDayReport(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营日报_01基本信息
     *
     * @param id 危险废物_经营日报_01基本信息ID
     * @return 危险废物_经营日报_01基本信息
     */
    @Override
    public BusinessDayReport findBusinessDayReportById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营日报_01基本信息列表
     *
     * @param businessDayReport 危险废物_经营日报_01基本信息
     * @return 危险废物_经营日报_01基本信息
     */
    @Override
    public List<BusinessDayReport> findBusinessDayReportList(BusinessDayReport businessDayReport) {
        LambdaQueryWrapper<BusinessDayReport> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营日报_01基本信息
     *
     * @param page              分页参数
     * @param businessDayReport 危险废物_经营日报_01基本信息
     * @return 危险废物_经营日报_01基本信息
     */
    @Override
    public PageT<BusinessDayReport> findBusinessDayReportPage(PageT<BusinessDayReport> page, BusinessDayReport businessDayReport) {
        LambdaQueryWrapper<BusinessDayReport> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
