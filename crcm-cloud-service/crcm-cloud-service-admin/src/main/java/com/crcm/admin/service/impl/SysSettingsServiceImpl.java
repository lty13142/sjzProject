package com.crcm.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.mapper.SysSettingsMapper;
import com.crcm.admin.model.entity.SysSettings;
import com.crcm.admin.service.SysSettingsService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.redis.service.RedisService;
import com.crcm.core.constant.SystemBaseConstants;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 系统设置Service业务层处理
 *
 * @author zzyt
 * @date 2021-06-25
 */
@Service
public class SysSettingsServiceImpl extends ServiceImpl<SysSettingsMapper, SysSettings> implements SysSettingsService {

    @Resource
    private RedisService redisService;

    /**
     * 新增系统设置
     *
     * @param sysSettings 系统设置
     * @return 结果
     */
    @Override
    public boolean saveSysSettings(SysSettings sysSettings) {
        boolean save = this.save(sysSettings);
        redisService.hset(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY, sysSettings.getCode(), sysSettings.getValue());
        return save;
    }

    /**
     * 修改系统设置
     *
     * @param sysSettings 系统设置
     * @return 结果
     */
    @Override
    public boolean updateSysSettings(SysSettings sysSettings) {
        boolean updateById = this.updateById(sysSettings);
        redisService.hset(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY, sysSettings.getCode(), sysSettings.getValue());
        return updateById;
    }

    /**
     * 删除系统设置信息
     *
     * @param id 系统设置ID
     * @return 结果
     */
    @Override
    public boolean deleteSysSettings(Long id) {
        return this.removeById(id);
    }

    /**
     * 查询系统设置
     *
     * @param id 系统设置ID
     * @return 系统设置
     */
    @Override
    public SysSettings findSysSettingsById(Long id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询系统设置列表
     *
     * @param sysSettings 系统设置
     * @return 系统设置
     */
    @Override
    public List<SysSettings> findSysSettingsList(SysSettings sysSettings) {
        LambdaQueryWrapper<SysSettings> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询系统设置
     *
     * @param page        分页参数
     * @param sysSettings 系统设置
     * @return 系统设置
     */
    @Override
    public PageT<SysSettings> findSysSettingsPage(PageT<SysSettings> page, SysSettings sysSettings) {
        LambdaQueryWrapper<SysSettings> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(sysSettings.getName()), SysSettings::getName, sysSettings.getName());
        return this.page(page, queryWrapper);
    }

    @Override
    @Cacheable(value = {SystemBaseConstants.BASE_PATH + ":SETTINGS"}, key = "#code")
    public SysSettings getValueByCode(String code) {
        LambdaQueryWrapper<SysSettings> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysSettings::getCode, code);
        SysSettings settings = this.getOne(wrapper);
        if (Objects.nonNull(settings)) {
            return settings;
        }
        return null;
    }

    /**
     * 初始化系统设置缓存
     *
     * @Author GZL
     * @Date 2023/2/24 9:38
     **/
    @Override
    public void initSystemSettingRedis() {
        LambdaQueryWrapper<SysSettings> queryWrapper = new LambdaQueryWrapper<>();
        List<SysSettings> sysSettings = this.baseMapper.selectList(queryWrapper);
        redisService.del(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY);
        if (CollectionUtils.isEmpty(sysSettings)) {
            return;
        }
        sysSettings.forEach(setting -> redisService.hset(SystemBaseConstants.SYSTEM_SETTINGS_REDIS_KEY, setting.getCode(), setting.getValue()));
    }
}
