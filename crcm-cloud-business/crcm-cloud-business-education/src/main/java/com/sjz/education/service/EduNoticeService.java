package com.sjz.education.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.education.model.entity.EduNotice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author SSSCCCC
 * @description 针对表【edu_notice(通知消息管理)】的数据库操作Service
 * @createDate 2023-04-03 14:35:16
 */
public interface EduNoticeService extends IService<EduNotice> {
    /**
     * 新增消息通知
     *
     * @param t
     * @return
     */
    boolean add(EduNotice t);

    /**
     * 修改消息通知
     *
     * @param t
     * @return
     */
    boolean edit(EduNotice t);

    /**
     * 根据id进行删除
     *
     * @param id
     * @return
     */
    boolean deleteById(String id);

    /**
     * 获取通知消息列表
     *
     * @param t
     * @return
     */
    List<EduNotice> getList(EduNotice t);

    /**
     * 获取通知消息分页
     *
     * @param t,page分页参数
     * @return
     */
    IPage<EduNotice> getPage(PageT page, EduNotice t);

    /**
     * 发布
     *
     * @param id
     * @return
     */
    int publish(String id);

    /**
     * 批量发布
     *
     * @param ids id集合
     * @return
     */
    boolean batchPublish(List<String> ids);
//
//    /**
//     * 审核不通过
//     *
//     * @param id
//     * @return
//     */
//    int reject(String id);
//
//    /**
//     * 批量审核不通过
//     *
//     * @param ids id集合
//     * @return
//     */
//    boolean batchReject(List<String> ids);
}
