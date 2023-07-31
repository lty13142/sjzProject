package com.zsgf.platform.service.wasteProduce;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.wasteProduce.WasteProduceOut;

import java.util.List;

/**
 * 产废出库信息Service接口
 *
 * @author zzyt
 * @date 2023-02-22
 */
public interface WasteProduceOutService extends IService<WasteProduceOut> {

    /**
     * 新增产废出库信息
     *
     * @param wasteProduceOut 产废出库信息
     * @return 结果
     */
    boolean saveWasteProduceOut(WasteProduceOut wasteProduceOut);

    /**
     * 修改产废出库信息
     *
     * @param wasteProduceOut 产废出库信息
     * @return 结果
     */
    boolean updateWasteProduceOut(WasteProduceOut wasteProduceOut);

    /**
     * 删除产废出库信息信息
     *
     * @param id 产废出库信息ID
     * @return 结果
     */
    boolean deleteWasteProduceOut(String id);

    /**
     * 查询产废出库信息
     *
     * @param id 产废出库信息ID
     * @return 产废出库信息
     */
    WasteProduceOut findWasteProduceOutById(String id);

    /**
     * 查询产废出库信息列表
     *
     * @param wasteProduceOut 产废出库信息
     * @return 产废出库信息集合
     */
    List<WasteProduceOut> findWasteProduceOutList(WasteProduceOut wasteProduceOut);

    /**
     * 分页查询产废出库信息列表
     *
     * @param page            分页参数
     * @param wasteProduceOut 产废出库信息
     * @return 产废出库信息集合
     */
    PageT<WasteProduceOut> findWasteProduceOutPage(PageT<WasteProduceOut> page, WasteProduceOut wasteProduceOut);
}
