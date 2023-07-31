package com.sjz.partbuilding.model.vo;

import com.sjz.partbuilding.model.entity.WorkFeedback;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author rmc
 * @version 1.0
 * @date 2020/10/18 9:30
 */
@Data
public class WorkFeedBackVo extends WorkFeedback implements Serializable {

    /**
     * 序号
     */
    private int rowNo;

    /**
     * 组织名
     */
    private String orgName;

    /**
     * 截止时间
     */
    private Date closeTime;

    /**
     * 完成情况
     */
    private String completeCh;

    /**
     * 超时情况
     */
    private String timeOutCh;
    /**
     * 已完成
     */
    private String toComplete;
    /**
     * 未完成
     */
    private String notComplete;
    /**
     * 总数
     */
    private String countComplete;


}
