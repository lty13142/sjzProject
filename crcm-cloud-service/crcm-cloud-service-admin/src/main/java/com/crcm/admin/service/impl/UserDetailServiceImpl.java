package com.crcm.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.model.entity.SysUserDetail;
import com.crcm.admin.mapper.UserDetailMapper;
import com.crcm.admin.model.vo.SysUserDetailVO;
import com.crcm.admin.service.UserDetailService;
import com.crcm.admin.model.vo.UserDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@Transactional
public class UserDetailServiceImpl extends ServiceImpl<UserDetailMapper, SysUserDetail> implements UserDetailService {

//    @Autowired
//    private AttachmentService attachmentService;

    @Override
    public int saveUserDetail(SysUserDetail sysUserDetail) {
        return this.baseMapper.insert(sysUserDetail);
    }

    @Override
    public int updateUserDetail(SysUserDetail sysUserDetail) {
        return this.baseMapper.updateById(sysUserDetail);
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
    public SysUserDetail findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<SysUserDetail> findList(SysUserDetail sysUserDetail) {
        QueryWrapper<SysUserDetail> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public Page<SysUserDetail> findPage(Page page, SysUserDetail sysUserDetail) {
        QueryWrapper<SysUserDetail> queryWrapper = new QueryWrapper<>();
        Page<SysUserDetail> pageUserDetail = this.page(page, queryWrapper);
        return pageUserDetail;
    }

    @Override
    public SysUserDetailVO getDetail(String userId) {
        QueryWrapper<SysUserDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        SysUserDetail sysUserDetail = this.baseMapper.selectOne(queryWrapper);
        SysUserDetailVO userDetailVo = new SysUserDetailVO();
        BeanUtil.copyProperties(sysUserDetail, userDetailVo);
        if (StringUtils.isNotBlank(userDetailVo.getFacePic())) {
//            String filePath = attachmentService.findFilePath(userDetailVo.getFacePic());
//            userDetailVo.setFacePicPath(filePath);
        }
        return userDetailVo;
    }
}
