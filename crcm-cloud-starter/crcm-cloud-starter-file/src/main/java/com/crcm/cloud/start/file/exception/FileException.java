package com.crcm.cloud.start.file.exception;

import cn.hutool.http.HttpStatus;
import com.crcm.core.exception.BaseException;

/**
 * @ClassName FileException
 * @Description 文件信息异常类
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/23
 **/

public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String message, Object[] args) {
        super("file", HttpStatus.HTTP_INTERNAL_ERROR, args, message);
    }

    public FileException(Integer code, Object[] args) {
        super("file", code, args, null);
    }

    public FileException(Object[] args) {
        super("file", HttpStatus.HTTP_INTERNAL_ERROR, args, null);
    }
}

