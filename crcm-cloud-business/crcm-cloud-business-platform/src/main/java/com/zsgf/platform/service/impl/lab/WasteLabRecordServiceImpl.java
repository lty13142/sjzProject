package com.zsgf.platform.service.impl.lab;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.lab.WasteLabRecordMapper;
import com.zsgf.platform.model.entity.lab.WasteLabRecord;
import com.zsgf.platform.service.lab.WasteLabRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实验室台账Service业务层处理
 *
 * @author gzl
 * @date 2023-03-23
 */
@Service
public class WasteLabRecordServiceImpl extends ServiceImpl<WasteLabRecordMapper, WasteLabRecord> implements WasteLabRecordService {


    /**
     * 新增实验室台账
     *
     * @param wasteLabRecord 实验室台账
     * @return 结果
     */
    @Override
    public boolean saveWasteLabRecord(WasteLabRecord wasteLabRecord) {
        return this.save(wasteLabRecord);
    }

    /**
     * 修改实验室台账
     *
     * @param wasteLabRecord 实验室台账
     * @return 结果
     */
    @Override
    public boolean updateWasteLabRecord(WasteLabRecord wasteLabRecord) {
        return this.updateById(wasteLabRecord);
    }

    /**
     * 删除实验室台账信息
     *
     * @param id 实验室台账ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteLabRecord(String id) {
        return this.removeById(id);
    }

    /**
     * 查询实验室台账
     *
     * @param id 实验室台账ID
     * @return 实验室台账
     */
    @Override
    public WasteLabRecord findWasteLabRecordById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询实验室台账列表
     *
     * @param wasteLabRecord 实验室台账
     * @return 实验室台账
     */
    @Override
    public List<WasteLabRecord> findWasteLabRecordList(WasteLabRecord wasteLabRecord) {
        LambdaQueryWrapper<WasteLabRecord> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询实验室台账
     *
     * @param page           分页参数
     * @param wasteLabRecord 实验室台账
     * @return 实验室台账
     */
    @Override
    public PageT<WasteLabRecord> findWasteLabRecordPage(PageT<WasteLabRecord> page, WasteLabRecord wasteLabRecord) {
        LambdaQueryWrapper<WasteLabRecord> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
