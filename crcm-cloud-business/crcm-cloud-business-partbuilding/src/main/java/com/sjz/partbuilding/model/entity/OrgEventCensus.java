package com.sjz.partbuilding.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 两会一课参与与组织活动
 */
@Data
public class OrgEventCensus implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * 两会一课名称
     */
    private ArrayList<String> orgEventName;

    /**
     * 参加次数
     */
    private ArrayList<Integer> joinCount;

    /**
     * 组织次数
     */
    private ArrayList<Integer> hostCount;
}
