package com.crcm.bpm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.bpm.domain.dto.ProcessCirculationDto;
import com.crcm.bpm.domain.dto.response.SendDuplicateDTO;
import com.crcm.bpm.domain.vo.SendDuplicateVo;

public interface SendDuplicateService extends IService<SendDuplicateVo> {

    /** 
    * @Description: 保存抄送
    * @Param: 
    * @Author: dzl 
    * @Date: 2021/3/30 11:34
    */ 
    Boolean saveSendDuplicate(ProcessCirculationDto processCirculationDto);
    
    /** 
    * @Description: 设置抄送为已读
    * @Param: 
    * @Author: dzl 
    * @Date: 2021/3/31 8:45 
    */ 
    Boolean setCopyReaded(SendDuplicateVo sendDuplicate);

    /**
    * @Description: 分页查询接收的抄送信息
    * @Param:
    * @Author: dzl
    * @Date: 2021/3/31 8:53
    */
    IPage<SendDuplicateDTO> getPage(Page page, SendDuplicateVo sendDuplicate);
    
    /** 
    * @Description: 分页查询发起的抄送信息
    * @Param: 
    * @Author: dzl 
    * @Date: 2021/9/23 11:24
    */ 
    IPage<SendDuplicateDTO> sendPage(Page page, SendDuplicateVo sendDuplicate);

    /**
    * @Description:  查询全部抄送
    * @Param:
    * @Date: 2021/12/16 10:39
    */
    IPage<SendDuplicateDTO> getAllPage(Page page, SendDuplicateVo sendDuplicate);

    int deleteById(Long id);


}
