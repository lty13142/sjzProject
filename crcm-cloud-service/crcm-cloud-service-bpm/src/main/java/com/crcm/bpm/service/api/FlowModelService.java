package com.crcm.bpm.service.api;

import org.flowable.engine.repository.Deployment;

/**
 * @ClassName ModelService
 * @Description：模型服务
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/17/13:51
 **/
public interface FlowModelService {
    /**
     * 通过XML流程图发布模型
     * @param name
     * @param key
     * @param tenantId
     * @param category
     * @param modelXml
     * @return
     */
    Deployment deploy(String name,String key, String tenantId, String category, String modelXml);
}
