package com.sjz.evaluations.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.evaluations.model.dto.VillageExamineSaveDTO;
import com.sjz.evaluations.model.entity.RegionExamine;
import com.sjz.evaluations.model.entity.VillageExamine;
import com.sjz.evaluations.model.vo.AssessmentStatics;
import com.sjz.evaluations.model.vo.VillageIndicatorsVO;

import java.util.List;

/**
 * 村级考核实体Service接口
 *
 * @author zzyt
 * @date 2023-04-25
 */
public interface VillageExamineService extends IService<VillageExamine> {

    /**
     * 新增村级考核实体
     *
     * @param VillageExamine 村级考核实体
     * @return 结果
     */
    boolean saveVillageExamine(VillageExamine VillageExamine);

    /**
     * 批量新增村级考核实体
     *
     * @param villageExamineSaveDTO 村级考核实体
     * @return 结果
     */
    boolean saveVillageExamineBatch(VillageExamineSaveDTO villageExamineSaveDTO);


    /**
     * 修改村级考核实体
     *
     * @param villageExamineSaveDTO 村级考核实体
     * @return 结果
     */
    boolean updateVillageExamine(VillageExamineSaveDTO villageExamineSaveDTO);

    /**
     * 删除村级考核实体信息
     *
     * @param id 村级考核实体ID
     * @return 结果
     */
    boolean deleteVillageExamine(String id);

    /**
     * 查询村级考核实体
     *
     * @param id 村级考核实体ID
     * @return 村级考核实体
     */
    VillageIndicatorsVO findVillageExamineById(String id);

    /**
     * 查询村级考核实体列表
     *
     * @param VillageExamine 村级考核实体
     * @return 村级考核实体集合
     */
    List<VillageExamine> findVillageExamineList(VillageExamine VillageExamine);

    /**
     * 分页查询村级考核实体列表
     *
     * @param page           分页参数
     * @param VillageExamine 村级考核实体
     * @return 村级考核实体集合
     */
    IPage<VillageExamine> findVillageExaminePage(PageT page, VillageExamine VillageExamine);

    /**
     * 根据指标id和管区考核id查询村级考核列表
     *
     * @param indicatorsId 指标id
     * @param regionId     管区id
     * @return 村级考核列表
     */
    List<VillageExamine> findVillageByRegionId(String indicatorsId, String regionId);

    /**
     * 管区提交对村级的打分
     *
     * @param villageExamine 打分信息
     * @return 结果
     */
    boolean submitRegionScore(VillageExamine villageExamine);

    /**
     * 镇级提交对管区的核实
     *
     * @param villageExamine 打分信息
     * @return 结果
     */
    boolean submitTownScore(VillageExamine villageExamine);

    List<AssessmentStatics> assessmentStatics();

    /**
     * 通过指标id和区域考核id查询村级考核
     * @param regionExamine
     * @return
     */
	List<VillageExamine> selectByIndicatorIdAndRegionId(RegionExamine regionExamine);
}
