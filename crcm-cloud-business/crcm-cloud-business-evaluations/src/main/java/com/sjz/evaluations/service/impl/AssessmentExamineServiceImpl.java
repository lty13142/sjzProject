package com.sjz.evaluations.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crcm.admin.api.dto.res.AreaDTO;
import com.crcm.admin.api.dto.res.SysOrg;
import com.crcm.admin.api.dto.res.SysPositionDTO;
import com.crcm.admin.api.dto.res.UserBaseInfoVO;
import com.crcm.admin.api.feign.RemoteAreaService;
import com.crcm.admin.api.feign.RemoteOrgService;
import com.crcm.admin.api.feign.RemotePositionService;
import com.crcm.admin.api.feign.RemoteUserService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.crcm.core.constant.AuthConstants;
import com.crcm.core.response.RestResult;
import com.sjz.evaluations.mapper.AssessmentScoreMapper;
import com.sjz.evaluations.model.entity.AssessmentScore;
import com.sjz.evaluations.model.entity.Examine;
import com.sjz.evaluations.model.vo.AssessmentExamineVo;
import com.sjz.evaluations.service.AssessmentScoreService;
import com.sjz.evaluations.service.ExamineService;
import com.sjz.evaluations.util.UtilTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sjz.evaluations.mapper.AssessmentExamineMapper;
import com.sjz.evaluations.model.entity.AssessmentExamine;
import com.sjz.evaluations.service.AssessmentExamineService;

/**
 * 考核指标Service业务层处理
 * 
 * @author guozhilin
 * @date 2023-04-06
 */
@Service
public class AssessmentExamineServiceImpl extends ServiceImpl<AssessmentExamineMapper, AssessmentExamine> implements AssessmentExamineService {

    @Autowired
    private RemoteAreaService remoteAreaService;

    @Autowired
    private ExamineService examineService;

    @Autowired
    private RemoteOrgService remoteOrgService;

    @Autowired
    private AssessmentScoreService assessmentScoreService;

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private RemotePositionService remotePositionService;

    /**
     * 新增考核指标
     * 
     * @param assessmentExamine 考核指标
     * @return 结果
     */
    @Override
    public boolean saveAssessmentExamine(AssessmentExamine assessmentExamine) {
        if (StrUtil.isBlank(assessmentExamine.getPid())){
            assessmentExamine.setPid("0");
        }
        return this.save(assessmentExamine);
    }

    /**
     * 修改考核指标
     * 
     * @param assessmentExamine 考核指标
     * @return 结果
     */
    @Override
    public boolean updateAssessmentExamine(AssessmentExamine assessmentExamine) {
        return this.updateById(assessmentExamine);
    }

    /**
     * 删除考核指标信息
     * 
     * @param id 考核指标ID
     * @return 结果
     */
    @Override
    public boolean deleteAssessmentExamine(String id) {
        return this.removeById(id);
    }

    /**
     * 查询考核指标
     *
     * @param id 考核指标ID
     * @return 考核指标
     */
    @Override
    public AssessmentExamine findAssessmentExamineById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询考核指标列表
     *
     * @param assessmentExamine 考核指标
     * @return 考核指标
     */
    @Override
    public List<AssessmentExamine> findAssessmentExamineList(AssessmentExamine assessmentExamine) {
        LambdaQueryWrapper<AssessmentExamine> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AssessmentExamine::getExamineId,assessmentExamine.getExamineId())
                .ne(AssessmentExamine::getLastStage,1);
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询考核指标
     *
     * @param page 分页参数
     * @param assessmentExamine 考核指标
     * @return 考核指标
     */
    @Override
    public PageT<AssessmentExamineVo> findAssessmentExaminePage(PageT<AssessmentExamine> page, AssessmentExamine assessmentExamine) {
        PageT<AssessmentExamineVo> pageT=new PageT<>();
        //先查询最末节点ID
        LambdaQueryWrapper<AssessmentExamine> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AssessmentExamine::getExamineId,assessmentExamine.getExamineId());
        List<AssessmentExamine> examines1 = this.baseMapper.selectList(lambdaQueryWrapper);
        if (examines1.size()>0) {
            //变成树
            TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
            treeNodeConfig.setChildrenKey("next");
            treeNodeConfig.setParentIdKey("pid");
            List<Tree<String>> trees = TreeUtil.build(examines1, "0", treeNodeConfig,
                    (treeNode, tree) -> {
                        tree.setId(treeNode.getId());
                        tree.setParentId(treeNode.getPid());
                        tree.setName(treeNode.getName());
                        tree.putExtra("lastStage", treeNode.getLastStage());
                        tree.putExtra("content", treeNode.getContent());
                        tree.putExtra("post",treeNode.getPost());
                        tree.putExtra("assessmentDepartment", treeNode.getAssessmentDepartment());
                        if (treeNode.getLastStage()) {
                            List<CharSequence> parentsName = tree.getParentsName(treeNode.getId(), false);
                            for (int i = 1; i <= parentsName.size(); i++) {
                                tree.putExtra("level" + i, parentsName.get(i));
                            }
                        }
                    });
            //扁平化取最后叶子节点
            List<Tree<String>> chindList = UtilTree.flatten(trees);
            List<String> id = chindList.stream().map(e -> Convert.toStr(e.get("id"))).distinct().collect(Collectors.toList());
            //在分页
            LambdaQueryWrapper<AssessmentExamine> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(AssessmentExamine::getId,id)
                    .like(StrUtil.isNotBlank(assessmentExamine.getName()),AssessmentExamine::getName,assessmentExamine.getName())
                    .eq(StrUtil.isNotBlank(assessmentExamine.getAssessmentDepartment()),AssessmentExamine::getAssessmentDepartment,assessmentExamine.getAssessmentDepartment())
                    .orderByAsc(AssessmentExamine::getCreateTime);
            PageT<AssessmentExamine> page1 = this.page(page, queryWrapper);

            List<AssessmentExamine> records = page1.getRecords();
            if (records.size() > 0) {
                //扁平化
                List<Tree<String>> flatten = new ArrayList<>();
                records.stream().forEach(e -> {
                    Tree<String> node = new Tree<>();
                    //根据当前ID查询所有的父节点
                    List<AssessmentExamine> examines = this.findParentment(e, new ArrayList<AssessmentExamine>());
                    Collections.reverse(examines);
                    for (int i = 0; i < 4; i++) {
                        if (examines.size()>i) {
                            node.put("level" + i, examines.get(i).getName());
                            node.put("id" + i, examines.get(i).getId());
                        }else{
                            node.put("level" + i,"");
                            node.put("id" + i,"");
                        }
                    }
                    node.putExtra("post",e.getPost());
                    node.put("createTime",e.getCreateTime());
                    node.put("content", e.getContent());
                    node.put("assessmentDepartment", e.getAssessmentDepartment());
                    node.put("lastStage", e.getLastStage());
                    flatten.add(node);
                });
                //排序
                List<AssessmentExamineVo> collect = flatten.stream().map(e -> {
                    AssessmentExamineVo examineVo = new AssessmentExamineVo();
                    examineVo.setLevel0(Convert.toStr(e.get("level0")));
                    examineVo.setLevel1(Convert.toStr(e.get("level1")));
                    examineVo.setLevel2(Convert.toStr(e.get("level2")));
                    examineVo.setLevel3(Convert.toStr(e.get("level3")));
                    examineVo.setId0(Convert.toStr(e.get("id0")));
                    examineVo.setId1(Convert.toStr(e.get("id1")));
                    examineVo.setId2(Convert.toStr(e.get("id2")));
                    examineVo.setId3(Convert.toStr(e.get("id3")));
                    examineVo.setCreateTime(Convert.toStr(e.get("createTime")));
                    examineVo.setAssessmentDepartment(Convert.toStr(e.get("assessmentDepartment")));
                    examineVo.setPost(Convert.toStr(e.get("post")));
                    //部门
                    Long orgId = Convert.toLong(e.get("assessmentDepartment"));
                    if (orgId!=null){
                        SysOrg assessmentDepartment = remoteOrgService.getById(orgId, AuthConstants.FROM_IN).getData();
                        examineVo.setAssessmentDepartmentName(assessmentDepartment.getName());
                    }
                    //岗位
                    if (StrUtil.isNotBlank(Convert.toStr(e.get("post")))){
                        SysPositionDTO post = remotePositionService.getInfo(Convert.toStr(e.get("post")), AuthConstants.FROM_IN).getData();
                        examineVo.setPostName(post.getPositionName());
                    }
                    examineVo.setContent(Convert.toStr(e.get("content")));
                    examineVo.setLastStage(Convert.toBool(e.get("lastStage")));
                    return examineVo;
                }).collect(Collectors.toList());

                //排序
                List<AssessmentExamineVo> collect1 = collect.stream().sorted(Comparator.comparing(AssessmentExamineVo::getId0).thenComparing(AssessmentExamineVo::getId1).thenComparing(AssessmentExamineVo::getId2).thenComparing(AssessmentExamineVo::getId3).thenComparing(AssessmentExamineVo::getCreateTime)).collect(Collectors.toList());
                pageT.setRecords(collect1);
                pageT.setPages(page1.getPages());
                pageT.setCurrent(page1.getCurrent());
                pageT.setOrders(page1.getOrders());
                pageT.setSize(page1.getSize());
                pageT.setTotal(page1.getTotal());
                return pageT;

            }
        }
        return pageT;
    }

    @Override
    public int deleteByLastId(String id) {
        //查询当前id判断是否顶级
        AssessmentExamine assessmentExamine = this.baseMapper.selectById(id);
        if (ObjectUtil.isNotEmpty(assessmentExamine)&& "0".equals(assessmentExamine.getPid())){
            //直接删除
         return this.baseMapper.deleteById(id);
        }
            //逐级删除
        return  deleteByParement(assessmentExamine);


    }

    @Override
    public boolean conference(AssessmentExamine assessmentExamine) {
        //根据表ID查询考核指标
        List<AssessmentExamineVo>  examineVoList= analyticalTable(assessmentExamine.getId());

        if (CollUtil.isEmpty(examineVoList)){
            return false;
        }
        //修改状态为已发布
        Examine examine=new Examine();
        examine.setId(assessmentExamine.getId());
        examine.setReleaseStatus(assessmentExamine.getReleaseStatus());
        boolean i = this.examineService.updateById(examine);


        //查询当前所有区域的
        List<AreaDTO> data = remoteAreaService.findAreaByType("3", AuthConstants.FROM_IN).getData();
        List<AssessmentScore> add=new ArrayList<AssessmentScore>();
        data.stream().forEach(e->{
            examineVoList.forEach(f->{
                AssessmentScore assessmentScore=new AssessmentScore();
                //指标表
                assessmentScore.setExamineId(assessmentExamine.getId());
                //一级指标
                assessmentScore.setFirstOrder(f.getLevel0());
                assessmentScore.setSecondaryOrder(f.getLevel1());
                assessmentScore.setThreeOrder(f.getLevel2());
                assessmentScore.setFourOrder(f.getLevel3());
                assessmentScore.setContent(f.getContent());
                assessmentScore.setConfirm("0");
                assessmentScore.setComplete("0");
                assessmentScore.setYearly(this.examineService.getById(assessmentExamine.getId()).getYearly());
                //被考核区域
                assessmentScore.setAreaId(e.getId());
                //考核部门
                assessmentScore.setAssessmentDepartment(f.getAssessmentDepartment());
                //考核岗位
                assessmentScore.setPost(f.getPost());
                //考核人
                //根据岗位查询人
                /* UserBaseInfoVO userDataVO = new UserBaseInfoVO();
                userDataVO.setOrgId(Convert.toLong(f.getAssessmentDepartment()));
                userDataVO.setPositionId(f.getPost());
               UserBaseInfoVO userBaseInfoVO = remoteUserService.getUserByOrgId(userDataVO, AuthConstants.FROM_IN).getData();
                assessmentScore.setUserId(userBaseInfoVO.getId());*/
                add.add(assessmentScore);
            });
        });
        //添加到被考核表
        this.assessmentScoreService.saveBatch(add);
        return i;
    }

    private List<AssessmentExamineVo> analyticalTable(String ex) {
        //先查询最末一级
        LambdaQueryWrapper<AssessmentExamine> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AssessmentExamine::getExamineId,ex);
        List<AssessmentExamine> examines1 = this.baseMapper.selectList(lambdaQueryWrapper);
        if (examines1.size()>0) {
            //变成树
            TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
            treeNodeConfig.setChildrenKey("next");
            treeNodeConfig.setParentIdKey("pid");
            List<Tree<String>> trees = TreeUtil.build(examines1, "0", treeNodeConfig,
                    (treeNode, tree) -> {
                        tree.setId(treeNode.getId());
                        tree.setParentId(treeNode.getPid());
                        tree.setName(treeNode.getName());
                        tree.putExtra("examineId",treeNode.getExamineId());
                        tree.putExtra("lastStage", treeNode.getLastStage());
                        tree.putExtra("content", treeNode.getContent());
                        tree.putExtra("post",treeNode.getPost());
                        tree.putExtra("assessmentDepartment", treeNode.getAssessmentDepartment());
                        if (treeNode.getLastStage()) {
                            List<CharSequence> parentsName = tree.getParentsName(treeNode.getId(), false);
                            for (int i = 1; i <= parentsName.size(); i++) {
                                tree.putExtra("level" + i, parentsName.get(i));
                            }
                        }
                    });
            //扁平化取最后叶子节点
            List<Tree<String>> chindList = UtilTree.flatten(trees);
            List<String> id = chindList.stream().map(e -> Convert.toStr(e.get("id"))).distinct().collect(Collectors.toList());
            //在分页
            LambdaQueryWrapper<AssessmentExamine> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(AssessmentExamine::getId,id)
                    .orderByAsc(AssessmentExamine::getCreateTime);

            List<AssessmentExamine> records = this.baseMapper.selectList(queryWrapper);
            if (records.size() > 0) {
                //扁平化
                List<Tree<String>> flatten = new ArrayList<>();
                records.stream().forEach(e -> {
                    Tree<String> node = new Tree<>();
                    //根据当前ID查询所有的父节点
                    List<AssessmentExamine> examines = this.findParentment(e, new ArrayList<AssessmentExamine>());
                    Collections.reverse(examines);
                    for (int i = 0; i < 4; i++) {
                        if (examines.size() > i) {
                            node.put("level" + i, examines.get(i).getName());
                            node.put("id" + i, examines.get(i).getId());
                        } else {
                            node.put("level" + i, "");
                            node.put("id" + i, "");
                        }
                    }
                    node.put("createTime", e.getCreateTime());
                    node.put("content", e.getContent());
                    node.put("assessmentDepartment", e.getAssessmentDepartment());
                    node.put("lastStage", e.getLastStage());
                    node.put("post",e.getPost());
                    flatten.add(node);
                });
                //排序
                List<AssessmentExamineVo> collect = flatten.stream().map(e -> {
                    AssessmentExamineVo examineVo = new AssessmentExamineVo();
                    examineVo.setLevel0(Convert.toStr(e.get("level0")));
                    examineVo.setLevel1(Convert.toStr(e.get("level1")));
                    examineVo.setLevel2(Convert.toStr(e.get("level2")));
                    examineVo.setLevel3(Convert.toStr(e.get("level3")));
                    examineVo.setId0(Convert.toStr(e.get("id0")));
                    examineVo.setId1(Convert.toStr(e.get("id1")));
                    examineVo.setId2(Convert.toStr(e.get("id2")));
                    examineVo.setId3(Convert.toStr(e.get("id3")));
                    examineVo.setCreateTime(Convert.toStr(e.get("createTime")));
                    examineVo.setAssessmentDepartment(Convert.toStr(e.get("assessmentDepartment")));
                    examineVo.setContent(Convert.toStr(e.get("content")));
                    examineVo.setLastStage(Convert.toBool(e.get("lastStage")));
                    examineVo.setPost(Convert.toStr(e.get("post")));
                    return examineVo;
                }).collect(Collectors.toList());
                //排序
                List<AssessmentExamineVo> collect1 = collect.stream().sorted(Comparator.comparing(AssessmentExamineVo::getId0).thenComparing(AssessmentExamineVo::getId1).thenComparing(AssessmentExamineVo::getId2).thenComparing(AssessmentExamineVo::getId3).thenComparing(AssessmentExamineVo::getCreateTime)).collect(Collectors.toList());
                return collect1;
            }
        }
        return null;
    }

    private int deleteByParement(AssessmentExamine assessmentExamine) {
        //先删除当前的
        int i = this.baseMapper.deleteById(assessmentExamine.getId());
        //循环查询上级是否有其他的
        LambdaQueryWrapper<AssessmentExamine> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(AssessmentExamine::getPid,assessmentExamine.getPid()).ne(AssessmentExamine::getId,assessmentExamine.getId());
        List<AssessmentExamine> examines = this.baseMapper.selectList(queryWrapper);
        if (examines.size()<1){
            deleteByParement(this.baseMapper.selectById(assessmentExamine.getPid()));
        }
        return i;
    }

    /**
     * 根据当前最后一个指标查询所有的父类ID
     * @param e
     * @param assessmentExamines
     * @return
     */
    //根据当前id查询所有的父类
    private List<AssessmentExamine> findParentment(AssessmentExamine e,List<AssessmentExamine> assessmentExamines) {
        //先加
        assessmentExamines.add(e);
        if (!"0".equals(e.getPid())){
            AssessmentExamine assessmentExamine = this.baseMapper.selectById(e.getPid());
            findParentment(assessmentExamine,assessmentExamines);
        }
        return assessmentExamines;
    }
}
