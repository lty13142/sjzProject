package com.zsgf.platform.service.impl.solid;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.solid.SolidUseFacilitiesMapper;
import com.zsgf.platform.model.entity.solid.SolidUseFacilities;
import com.zsgf.platform.service.solid.SolidUseFacilitiesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据共享_一般工业固体废物_综合利用设施信息Service业务层处理
 *
 * @author gzl
 * @date 2023-03-27
 */
@Service
public class SolidUseFacilitiesServiceImpl extends ServiceImpl<SolidUseFacilitiesMapper, SolidUseFacilities> implements SolidUseFacilitiesService {


    /**
     * 新增数据共享_一般工业固体废物_综合利用设施信息
     *
     * @param solidUseFacilities 数据共享_一般工业固体废物_综合利用设施信息
     * @return 结果
     */
    @Override
    public boolean saveSolidUseFacilities(SolidUseFacilities solidUseFacilities) {
        return this.save(solidUseFacilities);
    }

    /**
     * 修改数据共享_一般工业固体废物_综合利用设施信息
     *
     * @param solidUseFacilities 数据共享_一般工业固体废物_综合利用设施信息
     * @return 结果
     */
    @Override
    public boolean updateSolidUseFacilities(SolidUseFacilities solidUseFacilities) {
        return this.updateById(solidUseFacilities);
    }

    /**
     * 删除数据共享_一般工业固体废物_综合利用设施信息信息
     *
     * @param id 数据共享_一般工业固体废物_综合利用设施信息ID
     * @return 结果
     */
    @Override
    public boolean deleteSolidUseFacilities(String id) {
        return this.removeById(id);
    }

    /**
     * 查询数据共享_一般工业固体废物_综合利用设施信息
     *
     * @param id 数据共享_一般工业固体废物_综合利用设施信息ID
     * @return 数据共享_一般工业固体废物_综合利用设施信息
     */
    @Override
    public SolidUseFacilities findSolidUseFacilitiesById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询数据共享_一般工业固体废物_综合利用设施信息列表
     *
     * @param solidUseFacilities 数据共享_一般工业固体废物_综合利用设施信息
     * @return 数据共享_一般工业固体废物_综合利用设施信息
     */
    @Override
    public List<SolidUseFacilities> findSolidUseFacilitiesList(SolidUseFacilities solidUseFacilities) {
        LambdaQueryWrapper<SolidUseFacilities> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询数据共享_一般工业固体废物_综合利用设施信息
     *
     * @param page               分页参数
     * @param solidUseFacilities 数据共享_一般工业固体废物_综合利用设施信息
     * @return 数据共享_一般工业固体废物_综合利用设施信息
     */
    @Override
    public PageT<SolidUseFacilities> findSolidUseFacilitiesPage(PageT<SolidUseFacilities> page, SolidUseFacilities solidUseFacilities) {
        LambdaQueryWrapper<SolidUseFacilities> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
