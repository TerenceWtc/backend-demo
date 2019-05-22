package org.terence.backend.service.vo.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.terence.backend.dao.entity.base.TreeNode;

/**
 * @author terence
 * @since 2019/3/1 17:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuVo extends TreeNode {

    private String title;
    private String code;
//    private String href;
    private String icon;

    public MenuVo() {
    }

    public MenuVo(int id, String name, int parentId) {
        this.id = id;
        this.parentId = parentId;
        this.title = name;
    }

    public MenuVo(int id, String name, MenuVo parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.title = name;
    }
}
