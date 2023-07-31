package com.crcm.security.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName BpwdEncoderUtil
 * @Description 密码编码工具类
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/17
 **/
public class BpwdEncoderUtil {
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    /**
     * 用BCryptPasswordEncoder
     * @param password 密码
     * @return java.lang.String
     */
    public static String bCryptPassword(String password) {
        return ENCODER.encode(password);
    }

    /**
     * 密码匹配
     * @param rawPassword 未加工密码
     * @param encodedPassword 编码后密码
     * @return boolean
     */
    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return ENCODER.matches(rawPassword, encodedPassword);
    }
}
