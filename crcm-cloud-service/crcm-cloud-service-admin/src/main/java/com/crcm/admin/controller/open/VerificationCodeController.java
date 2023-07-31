package com.crcm.admin.controller.open;

import com.crcm.cloud.start.sso.utils.VerificationCodUtil;
import com.crcm.core.constant.SystemBaseConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName VerificationCodeController
 * @Description 验证码获取Controller
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Slf4j
@Api(tags = "验证码控制器")
@Controller
@RequestMapping("/open/captcha")
public class VerificationCodeController {

    /**
     * redis
     */
    private final RedisTemplate<String, Object> redisTemplate;

    public VerificationCodeController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 验证码获取
     * <p>
     * {@code key}唯一键为空时为首次获取验证码，刷新时需要携带第一次返回的key
     * 通过redis存储验证码
     * </p>
     *
     * @param key  唯一键
     * @param resp HttpServletResponse
     */
    @ApiOperation(value = "验证码获取")
    @GetMapping("/getCode")
    public void getAccessConfirmation(HttpServletResponse resp, @RequestParam(name = "key", required = false) String key) throws IOException {
        // 获取随机验证码
        String code = VerificationCodUtil.getRandomString();
        // 刷新唯一键
        if (StringUtils.isEmpty(key)) {
            // 唯一键
            key = UUID.randomUUID().toString().replace("-", "");
        }
        // 将UUID或者雪花算法等通过响应头传回客户端  // 建议使用UUID或者雪花算法等
        resp.setHeader("key", key);
        // 放入redis中存储
//        HashMap<String, Object> map = new HashMap<String, Object>();
//        map.put("code",code);
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set(SystemBaseConstants.VERIFICATION_CODE + ":" + key, code, 60L, TimeUnit.SECONDS);
        // 调过生成验证码图片的方法
        BufferedImage img = VerificationCodUtil.getImage(130, 50, code);
        // 设置resp的响应内容类型，前端将是blob
        resp.setContentType("image/jpeg");
        // 将图片通过输出流返回给客户端
        OutputStream out = resp.getOutputStream();
        ImageIO.write(img, "jpeg", out);
        out.close();
    }
}
