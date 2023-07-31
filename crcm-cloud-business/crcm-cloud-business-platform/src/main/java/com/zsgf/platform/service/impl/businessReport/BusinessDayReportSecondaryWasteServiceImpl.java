package com.zsgf.platform.service.impl.businessReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessReport.BusinessDayReportSecondaryWasteMapper;
import com.zsgf.platform.model.entity.businessReport.BusinessDayReportSecondaryWaste;
import com.zsgf.platform.service.businessReport.BusinessDayReportSecondaryWasteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营日报_04次生废物信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class BusinessDayReportSecondaryWasteServiceImpl extends ServiceImpl<BusinessDayReportSecondaryWasteMapper, BusinessDayReportSecondaryWaste>
        implements BusinessDayReportSecondaryWasteService {


    /**
     * 新增危险废物_经营日报_04次生废物信息
     *
     * @param businessDayReportSecondaryWaste 危险废物_经营日报_04次生废物信息
     * @return 结果
     */
    @Override
    public boolean saveBusinessDayReportSecondaryWaste(BusinessDayReportSecondaryWaste businessDayReportSecondaryWaste) {
        return this.save(businessDayReportSecondaryWaste);
    }

    /**
     * 修改危险废物_经营日报_04次生废物信息
     *
     * @param businessDayReportSecondaryWaste 危险废物_经营日报_04次生废物信息
     * @return 结果
     */
    @Override
    public boolean updateBusinessDayReportSecondaryWaste(BusinessDayReportSecondaryWaste businessDayReportSecondaryWaste) {
        return this.updateById(businessDayReportSecondaryWaste);
    }

    /**
     * 删除危险废物_经营日报_04次生废物信息信息
     *
     * @param id 危险废物_经营日报_04次生废物信息ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessDayReportSecondaryWaste(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营日报_04次生废物信息
     *
     * @param id 危险废物_经营日报_04次生废物信息ID
     * @return 危险废物_经营日报_04次生废物信息
     */
    @Override
    public BusinessDayReportSecondaryWaste findBusinessDayReportSecondaryWasteById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营日报_04次生废物信息列表
     *
     * @param businessDayReportSecondaryWaste 危险废物_经营日报_04次生废物信息
     * @return 危险废物_经营日报_04次生废物信息
     */
    @Override
    public List<BusinessDayReportSecondaryWaste> findBusinessDayReportSecondaryWasteList(BusinessDayReportSecondaryWaste businessDayReportSecondaryWaste) {
        LambdaQueryWrapper<BusinessDayReportSecondaryWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营日报_04次生废物信息
     *
     * @param page                            分页参数
     * @param businessDayReportSecondaryWaste 危险废物_经营日报_04次生废物信息
     * @return 危险废物_经营日报_04次生废物信息
     */
    @Override
    public PageT<BusinessDayReportSecondaryWaste> findBusinessDayReportSecondaryWastePage(PageT<BusinessDayReportSecondaryWaste> page, BusinessDayReportSecondaryWaste businessDayReportSecondaryWaste) {
        LambdaQueryWrapper<BusinessDayReportSecondaryWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
