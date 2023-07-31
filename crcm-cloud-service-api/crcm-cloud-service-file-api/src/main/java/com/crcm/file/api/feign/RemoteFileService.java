package com.crcm.file.api.feign;

import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.response.RestResult;
import com.crcm.file.api.feign.dto.res.FileResDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RemoteFileService
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/7/1
 **/
@FeignClient(contextId = "remoteFileService", value = ServiceNameConstants.FILE_SERVICE)
public interface RemoteFileService {

    /**
     * 文件上传
     *
     * @param files    上传的文件对象
     * @param type 存储路径
     * @return
     */
    @PostMapping(value = "/file/minioUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    RestResult<List<FileResDTO>> upload(@RequestPart("files") MultipartFile[] files, @RequestParam("type") Integer type, @RequestHeader(AuthConstants.FROM) String from);

    /**
     * 通过附件ID获取文件地址
     *
     * @param attId
     * @return
     */
    @GetMapping(value = "/att/path/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    RestResult<String> getFilePath(@PathVariable(value = "id") String attId, @RequestHeader(AuthConstants.FROM) String from);

    /**
     * 业务id查询附件集合详细信息
     * TODO 接口未实现
     * @param businessId
     * @return
     */
    @GetMapping("/att/findDetailListByBid/{businessId}")
    RestResult<List<FileResDTO>> findDetailListByBid(@PathVariable(value = "businessId") String businessId);

    /**
     * 根据业务id批量关联所属附件
     * TODO 接口未实现
     * @param attIds 附件ID集合
     * @param businessId 业务ID
     * @return
     */
    @RequestMapping(value = "/att/bindBusinessId", method = RequestMethod.POST)
    RestResult updateBatchByBussinessId(@RequestParam(name = "attIds", required = false) String attIds,
                                        @RequestParam(name = "businessId", required = true) String businessId);


    /**
     * 通过ids查询多个附件
     *
     * @param ids
     * @return
     */
    @GetMapping("/att/getAttachmentListByIds/{ids}")
    RestResult<List<Map<String,String>>> getAttachmentListByIds(@PathVariable(value = "ids") String ids, @RequestHeader(AuthConstants.FROM) String from);
    @GetMapping("/att/findFileListByIds/{ids}")
    RestResult findFileListByIds(@PathVariable(value = "ids") String ids, @RequestHeader(AuthConstants.FROM) String from);
}
