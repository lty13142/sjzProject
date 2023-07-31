package com.crcm.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.config.event.SystemAreaRefreshEvent;
import com.crcm.admin.mapper.SysAreaMapper;
import com.crcm.admin.model.dto.AreaCoordinatesDTO;
import com.crcm.admin.model.dto.AreaDTO;
import com.crcm.admin.model.entity.SysArea;
import com.crcm.admin.service.SysAreaService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.dto.TreeView;
import com.crcm.core.exception.CustomException;
import com.crcm.core.utils.TreeUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 区域Service业务层处理
 *
 * @author cb
 * @date 2023-04-04
 */
@Service
public class SysAreaServiceImpl extends ServiceImpl<SysAreaMapper, SysArea> implements SysAreaService {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;


    /**
     * 新增区域
     *
     * @param area 区域
     * @return 结果
     */
    @Override
    public String saveArea(SysArea area) {
        int validMenu = this.baseMapper.findValidArea(area);
        if (validMenu > 0) {
            return "名称已存在";
        }
        int validMenuCode = validAreaCode(area);
        if (validMenuCode > 0) {
            return "代码已存在";
        }
        this.baseMapper.insert(area);
        // 发布菜单缓存更新事件
        this.applicationEventPublisher.publishEvent(new SystemAreaRefreshEvent(this));
        return null;
    }


    /**
     * 修改区域
     *
     * @param area 区域
     * @return 结果
     */
    @Override
    @Transactional
    public String updateArea(SysArea area) {
//        int validMenu = this.baseMapper.findValidArea(area);
//        if (validMenu > 0) {
//            return "名称已存在";
//        }
//        int validMenuCode = validAreaCode(area);
//        if (validMenuCode > 0) {
//            return "代码已存在";
//        }
        // 修改子级父代码和父id
        List<SysArea> list = this.baseMapper.findChildList(area);
        if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach(item -> {
                item.setPid(area.getId());
                item.setPcode(area.getCode());
            });
            this.updateBatchById(list);
        }
        this.baseMapper.updateById(area);

        int validMenu = this.baseMapper.findValidArea(area);
        if (validMenu > 1) {
            throw new CustomException("名称已存在");
//            return "名称已存在";
        }
        int validMenuCode = validAreaCode(area);
        if (validMenuCode > 1) {
            throw new CustomException("代码已存在");
//            return "代码已存在";
        }

        // 发布缓存更新事件
        this.applicationEventPublisher.publishEvent(new SystemAreaRefreshEvent(this));
        return null;
    }

    /**
     * 删除区域信息
     *
     * @param id 区域ID
     * @return 结果
     */
    @Override
    public int deleteArea(String id) {
        // 查询该区域是否存在子级
        LambdaQueryWrapper<SysArea> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysArea::getPid, id);
        List<SysArea> list = this.baseMapper.selectList(queryWrapper);
        if (list.size() > 0) {
            List<String> listId = list.stream().map(SysArea::getId).collect(Collectors.toList());
            int i = this.baseMapper.deleteBatchIds(listId);
        }
        // 删除本身数据
        int deleteById = this.baseMapper.deleteById(id);
        // 发布菜单缓存更新事件
        this.applicationEventPublisher.publishEvent(new SystemAreaRefreshEvent(this));
        return deleteById;
    }

    /**
     * 查询区域
     *
     * @param id 区域ID
     * @return 区域
     */
    @Override
    public SysArea findAreaById(String id) {

        //return this.baseMapper.selectById(id);
        return this.baseMapper.findAreaById(id);
    }

    /**
     * 查询区域
     *
     * @param code 区域代码
     * @return 区域
     */
    @Override
    public SysArea findAreaByCode(String code) {
        LambdaQueryWrapper<SysArea> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysArea::getCode, code);
        return this.baseMapper.selectOne(wrapper);
    }

    /**
     * 查询区域列表
     *
     * @param area 区域
     * @return 区域
     */
    @Override
    public List<SysArea> findAreaList(SysArea area) {
        LambdaQueryWrapper<SysArea> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(area.getType()), SysArea::getType, area.getType());
        queryWrapper.eq(StringUtils.isNotBlank(area.getId()), SysArea::getPid, area.getId());
        queryWrapper.orderByAsc(SysArea::getType);
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询区域
     *
     * @param page 分页参数
     * @param area 区域
     * @return 区域
     */
    @Override
    public PageT<SysArea> findAreaPage(PageT<SysArea> page, AreaDTO area) {
        LambdaQueryWrapper<SysArea> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(area.getName()), SysArea::getName, area.getName());
        queryWrapper.like(StringUtils.isNotBlank(area.getCode()), SysArea::getCode, area.getCode());
        queryWrapper.orderByAsc(SysArea::getType);
        return this.page(page, queryWrapper);
    }

    @Override
    public List<TreeView> findTree(AreaDTO t) {
        List<TreeView> viewList = this.baseMapper.findTreeView(t);
        return TreeUtil.listToTree(viewList);
    }

    @Override
    public List<SysArea> findAreas() {
        return this.baseMapper.findAreas();
    }

    @Override
    public List<SysArea> findAreaByType(String type) {
        LambdaQueryWrapper<SysArea> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(type), SysArea::getType, type);
        queryWrapper.orderByAsc(SysArea::getType);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public SysArea getInfoByName(String name) {
        LambdaQueryWrapper<SysArea> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(name), SysArea::getName, name);
        queryWrapper.eq(SysArea::getDeleted, 0);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public void updateCoordinates(List<AreaCoordinatesDTO> area) {
        for (AreaCoordinatesDTO dto : area) {
            this.baseMapper.updateCoordinates(dto);
        }
        // 发布菜单缓存更新事件
        this.applicationEventPublisher.publishEvent(new SystemAreaRefreshEvent(this));
//        for(AreaCoordinatesDTO dto : area){
//            System.out.println(dto.getName());
//        }
    }

    @Override
    public List<SysArea> getSubsetByPid(String pid) {
        LambdaQueryWrapper<SysArea> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysArea::getPid, pid);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<SysArea> getAreaListById(String id) {
        SysArea sysArea = this.baseMapper.selectById(id);
        if (ObjectUtil.isNotEmpty(sysArea)) {
            switch (sysArea.getType()) {
                case "2":
                    LambdaQueryWrapper<SysArea> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.eq(SysArea::getPid, id);
                    return this.baseMapper.selectList(queryWrapper);
                case "3":
                    return new ArrayList<SysArea>() {{
                        add(sysArea);
                    }};
                default:
                    return null;
            }
        }
        return null;
    }


    /**
     * 通过代码验证区域存在数据个数
     *
     * @param area
     * @return
     */
    private int validAreaCode(SysArea area) {
        LambdaQueryWrapper<SysArea> query = new LambdaQueryWrapper<>();
        query.eq(SysArea::getCode, area.getCode());
//        query.eq(Objects.nonNull(area.getPid()), SysArea::getPid, area.getPid());
//        query.eq(Objects.nonNull(area.getPcode()), SysArea::getPcode, area.getPcode());
//        query.ne(Objects.nonNull(area.getId()), SysArea::getId, area.getId());
        return this.baseMapper.selectCount(query);
    }

    /**
     * 根据pCode查询下属区域
     *
     * @param pCode 上一层级的code
     * @return 下属区域列表
     */
    @Override
    public List<SysArea> getByParentCode(String pCode) {
        //根据pCode查询下属区域信息
        return this.baseMapper.selectList(
                Wrappers.lambdaQuery(SysArea.class)
                        .eq(SysArea::getPcode, pCode)
                        .orderByAsc(SysArea::getCode)
        );
    }

    /**
     * 查询所有村级区域数据，按照管区的ASC顺序来ASC
     *
     * @param regionCode 管区code
     * @return 所有村级区域
     */
    @Override
    public List<SysArea> findVillageAsc(String regionCode) {
        return this.baseMapper.findVillageAsc(regionCode);
    }

    /**
     * 查询所有管区区域数据，按照管区CODE的ASC顺序
     *
     * @param regionCode 管区code
     * @return 所有管区区域
     */
    @Override
    public List<SysArea> findRegionAsc(String regionCode) {
        return this.baseMapper.findRegionAsc(regionCode);
    }
}
