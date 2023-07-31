package com.zsgf.platform.service.impl.businessReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessReport.BusinessYearReportWasteOutsourcingDisposalMapper;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportWasteOutsourcingDisposal;
import com.zsgf.platform.service.businessReport.BusinessYearReportWasteOutsourcingDisposalService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营年报_05废物委外处置情况Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessYearReportWasteOutsourcingDisposalServiceImpl
        extends ServiceImpl<BusinessYearReportWasteOutsourcingDisposalMapper, BusinessYearReportWasteOutsourcingDisposal>
        implements BusinessYearReportWasteOutsourcingDisposalService {


    /**
     * 新增危险废物_经营年报_05废物委外处置情况
     *
     * @param businessYearReportWasteOutsourcingDisposal 危险废物_经营年报_05废物委外处置情况
     * @return 结果
     */
    @Override
    public boolean saveBusinessYearReportWasteOutsourcingDisposal(BusinessYearReportWasteOutsourcingDisposal businessYearReportWasteOutsourcingDisposal) {
        return this.save(businessYearReportWasteOutsourcingDisposal);
    }

    /**
     * 修改危险废物_经营年报_05废物委外处置情况
     *
     * @param businessYearReportWasteOutsourcingDisposal 危险废物_经营年报_05废物委外处置情况
     * @return 结果
     */
    @Override
    public boolean updateBusinessYearReportWasteOutsourcingDisposal(BusinessYearReportWasteOutsourcingDisposal businessYearReportWasteOutsourcingDisposal) {
        return this.updateById(businessYearReportWasteOutsourcingDisposal);
    }

    /**
     * 删除危险废物_经营年报_05废物委外处置情况信息
     *
     * @param id 危险废物_经营年报_05废物委外处置情况ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessYearReportWasteOutsourcingDisposal(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营年报_05废物委外处置情况
     *
     * @param id 危险废物_经营年报_05废物委外处置情况ID
     * @return 危险废物_经营年报_05废物委外处置情况
     */
    @Override
    public BusinessYearReportWasteOutsourcingDisposal findBusinessYearReportWasteOutsourcingDisposalById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营年报_05废物委外处置情况列表
     *
     * @param businessYearReportWasteOutsourcingDisposal 危险废物_经营年报_05废物委外处置情况
     * @return 危险废物_经营年报_05废物委外处置情况
     */
    @Override
    public List<BusinessYearReportWasteOutsourcingDisposal> findBusinessYearReportWasteOutsourcingDisposalList(BusinessYearReportWasteOutsourcingDisposal businessYearReportWasteOutsourcingDisposal) {
        LambdaQueryWrapper<BusinessYearReportWasteOutsourcingDisposal> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营年报_05废物委外处置情况
     *
     * @param page                                       分页参数
     * @param businessYearReportWasteOutsourcingDisposal 危险废物_经营年报_05废物委外处置情况
     * @return 危险废物_经营年报_05废物委外处置情况
     */
    @Override
    public PageT<BusinessYearReportWasteOutsourcingDisposal> findBusinessYearReportWasteOutsourcingDisposalPage(PageT<BusinessYearReportWasteOutsourcingDisposal> page, BusinessYearReportWasteOutsourcingDisposal businessYearReportWasteOutsourcingDisposal) {
        LambdaQueryWrapper<BusinessYearReportWasteOutsourcingDisposal> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
