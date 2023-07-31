package com.crcm.bpm.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName PostInfoDTO
 * @Description：部门信息传输类
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/26
 **/
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostInfoDTO implements Serializable {

    private static final long serialVersionUID = 179736071913108928L;
    /**
     * 部门ID
     */
    private String postId;
    /**
     * 部门名称
     */
    private String postName;
}
