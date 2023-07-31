package com.zsgf.platform.service.impl.wasteReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.wasteReport.WasteYearReportSelfUseDisposalMapper;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReportSelfUseDisposal;
import com.zsgf.platform.service.wasteReport.WasteYearReportSelfUseDisposalService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_产生年报_03自行利用处置情况Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class WasteYearReportSelfUseDisposalServiceImpl extends ServiceImpl<WasteYearReportSelfUseDisposalMapper, WasteYearReportSelfUseDisposal>
        implements WasteYearReportSelfUseDisposalService {


    /**
     * 新增危险废物_产生年报_03自行利用处置情况
     *
     * @param wasteYearReportSelfUseDisposal 危险废物_产生年报_03自行利用处置情况
     * @return 结果
     */
    @Override
    public boolean saveWasteYearReportSelfUseDisposal(WasteYearReportSelfUseDisposal wasteYearReportSelfUseDisposal) {
        return this.save(wasteYearReportSelfUseDisposal);
    }

    /**
     * 修改危险废物_产生年报_03自行利用处置情况
     *
     * @param wasteYearReportSelfUseDisposal 危险废物_产生年报_03自行利用处置情况
     * @return 结果
     */
    @Override
    public boolean updateWasteYearReportSelfUseDisposal(WasteYearReportSelfUseDisposal wasteYearReportSelfUseDisposal) {
        return this.updateById(wasteYearReportSelfUseDisposal);
    }

    /**
     * 删除危险废物_产生年报_03自行利用处置情况信息
     *
     * @param id 危险废物_产生年报_03自行利用处置情况ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteYearReportSelfUseDisposal(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_产生年报_03自行利用处置情况
     *
     * @param id 危险废物_产生年报_03自行利用处置情况ID
     * @return 危险废物_产生年报_03自行利用处置情况
     */
    @Override
    public WasteYearReportSelfUseDisposal findWasteYearReportSelfUseDisposalById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_产生年报_03自行利用处置情况列表
     *
     * @param wasteYearReportSelfUseDisposal 危险废物_产生年报_03自行利用处置情况
     * @return 危险废物_产生年报_03自行利用处置情况
     */
    @Override
    public List<WasteYearReportSelfUseDisposal> findWasteYearReportSelfUseDisposalList(WasteYearReportSelfUseDisposal wasteYearReportSelfUseDisposal) {
        LambdaQueryWrapper<WasteYearReportSelfUseDisposal> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_产生年报_03自行利用处置情况
     *
     * @param page                           分页参数
     * @param wasteYearReportSelfUseDisposal 危险废物_产生年报_03自行利用处置情况
     * @return 危险废物_产生年报_03自行利用处置情况
     */
    @Override
    public PageT<WasteYearReportSelfUseDisposal> findWasteYearReportSelfUseDisposalPage(PageT<WasteYearReportSelfUseDisposal> page, WasteYearReportSelfUseDisposal wasteYearReportSelfUseDisposal) {
        LambdaQueryWrapper<WasteYearReportSelfUseDisposal> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
