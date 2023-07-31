package com.sjz.governance.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import com.crcm.core.constant.DicConstant;
import com.sjz.governance.model.dto.camera.CameraHistoryOperateDTO;
import com.sjz.governance.model.dto.camera.CameraPlayBackDTO;
import com.sjz.governance.model.dto.camera.PTZCtrlProxyDTO;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * @ClassName GB28181Utils
 * @Description 监控国标工具类
 * @Author GZL
 * @Date 2023/4/4 9:28
 * @Version 1.0
 **/
public class GB28181Utils {
    /**
     * 图片抓拍地址
     */
    private static final String SNAP_URL = "{0}/index/api/getSnap?secret=56ac991c-d7a5-4b42-9233-234d7f03b193&url={1}/rtp/gb_play_{2}_{3}{4}&timeout_sec=10&expire_sec=0&random={5}";


    /**
     * 拉流代理
     */
    private static final String STREAM_PROXY = "{0}/index/api/addStreamProxy?secret=56ac991c-d7a5-4b42-9233-234d7f03b193&vhost={1}&app=rtp&stream={2}&url={3}";


    /**
     * 设备获取同步
     */
    private static final String DEVICE_SYNC = "/api/devices/{0}/sync";

    /**
     * 获取URL地址
     *
     * @param value 字典值
     * @return URL地址
     */
    public static String getUrl(String value) {
        return UtilDic.getDictComment(DicConstant.GB28181_CAMERA.CODE, value);
    }


    /**
     * 获取预览地址 StreamId
     *
     * @param deviceId  设备id
     * @param channelId 通道id
     * @return 预览地址
     */
    public static String getStreamId(String deviceId, String channelId, String rtsp) {
        if (StringUtils.isBlank(deviceId) || StringUtils.isBlank(channelId)) {
            return "";
        }

        if (StringUtils.isNotBlank(rtsp)) {
            return getRtspPreview(deviceId, channelId, rtsp);
        }

        return getPreview(deviceId, channelId);
    }

    /**
     * 采取rtsp 方式播放
     *
     * @param deviceId  设备id
     * @param channelId 通道id
     * @param rtsp      rtsp播放流
     * @return 结果
     */
    public static String getRtspPreview(String deviceId, String channelId, String rtsp) {
        // 视频流地址
        String video_streaming_url = getUrl("VIDEO_STREAMING_URL");
        String streamId = "gb_play_" + deviceId + "_" + channelId;
        String url = MessageFormat.format(STREAM_PROXY, video_streaming_url, video_streaming_url, streamId, rtsp);
        String result = HttpUtil.get(url);
        // 判断是否是JSON
        if (!JSONValidator.from(result).validate()) {
            return "";
        }
        if (StringUtils.isBlank(JSONObject.parseObject(result).getString("data"))) {
            return "";
        }
        return streamId;
    }


    /**
     * 获取预览地址
     *
     * @param deviceId  设备id
     * @param channelId 通道id
     * @return 结果
     */
    public static String getPreview(String deviceId, String channelId) {
        String result = HttpUtil.createGet(UtilDic.getDictComment(DicConstant.GB28181_CAMERA.CODE,
                "GB28181_SERVER_URL") + "/api/play/" + deviceId + "/" + channelId)
                .timeout(15000).execute().body();
        // 判断是否是JSON
        if (!JSONValidator.from(result).validate()) {
            return "";
        }
        return JSONObject.parseObject(result).getString("streamId");
    }

    /**
     * @return 回放数据
     * @Author gzl
     * @Description 回放
     * @Date 2023/4/4 10:11
     * @Param playBackDTO 回放参数
     **/
    public static JSONObject playBack(CameraPlayBackDTO playBackDTO) {
        if (StringUtils.isBlank(playBackDTO.getDeviceId()) || StringUtils.isBlank(playBackDTO.getChannelId())
                || StringUtils.isBlank(playBackDTO.getBeginTime()) || StringUtils.isBlank(playBackDTO.getEndTime())) {
            return null;
        }
        String result = HttpUtil.get(UtilDic.getDictComment(DicConstant.GB28181_CAMERA.CODE, "GB28181_SERVER_URL")
                + "/api/playback/" + playBackDTO.getDeviceId() + "/" + playBackDTO.getChannelId() + "?closeOpened=true&startTime="
                + playBackDTO.getBeginTime() + "&endTime=" + playBackDTO.getEndTime());
        // 判断是否是JSON
        if (!JSONValidator.from(result).validate()) {
            return null;
        }
        return JSONObject.parseObject(result);
    }

    /**
     * @return 回放数据
     * @Author gzl
     * @Description 回放
     * @Date 2023/4/4 10:11
     * @Param playBackDTO 回放参数
     **/
    public static String htmlPlayBack(CameraPlayBackDTO playBackDTO) {
        JSONObject result = playBack(playBackDTO);
        if (Objects.isNull(result) || !result.containsKey("streamId")) {
            return "";
        }
        String streamId = result.getString("streamId");
        if (StringUtils.isBlank(streamId)) {
            return "";
        }
        return GB28181Utils.getUrl("HTML_PREVIEW_URL") + "?token=" + streamId;
    }

    /**
     * 视频监控云台控制
     *
     * @param vo 控制参数
     * @return 结果
     */
    public static String getPTZCtrlProxy(PTZCtrlProxyDTO vo) {
        if (StringUtils.isBlank(vo.getDeviceId()) || StringUtils.isBlank(vo.getChannelId())) {
            return "";
        }
        String url = UtilDic.getDictComment(DicConstant.GB28181_CAMERA.CODE, "GB28181_SERVER_URL") + "/api/ptz/" + vo.getDeviceId() + "/" + vo.getChannelId();
        Map<String, Object> params = BeanUtil.beanToMap(vo);
        return HttpUtil.post(url, params);
    }


    /**
     * 获取监测设备集合
     *
     * @param deviceId 设备id
     * @return 结果
     */
    public static String getChannelList(String deviceId) {
        if (StringUtils.isBlank(deviceId)) {
            return "";
        }
        Map<String, Object> params = new HashMap<>();
        params.put("serial", deviceId);
        String result = HttpUtil.get(getUrl("GB28181_SERVER_URL") + "/api/v1/device/channellist", params);
        // 判断是否是JSON
        if (!JSONValidator.from(result).validate()) {
            return "";
        }
        return result;
    }


    /**
     * 监控信息同步更新
     *
     * @param deviceId 设备id
     */
    public static void cameraSync(String deviceId) {
        String path = getUrl("GB28181_SERVER_URL") + MessageFormat.format(DEVICE_SYNC, deviceId);
        HttpUtil.post(path, "");
    }


    /**
     * 获取抓拍图片地址
     *
     * @param deviceId  设备id
     * @param channelId 通道id
     */
    public static String getSnapUrl(String deviceId, String channelId, String deviceDesc) {
        if (StringUtils.isBlank(deviceId) || StringUtils.isBlank(channelId)) {
            return "";
        }
        // 视频流地址
        String video_streaming_url = getUrl("VIDEO_STREAMING_URL");
        Random random = RandomUtil.getRandom(true);
        String playMode = "/hls.m3u8";
        if (StringUtils.isBlank(deviceDesc)) {
            return MessageFormat.format(SNAP_URL, video_streaming_url, video_streaming_url, deviceId, channelId, playMode, random.nextFloat());
        }
        playMode = ".flv";
        return MessageFormat.format(SNAP_URL, video_streaming_url, video_streaming_url, deviceId, channelId, playMode, random.nextFloat());
    }

    /**
     * 倍速播放
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/4/7 17:03
     * @Param operateDTO 操作参数
     **/
    public static String getSpeed(CameraHistoryOperateDTO operateDTO) {
        if (StringUtils.isBlank(operateDTO.getStreamId()) || StringUtils.isBlank(operateDTO.getSpeed())) {
            return "";
        }
        Map<String, Object> params = new HashMap<>();
        params.put("streamId", operateDTO.getStreamId());
        params.put("speed", operateDTO.getSpeed());
        String result = HttpUtil.get(getUrl("GB28181_SERVER_URL") + "/api/playback/speed", params);
        // 判断是否是JSON
        if (!JSONValidator.from(result).validate()) {
            return "";
        }
        return result;
    }

    /**
     * 拖动播放
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/4/7 17:03
     * @Param operateDTO 操作参数
     **/
    public static String getSeek(CameraHistoryOperateDTO operateDTO) {
        if (StringUtils.isBlank(operateDTO.getStreamId()) || StringUtils.isBlank(operateDTO.getSeekTime())) {
            return "";
        }
        Map<String, Object> params = new HashMap<>();
        params.put("streamId", operateDTO.getStreamId());
        params.put("seekTime", operateDTO.getSeekTime());
        String result = HttpUtil.get(getUrl("GB28181_SERVER_URL") + "/api/playback/seek", params);
        // 判断是否是JSON
        if (!JSONValidator.from(result).validate()) {
            return "";
        }
        return result;
    }

    /**
     * 暂停播放
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/4/7 17:03
     * @Param operateDTO 操作参数
     **/
    public static String getPause(CameraHistoryOperateDTO operateDTO) {
        if (StringUtils.isBlank(operateDTO.getStreamId())) {
            return "";
        }
        Map<String, Object> params = new HashMap<>();
        params.put("streamId", operateDTO.getStreamId());
        String result = HttpUtil.get(getUrl("GB28181_SERVER_URL") + "/api/playback/pause", params);
        // 判断是否是JSON
        if (!JSONValidator.from(result).validate()) {
            return "";
        }
        return result;
    }

    /**
     * 重新播放
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/4/7 17:03
     * @Param operateDTO 操作参数
     **/
    public static String getReplay(CameraHistoryOperateDTO operateDTO) {
        if (StringUtils.isBlank(operateDTO.getStreamId())) {
            return "";
        }
        Map<String, Object> params = new HashMap<>();
        params.put("streamId", operateDTO.getStreamId());
        String result = HttpUtil.get(getUrl("GB28181_SERVER_URL") + "/api/playback/replay", params);
        // 判断是否是JSON
        if (!JSONValidator.from(result).validate()) {
            return "";
        }
        return result;
    }
}
