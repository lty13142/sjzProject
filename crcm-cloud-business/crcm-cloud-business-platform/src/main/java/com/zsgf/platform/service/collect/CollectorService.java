package com.zsgf.platform.service.collect;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.collect.Collector;

import java.util.List;

/**
 * 企业收集人员信息Service接口
 *
 * @author gzl
 * @date 2023-03-23
 */
public interface CollectorService extends IService<Collector> {

    /**
     * 新增企业收集人员信息
     *
     * @param collector 企业收集人员信息
     * @return 结果
     */
    boolean saveCollector(Collector collector);

    /**
     * 修改企业收集人员信息
     *
     * @param collector 企业收集人员信息
     * @return 结果
     */
    boolean updateCollector(Collector collector);

    /**
     * 删除企业收集人员信息信息
     *
     * @param id 企业收集人员信息ID
     * @return 结果
     */
    boolean deleteCollector(String id);

    /**
     * 查询企业收集人员信息
     *
     * @param id 企业收集人员信息ID
     * @return 企业收集人员信息
     */
    Collector findCollectorById(String id);

    /**
     * 查询企业收集人员信息列表
     *
     * @param collector 企业收集人员信息
     * @return 企业收集人员信息集合
     */
    List<Collector> findCollectorList(Collector collector);

    /**
     * 分页查询企业收集人员信息列表
     *
     * @param page      分页参数
     * @param collector 企业收集人员信息
     * @return 企业收集人员信息集合
     */
    PageT<Collector> findCollectorPage(PageT<Collector> page, Collector collector);
}
