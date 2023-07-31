package com.crcm.bpm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.bpm.domain.dto.NodeAssigneesDTO;
import com.crcm.bpm.domain.dto.request.ApplyReqDTO;
import com.crcm.bpm.domain.dto.response.ApplyDTO;
import com.crcm.bpm.domain.dto.response.FlowUserTaskDTO;
import com.crcm.bpm.domain.entity.ApplyDO;
import com.crcm.bpm.domain.vo.ApplyAddReqVO;

import java.util.List;

public interface ApplyService extends IService<ApplyDO> {

    int saveApply(ApplyDO applyDO);

    int updateApply(ApplyDO applyDO);

    int deleteById(String id);

    int realDelete(String id);

    ApplyDO findById(String id);

    List<ApplyDO> findList(ApplyDO applyDO);

    IPage<ApplyDO> findPage(Page page, ApplyDO applyDO);

    List<FlowUserTaskDTO> startProcess(ApplyReqDTO applyAddReqDTO);

    String generateApplySn(String applySnPrefix);

    ApplyDTO getApplyByApplyId(Long applyId);

    void updateApplyStatusByProInsId(ApplyDO applyDO);

    /**
     * 保存草稿
     * @param applyAddReqVO
     * @return
     */
    Boolean saveDraft(ApplyAddReqVO applyAddReqVO);

    /**
     * 发起前获取流程节点信息，（是否需要选择处理人）
     * @param applyReqDTO
     * @return
     */
    List<NodeAssigneesDTO> getStartNodeInfo(ApplyReqDTO applyReqDTO);

    /** 
    * @Description: 查询关联流程 
    * @Param: 
    * @Author: dzl 
    * @Date: 2021/5/31 17:45 
    */ 
    ApplyDO getForRelation(ApplyDO applyDO);
}
