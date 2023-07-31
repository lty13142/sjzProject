package com.crcm.bpm.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName ClaimTaskVO
 * @Description：任务签收视图传输类
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/19
 **/
@Data
public class ClaimTaskVO implements Serializable {
    /**
     * 任务ID
     */
    private Long taskId;
    /**
     * 业务数据
     */
    private Map<String,Object> businessData;
}
