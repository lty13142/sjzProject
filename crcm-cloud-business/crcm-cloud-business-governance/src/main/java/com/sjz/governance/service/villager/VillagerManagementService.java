package com.sjz.governance.service.villager;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.vo.echarts.EChartsIntegerBar;
import com.crcm.core.vo.echarts.EChartsIntegerPie;
import com.crcm.core.vo.echarts.EChartsIntegerPieTotal;
import com.sjz.governance.model.dto.villager.VillagerManagementDTO;
import com.sjz.governance.model.entity.villager.VillagerManagement;
import com.sjz.governance.model.vo.villager.VillagerManagementVo;
import com.sjz.governance.model.vo.villager.VillagerStaticsVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 村民信息管理Service接口
 *
 * @author guozhilin
 * @date 2023-04-03
 */
public interface VillagerManagementService extends IService<VillagerManagement> {

    /**
     * 新增村民信息管理
     *
     * @param villagerManagement 村民信息管理
     * @return 结果
     */
    boolean saveVillagerManagement(VillagerManagement villagerManagement);

    /**
     * 修改村民信息管理
     *
     * @param villagerManagement 村民信息管理
     * @return 结果
     */
    boolean updateVillagerManagement(VillagerManagement villagerManagement);

    /**
     * 删除村民信息管理信息
     *
     * @param id 村民信息管理ID
     * @return 结果
     */
    boolean deleteVillagerManagement(String id);

    void deleteBatch(List<String> ids);

    /**
     * 查询村民信息管理
     *
     * @param id 村民信息管理ID
     * @return 村民信息管理
     */
    VillagerManagement findVillagerManagementById(String id);

    /**
     * 查询村民信息管理列表
     *
     * @param villagerManagement 村民信息管理
     * @return 村民信息管理集合
     */
    List<VillagerManagement> findVillagerManagementList(VillagerManagementDTO villagerManagement);

    /**
     * 分页查询村民信息管理列表
     *
     * @param page               分页参数
     * @param villagerManagement 村民信息管理
     * @return 村民信息管理集合
     */
    PageT<VillagerManagementVo> findVillagerManagementPage(PageT<VillagerManagementVo> page, VillagerManagementDTO villagerManagement);

    /**
     * 导出村民信息
     *
     * @Author GZL
     * @Date 2023/4/4 14:18
     * @Param 条件
     * @Param request 请求信息
     * @Param response 响应信息
     **/
    void exportVillager(VillagerManagementDTO villagerManagement, HttpServletRequest request, HttpServletResponse response);

    /**
     * 导入村民信息
     *
     * @Author GZL
     * @Date 2023/4/4 15:39
     * @Param file 文件
     **/
    void importVillager(MultipartFile file);

    /**
     * 导出模板
     *
     * @Author GZL
     * @Date 2023/4/4 14:18
     * @Param request 请求信息
     * @Param response 响应信息
     **/
    void exportModel(HttpServletRequest request, HttpServletResponse response);

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
     * 根据类型统计人员数量
     * @Author GZL
     * @Date 2023/4/9 16:44
     * @return 人员数量
     **/
    EChartsIntegerPieTotal getVillagerStatisticsByType();

    /**
     * 村民信息统计-全镇村民综合人员统计
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/4/7 16:44
     * @Param type 类型
     **/
    EChartsIntegerBar getStatisticsByVillageAndType(String type);

    VillagerStaticsVo getStatisticsByStructure();

    /**
     * 通过身份证号查村民信息
     * @param idNum
     * @return
     */
	VillagerManagement findVillagerManagementByIdNum(String idNum);
}
