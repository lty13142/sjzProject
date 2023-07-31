package com.zsgf.platform.service.impl.businessReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessReport.BusinessDayReportWasteMapper;
import com.zsgf.platform.model.entity.businessReport.BusinessDayReportWaste;
import com.zsgf.platform.service.businessReport.BusinessDayReportWasteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营日报_02接收废物信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessDayReportWasteServiceImpl extends ServiceImpl<BusinessDayReportWasteMapper, BusinessDayReportWaste> implements BusinessDayReportWasteService {


    /**
     * 新增危险废物_经营日报_02接收废物信息
     *
     * @param businessDayReportWaste 危险废物_经营日报_02接收废物信息
     * @return 结果
     */
    @Override
    public boolean saveBusinessDayReportWaste(BusinessDayReportWaste businessDayReportWaste) {
        return this.save(businessDayReportWaste);
    }

    /**
     * 修改危险废物_经营日报_02接收废物信息
     *
     * @param businessDayReportWaste 危险废物_经营日报_02接收废物信息
     * @return 结果
     */
    @Override
    public boolean updateBusinessDayReportWaste(BusinessDayReportWaste businessDayReportWaste) {
        return this.updateById(businessDayReportWaste);
    }

    /**
     * 删除危险废物_经营日报_02接收废物信息信息
     *
     * @param id 危险废物_经营日报_02接收废物信息ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessDayReportWaste(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营日报_02接收废物信息
     *
     * @param id 危险废物_经营日报_02接收废物信息ID
     * @return 危险废物_经营日报_02接收废物信息
     */
    @Override
    public BusinessDayReportWaste findBusinessDayReportWasteById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营日报_02接收废物信息列表
     *
     * @param businessDayReportWaste 危险废物_经营日报_02接收废物信息
     * @return 危险废物_经营日报_02接收废物信息
     */
    @Override
    public List<BusinessDayReportWaste> findBusinessDayReportWasteList(BusinessDayReportWaste businessDayReportWaste) {
        LambdaQueryWrapper<BusinessDayReportWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营日报_02接收废物信息
     *
     * @param page                   分页参数
     * @param businessDayReportWaste 危险废物_经营日报_02接收废物信息
     * @return 危险废物_经营日报_02接收废物信息
     */
    @Override
    public PageT<BusinessDayReportWaste> findBusinessDayReportWastePage(PageT<BusinessDayReportWaste> page, BusinessDayReportWaste businessDayReportWaste) {
        LambdaQueryWrapper<BusinessDayReportWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
