package com.crcm.admin.runner;

import com.crcm.admin.config.event.SystemDictRefreshEvent;
import com.crcm.admin.model.entity.SysDict;
import com.crcm.admin.service.DictService;
import com.crcm.cloud.start.redis.service.RedisService;
import com.crcm.core.constant.SystemBaseConstants;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.dto.ConstantData;
import com.crcm.core.dto.DictCacheDTO;
import com.crcm.core.utils.SystemConstantUtil;
import com.crcm.core.utils.UtilDataFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SystemDictInitRunner
 * @Description 系统字典初始化
 * @Copyright Copyright(c) 2020
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2020/06/2020/6/30
 **/
@Slf4j
@Configuration
@RequiredArgsConstructor
public class SystemDictInitRunner {
    private final RedisService redisService;
    private final DictService dictService;

    @Async
    @Order
    @EventListener({WebServerInitializedEvent.class, SystemDictRefreshEvent.class})
    public void initDict() {
        long start = System.currentTimeMillis();
        log.info("初始化系统字典缓存");
        Map<String, Object> resultMap = new HashMap<>();
        List<SysDict> allDict = dictService.findCacheDicts();
        // 字典目录
        Map<Long, String> parentMap = UtilDataFormat.listToMap(allDict, dict -> SystemConstant.DICT_TYPE.LIST == dict.getType(),
                SysDict::getId, SysDict::getCode);
        // 字典内容
        Map<Long, List<SysDict>> contentMap = UtilDataFormat.listGroup(allDict,
                dict -> SystemConstant.DICT_TYPE.CONTENT == dict.getType(), SysDict::getPid);
        // 将字典内容加载到map中
        if (!parentMap.isEmpty() && !contentMap.isEmpty()) {
            contentMap.keySet().forEach(key -> {
                if (!parentMap.containsKey(key)) {
                    return;
                }
                List<SysDict> sysDictList = contentMap.get(key);
                List<DictCacheDTO> contentList = new ArrayList<>();
                sysDictList.forEach(dict -> contentList.add(new DictCacheDTO(dict.getName(), dict.getName(), dict.getValue(),
                        dict.getComments(), dict.getCode())));
                resultMap.put(parentMap.get(key), contentList);
            });
        }
        // 将系统字典数据加入到缓存中
        Map<String, List<ConstantData>> constantMap = SystemConstantUtil.getAllConstantData();
        for (String key : constantMap.keySet()) {
            // 数据存在则跳过，以字典表数据为准
            if (resultMap.containsKey(key)) {
                continue;
            }
            ArrayList<DictCacheDTO> cacheDataList = new ArrayList<>();
            constantMap.get(key).forEach(constantData -> cacheDataList.add(
                    new DictCacheDTO(constantData.getName(), constantData.getChName(), String.valueOf(constantData.getValue()),
                            constantData.getChName(), constantData.getCode())));
            resultMap.put(key, cacheDataList);
        }
        redisService.del(SystemBaseConstants.REDIS_DICT_DATA);
        redisService.hmset(SystemBaseConstants.REDIS_DICT_DATA, resultMap);
        log.info("字典缓存加载完成,用时{}毫秒", (System.currentTimeMillis() - start));
    }

}
