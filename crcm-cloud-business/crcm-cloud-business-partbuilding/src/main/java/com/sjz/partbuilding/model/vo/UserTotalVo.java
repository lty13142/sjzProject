package com.sjz.partbuilding.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2021/4/7 11:19
 **/
@Data
public class UserTotalVo implements Serializable {
    /**
     * 支部名称
     */
    private String partyName;
    /**
     * 党员
     */
    private Integer partyCount;
    /**
     * 预备党员
     */
    private  Integer prePartyCount;
    /**
     * 女党员
     */
    private  Integer womanPartCount;
    /**
     * 积极分子
     */
    private Integer jjPartyCount;
    /**
     * 少数名族
     */
    private Integer ssPartyCount;
}
