package com.zsgf.platform.service.impl.businessReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessReport.BusinessYearReportNewWasteMapper;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportNewWaste;
import com.zsgf.platform.service.businessReport.BusinessYearReportNewWasteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营年报_07新产生危废情况Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessYearReportNewWasteServiceImpl extends ServiceImpl<BusinessYearReportNewWasteMapper, BusinessYearReportNewWaste>
        implements BusinessYearReportNewWasteService {


    /**
     * 新增危险废物_经营年报_07新产生危废情况
     *
     * @param businessYearReportNewWaste 危险废物_经营年报_07新产生危废情况
     * @return 结果
     */
    @Override
    public boolean saveBusinessYearReportNewWaste(BusinessYearReportNewWaste businessYearReportNewWaste) {
        return this.save(businessYearReportNewWaste);
    }

    /**
     * 修改危险废物_经营年报_07新产生危废情况
     *
     * @param businessYearReportNewWaste 危险废物_经营年报_07新产生危废情况
     * @return 结果
     */
    @Override
    public boolean updateBusinessYearReportNewWaste(BusinessYearReportNewWaste businessYearReportNewWaste) {
        return this.updateById(businessYearReportNewWaste);
    }

    /**
     * 删除危险废物_经营年报_07新产生危废情况信息
     *
     * @param id 危险废物_经营年报_07新产生危废情况ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessYearReportNewWaste(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营年报_07新产生危废情况
     *
     * @param id 危险废物_经营年报_07新产生危废情况ID
     * @return 危险废物_经营年报_07新产生危废情况
     */
    @Override
    public BusinessYearReportNewWaste findBusinessYearReportNewWasteById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营年报_07新产生危废情况列表
     *
     * @param businessYearReportNewWaste 危险废物_经营年报_07新产生危废情况
     * @return 危险废物_经营年报_07新产生危废情况
     */
    @Override
    public List<BusinessYearReportNewWaste> findBusinessYearReportNewWasteList(BusinessYearReportNewWaste businessYearReportNewWaste) {
        LambdaQueryWrapper<BusinessYearReportNewWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营年报_07新产生危废情况
     *
     * @param page                       分页参数
     * @param businessYearReportNewWaste 危险废物_经营年报_07新产生危废情况
     * @return 危险废物_经营年报_07新产生危废情况
     */
    @Override
    public PageT<BusinessYearReportNewWaste> findBusinessYearReportNewWastePage(PageT<BusinessYearReportNewWaste> page, BusinessYearReportNewWaste businessYearReportNewWaste) {
        LambdaQueryWrapper<BusinessYearReportNewWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
