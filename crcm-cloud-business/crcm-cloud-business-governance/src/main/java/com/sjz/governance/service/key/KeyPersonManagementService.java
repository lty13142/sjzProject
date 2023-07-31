package com.sjz.governance.service.key;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

import com.sjz.governance.model.dto.key.KeyPersonManagementDTO;
import com.sjz.governance.model.entity.key.KeyPersonManagement;
import com.sjz.governance.model.vo.key.KeyPersonManagementVO;

/**
 * 综合治理_重点人员管理Service接口
 * 
 * @author pengl
 * @date 2023-04-03
 */
public interface KeyPersonManagementService extends IService<KeyPersonManagement>{

    /**
     * 新增综合治理_重点人员管理
     * 
     * @param dto 综合治理_重点人员管理
     * @return 结果
     */
    boolean saveKeyPersonManagement(KeyPersonManagementDTO dto);

    /**
     * 修改综合治理_重点人员管理
     * 
     * @param keyPersonManagement 综合治理_重点人员管理
     * @return 结果
     */
    boolean updateKeyPersonManagement(KeyPersonManagementDTO keyPersonManagement);

    /**
     * 删除综合治理_重点人员管理信息
     * 
     * @param id 综合治理_重点人员管理ID
     * @return 结果
     */
    boolean deleteKeyPersonManagement(Integer id);

    /**
     * 查询综合治理_重点人员管理
     *
     * @param id 综合治理_重点人员管理ID
     * @return 综合治理_重点人员管理
     */
    KeyPersonManagement findKeyPersonManagementById(Integer id);

    /**
     * 查询综合治理_重点人员管理列表
     *
     * @param keyPersonManagement 综合治理_重点人员管理
     * @return 综合治理_重点人员管理集合
     */
    List<KeyPersonManagement> findKeyPersonManagementList(KeyPersonManagement keyPersonManagement);

    /**
     * 分页查询综合治理_重点人员管理列表
     * @param page 分页参数
     * @param dto 综合治理_重点人员管理
     * @return 综合治理_重点人员管理集合
     */
    PageT<KeyPersonManagementVO> findKeyPersonManagementPage(PageT<KeyPersonManagementVO> page, KeyPersonManagementDTO dto);

    /**
     * 综合治理_创建人脸数据库
     * @param dto 综合治理_创建人脸数据库
     * @return 综合治理_创建人脸数据库
     */
    String createFaceDatabase(KeyPersonManagementDTO dto);
}
