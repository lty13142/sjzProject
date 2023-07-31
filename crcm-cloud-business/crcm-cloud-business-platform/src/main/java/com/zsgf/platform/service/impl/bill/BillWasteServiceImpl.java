package com.zsgf.platform.service.impl.bill;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.bill.BillWasteMapper;
import com.zsgf.platform.model.entity.bill.BillWaste;
import com.zsgf.platform.service.bill.BillWasteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 电子联单废物信息Service业务层处理
 *
 * @author zzyt
 * @date 2023-02-22
 */
@Service
public class BillWasteServiceImpl extends ServiceImpl<BillWasteMapper, BillWaste> implements BillWasteService {


    /**
     * 新增电子联单废物信息
     *
     * @param billWaste 电子联单废物信息
     * @return 结果
     */
    @Override
    public boolean saveBillWaste(BillWaste billWaste) {
        return this.save(billWaste);
    }

    /**
     * 修改电子联单废物信息
     *
     * @param billWaste 电子联单废物信息
     * @return 结果
     */
    @Override
    public boolean updateBillWaste(BillWaste billWaste) {
        return this.updateById(billWaste);
    }

    /**
     * 删除电子联单废物信息信息
     *
     * @param id 电子联单废物信息ID
     * @return 结果
     */
    @Override
    public boolean deleteBillWaste(String id) {
        return this.removeById(id);
    }

    /**
     * 查询电子联单废物信息
     *
     * @param id 电子联单废物信息ID
     * @return 电子联单废物信息
     */
    @Override
    public BillWaste findBillWasteById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询电子联单废物信息列表
     *
     * @param billWaste 电子联单废物信息
     * @return 电子联单废物信息
     */
    @Override
    public List<BillWaste> findBillWasteList(BillWaste billWaste) {
        LambdaQueryWrapper<BillWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询电子联单废物信息
     *
     * @param page      分页参数
     * @param billWaste 电子联单废物信息
     * @return 电子联单废物信息
     */
    @Override
    public PageT<BillWaste> findBillWastePage(PageT<BillWaste> page, BillWaste billWaste) {
        LambdaQueryWrapper<BillWaste> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
