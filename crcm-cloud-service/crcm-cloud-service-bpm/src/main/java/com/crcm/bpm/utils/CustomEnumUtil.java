package com.crcm.bpm.utils;

import com.crcm.bpm.core.constant.EnumUtilConstants;
import com.crcm.bpm.core.exception.CheckedException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wxl
 * @date 2020/4/23 15:59:02
 */
@Slf4j
public class CustomEnumUtil<E extends Enum<?> & EnumUtil> {

    private Class<E> clazz;

    CustomEnumUtil(Class<E> clazz) {
        this.clazz = clazz;
    }

    /**
     * 枚举转换，通过desc值返回枚举
     *
     * @param desc
     * @return
     */
    private E descOf(Object desc) {
        E[] es = clazz.getEnumConstants();
        for (int i = 0; i < es.length; ++i) {
            E e = es[i];
            if (e.getDesc() == desc) {
                return e;
            }

            if (desc instanceof Number) {
                if (e.getDesc() instanceof Number && ((Number) desc).doubleValue() == ((Number) e.getDesc()).doubleValue()) {
                    return e;
                }
            } else if (String.valueOf(desc).equals(String.valueOf(e.getDesc()))) {
                return e;
            }
        }

        return null;
    }

    /**
     * 通过枚举名获取枚举
     *
     * @param value
     * @return
     */
    private E nameOf(Object value) {
        E[] es = clazz.getEnumConstants();
        for (int i = 0; i < es.length; ++i) {
            if (es[i].name().equals(value)) {
                return es[i];
            }
        }
        return null;
    }

    /**
     * 通过value获取枚举
     *
     * @param value
     * @return
     */
    private E valueOf(Object value) {
        E[] es = clazz.getEnumConstants();
        for (int i = 0; i < es.length; ++i) {
            E e = es[i];
            if (e.getValue() == value) {
                return e;
            }
            if (value instanceof Number) {
                if (e.getValue() instanceof Number && ((Number) value).doubleValue() == ((Number) e.getValue()).doubleValue()) {
                    return e;
                }
            } else if (String.valueOf(value).equals(String.valueOf(e.getValue()))) {
                return e;
            }
        }
        return null;
    }

    /**
     * 通过枚举名或枚举值value或desc获取枚举
     *
     * @param value
     * @return
     */
    public E getEnum(Object value) {
        E result = nameOf(value);
        if (result == null) {
            result = valueOf(value);
            if (result == null) {
                result = descOf(value);
            }
        }
        checkResult(result, EnumUtilConstants.GET_ENUM, value);
        return result;
    }

    /**
     * 枚举转换，通过枚举名或desc返回value值
     *
     * @param value
     * @return
     */
    public Object getValue(Object value) {
        E result = nameOf(value);
        if (result == null) {
            result = descOf(value);
        }
        checkResult(result, EnumUtilConstants.GET_VALUE, value);
        return result.getValue();
    }

    /**
     * 枚举转换，通过枚举名或value返回desc值
     *
     * @param value
     * @return
     */
    public Object getDesc(Object value) {
        E result = nameOf(value);
        if (result == null) {
            result = valueOf(value);
        }
        checkResult(result, EnumUtilConstants.GET_DESC, value);
        return result.getDesc();
    }

    private void checkResult(E result, String method, Object value){
        if(result == null){
            log.error("不存在的枚举，class={},方法：{}，入参：{}",clazz.getName(), method, value);
            throw new CheckedException("不存在的枚举");
        }
    }
}
