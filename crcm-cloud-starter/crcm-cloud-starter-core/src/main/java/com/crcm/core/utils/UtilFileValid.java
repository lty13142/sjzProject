package com.crcm.core.utils;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.Locale;

/**
 * @ClassName UtilFileValid
 * @Description 文件验证工具类
 * @Author GZL
 * @Date 2023/3/28 11:51
 * @Version 1.0
 **/
public class UtilFileValid {

    /**
     * 文件后缀 支持的类型
     */
    public static final String[] FILE_SUFFIX_SUPPORT = {".xlsx", ".xls", ".doc", ".docx", ".txt", ".csv",
            ".jpg", ".jpeg", ".png", ".gif", ".icon", ".bmp", ".mp3", ".mp4", ".avi", ".swf", ".pdf", ".zip", ".rar", ".gz", ".ppt", ".pptx", ".wps", ".svg"};
    /**
     * 文件名字 需要排除的字符
     */
    private static final String[] FILE_NAME_EXCLUDE = {
            "`", "!", "@", "#", "$", "%", "^", "&", "*", "=", "+", "~", "！", "￥", "……", "——", "",
            "?", ",", ":", ";", "{", "}", "/", "\\", "|", "？", "，", "。", "：", "；"
    };

    /**
     * 文件大小 1GB
     */
    private static final long FILE_SIZE = 1024 * 1024 * 1024;


    /**
     * 上传文件校验大小、名字、后缀
     *
     * @param file file
     */
    public static String uploadVerify(File file) {
        // 校验文件是否为空
        if (file == null) {
            return "文件不能为空！";
        }
        // 校验文件大小
        long size = file.length();
        if (size > FILE_SIZE) {
            return "上传文件过大！";
        }
        return verifyFileName(file.getName());
    }

    /**
     * 上传文件校验大小、名字、后缀
     *
     * @param multipartFile multipartFile
     */
    public static String uploadVerify(MultipartFile multipartFile) {
        // 校验文件是否为空
        if (multipartFile == null || multipartFile.isEmpty()) {
            return "文件不能为空！";
        }
        // 校验文件大小
        long size = multipartFile.getSize();
        if (size > FILE_SIZE) {
            return "上传文件过大！";
        }
        return verifyFileName(multipartFile.getOriginalFilename());
    }

    /**
     * 文件名称验证
     * @Author GZL
     * @Date 2023/3/3 15:08
     * @Param fileName 文件名称
     * @return 结果
     **/
    public static String verifyFileName(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return "文件名不能为空！";
        }
        String nameFlag = null;
        for (String s : FILE_NAME_EXCLUDE) {
            if (fileName.contains(s)) {
                nameFlag = s;
                break;
            }
        }
        if (StringUtils.isNotBlank(nameFlag)) {
            return "文件名字不允许出现 " + nameFlag + " 等特殊字符！";
        }
        // 校验文件后缀
        if (!fileName.contains(".")) {
            return "文件不能没有后缀！";
        }
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        if (!Arrays.asList(FILE_SUFFIX_SUPPORT).contains(suffix.toLowerCase(Locale.ROOT))) {
            return "不允许操作 " + suffix + " 格式文件！";
        }
        return null;
    }
}



