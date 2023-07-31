package com.zsgf.platform.service.impl.collect;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.collect.CollectorMapper;
import com.zsgf.platform.model.entity.collect.Collector;
import com.zsgf.platform.service.collect.CollectorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企业收集人员信息Service业务层处理
 *
 * @author gzl
 * @date 2023-03-23
 */
@Service
public class CollectorServiceImpl extends ServiceImpl<CollectorMapper, Collector> implements CollectorService {


    /**
     * 新增企业收集人员信息
     *
     * @param collector 企业收集人员信息
     * @return 结果
     */
    @Override
    public boolean saveCollector(Collector collector) {
        return this.save(collector);
    }

    /**
     * 修改企业收集人员信息
     *
     * @param collector 企业收集人员信息
     * @return 结果
     */
    @Override
    public boolean updateCollector(Collector collector) {
        return this.updateById(collector);
    }

    /**
     * 删除企业收集人员信息信息
     *
     * @param id 企业收集人员信息ID
     * @return 结果
     */
    @Override
    public boolean deleteCollector(String id) {
        return this.removeById(id);
    }

    /**
     * 查询企业收集人员信息
     *
     * @param id 企业收集人员信息ID
     * @return 企业收集人员信息
     */
    @Override
    public Collector findCollectorById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询企业收集人员信息列表
     *
     * @param collector 企业收集人员信息
     * @return 企业收集人员信息
     */
    @Override
    public List<Collector> findCollectorList(Collector collector) {
        LambdaQueryWrapper<Collector> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询企业收集人员信息
     *
     * @param page      分页参数
     * @param collector 企业收集人员信息
     * @return 企业收集人员信息
     */
    @Override
    public PageT<Collector> findCollectorPage(PageT<Collector> page, Collector collector) {
        LambdaQueryWrapper<Collector> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
