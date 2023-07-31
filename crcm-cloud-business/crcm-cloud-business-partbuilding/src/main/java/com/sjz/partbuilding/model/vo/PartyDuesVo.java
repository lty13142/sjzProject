package com.sjz.partbuilding.model.vo;

import com.sjz.partbuilding.model.entity.PartyDues;
import lombok.Data;

import java.io.Serializable;


@Data
public class PartyDuesVo extends PartyDues implements Serializable {
    /**
     * 序号
     */
    private int rowNo;
    /**
     * 月份
     */
    private String monthNum;

}