package com.sjz.evaluations.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.evaluations.model.entity.Examine;
import com.sjz.evaluations.model.vo.ExamineVo;
import org.apache.ibatis.annotations.Param;

/**
 * 考核Mapper接口
 * 
 * @author guozhilin
 * @date 2023-04-06
 */
public interface ExamineMapper extends BaseMapper<Examine> {


    PageT<ExamineVo> getAssessedPage(PageT<ExamineVo> page, @Param("examine") Examine examine);
}
