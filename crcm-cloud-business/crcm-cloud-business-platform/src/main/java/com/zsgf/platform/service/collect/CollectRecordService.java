package com.zsgf.platform.service.collect;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.collect.CollectRecord;

import java.util.List;

/**
 * 企业收集台账Service接口
 *
 * @author gzl
 * @date 2023-03-23
 */
public interface CollectRecordService extends IService<CollectRecord> {

    /**
     * 新增企业收集台账
     *
     * @param collectRecord 企业收集台账
     * @return 结果
     */
    boolean saveCollectRecord(CollectRecord collectRecord);

    /**
     * 修改企业收集台账
     *
     * @param collectRecord 企业收集台账
     * @return 结果
     */
    boolean updateCollectRecord(CollectRecord collectRecord);

    /**
     * 删除企业收集台账信息
     *
     * @param id 企业收集台账ID
     * @return 结果
     */
    boolean deleteCollectRecord(String id);

    /**
     * 查询企业收集台账
     *
     * @param id 企业收集台账ID
     * @return 企业收集台账
     */
    CollectRecord findCollectRecordById(String id);

    /**
     * 查询企业收集台账列表
     *
     * @param collectRecord 企业收集台账
     * @return 企业收集台账集合
     */
    List<CollectRecord> findCollectRecordList(CollectRecord collectRecord);

    /**
     * 分页查询企业收集台账列表
     *
     * @param page          分页参数
     * @param collectRecord 企业收集台账
     * @return 企业收集台账集合
     */
    PageT<CollectRecord> findCollectRecordPage(PageT<CollectRecord> page, CollectRecord collectRecord);
}
