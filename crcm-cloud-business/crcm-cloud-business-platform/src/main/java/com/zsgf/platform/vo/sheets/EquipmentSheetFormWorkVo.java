package com.zsgf.platform.vo.sheets;

import com.zsgf.platform.model.entity.sheets.EquipmentSheetDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName EquipmentSheetFormWorkVo
 * @Description 确认书模板信息
 * @Author GZL
 * @Date 2023/3/30 11:48
 * @Version 1.0
 **/
@Data
public class EquipmentSheetFormWorkVo {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String companyName;

    /**
     * 附件名称
     */
    @ApiModelProperty(value = "附件名称")
    private String fileName;

    /**
     * 是否上传（0：否，1：是）
     */
    @ApiModelProperty(value = "是否上传（0：否，1：是）")
    private String uploadFlag;

    /**
     * 附件id
     */
    @ApiModelProperty(value = "附件id")
    private String fileId;

    /**
     * 监控摄像头数量
     */
    @ApiModelProperty(value = "监控摄像头数量")
    private Integer cameraTotal;

    /**
     * 电子称/叉车称数量
     */
    @ApiModelProperty(value = "电子称/叉车称数量")
    private Integer scaleTotal;

    /**
     * 蓝牙打印机数量
     */
    @ApiModelProperty(value = "蓝牙打印机数量")
    private Integer printerTotal;

    /**
     * 手持终端数量
     */
    @ApiModelProperty(value = "手持终端数量")
    private Integer terminalTotal;

    /**
     * 清单详细列表
     */
    @ApiModelProperty(value = "清单详细列表")
    private List<EquipmentSheetDetail> sheetDetailList;

}
