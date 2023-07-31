package com.zsgf.platform.service.company;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.dto.company.WasteInfoQueryDTO;
import com.zsgf.platform.model.entity.company.WasteInfo;
import com.zsgf.platform.vo.company.WasteInfoVo;

import java.util.List;

/**
 * 企业废物信息Service接口
 *
 * @author zzyt
 * @date 2023-02-22
 */
public interface WasteInfoService extends IService<WasteInfo> {

    /**
     * 新增企业废物信息
     *
     * @param wasteInfo 企业废物信息
     * @return 结果
     */
    boolean saveWasteInfo(WasteInfo wasteInfo);

    /**
     * 修改企业废物信息
     *
     * @param wasteInfo 企业废物信息
     * @return 结果
     */
    boolean updateWasteInfo(WasteInfo wasteInfo);

    /**
     * 删除企业废物信息信息
     *
     * @param id 企业废物信息ID
     * @return 结果
     */
    boolean deleteWasteInfo(String id);

    /**
     * 查询企业废物信息
     *
     * @param id 企业废物信息ID
     * @return 企业废物信息
     */
    WasteInfo findWasteInfoById(String id);

    /**
     * 查询企业废物信息列表
     *
     * @param wasteInfo 企业废物信息
     * @return 企业废物信息集合
     */
    List<WasteInfo> findWasteInfoList(WasteInfoQueryDTO wasteInfo);

    /**
     * 分页查询企业废物信息列表
     *
     * @param page      分页参数
     * @param wasteInfo 企业废物信息
     * @return 企业废物信息集合
     */
    PageT<WasteInfoVo> findWasteInfoPage(PageT<WasteInfoVo> page, WasteInfoQueryDTO wasteInfo);
}
