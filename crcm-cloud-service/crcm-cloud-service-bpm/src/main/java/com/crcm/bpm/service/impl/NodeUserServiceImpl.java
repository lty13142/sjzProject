package com.crcm.bpm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crcm.bpm.domain.dto.request.NodeUserSaveOrUpdateDTO;
import com.crcm.bpm.domain.entity.NodeUserDO;
import com.crcm.bpm.mapper.NodeUserMapper;
import com.crcm.bpm.service.NodeUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@Transactional
public class NodeUserServiceImpl extends ServiceImpl<NodeUserMapper, NodeUserDO>implements NodeUserService {


    @Override
    public int saveNodeUser(NodeUserDO nodeUserDO) {
        return this.baseMapper.insert(nodeUserDO);
    }

    @Override
    public int updateNodeUser(NodeUserDO nodeUserDO) {
        return this.baseMapper.updateById(nodeUserDO);
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
    public NodeUserDO findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<NodeUserDO> findList(NodeUserDO nodeUserDO) {
        QueryWrapper<NodeUserDO> queryWrapper=new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<NodeUserDO> findPage(Page page, NodeUserDO nodeUserDO) {
        QueryWrapper<NodeUserDO> queryWrapper=new QueryWrapper<>();
        IPage<NodeUserDO> pageNodeUser =this.baseMapper.selectPage(page,queryWrapper);
        return pageNodeUser;
    }

    @Override
    public boolean batchSave(List<NodeUserSaveOrUpdateDTO> nodeUserSaveList) {
        ArrayList<NodeUserDO> list = new ArrayList<>();
        for (NodeUserSaveOrUpdateDTO nodeUserSaveOrUpdateDTO : nodeUserSaveList) {
            list.add(BeanUtil.copyProperties(nodeUserSaveOrUpdateDTO,NodeUserDO.class));
        }
        return this.saveBatch(list);
    }

    @Override
    public boolean saveNodeUser(NodeUserSaveOrUpdateDTO nodeUserSaveOrUpdateDTO) {
        return this.save(BeanUtil.copyProperties(nodeUserSaveOrUpdateDTO,NodeUserDO.class));
    }
}
