package com.sjz.partbuilding.enums;

import io.swagger.models.auth.In;
import lombok.Getter;

/**
 * <p>
 *
 * </p>
 *
 * @author xiangmx
 * @createTime 2023/4/10 11:36
 **/
@Getter
public enum EducationEnum {
    OTHER(1, "其他"),
    SENIOR_MIDDLE_SCHOOL(2, "高中"),
    JUNIOR_COLLEGE(3, "专科"),
    UNDERGRADUATE(4, "本科"),
    POSTGRADUATE(5, "研究生"),
    DOCTOR(6, "博士");

    private Integer code;
    private String value;

    EducationEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Integer getCode(EducationEnum educationEnum){
        return educationEnum.code;
    }

    public static Integer getCodeByValue(String value) {
        EducationEnum[] enums = EducationEnum.values();
        for (EducationEnum typeEnum : enums) {
            if (typeEnum.value.equals(value)) {
                return typeEnum.code;
            }
        }
        return null;
    }

    public static String getValueByCode(Integer code) {
        EducationEnum[] enums = EducationEnum.values();
        for (EducationEnum typeEnum : enums) {
            if (typeEnum.code.equals(code)) {
                return typeEnum.value;
            }
        }
        return null;
    }
}
