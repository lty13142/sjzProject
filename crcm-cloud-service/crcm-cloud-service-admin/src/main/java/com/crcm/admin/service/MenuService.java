package com.crcm.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.admin.model.dto.MenuEnabledDTO;
import com.crcm.admin.model.dto.MenuQueryDTO;
import com.crcm.admin.model.entity.SysMenu;
import com.crcm.admin.model.vo.MenuVO;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.dto.TreeView;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface MenuService extends IService<SysMenu> {


    String saveMenu(SysMenu t);

    String updateMenu(SysMenu t);

    int deleteById(Serializable id);

    SysMenu findById(Serializable id);

    PageT<SysMenu> findPage(PageT<SysMenu> page, MenuQueryDTO t);

    List<MenuVO> findListMenuByRoleIds(MenuVO vo);

    List<MenuVO> findListButtonByRoleIds(MenuVO vo);

    Map<String, Object> findMenuTreeByRoleIds(MenuVO vo);

    List<TreeView> findTree(MenuQueryDTO t);

    List<TreeView> findMenuBtnTree(MenuQueryDTO t);

    List<SysMenu> findListMenus(MenuQueryDTO t);

    /**
     * 通过id集合查询菜单信息
     *
     * @param ids id集合
     * @return 菜单列表
     */
    List<SysMenu> findMenuByIds(List<String> ids, Integer type);

    /**
     * 初始化菜单在线图标redis
     * @Author GZL
     * @Date 2023/2/15 14:19
     **/
    void initMenuIconPathRedis();

    /**
     * 菜单启用/停用
     * @Author GZL
     * @Date 2023/2/20 10:21
     * @Param dto 菜单启用/停用信息
     **/
    void updateMenuEnabled(MenuEnabledDTO dto);
}
