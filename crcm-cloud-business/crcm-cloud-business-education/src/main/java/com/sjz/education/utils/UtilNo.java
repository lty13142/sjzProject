package com.sjz.education.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import com.crcm.cloud.start.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class UtilNo {
    @Autowired
    Environment environment;
    @Value("${machine.code:01}")
    String machineCode="01";

    /**
     * 2位平台码+14位时间戳+7+位自增
     *
     * @return
     */
    @Autowired
    RedisService redisService;

    /**
     * 根据日期生成编号工具类
     * @param key redis键值
     * @return
     */
    public String getSequenceNo(String key) {
        DateTime now = DateTime.now();
        Long increment = redisService.incr(key +now.toString(DatePattern.PURE_DATE_PATTERN), Long.valueOf(1));
        String timeStr = now.toString(DatePattern.PURE_DATE_PATTERN);
//        1
//        200220926165532+ 000001
//        000001
        String incrementStr = String.format("%03d", increment);
        String[] split = key.split(":");
        return String.format("%s%s%s",split[split.length-1], timeStr, incrementStr);

    }

}
