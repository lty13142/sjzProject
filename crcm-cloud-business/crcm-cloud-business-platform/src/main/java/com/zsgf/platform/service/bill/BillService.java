package com.zsgf.platform.service.bill;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.bill.Bill;

import java.util.List;

/**
 * 电子联单Service接口
 *
 * @author zzyt
 * @date 2023-02-22
 */
public interface BillService extends IService<Bill> {

    /**
     * 新增电子联单
     *
     * @param bill 电子联单
     * @return 结果
     */
    boolean saveBill(Bill bill);

    /**
     * 修改电子联单
     *
     * @param bill 电子联单
     * @return 结果
     */
    boolean updateBill(Bill bill);

    /**
     * 删除电子联单信息
     *
     * @param id 电子联单ID
     * @return 结果
     */
    boolean deleteBill(String id);

    /**
     * 查询电子联单
     *
     * @param id 电子联单ID
     * @return 电子联单
     */
    Bill findBillById(String id);

    /**
     * 查询电子联单列表
     *
     * @param bill 电子联单
     * @return 电子联单集合
     */
    List<Bill> findBillList(Bill bill);

    /**
     * 分页查询电子联单列表
     *
     * @param page 分页参数
     * @param bill 电子联单
     * @return 电子联单集合
     */
    PageT<Bill> findBillPage(PageT<Bill> page, Bill bill);
}
