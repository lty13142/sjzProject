package com.zsgf.platform.service.impl.lab;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.lab.WasteLabInventoryMapper;
import com.zsgf.platform.model.entity.lab.WasteLabInventory;
import com.zsgf.platform.service.lab.WasteLabInventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实验室台账库存Service业务层处理
 *
 * @author gzl
 * @date 2023-03-23
 */
@Service
public class WasteLabInventoryServiceImpl extends ServiceImpl<WasteLabInventoryMapper, WasteLabInventory> implements WasteLabInventoryService {


    /**
     * 新增实验室台账库存
     *
     * @param wasteLabInventory 实验室台账库存
     * @return 结果
     */
    @Override
    public boolean saveWasteLabInventory(WasteLabInventory wasteLabInventory) {
        return this.save(wasteLabInventory);
    }

    /**
     * 修改实验室台账库存
     *
     * @param wasteLabInventory 实验室台账库存
     * @return 结果
     */
    @Override
    public boolean updateWasteLabInventory(WasteLabInventory wasteLabInventory) {
        return this.updateById(wasteLabInventory);
    }

    /**
     * 删除实验室台账库存信息
     *
     * @param id 实验室台账库存ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteLabInventory(String id) {
        return this.removeById(id);
    }

    /**
     * 查询实验室台账库存
     *
     * @param id 实验室台账库存ID
     * @return 实验室台账库存
     */
    @Override
    public WasteLabInventory findWasteLabInventoryById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询实验室台账库存列表
     *
     * @param wasteLabInventory 实验室台账库存
     * @return 实验室台账库存
     */
    @Override
    public List<WasteLabInventory> findWasteLabInventoryList(WasteLabInventory wasteLabInventory) {
        LambdaQueryWrapper<WasteLabInventory> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询实验室台账库存
     *
     * @param page              分页参数
     * @param wasteLabInventory 实验室台账库存
     * @return 实验室台账库存
     */
    @Override
    public PageT<WasteLabInventory> findWasteLabInventoryPage(PageT<WasteLabInventory> page, WasteLabInventory wasteLabInventory) {
        LambdaQueryWrapper<WasteLabInventory> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
