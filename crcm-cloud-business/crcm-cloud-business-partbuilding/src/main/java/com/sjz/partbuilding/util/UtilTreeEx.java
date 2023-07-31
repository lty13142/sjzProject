package com.sjz.partbuilding.util;

import com.sjz.partbuilding.model.vo.TreeViewVo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UtilTreeEx {
    public UtilTreeEx() {
    }

    public static List<TreeViewVo> listToTree(List<TreeViewVo> list) {
        ArrayList<TreeViewVo> treeList = new ArrayList();
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            TreeViewVo view = (TreeViewVo)var2.next();
            if (StringUtils.isBlank(view.getPid())) {
                treeList.add(findChildren(view, list));
            }
        }

        return treeList;
    }

    private static TreeViewVo findChildren(TreeViewVo treeView, List<TreeViewVo> list) {
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            TreeViewVo view = (TreeViewVo)var2.next();
            if (treeView.getId().equals(view.getPid())) {
                if (treeView.getChildren() == null) {
                    treeView.setChildren(new ArrayList());
                }

                treeView.getChildren().add(findChildren(view, list));
            }
        }
        return treeView;
    }
}
