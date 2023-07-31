package com.crcm.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crcm.admin.model.vo.SysPositionVo;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.crcm.admin.mapper.SysPositionMapper;
import com.crcm.admin.model.entity.SysPosition;
import com.crcm.admin.service.SysPositionService;

/**
 * 岗位Service业务层处理
 * 
 * @author cb
 * @date 2023-04-06
 */
@Service
public class SysPositionServiceImpl extends ServiceImpl<SysPositionMapper, SysPosition> implements SysPositionService {

    

    /**
     * 新增岗位
     * 
     * @param position 岗位
     * @return 结果
     */
    @Override
    public boolean savePosition(SysPosition position) {
        return this.save(position);
    }

    /**
     * 修改岗位
     * 
     * @param position 岗位
     * @return 结果
     */
    @Override
    public boolean updatePosition(SysPosition position) {
        return this.updateById(position);
    }

    /**
     * 删除岗位信息
     * 
     * @param id 岗位ID
     * @return 结果
     */
    @Override
    public boolean deletePosition(String id) {
        return this.removeById(id);
    }

    /**
     * 查询岗位
     *
     * @param id 岗位ID
     * @return 岗位
     */
    @Override
    public SysPosition findPositionById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询岗位列表
     *
     * @param position 岗位
     * @return 岗位
     */
    @Override
    public List<SysPosition> findPositionList(SysPosition position) {
        LambdaQueryWrapper<SysPosition> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(position.getDepartmentId()), SysPosition::getDepartmentId,position.getDepartmentId());
        queryWrapper.eq(StringUtils.isNotBlank(position.getPositionLevel()), SysPosition::getPositionLevel,position.getPositionLevel());
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询岗位
     *
     * @param page 分页参数
     * @param position 岗位
     * @return 岗位
     */
    @Override
    public PageT<SysPosition> findPositionPage(PageT<SysPosition> page, SysPosition position) {
        LambdaQueryWrapper<SysPosition> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(position.getDepartmentId()), SysPosition::getDepartmentId,position.getDepartmentId());
        queryWrapper.like(StringUtils.isNotBlank(position.getPositionName()), SysPosition::getPositionName,position.getPositionName());
        queryWrapper.eq(StringUtils.isNotBlank(position.getPositionLevel()), SysPosition::getPositionLevel,position.getPositionLevel());
        return this.page(page, queryWrapper);
    }

    @Override
    public PageT<SysPositionVo> findPositionPageVo(PageT<SysPositionVo> page, SysPositionVo position) {
        return this.baseMapper.findPositionPageVo(page,position);
    }
}
