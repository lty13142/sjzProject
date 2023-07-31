package com.sjz.governance.mapper.villager;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.vo.echarts.EChartsIntegerPie;
import com.sjz.governance.excel.model.VillagerManagementModel;
import com.sjz.governance.model.dto.villager.VillagerManagementDTO;
import com.sjz.governance.model.entity.villager.VillagerManagement;
import com.sjz.governance.model.vo.villager.VillagerManagementVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 村民信息管理Mapper接口
 *
 * @author guozhilin
 * @date 2023-04-03
 */
public interface VillagerManagementMapper extends BaseMapper<VillagerManagement> {

    PageT<VillagerManagementVo> findVillagerManagementPage(@Param("page") PageT<VillagerManagementVo> page, @Param("dto") VillagerManagementDTO villagerManagement);

    /**
     * 获取村民导出信息
     *
     * @return 村民导出信息
     * @Author GZL
     * @Date 2023/4/4 14:18
     * @Param villagerManagement 条件
     **/
    List<VillagerManagementModel> getExportVillagerData(VillagerManagementDTO villagerManagement);

    /**
     * 根据区域统计各类人员数量
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/4/7 15:54
     * @Param dto 区域/村
     **/
    List<EChartsIntegerPie> getVillagerStatisticsByArea(VillagerManagementDTO dto);

    /**
     * 村民信息统计-全镇村民综合人员统计
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/4/7 16:49
     * @Param type 类型
     **/
    List<EChartsIntegerPie> getStatisticsByVillageAndType(String type);

    int getAllNum(String sex);

    List<VillagerManagementVo> getAllVillager();

	VillagerManagement findVillagerManagementByIdNum(String idNum);
}
