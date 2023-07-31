package com.zsgf.platform.service.waste;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.dto.waste.WasteBaseInfoDTO;
import com.zsgf.platform.model.entity.waste.WasteBaseInfo;
import com.zsgf.platform.vo.WasteBaseInfoTreeVo;

import java.util.List;

/**
 * 危废名录Service接口
 *
 * @author gzl
 * @date 2023-02-22
 */
public interface WasteBaseInfoService extends IService<WasteBaseInfo> {

    /**
     * 新增危废名录
     *
     * @param wasteBaseInfo 危废名录
     * @return 结果
     */
    boolean saveWasteBaseInfo(WasteBaseInfo wasteBaseInfo);

    /**
     * 修改危废名录
     *
     * @param wasteBaseInfo 危废名录
     * @return 结果
     */
    boolean updateWasteBaseInfo(WasteBaseInfo wasteBaseInfo);

    /**
     * 删除危废名录信息
     *
     * @param id 危废名录ID
     * @return 结果
     */
    boolean deleteWasteBaseInfo(String id);

    /**
     * 查询危废名录
     *
     * @param id 危废名录ID
     * @return 危废名录
     */
    WasteBaseInfo findWasteBaseInfoById(String id);

    /**
     * 查询危废名录列表
     *
     * @param wasteBaseInfo 危废名录
     * @return 危废名录集合
     */
    List<WasteBaseInfo> findWasteBaseInfoList(WasteBaseInfoDTO wasteBaseInfo);

    /**
     * 分页查询危废名录列表
     *
     * @param page          分页参数
     * @param wasteBaseInfo 危废名录
     * @return 危废名录集合
     */
    PageT<WasteBaseInfo> findWasteBaseInfoPage(PageT<WasteBaseInfo> page, WasteBaseInfoDTO wasteBaseInfo);

    /**
     * 查询危废名录树
     *
     * @return 危废名录树
     * @Author GZL
     * @Date 2023/3/20 10:33
     * @Param 版本
     **/
    List<WasteBaseInfoTreeVo> findWasteTree(WasteBaseInfoDTO wasteBaseInfo);
}
