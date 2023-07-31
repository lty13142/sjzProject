package com.zsgf.platform.service.impl.wasteReport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.wasteReport.WasteMonthReportWasteMapper;
import com.zsgf.platform.model.entity.wasteReport.WasteMonthReportWaste;
import com.zsgf.platform.service.wasteReport.WasteMonthReportWasteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_产生月报_02产废信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class WasteMonthReportWasteServiceImpl extends ServiceImpl<WasteMonthReportWasteMapper, WasteMonthReportWaste>
        implements WasteMonthReportWasteService {


    /**
     * 新增危险废物_产生月报_02产废信息
     *
     * @param wasteMonthReportWaste 危险废物_产生月报_02产废信息
     * @return 结果
     */
    @Override
    public boolean saveWasteMonthReportWaste(WasteMonthReportWaste wasteMonthReportWaste) {
        return this.save(wasteMonthReportWaste);
    }

    /**
     * 修改危险废物_产生月报_02产废信息
     *
     * @param wasteMonthReportWaste 危险废物_产生月报_02产废信息
     * @return 结果
     */
    @Override
    public boolean updateWasteMonthReportWaste(WasteMonthReportWaste wasteMonthReportWaste) {
        return this.updateById(wasteMonthReportWaste);
    }

    /**
     * 删除危险废物_产生月报_02产废信息信息
     *
     * @param id 危险废物_产生月报_02产废信息ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteMonthReportWaste(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_产生月报_02产废信息
     *
     * @param id 危险废物_产生月报_02产废信息ID
     * @return 危险废物_产生月报_02产废信息
     */
    @Override
    public WasteMonthReportWaste findWasteMonthReportWasteById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_产生月报_02产废信息列表
     *
     * @param wasteMonthReportWaste 危险废物_产生月报_02产废信息
     * @return 危险废物_产生月报_02产废信息
     */
    @Override
    public List<WasteMonthReportWaste> findWasteMonthReportWasteList(WasteMonthReportWaste wasteMonthReportWaste) {
        LambdaQueryWrapper<WasteMonthReportWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_产生月报_02产废信息
     *
     * @param page                  分页参数
     * @param wasteMonthReportWaste 危险废物_产生月报_02产废信息
     * @return 危险废物_产生月报_02产废信息
     */
    @Override
    public PageT<WasteMonthReportWaste> findWasteMonthReportWastePage(PageT<WasteMonthReportWaste> page, WasteMonthReportWaste wasteMonthReportWaste) {
        LambdaQueryWrapper<WasteMonthReportWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
