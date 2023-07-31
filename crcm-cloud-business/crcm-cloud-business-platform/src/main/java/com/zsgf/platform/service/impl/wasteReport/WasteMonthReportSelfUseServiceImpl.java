package com.zsgf.platform.service.impl.wasteReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.wasteReport.WasteMonthReportSelfUseMapper;
import com.zsgf.platform.model.entity.wasteReport.WasteMonthReportSelfUse;
import com.zsgf.platform.service.wasteReport.WasteMonthReportSelfUseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_产生月报_03废物自行利用信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class WasteMonthReportSelfUseServiceImpl extends ServiceImpl<WasteMonthReportSelfUseMapper, WasteMonthReportSelfUse>
        implements WasteMonthReportSelfUseService {


    /**
     * 新增危险废物_产生月报_03废物自行利用信息
     *
     * @param wasteMonthReportSelfUse 危险废物_产生月报_03废物自行利用信息
     * @return 结果
     */
    @Override
    public boolean saveWasteMonthReportSelfUse(WasteMonthReportSelfUse wasteMonthReportSelfUse) {
        return this.save(wasteMonthReportSelfUse);
    }

    /**
     * 修改危险废物_产生月报_03废物自行利用信息
     *
     * @param wasteMonthReportSelfUse 危险废物_产生月报_03废物自行利用信息
     * @return 结果
     */
    @Override
    public boolean updateWasteMonthReportSelfUse(WasteMonthReportSelfUse wasteMonthReportSelfUse) {
        return this.updateById(wasteMonthReportSelfUse);
    }

    /**
     * 删除危险废物_产生月报_03废物自行利用信息信息
     *
     * @param id 危险废物_产生月报_03废物自行利用信息ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteMonthReportSelfUse(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_产生月报_03废物自行利用信息
     *
     * @param id 危险废物_产生月报_03废物自行利用信息ID
     * @return 危险废物_产生月报_03废物自行利用信息
     */
    @Override
    public WasteMonthReportSelfUse findWasteMonthReportSelfUseById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_产生月报_03废物自行利用信息列表
     *
     * @param wasteMonthReportSelfUse 危险废物_产生月报_03废物自行利用信息
     * @return 危险废物_产生月报_03废物自行利用信息
     */
    @Override
    public List<WasteMonthReportSelfUse> findWasteMonthReportSelfUseList(WasteMonthReportSelfUse wasteMonthReportSelfUse) {
        LambdaQueryWrapper<WasteMonthReportSelfUse> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_产生月报_03废物自行利用信息
     *
     * @param page                    分页参数
     * @param wasteMonthReportSelfUse 危险废物_产生月报_03废物自行利用信息
     * @return 危险废物_产生月报_03废物自行利用信息
     */
    @Override
    public PageT<WasteMonthReportSelfUse> findWasteMonthReportSelfUsePage(PageT<WasteMonthReportSelfUse> page, WasteMonthReportSelfUse wasteMonthReportSelfUse) {
        LambdaQueryWrapper<WasteMonthReportSelfUse> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
