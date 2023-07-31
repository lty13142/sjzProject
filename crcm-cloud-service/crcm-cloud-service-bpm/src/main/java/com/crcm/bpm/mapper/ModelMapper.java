package com.crcm.bpm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.bpm.domain.dto.response.ModelDTO;
import com.crcm.bpm.domain.entity.ModelDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 流程模型图Mapper接口
 *
 * @author zzyt
 * @date 2020-08-18
 */
public interface ModelMapper extends BaseMapper<ModelDO> {


    /**
     * 查找已绑定的formId
     *
     * @param companyId
     * @return
     */
    List<String> selectFormIdList(@Param("companyId") String companyId);

    /**
     * 分页查询模型
     * @param page
     * @param model
     * @return
     */
    IPage<ModelDTO> selectPageModel(Page page, @Param("model") ModelDO model);

    /**
     * 查询最大版本号
     *
     * @param processKey
     * @return
     */
    Integer getMaxVersion(@Param("processKey") String processKey);

    ModelDO findModelById(@Param("id")String id);
}
