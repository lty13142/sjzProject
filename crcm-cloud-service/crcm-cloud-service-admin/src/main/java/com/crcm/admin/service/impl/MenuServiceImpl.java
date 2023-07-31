package com.crcm.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.config.event.SystemMenuRefreshEvent;
import com.crcm.admin.mapper.MenuMapper;
import com.crcm.admin.mapper.UserMapper;
import com.crcm.admin.model.dto.MenuEnabledDTO;
import com.crcm.admin.model.dto.MenuQueryDTO;
import com.crcm.admin.model.entity.SysMenu;
import com.crcm.admin.model.entity.SysRole;
import com.crcm.admin.model.entity.SysUser;
import com.crcm.admin.model.vo.MenuVO;
import com.crcm.admin.service.MenuService;
import com.crcm.admin.service.PermissionService;
import com.crcm.admin.service.UserRoleService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.redis.service.RedisService;
import com.crcm.cloud.start.sso.domain.AuthUser;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.SystemBaseConstants;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.constant.enums.ResultCode;
import com.crcm.core.dto.TreeView;
import com.crcm.core.exception.CustomException;
import com.crcm.core.response.RestResult;
import com.crcm.core.utils.TreeUtil;
import com.crcm.file.api.feign.RemoteFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
public class MenuServiceImpl extends ServiceImpl<MenuMapper, SysMenu> implements MenuService {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Resource
    private RedisService redisService;
    @Resource
    private RemoteFileService remoteFileService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private UserMapper userMapper;

    @Override
    public String saveMenu(SysMenu t) {
        int validMenu = this.baseMapper.findValidMenu(t);
        if (validMenu > 0) {
            return "菜单已存在";
        }
        int validMenuCode = validMenuCode(t);
        if (validMenuCode > 0) {
            return "菜单代码已存在";
        }
        this.baseMapper.insert(t);
        // 发布菜单缓存更新事件
        this.applicationEventPublisher.publishEvent(new SystemMenuRefreshEvent(this));
        return null;
    }

    @Override
    public String updateMenu(SysMenu t) {
        int validMenu = this.baseMapper.findValidMenu(t);
        if (validMenu > 0) {
            return "菜单已存在";
        }
        int validMenuCode = validMenuCode(t);
        if (validMenuCode > 0) {
            return "菜单代码已存在";
        }
        this.baseMapper.updateById(t);
        // 发布菜单缓存更新事件
        this.applicationEventPublisher.publishEvent(new SystemMenuRefreshEvent(this));
        return null;
    }

    @Override
    public int deleteById(Serializable id) {
        int deleteById = this.baseMapper.deleteById(id);
        // 发布菜单缓存更新事件
        this.applicationEventPublisher.publishEvent(new SystemMenuRefreshEvent(this));
        // 删除菜单权限
        permissionService.removePermissionByMenuId(id);
        return deleteById;
    }

    @Override
    public SysMenu findById(Serializable id) {
        SysMenu sysMenu = this.baseMapper.selectById(id);
        if (StringUtils.isNotBlank(sysMenu.getIconUrl())
                && redisService.hasKey(SystemBaseConstants.MENU_REDIS_KEY)
                && redisService.hHasKey(SystemBaseConstants.MENU_REDIS_KEY, sysMenu.getId().toString())) {
            sysMenu.setIconUrl(redisService.hget(SystemBaseConstants.MENU_REDIS_KEY, sysMenu.getId().toString()).toString());
        }
        return sysMenu;
    }

    @Override
    public PageT<SysMenu> findPage(PageT<SysMenu> page, MenuQueryDTO t) {
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(t.getCode()), SysMenu::getCode, t.getCode())
                .like(StringUtils.isNotBlank(t.getName()), SysMenu::getName, t.getName())
                .eq(null != t.getType(), SysMenu::getType, t.getType())
                .eq(!ObjectUtils.isEmpty(t.getMenuBelong()), SysMenu::getMenuBelong, t.getMenuBelong())
                .and(StringUtils.isNotBlank(t.getMenuId()), Wrapper -> Wrapper.eq(SysMenu::getId, t.getMenuId())
                        .or().eq(SysMenu::getPid, t.getMenuId()));
        queryWrapper.orderByAsc(SysMenu::getCode);
        return this.page(page, queryWrapper);
    }


    /**
     * 查询角色菜单
     *
     * @param vo 条件
     * @return 角色菜单
     */
    @Override
    public List<MenuVO> findListMenuByRoleIds(MenuVO vo) {
        if (CollectionUtil.isEmpty(vo.getRoleIds())) {
            return new ArrayList<>();
        }
        // 查询菜单
        vo.setType(SystemConstant.MENU_TYPE.MENU);
        HashMap<String, MenuVO> menuMap = new HashMap<>();
        List<MenuVO> menuList = this.baseMapper.findListByRoleIds(vo);
        //去重
        for (MenuVO menu : menuList) {
            menu.setTitle(menu.getName());
            menuMap.put(menu.getId(), menu);
        }
        //排序
        menuList = new ArrayList<>();
        for (String key : menuMap.keySet()) {
            menuList.add(menuMap.get(key));
        }
        menuList.sort(Comparator.comparing(MenuVO::getCode));
        return menuList;
    }

    /**
     * 查询角色按钮
     *
     * @param vo 条件
     * @return 角色按钮
     */
    @Override
    public List<MenuVO> findListButtonByRoleIds(MenuVO vo) {
        if (vo.getRoleIds().isEmpty()) {
            return new ArrayList<>();
        }
        vo.setType(SystemConstant.MENU_TYPE.BUTTON);
        HashMap<String, MenuVO> menuMap = new HashMap<>();
        List<MenuVO> menuList = this.baseMapper.findListByRoleIds(vo);
        //去重
        for (MenuVO menu : menuList) {
            menuMap.put(menu.getId(), menu);
        }
        menuList = new ArrayList<>();
        for (String key : menuMap.keySet()) {
            menuList.add(menuMap.get(key));
        }
        return menuList;
    }

    /**
     * 通过用户角色获取菜单树（构建前端菜单）
     *
     * @param vo 条件
     * @return 菜单树
     */
    @Override
    public Map<String, Object> findMenuTreeByRoleIds(MenuVO vo) {
        vo.setEnabled(SystemConstant.ENABLE_STATUS.ENABLE);
        // 查询菜单
        List<MenuVO> listMenu = findListMenuByRoleIds(vo);
        // 查询按钮
        List<MenuVO> listButton = findListButtonByRoleIds(vo);
        Map<String, List<String>> buttonMap = new HashMap<>();
        for (MenuVO button : listButton) {
            if (buttonMap.containsKey(button.getPid())) {
                List<String> buttons = buttonMap.get(button.getPid());
                buttons.add(button.getCode());
                buttonMap.put(button.getPid(), buttons);
            } else {
                List<String> buttons = new ArrayList<>();
                buttons.add(button.getCode());
                buttonMap.put(button.getPid(), buttons);
            }
        }
        // 菜单redis过期被删除后，重新加载
        if (!redisService.hasKey(SystemBaseConstants.MENU_REDIS_KEY)) {
            initMenuIconPathRedis();
        }
        // 将按钮加入菜单中
        for (MenuVO menuVo : listMenu) {
            if (buttonMap.containsKey(menuVo.getId())) {
                menuVo.setButtons(buttonMap.get(menuVo.getId()));
            }
            if (StringUtils.isNotBlank(menuVo.getIconUrl())
                    && redisService.hasKey(SystemBaseConstants.MENU_REDIS_KEY)
                    && redisService.hHasKey(SystemBaseConstants.MENU_REDIS_KEY, menuVo.getId())) {
                menuVo.setIconUrl(redisService.hget(SystemBaseConstants.MENU_REDIS_KEY, menuVo.getId()).toString());
            }
        }
        List<MenuVO> pcList = listMenu.stream().filter(data -> Objects.equals(SystemConstant.MENU_BELONG.PC, data.getMenuBelong()))
                .collect(Collectors.toList());
        List<MenuVO> appList = listMenu.stream().filter(data -> Objects.equals(SystemConstant.MENU_BELONG.APP, data.getMenuBelong()))
                .collect(Collectors.toList());
        Map<String, Object> result = new HashMap<>();
        result.put("pc", CollectionUtils.isEmpty(pcList) ? new ArrayList<>() : listToTree(pcList));
        result.put("app", CollectionUtils.isEmpty(appList) ? new ArrayList<>() : listToTree(appList));
        return result;
    }


    /**
     * 获取菜单树
     *
     * @param t 菜单
     * @return 菜单树
     */
    @Override
    public List<TreeView> findTree(MenuQueryDTO t) {
        t.setType(SystemConstant.MENU_TYPE.MENU);
        t.setEnabled(SystemConstant.ENABLE_STATUS.ENABLE);
        List<TreeView> viewList = this.baseMapper.findTreeView(t);
        return TreeUtil.listToTree(viewList);
    }

    /**
     * 获取菜单按钮树
     *
     * @param t 菜单按钮
     * @return 菜单按钮树
     */
    @Override
    public List<TreeView> findMenuBtnTree(MenuQueryDTO t) {
        t.setEnabled(SystemConstant.ENABLE_STATUS.ENABLE);
        List<TreeView> viewList = this.baseMapper.findTreeView(t);
        for (TreeView treeView : viewList) {
            if (Objects.equals(String.valueOf(SystemConstant.MENU_TYPE.BUTTON), treeView.getType())) {
                treeView.setIcon("button");
                treeView.setType("button");
            } else {
                treeView.setType("menu");
            }
        }
        return TreeUtil.listToTree(viewList);
    }

    @Override
    public List<SysMenu> findListMenus(MenuQueryDTO t) {
        AuthUser currentUser = SecurityUtil.getCurrentUser();
        if (null == currentUser) {
            throw new CustomException(ResultCode.LOGIN_OUT_OF_DATE.code, ResultCode.LOGIN_OUT_OF_DATE.msg);
        }
        SysUser user = this.findUserById(currentUser.getUserId());
        if (null == user) {
            throw new CustomException(ResultCode.USER_NO_FOUNT.code, ResultCode.USER_NO_FOUNT.msg);
        }
        List<String> roles = new ArrayList<>();
        List<Long> roleIds = new ArrayList<>();
        // 获取用户角色
        List<SysRole> userRoles = userRoleService.findUserRoles(user.getId());
        if (CollectionUtil.isNotEmpty(userRoles)) {
            userRoles.forEach(role -> {
                roles.add(role.getValue());
                roleIds.add(role.getId());
            });
        }
        // 获取用户菜单
        MenuVO menuVo = new MenuVO();
        menuVo.setRoleIds(roleIds);
        Map<String, Object> menuVoList = findMenuTreeByRoleIds(menuVo);
        List<MenuVO> appList = (List<MenuVO>)menuVoList.get("app");
        List<MenuVO> menuVOS = getTreeChangeList(appList);
        List<Long> menusId = menuVOS.stream().map(s -> Long.valueOf(s.getId())).collect(Collectors.toList());
        LambdaQueryWrapper<SysMenu> query = new LambdaQueryWrapper<>();
        query.eq(StringUtils.isNotBlank(t.getCode()), SysMenu::getCode, t.getCode());
        query.eq(Objects.nonNull(t.getType()), SysMenu::getType, t.getType());
        query.eq(Objects.nonNull(t.getMenuBelong()), SysMenu::getMenuBelong, t.getMenuBelong());
        query.eq(Objects.nonNull(t.getMenuPosition()), SysMenu::getMenuPosition, t.getMenuPosition());
        query.orderByAsc(SysMenu::getCode);
        List<SysMenu> list = this.list(query);
        List<SysMenu> collect = list.stream().filter(s -> menusId.contains(s.getId())).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<SysMenu> findMenuByIds(List<String> ids, Integer type) {
        LambdaQueryWrapper<SysMenu> query = new LambdaQueryWrapper<>();
        query.eq(SysMenu::getType, type).in(SysMenu::getId, ids);
        return this.list(query);
    }

    /**
     * 初始化菜单缓存
     *
     * @Author GZL
     * @Date 2023/2/15 17:31
     **/
    @Override
    public void initMenuIconPathRedis() {
        LambdaQueryWrapper<SysMenu> query = new LambdaQueryWrapper<>();
        query.eq(SysMenu::getType, SystemConstant.MENU_TYPE.MENU)
                .isNotNull(SysMenu::getIconUrl)
                .ne(SysMenu::getIconUrl, "");
        List<SysMenu> listMenus = this.list(query);
        redisService.del(SystemBaseConstants.MENU_REDIS_KEY);
        if (CollectionUtils.isEmpty(listMenus)) {
            return;
        }
        listMenus.forEach(menu -> {
            RestResult<String> restResult = remoteFileService.getFilePath(menu.getIconUrl(), AuthConstants.FROM_IN);
            // 只缓存3天，防止图片地址失效
            redisService.hset(SystemBaseConstants.MENU_REDIS_KEY, menu.getId().toString(),
                    menu.getIconUrl(), 3600 * 24 * 3L);
        });
    }

    /**
     * 菜单启用/停用
     *
     * @Author GZL
     * @Date 2023/2/20 10:21
     * @Param dto 菜单启用/停用信息
     **/
    @Override
    public void updateMenuEnabled(MenuEnabledDTO dto) {
        SysMenu menu = new SysMenu();
        BeanUtil.copyProperties(dto, menu);
        this.baseMapper.updateById(menu);
    }

    /**
     * 验证菜单代码
     *
     * @return 代码数量
     * @Author GZL
     * @Date 2023/2/17 17:13
     * @Param sysMenu 菜单信息
     **/
    private int validMenuCode(SysMenu sysMenu) {
        LambdaQueryWrapper<SysMenu> query = new LambdaQueryWrapper<>();
        query.eq(SysMenu::getCode, sysMenu.getCode());
        query.ne(Objects.nonNull(sysMenu.getId()), SysMenu::getId, sysMenu.getId());
        return this.baseMapper.selectCount(query);
    }


    /**
     * 将菜单集合转换成菜单树
     *
     * @param list 菜单集合
     * @return 菜单树
     */
    public List<MenuVO> listToTree(List<MenuVO> list) {
        List<MenuVO> menuList = new ArrayList<>();
        for (MenuVO menu : list) {
            if (StringUtils.isBlank(menu.getPid())) {
                menuList.add(findChildren(menu, list));
            }
        }
        return menuList;
    }

    //判断是否存在子集
    private boolean ifChilds(List<?> list) {
        boolean flag = false;
        if (list != null && list.size() != 0) {
            flag = true;
        }

        return flag;
    }

    private List<MenuVO> getTreeChangeList(List<MenuVO> lists){
        List<MenuVO> list = new ArrayList<>();
        lists.forEach(o->{
            this.treeToList(o,list);
        });
        return list;
    }

    /**
     * 将tree结构数据转成List结构
     * @param list
     * @return
     */
    public void treeToList(MenuVO node,List<MenuVO> list){
        if(list==null){
            list=new ArrayList<MenuVO>();
        }
        list.add(node);
        //遍历递归子节点
        if(ifChilds(node.getChildren())){
            for (int i = 0; i < node.getChildren().size(); i++) {
                MenuVO node_= node.getChildren().get(i);
                treeToList(node_,list);
            }
        }
    }

    public MenuVO findChildren(MenuVO menuVo, List<MenuVO> list) {
        for (MenuVO menu : list) {
            if (menuVo.getId().equals(menu.getPid())) {
                if (menuVo.getChildren() == null) {
                    menuVo.setChildren(new ArrayList<>());
                }
                menuVo.getChildren().add(findChildren(menu, list));
            }
        }
        return menuVo;
    }

    public SysUser findUserById(String id) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getId, id);
        SysUser user = userMapper.selectOne(queryWrapper);
        return user;
    }

}
