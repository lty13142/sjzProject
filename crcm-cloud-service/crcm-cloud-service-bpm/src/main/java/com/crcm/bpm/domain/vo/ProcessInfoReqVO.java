package com.crcm.bpm.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName ProcessInfoVo
 * @Description：流程信息获取请求类
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/13
 **/
@Data
@ToString
public class ProcessInfoReqVO implements Serializable {
    /**
     * 流程id
     */
    private Long processId;

    /**
     * 流程KEY
     */
    private String processKey;

    /**
     * 部门id
     */
    private String departmentId;

    /**
     * 部门名
     */
    private String departmentName;
}
