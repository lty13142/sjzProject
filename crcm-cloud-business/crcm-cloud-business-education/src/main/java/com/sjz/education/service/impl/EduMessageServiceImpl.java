package com.sjz.education.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.sso.domain.AuthUser;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.sjz.education.model.entity.EduMessage;
import com.sjz.education.service.EduMessageService;
import com.sjz.education.mapper.EduMessageMapper;
import com.sjz.education.utils.UtilPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SSSCCCC
 * @description 针对表【edu_message(德育积分_消息通知管理)】的数据库操作Service实现
 * @createDate 2023-04-04 14:36:50
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class EduMessageServiceImpl extends ServiceImpl<EduMessageMapper, EduMessage>
        implements EduMessageService {

    private static final String ERR_MSG_STATUS = "不能重复发布";

    /**
     * 新增消息通知
     *
     * @param t
     * @return
     */
    @Override
    public boolean add(EduMessage t) {
        t.setStatus(0);
        if (t.getMsgType().equals(2)) {
            List<String> targetIds = t.getTargetIds();
            String strip = StringUtils.strip(targetIds.toString(), "[]");
            t.setTargetId(strip.replaceAll(" ", ""));
        }
        return this.save(t);
    }

    /**
     * 修改消息通知
     *
     * @param t
     * @return
     */
    @Override
    public boolean edit(EduMessage t) {
        if (t.getMsgType().equals(2)) {
            List<String> targetIds = t.getTargetIds();
            String strip = StringUtils.strip(targetIds.toString(), "[]");
            t.setTargetId(strip.replaceAll(" ", ""));
        }
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
     * 用户通知消息列表
     *
     * @param t
     * @return
     */
    @Override
    public List<EduMessage> findByUser(EduMessage t) {
        AuthUser user = SecurityUtil.getCurrentUser();
        LambdaQueryWrapper<EduMessage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(t.getStatus()), EduMessage::getStatus, t.getStatus());
        wrapper.like(ObjectUtil.isNotEmpty(t.getTitle()), EduMessage::getTitle, t.getTitle());
        wrapper.orderByDesc(EduMessage::getCreateTime);
        List<EduMessage> messages = this.baseMapper.selectList(wrapper);
        messages = messages.stream().filter(eduMessage -> {
            if (eduMessage.getMsgType().equals(1)) {
                return true;
            } else {
                List<String> list = Arrays.asList(eduMessage.getTargetId().split(","));
                return list.contains(user.getUserId());
            }
        }).collect(Collectors.toList());
        return messages;
    }


    /**
     * 用户通知消息分页
     *
     * @param t
     * @return
     */
    @Override
    public IPage<EduMessage> findByUser(PageT page, EduMessage t) {
        List<EduMessage> byUser = this.findByUser(t);
        return UtilPage.getPage(page,byUser);
    }

    /**
     * 获取通知消息列表
     *
     * @param t
     * @return
     */
    @Override
    public List<EduMessage> getList(EduMessage t) {
        LambdaQueryWrapper<EduMessage> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ObjectUtil.isNotEmpty(t.getTitle()), EduMessage::getTitle, t.getTitle());
        wrapper.eq(ObjectUtil.isNotEmpty(t.getStatus()), EduMessage::getStatus, t.getStatus());
        wrapper.orderByDesc(EduMessage::getCreateTime);
        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 获取通知消息分页
     *
     * @param t,page分页参数
     * @return
     */
    @Override
    public IPage<EduMessage> getPage(PageT page, EduMessage t) {
        LambdaQueryWrapper<EduMessage> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ObjectUtil.isNotEmpty(t.getTitle()), EduMessage::getTitle, t.getTitle());
        wrapper.eq(ObjectUtil.isNotEmpty(t.getStatus()), EduMessage::getStatus, t.getStatus());
        wrapper.orderByDesc(EduMessage::getCreateTime);
        return this.page(page, wrapper);
    }

    /**
     * 发布
     *
     * @param id
     * @return
     */
    @Override
    public int publish(String id) {
        EduMessage message = this.baseMapper.selectById(id);
        if (!message.getStatus().equals(0)) {
            throw new RuntimeException(ERR_MSG_STATUS);
        }
        message.setStatus(1);
        return this.baseMapper.updateById(message);
    }

    /**
     * 批量发布
     *
     * @param ids id集合
     * @return
     */
    @Override
    public boolean batchPublish(List<String> ids) {
        LambdaQueryWrapper<EduMessage> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(EduMessage::getId, ids);
        List<EduMessage> messages = this.baseMapper.selectList(wrapper);
        for (EduMessage message : messages) {
            if (!message.getStatus().equals(0)) {
                throw new RuntimeException(ERR_MSG_STATUS);
            }
            message.setStatus(1);
        }
        return this.updateBatchById(messages);
    }

}




