package com.zsgf.platform.service.study;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.dto.study.DocumentInfoDTO;
import com.zsgf.platform.model.entity.study.DocumentInfo;
import com.zsgf.platform.vo.study.DocumentInfoVo;

import java.util.List;

/**
 * 文档信息Service接口
 *
 * @author gzl
 * @date 2023-03-28
 */
public interface DocumentInfoService extends IService<DocumentInfo> {

    /**
     * 新增文档信息
     *
     * @param documentInfo 文档信息
     * @return 结果
     */
    boolean saveDocumentInfo(DocumentInfo documentInfo);

    /**
     * 修改文档信息
     *
     * @param documentInfo 文档信息
     * @return 结果
     */
    boolean updateDocumentInfo(DocumentInfo documentInfo);

    /**
     * 删除文档信息信息
     *
     * @param id 文档信息ID
     * @return 结果
     */
    boolean deleteDocumentInfo(String id);

    /**
     * 查询文档信息
     *
     * @param id 文档信息ID
     * @return 文档信息
     */
    DocumentInfo findDocumentInfoById(String id);

    /**
     * 查询文档信息列表
     *
     * @param documentInfo 文档信息
     * @return 文档信息集合
     */
    List<DocumentInfo> findDocumentInfoList(DocumentInfoDTO documentInfo);

    /**
     * 分页查询文档信息列表
     *
     * @param page         分页参数
     * @param documentInfo 文档信息
     * @return 文档信息集合
     */
    PageT<DocumentInfoVo> findDocumentInfoPage(PageT<DocumentInfoVo> page, DocumentInfoDTO documentInfo);
}
