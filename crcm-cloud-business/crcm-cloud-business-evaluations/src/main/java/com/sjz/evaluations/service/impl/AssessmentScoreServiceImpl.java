package com.sjz.evaluations.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.api.dto.res.AreaDTO;
import com.crcm.admin.api.dto.res.SysOrg;
import com.crcm.admin.api.dto.res.SysPositionDTO;
import com.crcm.admin.api.dto.res.UserBaseInfoVO;
import com.crcm.admin.api.feign.RemoteAreaService;
import com.crcm.admin.api.feign.RemoteOrgService;
import com.crcm.admin.api.feign.RemotePositionService;
import com.crcm.admin.api.feign.RemoteUserService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.sso.domain.AuthUser;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.response.RestResult;
import com.sjz.evaluations.mapper.AssessmentScoreMapper;
import com.sjz.evaluations.model.entity.AssessmentScore;
import com.sjz.evaluations.model.vo.AreaVo;
import com.sjz.evaluations.model.vo.AssessmentExamineVo;
import com.sjz.evaluations.model.vo.AssessmentStatics;
import com.sjz.evaluations.model.vo.scoreVo;
import com.sjz.evaluations.service.AssessmentScoreService;
import com.sjz.evaluations.util.UtilSysArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 考核人员分数确认Service业务层处理
 * 
 * @author guozhilin
 * @date 2023-04-09
 */
@Service
public class AssessmentScoreServiceImpl extends ServiceImpl<AssessmentScoreMapper, AssessmentScore> implements AssessmentScoreService {

    @Autowired
    private RemotePositionService remotePositionService;

    @Autowired
    private RemoteOrgService remoteOrgService;
    @Autowired
    private RemoteAreaService remoteAreaService;

    @Autowired
    private RemoteUserService remoteUserService;
    

    /**
     * 新增考核人员分数确认
     * 
     * @param assessmentScore 考核人员分数确认
     * @return 结果
     */
    @Override
    public boolean saveAssessmentScore(AssessmentScore assessmentScore) {
        return this.save(assessmentScore);
    }

    /**
     * 修改考核人员分数确认
     * 
     * @param assessmentScore 考核人员分数确认
     * @return 结果
     */
    @Override
    public int updateAssessmentScore(AssessmentScore assessmentScore) {
        if (StrUtil.isNotBlank(assessmentScore.getConfirm())){
            //查询当前村庄的ID
            AuthUser currentUser = SecurityUtil.getCurrentUser();
            AreaDTO areaDTO = remoteAreaService.getInfoByCode(assessmentScore.getVillageCode(), AuthConstants.FROM_IN).getData();
            assessmentScore.setAreaId(areaDTO.getId());
        }
        if(StrUtil.isNotBlank(assessmentScore.getComplete())){
            UserBaseInfoVO data = remoteUserService.getUserBaseInfo(SecurityUtil.getCurrentUser().getUserId(), AuthConstants.FROM_IN).getData();
            assessmentScore.setAssessmentDepartment(Convert.toStr(data.getOrgId()));
        }
        LambdaUpdateWrapper<AssessmentScore> updateWrapper=new LambdaUpdateWrapper<>();
        //修改确认
        updateWrapper.set(StrUtil.isNotBlank(assessmentScore.getComplete()),AssessmentScore::getComplete,assessmentScore.getComplete())
                .set(StrUtil.isNotBlank(assessmentScore.getConfirm()),AssessmentScore::getConfirm,assessmentScore.getConfirm())
                .eq(StrUtil.isNotBlank(assessmentScore.getAreaId()),AssessmentScore::getAreaId,assessmentScore.getAreaId())
                .eq(StrUtil.isNotBlank(assessmentScore.getAssessmentDepartment()),AssessmentScore::getAssessmentDepartment,assessmentScore.getAssessmentDepartment())
                .eq(StrUtil.isNotBlank(assessmentScore.getExamineId()),AssessmentScore::getExamineId,assessmentScore.getExamineId());
        return this.baseMapper.update(null,updateWrapper);
    }

    /**
     * 删除考核人员分数确认信息
     * 
     * @param id 考核人员分数确认ID
     * @return 结果
     */
    @Override
    public boolean deleteAssessmentScore(String id) {
        return this.removeById(id);
    }

    /**
     * 查询考核人员分数确认
     *
     * @param id 考核人员分数确认ID
     * @return 考核人员分数确认
     */
    @Override
    public AssessmentScore findAssessmentScoreById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询考核人员分数确认列表
     *
     * @param assessmentScore 考核人员分数确认
     * @return 考核人员分数确认
     */
    @Override
    public List<AssessmentScore> findAssessmentScoreList(AssessmentScore assessmentScore) {
        LambdaQueryWrapper<AssessmentScore> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询考核人员分数确认
     *
     * @param page 分页参数
     * @param assessmentScore 考核人员分数确认
     * @return 考核人员分数确认
     */
    @Override
    public PageT<AssessmentScore> findAssessmentScorePage(PageT<AssessmentScore> page, AssessmentScore assessmentScore) {
        if (StrUtil.isNotBlank(assessmentScore.getVillageCode())){
            //查询村庄id
            //AreaDTO areaDTO = remoteAreaService.getInfoByCode(assessmentScore.getVillageCode(), AuthConstants.FROM_IN).getData();
            AreaVo villageByCode = UtilSysArea.getVillageByCode(assessmentScore.getVillageCode());
            assessmentScore.setAreaId(villageByCode.getId());
        }
        if ("0".equals(assessmentScore.getType())){
            UserBaseInfoVO data = remoteUserService.getUserBaseInfo(SecurityUtil.getCurrentUserId(), AuthConstants.FROM_IN).getData();
            assessmentScore.setAssessmentDepartment(Convert.toStr(data.getOrgId()));
            assessmentScore.setPost(data.getPositionId());
        }else{
            //UserBaseInfoVO data = remoteUserService.getUserBaseInfo(SecurityUtil.getCurrentUserId(), AuthConstants.FROM_IN).getData();
            //AreaDTO areaDTO = remoteAreaService.getInfoByCode(data.getVillageCode(), AuthConstants.FROM_IN).getData();
            AreaVo villageByCode = UtilSysArea.getVillageByCode(assessmentScore.getVillageCode());
            assessmentScore.setAreaId(villageByCode.getId());
        }
        LambdaQueryWrapper<AssessmentScore> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotBlank(assessmentScore.getAssessmentDepartment()),AssessmentScore::getAssessmentDepartment,assessmentScore.getAssessmentDepartment())
                .eq(StrUtil.isNotBlank(assessmentScore.getExamineId()),AssessmentScore::getExamineId,assessmentScore.getExamineId())
                .eq(StrUtil.isNotBlank(assessmentScore.getAreaId()),AssessmentScore::getAreaId,assessmentScore.getAreaId())
                .eq(StrUtil.isNotBlank(assessmentScore.getPost()),AssessmentScore::getPost,assessmentScore.getPost());
                //.orderByAsc(AssessmentScore::getAreaId,AssessmentScore::getFirstOrder,AssessmentScore::getSecondaryOrder,AssessmentScore::getThreeOrder,AssessmentScore::getFourOrder);
        PageT<AssessmentScore> pageT = this.page(page, queryWrapper);
        //查询
        List<AssessmentScore> records = pageT.getRecords();
        if (records.size()>0){
            records.stream().forEach(e->{
                AreaDTO data = remoteAreaService.getInfo(e.getAreaId(), AuthConstants.FROM_IN).getData();
                e.setAreaName(data.getName());
                e.setVillageName(data.getPname());

                Long orgId = Convert.toLong(e.getAssessmentDepartment());
                SysOrg assessmentDepartment = remoteOrgService.getById(orgId, AuthConstants.FROM_IN).getData();
                e.setAssessmentDepartmentName(assessmentDepartment.getName());
                //岗位
                SysPositionDTO post = remotePositionService.getInfo(Convert.toStr(e.getPost()), AuthConstants.FROM_IN).getData();
                e.setPostName(post.getPositionName());
            });
        }
        if ("1".equals(assessmentScore.getType())) {
            records = records.stream().sorted(Comparator.comparing(AssessmentScore::getVillageName).thenComparing(AssessmentScore::getAreaId).thenComparing(AssessmentScore::getFirstOrder).thenComparing(AssessmentScore::getSecondaryOrder).thenComparing(AssessmentScore::getThreeOrder).thenComparing(AssessmentScore::getFourOrder).thenComparing(AssessmentScore::getCreateTime)).collect(Collectors.toList());
        }
        pageT.setRecords(records);
        return pageT;
    }

    @Override
    public Boolean edit(AssessmentScore assessmentScore) {
        if (StrUtil.isNotBlank(assessmentScore.getScore())){
            assessmentScore.setUserId(SecurityUtil.getCurrentUserId());
        }
        return this.updateById(assessmentScore);
    }

    @Override
    public PageT<scoreVo> assessmentScorePage(PageT<AssessmentScore> page, AssessmentScore assessmentScore) {
      PageT<scoreVo> pageT= this.baseMapper.assessmentScorePage(page,assessmentScore);
        List<scoreVo> records = pageT.getRecords();
        if (records.size()>0){
            records.stream().forEach(e->{
                //根据id查询名称
                e.setId(e.getName());
                AreaVo data = UtilSysArea.getAreaList(SystemConstant.AREA_TYPE.VILLAGE, e.getName());
                if (ObjectUtil.isNotEmpty(data)) {
                    //AreaDTO data = remoteAreaService.getInfo(e.getName(), AuthConstants.FROM_IN).getData();
                    e.setName(data.getName());
                    e.setLatitude(data.getLatitude());
                    e.setLongitude(data.getLongitude());
                }
            });
        }
        return pageT;
    }

    @Override
    public List<AssessmentScore> lastStatistics() {
        int i = DateUtil.thisYear();
        List<AssessmentScore> list= this.baseMapper.lastStatistics(Convert.toStr(i));
        if (list.size()>0){
            list.stream().forEach(e->{
                //AreaDTO data = remoteAreaService.getInfo(e.getAreaId(), AuthConstants.FROM_IN).getData();
                AreaVo data = UtilSysArea.getAreaList(SystemConstant.AREA_TYPE.VILLAGE, e.getAreaId());
                if (ObjectUtil.isNotEmpty(data)) {
                    e.setAreaName(data.getName());
                }
            });
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> scoreCount(String areaId,String yearly) {
       List<Map<String,Object>> mapList= this.baseMapper.scoreCount(areaId,yearly);
        return mapList;
    }

    @Override
    public List<Map<String, Object>> indicatorCategoryStatistics(String yearly) {
        String s = Convert.toStr(DateUtil.thisYear());
        List<Map<String,Object>> mapList= this.baseMapper.indicatorCategoryStatistics(yearly);
        return mapList;
    }

    @Override
    public List<AssessmentStatics> assessmentStatics() {
        List<AssessmentStatics> resultList = new ArrayList<>();
        List<AssessmentScore> allList = new ArrayList<>();
        //获取所有村子当年的指标任务
        allList = this.baseMapper.getAllList();
        if(ObjectUtils.isEmpty(allList)){
        //获取所有村子去年的指标任务
            allList = this.baseMapper.getAllListLast();
        }
        if(!ObjectUtils.isEmpty(allList)){
            //获取所有管区
            List<AreaDTO> data = remoteAreaService.findAreaByType("2", AuthConstants.FROM_IN).getData();
            for(AreaDTO areaDTO : data){
                AssessmentStatics assessmentStatics = new AssessmentStatics();
                //获取所有管区下的村子
                List<AreaDTO> areaList = remoteAreaService.getList(areaDTO.getId(), AuthConstants.FROM_IN).getData();
                List<String> ids = areaList.stream().map(s -> s.getId()).collect(Collectors.toList());
                //将任务指标按照村子id分组
                Map<String, List<AssessmentScore>> collect = allList.stream().collect(Collectors.groupingBy(AssessmentScore::getAreaId));
                int sum = 0;
                int compeleteNum = 0;
                for(String id : ids){
                    List<AssessmentScore> assessmentScores = collect.get(id);
                    sum += assessmentScores.size();
                    compeleteNum += assessmentScores.stream().filter(s -> "1".equals(s.getComplete())).count();
                }
                assessmentStatics.setAreaName(areaDTO.getName());
                assessmentStatics.setVillagerNum(areaList.size());
                if(0 != sum){
                    BigDecimal b1 = new BigDecimal(compeleteNum);
                    BigDecimal b2 = new BigDecimal(sum);
                    assessmentStatics.setFinishPer(b1.divide(b2,2,BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)) + "%");
                } else {
                    assessmentStatics.setFinishPer("0.00%");
                }
                resultList.add(assessmentStatics);
            }
        }
        return resultList;
    }
}
