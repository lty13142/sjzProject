package com.crcm.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.mapper.ResourceMapper;
import com.crcm.admin.model.entity.SysResource;
import com.crcm.admin.service.ResourceService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.dto.TreeView;
import com.crcm.core.exception.CustomException;
import com.crcm.core.utils.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, SysResource> implements ResourceService {


    @Override
    public boolean update(SysResource resource) {
        return this.updateById(resource);
    }

    @Override
    public boolean deleteById(Serializable id) {
        return this.removeById(id);
    }

    @Override
    public int realDelete(Serializable id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public SysResource findById(Serializable id) {
        return this.getById(id);
    }

    @Override
    public List<SysResource> findList(SysResource t) {
        LambdaQueryWrapper<SysResource> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(null != t.getPid(), SysResource::getPid, t.getPid())
                .eq(null != t.getType(), SysResource::getType, t.getType());
        return this.list(wrapper);
    }

    /**
     * 新增功能
     *
     * @param t 资源
     */
    @Override
    public boolean addResource(SysResource t) {
        if (Objects.isNull(t.getType())) {
            t.setType(SystemConstant.RESOURCE_TYPE.RESOURCE);
        }
        if (StringUtils.isNotBlank(t.getValue())) {
            SysResource byValue = findByValue(t.getValue());
            if (Objects.nonNull(byValue)) {
                throw new CustomException("资源值已存在，请不要重复添加！");
            }
        }
        return this.save(t);
    }

    /**
     * 新增功能目录
     *
     * @param t 功能目录
     */
    @Override
    public boolean addResourceList(SysResource t) {
        t.setType(SystemConstant.RESOURCE_TYPE.LIST);
        return this.save(t);
    }


    @Override
    public PageT<SysResource> findPage(PageT<SysResource> page, SysResource t) {
        LambdaQueryWrapper<SysResource> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(null != t.getType(), SysResource::getType, t.getType())
                .like(StringUtils.isNotBlank(t.getName()), SysResource::getName, t.getName())
                .like(StringUtils.isNotBlank(t.getValue()), SysResource::getValue, t.getValue())
                .eq(null != t.getPid(), SysResource::getPid, t.getPid());
        return this.page(page, wrapper);
    }

    @Override
    public List<TreeView> findTree(SysResource t) {
        List<TreeView> list = this.baseMapper.findTree(t);
        return TreeUtil.listToTree(list);
    }

    @Override
    public List<SysResource> findUserResources(String userId) {
        List<SysResource> resources = this.baseMapper.selectUserResources(userId);
        // 去重
        if (CollectionUtil.isNotEmpty(resources)) {
            return resources.stream().filter(distinctByKey(SysResource::getId)).collect(Collectors.toList());
        }
        return resources;
    }

    @Override
    public SysResource findByValue(String value) {
        LambdaQueryWrapper<SysResource> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysResource::getValue, value);
        List<SysResource> list = this.list(wrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 指定属性去重
     *
     * @param keyExtractor key
     * @param <T>          泛型
     * @return 结果
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
