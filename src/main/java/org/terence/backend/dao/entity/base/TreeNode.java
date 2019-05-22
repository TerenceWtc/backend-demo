package org.terence.backend.dao.entity.base;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author terence
 * @since 2019/5/21 11:36
 */
@Data
public class TreeNode {

    protected int id;
    protected int parentId;
    protected List<TreeNode> children = new ArrayList<TreeNode>();

    public void add(TreeNode node){
        children.add(node);
    }
}
