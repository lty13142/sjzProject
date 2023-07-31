package com.crcm.bpm.domain.dto.response;

import com.crcm.bpm.domain.dto.BaseResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName ReturnNodeDTO
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：Administrator
 * @Date：2020/10/20
 **/
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnNodeDTO extends BaseResponseDTO {

    private static final long serialVersionUID = 227255324726616515L;
    /**
     * 节点ID
     */
    private String nodeId;
    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 节点分配人员ID集合
     */
    private List<String> assigneeUserIdList;

    /**
     *  节点分配人员姓名集合
     */
    private List<String> assigneeUserNameList;
}
