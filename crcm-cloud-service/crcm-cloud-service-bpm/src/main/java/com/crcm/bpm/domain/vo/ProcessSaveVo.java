package com.crcm.bpm.domain.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName ProcessSaveVo
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/8/19/15:37
 **/
@Getter
@Setter
@ToString
public class ProcessSaveVo {

    /**
     * 流程定义key
     */
    private String processKey;

    /**
     * 流程定义名字
     */
    private String processName;

    /**
     * 流程发布文件名字
     */
    private String resourceName;

    /**
     * 流程定义内容
     */
    private String xml;

    /**
     * 流程定义svg
     */
    private String svg;
    private String type;
    private String formId;
    private String formName;
    private Long processId;
}
