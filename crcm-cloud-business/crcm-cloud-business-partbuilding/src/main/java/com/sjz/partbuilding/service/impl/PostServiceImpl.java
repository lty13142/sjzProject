package com.sjz.partbuilding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjz.partbuilding.mapper.PostMapper;
import com.sjz.partbuilding.model.entity.Post;
import com.sjz.partbuilding.model.vo.PostTreeVo;
import com.sjz.partbuilding.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 党内职务serviceImpl
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/24 17:06
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

//    @Resource
//    private UserService userService;

    /**
     * 新增
     *
     * @param post 党内职务
     * @return
     */
    @Override
    public int savePost(Post post) {
        return this.baseMapper.insert(post);
    }

    /**
     * 修改
     *
     * @param post 党内职务
     * @return
     */
    @Override
    public int updatePost(Post post) {
        return this.baseMapper.updateById(post);
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
    public Post findById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询列表
     *
     * @param post 查询参数
     * @return
     */
    @Override
    public List<Post> findList(Post post) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        if (post.getPostName() != null && !"".equals(post.getPostName())) {
            queryWrapper.like("post_name", post.getPostName());
        }
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询
     *
     * @param page 分页参数
     * @param post 查询参数
     * @return
     */
    @Override
    public IPage<Post> findPage(Page page, Post post) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        IPage<Post> pagePost = this.baseMapper.selectPage(page, queryWrapper);
        return pagePost;
    }

    /**
     * 获取人员组织树
     *
     * @param orgId 组织id
     * @return
     */
    @Override
    public List<PostTreeVo> getPersonOrgTree(String orgId) {
        List<PostTreeVo> postTreeList = this.baseMapper.findPostTreeList(orgId);
        List<PostTreeVo> treeVoList = toPostTreeTree(postTreeList);
        return treeVoList;
    }

    /**
     * 递归
     *
     * @param list
     * @return
     */
    private List<PostTreeVo> toPostTreeTree(List<PostTreeVo> list) {
        //初始化树状结果返回值
        List<PostTreeVo> treeList = new ArrayList<>();
        //获取最上层级id集合
        List<String> superList = toSuperList(list);
        for (PostTreeVo postTreeVo : list) {
            if (superList.contains(postTreeVo.getId())) {
                //递归出最上层级下的所有子集合
                PostTreeVo result = toChildren(postTreeVo, list);
                //不为null则添加进结构数据中返回
                if (result != null && !"".equals(result)) {
                    treeList.add(postTreeVo);
                }
            }
        }
        return treeList;
    }

    /**
     * 递归出最上层级下的所有子集合
     *
     * @param list
     * @return
     */
    private List<String> toSuperList(List<PostTreeVo> list) {
        //创建储存所有ids的集合
        List<String> ids = new ArrayList<>();
        //创建ids返回集合
        List<String> superList = new ArrayList<>();
        for (PostTreeVo postTreeVo : list) {
            ids.add(postTreeVo.getId());
        }
        for (PostTreeVo postTreeVo : list) {
            //循环出没有pid的对象并添加进返回集合中
            if (!ids.contains(postTreeVo.getPid())) {
                superList.add(postTreeVo.getId());
            }
        }
        return superList;
    }

    /**
     * 进入递归
     *
     * @param postTreeVo
     * @param companyList
     * @return
     */
    private PostTreeVo toChildren(PostTreeVo postTreeVo, List<PostTreeVo> companyList) {
        for (PostTreeVo key : companyList) {
            //判断是否有子项
            if (postTreeVo.getId().equals(key.getPid())) {
                //如果没有储存子项的子集合则给他创建一个
                if (postTreeVo.getChildren() == null) {
                    postTreeVo.setChildren(new ArrayList<>());
                }
                //进入递归算法,进入下一层及寻找所有子项并将他们添加进子集合
                postTreeVo.getChildren().add(toChildren(key, companyList));
            }
        }
        return postTreeVo;
    }
}
