package com.crcm.bpm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.bpm.api.vo.GetApproveOpinionListVO;
import com.crcm.bpm.domain.dto.request.UserTaskInfoQueryDTO;
import com.crcm.bpm.domain.dto.response.UserTaskInfoDTO;
import com.crcm.bpm.domain.entity.UserTaskDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserTaskMapper extends BaseMapper<UserTaskDO> {

    /**
     * 真实删除
     *
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    IPage<UserTaskInfoDTO> selectApplyList(Page page, @Param("taskQuery") UserTaskInfoQueryDTO userTaskInfoQueryDTO);

    IPage<UserTaskInfoDTO> getToDoListByCondition(Page page, @Param("taskQuery") UserTaskInfoQueryDTO userTaskInfoQuery);

    IPage<UserTaskInfoDTO> getDraftListByCondition(Page page, @Param("taskQuery") UserTaskInfoQueryDTO userTaskInfoQuery);

    IPage<UserTaskInfoDTO> getHaveDoListByCondition(Page page, @Param("taskQuery") UserTaskInfoQueryDTO userTaskInfoQuery);

    /**
     * 获取意见流转
     *
     * @param applyId
     * @return
     */
    List<GetApproveOpinionListVO> getApproveOpinionList(@Param("applyId") String applyId);
    
    /** 
    * @Description: 查询待办条数
    * @Param: 
    * @Author: dzl 
    * @Date: 2021/2/25 9:56 
    */ 
    Integer selectToDoNumber(@Param("empId") String empId);
}
