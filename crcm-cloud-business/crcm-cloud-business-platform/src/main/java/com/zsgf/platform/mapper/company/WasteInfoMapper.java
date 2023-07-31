package com.zsgf.platform.mapper.company;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.dto.company.WasteInfoQueryDTO;
import com.zsgf.platform.model.entity.company.WasteInfo;
import com.zsgf.platform.vo.company.WasteInfoVo;
import org.apache.ibatis.annotations.Param;

/**
 * 企业废物信息Mapper接口
 *
 * @author zzyt
 * @date 2023-02-22
 */
public interface WasteInfoMapper extends BaseMapper<WasteInfo> {

    PageT<WasteInfoVo> findWasteInfoPage(@Param("page") PageT<WasteInfoVo> page, @Param("wasteInfo") WasteInfoQueryDTO wasteInfo);
}
