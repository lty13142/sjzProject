package com.zsgf.platform.service.impl.businessReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessReport.BusinessYearReportWasteUseDisposalMapper;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportWasteUseDisposal;
import com.zsgf.platform.service.businessReport.BusinessYearReportWasteUseDisposalService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营年报_04废物利用处置情况Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessYearReportWasteUseDisposalServiceImpl
        extends ServiceImpl<BusinessYearReportWasteUseDisposalMapper, BusinessYearReportWasteUseDisposal>
        implements BusinessYearReportWasteUseDisposalService {


    /**
     * 新增危险废物_经营年报_04废物利用处置情况
     *
     * @param businessYearReportWasteUseDisposal 危险废物_经营年报_04废物利用处置情况
     * @return 结果
     */
    @Override
    public boolean saveBusinessYearReportWasteUseDisposal(BusinessYearReportWasteUseDisposal businessYearReportWasteUseDisposal) {
        return this.save(businessYearReportWasteUseDisposal);
    }

    /**
     * 修改危险废物_经营年报_04废物利用处置情况
     *
     * @param businessYearReportWasteUseDisposal 危险废物_经营年报_04废物利用处置情况
     * @return 结果
     */
    @Override
    public boolean updateBusinessYearReportWasteUseDisposal(BusinessYearReportWasteUseDisposal businessYearReportWasteUseDisposal) {
        return this.updateById(businessYearReportWasteUseDisposal);
    }

    /**
     * 删除危险废物_经营年报_04废物利用处置情况信息
     *
     * @param id 危险废物_经营年报_04废物利用处置情况ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessYearReportWasteUseDisposal(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营年报_04废物利用处置情况
     *
     * @param id 危险废物_经营年报_04废物利用处置情况ID
     * @return 危险废物_经营年报_04废物利用处置情况
     */
    @Override
    public BusinessYearReportWasteUseDisposal findBusinessYearReportWasteUseDisposalById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营年报_04废物利用处置情况列表
     *
     * @param businessYearReportWasteUseDisposal 危险废物_经营年报_04废物利用处置情况
     * @return 危险废物_经营年报_04废物利用处置情况
     */
    @Override
    public List<BusinessYearReportWasteUseDisposal> findBusinessYearReportWasteUseDisposalList(BusinessYearReportWasteUseDisposal businessYearReportWasteUseDisposal) {
        LambdaQueryWrapper<BusinessYearReportWasteUseDisposal> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营年报_04废物利用处置情况
     *
     * @param page                               分页参数
     * @param businessYearReportWasteUseDisposal 危险废物_经营年报_04废物利用处置情况
     * @return 危险废物_经营年报_04废物利用处置情况
     */
    @Override
    public PageT<BusinessYearReportWasteUseDisposal> findBusinessYearReportWasteUseDisposalPage(PageT<BusinessYearReportWasteUseDisposal> page, BusinessYearReportWasteUseDisposal businessYearReportWasteUseDisposal) {
        LambdaQueryWrapper<BusinessYearReportWasteUseDisposal> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
