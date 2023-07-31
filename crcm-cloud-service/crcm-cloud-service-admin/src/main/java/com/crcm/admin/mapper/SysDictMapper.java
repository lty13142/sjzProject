package com.crcm.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.admin.model.dto.SysDictQueryDTO;
import com.crcm.admin.model.entity.SysDict;

import java.util.List;


public interface SysDictMapper extends BaseMapper<SysDict> {

    List<SysDict> findByDicCode(SysDictQueryDTO t);

    List<SysDict> findAllDicContent();

    SysDict findContentCodeById(String id);

    SysDict findContentCodeByCodeId(String codeId);

    List<SysDict> selectCacheDicts(SysDict dict);

    void deleteByPid(String id);
}
