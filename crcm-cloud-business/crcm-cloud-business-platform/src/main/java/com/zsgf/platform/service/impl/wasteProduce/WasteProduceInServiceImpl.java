package com.zsgf.platform.service.impl.wasteProduce;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.wasteProduce.WasteProduceInMapper;
import com.zsgf.platform.model.entity.wasteProduce.WasteProduceIn;
import com.zsgf.platform.service.wasteProduce.WasteProduceInService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产废入库信息Service业务层处理
 *
 * @author zzyt
 * @date 2023-02-22
 */
@Service
public class WasteProduceInServiceImpl extends ServiceImpl<WasteProduceInMapper, WasteProduceIn> implements WasteProduceInService {


    /**
     * 新增产废入库信息
     *
     * @param wasteProduceIn 产废入库信息
     * @return 结果
     */
    @Override
    public boolean saveWasteProduceIn(WasteProduceIn wasteProduceIn) {
        return this.save(wasteProduceIn);
    }

    /**
     * 修改产废入库信息
     *
     * @param wasteProduceIn 产废入库信息
     * @return 结果
     */
    @Override
    public boolean updateWasteProduceIn(WasteProduceIn wasteProduceIn) {
        return this.updateById(wasteProduceIn);
    }

    /**
     * 删除产废入库信息信息
     *
     * @param id 产废入库信息ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteProduceIn(String id) {
        return this.removeById(id);
    }

    /**
     * 查询产废入库信息
     *
     * @param id 产废入库信息ID
     * @return 产废入库信息
     */
    @Override
    public WasteProduceIn findWasteProduceInById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询产废入库信息列表
     *
     * @param wasteProduceIn 产废入库信息
     * @return 产废入库信息
     */
    @Override
    public List<WasteProduceIn> findWasteProduceInList(WasteProduceIn wasteProduceIn) {
        LambdaQueryWrapper<WasteProduceIn> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询产废入库信息
     *
     * @param page           分页参数
     * @param wasteProduceIn 产废入库信息
     * @return 产废入库信息
     */
    @Override
    public PageT<WasteProduceIn> findWasteProduceInPage(PageT<WasteProduceIn> page, WasteProduceIn wasteProduceIn) {
        LambdaQueryWrapper<WasteProduceIn> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
