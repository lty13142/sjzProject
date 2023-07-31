package com.sjz.partbuilding.model.vo;

import com.sjz.partbuilding.model.entity.PartyPayments;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PartyPaymentsVo extends PartyPayments {
    /**
     * 序号
     */
    private int rowNo;
    /**
     * 收入
     */
    private BigDecimal inMoney;
    /**
     * 支出
     */
    private BigDecimal outMoney;
    /**
     * 费用发生时间
     */
    private String paymentTimeStr;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 年份
     */
    private String year;
}
