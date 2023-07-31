package com.sjz.education.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.exception.CustomException;
import com.sjz.education.model.entity.EduPointsRule;
import com.sjz.education.model.vo.PointsRuleTreeView;
import com.sjz.education.service.EduPointsRuleService;
import com.sjz.education.mapper.EduPointsRuleMapper;
import com.sjz.education.utils.UtilPage;
import com.sjz.education.utils.UtilTree;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SSSCCCC
 * @description 针对表【edu_points_rule(积分规则管理)】的数据库操作Service实现
 * @createDate 2023-04-03 16:49:38
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class EduPointsRuleServiceImpl extends ServiceImpl<EduPointsRuleMapper, EduPointsRule>
        implements EduPointsRuleService {

    private static final String ERR_MSG_NAME = "标题名称已存在";
    private static final String ERR_MSG_STATUS = "不能重复发布";

    /**
     * 新增积分规则
     *
     * @param t
     * @return
     */
    @Override
    public boolean add(EduPointsRule t) {
        LambdaQueryWrapper<EduPointsRule> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduPointsRule::getRuleName, t.getRuleName());
        Integer count = this.baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new CustomException(ERR_MSG_NAME);
        }
        List<String> pids = t.getPids();
        if (ObjectUtil.isNotEmpty(pids)) {
            String strip = StringUtils.strip(pids.toString(), "[]");
            t.setPidCollect(strip.replaceAll(" ", ""));
        }
        if (StringUtils.isBlank(t.getPid())) {
            t.setNumber(1);
        } else {
            Integer number = this.baseMapper.selectById(t.getPid()).getNumber();
            t.setNumber(number + 1);
        }
        t.setStatus(0);
        return this.save(t);
    }

    /**
     * 修改积分规则
     *
     * @param t
     * @return
     */
    @Override
    public boolean edit(EduPointsRule t) {
        LambdaQueryWrapper<EduPointsRule> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduPointsRule::getRuleName, t.getRuleName());
        EduPointsRule rule = this.baseMapper.selectOne(wrapper);
        if (ObjectUtil.isNotEmpty(rule) && !rule.getId().equals(t.getId())) {
            throw new CustomException(ERR_MSG_NAME);
        }
        List<String> pids = t.getPids();
        if (ObjectUtil.isNotEmpty(pids)) {
            String strip = StringUtils.strip(pids.toString(), "[]");
            t.setPidCollect(strip.replaceAll(" ", ""));
        } else {
            t.setPid("");
            t.setPidCollect("");
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
        LambdaQueryWrapper<EduPointsRule> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(EduPointsRule::getPid,id);
        Integer count = this.baseMapper.selectCount(wrapper);
        if (count>0){
            return false;
        }
        return this.removeById(id);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @Override
    public EduPointsRule findById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 通过ids查询
     *
     * @param ids
     * @return
     */
    @Override
    public List<EduPointsRule> findByIds(List<String> ids) {
        LambdaQueryWrapper<EduPointsRule> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(EduPointsRule::getId, ids);
        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 获取积分规则列表
     *
     * @param t
     * @return
     */
    @Override
    public List<EduPointsRule> getList(EduPointsRule t) {
        LambdaQueryWrapper<EduPointsRule> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(t.getStatus()), EduPointsRule::getStatus, t.getStatus());
        wrapper.like(ObjectUtil.isNotEmpty(t.getRuleName()), EduPointsRule::getRuleName, t.getRuleName());
        wrapper.orderByDesc(EduPointsRule::getCreateTime);
        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 获取积分规则树形结构
     *
     * @param t
     * @return
     */
    @Override
    public List<PointsRuleTreeView> getTree(EduPointsRule t) {
        List<PointsRuleTreeView> tree = this.baseMapper.findTree(t);
        return UtilTree.listToTree(tree);
    }

    /**
     * 获取积分规则分页
     *
     * @param t,page分页参数
     * @return
     */
    @Override
    public IPage<EduPointsRule> getPage(PageT page, EduPointsRule t) {
        List<EduPointsRule> list = this.getList(t);
        if (ObjectUtil.isNotEmpty(t.getPid())) {
            list = this.getAllChild(t.getPid(), list);
        }
        return UtilPage.getPage(page, list);
    }

    /**
     * 递归获取所有子项目
     * @param id
     * @param list
     * @return
     */
    private List<EduPointsRule> getAllChild(String id, List<EduPointsRule> list) {
        Iterator var2 = list.iterator();
        List<EduPointsRule> ruleList = new ArrayList<>();
        ruleList.add(list.stream().filter(n -> n.getId().equals(id)).collect(Collectors.toList()).get(0));
        while (var2.hasNext()) {
            EduPointsRule rule = (EduPointsRule) var2.next();
            if (id.equals(rule.getPid())) {
                ruleList.addAll(getAllChild(rule.getId(), list));
            }
        }
        return ruleList;
    }

    /**
     * 审核通过并发布
     *
     * @param id
     * @return
     */
    @Override
    public int publish(String id) {
        EduPointsRule rule = this.baseMapper.selectById(id);
        if (!rule.getStatus().equals(0)) {
            throw new RuntimeException(ERR_MSG_STATUS);
        }
        rule.setStatus(1);
        return this.baseMapper.updateById(rule);
    }

    /**
     * 批量发布
     *
     * @param ids id集合
     * @return
     */
    @Override
    public boolean batchPublish(List<String> ids) {
        LambdaQueryWrapper<EduPointsRule> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(EduPointsRule::getId, ids);
        List<EduPointsRule> rules = this.baseMapper.selectList(wrapper);
        for (EduPointsRule rule : rules) {
            if (!rule.getStatus().equals(0)) {
                throw new RuntimeException(ERR_MSG_STATUS);
            }
            rule.setStatus(1);
        }
        return this.updateBatchById(rules);
    }

//    /**
//     * 审核不通过
//     *
//     * @param id
//     * @return
//     */
//    @Override
//    public int reject(String id) {
//        EduPointsRule rule = this.baseMapper.selectById(id);
//        if (!rule.getStatus().equals(0)) {
//            throw new RuntimeException(ERR_MSG_STATUS);
//        }
//        rule.setStatus(-1);
//        return this.baseMapper.updateById(rule);
//    }
//
//    /**
//     * 批量审核不通过
//     *
//     * @param ids id集合
//     * @return
//     */
//    @Override
//    public boolean batchReject(List<String> ids) {
//        LambdaQueryWrapper<EduPointsRule> wrapper = new LambdaQueryWrapper<>();
//        wrapper.in(EduPointsRule::getId, ids);
//        List<EduPointsRule> rules = this.baseMapper.selectList(wrapper);
//        for (EduPointsRule rule : rules) {
//            if (!rule.getStatus().equals(0)) {
//                throw new RuntimeException(ERR_MSG_STATUS);
//            }
//            rule.setStatus(-1);
//        }
//        return this.updateBatchById(rules);
//    }
}




