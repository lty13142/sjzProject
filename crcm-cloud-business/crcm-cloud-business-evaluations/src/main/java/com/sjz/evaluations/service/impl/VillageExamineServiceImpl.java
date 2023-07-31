package com.sjz.evaluations.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.api.dto.res.AreaDTO;
import com.crcm.admin.api.feign.RemoteAreaService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.SystemConstant;
import com.sjz.evaluations.constants.ExamineConstant;
import com.sjz.evaluations.mapper.ExamineIndicatorsMapper;
import com.sjz.evaluations.mapper.VillageExamineMapper;
import com.sjz.evaluations.model.dto.ExamineIndicatorsSaveDTO;
import com.sjz.evaluations.model.dto.VillageExamineSaveDTO;
import com.sjz.evaluations.model.entity.RegionExamine;
import com.sjz.evaluations.model.entity.VillageExamine;
import com.sjz.evaluations.model.vo.AssessmentStatics;
import com.sjz.evaluations.model.vo.VillageIndicatorsVO;
import com.sjz.evaluations.service.VillageExamineService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 村级考核实体Service业务层处理
 *
 * @author zzyt
 * @date 2023-04-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VillageExamineServiceImpl extends ServiceImpl<VillageExamineMapper, VillageExamine> implements VillageExamineService {

    @Autowired
    private RemoteAreaService remoteAreaService;

    @Resource
    private ExamineIndicatorsMapper examineIndicatorsMapper;

    /**
     * 新增村级考核实体
     *
     * @param VillageExamine 村级考核实体
     * @return 结果
     */
    @Override
    public boolean saveVillageExamine(VillageExamine VillageExamine) {
        return this.save(VillageExamine);
    }

    /**
     * 批量新增村级考核实体
     *
     * @param villageExamineSaveDTO 村级考核实体
     * @return 结果
     */
    @Override
    public boolean saveVillageExamineBatch(VillageExamineSaveDTO villageExamineSaveDTO) {
        //设置新增参数
        villageExamineSaveDTO.getVillageExamineList().forEach(villageExamine -> {
                    villageExamine.setCompleteStatus(ExamineConstant.INCOMPLETE)
                            .setStatus(ExamineConstant.PENDING_FEEDBACK)
                            .setUnit(villageExamineSaveDTO.getUnit())
                            .setIndicatorsId(villageExamineSaveDTO.getIndicatorsId())
                            .setRegionExamineId(villageExamineSaveDTO.getRegionExamineId());
                    if (StringUtils.isBlank(villageExamine.getTarget())) {
                        villageExamine.setTarget("0");
                    }
                }
        );
        return this.saveBatch(villageExamineSaveDTO.getVillageExamineList());
    }

    /**
     * 修改村级考核实体
     *
     * @param villageExamineSaveDTO 村级考核实体
     * @return 结果
     */
    @Override
    public boolean updateVillageExamine(VillageExamineSaveDTO villageExamineSaveDTO) {
        //存在则批量修改
        if (!CollectionUtils.isEmpty(villageExamineSaveDTO.getVillageExamineList())) {
            //设置修改参数
            villageExamineSaveDTO.getVillageExamineList().forEach(villageExamine -> {
                        villageExamine.setUnit(villageExamineSaveDTO.getUnit());
                    }
            );
            this.updateBatchById(villageExamineSaveDTO.getVillageExamineList());
        }
        //修改村级
        return this.updateById(villageExamineSaveDTO);
    }

    /**
     * 删除村级考核实体信息
     *
     * @param id 村级考核实体ID
     * @return 结果
     */
    @Override
    public boolean deleteVillageExamine(String id) {
        return this.removeById(id);
    }

    /**
     * 查询村级考核实体
     *
     * @param id 村级考核实体ID
     * @return 村级考核实体
     */
    @Override
    public VillageIndicatorsVO findVillageExamineById(String id) {
        //根据id查询村级
        VillageIndicatorsVO villageIndicatorsVO = this.baseMapper.findVillageExamineById(id);
        //查询村级考核实体所属的指标信息
        ExamineIndicatorsSaveDTO examineIndicators = examineIndicatorsMapper.findExamineIndicatorsById(villageIndicatorsVO.getIndicatorsId());
        //组装参数
        villageIndicatorsVO.setIndicatorsName(examineIndicators.getIndicatorsName())
                .setIndicatorsContent(examineIndicators.getIndicatorsContent())
                .setAttr(examineIndicators.getAttr())
                .setAttrName(examineIndicators.getAttrName())
                .setOrgId(examineIndicators.getOrgId())
                .setOrgName(examineIndicators.getOrgName())
                .setNikeName(examineIndicators.getNikeName())
                .setType(examineIndicators.getType());

        return villageIndicatorsVO;
    }

    /**
     * 查询村级考核实体列表
     *
     * @param VillageExamine 村级考核实体
     * @return 村级考核实体
     */
    @Override
    public List<VillageExamine> findVillageExamineList(VillageExamine VillageExamine) {
        LambdaQueryWrapper<VillageExamine> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询村级考核实体
     *
     * @param page           分页参数
     * @param VillageExamine 村级考核实体
     * @return 村级考核实体
     */
    @Override
    public IPage<VillageExamine> findVillageExaminePage(PageT page, VillageExamine VillageExamine) {
        LambdaQueryWrapper<VillageExamine> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }

    /**
     * 根据指标id和管区考核id查询村级考核列表
     *
     * @param indicatorsId 指标id
     * @param regionId     管区id
     * @return 村级考核列表
     */
    @Override
    public List<VillageExamine> findVillageByRegionId(String indicatorsId, String regionId) {
        return this.baseMapper.selectList(
                Wrappers.lambdaQuery(VillageExamine.class)
                        .eq(VillageExamine::getIndicatorsId, indicatorsId)
                        .eq(VillageExamine::getRegionExamineId, regionId)
                        .orderByAsc(VillageExamine::getVillageAreaCode)
        );
    }


    /**
     * 管区提交对村级的打分
     *
     * @param villageExamine 打分信息
     * @return 结果
     */
    @Override
    public boolean submitRegionScore(VillageExamine villageExamine) {
        //修改打分和打分说明
        return this.update(
                Wrappers.lambdaUpdate(VillageExamine.class)
                        .set(VillageExamine::getScore, villageExamine.getScore())
                        .set(VillageExamine::getScoreContent, villageExamine.getScoreContent())
                        .set(VillageExamine::getStatus,ExamineConstant.RATED)
                        .set(VillageExamine::getVerifyCompleteStatus,villageExamine.getVerifyCompleteStatus())
                        .eq(VillageExamine::getId, villageExamine.getId())
        );
    }

    /**
     * 镇级提交对管区的核实
     *
     * @param villageExamine 打分信息
     * @return 结果
     */
    @Override
    public boolean submitTownScore(VillageExamine villageExamine) {
        //修改打分和打分说明
        return this.update(
                Wrappers.lambdaUpdate(VillageExamine.class)
                        .set(VillageExamine::getScore, villageExamine.getScore())
                        .set(VillageExamine::getScoreContent, villageExamine.getScoreContent())
                        .set(VillageExamine::getStatus,ExamineConstant.VILLAGE_SUCCESS)
                        .set(VillageExamine::getVerifyCompleteStatus,villageExamine.getVerifyCompleteStatus())
                        .eq(VillageExamine::getId, villageExamine.getId())
        );
    }

    @Override
    public List<AssessmentStatics> assessmentStatics() {
        List<AssessmentStatics> resultList = new ArrayList<>();
        //获取所有管区
        List<AreaDTO> data = remoteAreaService.findAreaByType("2", AuthConstants.FROM_IN).getData();
        //获取当前年份
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        //获取当前年份下所有村子的指标任务
        List<VillageExamine> allVillageExamine = this.baseMapper.getAllVillageExamine(year);
        //指标任务按照村庄code分组
        Map<String, List<VillageExamine>> collectCode = allVillageExamine.stream().collect(Collectors.groupingBy(s -> s.getVillageAreaCode()));
        for(AreaDTO areaDTO : data){
            int sum = 0;
            int compeleteNum = 0;
            AssessmentStatics assessmentStatics = new AssessmentStatics();
            //获取所有管区下的村子
            List<AreaDTO> areaList = remoteAreaService.getList(areaDTO.getId(), AuthConstants.FROM_IN).getData();
            List<String> codes = areaList.stream().map(s -> s.getCode()).collect(Collectors.toList());
            //管区名称
            assessmentStatics.setAreaName(areaDTO.getName());
            //管区下的村子数量
            assessmentStatics.setVillagerNum(areaList.size());
            for(String code : codes){
                if(collectCode.keySet().contains(code)){
                    List<VillageExamine> villageExamines = collectCode.get(code);
                    sum += villageExamines.size();
                    for(VillageExamine villageExamine : villageExamines){
                        if(!ObjectUtils.isEmpty(villageExamine.getVerifyCompleteStatus()) && 1 == villageExamine.getVerifyCompleteStatus()){
                            compeleteNum += 1;
                        }
                    }
                }
            }
            if(0 != sum){
                BigDecimal b1 = new BigDecimal(compeleteNum);
                BigDecimal b2 = new BigDecimal(sum);
                assessmentStatics.setFinishPer(b1.multiply(BigDecimal.valueOf(100).divide(b2,2,BigDecimal.ROUND_HALF_UP)) + "%");
            } else {
                assessmentStatics.setFinishPer("0.00%");
            }
            resultList.add(assessmentStatics);
        }
        return resultList;
    }

    @Override
    public List<VillageExamine> selectByIndicatorIdAndRegionId(RegionExamine regionExamine) {
        LambdaQueryWrapper<VillageExamine> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(regionExamine.getIndicatorsId()),VillageExamine::getIndicatorsId,regionExamine.getIndicatorsId())
                    .eq(StringUtils.isNotBlank(regionExamine.getId()),VillageExamine::getRegionExamineId,regionExamine.getId())
                    .ne(VillageExamine::getStatus,ExamineConstant.VILLAGE_SUCCESS);
        return this.list(queryWrapper);
    }
}
