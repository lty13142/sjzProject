package com.zsgf.platform.service.impl.waste;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.redis.service.RedisService;
import com.crcm.core.constant.SystemBaseConstants;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.utils.UtilDataFormat;
import com.zsgf.platform.dto.waste.WasteBaseInfoDTO;
import com.zsgf.platform.mapper.waste.WasteBaseInfoMapper;
import com.zsgf.platform.model.entity.waste.WasteBaseInfo;
import com.zsgf.platform.service.waste.WasteBaseInfoService;
import com.zsgf.platform.vo.WasteBaseInfoTreeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 危废名录Service业务层处理
 *
 * @author gzl
 * @date 2023-02-22
 */
@Service
public class WasteBaseInfoServiceImpl extends ServiceImpl<WasteBaseInfoMapper, WasteBaseInfo> implements WasteBaseInfoService {


    @Resource
    private RedisService redisService;

    private String WASTE_LIST_VERSION = "2021";

    /**
     * 新增危废名录
     *
     * @param wasteBaseInfo 危废名录
     * @return 结果
     */
    @Override
    public boolean saveWasteBaseInfo(WasteBaseInfo wasteBaseInfo) {
        // 危废版本为空，设置版本值
        if (StringUtils.isBlank(wasteBaseInfo.getDirectoryVersion()) &&
                Objects.equals(SystemConstant.WASTE_CATEGORY.WASTE, wasteBaseInfo.getWasteCategory()) &&
                redisService.hasKey(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY)
                && redisService.hHasKey(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY, SystemBaseConstants.WASTE_LIST_NEW_VERSION)) {
            Object lockTime = redisService.hget(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY, SystemBaseConstants.WASTE_LIST_NEW_VERSION);
            WASTE_LIST_VERSION = Objects.isNull(lockTime) ? WASTE_LIST_VERSION : lockTime.toString();
            wasteBaseInfo.setDirectoryVersion(WASTE_LIST_VERSION);
        }
        return this.save(wasteBaseInfo);
    }

    /**
     * 修改危废名录
     *
     * @param wasteBaseInfo 危废名录
     * @return 结果
     */
    @Override
    public boolean updateWasteBaseInfo(WasteBaseInfo wasteBaseInfo) {
        return this.updateById(wasteBaseInfo);
    }

    /**
     * 删除危废名录信息
     *
     * @param id 危废名录ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteBaseInfo(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危废名录
     *
     * @param id 危废名录ID
     * @return 危废名录
     */
    @Override
    public WasteBaseInfo findWasteBaseInfoById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危废名录列表
     *
     * @param wasteBaseInfo 危废名录
     * @return 危废名录
     */
    @Override
    public List<WasteBaseInfo> findWasteBaseInfoList(WasteBaseInfoDTO wasteBaseInfo) {
        getVersion(wasteBaseInfo);
        LambdaQueryWrapper<WasteBaseInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(wasteBaseInfo.getDirectoryVersion()), WasteBaseInfo::getDirectoryVersion, wasteBaseInfo.getDirectoryVersion());
        queryWrapper.eq(Objects.nonNull(wasteBaseInfo.getWasteCategory()), WasteBaseInfo::getWasteCategory, wasteBaseInfo.getWasteCategory());
        queryWrapper.eq(Objects.nonNull(wasteBaseInfo.getLevel()), WasteBaseInfo::getLevel, wasteBaseInfo.getLevel());
        queryWrapper.eq(StringUtils.isNotBlank(wasteBaseInfo.getWasteType()), WasteBaseInfo::getWasteType, wasteBaseInfo.getWasteType());
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危废名录
     *
     * @param page          分页参数
     * @param wasteBaseInfo 危废名录
     * @return 危废名录
     */
    @Override
    public PageT<WasteBaseInfo> findWasteBaseInfoPage(PageT<WasteBaseInfo> page, WasteBaseInfoDTO wasteBaseInfo) {
        getVersion(wasteBaseInfo);
        LambdaQueryWrapper<WasteBaseInfo> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }

    @Override
    public List<WasteBaseInfoTreeVo> findWasteTree(WasteBaseInfoDTO wasteBaseInfo) {
        getVersion(wasteBaseInfo);
        List<WasteBaseInfo> list = findWasteBaseInfoList(wasteBaseInfo);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        List<WasteBaseInfoTreeVo> wasteList = JSON.parseArray(JSON.toJSONString(list), WasteBaseInfoTreeVo.class);
        List<WasteBaseInfoTreeVo> wasteTypeList = UtilDataFormat.listFilter(wasteList, data ->
                Objects.equals(SystemConstant.WASTE_BASE_LEVEL.PARENT, data.getLevel()));
        wasteTypeList.forEach(type -> type.setChildren(UtilDataFormat.listFilter(wasteList,
                data -> Objects.equals(type.getWasteCode(), data.getWasteType()))));
        return wasteTypeList;
    }

    private void getVersion(WasteBaseInfoDTO wasteBaseInfo) {
        if (Objects.equals(SystemConstant.WASTE_CATEGORY.WASTE, wasteBaseInfo.getWasteCategory()) && redisService.hasKey(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY)
                && redisService.hHasKey(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY, SystemBaseConstants.WASTE_LIST_NEW_VERSION)) {
            Object lockTime = redisService.hget(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY, SystemBaseConstants.WASTE_LIST_NEW_VERSION);
            WASTE_LIST_VERSION = Objects.isNull(lockTime) ? WASTE_LIST_VERSION : lockTime.toString();
        }
        wasteBaseInfo.setDirectoryVersion(WASTE_LIST_VERSION);
    }
}
