package com.sjz.evaluations.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.crcm.core.constant.DicConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sjz.evaluations.util.UtilDic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @author yzw
 * @data 2023/4/10
 * @apiNote
 */
@Data
public class ExamineVo {

    @ApiModelProperty(value = "主键", required = true)
    private String id;

    /** 名称 */
    @ApiModelProperty(value = "名称")
    private String name;

    /** 年份 */
    @ApiModelProperty(value = "年份")
    private String yearly;

    /** 发布状态 */
    @ApiModelProperty(value = "发布状态 字典code(RELEASE_STATUS)")
    private String releaseStatus;

    @ApiModelProperty(value = " 是否完成 0 未完成  1 已完成")
    private String complete;

    @ApiModelProperty(value = "是否确认 字典code(CONFIRM_STATUS)")
    private String confirm;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;



    public String getConfirmCh(){
        return UtilDic.getDictName(DicConstant.CONFIRM_STATUS.CODE,this.getConfirm());
    }

    public String getCompleteCh(){return UtilDic.getDictName(DicConstant.COMPLETE_STATUS.CODE,this.getComplete());}
}
