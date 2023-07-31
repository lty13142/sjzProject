package com.sjz.partbuilding.model.vo;

import com.sjz.partbuilding.model.entity.PartyDues;
import lombok.Data;

import java.util.List;

@Data
public class PartyDuesExportVo {
    /**
     * 行号
     */
    private int rowNo;
    /**
     * 姓名
     */
    private String name;
    /**
     *1月党费
     */
    private String january;
    /**
     *2月党费
     */
    private String february;
    /**
     *3月党费
     */
    private String march;
    /**
     *4月党费
     */
    private String april;
    /**
     *5月党费
     */
    private String may;
    /**
     *6月党费
     */
    private String june;
    /**
     *7月党费
     */
    private String july;
    /**
     *8月党费
     */
    private String august;
    /**
     *9月党费
     */
    private String september;
    /**
     *10月党费
     */
    private String october;
    /**
     *11月党费
     */
    private String november;
    /**
     *12月党费
     */
    private String december;
    /**
     *累计党费
     */
    private String total;

    /**
     * 党费明细列表
     */
    private List<PartyDues> partyDuesList;

    /**
     *备注
     */
    private String remark;
}
