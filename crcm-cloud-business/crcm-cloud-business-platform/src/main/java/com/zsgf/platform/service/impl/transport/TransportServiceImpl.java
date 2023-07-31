package com.zsgf.platform.service.impl.transport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.transport.TransportMapper;
import com.zsgf.platform.model.entity.transport.Transport;
import com.zsgf.platform.service.transport.TransportService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 废物运输信息Service业务层处理
 *
 * @author zzyt
 * @date 2023-02-22
 */
@Service
public class TransportServiceImpl extends ServiceImpl<TransportMapper, Transport> implements TransportService {


    /**
     * 新增废物运输信息
     *
     * @param transport 废物运输信息
     * @return 结果
     */
    @Override
    public boolean saveTransport(Transport transport) {
        return this.save(transport);
    }

    /**
     * 修改废物运输信息
     *
     * @param transport 废物运输信息
     * @return 结果
     */
    @Override
    public boolean updateTransport(Transport transport) {
        return this.updateById(transport);
    }

    /**
     * 删除废物运输信息信息
     *
     * @param id 废物运输信息ID
     * @return 结果
     */
    @Override
    public boolean deleteTransport(String id) {
        return this.removeById(id);
    }

    /**
     * 查询废物运输信息
     *
     * @param id 废物运输信息ID
     * @return 废物运输信息
     */
    @Override
    public Transport findTransportById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询废物运输信息列表
     *
     * @param transport 废物运输信息
     * @return 废物运输信息
     */
    @Override
    public List<Transport> findTransportList(Transport transport) {
        LambdaQueryWrapper<Transport> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询废物运输信息
     *
     * @param page      分页参数
     * @param transport 废物运输信息
     * @return 废物运输信息
     */
    @Override
    public PageT<Transport> findTransportPage(PageT<Transport> page, Transport transport) {
        LambdaQueryWrapper<Transport> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
