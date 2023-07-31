package com.sjz.evaluations.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.evaluations.model.entity.RegionExamine;

import java.util.List;

/**
 * 区域考核实体Service接口
 *
 * @author zzyt
 * @date 2023-04-25
 */
public interface RegionExamineService extends IService<RegionExamine> {

    /**
     * 新增区域考核实体
     *
     * @param RegionExamine 区域考核实体
     * @return 结果
     */
    boolean saveRegionExamine(RegionExamine RegionExamine);

    /**
     * 修改区域考核实体
     *
     * @param RegionExamine 区域考核实体
     * @return 结果
     */
    boolean updateRegionExamine(RegionExamine RegionExamine);

    /**
     * 删除区域考核实体信息
     *
     * @param id 区域考核实体ID
     * @return 结果
     */
    boolean deleteRegionExamine(String id);

    /**
     * 查询区域考核实体
     *
     * @param id 区域考核实体ID
     * @return 区域考核实体
     */
    RegionExamine findRegionExamineById(String id);

    /**
     * 查询区域考核实体列表
     *
     * @param RegionExamine 区域考核实体
     * @return 区域考核实体集合
     */
    List<RegionExamine> findRegionExamineList(RegionExamine RegionExamine);

    /**
     * 分页查询区域考核实体列表
     *
     * @param page          分页参数
     * @param RegionExamine 区域考核实体
     * @return 区域考核实体集合
     */
    IPage<RegionExamine> findRegionExaminePage(PageT page, RegionExamine RegionExamine);
}
