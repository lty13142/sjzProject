package com.sjz.education.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.education.model.entity.EduMessage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author SSSCCCC
* @description 针对表【edu_message(德育积分_消息通知管理)】的数据库操作Service
* @createDate 2023-04-04 14:36:50
*/
public interface EduMessageService extends IService<EduMessage> {

    boolean add(EduMessage t);

    boolean edit(EduMessage t);

    boolean deleteById(String id);

    List<EduMessage> findByUser(EduMessage t);

    IPage<EduMessage> findByUser(PageT page, EduMessage t);

    List<EduMessage> getList(EduMessage t);

    IPage<EduMessage> getPage(PageT page, EduMessage t);

    int publish(String id);

    boolean batchPublish(List<String> ids);
}
