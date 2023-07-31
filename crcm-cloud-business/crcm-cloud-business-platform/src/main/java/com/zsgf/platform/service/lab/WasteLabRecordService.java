package com.zsgf.platform.service.lab;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.lab.WasteLabRecord;

import java.util.List;

/**
 * 实验室台账Service接口
 *
 * @author gzl
 * @date 2023-03-23
 */
public interface WasteLabRecordService extends IService<WasteLabRecord> {

    /**
     * 新增实验室台账
     *
     * @param wasteLabRecord 实验室台账
     * @return 结果
     */
    boolean saveWasteLabRecord(WasteLabRecord wasteLabRecord);

    /**
     * 修改实验室台账
     *
     * @param wasteLabRecord 实验室台账
     * @return 结果
     */
    boolean updateWasteLabRecord(WasteLabRecord wasteLabRecord);

    /**
     * 删除实验室台账信息
     *
     * @param id 实验室台账ID
     * @return 结果
     */
    boolean deleteWasteLabRecord(String id);

    /**
     * 查询实验室台账
     *
     * @param id 实验室台账ID
     * @return 实验室台账
     */
    WasteLabRecord findWasteLabRecordById(String id);

    /**
     * 查询实验室台账列表
     *
     * @param wasteLabRecord 实验室台账
     * @return 实验室台账集合
     */
    List<WasteLabRecord> findWasteLabRecordList(WasteLabRecord wasteLabRecord);

    /**
     * 分页查询实验室台账列表
     *
     * @param page           分页参数
     * @param wasteLabRecord 实验室台账
     * @return 实验室台账集合
     */
    PageT<WasteLabRecord> findWasteLabRecordPage(PageT<WasteLabRecord> page, WasteLabRecord wasteLabRecord);
}
