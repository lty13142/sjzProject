package com.crcm.admin.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Locale;

/**
 * @ClassName PasswordValidUtil
 * @Description 密码（弱口令）验证工具类
 * @Author GZL
 * @Date 2023/2/2 14:17
 * @Version 1.0
 **/
public class PasswordValidUtil {

    /**
     * 密码最小长度，默认为8
     */
    private final static int MIN_LENGTH = 8;
    /**
     * 密码最大长度，默认为20
     */
    private final static int MAX_LENGTH = 64;
    /**
     * 特殊符号集合
     */
    private final static String SPECIAL_CHAR = "!\\\"#$%&'()*,-./:;<=>?@[\\\\]^_`{|}~";
    /**
     * 键盘物理位置横向不允许最小的连续个数
     */
    private final static int LIMIT_HORIZONTAL_NUM_KEY = 4;
    /**
     * 键盘物理位置斜向不允许最小的连续个数
     */
    private final static int LIMIT_SLOPE_NUM_KEY = 4;
    /**
     * 密码口令中字符在逻辑位置上不允许最小的连续个数
     */
    private final static int LIMIT_LOGIC_NUM_CHAR = 4;
    /**
     * 密码口令中相同字符不允许最小的连续个数
     */
    private final static int LIMIT_NUM_SAME_CHAR = 4;

    /**
     * 键盘横向方向规则
     */
    private final static String[] KEYBOARD_HORIZONTAL_ARR = {"01234567890", "qwertyuiop", "asdfghjkl", "zxcvbnm",};
    /**
     * 键盘斜线方向规则
     */
    private final static String[] KEYBOARD_SLOPE_ARR = {"1qaz", "2wsx", "3edc", "4rfv", "5tgb", "6yhn", "7ujm", "8ik,",
            "9ol.", "0p;/", "=[;.", "-pl,", "0okm", "9ijn", "8uhb", "7ygv", "6tfc", "5rdx", "4esz"};
    /**
     * 常用词库
     */
    private final static String[] SIMPLE_WORDS = {"admin", "szim", "epicrouter", "password", "grouter", "dare", "root",
            "guest", "user", "success", "pussy", "mustang", "fuckme", "jordan", "test", "hunter", "jennifer", "batman",
            "thomas", "soccer", "sexy", "killer", "george", "asshole", "fuckyou", "summer", "hello", "secret", "fucker",
            "enter", "cookie", "administrator",
            // 中国网民常用密码
            "xiaoming", "taobao", "iloveyou", "woaini", "982464",
            // 国外网民常用密码
            "monkey", "letmein", "trustno1", "dragon", "baseball", "master", "sunshine", "ashley", "bailey", "shadow",
            "superman", "football", "michael", "qazwsx"};


    /**
     * 检测密码中字符长度
     *
     * @param password 密码
     * @return 符合长度要求 返回
     */
    private static boolean checkPasswordLength(String password) {
        // 如未设置最大长度，仅判断最小长度即可
        return password.length() >= MIN_LENGTH && password.length() <= MAX_LENGTH;
    }

    /**
     * 检查密码中是否包含数字
     *
     * @param password 密码
     * @return 包含数字 返回true
     */
    private static boolean checkContainDigit(String password) {
        for (char pass : password.toCharArray()) {
            if (Character.isDigit(pass)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查密码中是否包含字母(不区分大小写)
     *
     * @param password 密码
     * @return 包含字母 返回true
     */
    private static boolean checkContainCase(String password) {
        for (char pass : password.toCharArray()) {
            if (Character.isLetter(pass)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查密码中是否包含小写字母
     *
     * @param password 密码
     * @return 包含小写字母 返回true
     */
    private static boolean checkContainLowerCase(String password) {
        char[] chPass = password.toCharArray();
        for (char pass : chPass) {
            if (Character.isLowerCase(pass)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查密码中是否包含大写字母
     *
     * @param password 密码
     * @return 包含大写字母 返回true
     */
    private static boolean checkContainUpperCase(String password) {
        char[] chPass = password.toCharArray();
        for (char pass : chPass) {
            if (Character.isUpperCase(pass)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查密码中是否包含特殊字符
     *
     * @param password 密码
     * @return 包含特殊字符 返回true
     */
    private static boolean checkContainSpecialChar(String password) {
        for (char pass : password.toCharArray()) {
            if (SPECIAL_CHAR.indexOf(pass) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 键盘规则匹配器 横向连续检测
     *
     * @param password 密码
     * @return 含有横向连续字符串 返回true
     */
    private static boolean checkLateralKeyboardSite(String password) {
        // 将字符串内所有字符转为小写
        for (int i = 0; i + LIMIT_HORIZONTAL_NUM_KEY <= password.length(); i++) {
            String distinguishStr = password.substring(i, i + LIMIT_HORIZONTAL_NUM_KEY);
            for (String configStr : KEYBOARD_HORIZONTAL_ARR) {
                String revOrderStr = new StringBuilder(configStr).reverse().toString();
                // 考虑 大写键盘匹配的情况
                String upperStr = configStr.toUpperCase();
                if ((configStr.contains(distinguishStr)) || (upperStr.contains(distinguishStr))) {
                    return true;
                }
                // 考虑逆序输入情况下 连续输入
                String revUpperStr = new StringBuilder(upperStr).reverse().toString();
                if ((revOrderStr.contains(distinguishStr)) || (revUpperStr.contains(distinguishStr))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 键盘规则匹配器 斜向规则检测
     *
     * @param password 密码
     * @return 含有斜向连续字符串 返回true
     */
    private static boolean checkKeyboardSlantSite(String password) {
        String t_password = password.toLowerCase(Locale.ENGLISH);
        // 键盘斜线方向规则检测
        for (int i = 0; i + LIMIT_SLOPE_NUM_KEY <= t_password.length(); i++) {
            String distinguishStr = password.substring(i, i + LIMIT_SLOPE_NUM_KEY);
            for (String configStr : KEYBOARD_SLOPE_ARR) {
                String revOrderStr = new StringBuilder(configStr).reverse().toString();
                // 考虑 大写键盘匹配的情况
                String UpperStr = configStr.toUpperCase();
                if ((configStr.contains(distinguishStr)) || (UpperStr.contains(distinguishStr))) {
                    return true;
                }
                // 考虑逆序输入情况下 连续输入
                String revUpperStr = new StringBuilder(UpperStr).reverse().toString();
                if ((revOrderStr.contains(distinguishStr)) || (revUpperStr.contains(distinguishStr))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 评估a-z,z-a这样的连续字符
     *
     * @param password 密码
     * @return 含有a-z,z-a连续字符串 返回true
     */
    private static boolean checkSequentialChars(String password) {
        // 检测包含字母(区分大小写)
        char[] pwdCharArr = password.toCharArray();
        for (int i = 0; i + LIMIT_LOGIC_NUM_CHAR <= password.length(); i++) {
            int normal_count = 0;
            int reversed_count = 0;
            for (int j = 0; j < LIMIT_LOGIC_NUM_CHAR - 1; j++) {
                if (pwdCharArr[i + j + 1] - pwdCharArr[i + j] == 1) {
                    normal_count++;
                    if (normal_count == LIMIT_LOGIC_NUM_CHAR - 1) {
                        return true;
                    }
                }
                if (pwdCharArr[i + j] - pwdCharArr[i + j + 1] == 1) {
                    reversed_count++;
                    if (reversed_count == LIMIT_LOGIC_NUM_CHAR - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 评估aaaa, 1111这样的相同连续字符
     *
     * @param password 密码
     * @return 含有aaaa, 1111等连续字符串 返回true
     */
    private static boolean checkSequentialSameChars(String password) {
        char[] pwdCharArr = password.toCharArray();
        boolean flag = false;
        for (int i = 0; i + LIMIT_NUM_SAME_CHAR <= password.length(); i++) {
            int count = 0;
            for (int j = 0; j < LIMIT_NUM_SAME_CHAR - 1; j++) {
                if (pwdCharArr[i + j] == pwdCharArr[i + j + 1]) {
                    count++;
                    if (count == LIMIT_NUM_SAME_CHAR - 1) {
                        return true;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 检测常用词库
     *
     * @param password 密码
     * @return 含有常见词库 返回true
     */
    private static boolean checkSimpleWord(String password) {
        return Arrays.asList(SIMPLE_WORDS).contains(password.toLowerCase());
    }

    /**
     * 评估密码中包含的字符类型是否符合要求
     *
     * @param password 密码
     * @return 符合要求 返回true
     */
    public static boolean evalPassword(String password) {
        if (StringUtils.isBlank(password)) {
            return false;
        }
        // 检测长度
        if (!checkPasswordLength(password)) {
            return false;
        }
        // 检测包含数字
        if (!checkContainDigit(password)) {
            return false;
        }
        // 检测包含字母
        if (!checkContainCase(password)) {
            return false;
        }
        // 检测字母区分大小写
        if (!checkContainLowerCase(password) || !checkContainUpperCase(password)) {
            return false;
        }
        // 检测包含特殊符号
        if (!checkContainSpecialChar(password)) {
            return false;
        }
        // 检测键盘横向连续
        if (checkLateralKeyboardSite(password)) {
            return false;
        }
        // 检测键盘斜向连续
        if (checkKeyboardSlantSite(password)) {
            return false;
        }
        // 检测逻辑位置连续
        if (checkSequentialChars(password)) {
            return false;
        }
        // 检测相邻字符是否相同
        if (checkSequentialSameChars(password)) {
            return false;
        }
        // 检测常用词库
        return !checkSimpleWord(password);
    }

    public static void main(String[] args) {
        boolean b = PasswordValidUtil.evalPassword("Zzyt@123");
        System.out.println("b = " + b);
    }
}
