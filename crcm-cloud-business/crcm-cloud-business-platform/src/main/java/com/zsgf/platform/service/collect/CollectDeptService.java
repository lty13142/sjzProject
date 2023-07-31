package com.zsgf.platform.service.collect;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.collect.CollectDept;

import java.util.List;

/**
 * 企业收集部门信息Service接口
 *
 * @author gzl
 * @date 2023-03-23
 */
public interface CollectDeptService extends IService<CollectDept> {

    /**
     * 新增企业收集部门信息
     *
     * @param collectDept 企业收集部门信息
     * @return 结果
     */
    boolean saveCollectDept(CollectDept collectDept);

    /**
     * 修改企业收集部门信息
     *
     * @param collectDept 企业收集部门信息
     * @return 结果
     */
    boolean updateCollectDept(CollectDept collectDept);

    /**
     * 删除企业收集部门信息信息
     *
     * @param id 企业收集部门信息ID
     * @return 结果
     */
    boolean deleteCollectDept(String id);

    /**
     * 查询企业收集部门信息
     *
     * @param id 企业收集部门信息ID
     * @return 企业收集部门信息
     */
    CollectDept findCollectDeptById(String id);

    /**
     * 查询企业收集部门列表
     *
     * @param collectDept 企业收集部门信息
     * @return 企业收集部门集合
     */
    List<CollectDept> findCollectDeptList(CollectDept collectDept);

    /**
     * 分页查询企业收集部门列表
     *
     * @param page        分页参数
     * @param collectDept 企业收集部门信息
     * @return 企业收集部门集合
     */
    PageT<CollectDept> findCollectDeptPage(PageT<CollectDept> page, CollectDept collectDept);
}
