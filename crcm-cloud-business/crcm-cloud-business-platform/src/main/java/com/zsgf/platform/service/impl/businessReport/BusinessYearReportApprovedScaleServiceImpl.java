package com.zsgf.platform.service.impl.businessReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessReport.BusinessYearReportApprovedScaleMapper;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportApprovedScale;
import com.zsgf.platform.service.businessReport.BusinessYearReportApprovedScaleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营年报_02许可证核准年经营规模Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessYearReportApprovedScaleServiceImpl extends ServiceImpl<BusinessYearReportApprovedScaleMapper, BusinessYearReportApprovedScale> implements BusinessYearReportApprovedScaleService {


    /**
     * 新增危险废物_经营年报_02许可证核准年经营规模
     *
     * @param businessYearReportApprovedScale 危险废物_经营年报_02许可证核准年经营规模
     * @return 结果
     */
    @Override
    public boolean saveBusinessYearReportApprovedScale(BusinessYearReportApprovedScale businessYearReportApprovedScale) {
        return this.save(businessYearReportApprovedScale);
    }

    /**
     * 修改危险废物_经营年报_02许可证核准年经营规模
     *
     * @param businessYearReportApprovedScale 危险废物_经营年报_02许可证核准年经营规模
     * @return 结果
     */
    @Override
    public boolean updateBusinessYearReportApprovedScale(BusinessYearReportApprovedScale businessYearReportApprovedScale) {
        return this.updateById(businessYearReportApprovedScale);
    }

    /**
     * 删除危险废物_经营年报_02许可证核准年经营规模信息
     *
     * @param id 危险废物_经营年报_02许可证核准年经营规模ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessYearReportApprovedScale(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营年报_02许可证核准年经营规模
     *
     * @param id 危险废物_经营年报_02许可证核准年经营规模ID
     * @return 危险废物_经营年报_02许可证核准年经营规模
     */
    @Override
    public BusinessYearReportApprovedScale findBusinessYearReportApprovedScaleById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营年报_02许可证核准年经营规模列表
     *
     * @param businessYearReportApprovedScale 危险废物_经营年报_02许可证核准年经营规模
     * @return 危险废物_经营年报_02许可证核准年经营规模
     */
    @Override
    public List<BusinessYearReportApprovedScale> findBusinessYearReportApprovedScaleList(BusinessYearReportApprovedScale businessYearReportApprovedScale) {
        LambdaQueryWrapper<BusinessYearReportApprovedScale> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营年报_02许可证核准年经营规模
     *
     * @param page                            分页参数
     * @param businessYearReportApprovedScale 危险废物_经营年报_02许可证核准年经营规模
     * @return 危险废物_经营年报_02许可证核准年经营规模
     */
    @Override
    public PageT<BusinessYearReportApprovedScale> findBusinessYearReportApprovedScalePage(PageT<BusinessYearReportApprovedScale> page, BusinessYearReportApprovedScale businessYearReportApprovedScale) {
        LambdaQueryWrapper<BusinessYearReportApprovedScale> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
