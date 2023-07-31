package com.crcm.bpm.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ComponyDTO
 * @Description：公司信息传输类
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
public class CompanyTreeDTO implements Serializable {
    private static final long serialVersionUID = -5096812853795910572L;

    /**
     * 公司ID
     */
    private String companyId;
    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 子公司
     */
    private List<CompanyTreeDTO> children;
}
