package com.zsgf.platform.service.impl.businessReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessReport.BusinessMonthReportWasteMapper;
import com.zsgf.platform.model.entity.businessReport.BusinessMonthReportWaste;
import com.zsgf.platform.service.businessReport.BusinessMonthReportWasteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营月报_02经营废物信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessMonthReportWasteServiceImpl extends ServiceImpl<BusinessMonthReportWasteMapper, BusinessMonthReportWaste>
        implements BusinessMonthReportWasteService {


    /**
     * 新增危险废物_经营月报_02经营废物信息
     *
     * @param businessMonthReportWaste 危险废物_经营月报_02经营废物信息
     * @return 结果
     */
    @Override
    public boolean saveBusinessMonthReportWaste(BusinessMonthReportWaste businessMonthReportWaste) {
        return this.save(businessMonthReportWaste);
    }

    /**
     * 修改危险废物_经营月报_02经营废物信息
     *
     * @param businessMonthReportWaste 危险废物_经营月报_02经营废物信息
     * @return 结果
     */
    @Override
    public boolean updateBusinessMonthReportWaste(BusinessMonthReportWaste businessMonthReportWaste) {
        return this.updateById(businessMonthReportWaste);
    }

    /**
     * 删除危险废物_经营月报_02经营废物信息信息
     *
     * @param id 危险废物_经营月报_02经营废物信息ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessMonthReportWaste(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营月报_02经营废物信息
     *
     * @param id 危险废物_经营月报_02经营废物信息ID
     * @return 危险废物_经营月报_02经营废物信息
     */
    @Override
    public BusinessMonthReportWaste findBusinessMonthReportWasteById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营月报_02经营废物信息列表
     *
     * @param businessMonthReportWaste 危险废物_经营月报_02经营废物信息
     * @return 危险废物_经营月报_02经营废物信息
     */
    @Override
    public List<BusinessMonthReportWaste> findBusinessMonthReportWasteList(BusinessMonthReportWaste businessMonthReportWaste) {
        LambdaQueryWrapper<BusinessMonthReportWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营月报_02经营废物信息
     *
     * @param page                     分页参数
     * @param businessMonthReportWaste 危险废物_经营月报_02经营废物信息
     * @return 危险废物_经营月报_02经营废物信息
     */
    @Override
    public PageT<BusinessMonthReportWaste> findBusinessMonthReportWastePage(PageT<BusinessMonthReportWaste> page, BusinessMonthReportWaste businessMonthReportWaste) {
        LambdaQueryWrapper<BusinessMonthReportWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
