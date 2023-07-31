package com.sjz.education.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.education.mapper.EduNoticeMapper;
import com.sjz.education.model.entity.EduNotice;
import com.sjz.education.service.EduNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author SSSCCCC
 * @description 针对表【edu_notice_activity(活动及通知消息管理)】的数据库操作Service实现
 * @createDate 2023-04-03 14:35:16
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class EduNoticeServiceImpl extends ServiceImpl<EduNoticeMapper, EduNotice>
        implements EduNoticeService {

    private static final String ERR_MSG_STATUS = "不能重复发布";

    /**
     * 新增公告
     *
     * @param t
     * @return
     */
    @Override
    public boolean add(EduNotice t) {
        t.setStatus(1);
        return this.save(t);
    }

    /**
     * 修改公告
     *
     * @param t
     * @return
     */
    @Override
    public boolean edit(EduNotice t) {
        return this.updateById(t);
    }

    /**
     * 根据id进行删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(String id) {

        return this.removeById(id);
    }

    /**
     * 获取公告列表
     *
     * @param t
     * @return
     */
    @Override
    public List<EduNotice> getList(EduNotice t) {
        LambdaQueryWrapper<EduNotice> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ObjectUtil.isNotEmpty(t.getTitle()), EduNotice::getTitle, t.getTitle());
        wrapper.eq(ObjectUtil.isNotEmpty(t.getStatus()), EduNotice::getStatus, t.getStatus());
        wrapper.orderByDesc(EduNotice::getCreateTime);
        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 获取公告分页
     *
     * @param t,page分页参数
     * @return
     */
    @Override
    public IPage<EduNotice> getPage(PageT page, EduNotice t) {
        LambdaQueryWrapper<EduNotice> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ObjectUtil.isNotEmpty(t.getTitle()), EduNotice::getTitle, t.getTitle());
        wrapper.eq(ObjectUtil.isNotEmpty(t.getStatus()), EduNotice::getStatus, t.getStatus());
        wrapper.orderByDesc(EduNotice::getCreateTime);
        return this.page(page, wrapper);
    }

    /**
     * 审核通过公告并发布
     *
     * @param id
     * @return
     */
    @Override
    public int publish(String id) {
        EduNotice notice = this.baseMapper.selectById(id);
        if (!notice.getStatus().equals(0)) {
            throw new RuntimeException(ERR_MSG_STATUS);
        }
        notice.setStatus(1);
        return this.baseMapper.updateById(notice);
    }

    /**
     * 批量审核通过公告并发布
     *
     * @param ids id集合
     * @return
     */
    @Override
    public boolean batchPublish(List<String> ids) {
        LambdaQueryWrapper<EduNotice> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(EduNotice::getId, ids);
        List<EduNotice> eduNotices = this.baseMapper.selectList(wrapper);
        for (EduNotice notice : eduNotices) {
            if (!notice.getStatus().equals(0)) {
                throw new RuntimeException(ERR_MSG_STATUS);
            }
            notice.setStatus(1);
        }
        return this.updateBatchById(eduNotices);
    }

//    /**
//     * 审核不通过
//     *
//     * @param id
//     * @return
//     */
//    @Override
//    public int reject(String id) {
//        EduNotice notice = this.baseMapper.selectById(id);
//        if (!notice.getStatus().equals(0)) {
//            throw new RuntimeException(ERR_MSG_STATUS);
//        }
//        notice.setStatus(-1);
//        return this.baseMapper.updateById(notice);
//    }
//
//    /**
//     * 批量审核不通过
//     *
//     * @param ids id集合
//     * @return
//     */
//    @Override
//    public boolean batchReject(List<String> ids) {
//        LambdaQueryWrapper<EduNotice> wrapper = new LambdaQueryWrapper<>();
//        wrapper.in(EduNotice::getId, ids);
//        List<EduNotice> eduNotices = this.baseMapper.selectList(wrapper);
//        for (EduNotice notice : eduNotices) {
//            if (!notice.getStatus().equals(0)) {
//                throw new RuntimeException(ERR_MSG_STATUS);
//            }
//            notice.setStatus(-1);
//        }
//        return this.updateBatchById(eduNotices);
//    }
}




