package com.zsgf.platform.service.impl.wasteReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.wasteReport.WasteYearReportOutsourcingUseMapper;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReportOutsourcingUse;
import com.zsgf.platform.service.wasteReport.WasteYearReportOutsourcingUseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_产生年报_04委外利用情况Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class WasteYearReportOutsourcingUseServiceImpl extends ServiceImpl<WasteYearReportOutsourcingUseMapper, WasteYearReportOutsourcingUse>
        implements WasteYearReportOutsourcingUseService {


    /**
     * 新增危险废物_产生年报_04委外利用情况
     *
     * @param wasteYearReportOutsourcingUse 危险废物_产生年报_04委外利用情况
     * @return 结果
     */
    @Override
    public boolean saveWasteYearReportOutsourcingUse(WasteYearReportOutsourcingUse wasteYearReportOutsourcingUse) {
        return this.save(wasteYearReportOutsourcingUse);
    }

    /**
     * 修改危险废物_产生年报_04委外利用情况
     *
     * @param wasteYearReportOutsourcingUse 危险废物_产生年报_04委外利用情况
     * @return 结果
     */
    @Override
    public boolean updateWasteYearReportOutsourcingUse(WasteYearReportOutsourcingUse wasteYearReportOutsourcingUse) {
        return this.updateById(wasteYearReportOutsourcingUse);
    }

    /**
     * 删除危险废物_产生年报_04委外利用情况信息
     *
     * @param id 危险废物_产生年报_04委外利用情况ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteYearReportOutsourcingUse(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_产生年报_04委外利用情况
     *
     * @param id 危险废物_产生年报_04委外利用情况ID
     * @return 危险废物_产生年报_04委外利用情况
     */
    @Override
    public WasteYearReportOutsourcingUse findWasteYearReportOutsourcingUseById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_产生年报_04委外利用情况列表
     *
     * @param wasteYearReportOutsourcingUse 危险废物_产生年报_04委外利用情况
     * @return 危险废物_产生年报_04委外利用情况
     */
    @Override
    public List<WasteYearReportOutsourcingUse> findWasteYearReportOutsourcingUseList(WasteYearReportOutsourcingUse wasteYearReportOutsourcingUse) {
        LambdaQueryWrapper<WasteYearReportOutsourcingUse> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_产生年报_04委外利用情况
     *
     * @param page                          分页参数
     * @param wasteYearReportOutsourcingUse 危险废物_产生年报_04委外利用情况
     * @return 危险废物_产生年报_04委外利用情况
     */
    @Override
    public PageT<WasteYearReportOutsourcingUse> findWasteYearReportOutsourcingUsePage(PageT<WasteYearReportOutsourcingUse> page, WasteYearReportOutsourcingUse wasteYearReportOutsourcingUse) {
        LambdaQueryWrapper<WasteYearReportOutsourcingUse> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
