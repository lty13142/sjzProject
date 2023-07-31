package com.crcm.bpm.service;

import com.crcm.bpm.domain.entity.ProcessCollectionDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 流程收藏表 服务类
 * </p>
 *
 * @author wxl
 * @since 2021-03-05
 */
public interface ProcessCollectionService extends IService<ProcessCollectionDO> {

    /**
     * 保存
     * @param processCollectionDO
     * @return
     */
    Boolean saveEntity(ProcessCollectionDO processCollectionDO);

}
