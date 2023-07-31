package com.zsgf.platform.service.impl.solid;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.solid.SolidStorageDisposalSiteMapper;
import com.zsgf.platform.model.entity.solid.SolidStorageDisposalSite;
import com.zsgf.platform.service.solid.SolidStorageDisposalSiteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据共享_一般工业固体废物_贮存处置场信息Service业务层处理
 *
 * @author gzl
 * @date 2023-03-27
 */
@Service
public class SolidStorageDisposalSiteServiceImpl extends ServiceImpl<SolidStorageDisposalSiteMapper, SolidStorageDisposalSite> implements SolidStorageDisposalSiteService {


    /**
     * 新增数据共享_一般工业固体废物_贮存处置场信息
     *
     * @param solidStorageDisposalSite 数据共享_一般工业固体废物_贮存处置场信息
     * @return 结果
     */
    @Override
    public boolean saveSolidStorageDisposalSite(SolidStorageDisposalSite solidStorageDisposalSite) {
        return this.save(solidStorageDisposalSite);
    }

    /**
     * 修改数据共享_一般工业固体废物_贮存处置场信息
     *
     * @param solidStorageDisposalSite 数据共享_一般工业固体废物_贮存处置场信息
     * @return 结果
     */
    @Override
    public boolean updateSolidStorageDisposalSite(SolidStorageDisposalSite solidStorageDisposalSite) {
        return this.updateById(solidStorageDisposalSite);
    }

    /**
     * 删除数据共享_一般工业固体废物_贮存处置场信息信息
     *
     * @param id 数据共享_一般工业固体废物_贮存处置场信息ID
     * @return 结果
     */
    @Override
    public boolean deleteSolidStorageDisposalSite(String id) {
        return this.removeById(id);
    }

    /**
     * 查询数据共享_一般工业固体废物_贮存处置场信息
     *
     * @param id 数据共享_一般工业固体废物_贮存处置场信息ID
     * @return 数据共享_一般工业固体废物_贮存处置场信息
     */
    @Override
    public SolidStorageDisposalSite findSolidStorageDisposalSiteById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询数据共享_一般工业固体废物_贮存处置场信息列表
     *
     * @param solidStorageDisposalSite 数据共享_一般工业固体废物_贮存处置场信息
     * @return 数据共享_一般工业固体废物_贮存处置场信息
     */
    @Override
    public List<SolidStorageDisposalSite> findSolidStorageDisposalSiteList(SolidStorageDisposalSite solidStorageDisposalSite) {
        LambdaQueryWrapper<SolidStorageDisposalSite> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询数据共享_一般工业固体废物_贮存处置场信息
     *
     * @param page                     分页参数
     * @param solidStorageDisposalSite 数据共享_一般工业固体废物_贮存处置场信息
     * @return 数据共享_一般工业固体废物_贮存处置场信息
     */
    @Override
    public PageT<SolidStorageDisposalSite> findSolidStorageDisposalSitePage(PageT<SolidStorageDisposalSite> page, SolidStorageDisposalSite solidStorageDisposalSite) {
        LambdaQueryWrapper<SolidStorageDisposalSite> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
