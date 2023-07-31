package com.crcm.bpm.utils;

import com.crcm.core.utils.SpringContextHolder;
import com.crcm.file.api.feign.RemoteFileService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/12/10 09:38 <br>
 * @Author: <a>bot</a>
 */
public class FileUtil {

    private static RemoteFileService remoteSystemFileService = SpringContextHolder.getBean(RemoteFileService.class);

    /**
     * 获取key为BusinessId,value为头像地址的map
     *
     * @param ids
     * @return
     */
    public static Map<String, String> getAvatarMap(List<String> ids) {
        // TODO 对接头像问题
//        List<FileResultVo> list = remoteSystemFileService.findAttachmentListByBids(JSON.toJSONString(ids)).getData();
        Map<String, String> map = new HashMap();
//        if (CollectionUtil.isNotEmpty(list)) {
//            list.forEach(fileResultVo -> {
//                map.put(fileResultVo.getBusinessId(), fileResultVo.getPath());
//            });
//        }
        return map;
    }
}
