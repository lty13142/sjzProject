package com.sjz.evaluations.service;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.evaluations.model.entity.GrDistrict;

import java.util.List;


/**
 * 管区Service接口
 * 
 * @author guozhilin
 * @date 2023-04-04
 */
public interface GrDistrictService extends IService<GrDistrict>{

    /**
     * 新增管区
     * 
     * @param grDistrict 管区
     * @return 结果
     */
    boolean saveGrDistrict(GrDistrict grDistrict);

    /**
     * 修改管区
     * 
     * @param grDistrict 管区
     * @return 结果
     */
    boolean updateGrDistrict(GrDistrict grDistrict);

    /**
     * 删除管区信息
     * 
     * @param id 管区ID
     * @return 结果
     */
    boolean deleteGrDistrict(String id);

    /**
     * 查询管区
     *
     * @param id 管区ID
     * @return 管区
     */
    GrDistrict findGrDistrictById(String id);

    /**
     * 查询管区列表
     *
     * @param grDistrict 管区
     * @return 管区集合
     */
    List<GrDistrict> findGrDistrictList(GrDistrict grDistrict);

    /**
     * 分页查询管区列表
     * @param page 分页参数
     * @param grDistrict 管区
     * @return 管区集合
     */
    PageT<GrDistrict> findGrDistrictPage(PageT<GrDistrict> page, GrDistrict grDistrict);
}
