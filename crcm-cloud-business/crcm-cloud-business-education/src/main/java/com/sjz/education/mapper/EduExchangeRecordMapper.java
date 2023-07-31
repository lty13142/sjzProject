package com.sjz.education.mapper;

import com.sjz.education.model.dto.EduPointExchangeDTO;
import com.sjz.education.model.entity.EduExchangeRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjz.education.model.vo.EchartsVO;
import com.sjz.education.model.vo.StatisticsVO;

import java.util.List;

/**
* @author SSSCCCC
* @description 针对表【edu_exchange_record(德育积分_积分兑换记录)】的数据库操作Mapper
* @createDate 2023-04-06 10:31:37
* @Entity com.sjz.education.model.entity.EduExchangeRecord
*/
public interface EduExchangeRecordMapper extends BaseMapper<EduExchangeRecord> {
    /**
     * 统计当月积分兑换走势图
     * @return
     */
    List<EchartsVO> countPointsByMonth();

	/**
	 * 统计当年积分兑换走势图
	 * @return
	 */
	List<EchartsVO> countPointsByYear();

	/**
	 * 统计本月兑换记录
	 * @param startTime
	 * @return
	 */
	int selectThisCount(String startTime);

	/**
	 * 统计上月兑换记录
	 * @param startTime
	 * @return
	 */
	int selectLastCount(String startTime);

	/**
	 * 查询总兑换记录
	 * @return
	 */
	int selectAllCount();

	/**
	 * 查询上年度兑换记录
	 * @param startTime
	 * @return
	 */
	int selectLastYearCount(String startTime);

	List<StatisticsVO> selectStaticList(EduPointExchangeDTO dto);

	List<EduExchangeRecord> selectAllPass();

	List<EduExchangeRecord> selectOwnPass(String userId);

}




