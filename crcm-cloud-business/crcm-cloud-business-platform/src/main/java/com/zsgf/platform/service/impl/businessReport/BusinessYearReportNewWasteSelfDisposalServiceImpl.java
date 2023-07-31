package com.zsgf.platform.service.impl.businessReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessReport.BusinessYearReportNewWasteSelfDisposalMapper;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportNewWasteSelfDisposal;
import com.zsgf.platform.service.businessReport.BusinessYearReportNewWasteSelfDisposalService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营年报_08新产生危废自行处置Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessYearReportNewWasteSelfDisposalServiceImpl
        extends ServiceImpl<BusinessYearReportNewWasteSelfDisposalMapper, BusinessYearReportNewWasteSelfDisposal>
        implements BusinessYearReportNewWasteSelfDisposalService {


    /**
     * 新增危险废物_经营年报_08新产生危废自行处置
     *
     * @param businessYearReportNewWasteSelfDisposal 危险废物_经营年报_08新产生危废自行处置
     * @return 结果
     */
    @Override
    public boolean saveBusinessYearReportNewWasteSelfDisposal(BusinessYearReportNewWasteSelfDisposal businessYearReportNewWasteSelfDisposal) {
        return this.save(businessYearReportNewWasteSelfDisposal);
    }

    /**
     * 修改危险废物_经营年报_08新产生危废自行处置
     *
     * @param businessYearReportNewWasteSelfDisposal 危险废物_经营年报_08新产生危废自行处置
     * @return 结果
     */
    @Override
    public boolean updateBusinessYearReportNewWasteSelfDisposal(BusinessYearReportNewWasteSelfDisposal businessYearReportNewWasteSelfDisposal) {
        return this.updateById(businessYearReportNewWasteSelfDisposal);
    }

    /**
     * 删除危险废物_经营年报_08新产生危废自行处置信息
     *
     * @param id 危险废物_经营年报_08新产生危废自行处置ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessYearReportNewWasteSelfDisposal(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营年报_08新产生危废自行处置
     *
     * @param id 危险废物_经营年报_08新产生危废自行处置ID
     * @return 危险废物_经营年报_08新产生危废自行处置
     */
    @Override
    public BusinessYearReportNewWasteSelfDisposal findBusinessYearReportNewWasteSelfDisposalById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营年报_08新产生危废自行处置列表
     *
     * @param businessYearReportNewWasteSelfDisposal 危险废物_经营年报_08新产生危废自行处置
     * @return 危险废物_经营年报_08新产生危废自行处置
     */
    @Override
    public List<BusinessYearReportNewWasteSelfDisposal> findBusinessYearReportNewWasteSelfDisposalList(BusinessYearReportNewWasteSelfDisposal businessYearReportNewWasteSelfDisposal) {
        LambdaQueryWrapper<BusinessYearReportNewWasteSelfDisposal> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营年报_08新产生危废自行处置
     *
     * @param page                                   分页参数
     * @param businessYearReportNewWasteSelfDisposal 危险废物_经营年报_08新产生危废自行处置
     * @return 危险废物_经营年报_08新产生危废自行处置
     */
    @Override
    public PageT<BusinessYearReportNewWasteSelfDisposal> findBusinessYearReportNewWasteSelfDisposalPage(PageT<BusinessYearReportNewWasteSelfDisposal> page, BusinessYearReportNewWasteSelfDisposal businessYearReportNewWasteSelfDisposal) {
        LambdaQueryWrapper<BusinessYearReportNewWasteSelfDisposal> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
