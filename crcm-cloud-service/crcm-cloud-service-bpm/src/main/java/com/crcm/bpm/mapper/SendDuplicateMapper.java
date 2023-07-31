package com.crcm.bpm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.bpm.domain.dto.response.SendDuplicateDTO;
import com.crcm.bpm.domain.vo.SendDuplicateVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SendDuplicateMapper  extends BaseMapper<SendDuplicateVo> {

    /**
    * @Description: 根据申请id查询已抄送的用户
    * @Param:
    * @Author: dzl
    * @Date: 2021/3/30 14:18
    */
    List<String> selectCopyToBYApplyId(@Param("applyId") Long applyId);

    /** 
    * @Description: 分页查询抄送信息
    * @Param: 
    * @Author: dzl 
    * @Date: 2021/3/31 9:08 
    */ 
    IPage<SendDuplicateDTO> selectSendDuplicatePage(@Param("page") Page page, @Param("sendDuplicate") SendDuplicateVo sendDuplicate);

}