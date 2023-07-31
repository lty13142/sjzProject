package com.sjz.governance.mapper.key;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.governance.model.dto.key.KeyPersonManagementDTO;
import com.sjz.governance.model.entity.key.KeyPersonManagement;
import com.sjz.governance.model.vo.key.KeyPersonManagementVO;
import com.sjz.governance.model.vo.key.KeyVehicleManagementVO;
import org.apache.ibatis.annotations.Param;

/**
 * 综合治理_重点人员管理Mapper接口
 * 
 * @author pengl
 * @date 2023-04-03
 */
public interface KeyPersonManagementMapper extends BaseMapper<KeyPersonManagement> {

    /**
     * 分页查询
     * @param page
     * @param dto
     * @return
     */
    PageT<KeyPersonManagementVO> findKeyPersonManagementPage(@Param("page") PageT<KeyPersonManagementVO> page, @Param("dto") KeyPersonManagementDTO dto);


}