package com.sjz.governance.service.impl.camera;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.utils.UtilDataFormat;
import com.crcm.core.vo.echarts.EChartsIntegerPie;
import com.sjz.governance.mapper.camera.CameraManagementMapper;
import com.sjz.governance.model.dto.camera.*;
import com.sjz.governance.model.entity.camera.CameraManagement;
import com.sjz.governance.model.vo.AreaVo;
import com.sjz.governance.model.vo.camera.CameraManagementVo;
import com.sjz.governance.model.vo.camera.CameraStatusStatisticsVo;
import com.sjz.governance.model.vo.camera.CameraTreeVo;
import com.sjz.governance.service.camera.CameraManagementService;
import com.sjz.governance.utils.GB28181Utils;
import com.sjz.governance.utils.UtilSysArea;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 视频监控管理Service业务层处理
 *
 * @author guozhilin
 * @date 2023-04-03
 */
@Service
public class CameraManagementServiceImpl extends ServiceImpl<CameraManagementMapper, CameraManagement> implements CameraManagementService {


    /**
     * 新增视频监控管理
     *
     * @param cameraManagement 视频监控管理
     * @return 结果
     */
    @Override
    public boolean saveCameraManagement(CameraManagement cameraManagement) {
        return this.save(cameraManagement);
    }

    /**
     * 修改视频监控管理
     *
     * @param cameraManagement 视频监控管理
     * @return 结果
     */
    @Override
    public boolean updateCameraManagement(CameraManagement cameraManagement) {
        return this.updateById(cameraManagement);
    }

    /**
     * 删除视频监控管理信息
     *
     * @param id 视频监控管理ID
     * @return 结果
     */
    @Override
    public boolean deleteCameraManagement(String id) {
        return this.removeById(id);
    }

    /**
     * 查询视频监控管理
     *
     * @param id 视频监控管理ID
     * @return 视频监控管理
     */
    @Override
    public CameraManagement findCameraManagementById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询视频监控管理列表
     *
     * @param cameraManagement 视频监控管理
     * @return 视频监控管理
     */
    @Override
    public List<CameraManagement> findCameraManagementList(CameraManagementDTO cameraManagement) {
        LambdaQueryWrapper<CameraManagement> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(cameraManagement.getStatus()), CameraManagement::getStatus, cameraManagement.getStatus());
        queryWrapper.eq(Objects.nonNull(cameraManagement.getDeviceId()), CameraManagement::getDeviceId, cameraManagement.getDeviceId());
        queryWrapper.eq(Objects.nonNull(cameraManagement.getDistrictId()), CameraManagement::getDistrictId, cameraManagement.getDistrictId());
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询视频监控管理
     *
     * @param page             分页参数
     * @param cameraManagement 视频监控管理
     * @return 视频监控管理
     */
    @Override
    public PageT<CameraManagementVo> findCameraManagementPage(PageT<CameraManagementVo> page, CameraManagementDTO cameraManagement) {
        return this.baseMapper.findCameraManagementPage(page, cameraManagement);
    }

    @Override
    public String saveByGb(String deviceId) {
        if (StringUtils.isBlank(deviceId)) {
            return "设备id不能为空";
        }
        // GB28181平台获取设备
        String result = GB28181Utils.getChannelList(deviceId);
        if (StringUtils.isBlank(result)) {
            return "监控接入失败";
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray channelList = jsonObject.getJSONArray("ChannelList");
        if (null == channelList || channelList.size() <= 0) {
            return "无监控信息";
        }
        CameraManagementDTO info = new CameraManagementDTO();
        info.setDeviceId(deviceId);
        // 根据设备ID获取监控设备信息
        List<CameraManagement> cameraInfoList = findCameraManagementList(info);
        for (Object o : channelList) {
            JSONObject camera = JSONObject.parseObject(o.toString());
            // Address为 "0.0.0.0" 时设备不存在
            if ("0.0.0.0".equals(camera.getString("Address")) || StringUtils.isBlank(camera.getString("Model"))) {
                continue;
            }
            CameraManagement cameraInfo = new CameraManagement();
            cameraInfo.setCameraName(camera.getString("Name"));
            cameraInfo.setIp(camera.getString("Address"));
            cameraInfo.setDeviceId(camera.getString("DeviceID"));
            cameraInfo.setChannelId(camera.getString("ID"));
            cameraInfo.setStatus(camera.getInteger("Status"));
            // 过滤查找存在的监控设备
            CameraManagement cameraManagement = cameraInfoList.stream().filter(item -> item.getChannelId().equals(cameraInfo.getChannelId()))
                    .findAny().orElse(null);
            // 查找不存在的监控设备（数据库存在，GB28181平台不存在）
            for (CameraManagement item : cameraInfoList) {
                if (item.getChannelId().equals(cameraInfo.getChannelId())) {
                    cameraInfoList.remove(item);
                    break;
                }
            }
            if (null == cameraManagement) {
                this.baseMapper.insert(cameraInfo);
            } else {
                // 判断数据库监控信息和获取的监控信息是否一致，一致则跳出本次循环
                if (Objects.equals(cameraManagement.getCameraName(), cameraInfo.getCameraName())
                        && Objects.equals(cameraManagement.getStatus(), cameraInfo.getStatus())) {
                    continue;
                }
                cameraManagement.setCameraName(cameraInfo.getCameraName());
                cameraManagement.setIp(cameraInfo.getIp());
                // 如果设备名字为空则表示监控不在线
                cameraManagement.setStatus(StringUtils.isBlank(cameraInfo.getCameraName()) ? 0 : cameraInfo.getStatus());
                updateCameraManagement(cameraManagement);
            }
        }
        if (cameraInfoList != null && cameraInfoList.size() > 0) {
            this.updateBatchById(cameraInfoList, cameraInfoList.size());
        }
        return null;
    }

    @Override
    public List<CameraTreeVo> getCameraTree() {
        List<CameraTreeVo> result = new ArrayList<>();
        List<CameraTreeVo> cameraTreeList = this.baseMapper.selectCamera();
        // 所属区列表
        List<AreaVo> areaList = UtilSysArea.getAreaByType(SystemConstant.AREA_TYPE.VILLAGE);
        if (CollectionUtils.isEmpty(cameraTreeList) || CollectionUtils.isEmpty(areaList)) {
            return result;
        }
        Map<String, List<CameraTreeVo>> collect = UtilDataFormat.listGroup(cameraTreeList, CameraTreeVo::getParentId);
        for (AreaVo areaVo : areaList) {
            CameraTreeVo parent = new CameraTreeVo();
            parent.setId(areaVo.getId());
            parent.setCameraName(areaVo.getName());
            parent.setCameraFlag(SystemConstant.YES_OR_NO.NO);
            parent.setChildren(collect.getOrDefault(areaVo.getId(), new ArrayList<>()));
            // 过滤无监控的村
            if(CollectionUtils.isNotEmpty(parent.getChildren())){
                result.add(parent);
            }
        }
        return result;
    }

    @Override
    public String getHtmlPreview(CameraHtmlPreviewDTO t) {
        String streamId = GB28181Utils.getStreamId(t.getDeviceId(), t.getChannelId(), t.getDeviceDesc());
        if (StringUtils.isBlank(streamId)) {
            return "";
        }
        return GB28181Utils.getUrl("HTML_PREVIEW_URL") + "?token=" + streamId;
    }

    @Override
    public String getPTZCtrlProxy(PTZCtrlProxyDTO t) {
        return GB28181Utils.getPTZCtrlProxy(t);
    }

    @Override
    public String getPlayback(CameraPlayBackDTO t) {
        return GB28181Utils.htmlPlayBack(t);
    }

    @Override
    public String getSpeed(CameraHistoryOperateDTO operateDTO) {
        return GB28181Utils.getSpeed(operateDTO);
    }

    @Override
    public String getSeek(CameraHistoryOperateDTO operateDTO) {
        return GB28181Utils.getSeek(operateDTO);
    }

    @Override
    public String getPause(CameraHistoryOperateDTO operateDTO) {
        return GB28181Utils.getPause(operateDTO);
    }

    @Override
    public String getReplay(CameraHistoryOperateDTO operateDTO) {
        return GB28181Utils.getReplay(operateDTO);
    }

    @Override
    public CameraStatusStatisticsVo getCameraCountByStatus() {
        CameraStatusStatisticsVo total = new CameraStatusStatisticsVo();
        List<EChartsIntegerPie> cameraCountByStatus = this.baseMapper.getCameraCountByStatus();
        int onLine = UtilDataFormat.getListSum(cameraCountByStatus, data -> Objects.equals(SystemConstant.YES_OR_NO.YES + "", data.getTypeCode()),
                EChartsIntegerPie::getValueData);
        total.setTotal(UtilDataFormat.getListSum(cameraCountByStatus, EChartsIntegerPie::getValueData));
        total.setOnLine(onLine);
        total.setOffLine(total.getTotal() - total.getOnLine());
        return total;
    }
}
