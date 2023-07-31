package com.crcm.file.controller;

import com.crcm.core.base.BaseController;
import com.crcm.file.service.AttachmentService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DownLoadController
 * @Description
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/6/10 9:52
 **/
@Slf4j
@Api(tags = {"文件下载接口"})
@RestController
@RequestMapping("/download")
public class DownloadController extends BaseController {

    @Autowired
    private AttachmentService attachmentService;


}
