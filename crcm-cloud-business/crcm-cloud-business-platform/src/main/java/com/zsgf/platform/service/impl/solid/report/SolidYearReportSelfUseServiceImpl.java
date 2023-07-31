package com.zsgf.platform.service.impl.solid.report;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.solid.report.SolidYearReportSelfUseMapper;
import com.zsgf.platform.model.entity.solid.report.SolidYearReportSelfUse;
import com.zsgf.platform.service.solid.report.SolidYearReportSelfUseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据共享_一般工业固体废物_产生年报_03自行利用情况Service业务层处理
 *
 * @author gzl
 * @date 2023-03-27
 */
@Service
public class SolidYearReportSelfUseServiceImpl extends ServiceImpl<SolidYearReportSelfUseMapper, SolidYearReportSelfUse>
        implements SolidYearReportSelfUseService {


    /**
     * 新增数据共享_一般工业固体废物_产生年报_03自行利用情况
     *
     * @param solidYearReportSelfUse 数据共享_一般工业固体废物_产生年报_03自行利用情况
     * @return 结果
     */
    @Override
    public boolean saveSolidYearReportSelfUse(SolidYearReportSelfUse solidYearReportSelfUse) {
        return this.save(solidYearReportSelfUse);
    }

    /**
     * 修改数据共享_一般工业固体废物_产生年报_03自行利用情况
     *
     * @param solidYearReportSelfUse 数据共享_一般工业固体废物_产生年报_03自行利用情况
     * @return 结果
     */
    @Override
    public boolean updateSolidYearReportSelfUse(SolidYearReportSelfUse solidYearReportSelfUse) {
        return this.updateById(solidYearReportSelfUse);
    }

    /**
     * 删除数据共享_一般工业固体废物_产生年报_03自行利用情况信息
     *
     * @param id 数据共享_一般工业固体废物_产生年报_03自行利用情况ID
     * @return 结果
     */
    @Override
    public boolean deleteSolidYearReportSelfUse(String id) {
        return this.removeById(id);
    }

    /**
     * 查询数据共享_一般工业固体废物_产生年报_03自行利用情况
     *
     * @param id 数据共享_一般工业固体废物_产生年报_03自行利用情况ID
     * @return 数据共享_一般工业固体废物_产生年报_03自行利用情况
     */
    @Override
    public SolidYearReportSelfUse findSolidYearReportSelfUseById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询数据共享_一般工业固体废物_产生年报_03自行利用情况列表
     *
     * @param solidYearReportSelfUse 数据共享_一般工业固体废物_产生年报_03自行利用情况
     * @return 数据共享_一般工业固体废物_产生年报_03自行利用情况
     */
    @Override
    public List<SolidYearReportSelfUse> findSolidYearReportSelfUseList(SolidYearReportSelfUse solidYearReportSelfUse) {
        LambdaQueryWrapper<SolidYearReportSelfUse> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询数据共享_一般工业固体废物_产生年报_03自行利用情况
     *
     * @param page                   分页参数
     * @param solidYearReportSelfUse 数据共享_一般工业固体废物_产生年报_03自行利用情况
     * @return 数据共享_一般工业固体废物_产生年报_03自行利用情况
     */
    @Override
    public PageT<SolidYearReportSelfUse> findSolidYearReportSelfUsePage(PageT<SolidYearReportSelfUse> page, SolidYearReportSelfUse solidYearReportSelfUse) {
        LambdaQueryWrapper<SolidYearReportSelfUse> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
