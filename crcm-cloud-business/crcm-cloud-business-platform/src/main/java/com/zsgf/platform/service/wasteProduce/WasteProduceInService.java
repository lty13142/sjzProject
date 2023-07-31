package com.zsgf.platform.service.wasteProduce;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.wasteProduce.WasteProduceIn;

import java.util.List;

/**
 * 产废入库信息Service接口
 *
 * @author zzyt
 * @date 2023-02-22
 */
public interface WasteProduceInService extends IService<WasteProduceIn> {

    /**
     * 新增产废入库信息
     *
     * @param wasteProduceIn 产废入库信息
     * @return 结果
     */
    boolean saveWasteProduceIn(WasteProduceIn wasteProduceIn);

    /**
     * 修改产废入库信息
     *
     * @param wasteProduceIn 产废入库信息
     * @return 结果
     */
    boolean updateWasteProduceIn(WasteProduceIn wasteProduceIn);

    /**
     * 删除产废入库信息信息
     *
     * @param id 产废入库信息ID
     * @return 结果
     */
    boolean deleteWasteProduceIn(String id);

    /**
     * 查询产废入库信息
     *
     * @param id 产废入库信息ID
     * @return 产废入库信息
     */
    WasteProduceIn findWasteProduceInById(String id);

    /**
     * 查询产废入库信息列表
     *
     * @param wasteProduceIn 产废入库信息
     * @return 产废入库信息集合
     */
    List<WasteProduceIn> findWasteProduceInList(WasteProduceIn wasteProduceIn);

    /**
     * 分页查询产废入库信息列表
     *
     * @param page           分页参数
     * @param wasteProduceIn 产废入库信息
     * @return 产废入库信息集合
     */
    PageT<WasteProduceIn> findWasteProduceInPage(PageT<WasteProduceIn> page, WasteProduceIn wasteProduceIn);
}
