package com.zsgf.platform.service.transport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.transport.Transport;

import java.util.List;

/**
 * 废物运输信息Service接口
 *
 * @author zzyt
 * @date 2023-02-22
 */
public interface TransportService extends IService<Transport> {

    /**
     * 新增废物运输信息
     *
     * @param transport 废物运输信息
     * @return 结果
     */
    boolean saveTransport(Transport transport);

    /**
     * 修改废物运输信息
     *
     * @param transport 废物运输信息
     * @return 结果
     */
    boolean updateTransport(Transport transport);

    /**
     * 删除废物运输信息信息
     *
     * @param id 废物运输信息ID
     * @return 结果
     */
    boolean deleteTransport(String id);

    /**
     * 查询废物运输信息
     *
     * @param id 废物运输信息ID
     * @return 废物运输信息
     */
    Transport findTransportById(String id);

    /**
     * 查询废物运输信息列表
     *
     * @param transport 废物运输信息
     * @return 废物运输信息集合
     */
    List<Transport> findTransportList(Transport transport);

    /**
     * 分页查询废物运输信息列表
     *
     * @param page      分页参数
     * @param transport 废物运输信息
     * @return 废物运输信息集合
     */
    PageT<Transport> findTransportPage(PageT<Transport> page, Transport transport);
}
