package com.zsgf.platform.service.impl.businessReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessReport.BusinessYearReportNewWasteOutsourcingDisposalMapper;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportNewWasteOutsourcingDisposal;
import com.zsgf.platform.service.businessReport.BusinessYearReportNewWasteOutsourcingDisposalService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营年报_09新产生危废委外处置Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessYearReportNewWasteOutsourcingDisposalServiceImpl
        extends ServiceImpl<BusinessYearReportNewWasteOutsourcingDisposalMapper, BusinessYearReportNewWasteOutsourcingDisposal>
        implements BusinessYearReportNewWasteOutsourcingDisposalService {


    /**
     * 新增危险废物_经营年报_09新产生危废委外处置
     *
     * @param businessYearReportNewWasteOutsourcingDisposal 危险废物_经营年报_09新产生危废委外处置
     * @return 结果
     */
    @Override
    public boolean saveBusinessYearReportNewWasteOutsourcingDisposal(BusinessYearReportNewWasteOutsourcingDisposal businessYearReportNewWasteOutsourcingDisposal) {
        return this.save(businessYearReportNewWasteOutsourcingDisposal);
    }

    /**
     * 修改危险废物_经营年报_09新产生危废委外处置
     *
     * @param businessYearReportNewWasteOutsourcingDisposal 危险废物_经营年报_09新产生危废委外处置
     * @return 结果
     */
    @Override
    public boolean updateBusinessYearReportNewWasteOutsourcingDisposal(BusinessYearReportNewWasteOutsourcingDisposal businessYearReportNewWasteOutsourcingDisposal) {
        return this.updateById(businessYearReportNewWasteOutsourcingDisposal);
    }

    /**
     * 删除危险废物_经营年报_09新产生危废委外处置信息
     *
     * @param id 危险废物_经营年报_09新产生危废委外处置ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessYearReportNewWasteOutsourcingDisposal(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营年报_09新产生危废委外处置
     *
     * @param id 危险废物_经营年报_09新产生危废委外处置ID
     * @return 危险废物_经营年报_09新产生危废委外处置
     */
    @Override
    public BusinessYearReportNewWasteOutsourcingDisposal findBusinessYearReportNewWasteOutsourcingDisposalById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营年报_09新产生危废委外处置列表
     *
     * @param businessYearReportNewWasteOutsourcingDisposal 危险废物_经营年报_09新产生危废委外处置
     * @return 危险废物_经营年报_09新产生危废委外处置
     */
    @Override
    public List<BusinessYearReportNewWasteOutsourcingDisposal> findBusinessYearReportNewWasteOutsourcingDisposalList(BusinessYearReportNewWasteOutsourcingDisposal businessYearReportNewWasteOutsourcingDisposal) {
        LambdaQueryWrapper<BusinessYearReportNewWasteOutsourcingDisposal> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营年报_09新产生危废委外处置
     *
     * @param page                                          分页参数
     * @param businessYearReportNewWasteOutsourcingDisposal 危险废物_经营年报_09新产生危废委外处置
     * @return 危险废物_经营年报_09新产生危废委外处置
     */
    @Override
    public PageT<BusinessYearReportNewWasteOutsourcingDisposal> findBusinessYearReportNewWasteOutsourcingDisposalPage(PageT<BusinessYearReportNewWasteOutsourcingDisposal> page, BusinessYearReportNewWasteOutsourcingDisposal businessYearReportNewWasteOutsourcingDisposal) {
        LambdaQueryWrapper<BusinessYearReportNewWasteOutsourcingDisposal> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
