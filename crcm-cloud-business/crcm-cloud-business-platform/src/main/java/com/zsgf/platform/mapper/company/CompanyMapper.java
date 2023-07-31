package com.zsgf.platform.mapper.company;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsgf.platform.model.entity.company.Company;
import com.zsgf.platform.vo.company.CompanySelectVo;

import java.util.List;

/**
 * 企业基本信息Mapper接口
 *
 * @author zzyt
 * @date 2023-02-22
 */
public interface CompanyMapper extends BaseMapper<Company> {


    List<CompanySelectVo> getSelectList(String companyLabel);
}
