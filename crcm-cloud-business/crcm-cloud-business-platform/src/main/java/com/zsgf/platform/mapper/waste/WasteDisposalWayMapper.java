package com.zsgf.platform.mapper.waste;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.dto.waste.WasteDisposalWayDTO;
import com.zsgf.platform.model.entity.waste.WasteDisposalWay;
import com.zsgf.platform.vo.waste.WasteDisposalWayVo;
import org.apache.ibatis.annotations.Param;

/**
 * 废物处置方式Mapper接口
 *
 * @author gzl
 * @date 2023-03-30
 */
public interface WasteDisposalWayMapper extends BaseMapper<WasteDisposalWay> {
    PageT<WasteDisposalWayVo> findWasteDisposalWayPage(@Param("page") PageT<WasteDisposalWayVo> page, @Param("dto") WasteDisposalWayDTO wasteDisposalWay);
}
