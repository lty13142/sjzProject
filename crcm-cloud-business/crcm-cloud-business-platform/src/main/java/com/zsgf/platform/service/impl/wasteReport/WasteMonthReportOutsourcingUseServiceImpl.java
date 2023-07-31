package com.zsgf.platform.service.impl.wasteReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.wasteReport.WasteMonthReportOutsourcingUseMapper;
import com.zsgf.platform.model.entity.wasteReport.WasteMonthReportOutsourcingUse;
import com.zsgf.platform.service.wasteReport.WasteMonthReportOutsourcingUseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_产生月报_04废物委外利用信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class WasteMonthReportOutsourcingUseServiceImpl extends ServiceImpl<WasteMonthReportOutsourcingUseMapper, WasteMonthReportOutsourcingUse>
        implements WasteMonthReportOutsourcingUseService {


    /**
     * 新增危险废物_产生月报_04废物委外利用信息
     *
     * @param wasteMonthReportOutsourcingUse 危险废物_产生月报_04废物委外利用信息
     * @return 结果
     */
    @Override
    public boolean saveWasteMonthReportOutsourcingUse(WasteMonthReportOutsourcingUse wasteMonthReportOutsourcingUse) {
        return this.save(wasteMonthReportOutsourcingUse);
    }

    /**
     * 修改危险废物_产生月报_04废物委外利用信息
     *
     * @param wasteMonthReportOutsourcingUse 危险废物_产生月报_04废物委外利用信息
     * @return 结果
     */
    @Override
    public boolean updateWasteMonthReportOutsourcingUse(WasteMonthReportOutsourcingUse wasteMonthReportOutsourcingUse) {
        return this.updateById(wasteMonthReportOutsourcingUse);
    }

    /**
     * 删除危险废物_产生月报_04废物委外利用信息信息
     *
     * @param id 危险废物_产生月报_04废物委外利用信息ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteMonthReportOutsourcingUse(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_产生月报_04废物委外利用信息
     *
     * @param id 危险废物_产生月报_04废物委外利用信息ID
     * @return 危险废物_产生月报_04废物委外利用信息
     */
    @Override
    public WasteMonthReportOutsourcingUse findWasteMonthReportOutsourcingUseById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_产生月报_04废物委外利用信息列表
     *
     * @param wasteMonthReportOutsourcingUse 危险废物_产生月报_04废物委外利用信息
     * @return 危险废物_产生月报_04废物委外利用信息
     */
    @Override
    public List<WasteMonthReportOutsourcingUse> findWasteMonthReportOutsourcingUseList(WasteMonthReportOutsourcingUse wasteMonthReportOutsourcingUse) {
        LambdaQueryWrapper<WasteMonthReportOutsourcingUse> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_产生月报_04废物委外利用信息
     *
     * @param page                           分页参数
     * @param wasteMonthReportOutsourcingUse 危险废物_产生月报_04废物委外利用信息
     * @return 危险废物_产生月报_04废物委外利用信息
     */
    @Override
    public PageT<WasteMonthReportOutsourcingUse> findWasteMonthReportOutsourcingUsePage(PageT<WasteMonthReportOutsourcingUse> page, WasteMonthReportOutsourcingUse wasteMonthReportOutsourcingUse) {
        LambdaQueryWrapper<WasteMonthReportOutsourcingUse> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
