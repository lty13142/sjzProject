package com.crcm.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.admin.model.dto.AreaCoordinatesDTO;
import com.crcm.admin.model.dto.AreaDTO;
import com.crcm.admin.model.entity.SysArea;
import com.crcm.core.dto.TreeView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 区域Mapper接口
 * 
 * @author cb
 * @date 2023-04-04
 */
public interface SysAreaMapper extends BaseMapper<SysArea> {


	/**
	 * 验证区域存在个数
	 * @param area
	 * @return
	 */
	int findValidArea(SysArea area);

	/**
	 * 查询组织区域树
	 * @param t
	 * @return
	 */
	List<TreeView> findTreeView(AreaDTO t);

	List<SysArea> findAreas();

	/**
	 * 通过父id找到子级
	 * @param area
	 * @return
	 */
	List<SysArea> findChildList(SysArea area);

    SysArea findAreaById(String id);

    void updateCoordinates(AreaCoordinatesDTO dto);

	/**
	 * 查询所有村级区域数据，按照管区的ASC顺序来ASC
	 *
	 * @param regionCode 管区code
	 * @return 所有村级区域
	 */
	List<SysArea> findVillageAsc(@Param("regionCode") String regionCode);

	/**
	 * 查询所有管区区域数据，按照管区CODE的ASC顺序
	 *
	 * @param regionCode 管区code
	 * @return 所有管区区域
	 */
	List<SysArea> findRegionAsc(@Param("regionCode") String regionCode);
}
