package com.sjz.partbuilding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjz.partbuilding.model.entity.Post;
import com.sjz.partbuilding.model.vo.PostTreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 党内职务mapper
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/24 17:06
 */
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 真实删除
     *
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    /**
     * 获取职位列表
     * @param orgId
     * @return
     */
    List<PostTreeVo> findPostTreeList(String orgId);

}
