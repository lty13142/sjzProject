package com.crcm.develop.tool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.develop.common.constant.BaseConstants;
import com.crcm.develop.core.jdbc.JDBCUtil;
import com.crcm.develop.exception.CheckedException;
import com.crcm.develop.tool.entity.DatasourceConfEntity;
import com.crcm.develop.tool.mapper.DatasourceConfMapper;
import com.crcm.develop.tool.service.DatasourceConfService;
import com.crcm.develop.tool.util.UtilAES;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * 数据源配置
 *
 * @author diaoy
 * @date 2020-04-01 17:12:29
 */
@Slf4j
@Service
public class DatasourceConfServiceImpl extends ServiceImpl<DatasourceConfMapper, DatasourceConfEntity> implements DatasourceConfService {


    @Override
    public boolean saveDataSource(DatasourceConfEntity datasourceConfEntity) {
        if (StringUtils.isNotBlank(datasourceConfEntity.getPassword())) {
            datasourceConfEntity.setPassword(UtilAES.aesEncrypt(datasourceConfEntity.getPassword()));
        }
        return this.baseMapper.insert(datasourceConfEntity) >= 1;
    }

    @Override
    public boolean updateDataSource(DatasourceConfEntity datasourceConfEntity) {
        if (StringUtils.isNotBlank(datasourceConfEntity.getPassword())) {
            // 先解密一遍，解密失败则说明是未加密的密码
            String password = UtilAES.aesDecrypt(datasourceConfEntity.getPassword());
            if (StringUtils.isBlank(password)) {
                datasourceConfEntity.setPassword(UtilAES.aesEncrypt(datasourceConfEntity.getPassword()));
            }
        } else {
            // 如果是base64加密的说明没有修改密码
            datasourceConfEntity.setPassword(null);
        }
        return this.baseMapper.updateById(datasourceConfEntity) >= 1;
    }

    @Override
    public boolean testDataSource(Long id) {
        DatasourceConfEntity source = this.getById(id);
        if (Objects.isNull(source)) {
            throw new CheckedException("数据库不存在");
        }
        JDBCUtil jdbcUtil = JDBCUtil.getInstance();
        Connection connection = null;
        try {
            if ("mysql".equals(source.getDbType())) {
                connection = jdbcUtil.getConnection(source.getUrl(), source.getUsername(), UtilAES.aesDecrypt(source.getPassword()), BaseConstants.MYSQL_DRIVER_NAME);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection != null;
    }
}
