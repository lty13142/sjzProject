package com.crcm.core.extend.masking;

/**
 * @ClassName DataMaskingOperation
 * @Description 数据脱敏操作接口
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2022/7/19
 **/
public interface DataMaskingOperation {
    String MASK_CHAR = "*";
    String mask(String content, String maskChar);
}
