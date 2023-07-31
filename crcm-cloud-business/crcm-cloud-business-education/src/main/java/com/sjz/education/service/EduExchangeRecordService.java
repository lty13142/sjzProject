package com.sjz.education.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.education.model.dto.EduPointExchangeDTO;
import com.sjz.education.model.entity.EduExchangeRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.education.model.vo.BaseQueryVO;
import com.sjz.education.model.vo.EchartsVO;
import com.sjz.education.model.vo.ExchangeRecordVO;

import java.util.List;
import java.util.Map;

/**
* @author SSSCCCC
* @description 针对表【edu_exchange_record(德育积分_积分兑换记录)】的数据库操作Service
* @createDate 2023-04-06 10:31:37
*/
public interface EduExchangeRecordService extends IService<EduExchangeRecord> {

    String add(EduExchangeRecord t);

    boolean edit(EduExchangeRecord t);

    boolean deleteById(String id);

    boolean pass(String id);

    boolean batchPass(BaseQueryVO t);

    List<ExchangeRecordVO> getListByUser(EduExchangeRecord t);

    List<ExchangeRecordVO> getPassList();

    List<EduExchangeRecord> getList(EduExchangeRecord t);

    IPage<EduExchangeRecord> getPage(PageT page, EduExchangeRecord t);

    List<EchartsVO> countByMonth();

    List<EchartsVO> countByYear();

    Map<String,Object> pointExchange(EduPointExchangeDTO dto);

    List<EchartsVO> countByVillage();

    List<EchartsVO> countByProducts();
}
