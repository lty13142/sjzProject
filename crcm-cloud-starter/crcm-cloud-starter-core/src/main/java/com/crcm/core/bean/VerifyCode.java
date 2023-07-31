package com.crcm.core.bean;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName: ImageCode
 * @Description 验证码类
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/11/11
 **/
@Data
public class VerifyCode implements Serializable {

    private static final long serialVersionUID = 1L;

    private BufferedImage image;
    private String code;
    private LocalDateTime expireTime;

    public VerifyCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}