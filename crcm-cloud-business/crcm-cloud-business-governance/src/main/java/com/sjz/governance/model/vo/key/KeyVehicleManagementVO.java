package com.sjz.governance.model.vo.key;

import com.crcm.core.constant.DicConstant;
import com.sjz.governance.model.entity.key.KeyVehicleManagement;
import com.sjz.governance.utils.UtilDic;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 综合治理_重点车辆管理对象Vo
 *
 * @author pengl
 * @date 2023-04-03
 */
@Data
public class KeyVehicleManagementVO extends KeyVehicleManagement {

    public String getVehicleBrandCh() {
        return UtilDic.getDictName(DicConstant.VEHICLE_BRAND.CODE, this.getVehicleBrand());
    }

    public String getColorCh() {
        return UtilDic.getDictName(DicConstant.VEHICLE_COLOR.CODE, this.getColor());
    }

}
