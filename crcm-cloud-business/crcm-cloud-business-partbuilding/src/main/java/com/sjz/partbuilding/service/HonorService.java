package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.partbuilding.model.entity.Honor;
import com.sjz.partbuilding.model.vo.HonorVo;

import java.util.List;

public interface HonorService extends IService<Honor> {

    int saveHonor(Honor honor);

    int updateHonor(Honor honor);

    int deleteById(String id);

    int realDelete(String id);

    HonorVo findById(String id);

    List<Honor> findList(Honor honor);

    IPage<Honor> findPage(Page page, Honor honor);

    Page<HonorVo> findGroupHonorPage(Page page, HonorVo honor);

    Page<HonorVo> findPersonHonorPage(Page page, HonorVo honor);
}
