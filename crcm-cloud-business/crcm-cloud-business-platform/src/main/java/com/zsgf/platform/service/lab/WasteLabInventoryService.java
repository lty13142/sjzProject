package com.zsgf.platform.service.lab;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.lab.WasteLabInventory;

import java.util.List;

/**
 * 实验室台账库存Service接口
 *
 * @author gzl
 * @date 2023-03-23
 */
public interface WasteLabInventoryService extends IService<WasteLabInventory> {

    /**
     * 新增实验室台账库存
     *
     * @param wasteLabInventory 实验室台账库存
     * @return 结果
     */
    boolean saveWasteLabInventory(WasteLabInventory wasteLabInventory);

    /**
     * 修改实验室台账库存
     *
     * @param wasteLabInventory 实验室台账库存
     * @return 结果
     */
    boolean updateWasteLabInventory(WasteLabInventory wasteLabInventory);

    /**
     * 删除实验室台账库存信息
     *
     * @param id 实验室台账库存ID
     * @return 结果
     */
    boolean deleteWasteLabInventory(String id);

    /**
     * 查询实验室台账库存
     *
     * @param id 实验室台账库存ID
     * @return 实验室台账库存
     */
    WasteLabInventory findWasteLabInventoryById(String id);

    /**
     * 查询实验室台账库存列表
     *
     * @param wasteLabInventory 实验室台账库存
     * @return 实验室台账库存集合
     */
    List<WasteLabInventory> findWasteLabInventoryList(WasteLabInventory wasteLabInventory);

    /**
     * 分页查询实验室台账库存列表
     *
     * @param page              分页参数
     * @param wasteLabInventory 实验室台账库存
     * @return 实验室台账库存集合
     */
    PageT<WasteLabInventory> findWasteLabInventoryPage(PageT<WasteLabInventory> page, WasteLabInventory wasteLabInventory);
}
