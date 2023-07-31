package com.crcm.bpm.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 流程收藏表
 * </p>
 *
 * @author wxl
 * @since 2021-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bpm_process_collection")
public class ProcessCollectionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 员工id
     */
    private String employeeId;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 流程表id
     */
    private Long processId;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}
