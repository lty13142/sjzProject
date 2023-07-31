package com.zsgf.platform.service.business;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.business.WasteBusinessInOut;

import java.util.List;

/**
 * 经营出入场记录Service接口
 *
 * @author gzl
 * @date 2023-02-22
 */
public interface WasteBusinessInOutService extends IService<WasteBusinessInOut> {

    /**
     * 新增经营出入场记录
     *
     * @param wasteBusinessInOut 经营出入场记录
     * @return 结果
     */
    boolean saveWasteBusinessInOut(WasteBusinessInOut wasteBusinessInOut);

    /**
     * 修改经营出入场记录
     *
     * @param wasteBusinessInOut 经营出入场记录
     * @return 结果
     */
    boolean updateWasteBusinessInOut(WasteBusinessInOut wasteBusinessInOut);

    /**
     * 删除经营出入场记录信息
     *
     * @param id 经营出入场记录ID
     * @return 结果
     */
    boolean deleteWasteBusinessInOut(String id);

    /**
     * 查询经营出入场记录
     *
     * @param id 经营出入场记录ID
     * @return 经营出入场记录
     */
    WasteBusinessInOut findWasteBusinessInOutById(String id);

    /**
     * 查询经营出入场记录列表
     *
     * @param wasteBusinessInOut 经营出入场记录
     * @return 经营出入场记录集合
     */
    List<WasteBusinessInOut> findWasteBusinessInOutList(WasteBusinessInOut wasteBusinessInOut);

    /**
     * 分页查询经营出入场记录列表
     *
     * @param page               分页参数
     * @param wasteBusinessInOut 经营出入场记录
     * @return 经营出入场记录集合
     */
    PageT<WasteBusinessInOut> findWasteBusinessInOutPage(PageT<WasteBusinessInOut> page, WasteBusinessInOut wasteBusinessInOut);
}
