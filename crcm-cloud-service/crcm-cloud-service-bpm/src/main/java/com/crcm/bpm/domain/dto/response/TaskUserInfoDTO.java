package com.crcm.bpm.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName TaskUserInfoDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：Administrator
 * @Date：2020/10/21
 **/
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskUserInfoDTO {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
}
