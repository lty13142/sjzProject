package com.crcm.auth.controller.open;

import com.crcm.core.bean.VerifyCode;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.SystemBaseConstants;
import com.crcm.core.utils.ValidateCodeUtil;
import com.crcm.security.utils.VerificationCodUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.UUID;

/**
 * @ClassName VerificationCodeController
 * @Description 验证码获取Controller
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/17
 **/
@Slf4j
@Controller
@RequestMapping("/open/")
@RequiredArgsConstructor
public class VerificationCodeController {
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 验证码获取
     *     {@code key}唯一键为空时为首次获取验证码，刷新时需要携带第一次返回的key
     *     通过redis存储验证码
     * @param resp
     * @param key
     * @throws IOException
     */
    @RequestMapping("/code/image")
    public void getAccessConfirmation(HttpServletResponse resp, @RequestParam(name = "key",required = false)String key)  throws IOException {
        // 获取随机验证码
        String code = VerificationCodUtil.getRandomString();
        // 刷新唯一键
        if(StringUtils.isEmpty(key)){
            // 唯一键
            key = UUID.randomUUID().toString().replace("-", "");
            // 将UUID或者雪花算法等通过响应头传回客户端  // 建议使用UUID或者雪花算法等
            resp.setHeader("key", key);
        }
        // 放入redis中存储
//        HashMap<String, Object> map = new HashMap<String, Object>();
////        map.put("code",code);
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.opsForHash().put(SystemBaseConstants.VERIFICATION_CODE,key,code);
        log.info("verificationCode放入redis中存储 key -> {} , code -> {}",key,code);
        VerifyCode verifyCode = ValidateCodeUtil.createImage(4, null, null);
        log.info("生成图形验证码：{}, 有效期:{}妙", verifyCode.getCode(), AuthConstants.CAPTCHA_CACHE_EXPIRE_TIME);
        // 将图片通过输出流返回给客户端
        OutputStream os = resp.getOutputStream();
        // 设置resp的响应内容类型，前端将是blob
        resp.setContentType("image/jpeg");
        try {
            ImageIO.write(verifyCode.getImage(), "jpeg", os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}
