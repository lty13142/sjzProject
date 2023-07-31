package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.partbuilding.model.entity.Target;

import java.util.List;

public interface TargetService extends IService<Target> {

    int saveTarget(Target Target);

    int updateTarget(Target Target);

    int deleteById(String id);

    int realDelete(String id);

    Target findById(String id);

    List<Target> findList(Target Target);

    IPage<Target> findPage(Page page,Target Target);

}
