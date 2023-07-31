package com.zsgf.platform.service.impl.study;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.dto.study.DocumentInfoDTO;
import com.zsgf.platform.mapper.study.DocumentInfoMapper;
import com.zsgf.platform.model.entity.study.DocumentInfo;
import com.zsgf.platform.service.study.DocumentInfoService;
import com.zsgf.platform.utils.SecurityBusinessUtil;
import com.zsgf.platform.vo.study.DocumentInfoVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文档信息Service业务层处理
 *
 * @author gzl
 * @date 2023-03-28
 */
@Service
public class DocumentInfoServiceImpl extends ServiceImpl<DocumentInfoMapper, DocumentInfo> implements DocumentInfoService {


    /**
     * 新增文档信息
     *
     * @param documentInfo 文档信息
     * @return 结果
     */
    @Override
    public boolean saveDocumentInfo(DocumentInfo documentInfo) {
        documentInfo.setCreateUser(SecurityBusinessUtil.getCurrentNickName());
        return this.save(documentInfo);
    }

    /**
     * 修改文档信息
     *
     * @param documentInfo 文档信息
     * @return 结果
     */
    @Override
    public boolean updateDocumentInfo(DocumentInfo documentInfo) {
        return this.updateById(documentInfo);
    }

    /**
     * 删除文档信息信息
     *
     * @param id 文档信息ID
     * @return 结果
     */
    @Override
    public boolean deleteDocumentInfo(String id) {
        return this.removeById(id);
    }

    /**
     * 查询文档信息
     *
     * @param id 文档信息ID
     * @return 文档信息
     */
    @Override
    public DocumentInfo findDocumentInfoById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询文档信息列表
     *
     * @param documentInfo 文档信息
     * @return 文档信息
     */
    @Override
    public List<DocumentInfo> findDocumentInfoList(DocumentInfoDTO documentInfo) {
        LambdaQueryWrapper<DocumentInfo> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询文档信息
     *
     * @param page         分页参数
     * @param documentInfo 文档信息
     * @return 文档信息
     */
    @Override
    public PageT<DocumentInfoVo> findDocumentInfoPage(PageT<DocumentInfoVo> page, DocumentInfoDTO documentInfo) {
        return this.baseMapper.findDocumentInfoPage(page, documentInfo);
    }
}
