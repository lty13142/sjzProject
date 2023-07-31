package com.sjz.governance.mapper.camera;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.vo.echarts.EChartsIntegerPie;
import com.sjz.governance.model.dto.camera.CameraManagementDTO;
import com.sjz.governance.model.entity.camera.CameraManagement;
import com.sjz.governance.model.vo.camera.CameraManagementVo;
import com.sjz.governance.model.vo.camera.CameraTreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 视频监控管理Mapper接口
 *
 * @author guozhilin
 * @date 2023-04-03
 */
public interface CameraManagementMapper extends BaseMapper<CameraManagement> {

    /**
     * 获取监控树
     *
     * @return 监控树
     * @Author GZL
     * @Date 2023/4/4 10:25
     **/
    List<CameraTreeVo> selectCamera();

    PageT<CameraManagementVo> findCameraManagementPage(@Param("page") PageT<CameraManagementVo> page, @Param("dto") CameraManagementDTO cameraManagement);

    /**
     * 根据状态统计监控数量
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/4/7 18:45
     **/
    List<EChartsIntegerPie> getCameraCountByStatus();
}
