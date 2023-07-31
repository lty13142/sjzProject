package com.zsgf.platform.utils.provincial;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import com.crcm.cloud.start.redis.service.RedisService;
import com.crcm.core.constant.SystemBaseConstants;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.exception.CustomException;
import com.crcm.core.utils.DateUtil;
import com.zsgf.platform.dto.ProvincialDTO;
import com.zsgf.platform.dto.ProvincialRequestDTO;
import com.zsgf.platform.enums.ProvincialPlatformInterfaceEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @ClassName ProvincialPlatformUtil
 * @Description 省平台数据回流工具类
 * @Author GZL
 * @Date 2023/2/10 9:50
 * @Version 1.0
 **/
@Component
public class ProvincialPlatformUtil {
    /**
     * 当前页字段
     */
    private static final String PAGE_NUM_STR = "pageNum";
    /**
     * 每页信息数字段
     */
    private static final String PAGE_SIZE_STR = "pageSize";
    /**
     * token字段
     */
    private static final String TOKEN_STR = "token";
    /**
     * 请求间隔时间
     */
    private static final long SLEEP_TIME = 10000;
    /**
     * 省平台token
     */
    private static String TOKEN;
    /**
     * 省平台服务地址
     */
    private static String SERVER_PATH;

    private static RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        ProvincialPlatformUtil.redisService = redisService;
    }

    /**
     * 数据回流
     *
     * @Author GZL
     * @Date 2023/3/21 17:14
     * @Param pageNumber 当前页
     * @Param requestDTO 回流信息（请求参数和响应接收封装数据）
     * @Param saveConsumer 数据存储函数
     **/
    public static <T> void dataReturn(int pageNumber, ProvincialRequestDTO<T> requestDTO, Consumer<List<T>> consumer) {
        // 初始化省平台回流接口地址及token
        initServerPathAndToken();
        Map<String, Object> param = getMap(requestDTO.getInterfaceEnum(), requestDTO.getQueryDTO());
        param.put(PAGE_NUM_STR, pageNumber);
        String result = HttpUtil.get(SERVER_PATH.concat(requestDTO.getInterfaceEnum().getInterfacePath()), param);
        if (JSONValidator.from(result).validate()) {
            RequestResult requestResult = JSON.parseObject(result, RequestResult.class);
            if (!requestResult.isSuccess()) {
                if (CollectionUtil.isEmpty(requestDTO.getResultList())) {
                    return;
                }
                consumer.accept(requestDTO.getResultList());
                return;
            }
            requestDTO.getResultList().addAll(requestResult.getObj().toJavaList(requestDTO.getClazz()));
            if (pageNumber < requestResult.getPages()) {
                ThreadUtil.sleep(SLEEP_TIME);
                dataReturn(pageNumber + 1, requestDTO, consumer);
            } else {
                if (CollectionUtil.isEmpty(requestDTO.getResultList())) {
                    return;
                }
                consumer.accept(requestDTO.getResultList());
            }
        }
    }

    /**
     * 数据回流（含数据转换）
     *
     * @Author GZL
     * @Date 2023/3/21 17:14
     * @Param pageNumber 当前页
     * @Param requestDTO 回流信息（请求参数和响应接收封装数据）
     * @Param dataFormatFunc 数据转换函数
     * @Param saveConsumer 数据存储函数
     **/
    public static <T> void dataReturnFormat(int pageNumber, ProvincialRequestDTO<T> requestDTO, Function<JSONObject, T> dataFormatFunc,
                                            Consumer<List<T>> saveConsumer) {
        // 初始化省平台回流接口地址及token
        initServerPathAndToken();
        Map<String, Object> param = getMap(requestDTO.getInterfaceEnum(), requestDTO.getQueryDTO());
        param.put(PAGE_NUM_STR, pageNumber);
        String result = HttpUtil.get(SERVER_PATH.concat(requestDTO.getInterfaceEnum().getInterfacePath()), param);
        if (JSONValidator.from(result).validate()) {
            RequestResult requestResult = JSON.parseObject(result, RequestResult.class);
            if (!requestResult.isSuccess()) {
                if (CollectionUtil.isEmpty(requestDTO.getResultList())) {
                    return;
                }
                saveConsumer.accept(requestDTO.getResultList());
                return;
            }
            // 数据转换
            for (int i = 0; i < requestResult.getObj().size(); i++) {
                requestDTO.getResultList().add(dataFormatFunc.apply(requestResult.getObj().getJSONObject(i)));
            }
            if (pageNumber < requestResult.getPages()) {
                ThreadUtil.sleep(SLEEP_TIME);
                dataReturnFormat(pageNumber + 1, requestDTO, dataFormatFunc, saveConsumer);
            } else {
                if (CollectionUtil.isEmpty(requestDTO.getResultList())) {
                    return;
                }
                saveConsumer.accept(requestDTO.getResultList());
            }
        }
    }

    /**
     * 请求参数封装
     *
     * @return 请求参数
     * @Author GZL
     * @Date 2023/3/21 17:12
     * @Param interfaceEnum 请求接口
     * @Param dto 条件
     **/
    private static Map<String, Object> getMap(ProvincialPlatformInterfaceEnum interfaceEnum, ProvincialDTO dto) {
        Map<String, Object> param = new HashMap<>();
        param.put(TOKEN_STR, TOKEN);
        param.put(PAGE_SIZE_STR, interfaceEnum.getMaxPageSize());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 开始时间不存在或格式有误时，设置默认值
        if (!DateUtil.patternMatchesDateTime(dto.getBeginTime())) {
            LocalDate startDateTime = Objects.equals(SystemConstant.DATA_RETURN_CYCLE_TYPE.WEEK, interfaceEnum.getQueryCycle()) ?
                    LocalDate.now().minusDays(7) : Objects.equals(SystemConstant.DATA_RETURN_CYCLE_TYPE.MONTH, interfaceEnum.getQueryCycle()) ?
                    LocalDate.now().minusMonths(1) : Objects.equals(SystemConstant.DATA_RETURN_CYCLE_TYPE.YEAR, interfaceEnum.getQueryCycle()) ?
                    LocalDate.now().minusYears(1) : LocalDate.now().minusDays(1);
            param.put("MODIFYRQ_KS", LocalDateTime.of(startDateTime, LocalTime.MIN).format(dateTimeFormatter));
        }
        // 结束时间不存在或格式有误时，设置默认值
        if (!DateUtil.patternMatchesDateTime(dto.getEndTime())) {
            param.put("MODIFYRQ_JS", LocalDateTime.now().format(dateTimeFormatter));
        }
        return param;
    }

    /**
     * 初始化省平台回流接口地址及token
     *
     * @Author GZL
     * @Date 2023/3/21 17:59
     **/
    private static void initServerPathAndToken() {
        if (StringUtils.isNotBlank(TOKEN)) {
            return;
        }
        if (!redisService.hasKey(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY) ||
                !redisService.hHasKey(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY, SystemBaseConstants.PROVINCIAL_PLATFORM_RETURN_TOKEN) ||
                !redisService.hHasKey(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY, SystemBaseConstants.PROVINCIAL_PLATFORM_RETURN_API)) {
            throw new CustomException("省平台数据回流参数缓存不存在");
        }
        TOKEN = redisService.hget(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY, SystemBaseConstants.PROVINCIAL_PLATFORM_RETURN_TOKEN).toString();
        SERVER_PATH = redisService.hget(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY, SystemBaseConstants.PROVINCIAL_PLATFORM_RETURN_API).toString();
    }

}
