package com.sjz.partbuilding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjz.partbuilding.model.entity.NotifyNews;
import com.sjz.partbuilding.model.vo.NotifyNewsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通知新闻mapper
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/7/8 14:19
 */
public interface NotifyNewsMapper extends BaseMapper<NotifyNews> {

    /**
     * 真实删除
     *
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    /**
     * 分页处理->获取新闻通知
     *
     * @param page         分页条件
     * @param notifyNewsVo 通知新闻
     * @return
     */
    List<NotifyNewsVo> getNotifyNewsPage(Page page, @Param("notifyNewsVo") NotifyNewsVo notifyNewsVo);

    /**
     * 分页处理->获取新闻通知
     *
     * @param page         分页条件
     * @param notifyNewsVo 通知新闻
     * @return
     */
    List<NotifyNewsVo> getAcceptNotifyNewsPage(Page page, @Param("notifyNewsVo") NotifyNewsVo notifyNewsVo);

    /**
     * 根据id获取notifyNews扩展类
     *
     * @param id 查询条件
     * @return
     */
    NotifyNewsVo getNotifyNewsVo(@Param("id") String id);

    /**
     * 获取大屏首页新闻
     *
     * @param page       分页参数
     * @param notifyNews 查询条件
     * @return
     */
    List<NotifyNewsVo> findLoginNewsPage(Page page, @Param("notifyNews") NotifyNews notifyNews);

    /**
     * 获取新闻封面轮播图
     * @param page
     * @param notifyNewsVo
     * @return
     */
    List<NotifyNewsVo> getCoverNewsPage(Page page, @Param("notifyNewsVo") NotifyNewsVo notifyNewsVo);
}
