package com.sjz.governance.mapper.event;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.vo.echarts.EChartsIntegerPie;
import com.sjz.governance.model.dto.event.AlarmEventDTO;
import com.sjz.governance.model.dto.event.IntelligentRecognitionEventDTO;
import com.sjz.governance.model.entity.event.AlarmEvent;
import com.sjz.governance.model.vo.event.IntelligentRecognitionEventVo;
import com.sjz.governance.model.vo.event.AlarmEventStatisticHome;
import com.sjz.governance.model.vo.event.AlarmEventVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 综合治理_告警事件Mapper接口
 * 
 * @author pengl
 * @date 2023-04-04
 */
public interface AlarmEventMapper extends BaseMapper<AlarmEvent> {

    /**
     * 分页查询
     * @param page
     * @param dto
     * @return
     */
    PageT<AlarmEventVO> findAlarmEventPage(@Param("page") PageT<AlarmEventVO> page, @Param("dto") AlarmEventDTO dto);

    AlarmEventVO getInfoById(String id);

    List<AlarmEventStatisticHome>  statisticEventHome();

    /**
     * 分页查询
     * @param page
     * @param dto
     * @return
     */
    PageT<AlarmEventVO> findAlarmEventPageByDealPerson(@Param("page") PageT<AlarmEventVO> page, @Param("dto") AlarmEventDTO dto);

    /**
     * 分页查询
     * @param page
     * @param dto
     * @return
     */
    PageT<AlarmEventVO> pageByCreateBy(@Param("page") PageT<AlarmEventVO> page, @Param("dto") AlarmEventDTO dto);

    /**
     * 综合治理首页顶部-告警事件总数统计
     * @Author GZL
     * @Date 2023/4/9 9:52
     * @return 告警事件总数统计
     **/
    List<EChartsIntegerPie> getEventCountByStatus();

    /**
     * 综合治理首页_分页查询
     * @Author GZL
     * @Date 2023/4/9 11:38
     * @Param page 分页信息
     * @return 结果
     **/
    PageT<IntelligentRecognitionEventVo> governanceHomePage(PageT<IntelligentRecognitionEventVo> page);

    /**
     * 智能识别分页查询
     * @Author GZL
     * @Date 2023/4/9 14:46
     * @Param page 分页信息
     * @Param queryDTO 查询条件
     * @return 智能识别数据
     **/
    PageT<IntelligentRecognitionEventVo> intelligentRecognitionPage(@Param("page") PageT<IntelligentRecognitionEventVo> page,
                                                                    @Param("dto") IntelligentRecognitionEventDTO queryDTO);
}
