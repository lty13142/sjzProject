package com.crcm.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.admin.model.dto.AreaCoordinatesDTO;
import com.crcm.admin.model.dto.AreaDTO;
import com.crcm.admin.model.entity.SysArea;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.dto.TreeView;

import java.util.List;

/**
 * 区域Service接口
 *
 * @author cb
 * @date 2023-04-04
 */
public interface SysAreaService extends IService<SysArea> {

    /**
     * 新增区域
     *
     * @param area 区域
     * @return 结果
     */
    String saveArea(SysArea area);

    /**
     * 修改区域
     *
     * @param area 区域
     * @return 结果
     */
    String updateArea(SysArea area);

    /**
     * 删除区域信息
     *
     * @param id 区域ID
     * @return 结果
     */
    int deleteArea(String id);

    /**
     * 查询区域
     *
     * @param id 区域ID
     * @return 区域
     */
    SysArea findAreaById(String id);

    /**
     * 查询区域
     *
     * @param code 区域代码
     * @return 区域
     */
    SysArea findAreaByCode(String code);

    /**
     * 查询区域列表
     *
     * @param area 区域
     * @return 区域集合
     */
    List<SysArea> findAreaList(SysArea area);

    /**
     * 分页查询区域列表
     *
     * @param page 分页参数
     * @param area 区域
     * @return 区域集合
     */
    PageT<SysArea> findAreaPage(PageT<SysArea> page, AreaDTO area);

    /**
     * 查询组织区域树
     *
     * @param t
     * @return
     */
    List<TreeView> findTree(AreaDTO t);

    /**
     * 查询所有区域数据
     *
     * @return
     */
    List<SysArea> findAreas();


    List<SysArea> findAreaByType(String type);

    SysArea getInfoByName(String name);

    void updateCoordinates(List<AreaCoordinatesDTO> area);

    List<SysArea> getSubsetByPid(String pid);

    List<SysArea> getAreaListById(String id);

    /**
     * 根据pCode查询下属区域
     *
     * @param pCode 上一层级的code
     * @return 下属区域列表
     */
    List<SysArea> getByParentCode(String pCode);

    /**
     * 查询所有村级区域数据，按照管区的ASC顺序来ASC
     *
     * @param regionCode 管区code
     * @return 所有村级区域
     */
    List<SysArea> findVillageAsc(String regionCode);

    /**
     * 查询所有管区区域数据，按照管区CODE的ASC顺序
     *
     * @param regionCode 管区code
     * @return 所有管区区域
     */
    List<SysArea> findRegionAsc(String regionCode);
}
