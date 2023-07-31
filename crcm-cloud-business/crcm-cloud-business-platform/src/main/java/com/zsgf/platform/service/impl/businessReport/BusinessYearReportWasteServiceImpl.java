package com.zsgf.platform.service.impl.businessReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessReport.BusinessYearReportWasteMapper;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportWaste;
import com.zsgf.platform.service.businessReport.BusinessYearReportWasteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营年报_03经营废物信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessYearReportWasteServiceImpl extends ServiceImpl<BusinessYearReportWasteMapper, BusinessYearReportWaste>
        implements BusinessYearReportWasteService {


    /**
     * 新增危险废物_经营年报_03经营废物信息
     *
     * @param businessYearReportWaste 危险废物_经营年报_03经营废物信息
     * @return 结果
     */
    @Override
    public boolean saveBusinessYearReportWaste(BusinessYearReportWaste businessYearReportWaste) {
        return this.save(businessYearReportWaste);
    }

    /**
     * 修改危险废物_经营年报_03经营废物信息
     *
     * @param businessYearReportWaste 危险废物_经营年报_03经营废物信息
     * @return 结果
     */
    @Override
    public boolean updateBusinessYearReportWaste(BusinessYearReportWaste businessYearReportWaste) {
        return this.updateById(businessYearReportWaste);
    }

    /**
     * 删除危险废物_经营年报_03经营废物信息信息
     *
     * @param id 危险废物_经营年报_03经营废物信息ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessYearReportWaste(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营年报_03经营废物信息
     *
     * @param id 危险废物_经营年报_03经营废物信息ID
     * @return 危险废物_经营年报_03经营废物信息
     */
    @Override
    public BusinessYearReportWaste findBusinessYearReportWasteById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营年报_03经营废物信息列表
     *
     * @param businessYearReportWaste 危险废物_经营年报_03经营废物信息
     * @return 危险废物_经营年报_03经营废物信息
     */
    @Override
    public List<BusinessYearReportWaste> findBusinessYearReportWasteList(BusinessYearReportWaste businessYearReportWaste) {
        LambdaQueryWrapper<BusinessYearReportWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营年报_03经营废物信息
     *
     * @param page                    分页参数
     * @param businessYearReportWaste 危险废物_经营年报_03经营废物信息
     * @return 危险废物_经营年报_03经营废物信息
     */
    @Override
    public PageT<BusinessYearReportWaste> findBusinessYearReportWastePage(PageT<BusinessYearReportWaste> page, BusinessYearReportWaste businessYearReportWaste) {
        LambdaQueryWrapper<BusinessYearReportWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}