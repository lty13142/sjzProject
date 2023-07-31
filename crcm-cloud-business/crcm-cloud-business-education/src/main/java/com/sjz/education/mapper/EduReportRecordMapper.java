package com.sjz.education.mapper;

import com.sjz.education.model.entity.EduReportRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjz.education.model.vo.EchartsVO;

import java.util.List;

/**
* @author SSSCCCC
* @description 针对表【edu_report_record(德育积分_积分上报记录)】的数据库操作Mapper
* @createDate 2023-04-04 14:36:50
* @Entity com.sjz.education.model.entity.EduReportRecord
*/
public interface EduReportRecordMapper extends BaseMapper<EduReportRecord> {
    /**
     * 统计当年积分获得走势图
     * @return
     */
    List<EchartsVO> countPointsByYear();
}




