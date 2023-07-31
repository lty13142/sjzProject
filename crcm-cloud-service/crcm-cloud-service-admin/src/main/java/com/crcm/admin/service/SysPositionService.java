package com.crcm.admin.service;

import com.crcm.admin.model.vo.SysPositionVo;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import com.crcm.admin.model.entity.SysPosition;

/**
 * 岗位Service接口
 * 
 * @author cb
 * @date 2023-04-06
 */
public interface SysPositionService extends IService<SysPosition>{

    /**
     * 新增岗位
     * 
     * @param position 岗位
     * @return 结果
     */
    boolean savePosition(SysPosition position);

    /**
     * 修改岗位
     * 
     * @param position 岗位
     * @return 结果
     */
    boolean updatePosition(SysPosition position);

    /**
     * 删除岗位信息
     * 
     * @param id 岗位ID
     * @return 结果
     */
    boolean deletePosition(String id);

    /**
     * 查询岗位
     *
     * @param id 岗位ID
     * @return 岗位
     */
    SysPosition findPositionById(String id);

    /**
     * 查询岗位列表
     *
     * @param position 岗位
     * @return 岗位集合
     */
    List<SysPosition> findPositionList(SysPosition position);

    /**
     * 分页查询岗位列表
     * @param page 分页参数
     * @param position 岗位
     * @return 岗位集合
     */
    PageT<SysPosition> findPositionPage(PageT<SysPosition> page, SysPosition position);

    PageT<SysPositionVo> findPositionPageVo(PageT<SysPositionVo> page, SysPositionVo position);


}
