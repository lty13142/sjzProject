package com.sjz.evaluations.util;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;

import com.crcm.core.dto.TreeView;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yzw
 * @data 2023/4/6
 * @apiNote
 */
public class UtilTree {
    public UtilTree() {
    }

    public static List<TreeView> listToTree(List<TreeView> list) {
        ArrayList<TreeView> treeList = new ArrayList();
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            TreeView view = (TreeView)var2.next();
            if (StringUtils.isBlank(view.getPid())) {
                treeList.add(findChildren(view, list));
            }
        }

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

                treeView.getChildren().add(findChildren(view, list));
            }
        }

        return treeView;
    }
    //list 转 tree

    //Recursion, building subtree structure

    /**
     * tree 转 list
     * @param flatList   flatList
     * @return list
     */
    public static List<Tree<String>> flatten(List<Tree<String>> flatList){
        List<Tree<String>> results = new ArrayList<>();
        if (flatList.size()>0){
            for (int i=0;i<flatList.size();i++) {
                results.addAll(flatten(flatList.get(i),results));
            }
            return results;
        }
        return null;
    }



    /**
     * . Flatten using a Deque - Double ended Queue
     *
     **/
    private static List<Tree<String>> flatten(Tree<String> node,  List<Tree<String>> flatList) {

        List<Tree<String>> children =(List<Tree<String>>) node.get("next");
        if (children==null){
            flatList.add(node);
            return flatList;
        }
        for (Tree<String> child : children) {
            if(child.get("next") != null) {
                List<Tree<String>> flatten = flatten(child, flatList);
               /* results.addAll(flatten);*/
            }else{
                flatList.add(child);
            }
        }
        return flatList;
    }
}
