package com.crcm.bpm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.bpm.domain.dto.request.CountersignCompletedDTO;
import com.crcm.bpm.domain.entity.CounterSignDO;
import com.crcm.bpm.mapper.CounterSignMapper;
import com.crcm.bpm.service.CounterSignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@Transactional
public class CounterSignServiceImpl extends ServiceImpl<CounterSignMapper, CounterSignDO> implements CounterSignService {


    @Override
    public int saveCounterSign(CounterSignDO counterSignDO) {
        return this.baseMapper.insert(counterSignDO);
    }

    @Override
    public int updateCounterSign(CounterSignDO counterSignDO) {
        return this.baseMapper.updateById(counterSignDO);
    }

    @Override
    public int deleteById(String id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    @Override
    public CounterSignDO findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<CounterSignDO> findList(CounterSignDO counterSignDO) {
        QueryWrapper<CounterSignDO> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<CounterSignDO> findPage(Page page, CounterSignDO counterSignDO) {
        QueryWrapper<CounterSignDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(counterSignDO.getTaskId() != null, CounterSignDO::getTaskId, counterSignDO.getTaskId());
        IPage<CounterSignDO> pageCounterSign = this.baseMapper.selectPage(page, queryWrapper);
        return pageCounterSign;
    }

    @Override
    public int doCounterSign(CountersignCompletedDTO dto) {
        QueryWrapper<CounterSignDO> queryWrapper = new QueryWrapper<>();
        CounterSignDO counterSignDO = BeanUtil.copyProperties(dto, CounterSignDO.class);
        queryWrapper.lambda().eq(CounterSignDO::getTaskId, dto.getTaskId())
                .eq(CounterSignDO::getAssignee, dto.getAssignee())
                .eq(CounterSignDO::getProcInstId, dto.getProcInstId());
        return this.baseMapper.update(counterSignDO, queryWrapper);
    }
}
