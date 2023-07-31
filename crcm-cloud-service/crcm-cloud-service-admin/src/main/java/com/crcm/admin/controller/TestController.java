package com.crcm.admin.controller;

import com.crcm.core.constant.AuthConstants;
import com.crcm.core.response.RestResult;
import com.crcm.file.api.feign.RemoteFileService;
import com.crcm.file.api.feign.dto.res.FileResDTO;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName TestController
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2022/1/24
 **/
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final RemoteFileService remoteFileService;

    @PostMapping("/upload")
    public RestResult<List<FileResDTO>> testUpload(@RequestParam("files") MultipartFile[] files, Integer type) {
        return remoteFileService.upload(files, type, AuthConstants.FROM_IN);
    }
}
