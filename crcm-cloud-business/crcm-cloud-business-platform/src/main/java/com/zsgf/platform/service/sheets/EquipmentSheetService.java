package com.zsgf.platform.service.sheets;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.dto.sheets.EquipmentSheetDTO;
import com.zsgf.platform.dto.sheets.EquipmentSheetUploadDTO;
import com.zsgf.platform.model.entity.sheets.EquipmentSheet;
import com.zsgf.platform.vo.sheets.EquipmentSheetFormWorkVo;

import java.util.List;

/**
 * 设备确认单Service接口
 *
 * @author gzl
 * @date 2023-03-30
 */
public interface EquipmentSheetService extends IService<EquipmentSheet> {

    /**
     * 新增设备确认单
     *
     * @param equipmentSheet 设备确认单
     * @return 结果
     */
    boolean saveEquipmentSheet(EquipmentSheet equipmentSheet);

    /**
     * 修改设备确认单
     *
     * @param equipmentSheet 设备确认单
     * @return 结果
     */
    boolean updateEquipmentSheet(EquipmentSheet equipmentSheet);

    /**
     * 删除设备确认单信息
     *
     * @param id 设备确认单ID
     * @return 结果
     */
    boolean deleteEquipmentSheet(Long id);

    /**
     * 查询设备确认单
     *
     * @return 设备确认单
     */
    EquipmentSheetFormWorkVo getInfo();

    /**
     * 查询设备确认单列表
     *
     * @param equipmentSheet 设备确认单
     * @return 设备确认单集合
     */
    List<EquipmentSheet> findEquipmentSheetList(EquipmentSheetDTO equipmentSheet);

    /**
     * 分页查询设备确认单列表
     *
     * @param page           分页参数
     * @param equipmentSheet 设备确认单
     * @return 设备确认单集合
     */
    PageT<EquipmentSheet> findEquipmentSheetPage(PageT<EquipmentSheet> page, EquipmentSheetDTO equipmentSheet);

    /**
     * 上传设备确认单
     *
     * @param equipmentSheet 确认单
     * @return 结果
     */
    boolean upload(EquipmentSheetUploadDTO equipmentSheet);

    /**
     * 上传状态
     * @Author GZL
     * @Date 2023/3/30 16:42
     * @return 是否上传
     **/
    boolean getUploadStatus();
}
