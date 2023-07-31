package com.sjz.governance.controller.camera;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.governance.model.dto.camera.*;
import com.sjz.governance.model.entity.camera.CameraManagement;
import com.sjz.governance.model.vo.camera.CameraManagementVo;
import com.sjz.governance.model.vo.camera.CameraStatusStatisticsVo;
import com.sjz.governance.model.vo.camera.CameraTreeVo;
import com.sjz.governance.service.camera.CameraManagementService;
import com.sjz.governance.utils.recognition.RecognitionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.util.List;

/**
 * 视频监控管理Controller
 *
 * @author guozhilin
 * @date 2023-04-03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/cameraManagement")
@Api(tags = "视频监控管理")
public class CameraManagementController extends BaseController {


    private final CameraManagementService cameraManagementService;

    /**
     * 新增视频监控管理
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增视频监控管理")
    @SysLog(title = "新增视频监控管理", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Validated @RequestBody CameraManagement cameraManagement) {
        return RestResult.succeed(cameraManagementService.saveCameraManagement(cameraManagement));
    }

    /**
     * 修改视频监控管理
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改视频监控管理")
    @SysLog(title = "修改视频监控管理", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> edit(@Validated @RequestBody CameraManagement cameraManagement) {
        return RestResult.succeed(cameraManagementService.updateCameraManagement(cameraManagement));
    }

    /**
     * 删除视频监控管理
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除视频监控管理")
    @SysLog(title = "删除视频监控管理", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(cameraManagementService.deleteCameraManagement(id));
    }


    /**
     * 分页查询视频监控管理
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询视频监控管理")
    public RestResult<PageT<CameraManagementVo>> getPage(PageT<CameraManagementVo> page, CameraManagementDTO cameraManagement) {
        return RestResult.succeed(cameraManagementService.findCameraManagementPage(page, cameraManagement));
    }

    /**
     * 查询视频监控管理列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询视频监控管理列表")
    public RestResult<List<CameraManagement>> getList(CameraManagementDTO cameraManagement) {
        return RestResult.succeed(cameraManagementService.findCameraManagementList(cameraManagement));
    }

    /**
     * 获取视频监控管理详细信息
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "获取视频监控管理详细信息")
    public RestResult<CameraManagement> getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(cameraManagementService.findCameraManagementById(id));
    }

    /**
     * 国标接入视频监控
     */
    @PostMapping("/saveByGb/{deviceId}")
    @ApiOperation(value = "国标接入视频监控")
    @SysLog(title = "国标接入视频监控", serviceId = ServiceNameConstants.GOVERNANCE_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<String> saveByGb(@PathVariable("deviceId") String deviceId) {
        String save = cameraManagementService.saveByGb(deviceId);
        return StringUtils.isNotBlank(save) ? RestResult.failed(save) : RestResult.succeed();
    }

    @GetMapping("/cameraTree")
    @ApiOperation(value = "监控树")
    public RestResult<List<CameraTreeVo>> cameraTree() {
        return RestResult.succeed(cameraManagementService.getCameraTree());
    }

    @GetMapping("/htmlPreview")
    @ApiOperation(value = "监控页面预览实时预览")
    public RestResult<String> getHtmlPreview(CameraHtmlPreviewDTO t) {
        try {
            String htmlPreview = cameraManagementService.getHtmlPreview(t);
            return StringUtils.isBlank(htmlPreview) ? RestResult.failed("预览失败") : RestResult.succeed(cameraManagementService.getHtmlPreview(t));
        }catch (Exception e){
            return RestResult.failed("预览超时");
        }
    }

    @PostMapping("/PTZCtrlProxy")
    @ApiOperation(value = "云台控制")
    public RestResult<String> getPTZCtrlProxy(PTZCtrlProxyDTO t) {
        return RestResult.succeed(cameraManagementService.getPTZCtrlProxy(t));
    }

    @GetMapping("/playback")
    @ApiOperation(value = "监控历史回放")
    public RestResult<String> getPlayback(CameraPlayBackDTO t) {
        return RestResult.succeed(cameraManagementService.getPlayback(t));
    }

    @PostMapping("/identifyResults")
    @ApiOperation(value = "识别结果接口")
    public RestResult<String> identifyResults() {
        RecognitionUtils.intelligentRecognition(Boolean.FALSE);
        return RestResult.succeed();
    }

    @GetMapping("/getSpeed")
    @ApiOperation(value = "倍速播放")
    public RestResult<String> getSpeed(CameraHistoryOperateDTO operateDTO) {
        return RestResult.succeed(cameraManagementService.getSpeed(operateDTO));
    }

    @GetMapping("/getSeek")
    @ApiOperation(value = "拖动播放")
    public RestResult<String> getSeek(CameraHistoryOperateDTO operateDTO) {
        return RestResult.succeed(cameraManagementService.getSeek(operateDTO));
    }

    @GetMapping("/getPause")
    @ApiOperation(value = "暂停播放")
    public RestResult<String> getPause(CameraHistoryOperateDTO operateDTO) {
        return RestResult.succeed(cameraManagementService.getPause(operateDTO));
    }

    @GetMapping("/getReplay")
    @ApiOperation(value = "重新播放")
    public RestResult<String> getReplay(CameraHistoryOperateDTO operateDTO) {
        return RestResult.succeed(cameraManagementService.getReplay(operateDTO));
    }

    @GetMapping("/getCameraCountByStatus")
    @ApiOperation(value = "根据状态统计监控数量")
    public RestResult<CameraStatusStatisticsVo> getCameraCountByStatus() {
        return RestResult.succeed(cameraManagementService.getCameraCountByStatus());
    }

}
