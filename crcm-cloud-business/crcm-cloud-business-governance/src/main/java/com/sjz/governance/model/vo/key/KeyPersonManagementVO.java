package com.sjz.governance.model.vo.key;

import com.crcm.core.constant.DicConstant;
import com.sjz.governance.model.entity.key.KeyPersonManagement;
import com.sjz.governance.utils.UtilDic;
import com.sjz.governance.utils.UtilSysArea;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;

/**
 * 综合治理_重点人员管理对象Vo
 *
 * @author pengl
 * @date 2023-04-03
 */
@Data
public class KeyPersonManagementVO extends KeyPersonManagement {


    public String getVillageCh(){
        return UtilSysArea.getVillageChById(this.getVillageId());
    }

    public String getGenderCh() {
        return UtilDic.getDictName(DicConstant.SEX.CODE, this.getGender());
    }

    public String getKeyPersonTypeCh() {
        return UtilDic.getDictName(DicConstant.KEY_PERSON_TYPE.CODE, this.getKeyPersonType());
    }

}
