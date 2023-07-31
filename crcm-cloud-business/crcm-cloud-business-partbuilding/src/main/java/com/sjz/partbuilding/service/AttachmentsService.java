package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.partbuilding.model.entity.Attachments;
import com.sjz.partbuilding.model.vo.AttachmentsVo;

import java.util.List;
import java.util.Map;


/**
 * @author zzyt
 * @version 1.0
 * @date 2020/7/20 9:05
 */
public interface AttachmentsService extends IService<Attachments> {


    /**
     * 新增
     *
     * @param t
     * @return
     */
    int saveAttachments(Attachments t);

    /**
     * 修改
     *
     * @param t
     * @return
     */
    int updateAttachments(Attachments t);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 真实删除
     *
     * @param id
     * @return
     */
    int realDelete(String id);

    /**
     * 根据id查询attachment
     *
     * @param id
     * @return
     */
    Attachments findById(String id);

    /**
     * 分页查询
     *
     * @param pageT
     * @param t
     * @return
     */
    IPage<Attachments> findPage(Page<Attachments> pageT, Attachments t);

    /**
     * 通过ids查询多个附件
     *
     * @param vo
     * @return
     */
    List<AttachmentsVo> findAttachments(AttachmentsVo vo);

    /**
     * 通过附件ID查询附件路径
     *
     * @param attachmentId
     * @return
     */
    String findFilePath(String attachmentId);

    /**
     * 根据条件获取附件路径
     *
     * @param remotePath   文件路径
     * @param fileSaveName 存储文件名
     * @return
     */
    String getFilePath(String remotePath, String fileSaveName);

    List<Attachments> selectCustomBatchById(String[] split);
    /**
     * 根据attId获取minIO附件信息
     *
     * @param attId 附件id
     * @return
     */
    Attachments getMinIoByAttId(String attId);

    /**
     * 通过attIds获取minIO附件信息集
     *
     * @param attIds 附件ids
     * @return
     */
    List<Attachments> getMinIoListByAttIds(String attIds);

    /**
     * 通过attIds获取minIO附件信息集
     * @param attIds
     * @return
     */
    List<Map<String,String>> getAttachmentListByIds(String attIds);


    /**
     * 通过attId获取minIO附件 URL
     * @param attId
     * @return
     */
    String getFilePathById(String attId);
}
