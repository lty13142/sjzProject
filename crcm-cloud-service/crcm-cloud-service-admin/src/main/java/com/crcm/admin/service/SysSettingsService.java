package com.crcm.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.admin.model.entity.SysSettings;
import com.crcm.cloud.start.data.mybatis.bean.PageT;

import java.util.List;

/**
 * 系统设置Service接口
 *
 * @author zzyt
 * @date 2021-06-25
 */
public interface SysSettingsService extends IService<SysSettings> {

    /**
     * 新增系统设置
     *
     * @param sysSettings 系统设置
     * @return 结果
     */
    boolean saveSysSettings(SysSettings sysSettings);

    /**
     * 修改系统设置
     *
     * @param sysSettings 系统设置
     * @return 结果
     */
    boolean updateSysSettings(SysSettings sysSettings);

    /**
     * 删除系统设置信息
     *
     * @param id 系统设置ID
     * @return 结果
     */
    boolean deleteSysSettings(Long id);

    /**
     * 查询系统设置
     *
     * @param id 系统设置ID
     * @return 系统设置
     */
    SysSettings findSysSettingsById(Long id);

    /**
     * 查询系统设置列表
     *
     * @param sysSettings 系统设置
     * @return 系统设置集合
     */
    List<SysSettings> findSysSettingsList(SysSettings sysSettings);

    /**
     * 分页查询系统设置列表
     *
     * @param page        分页参数
     * @param sysSettings 系统设置
     * @return 系统设置集合
     */
    PageT<SysSettings> findSysSettingsPage(PageT<SysSettings> page, SysSettings sysSettings);

    /**
     * 通过code获取设置值
     *
     * @param code 代码
     * @return 值
     */
    SysSettings getValueByCode(String code);

    /**
     * 初始化系统设置缓存
     *
     * @Author GZL
     * @Date 2023/2/24 9:38
     **/
    void initSystemSettingRedis();
}
