package com.crcm.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.admin.model.entity.Agreement;

import java.util.List;

/**
 * 协议管理Mapper接口
 * 
 * @author cb
 * @date 2023-06-28
 */
public interface AgreementMapper extends BaseMapper<Agreement> {


	Agreement getLastByType(Agreement agreement);

	List<Agreement> getListByType(Agreement agreement);
}
