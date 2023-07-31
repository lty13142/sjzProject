package com.sjz.education.model.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.sjz.education.model.entity.EduProducts;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 德育积分_积分兑换记录
 * @author: sssccc
 * @TableName edu_exchange_record
 */
@Data
@Accessors(chain = true)
public class ExchangeRecordVO extends EduProducts implements Serializable {
    /**
     * 记录id
     */
    private String recordId;
    /**
     * 所需积分
     */
    private Integer needPoint;

    /**
     * 总积分消耗
     */
    private Integer totalPoint;

    /**
     * 兑换数量
     */
    private Integer exchangeNumber;

    /**
     * 兑换码
     */
    private String exchangeCode;

    /**
     * 核销码
     */
    private String writeOffCode;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}