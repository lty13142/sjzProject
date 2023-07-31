package com.sjz.governance.utils;

import com.alibaba.fastjson.JSON;
import com.crcm.cloud.start.redis.service.RedisService;
import com.crcm.core.constant.SystemBaseConstants;
import com.crcm.core.dto.DictCacheDTO;
import com.crcm.core.utils.UtilDataFormat;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UtilDic
 * @Description 字典工具类
 * @Author GZL
 * @Date 2023/4/3 17:11
 * @Version 1.0
 **/
@Component
public class UtilDic {

    private static RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        UtilDic.redisService = redisService;
    }

    /**
     * 获取缓存中的字典数据
     */
    public static Map<String, List<DictCacheDTO>> loadCacheData() {
        Map<String, List<DictCacheDTO>> result = new HashMap<>();
        if (redisService.hasKey(SystemBaseConstants.REDIS_DICT_DATA)) {
            Map<Object, Object> redisMap = redisService.hmget(SystemBaseConstants.REDIS_DICT_DATA);
            redisMap.keySet().forEach(key -> result.put(String.valueOf(key), JSON.parseArray(JSON.toJSONString(redisMap.get(key)), DictCacheDTO.class)));
        }
        return result;
    }

    /**
     * 通过字典代码获取字典数据
     *
     * @param dicCode 字典代码
     * @return 字典数据
     */
    public static List<DictCacheDTO> getDicByCode(String dicCode) {
        Map<String, List<DictCacheDTO>> dicMap = loadCacheData();
        List<DictCacheDTO> list = dicMap.getOrDefault(dicCode, new ArrayList<>());
        return UtilDataFormat.getListSort(list, DictCacheDTO::getValue);
    }


    /**
     * 通过字典代码和字典值获取字典数据的名称
     *
     * @param dicCode 字典代码
     * @param value   字典值
     * @return 字典数据的名称
     */
    public static String getDictName(String dicCode, String value) {
        if (StringUtils.isBlank(dicCode) || StringUtils.isBlank(value)) {
            return "";
        }
        List<DictCacheDTO> dictCaches = getDicByCode(dicCode);
        if (dictCaches.size() <= 0) {
            return "";
        }
        for (DictCacheDTO dict : dictCaches) {
            if (StringUtils.isNotBlank(dict.getValue()) && dict.getValue().equals(value)) {
                return dict.getChName();
            }
        }
        return "";
    }

    /**
     * 通过字典代码和字典值获取字典数据的名称
     *
     * @param dicCode 字典代码
     * @param value   字典值
     * @return 字典数据的名称
     */
    public static String getDictComment(String dicCode, String value) {
        if (StringUtils.isBlank(dicCode) || StringUtils.isBlank(value)) {
            return "";
        }
        List<DictCacheDTO> dictCaches = getDicByCode(dicCode);
        if (dictCaches.size() <= 0) {
            return "";
        }
        for (DictCacheDTO dict : dictCaches) {
            if (StringUtils.isNotBlank(dict.getValue()) && dict.getValue().equals(value)) {
                return dict.getComments();
            }
        }
        return "";
    }

    /**
     * 通过字典代码和字典值获取字典数据的名称
     *
     * @param dictCaches 字典列表
     * @param value   字典值
     * @return 字典数据的名称
     */
    public static String getDictComment(List<DictCacheDTO> dictCaches, String value) {
        if (CollectionUtils.isEmpty(dictCaches) || StringUtils.isBlank(value)) {
            return "";
        }
        for (DictCacheDTO dict : dictCaches) {
            if (StringUtils.isNotBlank(dict.getValue()) && dict.getValue().equals(value)) {
                return dict.getComments();
            }
        }
        return "";
    }
}

