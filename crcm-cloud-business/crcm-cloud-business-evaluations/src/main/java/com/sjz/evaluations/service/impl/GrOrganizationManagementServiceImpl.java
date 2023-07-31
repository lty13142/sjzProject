package com.sjz.evaluations.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;


import com.sjz.evaluations.mapper.GrOrganizationManagementMapper;
import com.sjz.evaluations.model.entity.GrOrganizationManagement;
import com.sjz.evaluations.service.GrOrganizationManagementService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基层组织管理Service业务层处理
 * 
 * @author zzyt
 * @date 2023-04-03
 */
@Service
public class GrOrganizationManagementServiceImpl extends ServiceImpl<GrOrganizationManagementMapper, GrOrganizationManagement> implements GrOrganizationManagementService {

    

    /**
     * 新增基层组织管理
     * 
     * @param grOrganizationManagement 基层组织管理
     * @return 结果
     */
    @Override
    public boolean saveGrOrganizationManagement(GrOrganizationManagement grOrganizationManagement) {
        return this.save(grOrganizationManagement);
    }

    /**
     * 修改基层组织管理
     * 
     * @param grOrganizationManagement 基层组织管理
     * @return 结果
     */
    @Override
    public boolean updateGrOrganizationManagement(GrOrganizationManagement grOrganizationManagement) {
        return this.updateById(grOrganizationManagement);
    }

    /**
     * 删除基层组织管理信息
     * 
     * @param id 基层组织管理ID
     * @return 结果
     */
    @Override
    public boolean deleteGrOrganizationManagement(String id) {
        return this.removeById(id);
    }

    /**
     * 查询基层组织管理
     *
     * @param id 基层组织管理ID
     * @return 基层组织管理
     */
    @Override
    public GrOrganizationManagement findGrOrganizationManagementById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询基层组织管理列表
     *
     * @param grOrganizationManagement 基层组织管理
     * @return 基层组织管理
     */
    @Override
    public List<GrOrganizationManagement> findGrOrganizationManagementList(GrOrganizationManagement grOrganizationManagement) {
        LambdaQueryWrapper<GrOrganizationManagement> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询基层组织管理
     *
     * @param page 分页参数
     * @param grOrganizationManagement 基层组织管理
     * @return 基层组织管理
     */
    @Override
    public PageT<GrOrganizationManagement> findGrOrganizationManagementPage(PageT<GrOrganizationManagement> page, GrOrganizationManagement grOrganizationManagement) {
       /* LambdaQueryWrapper<GrOrganizationManagement> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);*/
        return this.baseMapper.findGrOrganizationManagementPage(page,grOrganizationManagement);
    }
}
