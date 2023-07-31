package com.crcm.bpm.service;

import com.crcm.bpm.domain.dto.response.*;
import com.crcm.bpm.domain.vo.ModelDataQueryVO;

import java.util.List;

/**
 * @ClassName ModelDesignDataService
 * @Description：模型设计数据接口
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/26
 **/
public interface ModelDesignDataService {

    /**
     * 获取用户列表
     *
     * @param queryVO
     * @return
     */
    List<PostUserDTO> getUserList(ModelDataQueryVO queryVO);


    /**
     * 获取公司树
     *
     * @param queryVO
     * @return
     */
    CompanyTreeDTO getCompanyTree(ModelDataQueryVO queryVO);

    /**
     * 获取公司部门
     * @param companyId
     * @return
     */
    List<DeptInfoDTO> getDeptList(String companyId);

    /**
     * 获取部门岗位
     * @param deptId
     * @return
     */
    List<PostInfoDTO> getPostList(String deptId);

}
