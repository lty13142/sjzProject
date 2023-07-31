package com.sjz.evaluations.util;

import cn.hutool.json.JSONUtil;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.response.RestResult;
import com.crcm.core.utils.SpringContextHolder;
import com.crcm.file.api.feign.RemoteFileService;
import com.sjz.evaluations.model.vo.AttachmentsSimpleVO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author yzw
 * @data 2023/4/6
 * @apiNote
 */
public class FileUtil {

    private static RemoteFileService remoteSystemFileService = SpringContextHolder.getBean(RemoteFileService.class);

    public static List<AttachmentsSimpleVO> getAllAttachments(String ids) {
        //判断当前附件不是空的
        if (StringUtils.isNotBlank(ids)){
            //List<String> split = StrUtil.split(path, ",", 0, true, true);
            RestResult fileListByIds = remoteSystemFileService.findFileListByIds(ids, AuthConstants.FROM_IN);
            Object data = fileListByIds.getData();
            List<AttachmentsSimpleVO> attachments = JSONUtil.toList(JSONUtil.parseArray(data), AttachmentsSimpleVO.class);
            return attachments;
        }
        return null;
    }

    // 校验文件名是否携带特殊字符
    public static Boolean validateFileName(String fileName) {
        Boolean flag = true;
        String[] strs = {"+", "%", "#", "&", "=","-", "?", "/"};
        for (int i = 0; i < strs.length; i++) {
            if (fileName.indexOf(strs[i]) >= 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
