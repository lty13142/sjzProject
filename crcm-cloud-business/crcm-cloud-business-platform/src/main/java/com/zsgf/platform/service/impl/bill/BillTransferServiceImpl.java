package com.zsgf.platform.service.impl.bill;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.bill.BillTransferMapper;
import com.zsgf.platform.model.entity.bill.BillTransfer;
import com.zsgf.platform.service.bill.BillTransferService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 电子联单运输信息Service业务层处理
 *
 * @author zzyt
 * @date 2023-02-22
 */
@Service
public class BillTransferServiceImpl extends ServiceImpl<BillTransferMapper, BillTransfer> implements BillTransferService {


    /**
     * 新增电子联单运输信息
     *
     * @param billTransfer 电子联单运输信息
     * @return 结果
     */
    @Override
    public boolean saveBillTransfer(BillTransfer billTransfer) {
        return this.save(billTransfer);
    }

    /**
     * 修改电子联单运输信息
     *
     * @param billTransfer 电子联单运输信息
     * @return 结果
     */
    @Override
    public boolean updateBillTransfer(BillTransfer billTransfer) {
        return this.updateById(billTransfer);
    }

    /**
     * 删除电子联单运输信息信息
     *
     * @param id 电子联单运输信息ID
     * @return 结果
     */
    @Override
    public boolean deleteBillTransfer(String id) {
        return this.removeById(id);
    }

    /**
     * 查询电子联单运输信息
     *
     * @param id 电子联单运输信息ID
     * @return 电子联单运输信息
     */
    @Override
    public BillTransfer findBillTransferById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询电子联单运输信息列表
     *
     * @param billTransfer 电子联单运输信息
     * @return 电子联单运输信息
     */
    @Override
    public List<BillTransfer> findBillTransferList(BillTransfer billTransfer) {
        LambdaQueryWrapper<BillTransfer> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询电子联单运输信息
     *
     * @param page         分页参数
     * @param billTransfer 电子联单运输信息
     * @return 电子联单运输信息
     */
    @Override
    public PageT<BillTransfer> findBillTransferPage(PageT<BillTransfer> page, BillTransfer billTransfer) {
        LambdaQueryWrapper<BillTransfer> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
