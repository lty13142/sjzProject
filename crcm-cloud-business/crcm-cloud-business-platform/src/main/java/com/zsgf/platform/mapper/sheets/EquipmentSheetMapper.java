package com.zsgf.platform.mapper.sheets;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsgf.platform.model.entity.sheets.EquipmentSheet;
import com.zsgf.platform.vo.sheets.EquipmentSheetFormWorkVo;

/**
 * 设备确认单Mapper接口
 *
 * @author gzl
 * @date 2023-03-30
 */
public interface EquipmentSheetMapper extends BaseMapper<EquipmentSheet> {

    /**
     * 根据企业id查询设备确认单
     *
     * @return 设备确认单
     * @Author GZL
     * @Date 2023/3/30 14:10
     * @Param companyId 企业id
     **/
    EquipmentSheetFormWorkVo selectByCompanyId(String companyId);
}
