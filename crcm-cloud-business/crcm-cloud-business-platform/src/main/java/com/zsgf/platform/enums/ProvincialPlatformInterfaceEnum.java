package com.zsgf.platform.enums;

import com.crcm.core.constant.SystemConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 省平台数据回流接口枚举
 *
 * @Author GZL
 * @Date 2023/2/10 9:52
 **/
@AllArgsConstructor
@Getter
public enum ProvincialPlatformInterfaceEnum {

    // 企业信息
    COMPANY("数据共享_固废企业信息", "202010271738507af8da0f6f584fd8a18449ecc3579d1a/V1", 500L,
            SystemConstant.DATA_RETURN_CYCLE_TYPE.DAY),
    COMPANY_ROLE("数据共享_固废企业信息_企业角色类型", "202011121504163269a493c3734b3eb89f36cba9b8a49b/V1", 500L,
            SystemConstant.DATA_RETURN_CYCLE_TYPE.DAY),
    // 危险废物名录
    WASTE_LIST("数据共享_危险废物名录", "202010301207235700a3600046494da8cd57ec6aa791d0/V1", 500L,
            SystemConstant.DATA_RETURN_CYCLE_TYPE.YEAR),
    // 经营许可证
    BUSINESS_LICENSE("数据共享_危险废物_经营许可证_01基本信息", "20201113231830d4819b17bf3b418bac54136725f68587/V1", 500L,
            SystemConstant.DATA_RETURN_CYCLE_TYPE.MONTH),
    BUSINESS_LICENSE_OPERATING_FACILITIES("数据共享_危险废物_经营许可证_02经营设施信息", "20201113232118ff5ae9f066fa43529b67715109e3b0b3/V1", 500L,
            SystemConstant.DATA_RETURN_CYCLE_TYPE.MONTH),
    BUSINESS_LICENSE_MAIN_EQUIPMENT("数据共享_危险废物_经营许可证_03主要设备装置情况", "20201113232259e799befc91b1426c8755a610026ede58/V1", 500L,
            SystemConstant.DATA_RETURN_CYCLE_TYPE.MONTH),
    BUSINESS_LICENSE_WASTE("数据共享_危险废物_经营许可证_04废物信息", "2020111323245995d6fa76637e4a7b805ceb20b73f2077/V1", 500L,
            SystemConstant.DATA_RETURN_CYCLE_TYPE.MONTH);

    /**
     * 接口名称
     */
    private final String interfaceName;
    /**
     * 接口地址
     */
    private final String interfacePath;
    /**
     * 最大页容量
     */
    private final Long maxPageSize;
    /**
     * 查询周期
     */
    private final String queryCycle;
}
