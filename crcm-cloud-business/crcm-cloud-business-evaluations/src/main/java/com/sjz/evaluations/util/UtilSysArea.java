package com.sjz.evaluations.util;

import com.alibaba.fastjson.JSON;
import com.crcm.cloud.start.redis.service.RedisService;
import com.crcm.core.constant.SystemBaseConstants;
import com.crcm.core.constant.SystemConstant;
import com.sjz.evaluations.model.vo.AreaVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName UtilSysArea
 * @Description 行政区划 工具类
 * @Author GZL
 * @Date 2023/4/6 9:22
 * @Version 1.0
 **/
@Component
public class UtilSysArea {


    private static RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        UtilSysArea.redisService = redisService;
    }

    /**
     * 获取缓存中的字典数据
     */
    public static Map<String, List<AreaVo>> loadCacheData() {
        Map<String, List<AreaVo>> result = new HashMap<>();
        if (redisService.hasKey(SystemBaseConstants.REDIS_AREA_DATA)) {
            Map<Object, Object> redisMap = redisService.hmget(SystemBaseConstants.REDIS_AREA_DATA);
            redisMap.keySet().forEach(key -> result.put(String.valueOf(key), JSON.parseArray(JSON.toJSONString(redisMap.get(key)), AreaVo.class)));
        }
        return result;
    }

    /**
     * 通过类型获取行政区划
     *
     * @param type 类型
     * @return 行政区划
     */
    public static List<AreaVo> getAreaByType(String type) {
        Map<String, List<AreaVo>> dicMap = loadCacheData();
        return dicMap.getOrDefault(type, new ArrayList<>());
    }


    /**
     * 通过类型和id获取名称
     *
     * @param areaList 数据列表
     * @param id       id
     * @return 名称
     */
    public static String getAreaName(List<AreaVo> areaList, String id) {
        if (StringUtils.isBlank(id) || CollectionUtils.isEmpty(areaList)) {
            return "";
        }
        for (AreaVo dict : areaList) {
            if (StringUtils.isNotBlank(dict.getId()) && dict.getId().equals(id)) {
                return dict.getName();
            }
        }
        return "";
    }

    /**
     * 通过类型和id获取名称
     *
     * @param type 类型
     * @param id   id
     * @return 名称
     */
    public static String getAreaName(String type, String id) {
        if (StringUtils.isBlank(type) || StringUtils.isBlank(id)) {
            return "";
        }
        List<AreaVo> areaList = getAreaByType(type);
        if (areaList.size() <= 0) {
            return "";
        }
        for (AreaVo dict : areaList) {
            if (StringUtils.isNotBlank(dict.getId()) && dict.getId().equals(id)) {
                return dict.getName();
            }
        }
        return "";
    }



    public static AreaVo getAreaList(String type, String id) {
        if (StringUtils.isBlank(type) || StringUtils.isBlank(id)) {
            return null;
        }
        List<AreaVo> areaList = getAreaByType(type);
        if (areaList.size() <= 0) {
            return null;
        }
        for (AreaVo dict : areaList) {
            if (StringUtils.isNotBlank(dict.getId()) && dict.getId().equals(id)) {
                return dict;
            }
        }
        return null;
    }

    /**
     * 通过类型和名称获取id
     *
     * @param areaList 数据列表
     * @param areaName 名称
     * @return id
     */
    public static String getAreaId(List<AreaVo> areaList, String areaName) {
        if (StringUtils.isBlank(areaName) || CollectionUtils.isEmpty(areaList)) {
            return "";
        }
        for (AreaVo areaVo : areaList) {
            if (StringUtils.isNotBlank(areaVo.getName()) && areaVo.getName().equals(areaName)) {
                return areaVo.getId();
            }
        }
        return "";
    }

    /**
     * 通过类型和名称获取id
     *
     * @param type     类型
     * @param areaName 名称
     * @return id
     */
    public static String getAreaId(String type, String areaName) {
        if (StringUtils.isBlank(type) || StringUtils.isBlank(areaName)) {
            return "";
        }
        List<AreaVo> areaList = getAreaByType(type);
        if (areaList.size() <= 0) {
            return "";
        }
        for (AreaVo areaVo : areaList) {
            if (StringUtils.isNotBlank(areaVo.getName()) && areaVo.getName().equals(areaName)) {
                return areaVo.getId();
            }
        }
        return "";
    }

    public static String getVillageChByCode(String code){
        List< AreaVo > villageList = UtilSysArea.getAreaByType(SystemConstant.AREA_TYPE.VILLAGE);
        Map<String, AreaVo> areaVoMap = villageList.stream().collect(Collectors.toMap(AreaVo::getCode, Function.identity()));
        AreaVo areaVo = areaVoMap.get(code);
        if (areaVo != null){
            return areaVo.getName();
        }else {
            return code;
        }
    }

    public static AreaVo getVillageByCode(String code){
        List< AreaVo > villageList = UtilSysArea.getAreaByType(SystemConstant.AREA_TYPE.VILLAGE);
        Map<String, AreaVo> areaVoMap = villageList.stream().collect(Collectors.toMap(AreaVo::getCode, Function.identity()));
        AreaVo areaVo = areaVoMap.get(code);
        if (areaVo != null){
            return areaVo;
        }else {
            return null;
        }
    }

    public static String getVillageChById(String villageId){
        List< AreaVo > villageList = UtilSysArea.getAreaByType(SystemConstant.AREA_TYPE.VILLAGE);
        Map<String, AreaVo> areaVoMap = villageList.stream().collect(Collectors.toMap(AreaVo::getId, Function.identity()));
        AreaVo areaVo = areaVoMap.get(villageId);
        if (areaVo != null){
            return areaVo.getName();
        }else {
            return villageId;
        }
    }
}
