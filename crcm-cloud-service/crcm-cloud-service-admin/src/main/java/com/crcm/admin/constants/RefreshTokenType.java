package com.crcm.admin.constants;

import lombok.Getter;

/**
 * @ClassName RefreshTokenType
 * @Description 刷新令牌类型
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Getter
public enum  RefreshTokenType {
    /**
     * 只返回token
     */
    ONLY_RETURN_TOKEN(1),

    /**
     * 返回完整token
     */
    RETURN_COMPLETE_TOKEN(2),

    ;

    /**
     * 刷新令牌类型
     */
    private final Integer type;

    RefreshTokenType(Integer type){
        this.type = type;
    }
}
