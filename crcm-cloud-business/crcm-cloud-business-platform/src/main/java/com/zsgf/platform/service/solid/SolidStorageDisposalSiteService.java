package com.zsgf.platform.service.solid;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.solid.SolidStorageDisposalSite;

import java.util.List;

/**
 * 数据共享_一般工业固体废物_贮存处置场信息Service接口
 *
 * @author gzl
 * @date 2023-03-27
 */
public interface SolidStorageDisposalSiteService extends IService<SolidStorageDisposalSite> {

    /**
     * 新增数据共享_一般工业固体废物_贮存处置场信息
     *
     * @param solidStorageDisposalSite 数据共享_一般工业固体废物_贮存处置场信息
     * @return 结果
     */
    boolean saveSolidStorageDisposalSite(SolidStorageDisposalSite solidStorageDisposalSite);

    /**
     * 修改数据共享_一般工业固体废物_贮存处置场信息
     *
     * @param solidStorageDisposalSite 数据共享_一般工业固体废物_贮存处置场信息
     * @return 结果
     */
    boolean updateSolidStorageDisposalSite(SolidStorageDisposalSite solidStorageDisposalSite);

    /**
     * 删除数据共享_一般工业固体废物_贮存处置场信息信息
     *
     * @param id 数据共享_一般工业固体废物_贮存处置场信息ID
     * @return 结果
     */
    boolean deleteSolidStorageDisposalSite(String id);

    /**
     * 查询数据共享_一般工业固体废物_贮存处置场信息
     *
     * @param id 数据共享_一般工业固体废物_贮存处置场信息ID
     * @return 数据共享_一般工业固体废物_贮存处置场信息
     */
    SolidStorageDisposalSite findSolidStorageDisposalSiteById(String id);

    /**
     * 查询数据共享_一般工业固体废物_贮存处置场信息列表
     *
     * @param solidStorageDisposalSite 数据共享_一般工业固体废物_贮存处置场信息
     * @return 数据共享_一般工业固体废物_贮存处置场信息集合
     */
    List<SolidStorageDisposalSite> findSolidStorageDisposalSiteList(SolidStorageDisposalSite solidStorageDisposalSite);

    /**
     * 分页查询数据共享_一般工业固体废物_贮存处置场信息列表
     *
     * @param page                     分页参数
     * @param solidStorageDisposalSite 数据共享_一般工业固体废物_贮存处置场信息
     * @return 数据共享_一般工业固体废物_贮存处置场信息集合
     */
    PageT<SolidStorageDisposalSite> findSolidStorageDisposalSitePage(PageT<SolidStorageDisposalSite> page, SolidStorageDisposalSite solidStorageDisposalSite);
}
