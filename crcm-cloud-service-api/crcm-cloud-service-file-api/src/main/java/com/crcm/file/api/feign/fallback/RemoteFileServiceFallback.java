package com.crcm.file.api.feign.fallback;

import com.crcm.core.response.RestResult;
import com.crcm.file.api.feign.RemoteFileService;
import com.crcm.file.api.feign.dto.res.FileResDTO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RemoteOauthServiceFallback
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/7/1
 **/
@Slf4j
@Service
public class RemoteFileServiceFallback implements RemoteFileService {

    @Setter
    private Throwable cause;

    @Override
    public RestResult<List<FileResDTO>> upload(MultipartFile[] files, Integer type, String form) {
        return null;
    }

    @Override
    public RestResult<String> getFilePath(String attId, String form) {
        return null;
    }

    @Override
    public RestResult<List<FileResDTO>> findDetailListByBid(String businessId) {
        return null;
    }

    @Override
    public RestResult updateBatchByBussinessId(String attIds, String businessId) {
        return null;
    }

    @Override
    public RestResult<List<Map<String, String>>> getAttachmentListByIds(String ids, String from) {
        return null;
    }

    @Override
    public RestResult findFileListByIds(String ids, String from) {
        return null;
    }
}
