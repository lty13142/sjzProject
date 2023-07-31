package com.sjz.evaluations.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.api.dto.res.AreaDTO;
import com.crcm.admin.api.dto.res.UserAccountDTO;
import com.crcm.admin.api.feign.RemoteAreaService;
import com.crcm.admin.api.feign.RemoteUserService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.sso.domain.AuthUser;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.dto.TreeView;
import com.crcm.core.response.RestResult;
import com.sjz.evaluations.constants.ExamineConstant;
import com.sjz.evaluations.mapper.ExamineTaskMapper;
import com.sjz.evaluations.model.dto.ExamineResultDTO;
import com.sjz.evaluations.model.dto.ExamineScoreDTO;
import com.sjz.evaluations.model.dto.RegionScoreDTO;
import com.sjz.evaluations.model.dto.VillageScoreDTO;
import com.sjz.evaluations.model.entity.ExamineTask;
import com.sjz.evaluations.model.vo.*;
import com.sjz.evaluations.service.ExamineTaskService;
import io.netty.util.Constant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 考核Service业务层处理
 *
 * @author zzyt
 * @date 2023-04-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExamineTaskServiceImpl extends ServiceImpl<ExamineTaskMapper, ExamineTask> implements ExamineTaskService {


    @Resource
    private RemoteUserService remoteUserService;

    @Resource
    private RemoteAreaService remoteAreaService;

    /**
     * 新增考核
     *
     * @param examineTask 考核
     * @return 结果
     */
    @Override
    public boolean saveExamineTask(ExamineTask examineTask) {
        AuthUser currentUser = SecurityUtil.getCurrentUser();
        examineTask.setCreateBy(currentUser.getNickName());
        examineTask.setUpdateBy(currentUser.getNickName());
        return this.save(examineTask.setPublishDate(LocalDate.now()));
    }

    /**
     * 修改考核
     *
     * @param examineTask 考核
     * @return 结果
     */
    @Override
    public boolean updateExamineTask(ExamineTask examineTask) {
        return this.updateById(examineTask);
    }

    /**
     * 删除考核信息
     *
     * @param id 考核ID
     * @return 结果
     */
    @Override
    public boolean deleteExamineTask(String id) {
        return this.removeById(id);
    }

    /**
     * 查询考核
     *
     * @param id 考核ID
     * @return 考核
     */
    @Override
    public ExamineTask findExamineTaskById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询考核列表
     *
     * @param examineTask 考核
     * @return 考核
     */
    @Override
    public List<ExamineTask> findExamineTaskList(ExamineTask examineTask) {
        LambdaQueryWrapper<ExamineTask> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询考核
     *
     * @param page        分页参数
     * @param examineTask 考核
     * @return 考核
     */
    @Override
    public IPage<ExamineTask> findExamineTaskPage(PageT page, ExamineTask examineTask) {
        return this.page(page,
                Wrappers.lambdaQuery(ExamineTask.class)
                        //考核年份
                        .eq(!ObjectUtils.isNull(examineTask.getExamineYear()), ExamineTask::getExamineYear, examineTask.getExamineYear())
                        //考核名称
                        .like(!StringUtils.isBlank(examineTask.getExamineName()), ExamineTask::getExamineName, examineTask.getExamineName())
                        //创建时间倒叙
                        .orderByDesc(ExamineTask::getCreateTime)
        );
    }

    /**
     * 查询管区考核任务分页
     *
     * @param page        分页参数
     * @param examineTask 考核
     * @return 管区考核任务分页
     */
    @Override
    public IPage<ExamineTask> findRegionExamineTaskPage(PageT page, ExamineTask examineTask) {
        //查询当前登陆人的区域代码
        RestResult<UserAccountDTO> restResult = remoteUserService.getByUsername(SecurityUtil.getCurrentUser().getUsername(), AuthConstants.FROM_IN);
        UserAccountDTO user = restResult.getData();
        //查询管区考核任务分页
        IPage<ExamineTask> iPage = this.baseMapper.findRegionExamineTaskPage(page, examineTask, user.getSelectedCode());
        return iPage;
    }

    /**
     * 查询村级考核任务分页
     *
     * @param page        分页参数
     * @param examineTask 考核
     * @return 村级考核任务分页
     */
    @Override
    public IPage<ExamineTask> findVillageExamineTaskPage(PageT page, ExamineTask examineTask) {
        //查询当前登陆人的区域代码
        RestResult<UserAccountDTO> restResult = remoteUserService.getByUsername(SecurityUtil.getCurrentUser().getUsername(), AuthConstants.FROM_IN);
        UserAccountDTO user = restResult.getData();
        //查询村级考核任务分页
        IPage<ExamineTask> iPage = this.baseMapper.findVillageExamineTaskPage(page, examineTask, user.getSelectedCode());
        return iPage;
    }


    /**
     * 查询管区的考核评分
     *
     * @param page            分页参数
     * @param examineScoreDTO 考核
     * @return 管区考核评分分页
     */
    @Override
    public IPage<ExamineScoreVO> findRegionScorePage(PageT page, ExamineScoreDTO examineScoreDTO) {
        //查询当前登陆人的区域代码
        RestResult<UserAccountDTO> restResult = remoteUserService.getByUsername(SecurityUtil.getCurrentUser().getUsername(), AuthConstants.FROM_IN);
        UserAccountDTO user = restResult.getData();

        IPage<ExamineScoreVO> iPage = this.baseMapper.findRegionScorePage(page, examineScoreDTO, user.getSelectedCode());
        return iPage;
    }


    /**
     * 查询村级考核评分
     *
     * @param page            分页参数
     * @param villageScoreDTO 请求参数
     * @return 村级-指标考核分页列表
     */
    @Override
    public IPage<VillageIndicatorsVO> findVillageScorePage(PageT page, VillageScoreDTO villageScoreDTO) {
        return this.baseMapper.findVillageScorePage(page, villageScoreDTO);
    }


    /**
     * 查询镇级考核任务评分
     *
     * @param page            分页参数
     * @param examineScoreDTO 考核
     * @return 所有考核任务评分分页
     */
    @Override
    public IPage<ExamineScoreVO> findTownScorePage(PageT page, ExamineScoreDTO examineScoreDTO) {
        return this.baseMapper.findTownScorePage(page, examineScoreDTO);
    }

    /**
     * 查询考核任务下所有管区的评分情况分页
     *
     * @param page           分页参数分页参数
     * @param regionScoreDTO 考核
     * @return 该考核任务下所有管区评分详情
     */
    @Override
    public IPage<RegionScoreVO> findTaskRegionScorePage(PageT page, RegionScoreDTO regionScoreDTO) {
        return this.baseMapper.findTaskRegionScorePage(page, regionScoreDTO);
    }

    /**
     * 查询镇级考核结果
     *
     * @param examineResultDTO 考核结果请求实体
     * @return 镇级考核结果
     */
    @Override
    public ExamineResultTableVO findTownExamineResult(ExamineResultDTO examineResultDTO) {
        List<ExamineResultInfoVO> examineResultInfoList = this.baseMapper.findTownExamineResultList(examineResultDTO);

        List<ExamineResultInfoVO> examineIndicatorsList = this.baseMapper.findTownExamineIndicatorsList(examineResultDTO);

        //按照指标进行MAP分组
        LinkedHashMap<String, List<ExamineResultInfoVO>> indicatorsMap = new LinkedHashMap<>();
        examineResultInfoList.forEach(examineResultInfoVO -> {
            if (!indicatorsMap.containsKey(examineResultInfoVO.getIndicatorsId())) {
                indicatorsMap.put(examineResultInfoVO.getIndicatorsId(), new ArrayList<>());
            }
            indicatorsMap.get(examineResultInfoVO.getIndicatorsId()).add(examineResultInfoVO);
        });

        //管区区域
        RestResult<List<AreaDTO>> regionRestResult = remoteAreaService.getRegionAsc(null, AuthConstants.FROM_IN);
        List<AreaDTO> regionAreaList = regionRestResult.getData();
        //村级区域
        RestResult<List<AreaDTO>> villageRestResult = remoteAreaService.getVillageAsc(null, AuthConstants.FROM_IN);
        List<AreaDTO> villageAreaList = villageRestResult.getData();

        //创建一个完成率预统计列表
        List<LinkedHashMap<String, List<String>>> completionRateList = new ArrayList<>();

        //组装表格数据
        List<LinkedHashMap<String, String>> resultList = this.assembleTableData(indicatorsMap, examineIndicatorsList, completionRateList,
                regionAreaList, villageAreaList);

        //组装动态表头
        List<TreeView> tableTitle = this.assembleTableTitle(regionAreaList, villageAreaList);

        //计算完成率
        List<List<String>> completionRateResultList = this.assembleCompletionRateResult(completionRateList, examineIndicatorsList);

        //获取表头
        return new ExamineResultTableVO()
                .setTableData(resultList)
                .setTitle(tableTitle)
                .setIndicatorsList(examineIndicatorsList)
                .setCompletionRateList(completionRateResultList);
    }

    private List<LinkedHashMap<String, String>> assembleTableData(LinkedHashMap<String, List<ExamineResultInfoVO>> indicatorsMap,
                                                                  List<ExamineResultInfoVO> examineIndicatorsList,
                                                                  List<LinkedHashMap<String, List<String>>> completionRateList,
                                                                  List<AreaDTO> regionAreaList, List<AreaDTO> villageAreaList) {
        //总的表格返回列表
        List<LinkedHashMap<String, String>> resultList = new ArrayList<>();
        //指标表格
        //针对指标下的打分实体进行循环
        for (Map.Entry<String, List<ExamineResultInfoVO>> entry : indicatorsMap.entrySet()) {
            //管区完成率的map创建
            LinkedHashMap<String, List<String>> regionRateMap = new LinkedHashMap<>();
            regionAreaList.forEach(region -> regionRateMap.put(region.getCode(), new ArrayList<>()));
            //每一行的返回列表
            LinkedHashMap<String, String> villageResultMap = new LinkedHashMap<>();
            //把集合转为村为Key，值为Value的map
            Map<String, ExamineResultInfoVO> villageMap = entry.getValue().stream().collect(Collectors.toMap(ExamineResultInfoVO::getVillageAreaCode, ExamineResultInfoVO -> ExamineResultInfoVO));

            //遍历所有村级
            for (AreaDTO area : villageAreaList) {
                //判断这个指标是定量还是定性，去分别处理
                ExamineResultInfoVO indicators = examineIndicatorsList.stream().filter(filter -> Objects.equals(filter.getIndicatorsId(), entry.getKey())).findFirst().orElse(null);
                //获取当前循环村级考核实体
                ExamineResultInfoVO examineResultInfoVO = villageMap.get(area.getCode());
                //为空直接赋值-
                if (ObjectUtils.isNull(examineResultInfoVO)) {
                    regionRateMap.get(area.getPcode()).add(String.valueOf(ExamineConstant.INCOMPLETE));
                    villageResultMap.put(area.getName(), "-");
                    continue;
                }
                if (Objects.equals(ExamineConstant.QUALITATIVE_INDICATORS, indicators.getType())) {
                    //定性指标
                    String ch;
                    if (ObjectUtils.isNull(examineResultInfoVO.getVerifyCompleteStatus())) {
                        ch = "-";
                        regionRateMap.get(area.getPcode()).add(String.valueOf(ExamineConstant.INCOMPLETE));
                    } else {
                        ch = Objects.equals(ExamineConstant.INCOMPLETE, examineResultInfoVO.getVerifyCompleteStatus()) ? "未完成" : "已完成";
                        regionRateMap.get(area.getPcode()).add(String.valueOf(examineResultInfoVO.getVerifyCompleteStatus()));
                    }
                    villageResultMap.put(area.getName(), ch);
                } else {
                    //定量指标->组装单元格值并按顺序插入到对应的地方
                    String value = examineResultInfoVO.getTarget() + "/" + examineResultInfoVO.getScore().stripTrailingZeros().toPlainString();
                    villageResultMap.put(area.getName(), value);
                    regionRateMap.get(area.getPcode()).add(value);
                }
            }
            resultList.add(villageResultMap);
            completionRateList.add(regionRateMap);
        }
        return resultList;
    }


    /**
     * 计算完成率
     *
     * @param completionRateList    完成情况列表
     * @param examineIndicatorsList 指标列表
     * @return 完成率结果
     */
    private List<List<String>> assembleCompletionRateResult(List<LinkedHashMap<String, List<String>>> completionRateList, List<ExamineResultInfoVO> examineIndicatorsList) {

        List<List<String>> completionRateResultList = new ArrayList<>();
        //计算完成率
        for (int i = 0; i < completionRateList.size(); i++) {
            List<String> regionRateList = new ArrayList<>();
            ExamineResultInfoVO indicators = examineIndicatorsList.get(i);
            if (Objects.equals(ExamineConstant.QUALITATIVE_INDICATORS, indicators.getType())) {
                //定性指标->将已完成(0)的除以当前管区总数=完成率
                for (Map.Entry<String, List<String>> entry : completionRateList.get(i).entrySet()) {
                    BigDecimal successNum = BigDecimal.ZERO;
                    for (String value : entry.getValue()) {
                        if (Objects.equals(String.valueOf(ExamineConstant.SUCCESS), value)) {
                            successNum = successNum.add(BigDecimal.ONE);
                        }
                    }
                    regionRateList.add(successNum.multiply(BigDecimal.valueOf(100)).divide(new BigDecimal(entry.getValue().size()), 2, BigDecimal.ROUND_HALF_UP) + "%");
                }
            } else {
                //定量指标->统计所有目标值,统计所有核实值;核实值÷目标值=完成率
                for (Map.Entry<String, List<String>> entry : completionRateList.get(i).entrySet()) {
                    BigDecimal targetNum = BigDecimal.ZERO;
                    BigDecimal scoreNum = BigDecimal.ZERO;
                    for (String value : entry.getValue()) {
                        if (value.indexOf("/") == -1) {
                            continue;
                        } else {
                            String[] split = value.split("/");
                            targetNum = targetNum.add(new BigDecimal(split[0]));
                            scoreNum = scoreNum.add(new BigDecimal(split[1]));
                        }
                    }
                    if (targetNum.compareTo(BigDecimal.ZERO) == 0) {
                        regionRateList.add("0.00%");
                    } else {
                        regionRateList.add(scoreNum.multiply(BigDecimal.valueOf(100)).divide(targetNum, 2, BigDecimal.ROUND_HALF_UP) + "%");
                    }
                }
            }
            completionRateResultList.add(regionRateList);
        }
        return completionRateResultList;
    }


    /**
     * 组装动态表头
     *
     * @param regionAreaList  管区列表
     * @param villageAreaList 村级列表
     * @return
     */
    private List<TreeView> assembleTableTitle(List<AreaDTO> regionAreaList, List<AreaDTO> villageAreaList) {
        List<TreeView> tableTitle = new ArrayList<>();
        //组装表头
        regionAreaList.forEach(region -> {
            TreeView treeView = new TreeView().setLabel(region.getName()).setChildren(new ArrayList<>());
            for (int i = 0; i < villageAreaList.size(); i++) {
                AreaDTO village = villageAreaList.get(i);
                if (!Objects.equals(region.getCode(), village.getPcode())) {
                    continue;
                }
                treeView.getChildren().add(new TreeView().setLabel(village.getName()));
            }
            tableTitle.add(treeView);
        });
        return tableTitle;
    }

    /**
     * 查询管区考核结果
     *
     * @param examineResultDTO 考核结果请求实体
     * @return 管区考核结果
     */
    @Override
    public ExamineResultTableVO findRegionExamineResult(ExamineResultDTO examineResultDTO) {
        //查询当前登陆人的区域代码
        RestResult<UserAccountDTO> restResult = remoteUserService.getByUsername(SecurityUtil.getCurrentUser().getUsername(), AuthConstants.FROM_IN);
        UserAccountDTO user = restResult.getData();

        List<ExamineResultInfoVO> examineResultInfoList = this.baseMapper.findTownExamineResultList(examineResultDTO.setRegionAreaCode(user.getSelectedCode()));

        List<ExamineResultInfoVO> examineIndicatorsList = this.baseMapper.findTownExamineIndicatorsList(examineResultDTO.setRegionAreaCode(user.getSelectedCode()));

        //按照指标进行MAP分组
        LinkedHashMap<String, List<ExamineResultInfoVO>> indicatorsMap = new LinkedHashMap<>();
        examineResultInfoList.forEach(examineResultInfoVO -> {
            if (!indicatorsMap.containsKey(examineResultInfoVO.getIndicatorsId())) {
                indicatorsMap.put(examineResultInfoVO.getIndicatorsId(), new ArrayList<>());
            }
            indicatorsMap.get(examineResultInfoVO.getIndicatorsId()).add(examineResultInfoVO);
        });

        //管区区域
        RestResult<List<AreaDTO>> regionRestResult = remoteAreaService.getRegionAsc(user.getSelectedCode(), AuthConstants.FROM_IN);
        List<AreaDTO> regionAreaList = regionRestResult.getData();
        //村级区域
        RestResult<List<AreaDTO>> villageRestResult = remoteAreaService.getVillageAsc(user.getSelectedCode(), AuthConstants.FROM_IN);
        List<AreaDTO> villageAreaList = villageRestResult.getData();

        //创建一个完成率预统计列表
        List<LinkedHashMap<String, List<String>>> completionRateList = new ArrayList<>();

        //组装表格数据
        List<LinkedHashMap<String, String>> resultList = this.assembleTableData(indicatorsMap, examineIndicatorsList, completionRateList,
                regionAreaList, villageAreaList);

        //组装动态表头
        List<TreeView> tableTitle = this.assembleTableTitle(regionAreaList, villageAreaList);

        //计算完成率
        List<List<String>> completionRateResultList = this.assembleCompletionRateResult(completionRateList, examineIndicatorsList);

        //获取表头
        return new ExamineResultTableVO()
                .setTableData(resultList)
                .setTitle(tableTitle)
                .setIndicatorsList(examineIndicatorsList)
                .setCompletionRateList(completionRateResultList);
    }


    /**
     * 查询村级考核结果
     *
     * @param page            分页数据
     * @param villageScoreDTO 查询条件
     * @return 村级考核结果分页
     */
    @Override
    public IPage<VillageIndicatorsVO> findVillageExamineResult(PageT page, VillageScoreDTO villageScoreDTO) {
        //查询当前登陆人的区域代码
        RestResult<UserAccountDTO> restResult = remoteUserService.getByUsername(SecurityUtil.getCurrentUser().getUsername(), AuthConstants.FROM_IN);
        UserAccountDTO user = restResult.getData();

        return this.baseMapper.findVillageExamineResult(page, villageScoreDTO.setVillageAreaCode(user.getSelectedCode()));
    }
}
