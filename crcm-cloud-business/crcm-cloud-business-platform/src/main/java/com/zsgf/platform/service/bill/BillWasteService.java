package com.zsgf.platform.service.bill;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import com.zsgf.platform.model.entity.bill.BillWaste;

/**
 * 电子联单废物信息Service接口
 * 
 * @author zzyt
 * @date 2023-02-22
 */
public interface BillWasteService extends IService<BillWaste>{

    /**
     * 新增电子联单废物信息
     * 
     * @param billWaste 电子联单废物信息
     * @return 结果
     */
    boolean saveBillWaste(BillWaste billWaste);

    /**
     * 修改电子联单废物信息
     * 
     * @param billWaste 电子联单废物信息
     * @return 结果
     */
    boolean updateBillWaste(BillWaste billWaste);

    /**
     * 删除电子联单废物信息信息
     * 
     * @param id 电子联单废物信息ID
     * @return 结果
     */
    boolean deleteBillWaste(String id);

    /**
     * 查询电子联单废物信息
     *
     * @param id 电子联单废物信息ID
     * @return 电子联单废物信息
     */
    BillWaste findBillWasteById(String id);

    /**
     * 查询电子联单废物信息列表
     *
     * @param billWaste 电子联单废物信息
     * @return 电子联单废物信息集合
     */
    List<BillWaste> findBillWasteList(BillWaste billWaste);

    /**
     * 分页查询电子联单废物信息列表
     * @param page 分页参数
     * @param billWaste 电子联单废物信息
     * @return 电子联单废物信息集合
     */
    PageT<BillWaste> findBillWastePage(PageT<BillWaste> page, BillWaste billWaste);
}
