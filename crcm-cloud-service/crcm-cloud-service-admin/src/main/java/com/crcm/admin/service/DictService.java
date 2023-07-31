package com.crcm.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.admin.model.dto.SysDictQueryDTO;
import com.crcm.admin.model.entity.SysDict;
import com.crcm.cloud.start.data.mybatis.bean.PageT;

import java.io.Serializable;
import java.util.List;

public interface DictService extends IService<SysDict> {

    Long saveDict(SysDict t);

    int updateDict(SysDict t);

    int deleteById(Serializable id);

    SysDict findById(Serializable id);

    PageT<SysDict> findPage(PageT<SysDict> pageT, SysDictQueryDTO t);

    List<SysDict> findByDicCode(SysDictQueryDTO t);

    List<SysDict> findList(SysDictQueryDTO t);

    int findMaxSort(String pid);

    List<SysDict> findCacheDicts();
}
