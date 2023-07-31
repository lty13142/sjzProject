package com.crcm.bpm.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName PostUserDTO
 * @Description：岗位用户数据传输类
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/23
 **/
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostUserDTO {

    /**
     * 员工ID
     */
    private String userId;
    /**
     * 员工名称
     */
    private String userName;
    /**
     * 岗位ID
     */
    private String postId;
    /**
     * 岗位名称
     */
    private String postName;
}
