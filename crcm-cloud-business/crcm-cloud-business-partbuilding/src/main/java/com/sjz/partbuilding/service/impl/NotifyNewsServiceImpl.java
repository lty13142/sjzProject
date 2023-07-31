package com.sjz.partbuilding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjz.partbuilding.constants.YTSystemConstant;
import com.sjz.partbuilding.mapper.NotifyNewsMapper;
import com.sjz.partbuilding.model.entity.Announcement;
import com.sjz.partbuilding.model.entity.Attachments;
import com.sjz.partbuilding.model.entity.KeyWord;
import com.sjz.partbuilding.model.entity.NotifyNews;
import com.sjz.partbuilding.model.vo.LoginKeyWordVo;
import com.sjz.partbuilding.model.vo.NotifyNewsVo;
import com.sjz.partbuilding.service.AnnouncementService;
import com.sjz.partbuilding.service.AttachmentsService;
import com.sjz.partbuilding.service.KeyWordService;
import com.sjz.partbuilding.service.NotifyNewsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 通知新闻serviceImpl
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/7/8 14:16
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NotifyNewsServiceImpl extends ServiceImpl<NotifyNewsMapper, NotifyNews> implements NotifyNewsService {

    @Resource
    private AttachmentsService attachmentsService;

    @Resource
    private KeyWordService keyWordService;

    @Resource
    private AnnouncementService announcementService;

    /**
     * 新增
     *
     * @param notifyNewsVo 通知新闻
     * @return
     */
    @Override
    public int saveNotifyNews(NotifyNewsVo notifyNewsVo) {
        //直接发布
        if (YTSystemConstant.NOTIFY_NEWS_TYPE.SYSTEM.equals(notifyNewsVo.getPublishType())) {
            //notifyNewsVo.setPublishTime(new Date());
        }
        //关键词
        if (notifyNewsVo.getKeywords() != null && !"".equals(notifyNewsVo.getKeywords())) {
            //组装关键词对象
            KeyWord keyWord = new KeyWord();
            keyWord.setNameStr(notifyNewsVo.getKeywords());
            //存入关键词表并返回ids(逗号隔开字符串)
            String idStr = keyWordService.saveKeyWordByNameStr(keyWord);
            notifyNewsVo.setKeywords(idStr);
        }
        //获取当前登陆人
        int insert = this.baseMapper.insert(notifyNewsVo);
        return insert;
    }


    /**
     * 修改
     *
     * @param notifyNewsVo 通知新闻
     * @return
     */
    @Override
    public int updateNotifyNews(NotifyNewsVo notifyNewsVo) {
        //点击发布
        if (YTSystemConstant.NOTIFY_NEWS_TYPE.SYSTEM.equals(notifyNewsVo.getPublishType())) {
            //notifyNewsVo.setPublishTime(new Date());
        }
        //附件全删
        if(StringUtils.isEmpty(notifyNewsVo.getAttachment())){
            notifyNewsVo.setAttachment(",");
        }
        //关键词
        if (notifyNewsVo.getKeywords() != null && !"".equals(notifyNewsVo.getKeywords())) {
            //获取到原关键词id
            NotifyNews notifyNews = this.baseMapper.selectById(notifyNewsVo.getId());
            //清空原关键词并把新关键词重新新增
            String ids = keyWordService.updateKeyWorkByOldIds(notifyNews.getKeywords(), notifyNewsVo.getKeywords());
            notifyNewsVo.setKeywords(ids);

        }
        return this.baseMapper.updateById(notifyNewsVo);
    }

    /**
     * 根据id删除
     *
     * @param id 删除条件
     * @return
     */
    @Override
    public int deleteById(String id) {
        return this.baseMapper.deleteById(id);
    }

    /**
     * 真实删除
     *
     * @param id 删除条件
     * @return
     */
    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }


    /**
     * 根据id查询
     *
     * @param id 查询条件
     * @return
     */
    @Override
    public NotifyNewsVo findById(String id) {
        //获取新闻通知对象
        NotifyNewsVo notifyNews = this.baseMapper.getNotifyNewsVo(id);
        if (notifyNews != null && !"".equals(notifyNews)) {
            //判断新闻封面字符串是否存在
            if (StringUtils.isNotBlank(notifyNews.getCover())) {
                //取到新闻封面的minIO路径
//                Attachments att = attachmentsService.getMinIoByAttId(notifyNews.getCover());
//                notifyNews.setCoverCh(att.getMinioPath());
                notifyNews.setCoverCh(attachmentsService.getFilePathById(notifyNews.getCover()));
            }
            //附件
            if (StringUtils.isNotBlank(notifyNews.getAttachment())) {
                List<Map<String,String>> attList = attachmentsService.getAttachmentListByIds(notifyNews.getAttachment());
                notifyNews.setAttachmentsList(attList);
            }
            //关键词
            if (StringUtils.isNotBlank(notifyNews.getKeywords())) {
                List<KeyWord> keyWorkList = keyWordService.getKeyWorkList(notifyNews.getKeywords());
                notifyNews.setKeyWordList(keyWorkList);
            }
        }
        return notifyNews;
    }

    /**
     * 根据条件查询列表
     *
     * @param notifyNews 通知新闻
     * @return
     */
    @Override
    public List<NotifyNews> findList(NotifyNews notifyNews) {
        QueryWrapper<NotifyNews> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }


    /**
     * 分页处理
     *
     * @param page         分页条件
     * @param notifyNewsVo 通知新闻
     * @return
     */
    public List<NotifyNewsVo> notifyNewsVoToPage(Page page, NotifyNewsVo notifyNewsVo) {
        //获取新闻通知
        List<NotifyNewsVo> notifyNewsVoList = this.baseMapper.getNotifyNewsPage(page, notifyNewsVo);
        //组装新闻封面x
        for (NotifyNewsVo vo : notifyNewsVoList) {
            if (vo.getCover() != null && !"".equals(vo.getCover())) {
                //取到新闻封面的minIO路径
//                Attachments att = attachmentsService.getMinIoByAttId(vo.getCover());
                vo.setCoverCh(attachmentsService.getFilePathById(vo.getCover()));
            }
        }
        return notifyNewsVoList;
    }

    /**
     * 获取新闻/通知管理分页
     *
     * @param page         分页条件
     * @param notifyNewsVo 通知新闻
     * @return
     */
    @Override
    public IPage<NotifyNews> getSendPage(Page page, NotifyNewsVo notifyNewsVo) {
        //获取当前登陆人
        /*UserDetail loginMessage = userDetailService.getLoginMessage();
        notifyNewsVo.setSendId(loginMessage.getId());*/
        List<NotifyNewsVo> notifyNewsVoList = notifyNewsVoToPage(page, notifyNewsVo);
        return page.setRecords(notifyNewsVoList);
    }

    /**
     * 获取新闻/通知分页
     *
     * @param page         分页条件
     * @param notifyNewsVo 通知新闻
     * @return
     */
    @Override
    public IPage<NotifyNews> getAcceptPage(Page page, NotifyNewsVo notifyNewsVo) {
        List<NotifyNewsVo> notifyNewsVoList=null;
        if (notifyNewsVo.getType()==1){
            notifyNewsVoList = notifyNewsVoToPage(page, notifyNewsVo);
        }
        if(notifyNewsVo.getType()==2){
            notifyNewsVoList = notifyNewsVoToAcceptPage(page, notifyNewsVo);
        }
        return page.setRecords(notifyNewsVoList);
    }

    /**
     * 接受新闻/通知
     * @param page
     * @param notifyNewsVo
     * @return
     */
    private List<NotifyNewsVo> notifyNewsVoToAcceptPage(Page page, NotifyNewsVo notifyNewsVo) {
        //获取新闻通知
        List<NotifyNewsVo> notifyNewsVoList = this.baseMapper.getAcceptNotifyNewsPage(page, notifyNewsVo);
        //组装新闻封面x
        for (NotifyNewsVo vo : notifyNewsVoList) {
            if (vo.getCover() != null && !"".equals(vo.getCover())) {
                //取到新闻封面的minIO路径
                Attachments att = attachmentsService.getMinIoByAttId(vo.getCover());
                vo.setCoverCh(att.getMinioPath());
            }
        }
        return notifyNewsVoList;
    }

    /**
     * 获取大屏首页新闻
     *
     * @param notifyNews 查询条件
     * @return
     */
    @Override
    public IPage<NotifyNewsVo> getLoginNews(NotifyNews notifyNews) {
        //分页条件展示前4条数据
        Page page = new Page();
        page.setTotal(1);
        page.setSize(4);
        //查询新闻数据
        List<NotifyNewsVo> notifyNewsVoList = this.baseMapper.findLoginNewsPage(page, notifyNews);
        for (NotifyNewsVo notifyNewsVo : notifyNewsVoList) {
            //组装附件
            if (notifyNewsVo.getCover() != null && !"".equals(notifyNewsVo.getCover())) {
                Attachments attachments = attachmentsService.getMinIoByAttId(notifyNewsVo.getCover());
                notifyNewsVo.setCoverCh(attachments.getMinioPath());
            }
        }
        page.setRecords(notifyNewsVoList);
        return page;
    }

    /**
     * 根据关键词id获取新闻或公告id
     *
     * @param id 关键词id
     * @return
     */
    @Override
    public LoginKeyWordVo getNotifyByKeyWordId(String id) {
        LambdaQueryWrapper<NotifyNews> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(NotifyNews::getKeywords, id);
        //根据关键词查新闻通知
        NotifyNews notifyNews = this.baseMapper.selectOne(lambdaQueryWrapper);
        //有值
        if (notifyNews != null) {
            LoginKeyWordVo loginKeyWordVo = new LoginKeyWordVo();
            loginKeyWordVo.setId(notifyNews.getId());
            loginKeyWordVo.setType(YTSystemConstant.NOTIFY_OR_ANNOUNCEMENT.NOTIFY);
            return loginKeyWordVo;
        }
        //根据关键词查公告
        Announcement announcement = announcementService.getAnnouncementByKeyWordId(id);
        //优质
        if (announcement != null) {
            LoginKeyWordVo loginKeyWordVo = new LoginKeyWordVo();
            loginKeyWordVo.setId(announcement.getId());
            loginKeyWordVo.setType(YTSystemConstant.NOTIFY_OR_ANNOUNCEMENT.ANNOUNCEMENT);
            return loginKeyWordVo;
        }
        return new LoginKeyWordVo();
    }

    /**
     * 获取新闻封面轮播图
     * @param notifyNewsVo
     * @return
     */
    @Override
    public List<NotifyNewsVo> getCoverNews(NotifyNewsVo notifyNewsVo) {
        Page page = new Page<>(1, 5);
        //获取新闻通知
        List<NotifyNewsVo> notifyNewsVoList = this.baseMapper.getCoverNewsPage(page, notifyNewsVo);
        //组装新闻封面x
        for (NotifyNewsVo vo : notifyNewsVoList) {
            if (vo.getCover() != null && !"".equals(vo.getCover())) {
//                //取到新闻封面的minIO路径
//                Attachments att = attachmentsService.getMinIoByAttId(vo.getCover());
                vo.setCoverCh(attachmentsService.getFilePathById(vo.getCover()));
            }
        }
        return notifyNewsVoList;
    }


}
