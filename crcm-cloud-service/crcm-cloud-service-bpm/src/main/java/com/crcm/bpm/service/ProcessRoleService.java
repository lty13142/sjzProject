package com.crcm.bpm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.bpm.domain.entity.ProcessRoleDo;
import com.crcm.bpm.domain.vo.ProcessRoleVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author zwj
 * @date 2020-10-28
 */
public interface ProcessRoleService extends IService<ProcessRoleDo> {
    /**
     * 新增
     *
     * @param processRoleVO
     * @return
     */
    int saveProcessRole(ProcessRoleVO processRoleVO);

    /**
     * 更新
     *
     * @param processRoleDo
     * @return
     */
    int updateProcessRole(ProcessRoleDo processRoleDo);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    ProcessRoleDo findById(String id);

    /**
     * 查询列表
     *
     * @param processRoleDo
     * @return
     */
    List<ProcessRoleDo> findList(ProcessRoleDo processRoleDo);

    /**
     * 分页查询
     *
     * @param page
     * @param processRoleDo
     * @return
     */
    IPage<ProcessRoleDo> findPage(Page page, ProcessRoleDo processRoleDo);

    /**
     * 根据公司id和角色id查询角色具备的流程表权限
     *
     * @param processRoleDo
     * @return
     */
    List<Long> findProcessIds(ProcessRoleDo processRoleDo);

}
