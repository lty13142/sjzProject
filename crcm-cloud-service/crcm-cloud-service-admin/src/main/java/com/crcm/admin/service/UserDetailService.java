package com.crcm.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.admin.model.entity.SysUserDetail;
import com.crcm.admin.model.vo.SysUserDetailVO;

import java.util.List;

public interface UserDetailService extends IService<SysUserDetail> {

    int saveUserDetail(SysUserDetail sysUserDetail);

    int updateUserDetail(SysUserDetail sysUserDetail);

    int deleteById(String id);

    int realDelete(String id);

    SysUserDetail findById(String id);

    List<SysUserDetail> findList(SysUserDetail sysUserDetail);

    Page<SysUserDetail> findPage(Page page, SysUserDetail sysUserDetail);

    SysUserDetailVO getDetail(String userId);
}
