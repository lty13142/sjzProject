package com.sjz.evaluations.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.evaluations.model.entity.GrOrganizationManagement;
import org.apache.ibatis.annotations.Param;


/**
 * 基层组织管理Mapper接口
 * 
 * @author zzyt
 * @date 2023-04-03
 */
public interface GrOrganizationManagementMapper extends BaseMapper<GrOrganizationManagement> {


    PageT<GrOrganizationManagement> findGrOrganizationManagementPage(PageT<GrOrganizationManagement> page, @Param("orgMent") GrOrganizationManagement grOrganizationManagement);
}
