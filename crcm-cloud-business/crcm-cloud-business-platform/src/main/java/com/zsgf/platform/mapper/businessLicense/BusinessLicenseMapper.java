package com.zsgf.platform.mapper.businessLicense;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.dto.businessLicense.BusinessLicenseDTO;
import com.zsgf.platform.model.entity.businessLicense.BusinessLicense;
import com.zsgf.platform.vo.businessLicense.BusinessLicenseVo;
import org.apache.ibatis.annotations.Param;

/**
 * 危险废物_经营许可证_01基本信息Mapper接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessLicenseMapper extends BaseMapper<BusinessLicense> {

    PageT<BusinessLicenseVo> findBusinessLicensePage(@Param("page") PageT<BusinessLicenseVo> page, @Param("dto") BusinessLicenseDTO businessLicense);
}
