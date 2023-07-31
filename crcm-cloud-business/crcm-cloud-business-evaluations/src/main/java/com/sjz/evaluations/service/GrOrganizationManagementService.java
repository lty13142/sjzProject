package com.sjz.evaluations.service;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.evaluations.model.entity.GrOrganizationManagement;


import java.util.List;


/**
 * 基层组织管理Service接口
 * 
 * @author zzyt
 * @date 2023-04-03
 */
public interface GrOrganizationManagementService extends IService<GrOrganizationManagement>{

    /**
     * 新增基层组织管理
     * 
     * @param grOrganizationManagement 基层组织管理
     * @return 结果
     */
    boolean saveGrOrganizationManagement(GrOrganizationManagement grOrganizationManagement);

    /**
     * 修改基层组织管理
     * 
     * @param grOrganizationManagement 基层组织管理
     * @return 结果
     */
    boolean updateGrOrganizationManagement(GrOrganizationManagement grOrganizationManagement);

    /**
     * 删除基层组织管理信息
     * 
     * @param id 基层组织管理ID
     * @return 结果
     */
    boolean deleteGrOrganizationManagement(String id);

    /**
     * 查询基层组织管理
     *
     * @param id 基层组织管理ID
     * @return 基层组织管理
     */
    GrOrganizationManagement findGrOrganizationManagementById(String id);

    /**
     * 查询基层组织管理列表
     *
     * @param grOrganizationManagement 基层组织管理
     * @return 基层组织管理集合
     */
    List<GrOrganizationManagement> findGrOrganizationManagementList(GrOrganizationManagement grOrganizationManagement);

    /**
     * 分页查询基层组织管理列表
     * @param page 分页参数
     * @param grOrganizationManagement 基层组织管理
     * @return 基层组织管理集合
     */
    PageT<GrOrganizationManagement> findGrOrganizationManagementPage(PageT<GrOrganizationManagement> page, GrOrganizationManagement grOrganizationManagement);
}
