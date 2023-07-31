package com.crcm.core.extend.masking;

import org.springframework.util.StringUtils;

/**
 * @ClassName DataMaskingFunc
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2022/7/19
 **/
public enum DataMaskingFunc {
    /**
     *  脱敏转换器
     */
    NO_MASK((str, maskChar) -> {
        return str;
    }),
    ALL_MASK((str, maskChar) -> {
        if (StringUtils.hasLength(str)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                sb.append(StringUtils.hasLength(maskChar) ? maskChar : DataMaskingOperation.MASK_CHAR);
            }
            return sb.toString();
        } else {
            return str;
        }
    });

    private final DataMaskingOperation operation;

    private DataMaskingFunc(DataMaskingOperation operation) {
        this.operation = operation;
    }

    public DataMaskingOperation operation() {
        return this.operation;
    }
}
