package com.sjz.partbuilding.model.vo;

import com.sjz.partbuilding.model.entity.OrgEvent;
import lombok.Data;

import java.io.Serializable;

/**
 * @author rmc
 * @version 1.0
 * @date 2020/10/21 19:20
 */
@Data
public class EventStatisticsVo extends OrgEvent implements Serializable {

    /**
     * 序号
     */
    private int rowNo;
    /**
     * 党员大会
     */
    private int congressOfParty;
    /**
     * 支委会
     */
    private int branchCommittee;
    /**
     * 党小组会
     */
    private int partyGroupMeeting;
    /**
     * 党课
     */
    private int partyClass;
    /**
     * 主题党日活动
     */
    private int themePartyDay;
    /**
     * 组织生活会
     */
    private int orgLifeMeeting;

}
