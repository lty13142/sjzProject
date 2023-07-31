package com.crcm.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.config.event.SystemDictRefreshEvent;
import com.crcm.admin.mapper.SysDictMapper;
import com.crcm.admin.model.dto.SysDictQueryDTO;
import com.crcm.admin.model.entity.SysDict;
import com.crcm.admin.service.DictService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.constant.SystemConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class DictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements DictService {
    private final ApplicationEventPublisher applicationEventPublisher;


    @Override
    public Long saveDict(SysDict t) {
        t.setEnabled(SystemConstant.ENABLE_STATUS.ENABLE);
        this.baseMapper.insert(t);
        // 发布字典缓存更新事件
        this.applicationEventPublisher.publishEvent(new SystemDictRefreshEvent(this));
        return t.getId();
    }

    @Override
    public int updateDict(SysDict t) {
        int count = this.baseMapper.updateById(t);
        // 发布字典缓存更新事件
        this.applicationEventPublisher.publishEvent(new SystemDictRefreshEvent(this));
        return count;
    }

    @Override
    public int deleteById(Serializable id) {
        this.baseMapper.deleteByPid(id.toString());
        int count = this.baseMapper.deleteById(id);
        // 发布字典缓存更新事件
        this.applicationEventPublisher.publishEvent(new SystemDictRefreshEvent(this));
        return count;
    }

    @Override
    public SysDict findById(Serializable id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public PageT<SysDict> findPage(PageT<SysDict> page, SysDictQueryDTO t) {
        LambdaQueryWrapper<SysDict> lambda = Wrappers.<SysDict>query().lambda();
        if (null != t.getPid()) {
            lambda.eq(SysDict::getPid, t.getPid());
        }
        if (StringUtils.isNotBlank(t.getName())) {
            lambda.like(SysDict::getName, t.getName());
        }
        if (t.getType() != null) {
            lambda.eq(SysDict::getType, t.getType());
        }
        return this.page(page, lambda);
    }

    @Override
    public List<SysDict> findByDicCode(SysDictQueryDTO t) {
        return this.baseMapper.findByDicCode(t);
    }

    @Override
    public List<SysDict> findList(SysDictQueryDTO t) {
        LambdaQueryWrapper<SysDict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(t.getType() != null, SysDict::getType, t.getType());
        wrapper.orderByAsc(SysDict::getSortOrder);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public int findMaxSort(String pid) {
        LambdaQueryWrapper<SysDict> lambda = Wrappers.<SysDict>query().lambda();
        if (StringUtils.isNotBlank(pid)) {
            lambda.eq(SysDict::getPid, pid);
        } else {
            lambda.isNull(SysDict::getPid);
        }
        lambda.orderByDesc(SysDict::getSortOrder);
        lambda.last("limit 1");
        SysDict dict = this.baseMapper.selectOne(lambda);
        if (dict != null) {
            return dict.getSortOrder();
        }
        return 0;
    }

    @Override
    public List<SysDict> findCacheDicts() {
        SysDict dict = new SysDict();
        dict.setEnabled(SystemConstant.ENABLE_STATUS.ENABLE);
        return this.baseMapper.selectCacheDicts(dict);
    }
}
