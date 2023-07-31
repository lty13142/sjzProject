package com.crcm.security.components;

import com.crcm.security.utils.BpwdEncoderUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName CustomPasswordEncoder
 * @Description 自定义密码加密验证
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/7/15
 **/
public class CustomPasswordEncoder implements PasswordEncoder {
    /**
     * 加密密码
     * <p>
     *     如果为空则不加密.
     *     拓展手机号验证码等验证方式后
     *     除用户名密码方式其余密码为空不加密返回空串.
     * </p>
     * @param charSequence 原始密码密码
     * @return java.lang.String
     */
    @Override
    public String encode(CharSequence charSequence) {
        if ("".contentEquals(charSequence)) {
            return "";
        }
        return BpwdEncoderUtil.bCryptPassword((String) charSequence);
    }

    /**
     * 验证密码是否正确
     * <p>
     *     原密码与密文都为空时则属于扩展登录，
     *     扩展登录只校验相应规则，不校验密码.
     *     密码模式密码登录、授权码登录的获取授权码步骤 与 隐式模式 校验密码
     * </p>
     * @param charSequence 未加密的原密码
     * @param s            密文
     * @return boolean
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        if ("".contentEquals(charSequence) && "".equals(s)) {
            return true;
        }
        return BpwdEncoderUtil.matches(charSequence, s);
    }
}
