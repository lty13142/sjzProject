package com.crcm.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.file.model.entity.Attachment;
import com.crcm.file.model.vo.AttachmentsSimpleVO;
import com.crcm.file.model.vo.AttachmentsVO;

import java.util.List;
import java.util.Map;

public interface AttachmentService extends IService<Attachment> {

    int saveAttachments(Attachment t);

    int updateAttachments(Attachment t);

    int deleteById(String id);

    int realDelete(String id);

    Attachment findById(String id);

    PageT<Attachment> findPage(PageT<Attachment> pageT, Attachment t);

    List<AttachmentsVO> findAttachments(AttachmentsVO vo);

    String findFilePath(String attachmentId);

    List<String> findBitchFilePath(List<String> attachmentIds);

    /**
     * 获取文件访问路径
     *
     * @param attachment
     * @return
     */
    String getFileUrl(Attachment attachment) throws Exception;

    Attachment findByMd5(String fileMd5);

    /**
     * 根据attId获取minIO附件信息
     *
     * @param attId 附件id
     * @return
     */
    Attachment getMinIoByAttId(String attId);

    /**
     * 通过attIds获取minIO附件信息集
     *
     * @param attIds 附件ids
     * @return
     */
    List<Attachment> getMinIoListByAttIds(String attIds);

    /**
     * 上传网络文件到文件服务器
     *
     * @param networkFile 网络文件路径
     * @param saveDir     存储目录
     * @return
     */
    String uploadNetworkFile(String networkFile, String saveDir);

    List<Map<String,String>> findAttachmentListByIds(String ids);

    List<AttachmentsSimpleVO> findFileListByIds(String ids);

}
