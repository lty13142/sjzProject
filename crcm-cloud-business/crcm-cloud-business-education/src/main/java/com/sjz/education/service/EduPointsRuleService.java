package com.sjz.education.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.education.model.entity.EduPointsRule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.education.model.vo.PointsRuleTreeView;

import java.util.List;

/**
 * @author SSSCCCC
 * @description 针对表【edu_points_rule(积分规则管理)】的数据库操作Service
 * @createDate 2023-04-03 16:49:38
 */
public interface EduPointsRuleService extends IService<EduPointsRule> {

    /**
     * 新增积分规则
     *
     * @param t
     * @return
     */
    boolean add(EduPointsRule t);

    /**
     * 修改积分规则
     *
     * @param t
     * @return
     */
    boolean edit(EduPointsRule t);

    /**
     * 根据id进行删除
     *
     * @param id
     * @return
     */
    boolean deleteById(String id);

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    EduPointsRule findById(String id);

    /**
     * 通过ids查询
     *
     * @param ids
     * @return
     */
    List<EduPointsRule> findByIds(List<String> ids);

    /**
     * 获取积分规则列表
     *
     * @param t
     * @return
     */
    List<EduPointsRule> getList(EduPointsRule t);

    /**
     * 获取积分规则树形结构
     *
     * @param t
     * @return
     */
    List<PointsRuleTreeView> getTree(EduPointsRule t);

    /**
     * 获取积分规则分页
     *
     * @param t,page分页参数
     * @return
     */
    IPage<EduPointsRule> getPage(PageT page, EduPointsRule t);

    /**
     * 审核通过并发布
     *
     * @param id
     * @return
     */
    int publish(String id);

    /**
     * 批量发布
     *
     * @param ids id集合
     * @return
     */
    boolean batchPublish(List<String> ids);
//
//    /**
//     * 审核不通过
//     *
//     * @param id
//     * @return
//     */
//    int reject(String id);
//
//    /**
//     * 批量审核不通过
//     *
//     * @param ids id集合
//     * @return
//     */
//    boolean batchReject(List<String> ids);
}
