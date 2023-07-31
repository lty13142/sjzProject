package com.sjz.education.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.education.model.entity.EduReportRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.education.model.vo.AreaReportVO;
import com.sjz.education.model.vo.EchartsVO;
import com.sjz.education.model.vo.ReportRecordVO;

import java.util.List;

/**
* @author SSSCCCC
* @description 针对表【edu_report_record(德育积分_积分上报记录)】的数据库操作Service
* @createDate 2023-04-04 14:36:50
*/
public interface EduReportRecordService extends IService<EduReportRecord> {

    boolean add(EduReportRecord t);

    boolean edit(EduReportRecord t);

    boolean deleteById(String id);

    boolean pass(EduReportRecord t);

    boolean reject(EduReportRecord t);

    List<ReportRecordVO> getListByUser(EduReportRecord t);

    List<EduReportRecord> getList(EduReportRecord t);

    IPage<EduReportRecord> getPage(PageT page, EduReportRecord t);

    List<EchartsVO> getRecordByVillage();

    List<AreaReportVO> getRecordPosition();

    List<EchartsVO> countByYear();
}
