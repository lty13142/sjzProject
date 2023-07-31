package com.sjz.education.service;

import com.sjz.education.model.entity.EduPersonnelManagement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.education.model.vo.BaseQueryVO;
import com.sjz.education.model.vo.RecordVO;

import java.util.List;

/**
* @author SSSCCCC
* @description 针对表【edu_personnel_management(人员管理)】的数据库操作Service
* @createDate 2023-04-04 15:59:39
*/
public interface EduPersonnelManagementService extends IService<EduPersonnelManagement> {

    boolean add(EduPersonnelManagement t);

    boolean edit(EduPersonnelManagement t);

    boolean deleteById(String id);

    EduPersonnelManagement findByUser();

    List<EduPersonnelManagement> getList();

    EduPersonnelManagement findById(String id);

    List<EduPersonnelManagement> findByIds(List<String> ids);

    List<EduPersonnelManagement> findByUserNames(List<String> userNames);

    List<EduPersonnelManagement> ranking();

    List<EduPersonnelManagement> rankingByVillage();

    EduPersonnelManagement myRanking();

    RecordVO myRecord(BaseQueryVO t);
}
