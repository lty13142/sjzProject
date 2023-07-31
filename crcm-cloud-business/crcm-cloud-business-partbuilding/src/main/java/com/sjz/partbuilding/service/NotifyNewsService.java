package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.sjz.partbuilding.model.entity.NotifyNews;
import com.sjz.partbuilding.model.vo.LoginKeyWordVo;
import com.sjz.partbuilding.model.vo.NotifyNewsVo;

import java.util.List;


/**
 * 通知新闻service
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/7/8 14:14
 */
public interface NotifyNewsService extends IService<NotifyNews> {

    /**
     * 新增
     *
     * @param notifyNewsVo 通知新闻
     * @return
     */
    int saveNotifyNews(NotifyNewsVo notifyNewsVo);

    /**
     * 修改
     *
     * @param notifyNewsVo 通知新闻
     * @return
     */
    int updateNotifyNews(NotifyNewsVo notifyNewsVo);

    /**
     * 根据id删除
     *
     * @param id 删除条件
     * @return
     */
    int deleteById(String id);

    /**
     * 真实删除
     *
     * @param id 删除条件
     * @return
     */
    int realDelete(String id);

    /**
     * 根据id查询
     *
     * @param id 查询条件
     * @return
     */
    NotifyNewsVo findById(String id);

    /**
     * 根据条件查询列表
     *
     * @param notifyNews 通知新闻
     * @return
     */
    List<NotifyNews> findList(NotifyNews notifyNews);

    /**
     * 获取新闻/通知管理分页
     *
     * @param page         分页条件
     * @param notifyNewsVo 通知新闻
     * @return
     */
    IPage<NotifyNews> getSendPage(Page page, NotifyNewsVo notifyNewsVo);

    /**
     * 获取新闻/通知分页
     *
     * @param page         分页条件
     * @param notifyNewsVo 通知新闻
     * @return
     */
    IPage<NotifyNews> getAcceptPage(Page page, NotifyNewsVo notifyNewsVo);

    /**
     * 获取大屏首页新闻
     *
     * @param notifyNews 查询条件
     * @return
     */
    IPage<NotifyNewsVo> getLoginNews(NotifyNews notifyNews);

    /**
     * 根据关键词id获取新闻或公告id
     *
     * @param id 关键词id
     * @return
     */
    LoginKeyWordVo getNotifyByKeyWordId(String id);

    /**
     * 获取新闻封面轮播图
     * @param notifyNewsVo
     * @return
     */
    List<NotifyNewsVo> getCoverNews(NotifyNewsVo notifyNewsVo);
}
