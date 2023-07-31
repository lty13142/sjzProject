package com.sjz.education.mapper;

import com.sjz.education.model.entity.EduExchangeRecord;
import com.sjz.education.model.entity.EduProducts;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author SSSCCCC
* @description 针对表【edu_products(积分可兑换商品)】的数据库操作Mapper
* @createDate 2023-04-03 14:35:16
* @Entity generator.model.entity.EduProducts
*/
public interface EduProductsMapper extends BaseMapper<EduProducts> {
    /**
     * 根据商品id查找兑换记录
     * @param id
     * @return
     */
    List<EduExchangeRecord> findByProductId(@Param("id") String id);

    /**
     * 根据商品ids查找兑换记录
     * @param ids
     * @return
     */
    List<EduExchangeRecord> findByProductIds(@Param("ids") List<String> ids);
}




