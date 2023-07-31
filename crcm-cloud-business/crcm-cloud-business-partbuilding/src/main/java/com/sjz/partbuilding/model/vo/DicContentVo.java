package com.sjz.partbuilding.model.vo;

import com.crcm.core.dto.ConstantData;
import lombok.Data;

@Data
public class DicContentVo {
    private static final long serialVersionUID = 2459720658746162401L;

    private String id;
    private String type;
    private String dicCode;
    private String name;
    private String value;
    private String comments;
    private Integer max;

    public DicContentVo() {
    }

    public DicContentVo(ConstantData constantData) {
        this.dicCode = constantData.getCode();
        this.value = String.valueOf(constantData.getValue());
        this.name = constantData.getChName();
    }
}
