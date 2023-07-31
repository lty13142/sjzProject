package com.zsgf.platform.service.impl.wasteProduce;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.wasteProduce.WasteProduceOutMapper;
import com.zsgf.platform.model.entity.wasteProduce.WasteProduceOut;
import com.zsgf.platform.service.wasteProduce.WasteProduceOutService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产废出库信息Service业务层处理
 *
 * @author zzyt
 * @date 2023-02-22
 */
@Service
public class WasteProduceOutServiceImpl extends ServiceImpl<WasteProduceOutMapper, WasteProduceOut> implements WasteProduceOutService {


    /**
     * 新增产废出库信息
     *
     * @param wasteProduceOut 产废出库信息
     * @return 结果
     */
    @Override
    public boolean saveWasteProduceOut(WasteProduceOut wasteProduceOut) {
        return this.save(wasteProduceOut);
    }

    /**
     * 修改产废出库信息
     *
     * @param wasteProduceOut 产废出库信息
     * @return 结果
     */
    @Override
    public boolean updateWasteProduceOut(WasteProduceOut wasteProduceOut) {
        return this.updateById(wasteProduceOut);
    }

    /**
     * 删除产废出库信息信息
     *
     * @param id 产废出库信息ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteProduceOut(String id) {
        return this.removeById(id);
    }

    /**
     * 查询产废出库信息
     *
     * @param id 产废出库信息ID
     * @return 产废出库信息
     */
    @Override
    public WasteProduceOut findWasteProduceOutById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询产废出库信息列表
     *
     * @param wasteProduceOut 产废出库信息
     * @return 产废出库信息
     */
    @Override
    public List<WasteProduceOut> findWasteProduceOutList(WasteProduceOut wasteProduceOut) {
        LambdaQueryWrapper<WasteProduceOut> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询产废出库信息
     *
     * @param page            分页参数
     * @param wasteProduceOut 产废出库信息
     * @return 产废出库信息
     */
    @Override
    public PageT<WasteProduceOut> findWasteProduceOutPage(PageT<WasteProduceOut> page, WasteProduceOut wasteProduceOut) {
        LambdaQueryWrapper<WasteProduceOut> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
