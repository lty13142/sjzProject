package com.crcm.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.mapper.OrgMapper;
import com.crcm.admin.model.entity.SysOrg;
import com.crcm.admin.service.OrgService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.dto.TreeView;
import com.crcm.core.utils.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Slf4j
@Service
@Transactional
public class OrgServiceImpl extends ServiceImpl<OrgMapper, SysOrg> implements OrgService {

    @Override
    public int saveOrg(SysOrg t) {
        return this.baseMapper.insert(t);
    }

    @Override
    public int updateOrg(SysOrg t) {
        return this.baseMapper.updateById(t);
    }

    @Override
    public int deleteById(Serializable id) {
        // 查询子级
        LambdaQueryWrapper<SysOrg> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Objects.nonNull(id),SysOrg::getPid,id);
        List<SysOrg> orgs = this.baseMapper.selectList(wrapper);
        List<Long> idList = new ArrayList<>();
        if (orgs.size() > 0){
            for (SysOrg org : orgs) {
                idList.add(org.getId());
            }
            // 删除子级
            this.baseMapper.deleteBatchIds(idList);
        }
        return this.baseMapper.deleteById(id);
    }

    @Override
    public SysOrg findById(Serializable id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public PageT<SysOrg> findPage(PageT<SysOrg> page, SysOrg t) {
        LambdaQueryWrapper<SysOrg> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(t.getName()), SysOrg::getName, t.getName()).like(StringUtils.isNotBlank(t.getCode()), SysOrg::getCode, t.getCode())
                .eq(null != t.getId(), SysOrg::getId, t.getId()).or().eq(null != t.getId(), SysOrg::getPid, t.getId());
        return this.page(page, wrapper);
    }

    @Override
    public List<TreeView> findTree(SysOrg t) {
        List<TreeView> listView = this.baseMapper.findTree(t);
        return TreeUtil.listToTree(listView);
    }

    @Override
    public List<SysOrg> findList(SysOrg t) {
        QueryWrapper<SysOrg> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(t.getCode())) {
            queryWrapper.eq("code", t.getCode());
        }
        if (StringUtils.isNotBlank(t.getName())) {
            queryWrapper.like("name", t.getName());
        }
        if (null != t.getPid()) {
            queryWrapper.eq("pid", t.getPid());
        }
        if (StringUtils.isNotBlank(t.getType())) {
            queryWrapper.eq("type", t.getType());
        }
        if (StringUtils.isNotBlank(t.getNumber())) {
            queryWrapper.eq("number", t.getNumber());
        }
        queryWrapper.orderByAsc("number");
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<TreeView> findTreeOrg(SysOrg t) {
        List<TreeView> listView = this.baseMapper.findTree(t);
        return TreeUtil.listToTree(listView);
    }

    /**
     * 查询负责部门
     *
     * @return 负责部门集合
     */
    @Override
    public List<SysOrg> findResponsibilityOrg() {
        LambdaQueryWrapper<SysOrg> queryWrapper = Wrappers.lambdaQuery(SysOrg.class)
                .eq(SysOrg::getType, 1)
                .isNotNull(SysOrg::getPid)
                .select(SysOrg::getName, SysOrg::getId)
                .orderByDesc(SysOrg::getCreateTime);
        return this.baseMapper.selectList(queryWrapper);
    }
}
