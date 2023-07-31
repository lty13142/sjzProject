package com.zsgf.platform.service.company;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.company.HazardousChemicals;

import java.util.List;

/**
 * 危险化学品备案信息Service接口
 *
 * @author gzl
 * @date 2023-03-24
 */
public interface HazardousChemicalsService extends IService<HazardousChemicals> {

    /**
     * 新增危险化学品备案信息
     *
     * @param hazardousChemicals 危险化学品备案信息
     * @return 结果
     */
    boolean saveHazardousChemicals(HazardousChemicals hazardousChemicals);

    /**
     * 修改危险化学品备案信息
     *
     * @param hazardousChemicals 危险化学品备案信息
     * @return 结果
     */
    boolean updateHazardousChemicals(HazardousChemicals hazardousChemicals);

    /**
     * 删除危险化学品备案信息信息
     *
     * @param id 危险化学品备案信息ID
     * @return 结果
     */
    boolean deleteHazardousChemicals(String id);

    /**
     * 查询危险化学品备案信息
     *
     * @param id 危险化学品备案信息ID
     * @return 危险化学品备案信息
     */
    HazardousChemicals findHazardousChemicalsById(String id);

    /**
     * 查询危险化学品备案信息列表
     *
     * @param hazardousChemicals 危险化学品备案信息
     * @return 危险化学品备案信息集合
     */
    List<HazardousChemicals> findHazardousChemicalsList(HazardousChemicals hazardousChemicals);

    /**
     * 分页查询危险化学品备案信息列表
     *
     * @param page               分页参数
     * @param hazardousChemicals 危险化学品备案信息
     * @return 危险化学品备案信息集合
     */
    PageT<HazardousChemicals> findHazardousChemicalsPage(PageT<HazardousChemicals> page, HazardousChemicals hazardousChemicals);
}
