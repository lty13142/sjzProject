package com.crcm.bpm.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * todo:
 *
 * @author : zwj
 * @date : 2020/10/29
 */
@Data
@ToString
@ApiModel("流程-角色表")
public class ProcessRoleVO implements Serializable {

    private static final long serialVersionUID = -1904908751341094576L;
    /**角色id*/
    private String roleId;
    /**公司id*/
    private String companyId;
    /**流程id*/
    private String processIdList;
}
