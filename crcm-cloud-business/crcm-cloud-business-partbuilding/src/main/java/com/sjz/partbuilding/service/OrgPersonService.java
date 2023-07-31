package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.partbuilding.model.entity.OrgPerson;
import com.sjz.partbuilding.model.vo.PersonTreeVo;

import java.util.List;

public interface OrgPersonService extends IService<OrgPerson> {

    int saveOrgPerson(OrgPerson orgPerson);

    int updateOrgPerson(OrgPerson orgPerson);

    int deleteById(String id);

    int realDelete(String id);

    OrgPerson findById(String id);

    List<OrgPerson> findList(OrgPerson orgPerson);

    IPage<OrgPerson> findPage(Page page, OrgPerson orgPerson);

    PersonTreeVo getOrgPersonTree(OrgPerson orgPerson);

    List<OrgPerson> getPartyGroup();
}
