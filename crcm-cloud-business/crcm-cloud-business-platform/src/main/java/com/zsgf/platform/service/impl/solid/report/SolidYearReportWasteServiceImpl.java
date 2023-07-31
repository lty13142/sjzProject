package com.zsgf.platform.service.impl.solid.report;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.solid.report.SolidYearReportWasteMapper;
import com.zsgf.platform.model.entity.solid.report.SolidYearReportWaste;
import com.zsgf.platform.service.solid.report.SolidYearReportWasteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据共享_一般工业固体废物_产生年报_02产废信息Service业务层处理
 *
 * @author gzl
 * @date 2023-03-27
 */
@Service
public class SolidYearReportWasteServiceImpl extends ServiceImpl<SolidYearReportWasteMapper, SolidYearReportWaste> implements SolidYearReportWasteService {


    /**
     * 新增数据共享_一般工业固体废物_产生年报_02产废信息
     *
     * @param solidYearReportWaste 数据共享_一般工业固体废物_产生年报_02产废信息
     * @return 结果
     */
    @Override
    public boolean saveSolidYearReportWaste(SolidYearReportWaste solidYearReportWaste) {
        return this.save(solidYearReportWaste);
    }

    /**
     * 修改数据共享_一般工业固体废物_产生年报_02产废信息
     *
     * @param solidYearReportWaste 数据共享_一般工业固体废物_产生年报_02产废信息
     * @return 结果
     */
    @Override
    public boolean updateSolidYearReportWaste(SolidYearReportWaste solidYearReportWaste) {
        return this.updateById(solidYearReportWaste);
    }

    /**
     * 删除数据共享_一般工业固体废物_产生年报_02产废信息信息
     *
     * @param id 数据共享_一般工业固体废物_产生年报_02产废信息ID
     * @return 结果
     */
    @Override
    public boolean deleteSolidYearReportWaste(String id) {
        return this.removeById(id);
    }

    /**
     * 查询数据共享_一般工业固体废物_产生年报_02产废信息
     *
     * @param id 数据共享_一般工业固体废物_产生年报_02产废信息ID
     * @return 数据共享_一般工业固体废物_产生年报_02产废信息
     */
    @Override
    public SolidYearReportWaste findSolidYearReportWasteById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询数据共享_一般工业固体废物_产生年报_02产废信息列表
     *
     * @param solidYearReportWaste 数据共享_一般工业固体废物_产生年报_02产废信息
     * @return 数据共享_一般工业固体废物_产生年报_02产废信息
     */
    @Override
    public List<SolidYearReportWaste> findSolidYearReportWasteList(SolidYearReportWaste solidYearReportWaste) {
        LambdaQueryWrapper<SolidYearReportWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询数据共享_一般工业固体废物_产生年报_02产废信息
     *
     * @param page                 分页参数
     * @param solidYearReportWaste 数据共享_一般工业固体废物_产生年报_02产废信息
     * @return 数据共享_一般工业固体废物_产生年报_02产废信息
     */
    @Override
    public PageT<SolidYearReportWaste> findSolidYearReportWastePage(PageT<SolidYearReportWaste> page, SolidYearReportWaste solidYearReportWaste) {
        LambdaQueryWrapper<SolidYearReportWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
