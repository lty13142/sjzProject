package com.zsgf.platform.service.wasteProduce;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.wasteProduce.WasteProduceRecord;

import java.util.List;

/**
 * 产废产生台账Service接口
 *
 * @author zzyt
 * @date 2023-02-22
 */
public interface WasteProduceRecordService extends IService<WasteProduceRecord> {

    /**
     * 新增产废产生台账
     *
     * @param wasteProduceRecord 产废产生台账
     * @return 结果
     */
    boolean saveWasteProduceRecord(WasteProduceRecord wasteProduceRecord);

    /**
     * 修改产废产生台账
     *
     * @param wasteProduceRecord 产废产生台账
     * @return 结果
     */
    boolean updateWasteProduceRecord(WasteProduceRecord wasteProduceRecord);

    /**
     * 删除产废产生台账信息
     *
     * @param id 产废产生台账ID
     * @return 结果
     */
    boolean deleteWasteProduceRecord(String id);

    /**
     * 查询产废产生台账
     *
     * @param id 产废产生台账ID
     * @return 产废产生台账
     */
    WasteProduceRecord findWasteProduceRecordById(String id);

    /**
     * 查询产废产生台账列表
     *
     * @param wasteProduceRecord 产废产生台账
     * @return 产废产生台账集合
     */
    List<WasteProduceRecord> findWasteProduceRecordList(WasteProduceRecord wasteProduceRecord);

    /**
     * 分页查询产废产生台账列表
     *
     * @param page               分页参数
     * @param wasteProduceRecord 产废产生台账
     * @return 产废产生台账集合
     */
    PageT<WasteProduceRecord> findWasteProduceRecordPage(PageT<WasteProduceRecord> page, WasteProduceRecord wasteProduceRecord);
}
