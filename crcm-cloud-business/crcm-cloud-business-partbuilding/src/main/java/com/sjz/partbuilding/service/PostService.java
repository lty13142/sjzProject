package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.partbuilding.model.entity.Post;
import com.sjz.partbuilding.model.vo.PostTreeVo;

import java.util.List;


/**
 * 党内职务service
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/24 17:03
 */
public interface PostService extends IService<Post> {

    /**
     * 新增
     *
     * @param post 党内职务
     * @return
     */
    int savePost(Post post);

    /**
     * 修改
     *
     * @param post 党内职务
     * @return
     */
    int updatePost(Post post);

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
    Post findById(String id);

    /**
     * 查询列表
     *
     * @param post 查询参数
     * @return
     */
    List<Post> findList(Post post);

    /**
     * 分页查询
     *
     * @param page 分页参数
     * @param post 查询参数
     * @return
     */
    IPage<Post> findPage(Page page, Post post);

    /**
     * 获取人员组织树
     *
     * @param orgId 组织id
     * @return
     */
    List<PostTreeVo> getPersonOrgTree(String orgId);
}
