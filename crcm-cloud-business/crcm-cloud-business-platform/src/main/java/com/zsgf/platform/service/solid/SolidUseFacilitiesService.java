package com.zsgf.platform.service.solid;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.solid.SolidUseFacilities;

import java.util.List;

/**
 * 数据共享_一般工业固体废物_综合利用设施信息Service接口
 *
 * @author gzl
 * @date 2023-03-27
 */
public interface SolidUseFacilitiesService extends IService<SolidUseFacilities> {

    /**
     * 新增数据共享_一般工业固体废物_综合利用设施信息
     *
     * @param solidUseFacilities 数据共享_一般工业固体废物_综合利用设施信息
     * @return 结果
     */
    boolean saveSolidUseFacilities(SolidUseFacilities solidUseFacilities);

    /**
     * 修改数据共享_一般工业固体废物_综合利用设施信息
     *
     * @param solidUseFacilities 数据共享_一般工业固体废物_综合利用设施信息
     * @return 结果
     */
    boolean updateSolidUseFacilities(SolidUseFacilities solidUseFacilities);

    /**
     * 删除数据共享_一般工业固体废物_综合利用设施信息信息
     *
     * @param id 数据共享_一般工业固体废物_综合利用设施信息ID
     * @return 结果
     */
    boolean deleteSolidUseFacilities(String id);

    /**
     * 查询数据共享_一般工业固体废物_综合利用设施信息
     *
     * @param id 数据共享_一般工业固体废物_综合利用设施信息ID
     * @return 数据共享_一般工业固体废物_综合利用设施信息
     */
    SolidUseFacilities findSolidUseFacilitiesById(String id);

    /**
     * 查询数据共享_一般工业固体废物_综合利用设施信息列表
     *
     * @param solidUseFacilities 数据共享_一般工业固体废物_综合利用设施信息
     * @return 数据共享_一般工业固体废物_综合利用设施信息集合
     */
    List<SolidUseFacilities> findSolidUseFacilitiesList(SolidUseFacilities solidUseFacilities);

    /**
     * 分页查询数据共享_一般工业固体废物_综合利用设施信息列表
     *
     * @param page               分页参数
     * @param solidUseFacilities 数据共享_一般工业固体废物_综合利用设施信息
     * @return 数据共享_一般工业固体废物_综合利用设施信息集合
     */
    PageT<SolidUseFacilities> findSolidUseFacilitiesPage(PageT<SolidUseFacilities> page, SolidUseFacilities solidUseFacilities);
}
