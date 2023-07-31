package com.sjz.partbuilding.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.file.api.feign.RemoteFileService;
import com.sjz.partbuilding.mapper.HonorMapper;
import com.sjz.partbuilding.model.entity.Honor;
import com.sjz.partbuilding.model.entity.UserDetail;
import com.sjz.partbuilding.model.vo.AttachmentsVo;
import com.sjz.partbuilding.model.vo.HonorVo;
import com.sjz.partbuilding.service.AttachmentsService;
import com.sjz.partbuilding.service.HonorService;
//import com.zzyt.core.common.utils.UtilBean;
//import com.zzyt.model.entity.system.org.*;
//import com.zzyt.model.vo.system.attachment.*;
//import com.zzyt.model.vo.system.user.UserBaseInfoVo;
//import com.zzyt.model.vo.zhdj.*;
//import com.zzyt.service.system.org.*;
//import com.zzyt.service.system.user.*;
import com.sjz.partbuilding.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@Transactional
public class HonorServiceImpl extends ServiceImpl<HonorMapper, Honor> implements HonorService {


    @Autowired
    private AttachmentsService attachmentsService;

//    @Autowired
//    private OrgService orgService;
//
//    @Autowired
//    private UserService userService;
//
    @Autowired
    private UserDetailService userDetailService;

    @Override
    public int saveHonor(Honor honor) {
        return this.baseMapper.insert(honor);
    }

    @Override
    public int updateHonor(Honor honor) {
        return this.baseMapper.updateById(honor);
    }

    @Override
    public int deleteById(String id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    @Override
    public HonorVo findById(String id) {
        HonorVo honorVo=new HonorVo();
        Honor honor = this.baseMapper.selectById(id);
        if(ObjectUtil.isNotEmpty(honor)) {
            if (ObjectUtil.isNotEmpty(honor.getAttachmentIds())) {
                honorVo.setAttachments(attachmentsService.getAttachmentListByIds(honor.getAttachmentIds()));
            }
//            if (ObjectUtil.isNotEmpty(honor.getComBy())) {
//                Org byId = orgService.getById(honor.getComBy());
//                honorVo.setComName(byId.getName());
//            }
            honorVo.setId(honor.getId());
            honorVo.setHonorary(honor.getHonorary());
            honorVo.setRemark(honor.getRemark());
            honorVo.setAttachmentIds(honor.getAttachmentIds());
//            if (ObjectUtil.isNotEmpty(honor.getEmpBy())) {
//                UserBaseInfoVo userBaseInfo = userService.findUserBaseInfo(honor.getEmpBy());
//                honorVo.setEmpName(userBaseInfo.getName());
//            }
        }
        BeanUtils.copyProperties(honor, honorVo);
        return honorVo;
    }

    @Override
    public List<Honor> findList(Honor honor) {
        QueryWrapper<Honor> queryWrapper=new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Honor> findPage(Page page, Honor honor) {
        QueryWrapper<Honor> queryWrapper=new QueryWrapper<>();
        if (ObjectUtil.isNotEmpty(honor.getHonorary())){
            queryWrapper.like("honorary", honor.getHonorary());
        }
        if (ObjectUtil.isNotEmpty(honor.getComBy())){
            queryWrapper.eq("com_by", honor.getComBy());
        }
        if (ObjectUtil.isNotEmpty(honor.getEmpBy())){
            queryWrapper.eq("emp_by", honor.getEmpBy());
        }
        if (ObjectUtil.isNotEmpty(honor.getType())){
            queryWrapper.eq("type", honor.getType());
        }
        IPage<Honor> pageHonor =this.baseMapper.selectPage(page,queryWrapper);
        return pageHonor;
    }

    @Override
    public Page<HonorVo> findGroupHonorPage(Page page, HonorVo honor) {
        String userId = SecurityUtil.getCurrentUser().getUserId();
        UserDetail detail = userDetailService.findByUserId(userId);
        honor.setComBy(detail.getOrgId());
        List<HonorVo> honorPage = baseMapper.findGroupHonorPage(page, honor);
        for (HonorVo h : honorPage) {
            h.setTime(h.getTime().replace("00:00:00", ""));
            if (ObjectUtil.isNotEmpty(h.getAttachmentIds())) {
                h.setAttachments(attachmentsService.getAttachmentListByIds(h.getAttachmentIds()));
//                List<AttachmentsVo> attachments = attachmentsService.findAttachments(new AttachmentsVo() {{
//                    setIds(h.getAttachmentIds());
//                }});
//                h.setAttachments(attachments);
            }
        }
        page.setRecords(honorPage);
        return page;
    }

    @Override
    public Page<HonorVo> findPersonHonorPage(Page page, HonorVo honor) {
        //todo 确定该功能需求是否需要区分个人和管理员权限，是否只需要保留管理员权限即可
//        honor.setEmpBy(SecurityUtil.getCurrentUser().getUserId());
        List<HonorVo> honorPage = baseMapper.findPersonHonorPage(page, honor);
        for (HonorVo h : honorPage) {
            h.setTime(h.getTime().replace("00:00:00", ""));
            if (ObjectUtil.isNotEmpty(h.getAttachmentIds())) {
//                List<AttachmentsVo> attachments = attachmentsService.findAttachments(new AttachmentsVo() {{
//                    setIds(h.getAttachmentIds());
//                }});
                h.setAttachments(attachmentsService.getAttachmentListByIds(h.getAttachmentIds()));
            }
        }
        page.setRecords(honorPage);
        return page;
    }
}
