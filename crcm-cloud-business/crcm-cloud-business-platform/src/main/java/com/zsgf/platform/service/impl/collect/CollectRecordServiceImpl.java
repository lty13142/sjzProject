package com.zsgf.platform.service.impl.collect;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.collect.CollectRecordMapper;
import com.zsgf.platform.model.entity.collect.CollectRecord;
import com.zsgf.platform.service.collect.CollectRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企业收集台账Service业务层处理
 *
 * @author gzl
 * @date 2023-03-23
 */
@Service
public class CollectRecordServiceImpl extends ServiceImpl<CollectRecordMapper, CollectRecord> implements CollectRecordService {


    /**
     * 新增企业收集台账
     *
     * @param collectRecord 企业收集台账
     * @return 结果
     */
    @Override
    public boolean saveCollectRecord(CollectRecord collectRecord) {
        return this.save(collectRecord);
    }

    /**
     * 修改企业收集台账
     *
     * @param collectRecord 企业收集台账
     * @return 结果
     */
    @Override
    public boolean updateCollectRecord(CollectRecord collectRecord) {
        return this.updateById(collectRecord);
    }

    /**
     * 删除企业收集台账信息
     *
     * @param id 企业收集台账ID
     * @return 结果
     */
    @Override
    public boolean deleteCollectRecord(String id) {
        return this.removeById(id);
    }

    /**
     * 查询企业收集台账
     *
     * @param id 企业收集台账ID
     * @return 企业收集台账
     */
    @Override
    public CollectRecord findCollectRecordById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询企业收集台账列表
     *
     * @param collectRecord 企业收集台账
     * @return 企业收集台账
     */
    @Override
    public List<CollectRecord> findCollectRecordList(CollectRecord collectRecord) {
        LambdaQueryWrapper<CollectRecord> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询企业收集台账
     *
     * @param page          分页参数
     * @param collectRecord 企业收集台账
     * @return 企业收集台账
     */
    @Override
    public PageT<CollectRecord> findCollectRecordPage(PageT<CollectRecord> page, CollectRecord collectRecord) {
        LambdaQueryWrapper<CollectRecord> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
