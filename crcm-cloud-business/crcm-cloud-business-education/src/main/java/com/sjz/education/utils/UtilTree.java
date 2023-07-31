package com.sjz.education.utils;

import com.sjz.education.model.vo.PointsRuleTreeView;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName UtilTree
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/26
 **/
public class UtilTree {
    public static List<PointsRuleTreeView> listToTree(List<PointsRuleTreeView> list) {
        ArrayList<PointsRuleTreeView> treeList = new ArrayList();
        list.stream().forEach(view -> {
            if (StringUtils.isBlank(view.getPid())) {
                treeList.add(findChildren(view, list));
            }
        });
        return treeList;
    }

    private static PointsRuleTreeView findChildren(PointsRuleTreeView treeView, List<PointsRuleTreeView> list) {
        Iterator var2 = list.iterator();
        while(var2.hasNext()) {
            PointsRuleTreeView view = (PointsRuleTreeView)var2.next();
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
