package com.crcm.bpm.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.bpm.domain.entity.ProcessRoleDo;
import com.crcm.bpm.domain.vo.ProcessRoleVO;
import com.crcm.bpm.mapper.ProcessRoleMapper;
import com.crcm.bpm.service.ProcessRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zwj
 * @date 2020-10-28
 */
@Slf4j
@Service
@Transactional
public class ProcessRoleServiceImpl extends ServiceImpl<ProcessRoleMapper, ProcessRoleDo> implements ProcessRoleService {

    @Override
    public int saveProcessRole(ProcessRoleVO processRoleVO) {
        // 删除之前得角色权限数据
        ProcessRoleDo processRoleDo = new ProcessRoleDo();
        processRoleDo.setRoleId(processRoleVO.getRoleId());
        processRoleDo.setCompanyId(processRoleVO.getCompanyId());
        List<ProcessRoleDo> processRoleDos = this.findList(processRoleDo);
        if (CollUtil.isNotEmpty(processRoleDos)) {
            List<Long> ids = new ArrayList<>();
            processRoleDos.forEach(processRoleDo1 -> ids.add(processRoleDo1.getId()));
            this.baseMapper.deleteBatchIds(ids);
        }
        // 批量保存流程角色与流程关系
        List<Long> processIds = JSONObject.parseArray(processRoleVO.getProcessIdList(), Long.class);
        List<ProcessRoleDo> processRoleDoList = new ArrayList<>();
        processIds.forEach(id -> {
            ProcessRoleDo processRole = new ProcessRoleDo();
            processRole.setRoleId(processRoleVO.getRoleId());
            processRole.setCompanyId(processRoleVO.getCompanyId());
            processRole.setProcessId(id);
            processRoleDoList.add(processRole);
        });
        this.saveBatch(processRoleDoList);
        return 1;
    }

    @Override
    public int updateProcessRole(ProcessRoleDo processRoleDo) {
        return this.baseMapper.updateById(processRoleDo);
    }

    @Override
    public int deleteById(String id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public ProcessRoleDo findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<ProcessRoleDo> findList(ProcessRoleDo processRoleDo) {
        return this.baseMapper.selectList(Wrappers.lambdaQuery(processRoleDo)
                .eq(ProcessRoleDo::getRoleId, processRoleDo.getRoleId())
                .eq(ProcessRoleDo::getCompanyId, processRoleDo.getCompanyId()));
    }

    @Override
    public IPage<ProcessRoleDo> findPage(Page page, ProcessRoleDo processRoleDo) {
        return this.baseMapper.selectPage(page, Wrappers.lambdaQuery(processRoleDo));
    }

    @Override
    public List<Long> findProcessIds(ProcessRoleDo processRoleDo) {
        List<ProcessRoleDo> list = findList(processRoleDo);
        List<Long> processIds = new ArrayList<>();
        list.forEach(processRoleDo1 -> processIds.add(processRoleDo1.getProcessId()));
        return processIds;
    }
}
