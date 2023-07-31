package com.sjz.governance.service.camera;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.vo.echarts.EChartsIntegerPieTotal;
import com.sjz.governance.model.dto.camera.*;
import com.sjz.governance.model.entity.camera.CameraManagement;
import com.sjz.governance.model.vo.camera.CameraManagementVo;
import com.sjz.governance.model.vo.camera.CameraStatusStatisticsVo;
import com.sjz.governance.model.vo.camera.CameraTreeVo;

import java.util.List;

/**
 * 视频监控管理Service接口
 *
 * @author guozhilin
 * @date 2023-04-03
 */
public interface CameraManagementService extends IService<CameraManagement> {

    /**
     * 新增视频监控管理
     *
     * @param cameraManagement 视频监控管理
     * @return 结果
     */
    boolean saveCameraManagement(CameraManagement cameraManagement);

    /**
     * 修改视频监控管理
     *
     * @param cameraManagement 视频监控管理
     * @return 结果
     */
    boolean updateCameraManagement(CameraManagement cameraManagement);

    /**
     * 删除视频监控管理信息
     *
     * @param id 视频监控管理ID
     * @return 结果
     */
    boolean deleteCameraManagement(String id);

    /**
     * 查询视频监控管理
     *
     * @param id 视频监控管理ID
     * @return 视频监控管理
     */
    CameraManagement findCameraManagementById(String id);

    /**
     * 查询视频监控管理列表
     *
     * @param cameraManagement 视频监控管理
     * @return 视频监控管理集合
     */
    List<CameraManagement> findCameraManagementList(CameraManagementDTO cameraManagement);

    /**
     * 分页查询视频监控管理列表
     *
     * @param page             分页参数
     * @param cameraManagement 视频监控管理
     * @return 视频监控管理集合
     */
    PageT<CameraManagementVo> findCameraManagementPage(PageT<CameraManagementVo> page, CameraManagementDTO cameraManagement);

    /**
     * 国标接入视频监控
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/4/4 9:42
     * @Param deviceId 设备id
     **/
    String saveByGb(String deviceId);

    /**
     * 获取监控树
     *
     * @return 监控树
     * @Author GZL
     * @Date 2023/4/4 10:25
     **/
    List<CameraTreeVo> getCameraTree();

    /**
     * 监控页面预览实时预览
     *
     * @return 预览地址
     * @Author GZL
     * @Date 2023/4/4 9:53
     * @Param t 条件
     **/
    String getHtmlPreview(CameraHtmlPreviewDTO t);

    /**
     * 云台控制
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/4/4 9:58
     * @Param t 控制参数
     **/
    String getPTZCtrlProxy(PTZCtrlProxyDTO t);

    /**
     * 监控历史回放
     *
     * @return 回放地址
     * @Author GZL
     * @Date 2023/4/4 10:03
     * @Param t 参数
     **/
    String getPlayback(CameraPlayBackDTO t);

    /**
     * 倍速播放
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/4/7 17:03
     * @Param operateDTO 操作参数
     **/
    String getSpeed(CameraHistoryOperateDTO operateDTO);

    /**
     * 拖动播放
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/4/7 17:03
     * @Param operateDTO 操作参数
     **/
    String getSeek(CameraHistoryOperateDTO operateDTO);

    /**
     * 暂停播放
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/4/7 17:03
     * @Param operateDTO 操作参数
     **/
    String getPause(CameraHistoryOperateDTO operateDTO);

    /**
     * 重新播放
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/4/7 17:03
     * @Param operateDTO 操作参数
     **/
    String getReplay(CameraHistoryOperateDTO operateDTO);

    /**
     * 根据状态统计监控数量
     * @Author GZL
     * @Date 2023/4/7 18:45
     * @return 结果
     **/
    CameraStatusStatisticsVo getCameraCountByStatus();
}
