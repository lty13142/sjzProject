package com.crcm.develop.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.develop.tool.entity.DatasourceConfEntity;

/**
 * 数据源配置
 *
 * @author diaoy
 * @date 2020-04-01 17:12:29
 */
public interface DatasourceConfService extends IService<DatasourceConfEntity> {

    boolean saveDataSource(DatasourceConfEntity datasourceConfEntity);

    boolean updateDataSource(DatasourceConfEntity datasourceConfEntity);

    boolean testDataSource(Long id);
}
