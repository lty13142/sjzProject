package com.crcm.bpm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.bpm.domain.dto.request.CountersignCompletedDTO;
import com.crcm.bpm.domain.entity.CounterSignDO;

import java.util.List;

public interface CounterSignService extends IService<CounterSignDO> {

    int saveCounterSign(CounterSignDO counterSignDO);

    int updateCounterSign(CounterSignDO counterSignDO);

    int deleteById(String id);

    int realDelete(String id);

    CounterSignDO findById(String id);

    List<CounterSignDO> findList(CounterSignDO counterSignDO);

    IPage<CounterSignDO> findPage(Page page, CounterSignDO counterSignDO);

    /**
     * 进行会签，修改会签表状态
     * @param dto
     * @return
     */
    int doCounterSign(CountersignCompletedDTO dto);

}
