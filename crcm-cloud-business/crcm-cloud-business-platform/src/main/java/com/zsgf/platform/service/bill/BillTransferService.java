package com.zsgf.platform.service.bill;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.bill.BillTransfer;

import java.util.List;

/**
 * 电子联单运输信息Service接口
 *
 * @author zzyt
 * @date 2023-02-22
 */
public interface BillTransferService extends IService<BillTransfer> {

    /**
     * 新增电子联单运输信息
     *
     * @param billTransfer 电子联单运输信息
     * @return 结果
     */
    boolean saveBillTransfer(BillTransfer billTransfer);

    /**
     * 修改电子联单运输信息
     *
     * @param billTransfer 电子联单运输信息
     * @return 结果
     */
    boolean updateBillTransfer(BillTransfer billTransfer);

    /**
     * 删除电子联单运输信息信息
     *
     * @param id 电子联单运输信息ID
     * @return 结果
     */
    boolean deleteBillTransfer(String id);

    /**
     * 查询电子联单运输信息
     *
     * @param id 电子联单运输信息ID
     * @return 电子联单运输信息
     */
    BillTransfer findBillTransferById(String id);

    /**
     * 查询电子联单运输信息列表
     *
     * @param billTransfer 电子联单运输信息
     * @return 电子联单运输信息集合
     */
    List<BillTransfer> findBillTransferList(BillTransfer billTransfer);

    /**
     * 分页查询电子联单运输信息列表
     *
     * @param page         分页参数
     * @param billTransfer 电子联单运输信息
     * @return 电子联单运输信息集合
     */
    PageT<BillTransfer> findBillTransferPage(PageT<BillTransfer> page, BillTransfer billTransfer);
}
