package com.sjz.partbuilding.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjz.partbuilding.constants.YTSystemConstant;
import com.sjz.partbuilding.mapper.AnnouncementMapper;
import com.sjz.partbuilding.model.entity.Announcement;
import com.sjz.partbuilding.model.entity.Attachments;
import com.sjz.partbuilding.model.entity.KeyWord;
import com.sjz.partbuilding.service.AnnouncementService;
import com.sjz.partbuilding.service.AttachmentsService;
import com.sjz.partbuilding.service.KeyWordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@Transactional
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {

    @Resource
    private KeyWordService keyWordService;

    @Resource
    private AttachmentsService attachmentsService;

    @Override
    public int saveAnnouncement(Announcement announcement) {
        //直接发布
        if (YTSystemConstant.NOTIFY_NEWS_TYPE.SYSTEM.equals(announcement.getPublishType())) {
            //announcement.setPublishTime(new Date());
        }
        //关键词
        if (announcement.getKeywords() != null && !"".equals(announcement.getKeywords())) {
            //组装关键词对象
            KeyWord keyWord = new KeyWord();
            keyWord.setNameStr(announcement.getKeywords());
            //存入关键词表并返回ids(逗号隔开字符串)
            String idStr = keyWordService.saveKeyWordByNameStr(keyWord);
            announcement.setKeywords(idStr);
        }
        return this.baseMapper.insert(announcement);
    }

    @Override
    public int updateAnnouncement(Announcement announcement) {
        //点击发布
        if (YTSystemConstant.NOTIFY_NEWS_TYPE.SYSTEM.equals(announcement.getPublishType())) {
            //announcement.setPublishTime(new Date());
        }
        //附件全删
        if(StringUtils.isEmpty(announcement.getAttachmentId())){
            announcement.setAttachmentId(",");
        }
        //关键词
        if (announcement.getKeywords() != null && !"".equals(announcement.getKeywords())) {
            //获取到原关键词id
            Announcement key = this.baseMapper.selectById(announcement.getId());
            //清空原关键词并把新关键词重新新增
            String ids = keyWordService.updateKeyWorkByOldIds(key.getKeywords(), announcement.getKeywords());
            announcement.setKeywords(ids);

        }
        return this.baseMapper.updateById(announcement);
    }

    @Override
    public int deleteById(String id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    @Override
    public Announcement findById(String id) {
        Announcement announcement = this.baseMapper.selectById(id);
        if (announcement != null && !"".equals(announcement)) {
            //组装附件集
            if (announcement.getAttachmentId() != null && !"".equals(announcement.getAttachmentId())) {
                List<Map<String,String>> attList = attachmentsService.getAttachmentListByIds(announcement.getAttachmentId());
                announcement.setAttachmentList(attList);
            }
            //关键词
            if (announcement.getKeywords() != null && !"".equals(announcement.getKeywords())) {
                List<KeyWord> keyWorkList = keyWordService.getKeyWorkList(announcement.getKeywords());
                announcement.setKeyWordList(keyWorkList);
            }
        }
        return announcement;
    }

    @Override
    public List<Announcement> findList(Announcement announcement) {
        QueryWrapper<Announcement> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Announcement> findPage(Page page, Announcement announcement) {
        /*List orgIds = new ArrayList();
        User user = userService.findById(UserContext.current().getUserId());
        List<User> userList = userService.findList(user);
        for (User user1 : userList) {
            orgIds.add(user1.getOrgId());
        }*/
        QueryWrapper<Announcement> queryWrapper = new QueryWrapper<>();
        //queryWrapper.in("org_id", orgIds);
        queryWrapper.eq("org_id",announcement.getOrgId());
        if (ObjectUtil.isNotEmpty(announcement.getTitle())) {
            queryWrapper.like("title", announcement.getTitle());
        }
        //发布状态
        if (announcement.getPublishType() != null && !"".equals(announcement.getPublishType())) {
            queryWrapper.eq("publish_type",announcement.getPublishType());
        }
        queryWrapper.orderByDesc("publish_time");
        IPage<Announcement> pageAnnouncement = this.baseMapper.selectPage(page, queryWrapper);
        return pageAnnouncement;
    }

    @Override
    public IPage<Announcement> findCreatePage(Page page, Announcement announcement) {
        QueryWrapper<Announcement> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("org_id", announcement.getOrgId());
        if (ObjectUtil.isNotEmpty(announcement.getTitle())) {
            queryWrapper.like("title", announcement.getTitle());
        }
        //发布状态
        if (announcement.getPublishType() != null && !"".equals(announcement.getPublishType())) {
            queryWrapper.eq("publish_type",announcement.getPublishType());
        }
        queryWrapper.orderByAsc("publish_type");
        queryWrapper.orderByDesc("publish_time");
        queryWrapper.orderByDesc("create_time");
        IPage<Announcement> pageAnnouncement = this.baseMapper.selectPage(page, queryWrapper);
        return pageAnnouncement;
    }

    /**
     * 根据关键词id获取公告id
     *
     * @param id 关键词id
     * @return
     */
    @Override
    public Announcement getAnnouncementByKeyWordId(String id) {
        LambdaQueryWrapper<Announcement> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Announcement::getKeywords,id);
        Announcement announcement = this.baseMapper.selectOne(lambdaQueryWrapper);
        return announcement;
    }
}
