package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.partbuilding.model.entity.Announcement;


import java.util.List;

public interface AnnouncementService extends IService<Announcement> {

    int saveAnnouncement(Announcement announcement);

    int updateAnnouncement(Announcement announcement);

    int deleteById(String id);

    int realDelete(String id);

    Announcement findById(String id);

    List<Announcement> findList(Announcement announcement);

    IPage<Announcement> findPage(Page page, Announcement announcement);

    IPage<Announcement> findCreatePage(Page initPage, Announcement announcement);

    /**
     * 根据关键词id获取公告id
     *
     * @param id 关键词id
     * @return
     */
    Announcement getAnnouncementByKeyWordId(String id);
}
