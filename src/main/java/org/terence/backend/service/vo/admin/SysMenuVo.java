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
public class SysMenuVo extends TreeNode {

    private String title;
    private String code;
    /// this attribute is temporarily not used
    /**
     *private String href;
     */

    private String icon;

    public SysMenuVo() {
    }

    public SysMenuVo(int id, String name, int parentId) {
        this.id = id;
        this.parentId = parentId;
        this.title = name;
    }

    public SysMenuVo(int id, String name, SysMenuVo parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.title = name;
    }
}
