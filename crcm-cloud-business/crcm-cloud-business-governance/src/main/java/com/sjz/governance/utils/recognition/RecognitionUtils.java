package com.sjz.governance.utils.recognition;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.DicConstant;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.response.RestResult;
import com.crcm.file.api.feign.RemoteFileService;
import com.crcm.file.api.feign.dto.res.FileResDTO;
import com.sjz.governance.model.dto.camera.CameraManagementDTO;
import com.sjz.governance.model.dto.event.AlarmEventDTO;
import com.sjz.governance.model.entity.camera.CameraManagement;
import com.sjz.governance.model.entity.key.KeyVehicleManagement;
import com.sjz.governance.model.vo.camera.CameraIdentificationVo;
import com.sjz.governance.service.camera.CameraManagementService;
import com.sjz.governance.service.event.AlarmEventService;
import com.sjz.governance.service.event.AlarmEventSyncService;
import com.sjz.governance.service.key.KeyVehicleManagementService;
import com.sjz.governance.utils.GB28181Utils;
import com.sjz.governance.utils.UtilDic;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * @ClassName RecognitionUtils
 * @Description 识别接口工具类
 * @Author GZL
 * @Date 2023/4/6 10:58
 * @Version 1.0
 **/

@Log4j2
@Component
public class RecognitionUtils {

    /**
     * 初始化线程池，同时执行10个线程
     */
    private static final ExecutorService realTimeExecutor = ThreadUtil.newExecutor(33);

    /**
     * 初始化线程池，同时执行6个线程
     */
    private static final ExecutorService invokeExecutor = ThreadUtil.newExecutor(6);

    private static CameraManagementService cameraManagementService;

    private static AlarmEventSyncService alarmEventSyncService;

    private static RemoteFileService remoteFileService;

    private static KeyVehicleManagementService keyVehicleManagementService;

    @Autowired
    public void setKeyVehicleManagementService(KeyVehicleManagementService keyVehicleManagementService) {
        RecognitionUtils.keyVehicleManagementService = keyVehicleManagementService;
    }

    @Autowired
    public void setRemoteFileService(RemoteFileService remoteFileService) {
        RecognitionUtils.remoteFileService = remoteFileService;
    }

    @Autowired
    public void setCameraManagementService(CameraManagementService cameraManagementService) {
        RecognitionUtils.cameraManagementService = cameraManagementService;
    }

    @Autowired
    public void setAlarmEventService(AlarmEventSyncService alarmEventSyncService) {
        RecognitionUtils.alarmEventSyncService = alarmEventSyncService;
    }

    /**
     * 智能识别
     *
     * @Author GZL
     * @Date 2023/4/6 14:03
     * @Param requestFlag 请求接口标记(true: 前端接口调用；false：后端实时调用)
     **/
    public static void intelligentRecognition(boolean requestFlag) {
        CameraManagementDTO cameraManagementDTO = new CameraManagementDTO();
        cameraManagementDTO.setStatus(SystemConstant.USER_STATUS.ON_LINE);
        List<CameraManagement> cameraInfoList = cameraManagementService.findCameraManagementList(cameraManagementDTO);
        // 空集合返回
        if (CollectionUtils.isEmpty(cameraInfoList)) {
            return;
        }
        List<CameraManagement> intelligentCameraInfoList = cameraInfoList.stream()
                .filter(data -> StringUtils.isNotBlank(data.getChannelId()) && StringUtils.isNotBlank(data.getDeviceId())
                        && StringUtils.isNotBlank(data.getCameraCaptureType())).collect(Collectors.toList());
        // 空集合返回
        if (CollectionUtils.isEmpty(intelligentCameraInfoList)) {
            return;
        }
        for (CameraManagement camera : intelligentCameraInfoList) {
            String previewUrl = GB28181Utils.getPreview(camera.getDeviceId(), camera.getChannelId());
            // 判断监控是否能预览
            if (StringUtils.isBlank(camera.getCameraCaptureType()) || StringUtils.isBlank(previewUrl)) {
                continue;
            }
            CameraIdentificationVo vo = new CameraIdentificationVo();
            BeanUtils.copyProperties(camera, vo);
            String snapUrl = GB28181Utils.getSnapUrl(camera.getDeviceId(), camera.getChannelId(), camera.getDeviceDesc());
            vo.setSnapUrl(snapUrl);
            if (requestFlag) {
                invokeExecutor.execute(() -> invokingRealTime(vo));
            } else {
                realTimeExecutor.execute(() -> invokingRealTime(vo));
            }
        }
    }

    public static void invokingRealTime(CameraIdentificationVo item) {
        // 获取抓拍图片
        log.info("监控图片地址：{}", item.getSnapUrl());
        HttpResponse execute = HttpRequest.get(item.getSnapUrl()).timeout(15000).execute();
        byte[] resultBytes = execute.bodyBytes();
        if (200 != execute.getStatus() || 1592 >= resultBytes.length) {
            return;
        }
        String base64 = Base64Utils.encodeToString(resultBytes);
        if (StringUtils.isBlank(base64)) {
            return;
        }
        // 获取监控识别类型
        String[] captureType = item.getCameraCaptureType().split(",");
        for (String type : captureType) {
            // 获取智能识别类型
            String value = UtilDic.getDictComment(SystemConstant.CAMERA_CAPTURE_TYPE.CODE, type);
            if (StringUtils.isBlank(value)) {
                continue;
            }
            // 识别类型名称
            String name = UtilDic.getDictName(SystemConstant.CAMERA_CAPTURE_TYPE.CODE, type);
            // 识别
            String recognitionResult = recognition(base64, value);
            System.out.println(item.getCameraName() + ":" + name + ": " + value);
            System.out.println("识别结果：" + recognitionResult);
            // 是否识别成功
            boolean identifyResult = StringUtils.isNotBlank(recognitionResult) && JSONValidator.from(recognitionResult).validate()
                    && 200 == JSONObject.parseObject(recognitionResult).getInteger("code");
            if (!identifyResult) {
                return;
            }
            // 判断返回是否是JSON数据
            if (!JSONValidator.from(recognitionResult).validate()) {
                return;
            }
            JSONObject ccrObject = JSONObject.parseObject(recognitionResult);
            JSONArray dataArray = ccrObject.getJSONArray("data");
            if (CollectionUtils.isEmpty(dataArray)) {
                return;
            }
            AlarmEventDTO result = recognitionResult(recognitionResult, resultBytes, type);
            if (Objects.nonNull(result)) {
                AlarmEventDTO alarmEvent = new AlarmEventDTO();
                alarmEvent.setEventType(type);
                alarmEvent.setAddressNetwork(item.getAddress());
                alarmEvent.setVillageId(item.getDistrictId());
                alarmEvent.setEventName(UtilDic.getDictName(SystemConstant.CAMERA_CAPTURE_TYPE.CODE, type));
                alarmEvent.setDealPerson(item.getUserId());
                alarmEvent.setDealPersonName(item.getUserName());
                alarmEvent.setDealNode(DicConstant.EVENT_DEAL_NODE.EVENT_DEAL_NODE_1);
                // 识别信息
                alarmEvent.setEventUrl(result.getEventUrl());
                alarmEvent.setRecognitionRate(result.getRecognitionRate());
                alarmEvent.setCameraId(item.getId());
                alarmEvent.setEventSource(SystemConstant.ALARM_EVENT_SOURCE.AUTO);
                alarmEvent.setAlarmTime(LocalDateTime.now());
                alarmEventSyncService.saveAlarmEvent(alarmEvent);
            }
        }
    }

    /**
     * 识别结果处置
     *
     * @param recognitionResult 识别结果
     * @param resultBytes       抓拍图片
     * @param type              识别类型
     */
    public static AlarmEventDTO recognitionResult(String recognitionResult, byte[] resultBytes, String type) {
        // 判断返回是否是JSON数据
        if (!JSONValidator.from(recognitionResult).validate()) {
            return null;
        }
        JSONObject ccrObject = JSONObject.parseObject(recognitionResult);
        JSONArray dataArray = ccrObject.getJSONArray("data");
        if (CollectionUtils.isEmpty(dataArray)) {
            return null;
        }
        AlarmEventDTO result = null;
        for (int i = 0; i < dataArray.size(); i++) {
            JSONArray array = dataArray.getJSONObject(i).getJSONArray("result");
            switch (type) {
                // 路口违停
                case SystemConstant.CAMERA_CAPTURE_TYPE.INTERSECTION_VIOLATION:
                    // 重点人员
                case SystemConstant.CAMERA_CAPTURE_TYPE.KEY_PERSONNEL:
                    // 火点识别
                case SystemConstant.CAMERA_CAPTURE_TYPE.FIRE_POINT_IDENTIFICATION:
                    // 人员进入
                case SystemConstant.CAMERA_CAPTURE_TYPE.PERSONNEL_ENTRY:
                    result = drawPicture(resultBytes, array);
                    break;
                // 重点车辆
                case SystemConstant.CAMERA_CAPTURE_TYPE.KEY_VEHICLES:
                    List<KeyVehicleManagement> vehicleList = keyVehicleManagementService.findKeyVehicleManagementList(new KeyVehicleManagement());
                    if (CollectionUtils.isEmpty(vehicleList)) {
                        return null;
                    }
                    List<String> plateNumberList = vehicleList.stream().filter(vehicle -> StringUtils.isNotBlank(vehicle.getPlateNumber()) && vehicle.getPlateNumber().length() > 4)
                            .map(KeyVehicleManagement::getPlateNumber).collect(Collectors.toList());
                    result = drawKeyVehicleSPicture(plateNumberList, resultBytes, array);
                    break;
                // 其他识别
                default:
                    break;
            }
        }
        return result;
    }

    /**
     * 重点车辆识别结果处置
     *
     * @param plateNumberList 车牌号列表
     * @param resultBytes     抓拍图片
     * @param jsonArray       识别结果
     */
    public static AlarmEventDTO drawKeyVehicleSPicture(List<String> plateNumberList, byte[] resultBytes, JSONArray jsonArray) {
        if (CollectionUtils.isEmpty(jsonArray) || CollectionUtils.isEmpty(plateNumberList)) {
            return null;
        }
        AlarmEventDTO alarmEventDTO;
        InputStream captureInputStream = new ByteArrayInputStream(resultBytes);
        try {
            BufferedImage image = ImageIO.read(captureInputStream);
            Graphics g = image.getGraphics();
            //画笔颜色
            g.setColor(Color.RED);
            // 识别率
            BigDecimal recognitionRate = BigDecimal.ZERO;
            for (int i = 0; i < jsonArray.size(); i++) {
                // 车牌号
                String plateNumber = jsonArray.getJSONObject(i).getString("text");
                // 车牌号存在才报警
                if (StringUtils.isBlank(plateNumber) || !plateNumberList.contains(plateNumber)) {
                    continue;
                }
                JSONObject location = jsonArray.getJSONObject(i).getJSONObject("location");
                JSONObject point = location.getJSONObject("point");
                //矩形框(原点x坐标，原点y坐标，矩形的长，矩形的宽)
                g.drawRect(point.getInteger("x"), point.getInteger("y"), location.getInteger("width"), location.getInteger("height"));
                String score = jsonArray.getJSONObject(i).getString("score");
                BigDecimal currentRecognitionRate = StringUtils.isBlank(score) ? BigDecimal.ZERO : new BigDecimal(jsonArray.getJSONObject(i).getString("score"));
                if (currentRecognitionRate.compareTo(recognitionRate) > 0) {
                    recognitionRate = currentRecognitionRate;
                }
            }
            // 释放资源
            g.dispose();
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(image, "jpeg", imOut);
            captureInputStream = new ByteArrayInputStream(bs.toByteArray());
            // InputStream转成 MultipartFile
            MultipartFile multipartFile = new MockMultipartFile("file", "file.jpg", "text/plain", captureInputStream);
            RestResult<List<FileResDTO>> upload = remoteFileService.upload(new MultipartFile[]{multipartFile}, 29, AuthConstants.FROM_IN);
            if (Objects.nonNull(upload) && upload.isSuccess() && CollectionUtils.isNotEmpty(upload.getData())) {
                alarmEventDTO = new AlarmEventDTO();
                alarmEventDTO.setRecognitionRate(recognitionRate.multiply(new BigDecimal("100")).toPlainString());
                alarmEventDTO.setEventUrl(upload.getData().get(0).getId());
                return alarmEventDTO;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 重点车辆识别结果处置
     *
     * @param resultBytes 抓拍图片
     * @param jsonArray   识别结果
     */
    public static AlarmEventDTO drawPicture(byte[] resultBytes, JSONArray jsonArray) {
        if (CollectionUtils.isEmpty(jsonArray)) {
            return null;
        }
        AlarmEventDTO alarmEventDTO;
        InputStream captureInputStream = new ByteArrayInputStream(resultBytes);
        try {
            BufferedImage image = ImageIO.read(captureInputStream);
            Graphics g = image.getGraphics();
            //画笔颜色
            g.setColor(Color.RED);
            // 识别率
            BigDecimal recognitionRate = BigDecimal.ZERO;
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject location = jsonArray.getJSONObject(i).getJSONObject("location");
                JSONObject point = location.getJSONObject("point");
                //矩形框(原点x坐标，原点y坐标，矩形的长，矩形的宽)
                g.drawRect(point.getInteger("x"), point.getInteger("y"), location.getInteger("width"), location.getInteger("height"));
                String score = jsonArray.getJSONObject(i).getString("score");
                BigDecimal currentRecognitionRate = StringUtils.isBlank(score) ? BigDecimal.ZERO : new BigDecimal(jsonArray.getJSONObject(i).getString("score"));
                if (currentRecognitionRate.compareTo(recognitionRate) > 0) {
                    recognitionRate = currentRecognitionRate;
                }
            }
            // 释放资源
            g.dispose();
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(image, "jpeg", imOut);
            captureInputStream = new ByteArrayInputStream(bs.toByteArray());
            // InputStream转成 MultipartFile
            MultipartFile multipartFile = new MockMultipartFile("file", "file.jpg", "text/plain", captureInputStream);
            RestResult<List<FileResDTO>> upload = remoteFileService.upload(new MultipartFile[]{multipartFile}, 29, AuthConstants.FROM_IN);
            if (Objects.nonNull(upload) && upload.isSuccess() && CollectionUtils.isNotEmpty(upload.getData())) {
                alarmEventDTO = new AlarmEventDTO();
                alarmEventDTO.setRecognitionRate(recognitionRate.multiply(new BigDecimal("100")).toPlainString());
                alarmEventDTO.setEventUrl(upload.getData().get(0).getId());
                return alarmEventDTO;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }


    /**
     * 识别
     *
     * @param base64 图片
     * @param url    接口
     * @return 识别结果
     */
    public static String recognition(String base64, String url) {
        JSONObject params = new JSONObject();
        List<String> images = new ArrayList<>();
        images.add(base64);
        params.put("images", images);
        return HttpRequest.post(url)
                .header("Content-Type", ContentType.JSON.toString())
                .header("Authorization", "39bc38410ed545ef894c1f6c8ed7f581:91edb2dc0e8c4310b466d900e3ef4231")
                .body(params.toJSONString())
                .timeout(50000)
                .executeAsync().body();
    }
}

