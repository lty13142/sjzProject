package com.crcm.bpm.controller.api;


import com.crcm.bpm.service.api.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * 流程模型图Controller
 *
 * @author zzyt
 * @date 2020-08-18
 */
@RestController
@RequestMapping("api/flow/img")
@Api(value = "Test", tags = {"跟踪高亮"})
@Slf4j
public class ImageController {
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/generateImage", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ApiOperation(value = "流程跟踪图", notes = "图片生成，接口返回")
    @ApiImplicitParams({@ApiImplicitParam(name = "procInstId", value = "流程实例ID", required = true, dataType = "String")})
    @ResponseBody
    public byte[] generateImage(String procInstId) throws Exception {
        return imageService.generateImageByProcInstId(procInstId);
    }

    @RequestMapping(value = "/viewProcessImg", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "流程跟踪图", notes = "生成到本地文件夹下，前端再读取", produces = "application/json")
    public void viewProcessImg(String processId, HttpServletResponse response) throws IOException {
        OutputStream os = null;
        try {
            String directory = "D:" + File.separator + "temp" + File.separator;
            final String suffix = ".png";
            File folder = new File(directory);
            File[] files = folder.listFiles();
            if (files != null && files.length >= 1) {
                for (File file : files) {
                    if (file.getName().equals(processId + suffix)) {
                        file.delete();
                    }
                }
            }
            byte[] processImage = imageService.generateImageByProcInstId(processId);
            File dest = new File(directory + processId + suffix);
            os = new FileOutputStream(dest, true);
            os.write(processImage, 0, processImage.length);
            os.flush();
        } catch (Exception e) {
            log.error("流程跟踪图生成失败： {}", ExceptionUtils.getStackTrace(e));
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}

