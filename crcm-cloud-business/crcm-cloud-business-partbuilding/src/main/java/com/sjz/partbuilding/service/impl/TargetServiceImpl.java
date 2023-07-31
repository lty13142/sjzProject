package com.sjz.partbuilding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.core.exception.CustomException;
import com.sjz.partbuilding.constants.YTBaseConstant;
import com.sjz.partbuilding.constants.YTDicConstant;
import com.sjz.partbuilding.constants.YTSystemConstant;
import com.sjz.partbuilding.mapper.TargetMapper;
import com.sjz.partbuilding.model.entity.Target;
import com.sjz.partbuilding.service.TargetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
@Transactional
public class TargetServiceImpl extends ServiceImpl<TargetMapper, Target>implements TargetService {

    @Resource
    private TargetMapper targetMapper;

    @Override
    public int saveTarget(Target Target) {
        return this.baseMapper.insert(Target);
    }

    @Override
    public int updateTarget(Target Target) {
        return this.baseMapper.updateById(Target);
    }

    @Override
    public int deleteById(String id) {
        //已使用的指标不能删除
        int count = targetMapper.selectUsedCount(id);
        if(count>0){
            throw new CustomException("指标已在使用中不能删除，如要删除请先删除积分管理数据");
        }
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    @Override
    public Target findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<Target> findList(Target Target) {
        QueryWrapper<Target> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("open_status", YTSystemConstant.YES_OR_NO.YES);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Target> findPage(Page page,Target target) {
        QueryWrapper<Target> queryWrapper=new QueryWrapper<>();
        if (StringUtils.isNotBlank(target.getName())) {
            queryWrapper.like("name", target.getName());
        }
        queryWrapper.orderByDesc("create_time");
        IPage<Target> pageTarget =this.baseMapper.selectPage(page,queryWrapper);
        return pageTarget;
    }
}
