package com.sjz.education.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: sjz-project
 * @description: 我的积分获得及支出记录VO
 * @author: sssccc
 * @create: 2023-04-06 15:24
 **/
@Data
public class RecordVO implements Serializable {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 获得积分汇总
     */
    private Integer earnPoints;

    /**
     * 获得积分流水
     */
    private List<ReportRecordVO> earnPointsList;
    /**
     * 支出积分汇总
     */
    private Integer consumePoints;

    /**
     * 支出积分流水
     */
    private List<ExchangeRecordVO> consumePointsList;
}
