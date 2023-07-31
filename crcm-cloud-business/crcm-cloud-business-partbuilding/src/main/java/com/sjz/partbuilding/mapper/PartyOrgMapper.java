package com.sjz.partbuilding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjz.partbuilding.model.entity.PartyOrg;
import com.sjz.partbuilding.model.vo.PartyOrgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 党组织详细信息mapper
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/24 16:52
 */
public interface PartyOrgMapper extends BaseMapper<PartyOrg> {

    /**
     * 真实删除
     *
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    /**
     * 根据orgId获取基本信息
     *
     * @param orgId 组织id
     * @return
     */
    List<PartyOrg> getBaseMessageByOrgId(@Param("orgId") String orgId);

    /**
     * 根据orgId获取partyOrgVo
     *
     * @param partyOrg
     * @return
     */
    PartyOrgVo getPartyOrgVoByOrgId(@Param("partyOrg") PartyOrg partyOrg);

    /**
     * 通过ID查询组织信息
     * @param orgId
     * @return
     */
    PartyOrgVo getPartyOrgCompany(String orgId);

    /**
     * 通过ID查询组织信息
     * @param orgId
     * @return
     */
    List<PartyOrgVo> getPartyOrgCompany0(@Param("orgIds")String[] orgIds);
}
