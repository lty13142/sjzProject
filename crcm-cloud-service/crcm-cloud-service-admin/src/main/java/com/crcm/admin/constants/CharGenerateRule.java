package com.crcm.admin.constants;

import lombok.Getter;

/**
 * @ClassName PasswordGenerateRule
 * @Description 密码生成规则
 * @Author GZL
 * @Date 2023/2/23 16:01
 * @Version 1.0
 **/
@Getter
public enum CharGenerateRule {
    /**
     * 无序
     */
    NO_ORDER(1),
    /**
     * 大写字母+小写字母+数字+符号
     */
    UPPERCASE_LOWERCASE_NUMBER_CHARACTER(2),
    /**
     * 字母+数字+符号
     */
    LETTER_NUMBER_CHARACTER(3),
    /**
     * 字母+符号+数字
     */
    LETTER_CHARACTER_NUMBER(4);

    private final Integer type;

    CharGenerateRule(Integer type){
        this.type = type;
    }

}
