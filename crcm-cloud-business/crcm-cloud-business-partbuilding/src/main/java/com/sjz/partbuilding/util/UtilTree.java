package com.sjz.partbuilding.util;

import com.crcm.core.dto.TreeView;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 仅通讯录组织机构人员树--使用
 * </p>
 *
 * @author zyred
 * @createTime 2022/10/13 15:36
 **/
public class UtilTree {
    public static List<TreeView> listToTree(List<TreeView> list) {
        ArrayList<TreeView> treeList = new ArrayList();
        list.stream().forEach(view -> {
            if (StringUtils.isBlank(view.getPid())) {
                treeList.add(findChildren(view, list));
            }
        });
        return treeList;
    }

    private static TreeView findChildren(TreeView treeView, List<TreeView> list) {
        Iterator var2 = list.iterator();
        while(var2.hasNext()) {
            TreeView view = (TreeView)var2.next();
            if (treeView.getId().equals(view.getPid())) {
                if (treeView.getChildren() == null) {
                    treeView.setChildren(new ArrayList());
                }
                //如果是机构数据
//                if("1".equals(view.getType())){
                    treeView.getChildren().add(findChildren(view, list));
//                }

            }
        }
        return treeView;
    }

}
