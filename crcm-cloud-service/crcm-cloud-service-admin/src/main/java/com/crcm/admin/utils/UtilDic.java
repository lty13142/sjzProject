package com.crcm.admin.utils;

import com.alibaba.fastjson.JSON;
import com.crcm.core.constant.SystemBaseConstants;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.dto.ConstantData;
import com.crcm.core.dto.DictCacheDTO;
import com.crcm.core.utils.SystemConstantUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @ClassName YTDicUtil
 * @Description 系统字典工具
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/5/14 11:10
 **/
@Component
public class UtilDic {

    private static RedisTemplate redisTemplate;

    @Autowired
    public UtilDic(RedisTemplate redisTemplate) {
        UtilDic.redisTemplate = redisTemplate;
    }

    /**
     * 通过代码和值获取系统常量中文名
     *
     * @param innerClazzName 内部类名称
     * @param value   值
     * @return 中文名
     */
    public static String getChNameByCode(String innerClazzName, String value) {
        return getChNameByCode(innerClazzName, value, SystemConstant.class);
    }

    /**
     * 通过代码和值获取系统常量中文名
     * @param innerClazzName 内部类名称
     * @param value 值
     * @param constantClass 常量类
     * @return 中文名
     */
    public static String getChNameByCode(String innerClazzName, String value, Class<?> constantClass) {
        List<ConstantData> innerClazzData = SystemConstantUtil.getInnerClazzData(innerClazzName, constantClass);
        if (innerClazzData != null && innerClazzData.size() > 0) {
            for (ConstantData dicContent : innerClazzData) {
                if (dicContent.getValue().equals(value)){
                    return dicContent.getName();
                }
            }
        }
        return null;
    }

    /**
     * 通过字典代码获取字典数据
     * @param dicCode 字典代码
     * @return 字典数据
     */
    public static List<Object> getDicByCode(String dicCode) {
        Map<String, List<Object>> dictMap = loadCacheData();
        return dictMap.get(dicCode);
    }

    /**
     * 获取缓存中的字典数据
     * @return 字典数据
     */
    public static Map<String,List<Object>> loadCacheData() {
        Map<String,List<Object>> dictMap = null;
        dictMap = redisTemplate.opsForHash().entries(SystemBaseConstants.REDIS_DICT_DATA);
        return dictMap;
    }


    /**
     * 通过字典代码和字典值获取字典数据的名称
     * @param dicCode 字典代码
     * @param value 字典值
     * @return 字典数据的名称
     */
    public static String getDictName(String dicCode, String value) {
        final String[] chName = new String[1];
        Map<String,List<Object>> dataMaps = loadCacheData();
        List<Object> dictCaches = dataMaps.get(dicCode);
        if (dictCaches != null && dictCaches.size() > 0) {
            dictCaches.stream().forEach(dictCache -> {
                DictCacheDTO dict = JSON.parseObject(JSON.toJSONString(dictCache), DictCacheDTO.class);
                if (StringUtils.isNotBlank(dict.getValue()) && dict.getValue().equals(value)) {
                    chName[0] = dict.getChName();
                    return;
                }
            });
        }
        return chName[0];
    }
}
