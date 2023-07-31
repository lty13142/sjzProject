package com.zsgf.platform.service.impl.bill;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.bill.BillMapper;
import com.zsgf.platform.model.entity.bill.Bill;
import com.zsgf.platform.service.bill.BillService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 电子联单Service业务层处理
 *
 * @author zzyt
 * @date 2023-02-22
 */
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements BillService {


    /**
     * 新增电子联单
     *
     * @param bill 电子联单
     * @return 结果
     */
    @Override
    public boolean saveBill(Bill bill) {
        return this.save(bill);
    }

    /**
     * 修改电子联单
     *
     * @param bill 电子联单
     * @return 结果
     */
    @Override
    public boolean updateBill(Bill bill) {
        return this.updateById(bill);
    }

    /**
     * 删除电子联单信息
     *
     * @param id 电子联单ID
     * @return 结果
     */
    @Override
    public boolean deleteBill(String id) {
        return this.removeById(id);
    }

    /**
     * 查询电子联单
     *
     * @param id 电子联单ID
     * @return 电子联单
     */
    @Override
    public Bill findBillById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询电子联单列表
     *
     * @param bill 电子联单
     * @return 电子联单
     */
    @Override
    public List<Bill> findBillList(Bill bill) {
        LambdaQueryWrapper<Bill> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询电子联单
     *
     * @param page 分页参数
     * @param bill 电子联单
     * @return 电子联单
     */
    @Override
    public PageT<Bill> findBillPage(PageT<Bill> page, Bill bill) {
        LambdaQueryWrapper<Bill> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
