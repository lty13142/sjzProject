package com.zsgf.platform.service.impl.sheets;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.sso.domain.AuthUser;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.utils.UtilDataFormat;
import com.zsgf.platform.dto.sheets.EquipmentSheetDTO;
import com.zsgf.platform.dto.sheets.EquipmentSheetUploadDTO;
import com.zsgf.platform.mapper.sheets.EquipmentSheetMapper;
import com.zsgf.platform.model.entity.sheets.EquipmentSheet;
import com.zsgf.platform.model.entity.sheets.EquipmentSheetDetail;
import com.zsgf.platform.service.sheets.EquipmentSheetDetailService;
import com.zsgf.platform.service.sheets.EquipmentSheetService;
import com.zsgf.platform.utils.SecurityBusinessUtil;
import com.zsgf.platform.vo.sheets.EquipmentSheetFormWorkVo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 设备确认单Service业务层处理
 *
 * @author gzl
 * @date 2023-03-30
 */
@Service
@AllArgsConstructor
public class EquipmentSheetServiceImpl extends ServiceImpl<EquipmentSheetMapper, EquipmentSheet> implements EquipmentSheetService {

    private final EquipmentSheetDetailService equipmentSheetDetailService;

    /**
     * 新增设备确认单
     *
     * @param equipmentSheet 设备确认单
     * @return 结果
     */
    @Override
    public boolean saveEquipmentSheet(EquipmentSheet equipmentSheet) {
        return this.save(equipmentSheet);
    }

    /**
     * 修改设备确认单
     *
     * @param equipmentSheet 设备确认单
     * @return 结果
     */
    @Override
    public boolean updateEquipmentSheet(EquipmentSheet equipmentSheet) {
        return this.updateById(equipmentSheet);
    }

    /**
     * 删除设备确认单信息
     *
     * @param id 设备确认单ID
     * @return 结果
     */
    @Override
    public boolean deleteEquipmentSheet(Long id) {
        EquipmentSheet equipmentSheetById = this.baseMapper.selectById(id);
        equipmentSheetById.setId(id);
        equipmentSheetById.setDeleted(SystemConstant.YES_OR_NO.YES);
        boolean updateById = this.updateById(equipmentSheetById);
        if (updateById) {
            equipmentSheetDetailService.deleteByCompanyId(equipmentSheetById.getCompanyId());
        }
        return updateById;
    }

    /**
     * 查询设备确认单
     *
     * @return 设备确认单
     */
    @Override
    public EquipmentSheetFormWorkVo getInfo() {
        AuthUser currentUser = SecurityBusinessUtil.getCurrentUserInfo(true);
//        EquipmentSheetFormWorkVo equipmentSheetFormWorkVo = this.baseMapper.selectByCompanyId(currentUser.getCompanyId());
//        if (Objects.isNull(equipmentSheetFormWorkVo)) {
//            return new EquipmentSheetFormWorkVo();
//        }
//        EquipmentSheetDetail detail = new EquipmentSheetDetail();
////        detail.setCompanyId(currentUser.getCompanyId());
//        //设备清单
//        List<EquipmentSheetDetail> equipmentSheetDetailList = equipmentSheetDetailService.findEquipmentSheetDetailList(detail);
//        equipmentSheetFormWorkVo.setSheetDetailList(equipmentSheetDetailList);
//        // 电子称/叉车称数量
//        equipmentSheetFormWorkVo.setScaleTotal(UtilDataFormat.getListSum(equipmentSheetDetailList,
//                data -> Objects.equals(SystemConstant.EQUIPMENT_SHEET_EQUIPMENT_TYPE.ELECTRONIC_SCALE, data.getEquipmentType())
//                        || Objects.equals(SystemConstant.EQUIPMENT_SHEET_EQUIPMENT_TYPE.FORKLIFT_SCALE, data.getEquipmentType()),
//                EquipmentSheetDetail::getEquipmentNumber));
//        // 监控摄像头数量
//        equipmentSheetFormWorkVo.setCameraTotal(UtilDataFormat.getListSum(equipmentSheetDetailList,
//                data -> Objects.equals(SystemConstant.EQUIPMENT_SHEET_EQUIPMENT_TYPE.CAMERA, data.getEquipmentType()),
//                EquipmentSheetDetail::getEquipmentNumber));
//        // 蓝牙打印机数量
//        equipmentSheetFormWorkVo.setPrinterTotal(UtilDataFormat.getListSum(equipmentSheetDetailList,
//                data -> Objects.equals(SystemConstant.EQUIPMENT_SHEET_EQUIPMENT_TYPE.BLUETOOTH_PRINTER, data.getEquipmentType()),
//                EquipmentSheetDetail::getEquipmentNumber));
//        // 手持终端数量
//        equipmentSheetFormWorkVo.setTerminalTotal(UtilDataFormat.getListSum(equipmentSheetDetailList,
//                data -> Objects.equals(SystemConstant.EQUIPMENT_SHEET_EQUIPMENT_TYPE.HANDHELD_TERMINAL, data.getEquipmentType()),
//                EquipmentSheetDetail::getEquipmentNumber));
//        return equipmentSheetFormWorkVo;
        return null;
    }

    /**
     * 查询设备确认单列表
     *
     * @param equipmentSheet 设备确认单
     * @return 设备确认单
     */
    @Override
    public List<EquipmentSheet> findEquipmentSheetList(EquipmentSheetDTO equipmentSheet) {
        LambdaQueryWrapper<EquipmentSheet> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询设备确认单
     *
     * @param page           分页参数
     * @param equipmentSheet 设备确认单
     * @return 设备确认单
     */
    @Override
    public PageT<EquipmentSheet> findEquipmentSheetPage(PageT<EquipmentSheet> page, EquipmentSheetDTO equipmentSheet) {
        AuthUser currentUser = SecurityBusinessUtil.getCurrentUserInfo();
        if (Objects.equals(SystemConstant.USER_TYPE.ENTERPRISE_ACCOUNT, currentUser.getUserType())) {
//            equipmentSheet.setCompanyId(currentUser.getCompanyId());
            equipmentSheet.setCompanyName(null);
        }
        if (Objects.equals(SystemConstant.USER_TYPE.SUPERVISE_ACCOUNT, currentUser.getUserType()) &&
                Objects.equals(SystemConstant.SUPERVISE_USER_TYPE.BRANCH_ACCOUNT, currentUser.getUserDetailType())) {
            equipmentSheet.setBelongAreaCode(currentUser.getAreaCode());
        }
        LambdaQueryWrapper<EquipmentSheet> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(equipmentSheet.getCompanyId()), EquipmentSheet::getCompanyId, equipmentSheet.getCompanyId());
        queryWrapper.like(StringUtils.isNotBlank(equipmentSheet.getCompanyName()), EquipmentSheet::getCompanyName, equipmentSheet.getCompanyName());
        queryWrapper.eq(StringUtils.isNotBlank(equipmentSheet.getBelongAreaCode()), EquipmentSheet::getBelongAreaCode, equipmentSheet.getBelongAreaCode());
        queryWrapper.orderByAsc(EquipmentSheet::getUploadFlag);
        queryWrapper.orderByAsc(EquipmentSheet::getBelongArea);
        return this.page(page, queryWrapper);
    }

    /**
     * 上传设备确认单
     *
     * @param equipmentSheet 确认单
     * @return 结果
     */
    @Override
    public boolean upload(EquipmentSheetUploadDTO equipmentSheet) {
        EquipmentSheet sheet = new EquipmentSheet();
        sheet.setId(equipmentSheet.getId());
        sheet.setFileId(equipmentSheet.getFileId());
        sheet.setFileName(equipmentSheet.getFileName());
        sheet.setUploadFlag(SystemConstant.YES_OR_NO.YES + "");
        return this.updateById(sheet);
    }

    /**
     * 上传状态
     *
     * @return 是否上传
     * @Author GZL
     * @Date 2023/3/30 16:42
     **/
    @Override
    public boolean getUploadStatus() {
        AuthUser currentUser = SecurityBusinessUtil.getCurrentUserInfo(true);
//        currentUser.getCompanyId();
        EquipmentSheetFormWorkVo sheetFormWorkVo = this.baseMapper.selectByCompanyId(null);
        return Objects.nonNull(sheetFormWorkVo) && Objects.equals(SystemConstant.YES_OR_NO.YES + "", sheetFormWorkVo.getUploadFlag());
    }
}
