package com.zsgf.platform.service.impl.businessReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessReport.BusinessMonthReportSecondaryWasteMapper;
import com.zsgf.platform.model.entity.businessReport.BusinessMonthReportSecondaryWaste;
import com.zsgf.platform.service.businessReport.BusinessMonthReportSecondaryWasteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营月报_03次生废物信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessMonthReportSecondaryWasteServiceImpl extends ServiceImpl<BusinessMonthReportSecondaryWasteMapper, BusinessMonthReportSecondaryWaste> implements BusinessMonthReportSecondaryWasteService {


    /**
     * 新增危险废物_经营月报_03次生废物信息
     *
     * @param businessMonthReportSecondaryWaste 危险废物_经营月报_03次生废物信息
     * @return 结果
     */
    @Override
    public boolean saveBusinessMonthReportSecondaryWaste(BusinessMonthReportSecondaryWaste businessMonthReportSecondaryWaste) {
        return this.save(businessMonthReportSecondaryWaste);
    }

    /**
     * 修改危险废物_经营月报_03次生废物信息
     *
     * @param businessMonthReportSecondaryWaste 危险废物_经营月报_03次生废物信息
     * @return 结果
     */
    @Override
    public boolean updateBusinessMonthReportSecondaryWaste(BusinessMonthReportSecondaryWaste businessMonthReportSecondaryWaste) {
        return this.updateById(businessMonthReportSecondaryWaste);
    }

    /**
     * 删除危险废物_经营月报_03次生废物信息信息
     *
     * @param id 危险废物_经营月报_03次生废物信息ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessMonthReportSecondaryWaste(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营月报_03次生废物信息
     *
     * @param id 危险废物_经营月报_03次生废物信息ID
     * @return 危险废物_经营月报_03次生废物信息
     */
    @Override
    public BusinessMonthReportSecondaryWaste findBusinessMonthReportSecondaryWasteById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营月报_03次生废物信息列表
     *
     * @param businessMonthReportSecondaryWaste 危险废物_经营月报_03次生废物信息
     * @return 危险废物_经营月报_03次生废物信息
     */
    @Override
    public List<BusinessMonthReportSecondaryWaste> findBusinessMonthReportSecondaryWasteList(BusinessMonthReportSecondaryWaste businessMonthReportSecondaryWaste) {
        LambdaQueryWrapper<BusinessMonthReportSecondaryWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营月报_03次生废物信息
     *
     * @param page                              分页参数
     * @param businessMonthReportSecondaryWaste 危险废物_经营月报_03次生废物信息
     * @return 危险废物_经营月报_03次生废物信息
     */
    @Override
    public PageT<BusinessMonthReportSecondaryWaste> findBusinessMonthReportSecondaryWastePage(PageT<BusinessMonthReportSecondaryWaste> page, BusinessMonthReportSecondaryWaste businessMonthReportSecondaryWaste) {
        LambdaQueryWrapper<BusinessMonthReportSecondaryWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
