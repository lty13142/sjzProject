package com.sjz.evaluations.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjz.evaluations.model.entity.VillageExamine;
import com.sjz.evaluations.model.vo.VillageIndicatorsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 村级考核实体Mapper接口
 * 
 * @author zzyt
 * @date 2023-04-25
 */
public interface VillageExamineMapper extends BaseMapper<VillageExamine> {

    /**
     * 根据id查询村级考核扩展类
     *
     * @param id 村级考核id
     * @return 村级考核扩展类
     */
    VillageIndicatorsVO findVillageExamineById(@Param("id") String id);

    List<VillageExamine> getAllVillageExamine(@Param("theYear") int theYear);
}
