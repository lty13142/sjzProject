package com.zsgf.platform.service.impl.business;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.business.WasteBusinessInOutMapper;
import com.zsgf.platform.model.entity.business.WasteBusinessInOut;
import com.zsgf.platform.service.business.WasteBusinessInOutService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 经营出入场记录Service业务层处理
 *
 * @author gzl
 * @date 2023-02-22
 */
@Service
public class WasteBusinessInOutServiceImpl extends ServiceImpl<WasteBusinessInOutMapper, WasteBusinessInOut> implements WasteBusinessInOutService {


    /**
     * 新增经营出入场记录
     *
     * @param wasteBusinessInOut 经营出入场记录
     * @return 结果
     */
    @Override
    public boolean saveWasteBusinessInOut(WasteBusinessInOut wasteBusinessInOut) {
        return this.save(wasteBusinessInOut);
    }

    /**
     * 修改经营出入场记录
     *
     * @param wasteBusinessInOut 经营出入场记录
     * @return 结果
     */
    @Override
    public boolean updateWasteBusinessInOut(WasteBusinessInOut wasteBusinessInOut) {
        return this.updateById(wasteBusinessInOut);
    }

    /**
     * 删除经营出入场记录信息
     *
     * @param id 经营出入场记录ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteBusinessInOut(String id) {
        return this.removeById(id);
    }

    /**
     * 查询经营出入场记录
     *
     * @param id 经营出入场记录ID
     * @return 经营出入场记录
     */
    @Override
    public WasteBusinessInOut findWasteBusinessInOutById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询经营出入场记录列表
     *
     * @param wasteBusinessInOut 经营出入场记录
     * @return 经营出入场记录
     */
    @Override
    public List<WasteBusinessInOut> findWasteBusinessInOutList(WasteBusinessInOut wasteBusinessInOut) {
        LambdaQueryWrapper<WasteBusinessInOut> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询经营出入场记录
     *
     * @param page               分页参数
     * @param wasteBusinessInOut 经营出入场记录
     * @return 经营出入场记录
     */
    @Override
    public PageT<WasteBusinessInOut> findWasteBusinessInOutPage(PageT<WasteBusinessInOut> page, WasteBusinessInOut wasteBusinessInOut) {
        LambdaQueryWrapper<WasteBusinessInOut> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
