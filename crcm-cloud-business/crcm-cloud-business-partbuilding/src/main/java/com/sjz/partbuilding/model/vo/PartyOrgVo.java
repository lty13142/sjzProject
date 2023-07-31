package com.sjz.partbuilding.model.vo;

import com.sjz.partbuilding.model.entity.PartyOrg;
import lombok.Data;

import java.io.Serializable;

/**
 * @author rmc
 * @version 1.0
 * @date 2020/10/21 15:40
 */
@Data
public class PartyOrgVo extends PartyOrg implements Serializable {

    /**
     * 党员  预备党员 入党积极分子 申请人管理
     */
    public String partyPerson;
    public String prepareParty;
    public String entryParty;
    public String prepareApply;
    private String contacts;
    private String address;
    private String companyName;
    private String companyPhone;

    /**
     * 序号
     */
    private int rowNo;

    /**
     * 工会成员总人数
     */
    private int gOrgTotalMembers;
}
