package com.crcm.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.admin.model.dto.MenuQueryDTO;
import com.crcm.core.dto.TreeView;
import com.crcm.admin.model.entity.SysMenu;
import com.crcm.admin.model.vo.MenuVO;

import java.util.List;


public interface MenuMapper extends BaseMapper<SysMenu> {


    List<MenuVO> findListByRoleIds(MenuVO vo);

    List<TreeView> findTreeView(MenuQueryDTO t);

    List<MenuVO> selectAdminList(MenuVO menuVo);

    /**
     * 验证菜单存在个数
     * @Author GZL
     * @Date 2023/2/8 15:23
     * @Param
     * @return
     **/
    int findValidMenu(SysMenu sysMenu);
}
