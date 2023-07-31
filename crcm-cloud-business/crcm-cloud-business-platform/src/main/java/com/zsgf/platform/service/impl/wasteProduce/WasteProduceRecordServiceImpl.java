package com.zsgf.platform.service.impl.wasteProduce;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.wasteProduce.WasteProduceRecordMapper;
import com.zsgf.platform.model.entity.wasteProduce.WasteProduceRecord;
import com.zsgf.platform.service.wasteProduce.WasteProduceRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产废产生台账Service业务层处理
 *
 * @author zzyt
 * @date 2023-02-22
 */
@Service
public class WasteProduceRecordServiceImpl extends ServiceImpl<WasteProduceRecordMapper, WasteProduceRecord> implements WasteProduceRecordService {


    /**
     * 新增产废产生台账
     *
     * @param wasteProduceRecord 产废产生台账
     * @return 结果
     */
    @Override
    public boolean saveWasteProduceRecord(WasteProduceRecord wasteProduceRecord) {
        return this.save(wasteProduceRecord);
    }

    /**
     * 修改产废产生台账
     *
     * @param wasteProduceRecord 产废产生台账
     * @return 结果
     */
    @Override
    public boolean updateWasteProduceRecord(WasteProduceRecord wasteProduceRecord) {
        return this.updateById(wasteProduceRecord);
    }

    /**
     * 删除产废产生台账信息
     *
     * @param id 产废产生台账ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteProduceRecord(String id) {
        return this.removeById(id);
    }

    /**
     * 查询产废产生台账
     *
     * @param id 产废产生台账ID
     * @return 产废产生台账
     */
    @Override
    public WasteProduceRecord findWasteProduceRecordById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询产废产生台账列表
     *
     * @param wasteProduceRecord 产废产生台账
     * @return 产废产生台账
     */
    @Override
    public List<WasteProduceRecord> findWasteProduceRecordList(WasteProduceRecord wasteProduceRecord) {
        LambdaQueryWrapper<WasteProduceRecord> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询产废产生台账
     *
     * @param page               分页参数
     * @param wasteProduceRecord 产废产生台账
     * @return 产废产生台账
     */
    @Override
    public PageT<WasteProduceRecord> findWasteProduceRecordPage(PageT<WasteProduceRecord> page, WasteProduceRecord wasteProduceRecord) {
        LambdaQueryWrapper<WasteProduceRecord> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
