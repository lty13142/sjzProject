package com.crcm.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.admin.model.dto.QueryDTO;
import com.crcm.admin.model.entity.SysLog;
import com.crcm.cloud.start.data.mybatis.bean.PageT;

import java.util.List;

public interface SysLogService extends IService<SysLog> {

    int saveSysLog(SysLog sysLog);

    int updateSysLog(SysLog sysLog);

    int deleteById(Long id);

    int realDelete(String id);

    SysLog findById(String id);

    List<SysLog> findList(SysLog sysLog);

    PageT<SysLog> findPage(PageT<SysLog> page, SysLog sysLog, QueryDTO queryDTO);

}
