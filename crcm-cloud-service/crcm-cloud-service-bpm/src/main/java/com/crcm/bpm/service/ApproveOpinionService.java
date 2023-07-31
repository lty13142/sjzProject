package com.crcm.bpm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.bpm.domain.entity.ApproveOpinionDO;

import java.util.List;

/**
 * <p>
 * 审批意见表 服务类
 * </p>
 *
 * @author
 * @since 2020-11-23
 */
public interface ApproveOpinionService extends IService<ApproveOpinionDO> {

    /**
     * 获取流转意见
     *
     * @param applyId
     * @return
     */
    List<ApproveOpinionDO> getApproveOpinion(Long applyId);
}
