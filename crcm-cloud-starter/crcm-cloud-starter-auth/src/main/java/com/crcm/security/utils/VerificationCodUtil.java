package com.crcm.security.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @ClassName VerificationCodUtil
 * @Description 验证码工具类
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/17
 **/
public class VerificationCodUtil {
    /**
     * 随机字符串长度{@value}
     */
    private final static int MAX = 4;

    /**
     * 获取随机字符产
     *
     * @return java.lang.String
     */
    public static String getRandomString() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int index;
        //生成四位随机字符
        for (int i = 0; i < MAX; i++) {
            //随机生成0、1、2三个整数，代表数字字符、大写字母、小写字母，保证验证码的组成比较正态随机
            index = random.nextInt(3);
            //调用本类封装的私有方法，根据编号获得对应的字符
            char result = getChar(index);
            //追加到可变长字符串
            stringBuilder.append(result);
        }
        return stringBuilder.toString();
    }

    /**
     * 根据编号获得对应的字符
     *
     * @param item 随机生成0、1、2三个整数，代表数字字符、大写字母、小写字母，保证验证码的组成比较正态随机
     * @return char
     */
    private static char getChar(int item) {
        //数字字符范围
        int digitalBound = 10;
        //字符范围
        int charBound = 26;
        Random random = new Random();
        int index;
        char c;
        //根据调用时候的三个选项，生成数字、大写字母、小写字母三种不同的字符
        if (item == 0) {
            index = random.nextInt(digitalBound);
            c = (char) ('0' + index);
        } else if (item == 1) {
            index = random.nextInt(charBound);
            c = (char) ('A' + index);
        } else {
            index = random.nextInt(charBound);
            c = (char) ('a' + index);
        }
        return c;
    }

    /**
     * 验证码生成
     *
     * @param width   宽
     * @param height  高
     * @param content 内容
     * @return java.awt.image.BufferedImage
     */
    public static BufferedImage getImage(int width, int height, String content) {
        //创建指定大小和图片模式的缓冲图片对象
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //绘图对象
        Graphics2D graphics = (Graphics2D) img.getGraphics();
        //设置颜色
        graphics.setColor(new Color(68, 134, 49));
        //绘制填充矩形
        graphics.fillRect(0, 0, width, height);
        //设置画笔颜色
        graphics.setPaint(new Color(60, 63, 65));
        //设置字体
        Font font = new Font("微软雅黑", Font.BOLD, 40);
        graphics.setFont(font);

        // 计算文字长度，计算居中的x点坐标
        FontMetrics fm = graphics.getFontMetrics();
        int textWidth = fm.stringWidth(content);
        int trueWidth = (width - textWidth) / 2;
        // 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
        graphics.drawString(content, trueWidth, 40);
        // 释放对象
        graphics.dispose();
        return img;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getRandomString());
        }
    }
}
