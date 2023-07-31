package com.sjz.governance.service.key;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

import com.sjz.governance.model.dto.key.KeyVehicleManagementDTO;
import com.sjz.governance.model.entity.key.KeyVehicleManagement;
import com.sjz.governance.model.vo.key.KeyVehicleManagementVO;

/**
 * 综合治理_重点车辆管理Service接口
 * 
 * @author pengl
 * @date 2023-04-03
 */
public interface KeyVehicleManagementService extends IService<KeyVehicleManagement>{

    /**
     * 新增综合治理_重点车辆管理
     * 
     * @param keyVehicleManagement 综合治理_重点车辆管理
     * @return 结果
     */
    boolean saveKeyVehicleManagement(KeyVehicleManagement keyVehicleManagement);

    /**
     * 修改综合治理_重点车辆管理
     * 
     * @param keyVehicleManagement 综合治理_重点车辆管理
     * @return 结果
     */
    boolean updateKeyVehicleManagement(KeyVehicleManagement keyVehicleManagement);

    /**
     * 删除综合治理_重点车辆管理信息
     * 
     * @param id 综合治理_重点车辆管理ID
     * @return 结果
     */
    boolean deleteKeyVehicleManagement(Integer id);

    /**
     * 查询综合治理_重点车辆管理
     *
     * @param id 综合治理_重点车辆管理ID
     * @return 综合治理_重点车辆管理
     */
    KeyVehicleManagement findKeyVehicleManagementById(Integer id);

    /**
     * 查询综合治理_重点车辆管理列表
     *
     * @param keyVehicleManagement 综合治理_重点车辆管理
     * @return 综合治理_重点车辆管理集合
     */
    List<KeyVehicleManagement> findKeyVehicleManagementList(KeyVehicleManagement keyVehicleManagement);

    /**
     * 分页查询综合治理_重点车辆管理列表
     * @param page 分页参数
     * @param dto 综合治理_重点车辆管理
     * @return 综合治理_重点车辆管理集合
     */
    PageT<KeyVehicleManagementVO> findKeyVehicleManagementPage(PageT<KeyVehicleManagementVO> page, KeyVehicleManagementDTO dto);
}
