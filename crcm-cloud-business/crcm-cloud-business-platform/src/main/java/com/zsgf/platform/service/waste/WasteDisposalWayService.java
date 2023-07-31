package com.zsgf.platform.service.waste;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.dto.waste.WasteDisposalWayDTO;
import com.zsgf.platform.model.entity.waste.WasteDisposalWay;
import com.zsgf.platform.vo.waste.WasteDisposalWayVo;

import java.util.List;

/**
 * 废物处置方式Service接口
 *
 * @author gzl
 * @date 2023-03-30
 */
public interface WasteDisposalWayService extends IService<WasteDisposalWay> {

    /**
     * 新增废物处置方式
     *
     * @param wasteDisposalWay 废物处置方式
     * @return 结果
     */
    boolean saveWasteDisposalWay(WasteDisposalWay wasteDisposalWay);

    /**
     * 修改废物处置方式
     *
     * @param wasteDisposalWay 废物处置方式
     * @return 结果
     */
    boolean updateWasteDisposalWay(WasteDisposalWay wasteDisposalWay);

    /**
     * 删除废物处置方式信息
     *
     * @param id 废物处置方式ID
     * @return 结果
     */
    boolean deleteWasteDisposalWay(String id);

    /**
     * 查询废物处置方式
     *
     * @param id 废物处置方式ID
     * @return 废物处置方式
     */
    WasteDisposalWay findWasteDisposalWayById(String id);

    /**
     * 查询废物处置方式列表
     *
     * @param wasteDisposalWay 废物处置方式
     * @return 废物处置方式集合
     */
    List<WasteDisposalWay> findWasteDisposalWayList(WasteDisposalWayDTO wasteDisposalWay);

    /**
     * 分页查询废物处置方式列表
     *
     * @param page             分页参数
     * @param wasteDisposalWay 废物处置方式
     * @return 废物处置方式集合
     */
    PageT<WasteDisposalWayVo> findWasteDisposalWayPage(PageT<WasteDisposalWayVo> page, WasteDisposalWayDTO wasteDisposalWay);
}
