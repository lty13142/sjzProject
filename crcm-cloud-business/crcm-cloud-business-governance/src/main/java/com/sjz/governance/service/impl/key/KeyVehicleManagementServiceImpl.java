package com.sjz.governance.service.impl.key;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;

import com.sjz.governance.mapper.key.KeyVehicleManagementMapper;
import com.sjz.governance.model.dto.key.KeyVehicleManagementDTO;
import com.sjz.governance.model.vo.key.KeyVehicleManagementVO;
import org.springframework.stereotype.Service;
import com.sjz.governance.model.entity.key.KeyVehicleManagement;
import com.sjz.governance.service.key.KeyVehicleManagementService;

/**
 * 综合治理_重点车辆管理Service业务层处理
 * 
 * @author pengl
 * @date 2023-04-03
 */
@Service
public class KeyVehicleManagementServiceImpl extends ServiceImpl<KeyVehicleManagementMapper, KeyVehicleManagement> implements KeyVehicleManagementService {

    

    /**
     * 新增综合治理_重点车辆管理
     * 
     * @param keyVehicleManagement 综合治理_重点车辆管理
     * @return 结果
     */
    @Override
    public boolean saveKeyVehicleManagement(KeyVehicleManagement keyVehicleManagement) {
        return this.save(keyVehicleManagement);
    }

    /**
     * 修改综合治理_重点车辆管理
     * 
     * @param keyVehicleManagement 综合治理_重点车辆管理
     * @return 结果
     */
    @Override
    public boolean updateKeyVehicleManagement(KeyVehicleManagement keyVehicleManagement) {
        return this.updateById(keyVehicleManagement);
    }

    /**
     * 删除综合治理_重点车辆管理信息
     * 
     * @param id 综合治理_重点车辆管理ID
     * @return 结果
     */
    @Override
    public boolean deleteKeyVehicleManagement(Integer id) {
        return this.removeById(id);
    }

    /**
     * 查询综合治理_重点车辆管理
     *
     * @param id 综合治理_重点车辆管理ID
     * @return 综合治理_重点车辆管理
     */
    @Override
    public KeyVehicleManagement findKeyVehicleManagementById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询综合治理_重点车辆管理列表
     *
     * @param keyVehicleManagement 综合治理_重点车辆管理
     * @return 综合治理_重点车辆管理
     */
    @Override
    public List<KeyVehicleManagement> findKeyVehicleManagementList(KeyVehicleManagement keyVehicleManagement) {
        LambdaQueryWrapper<KeyVehicleManagement> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询综合治理_重点车辆管理
     *
     * @param page 分页参数
     * @param dto 综合治理_重点车辆管理
     * @return 综合治理_重点车辆管理
     */
    @Override
    public PageT<KeyVehicleManagementVO> findKeyVehicleManagementPage(PageT<KeyVehicleManagementVO> page, KeyVehicleManagementDTO dto) {
        return this.baseMapper.findKeyVehicleManagementPage(page, dto);
    }
}
