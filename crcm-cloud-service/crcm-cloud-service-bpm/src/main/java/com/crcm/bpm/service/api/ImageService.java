package com.crcm.bpm.service.api;

import java.util.List;

/**
 * 流程追踪图生成类
 *
 * @author liuxz
 */
public interface ImageService {

    /**
     * 根据流程实例标识，生成流程跟踪图示（高亮）
     *
     * @param procInstId 流程实例标识
     * @return
     * @throws Exception
     */
    byte[] generateImageByProcInstId(String procInstId) throws Exception;

    /**
     * 获取已执行的线
     * @param procInstId
     * @return
     */
    List<String> getAccessLinesIds(String procInstId);

}
