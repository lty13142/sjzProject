package com.crcm.admin.utils;

import com.crcm.admin.constants.CharGenerateRule;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * @ClassName CharGenerateUtil
 * @Description 字符生成工具类
 * @Author GZL
 * @Date 2023/2/23 16:26
 * @Version 1.0
 **/
public class CharGenerateUtil {

    /**
     * 随机数生成器
     */
    private static SecureRandom random;

    /**
     * 特殊符号集合
     */
    private final static String SPECIAL_CHAR = "!\\\"#$%&'()*,-./:;<=>?@[\\\\]^_`{|}~";

    /**
     * 符号ASCII开始值
     */
    private final static int ASCII_CHAR_START = 33;
    /**
     * 小写字母ASCII开始值
     */
    private final static int ASCII_LOWERCASE_LETTERS_START = 97;
    /**
     * 小写字母ASCII结束值
     */
    private final static int ASCII_LOWERCASE_LETTERS_END = 122;

    /**
     * 生成随机数字
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/2/23 16:27
     * @Param maxNum 最大值
     **/
    public static Integer generateRandomInt(int maxNum) {
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            return Integer.MAX_VALUE;
        }
        return random.nextInt(maxNum);
    }

    /**
     * 随机生成指定长度的字符串
     *
     * @return 字符串
     * @Author GZL
     * @Date 2023/2/23 17:28
     * @Param length 字符串长度
     **/
    public static String generateRandomIntString(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(generateRandomInt(9));
        }
        return result.toString();
    }

    /**
     * 生成随机字符
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/2/23 16:27
     * @Param maxNum 最大值
     **/
    public static char generateChar() {
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("生成失败");
        }
        return (char) (random.nextInt(ASCII_LOWERCASE_LETTERS_END - ASCII_CHAR_START) + ASCII_CHAR_START);
    }

    /**
     * 随机生成指定长度的字符串
     *
     * @return 字符串
     * @Author GZL
     * @Date 2023/2/23 17:28
     * @Param length 字符串长度
     **/
    public static String generateRandomString(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(generateChar());
        }
        return result.toString();
    }

    /**
     * 生成随机小写字母
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/2/23 16:27
     **/
    public static char generateLoweCaseLetter() {
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("生成失败");
        }
        return (char) (random.nextInt(ASCII_LOWERCASE_LETTERS_END - ASCII_LOWERCASE_LETTERS_START) + ASCII_LOWERCASE_LETTERS_START);
    }

    /**
     * 随机生成指定长度的小写字符
     *
     * @return 字符串
     * @Author GZL
     * @Date 2023/2/23 17:28
     * @Param length 字符串长度
     **/
    public static String generateLoweCaseLetter(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(generateLoweCaseLetter());
        }
        return result.toString();
    }

    /**
     * 生成随机大写字母
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/2/23 16:27
     **/
    public static char generateUpperCaseLetter() {
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("生成失败");
        }
        return (char) (random.nextInt(ASCII_LOWERCASE_LETTERS_END - ASCII_LOWERCASE_LETTERS_START) + ASCII_LOWERCASE_LETTERS_START - 32);
    }

    /**
     * 随机生成指定长度的大写字符
     *
     * @return 字符串
     * @Author GZL
     * @Date 2023/2/23 17:28
     * @Param length 字符串长度
     **/
    public static String generateUpperCaseLetter(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(generateUpperCaseLetter());
        }
        return result.toString();
    }

    /**
     * 随机生成指定长度的大写字符
     *
     * @return 字符串
     * @Author GZL
     * @Date 2023/2/23 17:28
     * @Param length 字符串长度
     **/
    public static String generateLetters(int length) {
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < length; i++) {
                result.append(random.nextInt() % 2 == 0 ? generateLoweCaseLetter() : generateUpperCaseLetter());
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("生成失败");
        }
    }

    /**
     * 生成随机特殊字符
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/2/23 16:27
     **/
    public static char generateSpecialChar() {
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("生成失败");
        }
        return SPECIAL_CHAR.charAt(random.nextInt(SPECIAL_CHAR.length()));
    }


    /**
     * 随机生成指定长度的特殊字符
     *
     * @return 字符串
     * @Author GZL
     * @Date 2023/2/23 17:28
     * @Param length 字符串长度
     **/
    public static String generateSpecialChar(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(generateSpecialChar());
        }
        return result.toString();
    }

    /**
     * 生成指定长度的随机字符串
     *
     * @return 字符串
     * @Author GZL
     * @Date 2023/2/23 17:28
     * @Param length 字符串长度
     * @Param rule 规则
     **/
    public static String generateRoleString(CharGenerateRule rule, int... length) {
        StringBuilder result = new StringBuilder();
        if (Objects.isNull(rule) || length.length == 0) {
            return result.toString();
        }
        if (Objects.equals(CharGenerateRule.NO_ORDER, rule)) {
            return generateRandomString(length[0]);
        }
        if (Objects.equals(CharGenerateRule.UPPERCASE_LOWERCASE_NUMBER_CHARACTER, rule)) {
            result.append(generateUpperCaseLetter(length[0])).append(generateLoweCaseLetter((length.length > 1 ? length[1] : 0)))
                    .append(generateRandomIntString((length.length > 2 ? length[2] : 0))).append(generateSpecialChar((length.length > 3 ? length[3] : 0)));
            return result.toString();
        }
        if (Objects.equals(CharGenerateRule.LETTER_NUMBER_CHARACTER, rule)) {
            result.append(generateLetters(length[0])).append(generateRandomIntString((length.length > 1 ? length[1] : 0)))
                    .append(generateSpecialChar((length.length > 2 ? length[2] : 0)));
            return result.toString();
        }
        if (Objects.equals(CharGenerateRule.LETTER_CHARACTER_NUMBER, rule)) {
            result.append(generateLetters(length[0])).append(generateSpecialChar((length.length > 1 ? length[1] : 0)))
                    .append(generateRandomIntString((length.length > 2 ? length[2] : 0)));
            return result.toString();
        }
        return "";
    }

}
