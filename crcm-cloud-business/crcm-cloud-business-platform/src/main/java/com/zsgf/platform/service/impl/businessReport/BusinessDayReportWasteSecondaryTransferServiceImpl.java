package com.zsgf.platform.service.impl.businessReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.businessReport.BusinessDayReportWasteSecondaryTransferMapper;
import com.zsgf.platform.model.entity.businessReport.BusinessDayReportWasteSecondaryTransfer;
import com.zsgf.platform.service.businessReport.BusinessDayReportWasteSecondaryTransferService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_经营日报_03接收废物二次转移信息Service业务层处理
 *
 * @author gzl;
 * @date 2023-02-09
 */
@Service
public class BusinessDayReportWasteSecondaryTransferServiceImpl
        extends ServiceImpl<BusinessDayReportWasteSecondaryTransferMapper, BusinessDayReportWasteSecondaryTransfer>
        implements BusinessDayReportWasteSecondaryTransferService {


    /**
     * 新增危险废物_经营日报_03接收废物二次转移信息
     *
     * @param businessDayReportWasteSecondaryTransfer 危险废物_经营日报_03接收废物二次转移信息
     * @return 结果
     */
    @Override
    public boolean saveBusinessDayReportWasteSecondaryTransfer(BusinessDayReportWasteSecondaryTransfer businessDayReportWasteSecondaryTransfer) {
        return this.save(businessDayReportWasteSecondaryTransfer);
    }

    /**
     * 修改危险废物_经营日报_03接收废物二次转移信息
     *
     * @param businessDayReportWasteSecondaryTransfer 危险废物_经营日报_03接收废物二次转移信息
     * @return 结果
     */
    @Override
    public boolean updateBusinessDayReportWasteSecondaryTransfer(BusinessDayReportWasteSecondaryTransfer businessDayReportWasteSecondaryTransfer) {
        return this.updateById(businessDayReportWasteSecondaryTransfer);
    }

    /**
     * 删除危险废物_经营日报_03接收废物二次转移信息信息
     *
     * @param id 危险废物_经营日报_03接收废物二次转移信息ID
     * @return 结果
     */
    @Override
    public boolean deleteBusinessDayReportWasteSecondaryTransfer(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_经营日报_03接收废物二次转移信息
     *
     * @param id 危险废物_经营日报_03接收废物二次转移信息ID
     * @return 危险废物_经营日报_03接收废物二次转移信息
     */
    @Override
    public BusinessDayReportWasteSecondaryTransfer findBusinessDayReportWasteSecondaryTransferById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_经营日报_03接收废物二次转移信息列表
     *
     * @param businessDayReportWasteSecondaryTransfer 危险废物_经营日报_03接收废物二次转移信息
     * @return 危险废物_经营日报_03接收废物二次转移信息
     */
    @Override
    public List<BusinessDayReportWasteSecondaryTransfer> findBusinessDayReportWasteSecondaryTransferList(BusinessDayReportWasteSecondaryTransfer businessDayReportWasteSecondaryTransfer) {
        LambdaQueryWrapper<BusinessDayReportWasteSecondaryTransfer> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_经营日报_03接收废物二次转移信息
     *
     * @param page                                    分页参数
     * @param businessDayReportWasteSecondaryTransfer 危险废物_经营日报_03接收废物二次转移信息
     * @return 危险废物_经营日报_03接收废物二次转移信息
     */
    @Override
    public PageT<BusinessDayReportWasteSecondaryTransfer> findBusinessDayReportWasteSecondaryTransferPage(PageT<BusinessDayReportWasteSecondaryTransfer> page, BusinessDayReportWasteSecondaryTransfer businessDayReportWasteSecondaryTransfer) {
        LambdaQueryWrapper<BusinessDayReportWasteSecondaryTransfer> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
