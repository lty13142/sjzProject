package com.sjz.evaluations.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.api.dto.res.UserAccountDTO;
import com.crcm.admin.api.feign.RemoteUserService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.response.RestResult;
import com.sjz.evaluations.constants.ExamineConstant;
import com.sjz.evaluations.mapper.ExamineIndicatorsMapper;
import com.sjz.evaluations.model.dto.ExamineIndicatorsSaveDTO;
import com.sjz.evaluations.model.entity.Examine;
import com.sjz.evaluations.model.entity.ExamineIndicators;
import com.sjz.evaluations.model.entity.RegionExamine;
import com.sjz.evaluations.model.entity.VillageExamine;
import com.sjz.evaluations.model.vo.RegionIndicatorsVO;
import com.sjz.evaluations.model.vo.VillageIndicatorsVO;
import com.sjz.evaluations.service.ExamineIndicatorsService;
import com.sjz.evaluations.service.RegionExamineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 考核指标Service业务层处理
 *
 * @author zzyt
 * @date 2023-04-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExamineIndicatorsServiceImpl extends ServiceImpl<ExamineIndicatorsMapper, ExamineIndicators> implements ExamineIndicatorsService {

    @Resource
    private RemoteUserService remoteUserService;

    @Resource
    private RegionExamineService regionExamineService;

    @Resource
    private VillageExamineServiceImpl villageExamineService;

    /**
     * 新增考核指标
     *
     * @param examineIndicatorsSaveDTO 考核指标
     * @return 结果
     */
    @Override
    public boolean saveExamineIndicators(ExamineIndicatorsSaveDTO examineIndicatorsSaveDTO) {
        boolean saveFlag = this.save(examineIndicatorsSaveDTO);

        //组装参数
        examineIndicatorsSaveDTO.getRegionExamineList().forEach(region ->
                region.setIndicatorsId(examineIndicatorsSaveDTO.getId())
                        .setStatus(ExamineConstant.DISTRIBUTED)
        );
        //新增管区考核实体
        regionExamineService.saveBatch(examineIndicatorsSaveDTO.getRegionExamineList());
        //新增考核指标
        return saveFlag;
    }

    /**
     * 修改考核指标
     *
     * @param examineIndicatorsSaveDTO 考核指标
     * @return 结果
     */
    @Override
    public boolean updateExamineIndicators(ExamineIndicatorsSaveDTO examineIndicatorsSaveDTO) {
        //查询原数据
        ExamineIndicators examineIndicators = this.getById(examineIndicatorsSaveDTO.getId());
        //判断类型是否从"是"切换到"否"
        if (Objects.equals(ExamineConstant.QUANTIFY_INDICATORS, examineIndicators.getType()) &&
                Objects.equals(ExamineConstant.QUALITATIVE_INDICATORS, examineIndicatorsSaveDTO.getType())) {
            //从定量切换到定性则清空目标和单位字段
            examineIndicatorsSaveDTO.getRegionExamineList().forEach(regionExamine ->
                    regionExamine.setTarget("")
                            .setUnit(""));
        }
        //批量修改管区考核
        regionExamineService.updateBatchById(examineIndicatorsSaveDTO.getRegionExamineList());
        // 批量修改村级考核
        List<VillageExamine> villageExamineListResult = new ArrayList<>();
        if (examineIndicatorsSaveDTO.getRegionExamineList().size() > 0){
            for (RegionExamine regionExamine : examineIndicatorsSaveDTO.getRegionExamineList()) {
                List<VillageExamine> villageList = villageExamineService.selectByIndicatorIdAndRegionId(regionExamine);
                if (villageList.size() > 0){
                    villageList.stream().forEach(villageExamine -> {
                        //判断类型是否从"是"切换到"否"
                        if (Objects.equals(ExamineConstant.QUANTIFY_INDICATORS, examineIndicators.getType()) &&
                                Objects.equals(ExamineConstant.QUALITATIVE_INDICATORS, examineIndicatorsSaveDTO.getType())){
                            villageExamine.setUnit("");
                        }else {
                            villageExamine.setUnit(regionExamine.getUnit());
                        }
                    });
                    villageExamineListResult.addAll(villageList);
                }
            }
        }
        villageExamineService.updateBatchById(villageExamineListResult);
        //修改考核指标
        return this.updateById(examineIndicatorsSaveDTO);
    }

    /**
     * 删除考核指标信息
     *
     * @param id 考核指标ID
     * @return 结果
     */
    @Override
    public boolean deleteExamineIndicators(String id) {
        return this.removeById(id);
    }

    /**
     * 查询考核指标
     *
     * @param id 考核指标ID
     * @return 考核指标
     */
    @Override
    public ExamineIndicators findExamineIndicatorsById(String id) {
        //根据id查询指标
        ExamineIndicatorsSaveDTO examineIndicators = this.baseMapper.findExamineIndicatorsById(id);

        //查询当前指标下的所有管区考核
        List<RegionExamine> regionExamineList = regionExamineService.findRegionExamineList(new RegionExamine().setIndicatorsId(examineIndicators.getId()));
        return examineIndicators.setRegionExamineList(regionExamineList);
    }

    /**
     * 查询考核指标列表
     *
     * @param examineIndicators 考核指标
     * @return 考核指标
     */
    @Override
    public List<ExamineIndicators> findExamineIndicatorsList(ExamineIndicators examineIndicators) {
        LambdaQueryWrapper<ExamineIndicators> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询考核指标
     *
     * @param page              分页参数
     * @param examineIndicators 考核指标
     * @return 考核指标
     */
    @Override
    public IPage<ExamineIndicators> findExamineIndicatorsPage(PageT page, ExamineIndicators examineIndicators) {
        return this.page(
                page, Wrappers.lambdaQuery(ExamineIndicators.class)
                        .eq(!StringUtils.isBlank(examineIndicators.getExamineId()), ExamineIndicators::getExamineId, examineIndicators.getExamineId())
                        //指标名称
                        .like(!StringUtils.isBlank(examineIndicators.getIndicatorsName()), ExamineIndicators::getIndicatorsName, examineIndicators.getIndicatorsName())
                        //负责人
                        .like(!StringUtils.isBlank(examineIndicators.getNikeName()), ExamineIndicators::getNikeName, examineIndicators.getNikeName())
                        //负责部门
                        .like(!ObjectUtils.isNull(examineIndicators.getOrgId()), ExamineIndicators::getOrgId, examineIndicators.getOrgId())
                        //是否定量指标
                        .eq(!ObjectUtils.isNull(examineIndicators.getType()), ExamineIndicators::getType, examineIndicators.getType())
                        //创建时间倒叙
                        .orderByDesc(ExamineIndicators::getCreateTime)
        );
    }

    /**
     * 分页查询管区考核指标
     *
     * @param page              分页参数
     * @param examineIndicators 考核指标
     * @return 管区考核指标集合
     */
    @Override
    public IPage<RegionIndicatorsVO> findRegionIndicatorsPage(PageT page, ExamineIndicators examineIndicators) {
        //查询当前登陆人的区域代码
        RestResult<UserAccountDTO> restResult = remoteUserService.getByUsername(SecurityUtil.getCurrentUser().getUsername(), AuthConstants.FROM_IN);
        UserAccountDTO user = restResult.getData();
        //分页查询管区考核指标
        return this.baseMapper.findRegionIndicatorsPage(page, examineIndicators, user.getSelectedCode());
    }

    /**
     * 分页查询村级考核指标
     *
     * @param page              分页参数
     * @param examineIndicators 考核指标
     * @return 村级考核指标集合
     */
    @Override
    public IPage<VillageIndicatorsVO> findVillageIndicatorsPage(PageT page, ExamineIndicators examineIndicators) {
        //查询当前登陆人的区域代码
        RestResult<UserAccountDTO> restResult = remoteUserService.getByUsername(SecurityUtil.getCurrentUser().getUsername(), AuthConstants.FROM_IN);
        UserAccountDTO user = restResult.getData();
        //分页查询管区考核指标
        return this.baseMapper.findVillageIndicatorsPage(page, examineIndicators, user.getSelectedCode());
    }
}
