package com.crcm.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.mapper.SysLogMapper;
import com.crcm.admin.model.dto.QueryDTO;
import com.crcm.admin.model.entity.SysLog;
import com.crcm.admin.service.SysLogService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@Transactional
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {


    @Override
    public int saveSysLog(SysLog sysLog) {
        return this.baseMapper.insert(sysLog);
    }

    @Override
    public int updateSysLog(SysLog sysLog) {
        return this.baseMapper.updateById(sysLog);
    }

    @Override
    public int deleteById(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    @Override
    public SysLog findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<SysLog> findList(SysLog sysLog) {
        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public PageT<SysLog> findPage(PageT<SysLog> page, SysLog t, QueryDTO queryDTO) {
        LambdaQueryWrapper<SysLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(t.getOperateName()), SysLog::getOperateName, t.getOperateName())
                .eq(StringUtils.isNotBlank(t.getTitle()), SysLog::getTitle, t.getTitle())
                .eq(null != t.getType(), SysLog::getType, t.getType())
                .between(queryDTO.getBeginTime() != null && queryDTO.getEndTime() != null, SysLog::getCreateTime, queryDTO.getBeginTime(), queryDTO.getEndTime());
        return this.page(page, wrapper);
    }

}
