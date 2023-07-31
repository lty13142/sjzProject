package com.zsgf.platform.service.impl.collect;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.collect.CollectDeptMapper;
import com.zsgf.platform.model.entity.collect.CollectDept;
import com.zsgf.platform.service.collect.CollectDeptService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企业收集部门信息Service业务层处理
 *
 * @author gzl
 * @date 2023-03-23
 */
@Service
public class CollectDeptServiceImpl extends ServiceImpl<CollectDeptMapper, CollectDept> implements CollectDeptService {


    /**
     * 新增企业收集部门信息
     *
     * @param collectDept 企业收集部门信息
     * @return 结果
     */
    @Override
    public boolean saveCollectDept(CollectDept collectDept) {
        return this.save(collectDept);
    }

    /**
     * 修改企业收集部门信息
     *
     * @param collectDept 企业收集部门信息
     * @return 结果
     */
    @Override
    public boolean updateCollectDept(CollectDept collectDept) {
        return this.updateById(collectDept);
    }

    /**
     * 删除企业收集部门信息信息
     *
     * @param id 企业收集部门信息ID
     * @return 结果
     */
    @Override
    public boolean deleteCollectDept(String id) {
        return this.removeById(id);
    }

    /**
     * 查询企业收集部门信息
     *
     * @param id 企业收集部门信息ID
     * @return 企业收集部门信息
     */
    @Override
    public CollectDept findCollectDeptById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询企业收集部门列表
     *
     * @param collectDept 企业收集部门信息
     * @return 企业收集部门信息
     */
    @Override
    public List<CollectDept> findCollectDeptList(CollectDept collectDept) {
        LambdaQueryWrapper<CollectDept> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询企业收集部门信息
     *
     * @param page        分页参数
     * @param collectDept 企业收集部门信息
     * @return 企业收集部门信息
     */
    @Override
    public PageT<CollectDept> findCollectDeptPage(PageT<CollectDept> page, CollectDept collectDept) {
        LambdaQueryWrapper<CollectDept> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
