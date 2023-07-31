package com.sjz.partbuilding.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrgEventsVo implements Serializable {

    private String month;
    private Long zhiwei;
    private Long dangyuan;
    private Long xiaozu;
    private Long dangke;

}
